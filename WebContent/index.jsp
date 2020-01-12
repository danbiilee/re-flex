<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="item.model.vo.ItemImage"%>
<%@page import="item.model.vo.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
List<List<Item>> itemListList = (List<List<Item>>)request.getAttribute("itemListList");
List<ItemImage> imgListList = (List<ItemImage>)request.getAttribute("imgListList");
String[] hobbyArr = (String[])request.getAttribute("hobbyArr");
//System.out.println(hobbyArr[0]+"/"+hobbyArr[1]);
String[] hobbyNoArr = {"",""};
if(hobbyArr!=null){
	for(int i=0; i<hobbyArr.length;i++){
		String str=hobbyArr[i];
		String hobbyNo = "";
		switch(str){
		case "반려동물": hobbyNo = "CT01"; break;
		case "육아": hobbyNo = "CT02"; break;
		case "파티": hobbyNo = "CT03"; break;
		case "운동": hobbyNo = "CT04"; break;
		case "여행": hobbyNo = "CT05"; break;
		case "캠핑": hobbyNo = "CT06"; break;
		}
		hobbyNoArr[i] = hobbyNo;
	}
	
}
int imgNo = 0;

//System.out.println("itemListList@index.jsp="+itemListList);
//System.out.println("imgListList@index.jsp="+imgListList);
%>

    <div class="container-fluid contents none-nav">
        <!-- 배너 -->
        <div id="banner" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ul class="carousel-indicators">
              <li data-target="#banner" data-slide-to="0" class="active"></li>
              <li data-target="#banner" data-slide-to="1"></li>
              <li data-target="#banner" data-slide-to="2"></li>
            </ul>
            <!-- The slideshow -->
            <div class="carousel-inner">
                <div class="item active">
                  <img src="images/banner1.jfif" alt="banner1">
                </div>
                <div class="item">
                    <img src="images/banner2.jfif" alt="banner2">
                </div>
                <div class="item">
                    <img src="images/banner3.jfif" alt="banner3">
                </div>
            </div>
            <!-- Left and right controls -->
            <a class="left carousel-control" href="#banner" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#banner" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <!-- 이용방법 소개 -->
        <div id="introduce" class="line-style text-center">
            <a href="<%=request.getContextPath()%>/faq/faqIndex"><p><span class="eng">re:flex</span>의 렌탈서비스가 궁금하다면?</p></a>
        </div>
        <!-- 추천상품목록1 -->
       <div class="line-style rcmd-cate-header">
            <a href="<%=request.getContextPath()%>/item/itemList?categoryNo=<%=hobbyNoArr[0] %>">
                추천 카테고리 - <%=hobbyArr[0] %>
                <span class="glyphicon glyphicon-plus cate-plus" aria-hidden="true"></span>
                <span class="sr-only">추천 카테고리1로 이동</span>
            </a>
        </div>
        <div class="row item-list">
<% 
    if(itemListList != null && itemListList.size()==2){
	    List<Item> itemList1 = itemListList.get(0);
	    List<Item> itemList2 = itemListList.get(1);
	    System.out.println("@index="+itemList1.toString());
	    System.out.println("@index="+itemList2.toString());
	            	
	
		//조회된 상품이 있는 경우
		if(!itemList1.isEmpty()) {
			for(int i=0; i<itemList1.size(); i++){
				/* 상품번호에 맞는 상품이미지리스트 가져오기 */
				/* 상품번호는 itemNoList로 제어 */
				/* 상품목록에는 IMG01만 보이면 되니까 imgList.get()의 인덱스는 무조건 0임 */
				Item item = itemList1.get(i);
				
				//가격 콤마찍기
				int discountedPrice = (int)Math.ceil((item.getItemPrice()*0.95)/240*14)/100*100; //14일기준
				DecimalFormat dc = new DecimalFormat("###,###,###,###원");
				String dP = dc.format(discountedPrice);
%>
		<div class="col-md-3">
		    <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=item.getCategoryNo() %>&itemNo=<%=item.getItemNo()%>" class="center-block">
		        <img src="<%=request.getContextPath()%>/images/<%=item.getCategoryNo() %>/<%=imgListList.get(imgNo).getItemImageRenamed()%>" alt="item" class="center-block">
		        <div class="ptext-wrapper">
		            <p class="pbrand"><%=item.getItemBrand() %></p>
		            <p class="pname"><%=item.getItemName() %></p>
		            <div class="price-wrapper">
		                <p class="price"><%=dP %>/<span class="rent-period"> 14일</span></p>
		                <p class="rent-type">일시납</p>
		            </div>
		        </div>
		    </a>
		</div>
	<%	
			imgNo++;
		}
		//상품개수가 4의 배수가 아니면 부족한 만큼 빈 박스로 채움
		if(itemList1.size()%4!=0){
			int plus = 4 - (itemList1.size()%4);
			for(int i=0; i<plus; i++){
	%>
				<div class="col-md-3"></div>
	<% 
			}
		}
	%>
	<%
		}
		//조회된 상품이 없는 경우
		else{
	%>
		</div>
	</div>
	<div class="container-fluid">
	    <div class="row">
			<div class="col-md-1"></div>
				<div id="warning-wrapper" class="col-md-10 content-wrapper text-center">
					<p><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>등록된 상품이 없습니다.</p> 
				</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<%
		}
	%>
        </div>
        <!-- 추천상품목록2 -->
        <div class="line-style rcmd-cate-header">
            <a href="<%=request.getContextPath()%>/item/itemList?categoryNo=<%=hobbyNoArr[1] %>">
                추천 카테고리 - <%=hobbyArr[1] %>
                <span class="glyphicon glyphicon-plus cate-plus" aria-hidden="true"></span>
                <span class="sr-only">추천 카테고리1로 이동</span>
            </a>
        </div>
        <div class="row item-list">
            <% 
	//조회된 상품이 있는 경우
	if(!itemList2.isEmpty()) {
		for(int i=0; i<itemList2.size(); i++){
			/* 상품번호에 맞는 상품이미지리스트 가져오기 */
			/* 상품번호는 itemNoList로 제어 */
			/* 상품목록에는 IMG01만 보이면 되니까 imgList.get()의 인덱스는 무조건 0임 */
			Item item = itemList2.get(i);
			
			//가격 콤마찍기
			int discountedPrice = (int)Math.ceil((item.getItemPrice()*0.95)/240*14)/100*100; //14일기준
			DecimalFormat dc = new DecimalFormat("###,###,###,###원");
			String dP = dc.format(discountedPrice);
%>
		<div class="col-md-3">
		    <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=item.getCategoryNo() %>&itemNo=<%=item.getItemNo()%>" class="center-block">
		        <img src="<%=request.getContextPath()%>/images/<%=item.getCategoryNo() %>/<%=imgListList.get(imgNo).getItemImageRenamed()%>" alt="item" class="center-block">
		        <div class="ptext-wrapper">
		            <p class="pbrand"><%=item.getItemBrand() %></p>
		            <p class="pname"><%=item.getItemName() %></p>
		            <div class="price-wrapper">
		                <p class="price"><%=dP %>/<span class="rent-period"> 14일</span></p>
		                <p class="rent-type">일시납</p>
		            </div>
		        </div>
		    </a>
		</div>
	<%			
	imgNo++;
		}
		//상품개수가 4의 배수가 아니면 부족한 만큼 빈 박스로 채움
		if(itemList2.size()%4!=0){
			int plus = 4 - (itemList2.size()%4);
			for(int i=0; i<plus; i++){
	%>
				<div class="col-md-3"></div>
	<% 
			}
		}
	%>
	<%
		}
		//조회된 상품이 없는 경우
		else{
	%>
		</div>
	</div>
	<div class="container-fluid">
	    <div class="row">
			<div class="col-md-1"></div>
				<div id="warning-wrapper" class="col-md-10 content-wrapper text-center">
					<p><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>등록된 상품이 없습니다.</p> 
				</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<%
		}
    }
	%>
        </div>
        <!-- 맨위로 이동 버튼 -->
        <div id="go-to-top" class="btn-bottom">
            <button type="button" id="btn-gotop" class="center-block" onclick="window.scrollTo(0,0);">맨 위로 이동</button>
        </div>
    </div> 

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

