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

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(servletNames = { "MemberViewServlet" },
urlPatterns = {"/board/boardForm",
			   "/board/boardFormEnd",
			   "/board/boardCommentInsert"
			  })
public class LoginFilter implements Filter {

    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//로그인 여부 : session의 속서 memberLoggedIn객체의 null여부 판단
		// - 부모타입으로는 자식타입의 메서드를 사용할 수 없다.
		HttpSession session = ((HttpServletRequest)request).getSession();
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		
		//로그인 하지 않은 경우
		if(memberLoggedIn == null) {
			request.setAttribute("msg", "로그인 후 이용하세요.");
			request.setAttribute("loc", "/");
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
			return;
			//forward처리 후 다른 코드 동작 X => 내부 오류 발생 유발
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
