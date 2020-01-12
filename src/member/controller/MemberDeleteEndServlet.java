package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberDeleteServlet
 */
@WebServlet(name="MemberDeleteEndServlet", 
urlPatterns="/member/memberDeleteEnd")
public class MemberDeleteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//2.전송값 꺼내서 변수에 기록하기.
				//String javax.servlet.ServletRequest.getParameter(String arg0)
				String memberId = request.getParameter("memberId");
				String memberPwd=request.getParameter("memberPwd");
				System.out.println(memberId);
				//3.서비스로직호출
				int result = new MemberService().memberDelete(memberId,memberPwd);
		
		
		
		
		//4. 받은 결과에 따라 뷰페이지 내보내기
				String view = "/WEB-INF/views/common/msg.jsp";
				String msg = "";
				//javascript/html에서 사용할 url은 contextPath를 포함한다.
				String loc = "/";

				if(result>0) {
					msg = "성공적으로 회원정보를 삭제했습니다.";
					
					Member memberLoggedIn
						= (Member)request.getSession().getAttribute("memberLoggedIn");
					if(!"admin".equals(memberLoggedIn.getMemberId())) {
						//회원탈퇴인 경우, 로그아웃 처리함.
						loc = "/member/logout";				
					}
				}
				else 
					msg = "회원정보삭제에 실패했습니다.";	
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
				reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
