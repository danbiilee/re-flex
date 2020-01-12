package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDAO;
import board.model.vo.Board;
import order.model.vo.OrderDetail3;


public class BoardService {
	
	public List<Board> selectBoardListAll(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> list 
			= new BoardDAO().selectBoardListAll(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Board> selectBoardList(int itemNo, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> list 
			= new BoardDAO().selectBoardList(conn, itemNo, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int selectBoardCountAll() {
		Connection conn = getConnection();
		int totalContent = new BoardDAO().selectBoardCountAll(conn);
		close(conn);
		return totalContent;
	}

	public int selectBoardCount(int itemNo) {
		Connection conn = getConnection();
		int totalContent = new BoardDAO().selectBoardCount(conn, itemNo);
		close(conn);
		return totalContent;
	}

	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertBoard(conn, b);
		//트랜잭션 처리
		if(result>0) {
			//새로 발급된 게시글번호를 가져와서 board객체에 대입
//			b.setReview_no(new BoardDAO().selectLastSeq(conn));
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	public Board selectOne(int reviewNo) {
		Connection conn = getConnection();
		Board board = new BoardDAO().selectOne(conn, reviewNo);
		close(conn);
		return board;
	}
	
	
	
	 public int deleteBoard(int Review_no) {
		Connection conn = getConnection();
		int result = new BoardDAO().deleteBoard(conn, Review_no);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}


	public int updateBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDAO().updateBoard(conn, b);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	

	
	
	  public int deleteBoardComment(int boardCommentNo) {
			Connection conn = getConnection();
			int result = new BoardDAO().deleteBoardComment(conn, boardCommentNo);
			if(result>0)
				commit(conn);
			else 
				rollback(conn);
			close(conn);
			
			return result;
		}

	public List<OrderDetail3> selectBoardList2(String memberId) {
		Connection conn = getConnection();
		List<OrderDetail3> list2 
			= new BoardDAO().selectBoardList2(conn,memberId);
		close(conn);
//		System.out.println("BoardService@+="+list);
		return list2;
	}

	


}






