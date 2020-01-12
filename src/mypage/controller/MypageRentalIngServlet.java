package mypage.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.model.service.ItemService;
import item.model.vo.ItemImage;
import rent.model.service.rentService;
import rent.model.vo.rent;

/**
 * Servlet implementation class MypageRentalIngServlet
 */
@WebServlet("/mypage/mypageRentalIng")
public class MypageRentalIngServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
		
		String itemrentuser = request.getParameter("memberId");
		Date itemrentend = setTimestamp(request.getParameter("item_rent_end"));
		
		List<rent> list = new rentService().rentingviewList(itemrentuser);
		int cnt = new rentService().rentingcnt(itemrentuser);
		List<Integer> dday = new rentService().rentingdday(itemrentuser);
		
		//상품이미지 가져오기
		List<Integer> itemNoList = new ArrayList<>(); //상품번호 담을 리스트
		Map<Integer, List<ItemImage>> imgMap = new HashMap<>(); //키:상품번호, 값:해당 상품 이미지리스트
		
		//상품번호 담기
		for(rent r: list){
			itemNoList.add(r.getItemNo());
		}
		//상품이미지 담기
		for(int i=0; i<itemNoList.size(); i++) {
			List<ItemImage> imgList = new ItemService().selectItemImageList(itemNoList.get(i));
			imgMap.put(itemNoList.get(i), imgList);
		}
		
		//4.뷰단 포워딩		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/mypage/mypageRentalIng.jsp");
		request.setAttribute("list",list);
		request.setAttribute("dday",dday);
		request.setAttribute("cnt", cnt);	
		request.setAttribute("itemNoList", itemNoList);	
		request.setAttribute("imgMap", imgMap);	
		reqDispatcher.forward(request, response);
		
	}
	 
	private Date setTimestamp(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
