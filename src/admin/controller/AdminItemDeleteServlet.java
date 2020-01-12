package admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.service.ItemService;
import item.model.vo.ItemImage;

/**
 * Servlet implementation class AdminItemDeleteServlet
 */
@WebServlet("/admin/deleteItem")
public class AdminItemDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminItemDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.parameter handling
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		String category =request.getParameter("category");
		
		//2.business login
		//파일삭제
		System.out.println("======="+itemNo);
		List<ItemImage> list = new ItemService().selectItemImageList(itemNo);
		System.out.println("ListImg@servlet="+list);
		
		
		for(ItemImage itemImg : list) {
			String renamedFileName = itemImg.getItemImageRenamed();
			System.out.println("=============");
			if(!"".equals(renamedFileName)) {
				//파일저장경로
				String saveDirectory = getServletContext().getRealPath("/images/"+category+"/");
				
				File delFile = new File(saveDirectory, renamedFileName);
				System.out.println("delFile="+delFile);
				
				//삭제처리
				boolean bool = delFile.delete();
				
				System.out.println("파일삭제"+(bool?"성공":"실패"));
			}
		}
		//DB삭제
		int result = new ItemService().deleteItem(itemNo);
		System.out.println("result@servlet="+result);
		
		
		

		
		
		//3.view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/admin/itemSearch";
		
		if(result>0) {
			msg="게시글 삭제 성공.";
		}
		else {
			msg="게시글 삭제 실패";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view).forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
