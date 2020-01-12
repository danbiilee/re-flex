package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import board.model.vo.Board;
import admin.model.dao.AdminDAO;
import admin.model.vo.QnaAns;
import item.model.vo.Item;
import item.model.vo.ItemQnaAns;
import itemRentEach.model.vo.ItemRentEach;
import member.model.vo.Member;
import mypage.model.vo.Qna;
import order.model.vo.OrderDetail;
import order.model.vo.OrderDetail2;
import order.model.vo.OrderSheet;
//프로젝트 Service
public class AdminService {

	public int selectTotalContent() {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContent(conn);
		close(conn);
		return totalContent;
	}

	public List<Member> selectMemberList(int cPage, int numPerPage) {
		Connection conn = getConnection();
	    List<Member> list= new AdminDAO().selectMemberList(conn, cPage, numPerPage);
	    close(conn);
	    return list;
	}

	public List<Member> selectMemberByMemberId(String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		Connection conn = getConnection();
		list = new AdminDAO().selectMemberByMemberId(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContentByMemberId(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberId(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public List<Member> selectMemberByMemberName(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByMemberName(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContentByMemberName(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberName(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int memberDelete(String memberId) {
		Connection conn = getConnection();
		int result = new AdminDAO().memberDelete(conn, memberId);
		
		
		if(result>0)
			commit(conn);
		
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	//====================상품 추가 삭제 수정 조회
	public int selectTotalItem() {
		Connection conn = getConnection();
		int totalItem = new AdminDAO().selectTotalItem(conn);
		close(conn);
		return totalItem;
	}

	public List<Item> selectItemList(int cPage, int numPerPage) {
		 Connection conn = getConnection();
	     List<Item> list= new AdminDAO().selectItemList(conn, cPage, numPerPage);
	     close(conn);
	     return list;
	}

	public List<Item> selectItemByItemName(String searchKeyword, int cPage, int numPerPage) {
		List<Item> list = null;
		Connection conn = getConnection();
		list = new AdminDAO().selectItemByItemName(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Item> selectItemByCategoryNo(String searchKeyword, int cPage, int numPerPage) {
		List<Item> list = null;
		Connection conn = getConnection();
		list = new AdminDAO().selectItemByCategoryNo(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalItemByItemName(String searchKeyword) {
		Connection conn = getConnection();
		int totalItem = new AdminDAO().selectTotalItemByItemName(conn, searchKeyword);
		close(conn);
		return totalItem;
	}

	public int selectTotalItemByCategoryNo(String searchKeyword) {
		Connection conn = getConnection();
		int totalItem = new AdminDAO().selectTotalItemByCategoryNo(conn, searchKeyword);
		close(conn);
		return totalItem;
	}

	public int selectSellingItem() {
		Connection conn = getConnection();
		int totalItem = new AdminDAO().selectSellingItem(conn);
		close(conn);
		return totalItem;
	}

	public int selectSoldoutItem() {
		Connection conn = getConnection();
		int totalItem = new AdminDAO().selectSoldoutItem(conn);
		close(conn);
		return totalItem;
	}

	public List<Qna> selectQnaList(int cPage, int numPerPage) {
		 Connection conn = getConnection();
	        List<Qna> list= new AdminDAO().selectQnaList(conn, cPage, numPerPage);
	        close(conn);
	        return list;
	}

	public int selectTotalContent2() {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContent2(conn);
		close(conn);
		return totalContent;
	}

	public Qna selectOne(int qNo) {
		Connection conn = getConnection();
		int result = 0;
		Qna q = new AdminDAO().selectOne(conn, qNo);
		
		//트랜잭션처리
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return q;
	}

	public int insertAns(QnaAns a) {
		Connection conn = getConnection();
		int result = new AdminDAO().insertAns(conn, a);
		//트랜잭션 처리
		if(result>0) {
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public String selectAns(int qNo) {
		Connection conn = getConnection();
		int result = 0;
		String ans = new AdminDAO().selectAns(conn, qNo);
		
		//트랜잭션처리
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return ans;
	}

	public List<Qna> selectqnaType(String qnaSearchword, int cPage, int numPerPage) {
		List<Qna> list = null;
		Connection conn = getConnection();
		list = new AdminDAO().selectqnaType(conn, qnaSearchword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContentByqnaType(String qnaSearchword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByqnaType(conn, qnaSearchword);
		close(conn);
		return totalContent;
	}

	public List<Qna> selectqnaYN(String qnaSearchword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Qna> list = new AdminDAO().selectqnaYN(conn, qnaSearchword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContentByqnaYN(String qnaSearchword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByqnaYN(conn, qnaSearchword);
		close(conn);
		return totalContent;
	}


//관리자 - rentalList페이지

public int selectTotalDetailItem(int itemNo) {
		Connection conn = getConnection();
		int totalItem = new AdminDAO().selectTotalDetailItem(conn,itemNo);
		close(conn);
		return totalItem;
	}

	public int rentItemYes(int itemNo) {
		Connection conn = getConnection();
		int rentItemYes = new AdminDAO().rentItemYes(conn,itemNo);
		close(conn);
		return rentItemYes;

	}

	public int rentItemNo(int itemNo) {
		Connection conn = getConnection();
		int rentItemNo = new AdminDAO().rentItemNo(conn,itemNo);
		close(conn);
		return rentItemNo;
	}

	public List<ItemRentEach> selectItemEachList(int itemNo, int cPage, int numPerPage) {
		 Connection conn = getConnection();
	     List<ItemRentEach> list= new AdminDAO().selectItemEachList(conn, itemNo, cPage, numPerPage);
	     close(conn);
	     return list;
	}

	public int selectTotalDetailItemAll() {
		Connection conn = getConnection();
		int totalItem = new AdminDAO().selectTotalDetailItemAll(conn);
		close(conn);
		return totalItem;
	}

	public int rentItemYesAll() {
		Connection conn = getConnection();
		int rentItemYes = new AdminDAO().rentItemYesAll(conn);
		close(conn);
		return rentItemYes;
	}

	public int rentItemNoAll() {
		Connection conn = getConnection();
		int rentItemNo = new AdminDAO().rentItemNoAll(conn);
		close(conn);
		return rentItemNo;
	}

	public List<ItemRentEach> selectItemEachListAll(int cPage, int numPerPage) {
		Connection conn = getConnection();
	    List<ItemRentEach> list= new AdminDAO().selectItemEachListAll(conn, cPage, numPerPage);
	    close(conn);
	    return list;
	}

	public List<ItemRentEach> selectItemEachListByItemName(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
	    List<ItemRentEach> list= new AdminDAO().selectItemEachListByItemName(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalItemEachByItemName(String searchKeyword) {
		Connection conn = getConnection();
		int totalItem = new AdminDAO().selectTotalItemEachByItemName(conn, searchKeyword);
		close(conn);
		return totalItem;
	}
	
	public int selectYesItemEachByItemName(String searchKeyword) {
		Connection conn = getConnection();
		int yesItem = new AdminDAO().selectYesItemEachByItemName(conn, searchKeyword);
		close(conn);
		return yesItem;
	}
	
	public int selectNoItemEachByItemName(String searchKeyword) {
		Connection conn = getConnection();
		int noItem = new AdminDAO().selectNoItemEachByItemName(conn, searchKeyword);
		close(conn);
		return noItem;
	}

	public List<ItemRentEach> selectItemEachListByCategoryNo(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
	    List<ItemRentEach> list= new AdminDAO().selectItemEachListByCategoryNo(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalItemEachByCategoryNo(String searchKeyword) {
		Connection conn = getConnection();
		int totalItem = new AdminDAO().selectTotalItemEachByCategoryNo(conn, searchKeyword);
		close(conn);
		return totalItem;
	}
	
	public int selectYesItemEachByCategoryNo(String searchKeyword) {
		Connection conn = getConnection();
		int yesItem = new AdminDAO().selectYesItemEachByCategoryNo(conn, searchKeyword);
		close(conn);
		return yesItem;
	}
	
	public int selectNoItemEachByCategoryNo(String searchKeyword) {
		Connection conn = getConnection();
		int noItem = new AdminDAO().selectNoItemEachByCategoryNo(conn, searchKeyword);
		close(conn);
		return noItem;
	}


	public List<ItemRentEach> selectItemEachListByRentYn(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
	    List<ItemRentEach> list= new AdminDAO().selectItemEachListByRentYn(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalItemEachByRent_yn(String searchKeyword) {
		Connection conn = getConnection();
		int totalItem = new AdminDAO().selectTotalItemEachByRent_yn(conn, searchKeyword);
		close(conn);
		return totalItem;
	}

	public int insertItemAns(ItemQnaAns iqn) {
		Connection conn = getConnection();
		int result = new AdminDAO().insertItemAns(conn, iqn);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	//=====================================
	//관리자 리뷰 페이지
	public int selectTotalDetailReview(int itemNo) {
		Connection conn = getConnection();
		int reviewCnt = new AdminDAO().selectTotalDetailReview(conn, itemNo);
		close(conn);
		return reviewCnt;
	}
	public List<Board> selectItemReviewList(int itemNo, int cPage, int numPerPage) {
		Connection conn = getConnection();
	    List<Board> list= new AdminDAO().selectItemReviewList(conn, itemNo, cPage, numPerPage);
		close(conn);
		return list;
		
		
	}


	//====================================
	//관리자 판매현황 페이지
	public List<Integer> selectCategorySellCount() {
		Connection conn = getConnection();
		List<Integer> categorySellCount = new AdminDAO().selectCategorySellCount(conn);
		close(conn);
		return categorySellCount;
		
	}

	public List<Integer> selectMontlyIncome() {
		Connection conn = getConnection();
		List<Integer> montlyIncome = new AdminDAO().selectMontlyIncome(conn);
		close(conn);
		return montlyIncome;
	}

	public List<Integer> selectMontlySaleAmount() {
		Connection conn = getConnection();
		List<Integer> montlySaleAmount = new AdminDAO().selectMontlySaleAmount(conn);
		close(conn);
		return montlySaleAmount;
	}

	public List<Integer> selectYearlyIncome() {
		Connection conn = getConnection();
		List<Integer> yearlyIncome = new AdminDAO().selectYearlyIncome(conn);
		close(conn);
		return yearlyIncome;
	}

	public List<Integer> selectYearlySaleAmount() {
		Connection conn = getConnection();
		List<Integer> yearlySaleAmount = new AdminDAO().selectYearlySaleAmount(conn);
		close(conn);
		return yearlySaleAmount;
	}

	
	//====================================
	//관리자 배송관리 페이지
	public int selectTotalOrderAll() {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalOrderAll(conn);
		close(conn);
		return totalContent;
	}

	public int orderstatus(String os) {
		Connection conn = getConnection();
		int osCnt= new AdminDAO().orderstatus(conn,os);
		close(conn);
		return osCnt;
	}

	public List<OrderDetail2> selectOrderSheetList(int cPage, int numPerPage) {
		Connection conn = getConnection();
	    List<OrderDetail2> list= new AdminDAO().selectOrderSheetList(conn, cPage, numPerPage);
	    close(conn);
	    return list;
	}

	public List<OrderDetail2> selectOrderListbyId(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
	    List<OrderDetail2> list= new AdminDAO().selectOrderListbyId(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<OrderDetail2> selectOrderListbyOs(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
	    List<OrderDetail2> list= new AdminDAO().selectOrderListbyOs(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalListById(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalListById(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectTotalListByOs(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalListByOs(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int updateOrder(String orderNo, String orderStatus) {
		Connection conn = getConnection();
		int result = new AdminDAO().updateOrder(conn, orderNo, orderStatus);
		close(conn);
		return result;
	}

		
	//====================================
	//인덱스 필터처리
	
	public List<String> selectCategorySellAmount() {
		Connection conn = getConnection();
		List<String> categorySellAmount = new AdminDAO().selectCategorySellAmount(conn);
		close(conn);
		return categorySellAmount;
		
	}

	
}
