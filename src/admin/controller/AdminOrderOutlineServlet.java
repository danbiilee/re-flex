package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;

@WebServlet("/admin/orderOutline")
public class AdminOrderOutlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminOrderOutlineServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> categorySellCount = new AdminService().selectCategorySellCount();
		List<Integer> montlyIncome = new AdminService().selectMontlyIncome();
		List<Integer> montlySaleAmount = new AdminService().selectMontlySaleAmount();
		List<Integer> yearlyIncome = new AdminService().selectYearlyIncome();
		List<Integer> yearlySaleAmount = new AdminService().selectYearlySaleAmount();
		
		
		request.setAttribute("categorySellCount", categorySellCount);
		request.setAttribute("montlyIncome", montlyIncome);
		request.setAttribute("montlySaleAmount", montlySaleAmount);
		request.setAttribute("yearlyIncome", yearlyIncome);
		request.setAttribute("yearlySaleAmount", yearlySaleAmount);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/order/adminOrderOutline.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
