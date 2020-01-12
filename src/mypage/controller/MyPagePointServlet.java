package mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mypage.model.service.MyPageService;
import mypage.model.vo.MyPage;


@WebServlet("/mypage/mypagePoint")
public class MyPagePointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MyPagePointServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리
		int cPage = 1;
		final int numPerPage = 10; 
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		} catch(NumberFormatException e) {
			
		}
		
		//페이징바영역처리
		int totalContent = new MyPageService().selectTotalContent();
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
		
		String pageBar = "";
		int pageBarSize = 5;
	
		//(공식3)
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize + 1;
		int pageEnd = pageStart+pageBarSize-1;
		
		//증감변수 pageNo
		int pageNo = pageStart;

		String memberId = request.getParameter("memberId");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+memberId);
	
		List<MyPage> list = new MyPageService().selectPointList(memberId,cPage, numPerPage);

		//1.이전
		if(pageNo != 1) {
			pageBar += "<a href='"+request.getContextPath()+"/mypage/mypagePoint?memberId="+memberId+"&cPage="+(pageNo-1)+"'>[이전]</a>\n";
		}
		
		//2.pageNo
		while(pageNo<=pageEnd && pageNo<=totalPage) {
			//현재페이지인 경우
			if(cPage == pageNo) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>\n";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/mypage/mypagePoint?memberId="+memberId+"&cPage="+pageNo+"'>"+pageNo+"</a>\n";				
			}
			
			pageNo++;
		}
		//3.다음
		if(pageNo <= totalPage) {
			pageBar += "<a href='"+request.getContextPath()+"/mypage/mypagePoint?memberId="+memberId+"&cPage="+pageNo+"'>[다음]</a>\n";							
		}	
		
		int point = new MyPageService().selectOne(memberId);
		
		System.out.println("dddddd"+memberId);
		
		request.setAttribute("list",list);
		request.setAttribute("point",point);
		request.setAttribute("pageBar", pageBar);
		
		request.getRequestDispatcher("/WEB-INF/views/mypage/mypagePoint.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
