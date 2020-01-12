package item.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.service.ItemService;
import item.model.vo.Item;

/**
 * Servlet implementation class ItemQnaFormServlet
 */
@WebServlet("/item/itemQnaForm")
public class ItemQnaFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String categoryNo = request.getParameter("categoryNo");
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		
		try {
			//업무로직
			Item item = new ItemService().selectItemOne(itemNo);
			
			//뷰단처리
			String view = "";
			if(categoryNo!=null && item!=null) {
				view = "/WEB-INF/views/item/itemQnaForm.jsp";
				request.setAttribute("categoryNo", categoryNo);
				request.setAttribute("item", item);
			}
			else {
				view = "/WEB-INF/views/common/msp.jsp";
				request.setAttribute("msg", "상품Q&A등록페이지 로딩 실패!");
				request.setAttribute("loc", "/item/itemView?categoryNo="+categoryNo+"&itemNo="+item.getItemNo());
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
