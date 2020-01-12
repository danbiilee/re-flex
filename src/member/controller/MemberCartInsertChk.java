package member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * Servlet implementation class MemberCartInsertChk
 */
@WebServlet("/member/memberCartInsertChk")
public class MemberCartInsertChk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String memberId = request.getParameter("memberId");
		String addInfo = request.getParameter("addInfo");
		
		//addInfo자르기 (상품번호,렌탈유형/상품번호,렌탈유형);
		String[] addInfoArr = addInfo.split("/"); //"/"로 자른 문자열 배열
		String[] sArr = null; //","로 자른 문자열 배열
		List<String[]> strList = new ArrayList<>(); //최종 [상품번호,렌탈유형]담길 리스트
		
		for(String s: addInfoArr) {
			sArr = s.split(",");
			strList.add(sArr);
		}
		
		try {
			//업무로직
			CartService cartService = new CartService();
			JSONObject resultObj = new JSONObject();
			int result = 0; //insert결과 확인용
			int count = 0; //중복 확인용: 1이면 이미 있는 상품
			int stock = 0; //재고 확인용
			
			//상품, 장바구니 담길 리스트
			List<Item> itemList = new ArrayList<>();
			List<Cart> cartList = new ArrayList<>();
			
			for(int i=0; i<strList.size(); i++) {
				//상품 객체 가져오기
				Item item = new ItemService().selectOne(Integer.parseInt(strList.get(i)[0]));
				itemList.add(item);
				
				//장바구니 객체 만들기
				Cart cart = new Cart(0, memberId, item, strList.get(i)[1], 1, 0); //인자: 아이디, 상품, 렌탈유형, 수량(고정값1)
				
				//중복 아니고, 재고가 1개 이상이면 리스트에 추가
				count += cartService.selectCartCount(cart); 
				stock = item.getItemStock();
				if(count==0 && stock>0) {
					cartList.add(cart); 
				}
			}
			
			//장바구니에 상품 추가
			for(Cart cart: cartList) {
				result += cartService.insertCart(cart);
			}
			
			//추가 성공한 경우
			if(result>0) {
				resultObj.put("itemListSize", itemList.size()); //선택한 상품수
				resultObj.put("result", result); //insert성공한 수
				resultObj.put("count", count); //중복된 게 있으면 선택한 상품수=result+count 가 될 것. 
				//resultObj.put("stock", stock); //재고
			}
			//추가 실패한 경우
			else {
				resultObj.put("result", -1);
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
