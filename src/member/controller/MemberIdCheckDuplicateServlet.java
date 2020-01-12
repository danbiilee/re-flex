package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/member/memberIdCheckDuplicate")
public class MemberIdCheckDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberIdCheckDuplicateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.encoding
		request.setCharacterEncoding("UTF-8");
		
		//2.parameter handling
		String memberId = request.getParameter("memberId");
		
		//3.business logic
		Member m = new MemberService().selectOne(memberId);
		boolean isUsable = m==null?true:false;
		
		
		//4.view단 처리
		request.setAttribute("isUsable", isUsable);
		request.getRequestDispatcher("/WEB-INF/views/member/memberIdcheckDuplicate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
