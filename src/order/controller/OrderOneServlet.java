package order.controller;

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
import item.model.vo.Item;
import item.model.vo.ItemImage;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class ItemOrderServlet
 */
@WebServlet("/order/orderOne")
public class OrderOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String memberId = request.getParameter("memberId");
		String categoryNo = request.getParameter("categoryNo");
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		int ea = Integer.parseInt(request.getParameter("ea"));
		
		String rentOptNo = request.getParameter("rentType");
		if("7".equals(rentOptNo)) rentOptNo = "RT01";
		else if("14".equals(rentOptNo)) rentOptNo = "RT02";
		else if("30".equals(rentOptNo)) rentOptNo = "RT03";
		
		
		MemberService memService = new MemberService();
		ItemService itemService = new ItemService();
		try {
			//업무로직
			//회원정보 가져오기
			Member m = memService.selectOne(memberId);
			
			//회원 포인트 가져오기
			int usablePoint = itemService.selectMemberUsablePoint(memberId);
			
			//상품정보, 이미지 가져오기
			Item item = itemService.selectItemOne(itemNo);
			
			List<Integer> itemNoList = new ArrayList<>(); //상품번호 담을 리스트
			Map<Integer, List<ItemImage>> imgMap = new HashMap<>(); //키:상품번호, 값:해당 상품 이미지리스트
			
			if(item!=null) {
				//상품번호 담기
				itemNoList.add(itemNo);
				
				//상품이미지 담기
				List<ItemImage> imgList = itemService.selectItemImageList(itemNo);
				imgMap.put(itemNo, imgList);
			}

			
			//뷰단처리
			String view = "";
			if(m!=null && item!=null & imgMap!=null) {
				view = "/WEB-INF/views/order/orderOne.jsp";
				request.setAttribute("m", m);
				request.setAttribute("item", item);
				request.setAttribute("itemNoList", itemNoList);
				request.setAttribute("imgMap", imgMap);
				request.setAttribute("rentOptNo", rentOptNo);
				request.setAttribute("ea", ea);
				request.setAttribute("usablePoint", usablePoint);
			}
			else {
				view = "/WEB-INF/views/common/msg.jsp"; 
				request.setAttribute("msg", "주문페이지 이동 실패!");
				request.setAttribute("loc", "/item/itemView?categoryNo="+categoryNo+"&itemNo="+itemNo);
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
