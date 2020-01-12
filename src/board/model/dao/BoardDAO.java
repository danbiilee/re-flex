   package board.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import board.model.vo.Board;
import order.model.vo.OrderDetail;
import order.model.vo.OrderDetail3;

public class BoardDAO {

	private Properties prop = new Properties();
	
	public BoardDAO() {
		String fileName = BoardDAO.class
								  .getResource("/sql/board/board-query.properties")
								  .getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Board> selectBoardListAll(Connection conn, int cPage, int numPerPage) {
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardListAll");
		List<Board> list = new ArrayList<>();

		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(2, cPage*numPerPage);//end rownum
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b1=new Board();
			
				b1.setReview_no(rset.getInt("review_no"));
				b1.setOrder_details_no(rset.getInt("order_detail_no"));
				b1.setReview_writer(rset.getString("review_writer"));
				b1.setReview_date(rset.getDate("review_date"));
				b1.setReview_star(rset.getInt("review_star"));
				b1.setReview_content(rset.getString("review_content"));
				b1.setReview_image(rset.getString("review_image"));
				b1.setReview_image_rename(rset.getString("review_image_rename"));
				b1.setReview_readCount(rset.getInt("review_readCount"));
				b1.setItem_no(rset.getInt("item_no"));
				//댓글수 필드 추가
				//b.setCommentCnt(rset.getInt("comment_cnt"));
				
				list.add(b1);
			}
//			System.out.println("list@dao="+list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Board> selectBoardList(Connection conn, int itemNo, int cPage, int numPerPage) {
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardList");
		List<Board> list = new ArrayList<>();

		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);//start rownum
			pstmt.setInt(2, (cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b1=new Board();
			
				b1.setReview_no(rset.getInt("review_no"));
				b1.setOrder_details_no(rset.getInt("order_detail_no"));
				b1.setReview_writer(rset.getString("review_writer"));
				b1.setReview_date(rset.getDate("review_date"));
				b1.setReview_star(rset.getInt("review_star"));
				b1.setReview_content(rset.getString("review_content"));
				b1.setReview_image(rset.getString("review_image"));
				b1.setReview_readCount(rset.getInt("review_readCount"));
				b1.setItem_no(rset.getInt("item_no"));
				//댓글수 필드 추가
				//b.setCommentCnt(rset.getInt("comment_cnt"));
				
				list.add(b1);
			}
//			System.out.println("list@dao="+list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public int selectBoardCountAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardCountAll");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public int selectBoardCount(Connection conn, int itemNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardCount");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}


	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBoard");
		int result = 0;
		//INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT)
		
		try {
			pstmt = conn.prepareStatement(query);
			

	

			pstmt.setInt(1, b.getOrder_details_no());
			pstmt.setString(2, b.getReview_writer());	
			pstmt.setInt(3, b.getReview_star());
			pstmt.setString(4, b.getReview_content());
			pstmt.setString(5, b.getReview_image());
			pstmt.setString(6, b.getReview_image_rename());
			pstmt.setInt(7, b.getItem_no());
			
			
		
			
	
			
			result = pstmt.executeUpdate();
			
			
			//System.out.println("result@dao="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	 public Board selectOne(Connection conn, int reviewNo) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, reviewNo);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				board = new Board();
				board.setReview_no(rset.getInt("review_no"));
				board.setOrder_details_no(rset.getInt("order_detail_no"));
				board.setReview_writer(rset.getNString("review_writer"));
				board.setReview_date(rset.getDate("review_date"));
				board.setReview_star(rset.getInt("review_star"));
				board.setReview_content(rset.getString("review_content"));
				board.setReview_image(rset.getString("review_image"));
				board.setReview_readCount(rset.getInt("review_readCount"));
				
			}
			//System.out.println("board@DAO="+board);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return board;
	}


	public int increaseReadCount(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("increaseReadCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBoard(Connection conn, int Review_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, Review_no);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		
		return result;
	}
	
	public int updateBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateBoard"); 
		System.out.println("update@DAO@@Board=="+b);
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, b.getReview_star());
			pstmt.setString(2, b.getReview_content());
			pstmt.setString(3, b.getReview_image());
			pstmt.setString(4, b.getReview_image_rename());
			pstmt.setInt(5, b.getReview_no());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			System.out.println("update@DAO="+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int selectLastSeq(Connection conn) {
		int boardNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectLastSeq");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getInt("currval");
			}
			System.out.println("새로발급받은 게시글번호@dao="+boardNo);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return boardNo;
	}


	

	
	public int deleteBoardComment(Connection conn, int boardCommentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoardComment"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, boardCommentNo);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public List<OrderDetail3> selectBoardList2(Connection conn, String memberId) {
		OrderDetail3 o = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardList2");
		List<OrderDetail3> list2 = new ArrayList<>();

	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);//start rownum
		
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				o=new OrderDetail3();
				o.setItemNo(rset.getInt("item_no"));
				o.setOrderDetailNo(rset.getInt("order_detail_no"));
				o.setItemBrand(rset.getString("item_brand"));
				o.setItemName(rset.getString("item_name"));
				o.setCategoryNo(rset.getString("category_no"));
			
				
				
				//댓글수 필드 추가
				//b.setCommentCnt(rset.getInt("comment_cnt"));
				
				list2.add(o);
				
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list2;
	}


}






