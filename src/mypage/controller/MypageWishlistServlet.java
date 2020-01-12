package mypage.controller;

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
import mypage.model.service.MyPageService;
import mypage.model.vo.WishlistItem;

/**
 * Servlet implementation class MypageWishlistServlet
 */
@WebServlet("/mypage/mypageWishlist")
public class MypageWishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String memberId = request.getParameter("memberId");
		
		//페이징: 컨텐츠영역
		//사용자 입력값 처리
		int cPage = 1;
		final int numPerPage = 5;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}
		System.out.println("cPage@Wishlistservlet="+cPage);
		
		MyPageService myService = new MyPageService();
		try {
			//페이징: 페이징바영역
			int pageBarSize = 5;
			int totalContent = myService.selectWishlistTotalContent(memberId);
			int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
			
			int pageStart = ((cPage-1)/pageBarSize)*pageBarSize + 1;
			int pageEnd = pageStart + pageBarSize - 1;
			int pageNo = pageStart;
			String pageBar = "";
			
			//1.이전
			if(pageNo!=1) 
				pageBar += "<li><a href='"+request.getContextPath()+"/mypage/mypageWishlist?memberId="+memberId+"&cPage="+(pageNo-1)+"' aria-label='Previous'><span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span></a></li>\n";
			else 
				pageBar += "<li><a href='"+request.getContextPath()+"/mypage/mypageWishlist?memberId="+memberId+"&cPage=1' aria-label='Previous'><span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span></a></li>\n";
			//2.pageNo
			while(pageNo<=pageEnd && pageNo<=totalPage) {
				if(cPage==pageNo)
					pageBar += "<li class='cPage'><a href='"+request.getContextPath()+"/mypage/mypageWishlist?memberId="+memberId+"&cPage="+pageNo+"'>"+pageNo+"</a></li>\n";
				else
					pageBar += "<li><a href='"+request.getContextPath()+"/mypage/mypageWishlist?memberId="+memberId+"&cPage="+pageNo+"'>"+pageNo+"</a></li>\n";
				pageNo++;
			}
			//3.다음
			if(pageNo<=totalPage) 
				pageBar += "<li><a href='"+request.getContextPath()+"/mypage/mypageWishlist?memberId="+memberId+"&cPage="+pageNo+"' aria-label='Next'><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span></a></li>\n";
			else 
				pageBar += "<li><a href='"+request.getContextPath()+"/mypage/mypageWishlist?memberId="+memberId+"&cPage="+(pageNo-1)+"' aria-label='Next'><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span></a></li>\n";
			
			
			//업무로직
			List<WishlistItem> wishItemList = new MyPageService().selectWishlistAll(memberId);
			List<Integer> itemNoList = new ArrayList<>(); //상품번호 담을 리스트
			Map<Integer, List<ItemImage>> imgMap = new HashMap<>(); //키:상품번호, 값:해당 상품 이미지리스트
			
			if(wishItemList!=null && !wishItemList.isEmpty()) {
				//상품번호 담기
				for(WishlistItem i: wishItemList){
					itemNoList.add(i.getItemNo());
				}
				
				for(int i=0; i<itemNoList.size(); i++) {
					//상품이미지 담기
					List<ItemImage> imgList = new ItemService().selectItemImageList(itemNoList.get(i));
					imgMap.put(itemNoList.get(i), imgList);
				}
			}
			
			//뷰단처리: 위시리스트에 아무 것도 없을 수 있음!
			String view = "";
			String referer = request.getHeader("Referer");
			System.out.println("referer="+referer);
			
			if(wishItemList!=null) {
				view = "/WEB-INF/views/mypage/mypageWishlist.jsp";
				request.setAttribute("wishItemList", wishItemList);
				request.setAttribute("itemNoList", itemNoList);
				request.setAttribute("imgMap", imgMap);
				request.setAttribute("pageBar", pageBar);
			}
			else {
				view = "/WEB-INF/views/common/msg.jsp";
				request.setAttribute("msg", "위시리스트페이지 조회 실패!");
				request.setAttribute("loc", "/");
				//request.setAttribute("script", "location.href="+referer);
				//response.sendRedirect(referer);
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
