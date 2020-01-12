package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import item.model.service.ItemService;
import item.model.vo.Item;
import member.model.service.CartService;
import member.model.vo.Cart;

/**
 * Servlet implementation class MemberCartInsertServlet
 */
@WebServlet("/member/memberCartInsert")
public class MemberCartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String memberId = request.getParameter("memberId");
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
		
		String rentOptNo = request.getParameter("rentType");
		if("7".equals(rentOptNo)) rentOptNo = "RT01";
		else if("14".equals(rentOptNo)) rentOptNo = "RT02";
		else if("30".equals(rentOptNo)) rentOptNo = "RT03";
		
		try {
			//업무로직
			CartService cartService = new CartService();
			JSONObject resultObj = new JSONObject();
			int result = 0;
			
			//상품 객체 가져오기
			Item item = new ItemService().selectOne(itemNo);
			
			//장바구니 객체 만들기
			Cart cart = new Cart(0, memberId, item, rentOptNo, itemQuantity, 0);
			
			int count = cartService.selectCartCount(cart); //중복 확인용
			int stock = item.getItemStock(); //재고 확인용
			
			//장바구니에 상품 추가: 중복아이템이 없고, 품절되지 않았고, 선택한 수량보다 재고가 많을 때만
			if(count==0 && stock>0 && stock>=itemQuantity) {
				result = cartService.insertCart(cart);
				resultObj.put("result", result);
			}
			else {
				//상품추가에 실패한 경우 중복, 재고값을 리턴
				resultObj.put("count", count);
				resultObj.put("stock", stock);
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
