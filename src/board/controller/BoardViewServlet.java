package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;


/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터핸들링
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		//게시글 상세보기 관련 쿠키처리
		Cookie[] cookies = request.getCookies();
		String boardCookieVal = "";//이미 존재하는 value
		boolean hasRead = false;
		
		//사이트 첫방문시, 아무런 쿠키가 없다. jsessionid값도 없다. 
		if(cookies != null) {
			for(Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
//				System.out.println("Cookie: "+ name+" = "+value);
				if("boardCookie".equals(name)) {
					boardCookieVal = value;
					if(value.contains("|"+boardNo+"|")) {
						hasRead = true;
					}
				}
			}
		}
		
		//이 게시글을 읽은 적이 없다면, 쿠키 재생성
		if(!hasRead) {
			//session cookie : 
			//	setMaxAge를 설정하지 않은 경우. 클라이언트 닫는 경우 소멸.
			//	setMaxAge(-1)과 동일. 기본값.
			//persistent cookie :
			// 	setMaxAge를 설정한 경우, 지정한 시각까지 영속함.
			Cookie boardCookie = new Cookie("boardCookie", boardCookieVal+"|"+boardNo+"|");
			boardCookie.setPath(request.getContextPath()+"/board");
			boardCookie.setMaxAge(365*24*60*60);//영속쿠키 작성
			response.addCookie(boardCookie);
		}
		
		//2.업무로직
		Board board = new BoardService().selectOne(boardNo);
		
		//3.view단처리:db에서 읽어온 Board객체가 null인 경우, msg.jsp를 통해서
		//"해당하는 글은 없습니다." 사용자에게 알려주고, 목록페이지로 이동시킬것
		//게시글 가져오기에 실패한경우
		String view = "";//RequestDispatcher객체에 전달한 view단 주소
		if(board == null){
			request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			request.setAttribute("loc", "/board/boardList");
			view = "/WEB-INF/views/common/msg.jsp";
		}
		else {
			request.setAttribute("board", board);
			
			view = "/WEB-INF/views/board/boardView.jsp";			
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
