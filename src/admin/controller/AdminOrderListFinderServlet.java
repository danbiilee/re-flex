package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import item.model.vo.Item;
import order.model.vo.OrderDetail;
import order.model.vo.OrderDetail2;

@WebServlet("/admin/orderListFinder")
public class AdminOrderListFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminOrderListFinderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.parameter handling
		int cPage = 1;
		final int numPerPage = 10;

		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {

		}

		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		System.out.println("searchType@finder=" + searchType);
		System.out.println("searchKeyword@finder=" + searchKeyword);

		// 2.업무로직
		List<OrderDetail2> list = null;
		AdminService adminService = new AdminService();
		System.out.println("searchType=" + searchType);

		switch (searchType) {
		case "member_id":
			list = adminService.selectOrderListbyId(searchKeyword, cPage, numPerPage);
			break;
		case "order_status":
			list = adminService.selectOrderListbyOs(searchKeyword, cPage, numPerPage);
			break;
		}
		System.out.println("---------list@finderSevelt=" + list);

		// 페이징바 영역
		int totalItem = 0;
		switch (searchType) {
		case "member_id":
			totalItem = new AdminService().selectTotalListById(searchKeyword);
			break;
		case "order_status":
			totalItem = new AdminService().selectTotalListByOs(searchKeyword);
			break;
		}
		// (공식2)totalPage구하기
		int totalPage = (int) Math.ceil((double) totalItem / numPerPage);
		System.out.println("totalItem=" + totalItem + ", totalPage=" + totalPage);

		// 페이지바 html코드
		String pageBar = "";
		// 페이지바 길이
		int pageBarSize = 5;
		// (공식3)시작페이지 번호 세팅
		int pageStart = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		// 종료페이지 번호 세팅
		int pageEnd = pageStart + pageBarSize - 1;
		System.out.println("pageStart[" + pageStart + "] ~ pageEnd[" + pageEnd + "]");
		int pageNo = pageStart;

		// 이전 section
		if (pageNo == 1) {

		} else {
			pageBar += "<li><a href='" + request.getContextPath() + "/admin/orderListFinder?searchType=" + searchType
					+ "&searchKeyword=" + searchKeyword + "&cPage=" + (pageNo - pageBarSize)
					+ "'><span aria-hidden='true'>&laquo;</span></a></li>";
		}
		// pageNo section
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (cPage == pageNo) {
				pageBar += "<li class='active'><span class='cPage'>" + pageNo + "</span></li>";
			} else {
				pageBar += "<li><a href='" + request.getContextPath() + "/admin/orderListFinder?searchType="
						+ searchType + "&searchKeyword=" + searchKeyword + "&cPage=" + pageNo + "'>" + pageNo
						+ "</a></li>";
			}
			pageNo++;
		}

		// [다음]
		if (pageNo > totalPage) {

		} else {

			pageBar += "<li><a href='" + request.getContextPath() + "/admin/orderListFinder?searchType=" + searchType
					+ "&searchKeyword=" + searchKeyword + "&cPage=" + pageNo
					+ "'><span aria-hidden='true'>&raquo;</span></a></li>";
		}

		System.out.println("list@finder=" + list);
		System.out.println("pageBar@finder=" + pageBar);

		//주문완료인 상품수
		int[] OSArr = {0,0,0,0,0};
		OSArr[0] = new AdminService().orderstatus("OS01");
		OSArr[1] = new AdminService().orderstatus("OS02");
		OSArr[2] = new AdminService().orderstatus("OS03");
		OSArr[3] = new AdminService().orderstatus("OS04");
		OSArr[4] = new AdminService().orderstatus("OS05");
		
		//전체 주문수
		int totalContent = new AdminService().selectTotalOrderAll();


		// 3.view단 처리
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("totalContent", totalContent);
		request.setAttribute("OSArr", OSArr);

		System.out.println("-=-=-=-=-=-=-=-="+list);
		System.out.println(pageBar);
		System.out.println(cPage);
		System.out.println(totalContent);
		System.out.println(OSArr);
		
		
		request.getRequestDispatcher("/WEB-INF/views/admin/order/adminOrderList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
