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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import item.model.service.ItemService;
import item.model.vo.ItemImage;
import mypage.model.service.MyPageService;
import mypage.model.vo.WishlistItem;

/**
 * Servlet implementation class MypageWishlistDelChkServlet
 */
@WebServlet("/mypage/mypageWishlistDelChk")
public class MypageWishlistDelChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String memberId = request.getParameter("memberId");
		String delInfo = request.getParameter("delInfo");
		
		//delInfo자르기 (상품번호,렌탈유형/상품번호,렌탈유형)
		String[] delInfoArr = null; //"/"로 자른 문자열 배열
		String[] sArr = null; //","로 자른 문자열 배열
		List<String[]> strList = new ArrayList<>(); //최종 [상품번호,렌탈유형]담길 리스트
		
		//"/"가 있으면 체크박스 클릭해서 넘어온 것
		if(delInfo.contains("/")) {
			System.out.println("체크박스 클릭");
			delInfoArr = delInfo.split("/");
			for(String s: delInfoArr) {
				sArr = s.split(",");
				strList.add(sArr);
			}
		}
		//없으면 x버튼클릭해서 넘어온 것 
		else {
			System.out.println("x버튼 클릭");
			sArr = delInfo.split(",");
			strList.add(sArr);
		}
		
		//페이징: 컨텐츠영역
		//사용자 입력값 처리
		int cPage = 1;
		final int numPerPage = 5;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}
		//System.out.println("cPage@Wishlistservlet="+cPage);
		
		MyPageService myService = new MyPageService();
		try {
			//업무로직: 삭제라서 페이징보다 먼저 처리
			int result = 0;
			
			//총 선택된 상품개수와 총 삭제된 행의 수 비교할 수 있게 result값을 +=로 더해줌
			for(String[] s: strList) {
				result += myService.deleteChkWishlist(memberId, s);
			}
			
			//삭제하고 남은 위시리스트 상품 읽어오기
			List<WishlistItem> wishItemList = myService.selectWishlistAll(memberId);
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
			
			
			//뷰단처리
			String view = "/WEB-INF/views/common/msg.jsp";
			String loc = "/mypage/mypageWishlist?memberId="+memberId;
			String msg = "";
			//선택된 상품개수와 삭제된 행의 수가 같다면 전부 잘 삭제된 것!
			if(result==strList.size()) {
				msg = "상품이 위시리스트에서 삭제되었습니다.";
				request.setAttribute("wishItemList", wishItemList);
				request.setAttribute("itemNoList", itemNoList);
				request.setAttribute("imgMap", imgMap);
				request.setAttribute("pageBar", pageBar);
			}
			else {
				msg = "상품삭제가 실패되었습니다.";
			}
			
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
