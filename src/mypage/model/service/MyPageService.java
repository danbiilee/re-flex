

package mypage.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import mypage.model.dao.MyPageDAO;
import mypage.model.vo.MyPage;
import mypage.model.vo.Wishlist;
import mypage.model.vo.WishlistItem;



public class MyPageService {
	
///////////////////////////////////////////////////////////////////////
	//위시리스트
	public int insertWishlist(Wishlist wish) {
		Connection conn = getConnection();
		int result = new MyPageDAO().insertWishlist(conn, wish);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int selectWishlistCount(Wishlist wish, String rentType) {
		Connection conn = getConnection();
		int count = new MyPageDAO().selectWishlistCount(conn, wish, rentType);
		close(conn);
		return count;
	}

	public List<WishlistItem> selectWishlistAll(String memberId) {
		Connection conn = getConnection();
		List<WishlistItem> wishItemList = new MyPageDAO().selectWishlistAll(conn, memberId);
		close(conn);
		return wishItemList;
	}

	public int selectWishlistTotalContent(String memberId) {
		Connection conn = getConnection();
		int totalContent = new MyPageDAO().selectWishlistTotalContent(conn, memberId);
		close(conn);
		return totalContent;
	}

	public int deleteChkWishlist(String memberId, String[] s) {
		Connection conn = getConnection();
		int result = new MyPageDAO().deleteChkWishlist(conn, memberId, s);
		close(conn);
		return result;
	}

	public int deleteAllWishlist(String memberId) {
		Connection conn = getConnection();
		int result = new MyPageDAO().deleteAllWishlist(conn, memberId);
		close(conn);
		return result;
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////
	//포인트
	public List<MyPage> selectPointList(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointList(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContent() {
		Connection conn = getConnection();
		int totalContent = new MyPageDAO().selectTotalContent(conn);
		close(conn);
		return totalContent;
	}
	

	public int selectOne(String memberId) {
		Connection conn = getConnection();
		
		int point = new MyPageDAO().selectOne(conn, memberId);
		
		close(conn);
		
		return point;
	}

	public List<MyPage> selectPointPlusList(String memberId, int cPage, int numPerPage) {
		 Connection conn = getConnection();
	        List<MyPage> list= new MyPageDAO().selectPointPlusList(conn,memberId, cPage, numPerPage);
	        close(conn);
	        return list;
	}

	public List<MyPage> selectPointMinusList(String memberId, int cPage, int numPerPage) {
		 Connection conn = getConnection();
	        List<MyPage> list= new MyPageDAO().selectPointMinusList(conn,memberId, cPage, numPerPage);
	        close(conn);
	        return list;
	}

	public List<MyPage> selectPointOne(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointOne(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointThree(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointThree(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointSix(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointSix(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointAll(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointAll(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointPOne(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointPOne(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointPThree(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointPThree(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointPSix(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointPSix(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointPAll(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointPAll(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointMOne(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointMOne(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointMThree(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointMThree(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointMSix(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointMSix(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<MyPage> selectPointMAll(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MyPage> list = new MyPageDAO().selectPointMAll(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectPointPTotalContent() {
		Connection conn = getConnection();
		int totalContent = new MyPageDAO().selectPointPTotalContent(conn);
		close(conn);
		return totalContent;
	}


}

