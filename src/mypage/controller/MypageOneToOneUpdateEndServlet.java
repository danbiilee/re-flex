package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import mypage.model.service.qService;
import mypage.model.vo.Qna;

/**
 * Servlet implementation class MypageOneToOneUpdateEndServlet
 */
@WebServlet("/mypage/mypageOneToOneUpdateEnd")
public class MypageOneToOneUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//파일이 저장될 절대경로 가져오기
				String saveDirectory
					= getServletContext().getRealPath("/upload/board");//웹루트 WebContent
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
				int qNo =Integer.parseInt(multiReq.getParameter("qNo"));
				System.out.println("qNo="+qNo);
				String qTypeNo = multiReq.getParameter("q-sort");
				System.out.println("qtype="+qTypeNo);
				String qTitle = multiReq.getParameter("qTitle");
				String qContent = multiReq.getParameter("qContent");
				String memberId = multiReq.getParameter("memberId");
				System.out.println("qTitle="+qTitle);
				System.out.println("qContent="+qContent);
				System.out.println("memberId="+memberId);
				//XSS공격대비 &문자변환
				qContent = qContent.replaceAll("<", "&lt;")
										   .replaceAll(">", "&gt;")
										   .replaceAll("\\n", "<br/>");//개행문자처리
				String qImage
					= multiReq.getFilesystemName("upFile");//사용자 업로드한 파일명
//				String renamedFileName
//					= multiReq.getFilesystemName("upFile");//실제 저장된 파일명
				Qna q = new Qna(qNo,memberId,qTypeNo,qTitle,qContent,null,null,qImage);
				System.out.println("q@QnaFormEnd="+q);
				//2.business logic
				int result = new qService().updateQna(q);
				String msg = "";
				String loc = "/mypage/mypageOneToOne?memberId="+memberId;
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
