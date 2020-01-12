package mypage.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.dao.MemberDAO;
import mypage.model.exception.MypageException;
import mypage.model.vo.MyPage;
import mypage.model.vo.Wishlist;
import mypage.model.vo.WishlistItem;
import rent.model.vo.rent;
import member.model.vo.*;
import item.model.vo.*;

public class MyPageDAO {
	
	private Properties prop = new Properties();
	
	public MyPageDAO() {
		String fileName = MemberDAO.class.getResource("/sql/mypage/mypage-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
///////////////////////////////////////////////////////////////////////
	//위시리스트
	public int insertWishlist(Connection conn, Wishlist wish) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertWishlist");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, wish.getMemberId());
			pstmt.setInt(2, wish.getItemNo());
			pstmt.setString(3, wish.getRentOptNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MypageException("위시리스트 추가 에러!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectWishlistCount(Connection conn, Wishlist wish, String rentType) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectWishlistCount");
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, wish.getMemberId());
			pstmt.setInt(2, wish.getItemNo());
			pstmt.setString(3, rentType);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MypageException("위시리스트 중복상품 조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public List<WishlistItem> selectWishlistAll(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectWishlistAll");
		List<WishlistItem> wishItemList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			wishItemList = new ArrayList<>();
			while(rset.next()) {
				WishlistItem item = new WishlistItem();
				item.setItemNo(rset.getInt("item_no"));
				item.setCategoryNo(rset.getString("category_no"));
				item.setItemStock(rset.getInt("item_stock"));
				item.setItemBrand(rset.getString("item_brand"));
				item.setItemName(rset.getString("item_name"));
				item.setItemPrice(rset.getInt("item_price"));
				item.setRentOptNo(rset.getString("rent_opt_no"));
				wishItemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MypageException("위시리스트 조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return wishItemList;
	}

	public int selectWishlistTotalContent(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectWishlistTotalContent");
		int totalConent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalConent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MypageException("위시리스트 총상품개수 조회 에러!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalConent;
	}

	public int deleteChkWishlist(Connection conn, String memberId, String[] s) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteChkWishlist");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, Integer.parseInt(s[0]));
			pstmt.setString(3, s[1]);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MypageException("위시리스트 선택상품 삭제 에러!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteAllWishlist(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteAllWishlist");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MypageException("위시리스트 전체삭제 에러!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////
	//포인트

	public List<MyPage> selectPointList(Connection conn,String memberId, int cPage, int numPerPage) {
		
		List<MyPage> list = new ArrayList<>();
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;
	        
	        String query = prop.getProperty("selectPointListByPaging");
	        
	        try{
	            //미완성쿼리문을 가지고 객체생성. 
	            pstmt = conn.prepareStatement(query);
	         
	            //(공식1)시작rownum, 끝rownum
	        	pstmt.setString(1, memberId);
	            pstmt.setInt(2, (cPage-1)*numPerPage+1);
	            pstmt.setInt(3, cPage*numPerPage);
	            
	            
	            //쿼리문실행
	            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
	            rset = pstmt.executeQuery();
	            
	            while(rset.next()){
	            	MyPage m = new MyPage();
	                //컬럼명은 대소문자 구분이 없다.
	            	m.setPointNo(rset.getInt("point_no"));
	            	m.setMemberId(rset.getString("member_id"));
	            	m.setPointStatus(rset.getString("point_status").charAt(0));
					m.setPointAmount(rset.getInt("point_amount"));
					m.setPointChangeReason(rset.getString("point_change_reason"));
					m.setPointChangeDate(rset.getDate("point_change_date"));
	                
	                list.add(m);
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            close(rset);
	            close(pstmt);
	        }
	        
	        
	        return list;
	}

	public int selectTotalContent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContent");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContent;
	}

	public int selectOne(Connection conn, String memberId) {
		int point = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPointOne");
		System.out.println("dkddk @@" + query);
		try {
			//1.Statement객체생성
			pstmt = conn.prepareStatement(query);//미완성 쿼리 전달
			
			//2.미완성 쿼리 값대입
			pstmt.setString(1, memberId);
			/*pstmt.setString(2, memberId);*/
			
			//3.쿼리실행 => ResultSet
			rset = pstmt.executeQuery();
			
			//4.ResultSet => Member
			if(rset.next()) {
				point = rset.getInt("total");
				
				/*m.setMemberId(rset.getString("member_id"));*/
				
//				m.setPointChangeDate(rset.getDate("point_change_date"));;
//				m.setPointChangeReason(rset.getString("point_change_reason"));
//				m.setPointAmount(rset.getInt("total"));
//				m.setPointStatus(rset.getString("point_status").charAt(0));
				
				
				
			}
			
			System.out.println("point@dao.selectOne="+point);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5.자원반납
			close(rset);
			close(pstmt);
		}
		
		return point;
	}

	public List<MyPage> selectPointPlusList(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointPlusListByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointMinusList(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointMinusListByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}
	

	//종료된 렌탈
	public List<rent> finishviewList(Connection conn, String itemNo) {
		
		List<rent> rentList = new ArrayList<>();
		
		rent r = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = " SELECT * FROM ITEM_RENT_EACH WHERE ITEM_RENT_USER = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1 , itemNo );
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				r = new rent();
				r.setItemNo(rset.getInt("item_no"));
				r.setItemEachNo(rset.getInt("Item_Each_No("));
				r.setItemRentEnd(rset.getDate("item_rent_end"));
				r.setItemRentStart(rset.getDate("item_rent_start"));
				r.setItemRentUser(rset.getString("item_rent_user"));
				r.setItemRentYN((rset.getString("item_rent_yn")).charAt(0));
				r.setRentOptNo(rset.getString("rent_opt_no"));




	

				
				rentList.add(r);
				
				System.out.println("@@@DAO"+rentList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return rentList;
	}

	public List<MyPage> selectPointOne(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointOneByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointThree(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointThreeByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointSix(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointSixByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointAll(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointSixByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointPOne(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointPOneByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointPThree(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointPThreeByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointPSix(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointPSixByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointPAll(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointPlusListByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointMOne(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointMOneByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointMThree(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointMThreeByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointMSix(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointMSixByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public List<MyPage> selectPointMAll(Connection conn, String memberId, int cPage, int numPerPage) {
		List<MyPage> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String query = prop.getProperty("selectPointMinusListByPaging");
        
        try{
            //미완성쿼리문을 가지고 객체생성. 
            pstmt = conn.prepareStatement(query);
         
            //(공식1)시작rownum, 끝rownum
        	pstmt.setString(1, memberId);
            pstmt.setInt(2, (cPage-1)*numPerPage+1);
            pstmt.setInt(3, cPage*numPerPage);
            
            
            //쿼리문실행
            //완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
            rset = pstmt.executeQuery();
            
            while(rset.next()){
            	MyPage m = new MyPage();
                //컬럼명은 대소문자 구분이 없다.
            	m.setPointNo(rset.getInt("point_no"));
            	m.setMemberId(rset.getString("member_id"));
            	m.setPointStatus(rset.getString("point_status").charAt(0));
				m.setPointAmount(rset.getInt("point_amount"));
				m.setPointChangeReason(rset.getString("point_change_reason"));
				m.setPointChangeDate(rset.getDate("point_change_date"));
                
                list.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}

	public int selectPointPTotalContent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPointPAllPaging");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContent;
	}
}

