package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Board;
import common.MvcFileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateFormServlet
 */
@WebServlet("/board/boardUpdateForm")
public class BoardUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일이 저장될 절대경로 가져오기
				String saveDirectory
					= getServletContext().getRealPath("/upload/board");// / 웹루트 WebContent
				System.out.println("saveDirectory="+saveDirectory);
				
				//파일최대업로드크기 제한: 10MB까지 제한
				//10MB = 1024 * 1024 * 10 
				int maxPostSize = 1024 * 1024 * 10; 
				
				//파일명 재지정 정책 객체
				FileRenamePolicy mvcFileRenamePolicy
					= new MvcFileRenamePolicy();
				MultipartRequest multiReq = new MultipartRequest(request,
																 saveDirectory, 
																 maxPostSize, 
																 "utf-8",
																 mvcFileRenamePolicy);
				
				
				
				//1.parameter handling
				int reviewStar =Integer.parseInt(multiReq.getParameter("star"));
				int reviewNo =Integer.parseInt(multiReq.getParameter("reviewNo"));
				int itemNo =Integer.parseInt(multiReq.getParameter("itemNo"));
				int orderDetailNo =Integer.parseInt(multiReq.getParameter("orderDetailNo"));
				String reviewWriter = multiReq.getParameter("reviewWriter");
				String reviewContent = multiReq.getParameter("reviewContent");
		
				
				String review_image 
					= multiReq.getOriginalFileName("upFile");//사용자 업로드한 파일명
				String renamedFileName
					= multiReq.getFilesystemName("upFile");//실제 저장된 파일명
				
				//기존첨부파일 정보: 파일이 없는 경우 ""값이 넘어온다.
				String oldOriginalFileName
					= multiReq.getParameter("oldOriginalFileName");
				String oldRenamedFileName
				= multiReq.getParameter("oldRenamedFileName");
				
				if(!"".equals(oldOriginalFileName)) {
					
					//신규첨부파일 유무 검사: upFile파일첨부가 없는 경우, null을 리턴
					File f = multiReq.getFile("upFile");
					
					//신규첨부파일이 있는 경우, 기존첨부파일 삭제
					if(f!=null) {
						File delFile = new File(saveDirectory, oldRenamedFileName);
						boolean result = delFile.delete();
						System.out.println("기존첨부파일삭제: "+(result?"성공!":"실패!"));
					}
					//신규첨부파일이 없는 경우: 기존파일 삭제
					else if(multiReq.getParameter("delFileChk")!=null) {
						File delFile = new File(saveDirectory, oldRenamedFileName);
						boolean result = delFile.delete();
						System.out.println("기존첨부파일삭제: "+(result?"성공!":"실패!"));				
					}
					//신규첨부파일이 없는 경우: 기존파일 유지
					else {
						review_image = oldOriginalFileName;
						renamedFileName = oldRenamedFileName;
					}
				}
				
				Board b = new Board(reviewNo,orderDetailNo,reviewWriter,null,reviewStar,reviewContent,review_image,renamedFileName,0,itemNo);
				
				
				
				//2.business logic
				int result = new BoardService().updateBoard(b);
				
				String msg = "";
				String loc = "/mypage/mypageReview?memberId="+reviewWriter;
				
				if(result>0) {
					msg = "게시글 수정 성공!";
				}
				else {
					msg = "게시글 수정 실패! 관리자에게 문의하세요.";
				}
				
				
				//3.view단 처리
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
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
