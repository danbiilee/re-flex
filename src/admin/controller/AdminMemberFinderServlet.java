
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

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/admin/member/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //프로젝트 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberFinderServlet() {
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
		
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		System.out.println("searchType@finder="+searchType);
		System.out.println("searchKeyword@finder="+searchKeyword);
		
		//2.업무로직
		List<Member> list = null;
		AdminService adminService = new AdminService();
		
		switch(searchType) {
		case "memberId": list = adminService.selectMemberByMemberId(searchKeyword, cPage, numPerPage); break;
		case "memberName": list = adminService.selectMemberByMemberName(searchKeyword, cPage, numPerPage); break;
		}
		
		
		//페이징바 영역
		int totalContent = 0;
		switch (searchType) {
		case "memberId"	:totalContent = new AdminService().selectTotalContentByMemberId(searchKeyword);break;
		case "memberName" :totalContent = new AdminService().selectTotalContentByMemberName(searchKeyword);break;
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
			pageBar += "<li><a href='"+request.getContextPath()+"/admin/member/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo-pageBarSize)+"'><span aria-hidden='true'>&laquo;</span></a></li> ";
		}
		// pageNo section
		while(pageNo<=pageEnd && pageNo<=totalPage){
			if(cPage ==  pageNo ){
				pageBar += "<li class='active'><span class='cPage'>"+pageNo+"</span></li> ";
			} 
			else {
				pageBar += "<li><a href='"+request.getContextPath()+"/admin/member/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'>"+pageNo+"</a></li> ";
			}
			pageNo++;
		}
		
		//[다음]
		if(pageNo > totalPage){
			
		} else {
			
			pageBar += "<li><a href='"+request.getContextPath()+"/admin/member/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'><span aria-hidden='true'>&raquo;</span></a></li>";
		}
		
		
		
		System.out.println("list@finder="+list);
		System.out.println("pageBar@finder="+pageBar);
		
		//3.view단 처리
		request.setAttribute("list", list);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("cPage",cPage);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/member/adminMemberSearchFinder.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
