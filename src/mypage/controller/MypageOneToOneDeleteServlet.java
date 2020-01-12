package mypage.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import mypage.model.service.qService;

/**
 * Servlet implementation class MypageOneToOneDeleteServlet
 */
@WebServlet("/mypage/mypageOneToOneDelete2")
public class MypageOneToOneDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.parameter handling
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String memberId = request.getParameter("memberId");
//		String qImage = request.getParameter("qImage");
//		System.out.println(qNo+"/"+qImage);
		
		//2.business logic
		
		//DB삭제
		int result = new qService().deleteQna(qNo);
		
//		//파일삭제
//		if(result>0 && !"".equals(qImage)) {
//			//파일저장경로
//			String saveDirectory = getServletContext().getRealPath("/upload/board");
//			
//			File delFile = new File(saveDirectory, qImage);
//			System.out.println("delFile="+delFile);
//			
//			//1)삭제처리
//			boolean bool = delFile.delete();
//			
////			//2)이동처리
////			String delDirectory = getServletContext().getRealPath("/delete/board");
////			
////			File delFileTo = new File(delDirectory, qImage);
////			boolean bool = delFile.renameTo(delFileTo);
//			
//			System.out.println("파일삭제"+(bool?"성공":"실패"));
//		}
		
		
		//3.view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/"; // index페이지 가르키는 중 
		
		if(result>0) {
			msg="게시글 삭제 성공.";
			loc="/mypage/mypageOneToOne?memberId="+memberId;
		}
		else {
			msg="게시글 삭제 실패";
			loc="/mypage/mypageOneToOne?memberId="+memberId;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
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
