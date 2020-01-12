package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import itemRentEach.model.vo.ItemRentEach;
import member.model.vo.Member;

@WebServlet("/admin/rentalListFinder")
public class AdminRentalListFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminRentalListFinderServlet() {
        super();
    }

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
		String keyword="";
		switch(searchKeyword) {
		case "반려동물":keyword="CT01";break;
		case "육아":keyword="CT02";break;
		case "파티":keyword="CT03";break;
		case "운동":keyword="CT04";break;
		case "여행":keyword="CT05";break;
		case "캠핑":keyword="CT06";break;
		default: keyword=searchKeyword;break;
		}
		
		System.out.println("searchType@finder="+searchType);
		System.out.println("searchKeyword@finder="+keyword);
		
		//대여중인 상품수
		int rentItemYes = 0;
		
		//대여가능한 상품수
		int rentItemNo = 0;
		
		//2.업무로직
		List<ItemRentEach> list = null;
		AdminService adminService = new AdminService();
		
		switch(searchType) {
		case "item_name": list = adminService.selectItemEachListByItemName(keyword, cPage, numPerPage); break;
		case "category_no": list = adminService.selectItemEachListByCategoryNo(keyword, cPage, numPerPage); break;
		case "rent_yn": list = adminService.selectItemEachListByRentYn(keyword, cPage, numPerPage); break;
		}
		
		
		//페이징바 영역
		int totalContent = 0;
		switch (searchType) {
		case "item_name"	:
			totalContent = new AdminService().selectTotalItemEachByItemName(keyword);
			rentItemYes = new AdminService().selectYesItemEachByItemName(keyword);
			rentItemNo = new AdminService().selectNoItemEachByItemName(keyword);
			break;
		case "category_no" :
			totalContent = new AdminService().selectTotalItemEachByCategoryNo(keyword);
			rentItemYes = new AdminService().selectYesItemEachByCategoryNo(keyword);
			rentItemNo = new AdminService().selectNoItemEachByCategoryNo(keyword);
			break;
		case "rent_yn"	:
			totalContent = new AdminService().selectTotalItemEachByRent_yn(keyword);
			rentItemYes = 0;
			rentItemNo = totalContent;
			break;
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
			pageBar += "<li><a href='"+request.getContextPath()+"/admin/rentalListFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo-pageBarSize)+"'><span aria-hidden='true'>&laquo;</span></a></li>";
		}
		// pageNo section
		while(pageNo<=pageEnd && pageNo<=totalPage){
			if(cPage ==  pageNo ){
				pageBar += "<li class='active'><span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<li><a href='"+request.getContextPath()+"/admin/rentalListFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
		}
		
		//[다음]
		if(pageNo > totalPage){
			
		} else {
			
			pageBar += "<li><a href='"+request.getContextPath()+"/admin/rentalListFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'><span aria-hidden='true'>&raquo;</span></a></li>";
		}
		
		
		
		System.out.println("list@finder="+list);
		System.out.println("pageBar@finder="+pageBar);
		
		//3.view단 처리
		request.setAttribute("list", list);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("cPage",cPage);
		
		request.setAttribute("totalContent", totalContent);
		request.setAttribute("rentItemYes", rentItemYes);
		request.setAttribute("rentItemNo", rentItemNo);
		request.getRequestDispatcher("/WEB-INF/views/admin/order/adminOrderRentallist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
