package admin.controller;

import java.io.File;
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

@WebServlet("/admin/itemUpdateEnd")
public class AdminItemUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminItemUpdateEndServlet() {
        super();
    }
    
    /*
	 * 게시글 수정
	 * 
	 * 1.첨부파일이 있는 경우 : 
	 *   - 새로운 첨부파일을 수정하는 경우 -> 기존파일 삭제, 신규파일정보  DB등록
	 *   - 기존파일만 삭제하는 경우 -> 기존파일 삭제, db데이터 null값 대입
	 *   - 기존파일을 유지하는 경우 -> 기존파일 유지, db데이터 기존값 유지
	 * 
	 * 2.첨부파일이 없는 경우 : 게시글 최초 등록과 동일
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int result=0;
			//파일 저장 경로 설정하기
			String saveImgDirectory = getServletContext().getRealPath("/images/");
			
			//파일 최대 업로드 크기 제한 : 10MB까지 제한
			int maxImageSize = 1024*1024*10;
			
			//파일명 재지정 정책 객체
			FileRenamePolicy reflexRileRenamePolicy = new ReflexFileRenamePolicy();
			MultipartRequest multiReq = new MultipartRequest(request, saveImgDirectory, maxImageSize, "utf-8", reflexRileRenamePolicy);
			
			//1.parameter handling
			int itemNo = Integer.parseInt(multiReq.getParameter("itemNo"));
			String categoryNo = multiReq.getParameter("category_");
			String itemBrand = multiReq.getParameter("itemBrand");
			String itemName = multiReq.getParameter("itemName");
			int itemPrice = Integer.parseInt(multiReq.getParameter("itemPrice"));
			System.out.println(itemNo+"/"+categoryNo+"/"+itemBrand+"/"+itemName+"/"+itemPrice);
			int itemStock = Integer.parseInt(multiReq.getParameter("itemStock"));
			String itemDesc = multiReq.getParameter("itemDesc");
			
			int itemMainImageNo = Integer.parseInt(multiReq.getParameter("mainImg"));
			int itemSub1ImageNo = Integer.parseInt(multiReq.getParameter("sub1Img"));
			int itemSub2ImageNo = Integer.parseInt(multiReq.getParameter("sub2Img"));
			int itemSub3ImageNo = Integer.parseInt(multiReq.getParameter("sub3Img"));
			int itemDetailImageNo = Integer.parseInt(multiReq.getParameter("detailImg"));
			
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
			
			//XSS공격대비 &문자변환
			itemDesc = itemDesc.replaceAll("<", "&lt;")
							   .replaceAll(">", "&gt;")
							   .replaceAll("\\n", "<br/>");
			
			String oldOriginalFileNameMain = multiReq.getParameter("oldOriginalFileNameMain");
			String oldRenamedFileNameMain = multiReq.getParameter("oldRenamedFileNameMain");
			
			String oldOriginalFileNameSub1 = multiReq.getParameter("oldOriginalFileNameSub1");
			String oldRenamedFileNameSub1 = multiReq.getParameter("oldRenamedFileNameSub1");
			
			String oldOriginalFileNameSub2 = multiReq.getParameter("oldOriginalFileNameSub2");
			String oldRenamedFileNameSub2 = multiReq.getParameter("oldRenamedFileNameSub2");
			
			String oldOriginalFileNameSub3 = multiReq.getParameter("oldOriginalFileNameSub3");
			String oldRenamedFileNameSub3 = multiReq.getParameter("oldRenamedFileNameSub3");
			
			String oldOriginalFileNameDetail = multiReq.getParameter("oldOriginalFileNameDetail");
			String oldRenamedFileNameDetail = multiReq.getParameter("oldRenamedFileNameDetail");
			
			//대표이미지
			//기존 첨부파일이 있었는지 여부 확인
			if(!"".equals(oldOriginalFileNameMain)) {
				//신규첨부파일 유무 검사 : upFile 파일첨부가 없는 경우 null을 리턴
				File f = multiReq.getFile("inputItemImageMain");
				
				//신규 첨부파일이 있는 경우, 기존 첨부파일 삭제
				if(f!=null) {
					File delFile = new File(saveImgDirectory,oldRenamedFileNameMain);
					boolean result2 = delFile.delete();
					System.out.println("기존첨부파일삭제 : "+(result2?"성공":"실패"));
				}
				//기존 파일을 유지할 때
				else {
					itemImageMainDefault = oldOriginalFileNameMain;
					itemImageMainRenamed = oldRenamedFileNameMain;
				}
			}
			
			//서브이미지1
			//기존 첨부파일이 있었는지 여부 확인
			if(!"".equals(oldOriginalFileNameSub1)) {
				//신규첨부파일 유무 검사 : upFile 파일첨부가 없는 경우 null을 리턴
				File f = multiReq.getFile("inputItemImageSub1");
				
				//신규 첨부파일이 있는 경우, 기존 첨부파일 삭제
				if(f!=null) {
					File delFile = new File(saveImgDirectory,oldRenamedFileNameSub1);
					boolean result2 = delFile.delete();
					System.out.println("기존첨부파일삭제 : "+(result2?"성공":"실패"));
				}
				//기존 파일을 유지할 때
				else {
					itemImageSub1Default = oldOriginalFileNameSub1;
					itemImageSub1Renamed = oldRenamedFileNameSub1;
				}
			}
			
			//서브이미지2
			//기존 첨부파일이 있었는지 여부 확인
			if(!"".equals(oldOriginalFileNameSub2)) {
				//신규첨부파일 유무 검사 : upFile 파일첨부가 없는 경우 null을 리턴
				File f = multiReq.getFile("inputItemImageSub2");
				
				//신규 첨부파일이 있는 경우, 기존 첨부파일 삭제
				if(f!=null) {
					File delFile = new File(saveImgDirectory,oldRenamedFileNameSub2);
					boolean result2 = delFile.delete();
					System.out.println("기존첨부파일삭제 : "+(result2?"성공":"실패"));
				}
				//기존 파일을 유지할 때
				else {
					itemImageSub2Default = oldOriginalFileNameSub2;
					itemImageSub2Renamed = oldRenamedFileNameSub2;
				}
			}
			
			//서브이미지3
			//기존 첨부파일이 있었는지 여부 확인
			if(!"".equals(oldOriginalFileNameSub3)) {
				//신규첨부파일 유무 검사 : upFile 파일첨부가 없는 경우 null을 리턴
				File f = multiReq.getFile("inputItemImageSub3");
				
				//신규 첨부파일이 있는 경우, 기존 첨부파일 삭제
				if(f!=null) {
					File delFile = new File(saveImgDirectory,oldRenamedFileNameSub3);
					boolean result2 = delFile.delete();
					System.out.println("기존첨부파일삭제 : "+(result2?"성공":"실패"));
				}
				//기존 파일을 유지할 때
				else {
					itemImageSub3Default = oldOriginalFileNameSub3;
					itemImageSub3Renamed = oldRenamedFileNameSub3;
				}
			}
			
			//상세이미지
			//기존 첨부파일이 있었는지 여부 확인
			if(!"".equals(oldOriginalFileNameDetail)) {
				//신규첨부파일 유무 검사 : upFile 파일첨부가 없는 경우 null을 리턴
				File f = multiReq.getFile("inputItemImageDetail");
				
				//신규 첨부파일이 있는 경우, 기존 첨부파일 삭제
				if(f!=null) {
					File delFile = new File(saveImgDirectory,oldRenamedFileNameDetail);
					boolean result2 = delFile.delete();
					System.out.println("기존첨부파일삭제 : "+(result2?"성공":"실패"));
				}
				//기존 파일을 유지할 때
				else {
					itemImageDetailDefault = oldOriginalFileNameDetail;
					itemImageDetailRenamed = oldRenamedFileNameDetail;
				}
			}
			
			//2.business logic
			Item item = new ItemService().selectOne(itemNo);
			item.setCategoryNo(categoryNo);
			item.setItemStock(itemStock);
			item.setItemBrand(itemBrand);
			item.setItemName(itemName);
			item.setItemPrice(itemPrice);
			item.setItemDesc(itemDesc);
			
			//item테이블에 상품 정보 등록
			result = new ItemService().updateItem(item);
			//상품 번호 조회
			System.out.println("itemNo@servlet="+itemNo);
			
			
			//이미지테이블에 상품번호를 포함하여 데이터 저장
			//대표이미지
			if(itemImageMainDefault != null) {
				System.out.println("==============================");
				ItemImage itemImgMain = new ItemService().selectImageOne(itemMainImageNo);
				itemImgMain.setItemImageNo(itemMainImageNo);
				itemImgMain.setItemNo(itemNo);
				itemImgMain.setItemImageTypeNo("IMG01");
				itemImgMain.setItemImageDefault(itemImageMainDefault);
				itemImgMain.setItemImageRenamed(itemImageMainRenamed);
				result = new ItemService().updateImage(itemImgMain);
				System.out.println("result@img"+result);
				
			}
			
			//서브이미지1
			if(itemImageSub1Default != null) {
				System.out.println("==============================");
				ItemImage itemImgSub1 = new ItemService().selectImageOne(itemSub1ImageNo);
				itemImgSub1.setItemImageNo(itemSub1ImageNo);
				itemImgSub1.setItemNo(itemNo);
				itemImgSub1.setItemImageTypeNo("IMG02");
				itemImgSub1.setItemImageDefault(itemImageSub1Default);
				itemImgSub1.setItemImageRenamed(itemImageSub1Renamed);
				result = new ItemService().updateImage(itemImgSub1);
				System.out.println("result@img"+result);
			}
			
			//서브이미지2
			if(itemImageSub2Default != null) {
				System.out.println("==============================");
				ItemImage itemImgSub2 = new ItemService().selectImageOne(itemSub2ImageNo);
				itemImgSub2.setItemImageNo(itemSub2ImageNo);
				itemImgSub2.setItemNo(itemNo);
				itemImgSub2.setItemImageTypeNo("IMG03");
				itemImgSub2.setItemImageDefault(itemImageSub2Default);
				itemImgSub2.setItemImageRenamed(itemImageSub2Renamed);
				result = new ItemService().updateImage(itemImgSub2);
				System.out.println("result@img"+result);
			}
			
			//서브이미지3
			if(itemImageSub3Default != null) {
				System.out.println("==============================");
				ItemImage itemImgSub3 = new ItemService().selectImageOne(itemSub3ImageNo);
				itemImgSub3.setItemImageNo(itemSub3ImageNo);
				itemImgSub3.setItemNo(itemNo);
				itemImgSub3.setItemImageTypeNo("IMG04");
				itemImgSub3.setItemImageDefault(itemImageSub3Default);
				itemImgSub3.setItemImageRenamed(itemImageSub3Renamed);
				result = new ItemService().updateImage(itemImgSub3);
				System.out.println("result@img"+result);
			}
			
			//상세이미지
			if(itemImageDetailDefault != null) {
				System.out.println("==============================");
				ItemImage itemImgDetail = new ItemService().selectImageOne(itemDetailImageNo);
				itemImgDetail.setItemImageNo(itemDetailImageNo);
				itemImgDetail.setItemNo(itemNo);
				itemImgDetail.setItemImageTypeNo("IMG05");
				itemImgDetail.setItemImageDefault(itemImageDetailDefault);
				itemImgDetail.setItemImageRenamed(itemImageDetailRenamed);
				result = new ItemService().updateImage(itemImgDetail);
				System.out.println("result@img"+result);
			}
			
			
			//3.뷰단 처리
			String msg = "";
			String loc = "/admin/itemSearch";
			
			
			if(result > 0) {
				msg = "상품 수정 성공했습니다.";
			}
			else {
				msg = "상품 수정 실패했습니다.";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		} catch(Exception e) {
			System.out.println("itemEnrollEndServlet 예외처리함");
			throw e;//컨테이너한테 예외를 던져야 에러페이지 전환이 가능하다.
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
