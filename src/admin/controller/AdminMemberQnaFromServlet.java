package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import admin.model.vo.QnaAns;
import mypage.model.vo.Qna;

/**
 * Servlet implementation class AdminMemberQnaFromServlet
 */
@WebServlet("/admin/member/memberQnaForm")
public class AdminMemberQnaFromServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberQnaFromServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.파라미터핸들링
		int qNo = Integer.parseInt(request.getParameter("qNo"));

		// 2.업무로직
		Qna q = new AdminService().selectOne(qNo); // 회원이 남긴 문의 들고오기

		System.out.println("admin - member QNA Form@servlet=" + q);

		// 게시글 가져오기에 실패한경우
		String view = "";// RequestDispatcher객체에 전달한 view단 주소
		if (q == null) {
			request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			request.setAttribute("loc", "/admin/member/memberQna");
			view = "/WEB-INF/views/common/msg.jsp";
		} else {
			request.setAttribute("q", q);

			view = "/WEB-INF/views/admin/member/admin_member_qnaFrom.jsp";
		}
		request.getRequestDispatcher(view).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
