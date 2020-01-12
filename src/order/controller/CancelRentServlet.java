package order.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

/**
 * Servlet implementation class CancelRentServlet
 */
@WebServlet("/order/cancelRent")
public class CancelRentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		int orderDetailNo = Integer.parseInt(request.getParameter("odNo"));
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		String rentOptNo = request.getParameter("optNo");
		String memberId = request.getParameter("memberId");
		//파라미터 map에 담기
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemNo", itemNo);
		paramMap.put("rentOptNo", rentOptNo);
		paramMap.put("memberId", memberId);
		
		OrderService orderService = new OrderService();
		try {
			//업무로직
			///order_detail에서 order_cancel_status OC03로 업데이트
			int canUpResult = orderService.UpdateOrderDetailCancelNo(orderDetailNo, "OC03");
			System.out.println("canUpResult="+canUpResult);
			
			//item_rent_each_no 찾기
			List<Integer> eachNoList = orderService.selectRentEachNoList(paramMap);
			System.out.println("eachNoList="+eachNoList);
			
			//렌탈해지: 반납일 (오늘날짜+5)일 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 5); //+5
			String changeDate = sdf.format(cal.getTime()); //문자열 변환
			
			//반납일 업데이트하기
			int upResult = 0;
			if(eachNoList!=null && !eachNoList.isEmpty()) {
				for(int i=0; i<eachNoList.size(); i++) {
					upResult += orderService.updateRentEndDate(eachNoList.get(i), changeDate);
				}
			}
			System.out.println("upResult="+upResult);
			
			
			//뷰단처리
			JSONObject resultObj = new JSONObject();
			if(upResult==eachNoList.size()) {
				resultObj.put("result", 1);
			}
			else {
				resultObj.put("result", -1);
			}
			
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
