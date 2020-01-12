package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import admin.model.service.AdminService;
import admin.model.vo.QnaAns;
import common.MvcFileRenamePolicy;
import mypage.model.vo.Qna;

/**
 * Servlet implementation class AdminMemberQnaFormEndServlet
 */
@WebServlet("/admin/member/qnaFormEnd")
public class AdminMemberQnaFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberQnaFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String aContent = request.getParameter("aContent");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@"+qNo);
		
		//XSS공격대비 &문자변환
		aContent = aContent.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\\n", "<br/>");
		
		QnaAns a = new QnaAns(0,qNo,"", null, aContent);
		
		System.out.println("qna form ens servlet - QnaAns @@  " + a);
		
		int result = new AdminService().insertAns(a);
		
		String msg = "";
		String loc = "/admin/member/memberQna";
		
		if(result>0) {
			msg = "1:1 문의답변 등록 성공!";
		}
		else {
			msg = "1:1 문의답변 등록 실패!";
		}
		
		//3.view단 처리
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
