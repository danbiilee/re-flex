package member.model.dao;

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

import item.model.vo.Item;
import member.model.exception.MemberException;
import member.model.vo.Cart;
import member.model.vo.Cart;

public class CartDAO {
	
	private Properties prop = new Properties();
	
	public CartDAO() {
		String fileName = MemberDAO.class.getResource("/sql/cart/cart-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Cart> selectCartList(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectList");
		List<Cart> cartList = new ArrayList<Cart>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Cart c = new Cart();
				
				Item i = new Item();
				i.setItemNo(rset.getInt("ITEM_NO"));
				i.setCategoryNo(rset.getString("CATEGORY_NO"));
				i.setItemBrand(rset.getString("ITEM_BRAND"));
				i.setItemName(rset.getString("ITEM_NAME"));
				i.setItemPrice(rset.getInt("ITEM_PRICE"));
				
				c.setCartNo(rset.getInt("CART_NO"));
				c.setMemberId(rset.getString("MEMBER_ID"));
				c.setItem(i);
				c.setRentOptNo(rset.getString("RENT_OPT_NO"));
				c.setItemQuantity(rset.getInt("ITEM_QUANTITY"));
				
				//렌탈유형별 가격 구하기
				double disRate = 0; //할인율
				int rentPeriod = 0;
				if("RT01".equals(c.getRentOptNo())){
		    		rentPeriod = 7;
		    		disRate = 0.98;
		    	}
		    	else if("RT02".equals(c.getRentOptNo())){
		    		rentPeriod = 14;
		    		disRate = 0.95;
		    	}
		    	else{
		    		rentPeriod = 30;
		    		disRate = 0.90;
		    	}
				c.setPriceByRentOptNo(i.getItemPrice(), rentPeriod, disRate);
				
				cartList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("장바구니 리스트 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return cartList;
	}
	
	public int selectCartCount(Connection conn, Cart cart) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCartCount");
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cart.getMemberId());
			pstmt.setInt(2, cart.getItem().getItemNo());
			pstmt.setString(3, cart.getRentOptNo());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("장바구니 중복 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public int insertCart(Connection conn, Cart cart) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertCart");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cart.getMemberId());
			pstmt.setInt(2, cart.getItem().getItemNo());
			pstmt.setString(3, cart.getRentOptNo());
			pstmt.setInt(4, cart.getItemQuantity());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("장바구니 추가 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int delItems(Connection conn, int[] itemsArr) {
		int delCnt = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int arrSize = itemsArr.length;

		//String query = prop.getProperty("selectCartList");
		StringBuilder sb= new StringBuilder();
		sb.append("DELETE FROM ITEM_CART WHERE CART_NO IN (");
		
		for (int i=0; arrSize > i; i++) {
			sb.append(" ?,");
		}
		
		String query = sb.toString();
		
		query = query.substring(0, query.length()-1);
		query = query + ")";
		System.out.println(query);
		
		try {
			//1. prepareStatment객체 생성
			pstmt = conn.prepareStatement(query);
			
			//2.미완성 쿼리 값 대입
			for (int i=0; arrSize > i; i++) {
				pstmt.setInt(i+1, itemsArr[i]);
			}
			
			//4. resultSet => List
			delCnt = pstmt.executeUpdate();
			
			System.out.println(delCnt);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return delCnt;
	}

	public int deleteAllCart(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteAllCart");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			System.out.println("result@dao="+result);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("장바구니 전체삭제 에러!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Cart selectCartOne(Connection conn, String memberId, int cartNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCartOne");
		Cart cart = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, cartNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cart = new Cart();
				
				Item i = new Item();
				i.setItemNo(rset.getInt("ITEM_NO"));
				i.setCategoryNo(rset.getString("CATEGORY_NO"));
				i.setItemBrand(rset.getString("ITEM_BRAND"));
				i.setItemName(rset.getString("ITEM_NAME"));
				i.setItemPrice(rset.getInt("ITEM_PRICE"));
				
				cart.setCartNo(rset.getInt("CART_NO"));
				cart.setMemberId(rset.getString("MEMBER_ID"));
				cart.setItem(i);
				cart.setRentOptNo(rset.getString("RENT_OPT_NO"));
				cart.setItemQuantity(rset.getInt("ITEM_QUANTITY"));
				
				//렌탈유형별 가격 구하기
				double disRate = 0; //할인율
				int rentPeriod = 0;
				if("RT01".equals(cart.getRentOptNo())){
		    		rentPeriod = 7;
		    		disRate = 0.98;
		    	}
		    	else if("RT02".equals(cart.getRentOptNo())){
		    		rentPeriod = 14;
		    		disRate = 0.95;
		    	}
		    	else{
		    		rentPeriod = 30;
		    		disRate = 0.90;
		    	}
				cart.setPriceByRentOptNo(i.getItemPrice(), rentPeriod, disRate);
				
			}
			System.out.println("cart@dao="+cart);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("장바구니 한 개 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return cart;
	}

}
