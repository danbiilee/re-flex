package item.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import item.model.service.ItemService;
import item.model.vo.Item;
import item.model.vo.ItemImage;
import item.model.vo.ItemQna;
import item.model.vo.ItemQnaAns;
import order.model.vo.OrderDetail;

/**
 * Servlet implementation class ItemViewServlet
 */
@WebServlet("/item/itemView")
public class ItemViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 핸들링
		String categoryNo = request.getParameter("categoryNo");
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		
		//a.페이징: 컨텐츠영역
		int cPage = 1;
		final int numPerPage = 5;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		}catch(NumberFormatException e) {
			
		}

		ItemService itemService = new ItemService();
		BoardService boardService = new BoardService();
		try {
			//b.페이징바영역
			int pageBarSize = 5; 
			//이용후기
			int reviewTotalContent = boardService.selectBoardCount(itemNo);
			int reviewTotalPage =  (int)Math.ceil((double)reviewTotalContent/numPerPage);//(공식2)
			String reviewPageBar = getPageBar(request, categoryNo, itemNo, cPage, pageBarSize, reviewTotalPage);
			//Q&A
			int qnaTotalContent = itemService.selectItemQnaCount(itemNo);
			int qnaTotalPage =  (int)Math.ceil((double)reviewTotalContent/numPerPage);//(공식2)
			String qnaPageBar = getPageBar(request, categoryNo, itemNo, cPage, pageBarSize, qnaTotalPage);
			
			
			//업무로직
			//상품정보, 상품이미지 가져오기
			Item item = itemService.selectItemOne(itemNo);
			List<ItemImage> imgList = itemService.selectItemImageList(itemNo);
			
			//상품 이용후기 가져오기
			List<Board> list = boardService.selectBoardList(itemNo, cPage, numPerPage); 
			
			//상품 Q&A질문 가져오기
			List<ItemQna> qList = itemService.selectItemQnaList(itemNo, cPage, numPerPage);
			
			//상품 Q&A답변 가져오기
			Map<Integer, ItemQnaAns> qnaMap = new HashMap<>(); //키:상품Q&A번호 - 값:해당 Q&A답변
			ItemQnaAns qnaAns = null; //값:해당 Q&A답변
			
			for(ItemQna q: qList) {
				qnaAns = itemService.selectIteQnaAnsOne(q.getItemQnaNo());
				qnaMap.put(q.getItemQnaNo(), qnaAns);
			}
			
			
			//뷰단처리
			String view = "";
			if(item!=null && (imgList!=null && !imgList.isEmpty()) && qList!=null && qnaMap!=null && list!=null) {
				view = "/WEB-INF/views/item/itemView.jsp";
				
				request.setAttribute("categoryNo", categoryNo);
				request.setAttribute("item", item); //상품
				request.setAttribute("imgList", imgList); //상품이미지 리스트
				
				request.setAttribute("list",list); //이용후기 리스트
				request.setAttribute("qList", qList); //qna 리스트
				request.setAttribute("qnaMap", qnaMap); //qna+답변
				
				request.setAttribute("reviewPageBar",reviewPageBar); //이용후기 pageBar
				request.setAttribute("qnaPageBar",qnaPageBar); //qna pageBar
				
				request.setAttribute("reviewTotalContent", reviewTotalContent); //이용후기 총 게시글수
				request.setAttribute("qnaTotalContent", qnaTotalContent); //qna 총 게시글수
			}
			else {
				view = "/WEB-INF/views/common/msg.jsp";
				request.setAttribute("msg", "상품상세조회 실패!");
				request.setAttribute("loc", "/");
			}
			
			request.getRequestDispatcher(view).forward(request, response);
		} catch(Exception e) {
			throw e;
		}
		
	}
	
	private String getPageBar(HttpServletRequest request, String categoryNo, int itemNo, int cPage, int pageBarSize, int totalPage) {
		String pageBar = "";
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;//(공식3)
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		
		//1.이전
		if(pageNo!=1) 
			pageBar += "<li><a href='"+request.getContextPath()+"/item/itemView?categoryNo="+categoryNo+"&itemNo="+itemNo+"&cPage="+(pageNo-1)+"' aria-label='Previous'><span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span></a></li>\n";
		else 
			pageBar += "<li><a href='"+request.getContextPath()+"/item/itemView?categoryNo="+categoryNo+"&itemNo="+itemNo+"&cPage=1' aria-label='Previous'><span class='glyphicon glyphicon-menu-left' aria-hidden='true'></span></a></li>\n";
		//2.pageNo
		while(pageNo<=pageEnd && pageNo<=totalPage) {
			if(cPage==pageNo)
				pageBar += "<li class='cPage'><a href='"+request.getContextPath()+"/item/itemView?categoryNo="+categoryNo+"&itemNo="+itemNo+"&cPage="+pageNo+"'>"+pageNo+"</a></li>\n";
			else
				pageBar += "<li><a href='"+request.getContextPath()+"/item/itemView?categoryNo="+categoryNo+"&itemNo="+itemNo+"&cPage="+pageNo+"'>"+pageNo+"</a></li>\n";
			pageNo++;
		}
		//3.다음
		if(pageNo<=totalPage) 
			pageBar += "<li><a href='"+request.getContextPath()+"/item/itemView?categoryNo="+categoryNo+"&itemNo="+itemNo+"&cPage="+pageNo+"' aria-label='Next'><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span></a></li>\n";
		else 
			pageBar += "<li><a href='"+request.getContextPath()+"/item/itemView?categoryNo="+categoryNo+"&itemNo="+itemNo+"&cPage="+(pageNo-1)+"' aria-label='Next'><span class='glyphicon glyphicon-menu-right' aria-hidden='true'></span></a></li>\n";
		
		return pageBar;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
