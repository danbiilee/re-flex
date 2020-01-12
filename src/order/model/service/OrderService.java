package order.model.service;

import order.model.dao.OrderDAO;
import order.model.vo.OrderDetail;
import order.model.vo.OrderSheet;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import mypage.model.dao.MyPageDAO;

public class OrderService {

	public int insertOrderSheet(OrderSheet os) {
		Connection conn = getConnection();
		int result = new OrderDAO().insertOrderSheet(conn, os);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int selectorderDetailNo(String orderNo, int i) {
		Connection conn = getConnection();
		int orderDetailNo = new OrderDAO().selectorderDetailNo(conn, orderNo, i);
		close(conn);
		return orderDetailNo;
	}

	public int updateOrderDetail(int orderDetailNoArr, String[] strings) {
		Connection conn = getConnection();
		int result = new OrderDAO().updateOrderDetail(conn, orderDetailNoArr, strings);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<OrderDetail> selectOrderList(String memberId, String orderNo) {
		Connection conn = getConnection();
		List<OrderDetail> orderList = new OrderDAO().selectOrderList(conn, memberId, orderNo);
		close(conn);
		return orderList;
	}

	public List<String> selectOrderNo(String memberId) {
		Connection conn = getConnection();
		List<String> orderNoList = new OrderDAO().selectOrderNo(conn, memberId);
		close(conn);
		return orderNoList;
	}
	
	public List<String> selectOrderNoByOneM(String memberId) {
		Connection conn = getConnection();
		List<String> orderNoList = new OrderDAO().selectOrderNoByOneM(conn, memberId);
		close(conn);
		return orderNoList;
	}
	
	public List<String> selectOrderNoBySixM(String memberId) {
		Connection conn = getConnection();
		List<String> orderNoList = new OrderDAO().selectOrderNoBySixM(conn, memberId);
		close(conn);
		return orderNoList;
	}
	
	public List<String> selectOrderNoByAll(String memberId) {
		Connection conn = getConnection();
		List<String> orderNoList = new OrderDAO().selectOrderNoByAll(conn, memberId);
		close(conn);
		return orderNoList;
	}

	public List<Integer> selectItemNo() {
		Connection conn = getConnection();
		List<Integer> itemNoList = new OrderDAO().selectItemNo(conn);
		close(conn);
		return itemNoList;
	}

	public int UpdateOrderDetailCancelNo(int orderDetailNo, String cancelNo) {
		Connection conn = getConnection();
		int result = new OrderDAO().UpdateOrderDetailCancelNo(conn, orderDetailNo, cancelNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Integer> selectRentEachNoList(Map<String, Object> paramMap) {
		Connection conn = getConnection();
		List<Integer> eachNoList = new OrderDAO().selectRentEachNoList(conn, paramMap);
		close(conn);
		return eachNoList;
	}

	public int updateItemRentEach(int eachNo) {
		Connection conn = getConnection();
		int result = new OrderDAO().updateItemRentEach(conn, eachNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public Date selectRentEndDate(int eachNo) {
		Connection conn = getConnection();
		Date endDate = new OrderDAO().selectRentEndDate(conn, eachNo);
		close(conn);
		return endDate;
	}

	public int updateRentEndDate(int eachNo, String changeDate) {
		Connection conn = getConnection();
		int result = new OrderDAO().updateRentEndDate(conn, eachNo, changeDate);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int selectOrderListTotalContent(String memberId) {
		Connection conn = getConnection();
		int totalContent = new OrderDAO().selectOrderListTotalContent(conn, memberId);
		close(conn);
		return totalContent;
	}

	public int selectRentEachNoOne(Map<String, Object> paramMap) {
		Connection conn = getConnection();
		int eachNo = new OrderDAO().selectRentEachNoOne(conn, paramMap);
		close(conn);
		return eachNo;
	}
	
	public Date[] selectRentStartEndDate(int eachNo) {
		Connection conn = getConnection();
		Date[] dArr = new OrderDAO().selectRentStartEndDate(conn, eachNo);
		close(conn);
		return dArr;
	}

}
