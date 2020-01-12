package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.service.ItemService;
import item.model.vo.Item;
import item.model.vo.ItemImage;

@WebServlet("/admin/updateItem")
public class AdminItemUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminItemUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2.parameter handling
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
				
		//3.business logic
		Item i = new ItemService().selectOne(itemNo);
		System.out.println("item@UpdateServlet="+i);
		
		List<ItemImage> list = new ItemService().selectItemImageList(itemNo);
		System.out.println("itemImgList@UpdateViewServlet="+list);
		
		
		
		//4.view단
		String view = "";
		
		//조회된 회원이 있는 경우
		if(i != null) {
			view = "/WEB-INF/views/admin/item/adminItemUpdate.jsp";
			//조회된 회원 속성으로 저장
			request.setAttribute("item", i);
			request.setAttribute("imgList", list);
		}
		//조회된 회원이 없는 경우
		else {
			String loc = "/";
			String msg = "해당상품이 없습니다.";
			view = "/WEB-INF/views/common/msg.jsp";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}
		
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
