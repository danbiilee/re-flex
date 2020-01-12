package item.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.service.ItemService;

/**
 * Servlet implementation class ItemQnaFormEndServlet
 */
@WebServlet("/item/itemQnaFormEnd")
public class ItemQnaFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String categoryNo = request.getParameter("categoryNo");
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		String qnaContent = request.getParameter("qnaContent");
		//XSS공격대비 &문자변환
		qnaContent = qnaContent.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\\n", "<br/>");
		String memberId = request.getParameter("memberId");
		
		Map<String, Object> paramMap = new HashMap<>(); //파라미터 담을 map
		paramMap.put("itemNo", itemNo);
		paramMap.put("qnaContent", qnaContent);
		paramMap.put("memberId", memberId);
		
		try {
			//업무로직
			int result = new ItemService().insertItemQna(paramMap);
			
			//뷰단처리
			String msg = "";
			
			if(result>0) msg = "상품Q&A등록이 완료되었습니다.";
			else msg = "상품Q&A등록에 실패하였습니다.";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", "/item/itemView?categoryNo="+categoryNo+"&itemNo="+itemNo);
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
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
