package item.controller;

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

/**
 * Servlet implementation class ItemListAjaxServlet
 */
@WebServlet("/item/itemListAjax")
public class ItemListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String categoryNo = request.getParameter("categoryNo");
		String filterType = request.getParameter("filterType");
		System.out.println("filterType="+filterType);
		
		//페이징: 컨텐츠영역
		//사용자 입력값 처리
		int cPage = 1;
		final int numPerPage = 16;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}
		System.out.println("cPage@servlet="+cPage);
		
		ItemService itemService = new ItemService();
		try {
			//페이징: 페이징바영역
			int pageBarSize = 5;
			int totalContent = itemService.selectTotalContent(categoryNo);
			int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
			
			int pageStart = ((cPage-1)/pageBarSize)*pageBarSize + 1;
			int pageEnd = pageStart + pageBarSize - 1;
			int pageNo = pageStart;
			String pageBar = "";
			
			//1.이전
			if(pageNo!=1) 
				pageBar += "<li><a href='javascript:void(0);' onclick='pageBarAjax("+(pageNo-1)+", \""+filterType+"\");' aria-label='Previous'><span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span></a></li>\n";
			else 
				pageBar += "<li><a href='javascript:void(0);' onclick='pageBarAjax(1, \""+filterType+"\");' aria-label='Previous'><span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span></a></li>\n";
			//2.pageNo
			while(pageNo<=pageEnd && pageNo<=totalPage) {
				if(cPage==pageNo)
					pageBar += "<li class='cPage'><a href='javascript:void(0);' onclick='pageBarAjax("+pageNo+", \""+filterType+"\");'>"+pageNo+"</a></li>\n";
				else
					pageBar += "<li><a href='javascript:void(0);' onclick='pageBarAjax("+pageNo+", \""+filterType+"\");'>"+pageNo+"</a></li>\n";
				pageNo++;
			}
			//3.다음
			if(pageNo<=totalPage) 
				pageBar += "<li><a href='javascript:void(0);' onclick='pageBarAjax("+pageNo+", \""+filterType+"\");' aria-label='Next'><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span></a></li>\n";
			else 
				pageBar += "<li><a href='javascript:void(0);' onclick='pageBarAjax("+(pageNo-1)+", \""+filterType+"\");' aria-label='Next'><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span></a></li>\n";
			
			
			//업무로직
			//파라미터 맵
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("categoryNo", categoryNo);
			paramMap.put("cPage", cPage);
			paramMap.put("numPerPage", numPerPage);
			
			List<Item> itemList = null;
			Map<Integer, List<ItemImage>> imgMap = new HashMap<>();
			List<Integer> itemNoList = new ArrayList<>(); //상품번호 담을 리스트
			
			if("upToDate".equals(filterType)) {
				itemList = itemService.selectItemAll(categoryNo, cPage, numPerPage);
			}
			else if("lowPrice".equals(filterType)) {
				itemList = itemService.selectItemAllByLowPrice(paramMap);
			}
			else if("highPrice".equals(filterType)) {
				itemList = itemService.selectItemAllByHighPrice(paramMap);
			}
			
			
			//뷰단처리
			if(itemList!=null && !itemList.isEmpty()) {
				//상품번호 담기
				for(Item i: itemList){
					itemNoList.add(i.getItemNo());
				}
				
				for(int i=0; i<itemNoList.size(); i++) {
					//상품이미지 가져오기
					List<ItemImage> imgList = itemService.selectItemImageList(itemNoList.get(i));
					imgMap.put(itemNoList.get(i), imgList);
				}
				
				request.setAttribute("categoryNo", categoryNo);
				request.setAttribute("itemList", itemList);
				request.setAttribute("itemNoList", itemNoList);
				request.setAttribute("imgMap", imgMap);
				request.setAttribute("pageBar", pageBar);
				request.getRequestDispatcher("/WEB-INF/views/item/itemListAjax.jsp").forward(request, response);
			}
			
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
