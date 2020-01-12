package item.model.service;

import static common.JDBCTemplate.*;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import item.model.dao.ItemDAO;
import item.model.vo.Item;
import item.model.vo.ItemImage;
import item.model.vo.ItemQna;
import item.model.vo.ItemQnaAns;

public class ItemService {

	public List<Item> selectItemAll(String categoryNo, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Item> list = new ItemDAO().selectItemAll(conn, categoryNo, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public List<Item> selectItemAllByLowPrice(Map<String, Object> paramMap) {
		Connection conn = getConnection();
		List<Item> list = new ItemDAO().selectItemAllByLowPrice(conn, paramMap);
		close(conn);
		return list;
	}
	
	public List<Item> selectItemAllByHighPrice(Map<String, Object> paramMap) {
		Connection conn = getConnection();
		List<Item> list = new ItemDAO().selectItemAllByHighPrice(conn, paramMap);
		close(conn);
		return list;
	}

	public List<ItemImage> selectItemImageList(int itemNo) {
		Connection conn = getConnection();
		List<ItemImage> list = new ItemDAO().selectItemImageList(conn, itemNo);
		close(conn);
		return list;
	}

	public int selectTotalContent(String categoryNo) {
		Connection conn = getConnection();
		int totalContent = new ItemDAO().selectTotalContent(conn, categoryNo);
		close(conn);

		return totalContent;
	}

	public List<Item> selectItemAllByLowPrice(String categoryNo, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Item> list = new ItemDAO().selectItemAllByLowPrice(conn, categoryNo, cPage, numPerPage);
		close(conn);
		return list;
	}

	public Item selectItemOne(int itemNo) {
		Connection conn = getConnection();
		Item item = new ItemDAO().selectItemOne(conn, itemNo);
		close(conn);
		return item;
	}

	public int insertItemQna(Map<String, Object> paramMap) {
		Connection conn = getConnection();
		int result = new ItemDAO().insertItemQna(conn, paramMap);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<ItemQna> selectItemQnaList(int itemNo, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<ItemQna> qnaList = new ItemDAO().selectItemQnaList(conn, itemNo, cPage, numPerPage);
		close(conn);
		return qnaList;
	}

	public ItemQnaAns selectIteQnaAnsOne(int itemQnaNo) {
		Connection conn = getConnection();
		ItemQnaAns qnaAns = new ItemDAO().selectIteQnaAnsOne(conn, itemQnaNo);
		close(conn);
		return qnaAns;
	}
	
	public int selectItemQnaCount(int itemNo) {
		Connection conn = getConnection();
		int totalContent = new ItemDAO().selectItemQnaCount(conn, itemNo);
		close(conn);
		return totalContent;
	}
	
	
	//============================================
	//==================<<관리자>>==================
	//============================================
	
	
	
	public List<Item> selectItemList(String categoryNo) {
		Connection conn = getConnection();
		List<Item> list = new ItemDAO().selectItemList(conn, categoryNo);
		close(conn);
		return list;
	}

	public int enrollItem(Item item) {
		Connection conn = getConnection();
		int result = new ItemDAO().enrollItem(conn, item);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}

	public int selectItemLastNo() {
		Connection conn = getConnection();
		int itemNo = new ItemDAO().selectItemLastNo(conn);
		close(conn);
		return itemNo;
	}

	public int enrollImage(ItemImage itemImg) {
		Connection conn = getConnection();
		int result = new ItemDAO().enrollImage(conn,itemImg);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}

	public Item selectOne(int itemNo) {
		Connection conn = getConnection();
		Item item = new ItemDAO().selectOne(conn,itemNo);
		close(conn);
		return item;
	}

	public int updateItem(Item item) {
		Connection conn = getConnection();
		int result = new ItemDAO().updateItem(conn, item);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}


	public ItemImage selectImageOne(int itemMainImageNo) {
		Connection conn = getConnection();
		ItemImage itemImg = new ItemDAO().selectImageOne(conn,itemMainImageNo);
		close(conn);
		return itemImg;
	}

	public int updateImage(ItemImage itemImg) {
		Connection conn = getConnection();
		int result = new ItemDAO().updateImage(conn, itemImg);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}

	public int deleteItem(int itemNo) {
		Connection conn = getConnection();
		int result = new ItemDAO().deleteItem(conn, itemNo);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}
	
	//////////////////////////////////////////////////////주문 임시조회용
	public int selectMemberUsablePoint(String memberId) {
		Connection conn = getConnection();
		int usablePoint = new ItemDAO().selectMemberUsablePoint(conn, memberId);
		close(conn);
		return usablePoint;
	}
	
	
	//========================헤더 검색=================
	
	public int selectTotalContentBySearch(String search) {
		Connection conn = getConnection();
		int totalContent = new ItemDAO().selectTotalContentBySearch(conn, search);
		close(conn);
		return totalContent;
	}

	public List<Item> selectItemAllBySearch(String search, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Item> list = new ItemDAO().selectItemAllBySearch(conn, search, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Item> selectItemAllBySearchByLowPrice(String search, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Item> list = new ItemDAO().selectItemAllBySearchByLowPrice(conn, search, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<ItemImage> selectItemMainImageList(Integer itemNo) {
		Connection conn = getConnection();
		List<ItemImage> list = new ItemDAO().selectItemMainImageList(conn, itemNo);
		close(conn);
		return list;
	}
	
	//========================헤더 검색 끝=================
}
