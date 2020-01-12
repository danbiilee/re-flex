package mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rent.model.service.rentService;
import rent.model.vo.rent;

/**
 * Servlet implementation class MypageRentalFinServlet
 */
@WebServlet("/mypage/MypageRentalFinSix")
public class MypageRentalFinSix extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String itemrentuser = request.getParameter("memberId");
		
		
		System.out.println(itemrentuser);
		List<rent> list = new rentService().MypageRentalFinSix(itemrentuser);
		int cntfin = new rentService().rentfincnt(itemrentuser);
	
		
		
		//4.뷰단 포워딩		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/mypage/mypageRentalFinAjax.jsp");
		request.setAttribute("list",list);
		request.setAttribute("cntfin", cntfin);
		reqDispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
