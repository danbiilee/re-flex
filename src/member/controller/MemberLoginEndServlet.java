package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet(name="MemberLoginEndServlet", 
urlPatterns="/member/memberLoginEnd")
public class MemberLoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2.parameter
				String memberId = request.getParameter("memberId");
				String memberPwd = request.getParameter("memberPwd");
				//System.out.println("memberId="+memberId);
				//System.out.println("memberPwd="+memberPwd);
				
				//3.businessLogic
				Member m = new MemberService().selectOne(memberId);
//				System.out.println("member@loginServlet="+m);
				
				String msg = "";
				String loc = "/";
				RequestDispatcher reqDispatcher 
					= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				
				//아이디가 존재하지 않는 경우 => 로그인 실패
				if(m == null) {
					msg = "존재하지 않는 아이디입니다.";
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
					reqDispatcher.forward(request, response);
					
				}
				else {
					//사용자입력비번과 db회원정보비번 비교
					//비번이 틀린 경우 =>  로그인 실패
					if(!m.getMemberPassword().equals(memberPwd)) {
						msg = "비밀번호가 틀렸습니다.";
						request.setAttribute("msg", msg);
						request.setAttribute("loc", loc);
						reqDispatcher.forward(request, response);
						  
					}
					//아이디/비번이 모두 일치한 경우 => 로그인 성공
					else {
						//세션이 있으면, 해당세션을 리턴하고, 없으면, 새로생성해서 리턴
						HttpSession session = request.getSession(true);
						//System.out.println("SESSIONID="+session.getId());
						//세션에 로그인한 회원객체 속성으로 저장
						session.setAttribute("memberLoggedIn", m);
						
						//session유효시간 설정: 초단위
						session.setMaxInactiveInterval(60*30);
						
						//아이디저장 쿠키관련
						//체크된경우:"on", 체크되지 않은 경우:null
						String saveId = request.getParameter("saveId");
//						System.out.println("saveId@loginServlet="+saveId);
						
						//체크한경우
						if(saveId != null) {
							Cookie c = new Cookie("saveId", memberId);
							c.setMaxAge(7*24*60*60);//7일후 소멸
							c.setPath("/");//쿠키사용디렉토리. 도메인 전역에서 사용함.
							response.addCookie(c);
						}
						//체크하지 않은 경우
						else {
							Cookie c = new Cookie("saveId", memberId);
							c.setMaxAge(0);//삭제를 위해 유효기간을 0으로 설정
							c.setPath("/");
							response.addCookie(c);
						}
						
						
						//로그인후 페이지 리다이렉트
						response.sendRedirect(request.getContextPath());	
						
						
					}
					
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
