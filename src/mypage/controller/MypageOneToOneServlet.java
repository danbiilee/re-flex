package mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.qService;
import mypage.model.vo.Qna;

/**
 * Servlet implementation class MypageOneToOneServlet
 */
@WebServlet("/mypage/mypageOneToOne")
public class MypageOneToOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("어디까지 왔니");
		qService qService = new qService();
		
		
		//1.파라미터 핸들링
				final int numPerPage = 5;
				int cPage = 1;
				
				try {
					cPage = Integer.parseInt(request.getParameter("cPage"));			
				}catch(NumberFormatException e) {
					
				}
				
				//2.업무로직
				//a.컨텐츠영역
				String memberId = request.getParameter("memberId");
				System.out.println("MEMBERID@SERVLET="+memberId);
				List<Qna> list = qService.selectQnaList(memberId, cPage, numPerPage); 
				System.out.println("list@servletontoone="+list);
				
				//b.페이징바영역
				//전체게시글수, 전체페이지수
				int pageBarSize = 5; 
				int totalContent = qService.selectQnaCount(memberId);
				int totalPage =  (int)Math.ceil((double)totalContent/numPerPage);//(공식2)
				
				int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;//(공식3)
				int pageEnd = pageStart+pageBarSize-1;
				int pageNo = pageStart;
				String pageBar = "";
				
				//[이전] section
				if(pageNo == 1 ){
//					pageBar += "<span>[이전]</span>"; 
				}
				else {
					pageBar += "<a href='"+request.getContextPath()+"/mypage/QnaList?cPage="+(pageNo-1)+"'>[이전]</a> ";
				}
					
				// pageNo section
				while(pageNo<=pageEnd && pageNo<=totalPage){
					
					if(cPage == pageNo ){
						pageBar += "<span class='cPage'>"+pageNo+"</span> ";
					} 
					else {
						pageBar += "<a href='"+request.getContextPath()+"/mypage/QnaList?cPage="+pageNo+"'>"+pageNo+"</a> ";
					}
					pageNo++;
				}
				
				//[다음] section
				if(pageNo > totalPage){

				} else {
					pageBar += "<a href='"+request.getContextPath()+"/mypage/QnaList?cPage="+pageNo+"'>[다음]</a>";
				}
				
				
				//4.뷰단 포워딩		
				request.setAttribute("list",list);
//				request.setAttribute("pageBar",pageBar);		
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/mypage/mypageOneToOne.jsp");
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
