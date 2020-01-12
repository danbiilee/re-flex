package common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import admin.model.service.AdminService;
import item.model.service.ItemService;
import item.model.vo.Item;
import item.model.vo.ItemImage;
import member.model.vo.Member;

@WebFilter("/index.jsp")
public class IndexItemFilter implements Filter {

    public IndexItemFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//로그인 여부 : session의 속서 memberLoggedIn객체의 null여부 판단
		// - 부모타입으로는 자식타입의 메서드를 사용할 수 없다.
		HttpSession session = ((HttpServletRequest)request).getSession();
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		
		//4개씩 끊은 상품 리스트를 담을 리스트
		List<List<Item>> itemListList = new ArrayList<>();
		List<ItemImage> imgListList = new ArrayList<>();
		
		//4개씩 끊어 담을 리스트
		List<Item> itemList2 = new ArrayList<>();
		
		List<Item> itemList = null;
		List<ItemImage> imgList = null;
		
		List<String> categorySellAmount = new AdminService().selectCategorySellAmount();
		String[] categorySellAmountArr= {"",""};
		for(int i=0;i<categorySellAmountArr.length;i++) {
			String str="";
			switch(categorySellAmount.get(i)) {
			case "CT01" : str="반려동물"; break;
			case "CT02" : str="육아"; break;
			case "CT03" : str="파티"; break;
			case "CT04" : str="운동"; break;
			case "CT05" : str="여행"; break;
			case "CT06" : str="캠핑"; break;
			}
			categorySellAmountArr[i] = str;
			
		}
		
		String[] hobbyArr= {"",""};
		//로그인한 경우
		if(memberLoggedIn != null) {
			if(memberLoggedIn.getMemberHobby1() == null) {
				hobbyArr[0] = categorySellAmountArr[0];
				hobbyArr[1] = categorySellAmountArr[1];
			}
			else if(memberLoggedIn.getMemberHobby2() == null) {
				hobbyArr[0] = memberLoggedIn.getMemberHobby1();
				hobbyArr[1] = categorySellAmountArr[1];
			}
			else {
				hobbyArr[0] = memberLoggedIn.getMemberHobby1();
				hobbyArr[1] = memberLoggedIn.getMemberHobby2();
			}
		}
		else {
			hobbyArr[0] = categorySellAmountArr[0];
			hobbyArr[1] = categorySellAmountArr[1];
		}
		System.out.println(hobbyArr[0]+"/"+hobbyArr[1]);
		for(String str : hobbyArr) {
			switch(str) {
			case "반려동물" : str="CT01"; break;
			case "육아" : str="CT02"; break;
			case "파티" : str="CT03"; break;
			case "운동" : str="CT04"; break;
			case "여행" : str="CT05"; break;
			case "캠핑" : str="CT06"; break;
			}
			//카테고리에 맞는 상품 리스트 가져오기
			itemList = new ItemService().selectItemList(str);
			System.out.println("itemList@filter="+itemList);
			
			//상품리스트중 4개만 추출
			for(int i=0; i<4; i++) {
				itemList2.add(itemList.get(i));
			}
			//추출한 4개를 리스트에 담기
			itemListList.add(itemList2);
			
			List<Integer> itemNoList = new ArrayList<>(); //상품번호 담을 리스트
			if(itemList2!=null) {
				//상품번호 담기
				for(Item i: itemList2){
					itemNoList.add(i.getItemNo());
				}
				for(int i=0; i<itemNoList.size(); i++) {
					//상품이미지 담기
					imgList = new ItemService().selectItemMainImageList(itemNoList.get(i));
					imgListList.add(imgList.get(0));
				}
				//초기화
				itemList2 = new ArrayList<>();
			}
		}
	
		
		request.setAttribute("itemListList", itemListList);
		
		if(imgList!=null) {
			request.setAttribute("imgListList", imgListList);
		}
		
		request.setAttribute("hobbyArr", hobbyArr);
		System.out.println("hobbyArr@filter"+hobbyArr);
		request.getRequestDispatcher("/index.jsp").forward(request, response);

				
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("===================index필터 시작==============");
	}

}
