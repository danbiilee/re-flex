package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class MypageReviewUpdate
 */
@WebServlet("/mypage/mypageReviewUpdate")
public class MypageReviewUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageReviewUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 핸들링
				int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
				System.out.println("reviewNO@====="+reviewNo);
				
				//게시글 상세보기 관련 쿠키처리
				Cookie[] cookies = request.getCookies();
				String boardCookieVal = "";//이미 존재하는 value
				boolean hasRead = false;
				
				//사이트 첫방문시, 아무런 쿠키가 없다. jsessionid값도 없다. 
				if(cookies != null) {
					for(Cookie c : cookies) {
						String name = c.getName();
						String value = c.getValue();
//						System.out.println("Cookie: "+ name+" = "+value);
						if("boardCookie".equals(name)) {
							boardCookieVal = value;
							if(value.contains("|"+reviewNo+"|")) {
								hasRead = true;
							}
						}
					}
				}
				
				//2.업무로직
				Board b = new BoardService().selectOne(reviewNo);
				System.out.println("mypage@review ="+b);
				String view = "";
				if(b == null){
					request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
					request.setAttribute("loc", "/mypage/mypageReview");
					view = "/WEB-INF/views/common/msg.jsp";
				}
				else {
					request.setAttribute("board", b);
					
					view = "/WEB-INF/views/mypage/mypageReviewUpdate.jsp";			
				}
				request.getRequestDispatcher(view)
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
