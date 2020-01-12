package mypage.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import mypage.model.dao.QnaDAO;
import mypage.model.vo.MyPage;
import mypage.model.vo.Qna;

public class qService {
	
	public List<Qna> selectQnaList(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Qna> list
			= new QnaDAO().selectQnaList(conn,memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectQnaCount(String memberId) {
		Connection conn = getConnection();
		int totalContent = new QnaDAO().selectQnaCount(conn,memberId);
		close(conn);
		return totalContent;
	}
	
	public int insertQna(Qna q) {
		Connection conn = getConnection();
		int result = new QnaDAO().qEnroll(conn, q);
		//트랜잭션 처리
		if(result>0) {
//			//새로 발급된 게시글번호를 가져와서 board객체에 대입
//			q.setqNo(new QnaDAO().selectLastSeq(conn));
			commit(conn);
		}
		else
			rollback(conn);
		close(conn);
		return result;
	}
	public Qna selectQna(String memberId) {
		Connection conn = getConnection();
		Qna q = new QnaDAO().selectQna(conn, memberId);
		close(conn);
		return q;
	}

	 public int deleteQna(int qNo) {
		Connection conn = getConnection();
		int result = new QnaDAO().deleteQna(conn, qNo);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	public int updateQna(Qna q) {
		Connection conn = getConnection();
		int result = new QnaDAO().updateQna(conn, q);
		if(result>0){
			commit(conn);
		}
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public List<MyPage> selectMemberList(String memberId, int cPage, int numPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	public Qna selectOne(int qNo) {
		System.out.println("@@@@@@@@@@service시작");
		Connection conn = getConnection();
		Qna q = new QnaDAO().selectOne(conn, qNo);
		System.out.println("@@@@@q@service = "+q);
		close(conn);
		return q;
	}

	public int selectTotalContent() {
		Connection conn = getConnection();
		int totalContent = new QnaDAO().selectTotalContent(conn);
		close(conn);
		return totalContent;
	}


	public List<Qna> selectOneToOneAll(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Qna> list = new QnaDAO().selectOneToOneAll(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Qna> selectOneToOneOne(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Qna> list = new QnaDAO().selectOneToOneAll(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Qna> selectOneToOneThree(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Qna> list = new QnaDAO().selectOneToOneAll(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Qna> selectOneToOneSix(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Qna> list = new QnaDAO().selectOneToOneAll(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}



}

