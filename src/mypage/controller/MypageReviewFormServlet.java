package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MypageReviewFormServlet
 */
@WebServlet("/mypage/mypageReviewForm")
public class MypageReviewFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		int orderDetailNo = Integer.parseInt(request.getParameter("orderDetailNo"));
		
		System.out.println(itemNo);
		System.out.println(orderDetailNo);
		
		request.setAttribute("itemNo",itemNo);
		request.setAttribute("orderDetailNo",orderDetailNo);
		request.getRequestDispatcher("/WEB-INF/views/mypage/mypageReviewForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
