package order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import order.model.service.OrderService;
import order.model.vo.OrderSheet;

/**
 * Servlet implementation class OrderPaymentsCompleteServlet
 */
@WebServlet("/order/manyPaymentsComplete")
public class OrderManyPaymentsCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String orderNo = request.getParameter("merchant_uid"); //주문번호
		String impUid = request.getParameter("imp_uid"); //아임포트에서 발급하는 거래건당 고유번호
		String memberId = request.getParameter("memberId"); //회원아이디
		String orderPayMethod = request.getParameter("payMethod"); //결제수단
		int orderTotalItemEa = Integer.parseInt(request.getParameter("totalItemEa")); //주문 한 건에 들어있는 총 상품개수
		int orderTotalPrice = Integer.parseInt(request.getParameter("totalPrice")); //총 주문금액
		int orderUsePoint = Integer.parseInt(request.getParameter("usePoint")); //주문시 사용한 포인트
		
		//여러 건
		String itemNo = request.getParameter("itemNo"); //상품번호
		String rentOptNo = request.getParameter("rentType"); //렌탈유형
		String orderQuantity = request.getParameter("ea"); //상품하나당 주문수량
		String[] noStrArr = itemNo.split(",");
		String[] rentOptArr = rentOptNo.split(",");
		String[] quaStrArr = orderQuantity.split(",");
		//최종적으로 상품번호, 렌탈유형, 상품수량 담길 배열
		String[][] totalArr = new String [orderTotalItemEa][3]; 
		List<String[]> infoList = new ArrayList<>(); //totalArr[i]담기게 될 리스트
		
		for(int i=0; i<orderTotalItemEa; i++) {
			totalArr[i][0] = noStrArr[i];
			totalArr[i][1] = rentOptArr[i];
			totalArr[i][2] = quaStrArr[i];
			
			infoList.add(totalArr[i]);
		}
	
		OrderSheet os = new OrderSheet(orderNo, memberId, null, orderPayMethod, null, orderTotalItemEa, orderTotalPrice, orderUsePoint, impUid);
		System.out.println("os@servlet="+os);
		
		OrderService orderService = new OrderService();
		try {
			//업무로직
			//1.주문테이블(order_sheet)에 insert -> 주문상품내역 테이블(order_detail)에 orderTotalItemEa만큼 행추가(트리거) 
			int resultIns = orderService.insertOrderSheet(os);
			System.out.println("resultIns@servlet="+resultIns);
			
			//2.주문상품내역 테이블(order_detail) 업데이트 -> 개별상품관리(item_rent_each) 업데이트(트리거)
			//2-1.order_detail_no 가져오기 
			int[] orderDetailNoArr = new int[orderTotalItemEa];  
			
			for(int i=0; i<orderTotalItemEa; i++) {
				orderDetailNoArr[i] = orderService.selectorderDetailNo(orderNo, i+1);
			}
			
			//2-2.orderTotalItemEa만큼 반복문 돌려서 업데이트
			int resultUp = 0;
			for(int i=0; i<orderTotalItemEa; i++) {
				System.out.println("orderDetailNoArr="+orderDetailNoArr[i]+", list="+infoList.get(i)[0]);
				resultUp += orderService.updateOrderDetail(orderDetailNoArr[i], infoList.get(i));
			}
			System.out.println("resultUp@servlet="+resultUp);
			
			//뷰단처리
			JSONObject resultObj = new JSONObject();
			resultObj.put("test", "test");
			resultObj.put("resultIns", resultIns);
			resultObj.put("resultUp", resultUp);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(resultObj);
			
		} catch(Exception e) {
			throw e;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
