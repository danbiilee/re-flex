package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;

@WebServlet("/admin/updateOrder")
public class AdminOrderUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminOrderUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderNo = request.getParameter("orderNo");
		String orderStatus = request.getParameter("orderStatus");
		
		int result = new AdminService().updateOrder(orderNo, orderStatus);
		
		//3.뷰단 처리
		String msg = "";
		String loc = "/admin/orderList";
		
		if(result > 0) {
			msg = "상품 수정 성공했습니다.";
		}
		else {
			msg = "상품 수정 실패했습니다.";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
