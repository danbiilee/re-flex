package member.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import member.model.dao.CartDAO;
import member.model.vo.Cart;
import member.model.vo.Cart;
import mypage.model.dao.MyPageDAO;

public class CartService {

	public List<Cart> selectCartList(String memberId) {
		Connection conn = getConnection();
		List<Cart> cartList = new CartDAO().selectCartList(conn, memberId);
		close(conn);
		return cartList;
	}
	
	public int selectCartCount(Cart cart) {
		Connection conn = getConnection();
		int count = new CartDAO().selectCartCount(conn, cart);
		close(conn);
		return count;
	}
	
	public int insertCart(Cart cart) {
		Connection conn = getConnection();
		int result = new CartDAO().insertCart(conn, cart);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int delItems(int[] itemsArr) {
		Connection conn = getConnection();
		int delCount = 0;
		delCount = new CartDAO().delItems(conn, itemsArr);
		if(delCount>0)
			commit(conn);
		else {
			rollback(conn);
		}
		close(conn);
		return delCount;
	}

	public int deleteAllCart(String memberId) {
		Connection conn = getConnection();
		int result = new CartDAO().deleteAllCart(conn, memberId);
		close(conn);
		return result;
	}

	public Cart selectCartOne(String memberId, int cartNo) {
		Connection conn = getConnection();
		Cart cart = new CartDAO().selectCartOne(conn, memberId, cartNo);
		close(conn);
		return cart;
	}
	
}
