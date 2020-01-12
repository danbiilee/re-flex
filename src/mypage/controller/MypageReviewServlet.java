package mypage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import item.model.service.ItemService;
import item.model.vo.ItemImage;
import order.model.vo.OrderDetail3;

/**
 * Servlet implementation class MypageReviewServlet
 */
@WebServlet("/mypage/mypageReview")
public class MypageReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = (request.getParameter("memberId"));
		
		BoardService boardService = new BoardService();
		
		//1.파라미터 핸들링
		final int numPerPage = 5;
		int cPage = 1;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		}catch(NumberFormatException e) {
			
		}
		
		//2.업무로직
		//a.컨텐츠영역
		List<Board> list 
			= boardService.selectBoardListAll(cPage, numPerPage); 
		
		List<OrderDetail3> list2=boardService.selectBoardList2(memberId);
		
		//상품 이미지 가져오기
		List<Integer> itemNoList = new ArrayList<>(); //상품번호 담을 리스트
		Map<Integer, List<ItemImage>> imgMap = new HashMap<>(); //키:상품번호, 값:해당 상품 이미지리스트
		
		//상품번호 담기
		for(OrderDetail3 o: list2){
			itemNoList.add(o.getItemNo());
		}
		
		for(int i=0; i<itemNoList.size(); i++) {
			//상품이미지 담기
			List<ItemImage> imgList = new ItemService().selectItemImageList(itemNoList.get(i));
			imgMap.put(itemNoList.get(i), imgList);
		}
		
		
		//b.페이징바영역
		//전체게시글수, 전체페이지수
		int totalContent = boardService.selectBoardCountAll();
		int totalPage =  (int)Math.ceil((double)totalContent/numPerPage);//(공식2)
		
		String pageBar = "";
		int pageBarSize = 5; 
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;//(공식3)
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		
		//1.이전
		if(pageNo!=1) 
			pageBar += "<li><a href='"+request.getContextPath()+"/mypage/mypageReview?cPage="+(pageNo-1)+"' aria-label='Previous'><span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span></a></li>\n";
		else 
			pageBar += "<li><a href='"+request.getContextPath()+"/mypage/mypageReview?cPage=1' aria-label='Previous'><span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span></a></li>\n";
		//2.pageNo
		while(pageNo<=pageEnd && pageNo<=totalPage) {
			if(cPage==pageNo)
				pageBar += "<li class='cPage'><a href='"+request.getContextPath()+"/mypage/mypageReview?cPage="+pageNo+"'>"+pageNo+"</a></li>\n";
			else
				pageBar += "<li><a href='"+request.getContextPath()+"/mypage/mypageReview?cPage="+pageNo+"'>"+pageNo+"</a></li>\n";
			pageNo++;
		}
		//3.다음
		if(pageNo<=totalPage) 
			pageBar += "<li><a href='"+request.getContextPath()+"/mypage/mypageReview?cPage="+pageNo+"' aria-label='Next'><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span></a></li>\n";
		else 
			pageBar += "<li><a href='"+request.getContextPath()+"/mypage/mypageReview?cPage="+(pageNo-1)+"' aria-label='Next'><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span></a></li>\n";
		
	
		//4.뷰단 포워딩		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/mypage/mypageReview.jsp");
		request.setAttribute("list",list);
		request.setAttribute("list2",list2);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("itemNoList",itemNoList);
		request.setAttribute("imgMap",imgMap);
		
		reqDispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
