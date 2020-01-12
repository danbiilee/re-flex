package mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import mypage.model.service.MyPageService;
import mypage.model.vo.Wishlist;

/**
 * Servlet implementation class MypageWishlistServlet
 */
@WebServlet("/mypage/mypageWishlistInsert")
public class MypageWishlistInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String memberId = request.getParameter("memberId");
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		String rentType = request.getParameter("rentType");
		String rentTypePrice = request.getParameter("rentTypePrice");
		
		rentType = "7".equals(rentType)?"RT01":"14".equals(rentType)?"RT02":"RT03";
		
		try {
			//업무로직
			MyPageService myService = new MyPageService();
			JSONObject resultObj = new JSONObject();
			Wishlist wish = new Wishlist(0, memberId, itemNo, rentType);
			int result = 0;
			
			//1.중복아이템이 있는지 확인
			int count = myService.selectWishlistCount(wish, rentType);
			
			//2.중복이 아니라면 위시리스트에 상품 추가
			if(count==0) {
				result = myService.insertWishlist(wish);
				resultObj.put("result", result);
			}
			//중복일 경우 -1을 리턴
			else {
				result = -1;
				resultObj.put("result", result);
			}
			
			//뷰단처리
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
