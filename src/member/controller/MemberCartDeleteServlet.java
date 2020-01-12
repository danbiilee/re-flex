package member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.CartService;
import member.model.vo.Cart;
import member.model.vo.Member;


/**
 * Servlet implementation class MemberCartServlet
 */
@WebServlet("/member/memberCart/delete")
public class MemberCartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("/WEB-INF/views/member/memberCart.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemsStr = request.getParameter("items");
		String[] tmpArr = itemsStr.split(",");
		int[] itemsArr = new int[tmpArr.length];
		
		for (int i=0; i < tmpArr.length; i++) {
			itemsArr[i] = Integer.parseInt(tmpArr[i]);
		}
		int delCount = new CartService().delItems(itemsArr);
		
		System.out.println("삭제된 개수: " + delCount);

	}

}
