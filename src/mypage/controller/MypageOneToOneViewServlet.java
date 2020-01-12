package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import mypage.model.service.qService;
import mypage.model.vo.Qna;

/**
 * Servlet implementation class MypageOneToOneViewServlet
 */
@WebServlet("/mypage/mypageOneToOneView")
public class MypageOneToOneViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public MypageOneToOneViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.파라미터핸들링
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
				int qNo = Integer.parseInt(request.getParameter("qNo"));
				String ans = new AdminService().selectAns(qNo);
				
				System.out.println("@@@@@@@@qno="+qNo);
				
				//2.업무로직
				Qna q = new qService().selectOne(qNo);
			
				System.out.println("mypage - Mypage OneToOne View@servlet="+q);
				
				
				//게시글 가져오기에 실패한경우
				String msg = "";//RequestDispatcher객체에 전달한 view단 주소
				String loc="/";
				
				if(q == null){
					msg="해당 글 없습니다.";
					loc="/mypage/mypageOneToOne";
					
					request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
					
				}
				else {
					request.setAttribute("q", q);
					request.setAttribute("ans", ans);
					
					request.getRequestDispatcher("/WEB-INF/views/mypage/mypageOneToOneView.jsp").forward(request, response);
					
								
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
