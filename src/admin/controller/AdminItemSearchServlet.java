package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import item.model.vo.Item;
import member.model.vo.Member;

@WebServlet("/admin/itemSearch")
public class AdminItemSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminItemSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리
		int cPage = 1;//초기값 설정
		final int numPerPage = 10; 
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		} catch(NumberFormatException e) {
			//cPage입력값이 없거나, 부정입력한 경우 기본값으로 처리된다.
		}
//		System.out.println("cPage@list="+cPage);
		
		//페이징바영역처리
		int totalContent = new AdminService().selectTotalItem();
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage);//(공식2)
//		System.out.printf("totalContent=%s, totalPage=%s%n", totalContent, totalPage);
		
		String pageBar = "";
		int pageBarSize = 5;
	
		//(공식3)
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize + 1;
		int pageEnd = pageStart+pageBarSize-1;
		
		//증감변수 pageNo
		int pageNo = pageStart;


		//1.이전
		if(pageNo != 1) {
			pageBar += "<li><a href='"+request.getContextPath()+"/admin/itemSearch?cPage="+(pageNo-1)+"'><span aria-hidden='true'>&laquo;</span></a></li>\n";
		}
		
		//2.pageNo
		while(pageNo<=pageEnd && pageNo<=totalPage) {
			//현재페이지인 경우
			if(cPage == pageNo) {
				pageBar += "<li class='active'><span class='cPage'>"+pageNo+"</span></li>\n";
			}
			else {
				pageBar += "<li><a href='"+request.getContextPath()+"/admin/itemSearch?cPage="+pageNo+"'>"+pageNo+"</a></li>\n";				
			}
			
			pageNo++;
		}
		
		//3.다음
		if(pageNo <= totalPage) {
			pageBar += "<li><a href='"+request.getContextPath()+"/admin/itemSearch?cPage="+pageNo+"'><span aria-hidden='true'>&raquo;</span></a><li>\n";							
		}

		//판매중인 상품수
		int sellingItemCnt = new AdminService().selectSellingItem();
		
		//품절인 상품수
		int soldoutItemCnt = new AdminService().selectSoldoutItem();
		
		//3.업무로직
		List<Item> list = new AdminService().selectItemList(cPage, numPerPage);
		request.setAttribute("list",list);
		request.setAttribute("pageBar", pageBar);
		
		request.setAttribute("totalContent", totalContent);
		request.setAttribute("sellingItemCnt", sellingItemCnt);
		request.setAttribute("soldoutItemCnt", soldoutItemCnt);

		System.out.println(list);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/item/adminItemSearch.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
