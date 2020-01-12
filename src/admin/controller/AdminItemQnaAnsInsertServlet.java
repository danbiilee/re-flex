package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import item.model.vo.ItemQnaAns;

/**
 * Servlet implementation class AdminItemQnaAnsInsertServlet
 */
@WebServlet("/admin/item/qnaAnsInsert")
public class AdminItemQnaAnsInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminItemQnaAnsInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터
		String categoryNo = request.getParameter("categoryNo");
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		
		int itemQnaNo = Integer.parseInt(request.getParameter("itemQnaNo"));
		String itemQnaAnsContent = request.getParameter("itemQnaAnsContent");
		
		ItemQnaAns iqn = new ItemQnaAns(0, itemQnaNo, "", itemQnaAnsContent, null);
		System.out.println("admin item qna ans insert servlet %%   "+ iqn);
		
		//업무로직
		int result = new AdminService().insertItemAns(iqn);
		
		//view단처리
		String msg = "";
		if(result>0) msg = "답변 등록 성공!";
		else msg = "답변 등록 실패!";
				
		String loc = "/item/itemView?categoryNo="+categoryNo+"&itemNo="+itemNo;
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
