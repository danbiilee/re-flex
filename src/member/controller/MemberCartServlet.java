package member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.service.ItemService;
import item.model.vo.ItemImage;
import member.model.service.CartService;
import member.model.vo.Cart;
import member.model.vo.Cart;


/**
 * Servlet implementation class MemberCartServlet
 */
@WebServlet("/member/memberCart")
public class MemberCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String memberId = request.getParameter("memberId");
		
		try {
			//업무로직
			List<Cart> cartList = new CartService().selectCartList(memberId); //장바구니목록 담을 리스트
			
			List<Integer> itemNoList = new ArrayList<>(); //상품번호 담을 리스트
			Map<Integer, List<ItemImage>> imgMap = new HashMap<>(); //키:상품번호, 값:해당 상품 이미지리스트
			
			if(cartList!=null && !cartList.isEmpty()) {
				//상품번호 담기
				for(Cart c: cartList){
					itemNoList.add(c.getItem().getItemNo());
				}
				
				for(int i=0; i<itemNoList.size(); i++) {
					//상품이미지 담기
					List<ItemImage> imgList = new ItemService().selectItemImageList(itemNoList.get(i));
					imgMap.put(itemNoList.get(i), imgList);
				}
			}
			
			//뷰단처리: 장바구니 리스트 비어있을 수 있음
			String view = "";
			if(cartList!=null) {
				view = "/WEB-INF/views/member/memberCart.jsp";
				request.setAttribute("cartList", cartList);
				request.setAttribute("itemNoList", itemNoList);
				request.setAttribute("imgMap", imgMap);
			}
			else {
				view = "/WEB-INF/views/common/msg.jsp";
				request.setAttribute("msg", "장바구니페이지 조회 실패!");
				request.setAttribute("loc", "/");
			}
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
