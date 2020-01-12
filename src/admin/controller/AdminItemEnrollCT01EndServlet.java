package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.ReflexFileRenamePolicy;
import item.model.service.ItemService;
import item.model.vo.Item;
import item.model.vo.ItemImage;

@WebServlet("/admin/itemEnrollEnd/CT01")
public class AdminItemEnrollCT01EndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminItemEnrollCT01EndServlet() {
        super();
    }

    /**
	 * 1.파일업로드 절차
	 * 	- 저장경로 
	 * 	- 파일용량제한
	 *  - 파일rename정책 : FileRenamePolicy
	 *  - MultipartReqeust객체생성: 파일입출력수행
	 * 2. 사용자입력값처리: MultipartRequest객체 
	 * 3. 업무로직처리
	 * 4. view단 위임
	 * 
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("======start======");
			int result=0;
			//파일 저장 경로 설정하기
			String saveImgDirectory = getServletContext().getRealPath("/images/CT01");
			
			//파일 최대 업로드 크기 제한 : 10MB까지 제한
			int maxImageSize = 1024*1024*10;
			
			//파일명 재지정 정책 객체
			FileRenamePolicy reflexRileRenamePolicy = new ReflexFileRenamePolicy();
			MultipartRequest multiReq = new MultipartRequest(request, saveImgDirectory, maxImageSize, "utf-8", reflexRileRenamePolicy);
//			multiReq.set
			
			
			//1.parameter handling
			String categoryNo = multiReq.getParameter("category");
			String itemBrand = multiReq.getParameter("itemBrand");
			String itemName = multiReq.getParameter("itemName");
			int itemPrice = Integer.parseInt(multiReq.getParameter("itemPrice"));
			int itemStock = Integer.parseInt(multiReq.getParameter("itemStock"));
			String itemDesc = multiReq.getParameter("itemDesc");
			
			//XSS공격대비 &문자변환
			itemDesc = itemDesc.replaceAll("<", "&lt;")
							   .replaceAll(">", "&gt;")
							   .replaceAll("\\n", "<br/>");
			
			//대표이미지
			String itemImageMainDefault = multiReq.getOriginalFileName("inputItemImageMain");
			String itemImageMainRenamed = multiReq.getFilesystemName("inputItemImageMain");
			
			//서브이미지1
			String itemImageSub1Default = multiReq.getOriginalFileName("inputItemImageSub1");
			String itemImageSub1Renamed = multiReq.getFilesystemName("inputItemImageSub1");
			
			
			//서브이미지2
			String itemImageSub2Default = multiReq.getOriginalFileName("inputItemImageSub2");
			String itemImageSub2Renamed = multiReq.getFilesystemName("inputItemImageSub2");
			
			
			//서브이미지3
			String itemImageSub3Default = multiReq.getOriginalFileName("inputItemImageSub3");
			String itemImageSub3Renamed = multiReq.getFilesystemName("inputItemImageSub3");
			
			
			//상세이미지
			String itemImageDetailDefault = multiReq.getOriginalFileName("inputItemImageDetail");
			String itemImageDetailRenamed = multiReq.getFilesystemName("inputItemImageDetail");
			
			
			//2.business logic
			Item item = new Item();
			item.setCategoryNo(categoryNo);
			item.setItemStock(itemStock);
			item.setItemBrand(itemBrand);
			item.setItemName(itemName);
			item.setItemPrice(itemPrice);
			item.setItemDesc(itemDesc);
			
			//item테이블에 상품 정보 등록
			result = new ItemService().enrollItem(item);
			
			//상품 번호 조회
			int itemNo = new ItemService().selectItemLastNo();
			System.out.println("itemNo@servlet="+itemNo);
			
			//이미지테이블에 상품번호를 포함하여 데이터 저장
			//대표이미지
			ItemImage itemImgMain = new ItemImage();
			itemImgMain.setItemNo(itemNo);
			itemImgMain.setItemImageTypeNo("IMG01");
			itemImgMain.setItemImageDefault(itemImageMainDefault);
			itemImgMain.setItemImageRenamed(itemImageMainRenamed);
			
			result = new ItemService().enrollImage(itemImgMain);
			
			//서브이미지1
			if(itemImageSub1Default != null) {
				ItemImage itemImgSub1 = new ItemImage();
				itemImgSub1.setItemNo(itemNo);
				itemImgSub1.setItemImageTypeNo("IMG02");
				itemImgSub1.setItemImageDefault(itemImageSub1Default);
				itemImgSub1.setItemImageRenamed(itemImageSub1Renamed);
				result = new ItemService().enrollImage(itemImgSub1);
			}
			
			//서브이미지2
			if(itemImageSub2Default != null) {
				ItemImage itemImgSub2 = new ItemImage();
				itemImgSub2.setItemNo(itemNo);
				itemImgSub2.setItemImageTypeNo("IMG03");
				itemImgSub2.setItemImageDefault(itemImageSub2Default);
				itemImgSub2.setItemImageRenamed(itemImageSub2Renamed);
				result = new ItemService().enrollImage(itemImgSub2);
			}
			
			//서브이미지3
			if(itemImageSub3Default != null) {
				ItemImage itemImgSub3 = new ItemImage();
				itemImgSub3.setItemNo(itemNo);
				itemImgSub3.setItemImageTypeNo("IMG04");
				itemImgSub3.setItemImageDefault(itemImageSub3Default);
				itemImgSub3.setItemImageRenamed(itemImageSub3Renamed);
				result = new ItemService().enrollImage(itemImgSub3);
			}
			
			//상세이미지
			if(itemImageDetailDefault != null) {
				ItemImage itemImgDetail = new ItemImage();
				itemImgDetail.setItemNo(itemNo);
				itemImgDetail.setItemImageTypeNo("IMG05");
				itemImgDetail.setItemImageDefault(itemImageDetailDefault);
				itemImgDetail.setItemImageRenamed(itemImageDetailRenamed);
				result = new ItemService().enrollImage(itemImgDetail);
			}
			
			
			//3.뷰단 처리
			String msg = "";
			String loc = "/";
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			
			if(result > 0) {
				msg = item.getItemName()+"이 등록되었습니다.";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				requestDispatcher.forward(request, response);
			}
			else {
				msg = "상품 추가에 실패했습니다.";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				requestDispatcher.forward(request, response);
			}
		} catch(Exception e) {
			System.out.println("itemEnrollEndServlet 예외처리함");
			throw e;//컨테이너한테 예외를 던져야 에러페이지 전환이 가능하다.
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
