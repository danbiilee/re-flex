package mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.MyPageService;
import mypage.model.vo.WishlistItem;

/**
 * Servlet implementation class MypageWishlistDelChkServlet
 */
@WebServlet("/mypage/mypageWishlistDelAll")
public class MypageWishlistDelAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String memberId = request.getParameter("memberId");
		
		MyPageService myService = new MyPageService();
		try {
			//업무로직
			int result = new MyPageService().deleteAllWishlist(memberId);
			
			//뷰단처리
			String view = "/WEB-INF/views/common/msg.jsp";
			String loc = "/mypage/mypageWishlist?memberId="+memberId;
			String msg = "";
			
			if(result>0) 
				msg = "위시리스트가 전부 삭제되었습니다.";
			else 
				msg = "전체삭제가 실패되었습니다.";
			
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
