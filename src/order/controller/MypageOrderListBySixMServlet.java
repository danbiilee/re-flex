package order.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.service.ItemService;
import item.model.vo.ItemImage;
import order.model.service.OrderService;
import order.model.vo.OrderDetail;

/**
 * Servlet implementation class MypageOrderListByOneMServlet
 */
@WebServlet("/mypage/mypageOrderListBySixM")
public class MypageOrderListBySixMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String memberId = request.getParameter("memberId");
		
		OrderService orderService = new OrderService();
		try {
			//업무로직
			//주문번호 담은 리스트
			List<String> orderNoList = orderService.selectOrderNoBySixM(memberId);
			
			//키:주문번호, 값:해당 주문번호의 주문상품내역 리스트
			Map<String, List<OrderDetail>> orderListByOrderNo = new HashMap<>();
			
			for(int i=0; i<orderNoList.size(); i++) {
				List<OrderDetail> orderList = orderService.selectOrderList(memberId, orderNoList.get(i));
				orderListByOrderNo.put(orderNoList.get(i), orderList);
			}
			
			//각 주문상품의 대여시작일, 반납일 가져오기
			//1.item_rent_each_no 찾기 
			Map<String, Object> paramMap = new HashMap<>(); //each_no찾는 파라미터 담길 map
			Map<String, List<Integer>> eachNoMap = new HashMap<>(); //키:주문번호, 값:eacnNo리스트
			int eachNo = 0;
			
			for(int i=0; i<orderNoList.size(); i++) {
				List<Integer> eachNoList = new ArrayList<>(); //each_no담길 리스트
				List<OrderDetail> orderList = orderListByOrderNo.get(orderNoList.get(i));
				
				for(int j=0; j<orderList.size(); j++) {
					//param 담기
					paramMap.put("itemNo", orderList.get(j).getItem().getItemNo());
					paramMap.put("rentOptNo", orderList.get(j).getRentOptNo());
					paramMap.put("memberId", memberId);
					
					//한 상품의 주문수량이 여러 개일 경우를 대비 rownum=1인 each_no만 가져오기
					eachNo = orderService.selectRentEachNoOne(paramMap);
					eachNoList.add(eachNo);
				}
				
				eachNoMap.put(orderNoList.get(i), eachNoList); //주문번호에 맞게 담기
			}
			//2.각 eachNo에 해당하는 개별상품 대여 시작일, 반납일 가져오기
			Date[] dArr = null; //주문상품의 대여시작일, 반납일 담길 배열
			List<Date[]> dateList = new ArrayList<>();
			//3.(최종)키:주문번호, 값:주문상품내역들의 시작일/반납일
			Map<String, List<Date[]>> rentDateMap = new HashMap<>();
			
			for(int i=0; i<orderNoList.size(); i++) {
				List<Integer> eachNoList = eachNoMap.get(orderNoList.get(i));
				for(int no: eachNoList) {
					//2.
					dArr = orderService.selectRentStartEndDate(no); 
					dateList.add(dArr);
				}
				//3.
				rentDateMap.put(orderNoList.get(i), dateList);
			}
			
			//상품번호, 상품 이미지 담기
			List<Integer> itemNoList = orderService.selectItemNo(); //상품번호 담기
			Map<Integer, List<ItemImage>> imgMap = new HashMap<>(); //키:상품번호, 값:해당 상품 이미지리스트
			
			if(itemNoList!=null) {
				for(int i=0; i<itemNoList.size(); i++) {
					//상품이미지 담기
					List<ItemImage> imgList = new ItemService().selectItemImageList(itemNoList.get(i));
					imgMap.put(itemNoList.get(i), imgList);
				}
			}
			
			//뷰단처리: 주문내역 비어있을 수 있음. 
			String view = "";
			if(orderNoList!=null && orderListByOrderNo!=null) {
				view = "/WEB-INF/views/mypage/mypageOrderListAjax.jsp";
				request.setAttribute("orderNoList", orderNoList);
				request.setAttribute("orderListByOrderNo", orderListByOrderNo);
				request.setAttribute("rentDateMap", rentDateMap);
				request.setAttribute("itemNoList", itemNoList);
				request.setAttribute("imgMap", imgMap);
				
			}
			else {
				view = "/WEB-INF/views/common/msg.jsp";
				request.setAttribute("msg", "주문내역 6개월치 조회 실패!");
				request.setAttribute("loc", "/");
			}
			request.getRequestDispatcher(view).forward(request, response);
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
