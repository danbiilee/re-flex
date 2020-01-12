package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import member.model.service.CartService;

/**
 * Servlet implementation class MemberCartDeleteAllServlet
 */
@WebServlet("/member/memberCartDeleteAll")
public class MemberCartDeleteAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String memberId = request.getParameter("memberId");
		
		try {
			//업무로직
			int result = new CartService().deleteAllCart(memberId);
			
			//뷰단처리
			String view = "/WEB-INF/views/common/msg.jsp";
			String loc = "/member/memberCart?memberId="+memberId;
			String msg = "";
			
			if(result>0) 
				msg = "장바구니 비우기가 완료됐습니다.";
			else 
				msg = "장바구니 비우기에 실패했습니다.";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
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
