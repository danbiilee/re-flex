package item.model.dao;

import static common.JDBCTemplate.close;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import item.model.exception.ItemException;
import item.model.vo.Item;
import item.model.vo.ItemImage;
import item.model.vo.ItemQna;
import item.model.vo.ItemQnaAns;

public class ItemDAO {
	private Properties prop = new Properties();
	
	public ItemDAO() {
		String fileName = ItemDAO.class.getResource("/sql/item/item-query.properties").getPath();
		try {
			prop.load(new FileReader(new File(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Item> selectItemAll(Connection conn, String categoryNo, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemAllByPaging");
		List<Item> list = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, categoryNo);
			pstmt.setInt(2, (cPage-1)*numPerPage + 1); //startNo
			pstmt.setInt(3, numPerPage*cPage); //endNo
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Item item = new Item();
				item.setItemNo(rset.getInt("item_no"));
				item.setCategoryNo(categoryNo);
				item.setItemStock(rset.getInt("item_stock"));
				item.setItemBrand(rset.getString("item_brand"));
				item.setItemName(rset.getString("item_name"));
				item.setItemPrice(rset.getInt("item_price"));
				item.setItemDesc(rset.getString("item_desc"));
				item.setItemEnrollDate(rset.getDate("item_enroll_date"));
				list.add(item);
			}
			System.out.println("listByPaging@dao="+list);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품목록조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Item> selectItemAllByLowPrice(Connection conn, Map<String, Object> paramMap) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemAllByLowPrice");
		List<Item> list = null;
		int cPage = (int)paramMap.get("cPage");
		int numPerPage = (int)paramMap.get("numPerPage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, (String)paramMap.get("categoryNo"));
			pstmt.setInt(2, (cPage-1)*numPerPage + 1); //startNo
			pstmt.setInt(3, numPerPage*cPage); //endNo
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Item item = new Item();
				item.setItemNo(rset.getInt("item_no"));
				item.setCategoryNo("category_no");
				item.setItemStock(rset.getInt("item_stock"));
				item.setItemBrand(rset.getString("item_brand"));
				item.setItemName(rset.getString("item_name"));
				item.setItemPrice(rset.getInt("item_price"));
				item.setItemDesc(rset.getString("item_desc"));
				item.setItemEnrollDate(rset.getDate("item_enroll_date"));
				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품목록 낮은가격순 조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Item> selectItemAllByHighPrice(Connection conn, Map<String, Object> paramMap) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemAllByHighPrice");
		List<Item> list = null;
		int cPage = (int)paramMap.get("cPage");
		int numPerPage = (int)paramMap.get("numPerPage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, (String)paramMap.get("categoryNo"));
			pstmt.setInt(2, (cPage-1)*numPerPage + 1); //startNo
			pstmt.setInt(3, numPerPage*cPage); //endNo
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Item item = new Item();
				item.setItemNo(rset.getInt("item_no"));
				item.setCategoryNo("category_no");
				item.setItemStock(rset.getInt("item_stock"));
				item.setItemBrand(rset.getString("item_brand"));
				item.setItemName(rset.getString("item_name"));
				item.setItemPrice(rset.getInt("item_price"));
				item.setItemDesc(rset.getString("item_desc"));
				item.setItemEnrollDate(rset.getDate("item_enroll_date"));
				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품목록 높은가격순 조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<ItemImage> selectItemImageList(Connection conn, int itemNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemImageList");
		List<ItemImage> list = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				ItemImage img = new ItemImage();
				img.setItemImageNo(rset.getInt("item_image_no"));
				img.setItemNo(itemNo);
				img.setItemImageTypeNo(rset.getString("item_image_type_no"));
				img.setItemImageDefault(rset.getString("item_image_default"));
				img.setItemImageRenamed(rset.getString("item_image_renamed"));
				list.add(img);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품이미지조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalContent(Connection conn, String categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContent");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, categoryNo);
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품 총개수 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public List<Item> selectItemAllByLowPrice(Connection conn, String categoryNo, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemAllByLowPrice");
		List<Item> list = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, categoryNo);
			pstmt.setInt(2, (cPage-1)*numPerPage + 1); //startNo
			pstmt.setInt(3, numPerPage*cPage); //endNo
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Item item = new Item();
				item.setItemNo(rset.getInt("item_no"));
				item.setCategoryNo(categoryNo);
				item.setItemStock(rset.getInt("item_stock"));
				item.setItemBrand(rset.getString("item_brand"));
				item.setItemName(rset.getString("item_name"));
				item.setItemPrice(rset.getInt("item_price"));
				item.setItemDesc(rset.getString("item_desc"));
				item.setItemEnrollDate(rset.getDate("item_enroll_date"));
				list.add(item);
			}
			System.out.println("listByLowPrice@dao="+list);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("낮은가격순 조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Item selectItemOne(Connection conn, int itemNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemOne");
		Item item = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				item = new Item();
				item.setItemNo(rset.getInt("item_no"));
				item.setCategoryNo(rset.getString("category_no"));
				item.setItemStock(rset.getInt("item_stock"));
				item.setItemBrand(rset.getString("item_brand"));
				item.setItemName(rset.getString("item_name"));
				item.setItemPrice(rset.getInt("item_price"));
				item.setItemDesc(rset.getString("item_desc"));
				item.setItemEnrollDate(rset.getDate("item_enroll_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품상세 조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return item;
	}
	
	
	
	//===============================================
	//==================<<아이템Q&A>>==================
	//===============================================
	
	public int insertItemQna(Connection conn, Map<String, Object> paramMap) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertItemQna");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (int)paramMap.get("itemNo"));
			pstmt.setString(2, String.valueOf(paramMap.get("memberId")));
			pstmt.setString(3, String.valueOf(paramMap.get("qnaContent")));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품Q&A등록 에러!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<ItemQna> selectItemQnaList(Connection conn, int itemNo, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemQnaList");
		List<ItemQna> qnaList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rset = pstmt.executeQuery();
			
			qnaList = new ArrayList<>();
			while(rset.next()) {
				ItemQna q = new ItemQna();
				q.setItemQnaNo(rset.getInt("item_qna_no"));
				q.setMemberId(rset.getString("member_id"));
				q.setItemNo(rset.getInt("item_no"));
				q.setItemQnaContent(rset.getString("item_qna_content"));
				q.setItemQnaDate(rset.getDate("item_qna_date"));
				q.setItemQnaAnsYn(rset.getString("item_qna_ans_yn"));
				qnaList.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품Q&A조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return qnaList;
	}

	public ItemQnaAns selectIteQnaAnsOne(Connection conn, int itemQnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectIteQnaAnsOne");
		ItemQnaAns qnaAns = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemQnaNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qnaAns = new ItemQnaAns();
				qnaAns.setItemQnaAnsNo(rset.getInt("item_qna_ans_no"));
				qnaAns.setItemQnaNo(rset.getInt("item_qna_no"));
				qnaAns.setItemQnaAnsWriter(rset.getString("item_qna_ans_writer"));
				qnaAns.setItemQnaAnsContent(rset.getString("item_qna_ans_content"));
				qnaAns.setItemQnaAnsDate(rset.getDate("item_qna_ans_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품Q&A 답변조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return qnaAns;
	}

	public int selectItemQnaCount(Connection conn, int itemNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemQnaCount");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품Q&A 게시글수 조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	
	//===================================================
	//==================<<아이템Q&A 끝>>==================
	//===================================================
	
	
	
	
		
	//============================================
	//==================<<관리자>>==================
	//============================================
	
	public List<Item> selectItemList(Connection conn, String categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemList");
		List<Item> list = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, categoryNo);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Item item = new Item();
				item.setItemNo(rset.getInt("item_no"));
				item.setCategoryNo(categoryNo);
				item.setItemStock(rset.getInt("item_stock"));
				item.setItemBrand(rset.getString("item_brand"));
				item.setItemName(rset.getString("item_name"));
				item.setItemPrice(rset.getInt("item_price"));
				item.setItemDesc(rset.getString("item_desc"));
				item.setItemEnrollDate(rset.getDate("item_enroll_date"));
				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품목록조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int enrollItem(Connection conn, Item item) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("enrollItem");
		try {
			//1. prepareStatment객체 생성
			pstmt = conn.prepareStatement(query);
			
			//2.쿼리 실행
			pstmt.setString(1, item.getCategoryNo());
			pstmt.setInt(2, item.getItemStock());
			pstmt.setString(3, item.getItemBrand());
			pstmt.setString(4, item.getItemName());
			pstmt.setInt(5, item.getItemPrice());
			pstmt.setString(6, item.getItemDesc());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectItemLastNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int itemNo = 0;
		String query = prop.getProperty("itemLastNo");
		System.out.println(query);
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				itemNo = rset.getInt(1);
			}
			System.out.println("itemNo="+itemNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return itemNo;
	}

	public int enrollImage(Connection conn, ItemImage itemImg) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("enrollImage");
		try {
			//1. prepareStatment객체 생성
			pstmt = conn.prepareStatement(query);
			
			//2.쿼리 실행
			pstmt.setInt(1, itemImg.getItemNo());
			pstmt.setString(2, itemImg.getItemImageTypeNo());
			System.out.println("typeNo@dao="+itemImg.getItemImageTypeNo());
			pstmt.setString(3, itemImg.getItemImageDefault());
			pstmt.setString(4, itemImg.getItemImageRenamed());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Item selectOne(Connection conn, int itemNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Item item = null;
		String query = prop.getProperty("selectOne");
		System.out.println(query);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				item = new Item();
				item.setItemNo(rset.getInt("item_no"));
				item.setCategoryNo(rset.getString("category_no"));
				item.setItemStock(rset.getInt("item_stock"));
				item.setItemBrand(rset.getString("item_brand"));
				item.setItemName(rset.getString("item_name"));
				item.setItemPrice(rset.getInt("item_price"));
				item.setItemDesc(rset.getString("item_desc"));
				item.setItemEnrollDate(rset.getDate("item_enroll_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return item;
	}

	public int updateItem(Connection conn, Item item) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateItem");
		try {
			//1. prepareStatment객체 생성
			pstmt = conn.prepareStatement(query);
			
			//2.쿼리 실행
			pstmt.setString(1, item.getCategoryNo());
			pstmt.setInt(2, item.getItemStock());
			pstmt.setString(3, item.getItemBrand());
			pstmt.setString(4, item.getItemName());
			pstmt.setInt(5, item.getItemPrice());
			pstmt.setString(6, item.getItemDesc());
			pstmt.setInt(7, item.getItemNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ItemImage selectImageOne(Connection conn, int itemMainImageNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ItemImage itemImg = null;
		String query = prop.getProperty("selectImageOne");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemMainImageNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				itemImg = new ItemImage();
				itemImg.setItemImageNo(rset.getInt("item_image_no"));
				itemImg.setItemNo(rset.getInt("item_no"));
				itemImg.setItemImageTypeNo(rset.getString("item_image_type_no"));
				itemImg.setItemImageDefault(rset.getString("item_image_default"));
				itemImg.setItemImageRenamed(rset.getString("item_image_renamed"));
			}
			System.out.println("itemImg="+itemImg);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return itemImg;
	}

	public int updateImage(Connection conn, ItemImage itemImg) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateImage");
		try {
			//1. prepareStatment객체 생성
			pstmt = conn.prepareStatement(query);
			
			//2.쿼리 실행
			pstmt.setString(1, itemImg.getItemImageDefault());
			pstmt.setString(2, itemImg.getItemImageRenamed());
			pstmt.setInt(3, itemImg.getItemImageNo());
			System.out.println(itemImg.getItemImageDefault());
			System.out.println(itemImg.getItemImageRenamed());
			System.out.println(itemImg.getItemImageNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int deleteItem(Connection conn, int itemNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteItem");
		try {
			//1. prepareStatment객체 생성
			pstmt = conn.prepareStatement(query);
			
			//2.쿼리 실행
			pstmt.setInt(1, itemNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	//============================================
	//==================<<관리자끝>>==================
	//============================================

	

	//================================================
	//========================헤더 검색=================
	//================================================
	
	public int selectTotalContentBySearch(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentBySearch");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			System.out.println("totalContent@dao="+totalContent);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품 총개수 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public List<Item> selectItemAllBySearch(Connection conn, String search, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemAllBySearchByPaging");
		List<Item> list = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage + 1); //startNo
			pstmt.setInt(3, numPerPage*cPage); //endNo
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Item item = new Item();
				item.setItemNo(rset.getInt("item_no"));
				item.setCategoryNo(rset.getString("category_no"));
				item.setItemStock(rset.getInt("item_stock"));
				item.setItemBrand(rset.getString("item_brand"));
				item.setItemName(rset.getString("item_name"));
				item.setItemPrice(rset.getInt("item_price"));
				item.setItemDesc(rset.getString("item_desc"));
				item.setItemEnrollDate(rset.getDate("item_enroll_date"));
				list.add(item);
			}
			System.out.println("listByPaging@dao="+list);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품목록조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Item> selectItemAllBySearchByLowPrice(Connection conn, String search, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemAllBySearchByLowPrice");
		List<Item> list = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage + 1); //startNo
			pstmt.setInt(3, numPerPage*cPage); //endNo
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				Item item = new Item();
				item.setItemNo(rset.getInt("item_no"));
				item.setCategoryNo(rset.getString("category_no"));
				item.setItemStock(rset.getInt("item_stock"));
				item.setItemBrand(rset.getString("item_brand"));
				item.setItemName(rset.getString("item_name"));
				item.setItemPrice(rset.getInt("item_price"));
				item.setItemDesc(rset.getString("item_desc"));
				item.setItemEnrollDate(rset.getDate("item_enroll_date"));
				list.add(item);
			}
			System.out.println("listByLowPrice@dao="+list);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("낮은가격순 조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<ItemImage> selectItemMainImageList(Connection conn, Integer itemNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemMainImageList");
		List<ItemImage> list = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				ItemImage img = new ItemImage();
				img.setItemImageNo(rset.getInt("item_image_no"));
				img.setItemNo(itemNo);
				img.setItemImageTypeNo(rset.getString("item_image_type_no"));
				img.setItemImageDefault(rset.getString("item_image_default"));
				img.setItemImageRenamed(rset.getString("item_image_renamed"));
				list.add(img);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("상품이미지조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	//===================================================
	//========================헤더 검색 끝=================
	//===================================================
	
	
	
	/////////////////////////////////////////////////////////////상품주문 임시 포인트조회
	public int selectMemberUsablePoint(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberUsablePoint");
		int usablePoint = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				usablePoint = rset.getInt("sum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ItemException("사용가능한 포인트 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return usablePoint;
	}

	
}
