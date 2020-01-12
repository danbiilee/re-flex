package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.vo.Member;
import mypage.model.vo.Qna;

/**
 * Servlet implementation class AdimMemberQnaSerarchServlet
 */
@WebServlet("/admin/member/qnaSearch")
public class AdimMemberQnaSerarchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdimMemberQnaSerarchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.parameter handling
		int cPage = 1;
		final int numPerPage = 10;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		}catch(NumberFormatException e) {
			
		}
		
		String qnaSearchType = request.getParameter("qnaSearchType");
		String qnaSearchword = request.getParameter("qnaSearchword");
		String keyword="";
    	switch(qnaSearchword){
    	case "상품문의": keyword = "QT01"; break;
    	case "배송문의": keyword = "QT02"; break;
    	case "기타문의": keyword = "QT03"; break;
    	default: keyword=qnaSearchword;break;
    	}
		System.out.println("qna search Servlet 검색타입 %% = "+qnaSearchType);
		System.out.println("qna search Servlet 키워드 %% = "+keyword);
		
		//2.업무로직
		List<Qna> list = null;
		AdminService adminService = new AdminService();
		
		switch(qnaSearchType) {
		case "qnaType": list = adminService.selectqnaType(keyword, cPage, numPerPage); break;
		case "qnaYN": list = adminService.selectqnaYN(keyword, cPage, numPerPage); break;
		}
		
		
		//페이징바 영역
		int totalContent = 0;
		switch (qnaSearchType) {
		case "qnaType"	:totalContent = new AdminService().selectTotalContentByqnaType(keyword);break;
		case "qnaYN" :totalContent = new AdminService().selectTotalContentByqnaYN(keyword);break;
		}
		//(공식2)totalPage구하기
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
		System.out.println("totalMember="+totalContent+", totalPage="+totalPage);
		
		//페이지바 html코드
		String pageBar = "";	
		//페이지바 길이
		int pageBarSize = 5;
		//(공식3)시작페이지 번호 세팅
		int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		//종료페이지 번호 세팅
		int pageEnd = pageStart+pageBarSize-1;
		System.out.println("pageStart["+pageStart+"] ~ pageEnd["+pageEnd+"]");
		int pageNo = pageStart;		


		//이전 section
		if(pageNo == 1 ){

		}
		else {
			pageBar += "<li><a href='"+request.getContextPath()+"/admin/member/qnaFinder?searchType="+qnaSearchType+"&searchKeyword="+qnaSearchword+"&cPage="+(pageNo-pageBarSize)+"'>[이전]</a></li>";
		}
		// pageNo section
		while(pageNo<=pageEnd && pageNo<=totalPage){
			if(cPage ==  pageNo ){
				pageBar += "<li class='active'><span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<li><a href='"+request.getContextPath()+"/admin/member/qnaFinder?searchType="+qnaSearchType+"&searchKeyword="+qnaSearchword+"&cPage="+pageNo+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
		}
		
		//[다음]
		if(pageNo > totalPage){
			
		} else {
			
			pageBar += "<li><a href='"+request.getContextPath()+"/admin/member/qnaFinder?searchType="+qnaSearchType+"&searchKeyword="+qnaSearchword+"&cPage="+pageNo+"'>[다음]</a></li>";
		}
		
		
		
		System.out.println("list@finder="+list);
		System.out.println("pageBar@finder="+pageBar);
		
		//3.view단 처리
		request.setAttribute("list", list);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("cPage",cPage);

		request.getRequestDispatcher("/WEB-INF/views/admin/member/adminQnaFinder.jsp").forward(request, response);

		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
