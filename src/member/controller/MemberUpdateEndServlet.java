package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet(name="MemberUpdateEndServlet", 
urlPatterns="/member/memberUpdateEnd")
public class MemberUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdateEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		if(hobbyArr == null) {
			memberHobby1 = null;
			memberHobby2 = null;
		}
		else if(hobbyArr.length == 1) {
			memberHobby1 = hobbyArr[0];
			memberHobby2 = null;
		}
		else if(hobbyArr.length == 2) {
			memberHobby1 = hobbyArr[0];
			memberHobby2 = hobbyArr[1];
		}

		
		member.setMemberHobby1(memberHobby1);
		member.setMemberHobby2(memberHobby2);
		
		int result = new MemberService().memberUpdate(member);
		
		//4.view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/member/memberUpdate?memberId="+memberId; // index페이지 가르키는 중 
		
		if(result>0) {
			msg="회원 수정 성공.";
		}
		else {
			msg="회원 수정 실패.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
//		HttpSession session = request.getSession(true);
		
//		Member MemberLoggedIn = (Member)session.getAttribute("memberLoggedIn");

//		//로그인한 회원이 admin이아닌 경우에만 session 수정
//		if(!"admin".equals(MemberLoggedIn.getMemberId())) {
//			//세션에 로그인한 회원 객체를 속성으로 저장.
//			session.setAttribute("memberLoggedIn", member);
//		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
