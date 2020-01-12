package order.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import item.model.vo.Item;
import member.model.dao.MemberDAO;
import order.model.exception.OrderException;
import order.model.vo.OrderDetail;
import order.model.vo.OrderSheet;

public class OrderDAO {
	
	private Properties prop = new Properties();
	
	public OrderDAO() {
		String fileName = MemberDAO.class.getResource("/sql/order/order-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertOrderSheet(Connection conn, OrderSheet os) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertOrderSheet");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, os.getOrderNo());
			pstmt.setString(2, os.getMemberId());
			pstmt.setString(3, os.getOrderPayMethod());
			pstmt.setInt(4, os.getOrderTotalItemEa());
			pstmt.setInt(5, os.getOrderTotalPrice());
			pstmt.setInt(6, os.getOrderUsePoint());
			pstmt.setString(7, os.getImpUid());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("주문테이블 insert 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectorderDetailNo(Connection conn, String orderNo, int i) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectorderDetailNo");
		int orderDetailNo = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, orderNo);
			pstmt.setInt(2, i);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				orderDetailNo = rset.getInt("ORDER_DETAIL_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("orderDetailNo 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return orderDetailNo;
	}

	public int updateOrderDetail(Connection conn, int orderDetailNoArr, String[] strings) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateOrderSheeetItemInfo");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strings[0]));
			pstmt.setString(2, strings[1]);
			pstmt.setInt(3, Integer.parseInt(strings[2]));
			pstmt.setInt(4, orderDetailNoArr);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("order_sheet_itemInfo테이블 업데이트 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<OrderDetail> selectOrderList(Connection conn, String memberId, String orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOrderList");
		List<OrderDetail> orderList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, orderNo);
			rset = pstmt.executeQuery();
			
			orderList = new ArrayList<>();
			while(rset.next()) {
				OrderDetail od = new OrderDetail();
				
				od.setOrderNo(rset.getString("order_no"));
				od.setMemberId(rset.getString("member_id"));
				od.setOrderDate(rset.getDate("order_date"));
				od.setOrderTotalItemEa(rset.getInt("order_total_item_ea"));
				od.setOrderDetailNo(rset.getInt("order_detail_no"));
				
				Item i = new Item();
				i.setItemNo(rset.getInt("ITEM_NO"));
				i.setCategoryNo(rset.getString("CATEGORY_NO"));
				i.setItemBrand(rset.getString("ITEM_BRAND"));
				i.setItemName(rset.getString("ITEM_NAME"));
				i.setItemPrice(rset.getInt("ITEM_PRICE"));
				
				od.setItem(i);
				od.setRentOptNo(rset.getString("RENT_OPT_NO"));
				od.setItemQuantity(rset.getInt("ORDER_QUANTITY"));
				
				//렌탈유형별 가격 구하기
				double disRate = 0; //할인율
				int rentPeriod = 0;
				if("RT01".equals(od.getRentOptNo())){
		    		rentPeriod = 7;
		    		disRate = 0.98;
		    	}
		    	else if("RT02".equals(od.getRentOptNo())){
		    		rentPeriod = 14;
		    		disRate = 0.95;
		    	}
		    	else{
		    		rentPeriod = 30;
		    		disRate = 0.90;
		    	}
				od.setPriceByRentOptNo(i.getItemPrice(), rentPeriod, disRate);
				od.setOrderStatusNo(rset.getString("order_status_no"));
				od.setReviewYn(rset.getString("review_yn"));
				od.setOrderCancelNo(rset.getString("order_cancel_no"));
				
				orderList.add(od);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("주문내역 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return orderList;
	}

	public List<String> selectOrderNo(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOrderNo");
		List<String> orderNoList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			orderNoList = new ArrayList<>();
			while(rset.next()) {
				String orderNo = rset.getString("order_no");
				orderNoList.add(orderNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("주문번호 3개월치 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return orderNoList;
	}
	
	public List<String> selectOrderNoByOneM(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOrderNoByOneM");
		List<String> orderNoList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			orderNoList = new ArrayList<>();
			while(rset.next()) {
				String orderNo = rset.getString("order_no");
				orderNoList.add(orderNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("주문번호 1개월치 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return orderNoList;
	}
	
	public List<String> selectOrderNoBySixM(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOrderNoBySixM");
		List<String> orderNoList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			orderNoList = new ArrayList<>();
			while(rset.next()) {
				String orderNo = rset.getString("order_no");
				orderNoList.add(orderNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("주문번호 6개월치 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return orderNoList;
	}
	
	public List<String> selectOrderNoByAll(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOrderNoByAll");
		List<String> orderNoList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			orderNoList = new ArrayList<>();
			while(rset.next()) {
				String orderNo = rset.getString("order_no");
				orderNoList.add(orderNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("주문번호 전체 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return orderNoList;
	}

	public List<Integer> selectItemNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectItemNo");
		List<Integer> itemNoList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			itemNoList = new ArrayList<>();
			while(rset.next()) {
				int itemNo = rset.getInt("ITEM_NO");
				itemNoList.add(itemNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("상품번호 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return itemNoList;
	}

	public int UpdateOrderDetailCancelNo(Connection conn, int orderDetailNo, String cancelNo) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("UpdateOrderDetailCancelNo");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cancelNo);
			pstmt.setInt(2, orderDetailNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("order_detail테이블 order_cancel_no 업데이트 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Integer> selectRentEachNoList(Connection conn, Map<String, Object> paramMap) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectRentEachNoList");
		List<Integer> eachNoList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (int)paramMap.get("itemNo"));
			pstmt.setString(2, (String)paramMap.get("rentOptNo"));
			pstmt.setString(3, (String)paramMap.get("memberId"));
			rset = pstmt.executeQuery();
			
			eachNoList = new ArrayList<>();
			while(rset.next()) {
				int eachNo = rset.getInt("ITEM_EACH_NO");
				eachNoList.add(eachNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("item_each_no 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return eachNoList;
	}

	public int updateItemRentEach(Connection conn, int eachNo) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateItemRentEach");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eachNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("주문취소: item_rent_each테이블 업데이트 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Date selectRentEndDate(Connection conn, int eachNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectRentEndDate");
		Date endDate = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eachNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				endDate = rset.getDate("ITEM_RENT_END");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("item_rent_end 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return endDate;
	}

	public int updateRentEndDate(Connection conn, int eachNo, String changeDate) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateRentEndDate");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, changeDate);
			pstmt.setInt(2, eachNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("상품교환: item_rent_each테이블 반납일 업데이트 실패!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectOrderListTotalContent(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOrderListTotalContent");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("주문내역 전체 수 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
	
	public int selectRentEachNoOne(Connection conn, Map<String, Object> paramMap) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectRentEachNoOne");
		int eachNo = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (int)paramMap.get("itemNo"));
			pstmt.setString(2, String.valueOf(paramMap.get("rentOptNo")));
			pstmt.setString(3, String.valueOf(paramMap.get("memberId")));
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				eachNo = rset.getInt("ITEM_EACH_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("item_rent_each_no 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return eachNo;
	}

	public Date[] selectRentStartEndDate(Connection conn, int eachNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectRentStartEndDate");
		Date[] dArr = new Date[2];
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eachNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				dArr[0] = rset.getDate("ITEM_RENT_START"); 
				dArr[1] = rset.getDate("ITEM_RENT_END"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("대여 시작일, 반납일 조회 실패!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return dArr;
	}

}
