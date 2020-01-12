package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.전송값 꺼내서 변수에 기록하기.
		int Review_no = Integer.parseInt(request.getParameter("Review_no"));
		String renamedFileName = request.getParameter("renamedFileName");

		//2.서비스로직호출
		int result = new BoardService().deleteBoard(Review_no);
		
		//파일삭제
	//	if(result>0 && !"".equals(renamedFileName)) {
			//파일저장경로
//			String saveDirectory
//				= getServletContext().getRealPath("/upload/board");
//			
//			File delFile = new File(saveDirectory, renamedFileName);
//			System.out.println("delFile="+delFile);
			
			//1.삭제처리
//			boolean bool = delFile.delete();
			
			//2.이동처리
//			String delDirectory 
//				= getServletContext().getRealPath("/delete/board");
//			File delFileTo = new File(delDirectory, renamedFileName);
//			boolean bool = delFile.renameTo(delFileTo);
			
//			System.out.println("파일삭제 : "+(bool?"성공!":"실패!"));
		//}
		
		//3. 받은 결과에 따라 뷰페이지 내보내기
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		//javascript/html에서 사용할 url은 contextPath를 포함한다.
		String loc = "/mypage/mypageReview";

		if(result>0)
			msg = "게시글 삭제 성공!";
			
		else 
			msg = "게시글 삭제 실패!";	
		
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
