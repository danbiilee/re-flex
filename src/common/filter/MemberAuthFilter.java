package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;

@WebFilter(servletNames = { "MemberViewServlet" })
public class MemberAuthFilter implements Filter {

    public MemberAuthFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 *  로그인한 사용자가 다른 사용자의 상세보기 페이지를 요청하는 경우를 방지한다.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//로그인한 사용자의 아이디 == memberView?memberId 비교
		
		//로그인 한 사용자의 아이디
		HttpSession session = ((HttpServletRequest)request).getSession();
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		
		//상세보기를 요청한 아이디
		String viewMemberId = ((HttpServletRequest)request).getParameter("memberId");
		
		if(memberLoggedIn == null || !(memberLoggedIn.getMemberId().equals(request.getParameter("memberId")) || "admin".equals(memberLoggedIn.getMemberId()))) {
			request.setAttribute("msg", "다른 사용자의 계정은 확인할 수 없습니다.");
			request.setAttribute("loc", "/member/memberView?memberId="+(memberLoggedIn.getMemberId()));
	
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
