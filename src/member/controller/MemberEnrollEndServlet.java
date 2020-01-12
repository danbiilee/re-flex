package member.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet(name="MemberEnrollEndServlet", 
			urlPatterns="/member/memberEnrollEnd")
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//1.encoding
			request.setCharacterEncoding("UTF-8");
			
			//2.parameter handling
			String memberName = request.getParameter("memberName");
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			String tel1 = request.getParameter("tel1");
			String tel2 = request.getParameter("tel2");
			String memberEmail = request.getParameter("memberEmail");
			int memberPostcode = Integer.parseInt(request.getParameter("memberPostcode"));
			String memberAddress1 = request.getParameter("memberAddress1");
			String memberAddress2 = request.getParameter("memberAddress2");
			String[] hobbyArr = request.getParameterValues("hobby");
			
			//3.business logic
			Member member = new Member();
			member.setMemberName(memberName);
			member.setMemberId(memberId);
			member.setMemberPassword(memberPwd);
			member.setMemberPhone(tel1+tel2);
			member.setMemberEmail(memberEmail);
			member.setMemberPostcode(memberPostcode);
			member.setMemberAddress(memberAddress1);
			member.setMemberDetailAddress(memberAddress2);
			
			String memberHobby1 = null;
			String memberHobby2 = null;
			
			if(hobbyArr.length == 1) {
				memberHobby1 = hobbyArr[0];
				memberHobby2 = null;
			}
			else if(hobbyArr.length == 2) {
				memberHobby1 = hobbyArr[0];
				memberHobby2 = hobbyArr[1];
			}
			else {
				
			}
			
			member.setMemberHobby1(memberHobby1);
			member.setMemberHobby2(memberHobby2);
			
			System.out.println("before :: m@EnrollEndServlet.java="+member);
			
			int result = new MemberService().memberEnroll(member);
			System.out.println("result@EnrollEndServlet.java="+result);
			System.out.println("after :: m@EnrollEndServlet.java="+member);
			
			//4.view단 처리 : msg.jsp사용 -> index페이지로 리다이렉트
			String msg = "";
			String loc = "/";
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			
			if(result > 0) {
				msg = member.getMemberId()+"님 회원가입에 성공하셨습니다.";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				requestDispatcher.forward(request, response);
			}
			else {
				msg = "회원가입에 실패하였습니다.";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				requestDispatcher.forward(request, response);
			}
		} catch(Exception e) {
			System.out.println("memberEnrollEndServlet 예외처리함");
			throw e;//컨테이너한테 예외를 던져야 에러페이지 전환이 가능하다.
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
