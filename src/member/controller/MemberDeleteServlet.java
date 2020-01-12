package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2.parameter handling
				String memberId = request.getParameter("memberId");
				
				//3.business logic
				Member m = new MemberService().selectOne(memberId);
				System.out.println("member@MemberViewServlet="+m);
				
				//4.view단
				String view = "";
				
				//조회된 회원이 있는 경우
				if(m != null) {
					view = "/WEB-INF/views/member/memberDelete.jsp";
					//조회된 회원 속성으로 저장
					request.setAttribute("member", m);
				}
				//조회된 회원이 없는 경우
				else {
					String loc = "/";
					String msg = "해당회원이 없습니다.";
					view = "/WEB-INF/views/common/msg.jsp";
					
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
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
