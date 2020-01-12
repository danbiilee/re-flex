<%@page import="item.model.vo.ItemImage"%>
<%@page import="java.util.Map"%>
<%@page import="item.model.vo.Item"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	String categoryNo = (String)request.getAttribute("categoryNo");
	String filterType = (String)request.getAttribute("filterType");
	filterType = (filterType==null)?"null":filterType;
	List<Item> itemList = (List<Item>)request.getAttribute("itemList");
	List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap");
	String pageBar = (String)request.getAttribute("pageBar");
%>
<script>
document.addEventListener('DOMContentLoaded', function(){
	listFilter(); //목록 정렬
	setFilter(); //정렬값에 따라 option값 selected유지
});
function listFilter(){
	let selectFilter = document.querySelector("#filterType");
	
	//정렬
	selectFilter.addEventListener('change', function(){
		let optionVal = selectFilter.options[selectFilter.selectedIndex].value;
		
		//신상품순
		if(optionVal==="upToDate")
			location.href = "<%=request.getContextPath()%>/item/itemList?categoryNo=<%=categoryNo%>";
		//낮은 가격순
		else if(optionVal==="lowPrice")
			location.href = "<%=request.getContextPath()%>/item/itemListByLowPrice?categoryNo=<%=categoryNo%>&filterType=lowPrice";
		//높은 가격순
		else if(optionVal==="highPrice")
			location.href = "<%=request.getContextPath()%>/item/itemListByHighPrice?categoryNo=<%=categoryNo%>&filterType=highPrice";
	});
}
function setFilter(){
	let options = document.querySelectorAll("#filterType option");
	
	options.forEach(function(obj, idx){
		let val = obj.value;
		if(val==="<%=filterType%>"){
			obj.selected = true;
		}
	});
	
}
</script>
<!-- page nav -->
<nav class="line-style page-nav">
    <ul class="list-unstyled list-inline">
        <li class="go-home">
            <a href="<%=request.getContextPath()%>/index.jsp">메인</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li class="go-boxmenu">
            <a href="<%=request.getContextPath()%>/common/boxMenu?level1=when">이럴 때 빌려봐</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <% 
        	if("CT01".equals(categoryNo)){
        %>
        	<li>반려동물과 함께 할 때</li>
        <%
        	}
        	if("CT02".equals(categoryNo)){
        %>
        	<li>육아할 때</li>
        <%
        	}
        	if("CT03".equals(categoryNo)){
        %>
        	<li>파티할 때</li>
        <%
        	}
        	if("CT04".equals(categoryNo)){
        %>
        	<li>운동할 때</li>
        <%
        	}
        	if("CT05".equals(categoryNo)){
        %>
        	<li>여행갈 때</li>
        <%
        	}
        	if("CT06".equals(categoryNo)){
        %>
        	<li>캠핑갈 때</li>
        <%
        	}
        %>
        <li class="pull-right">
            <select name="filterType" id="filterType">
                <option value="upToDate">신상품순</option>
                <option value="lowPrice">낮은 가격순</option>
                <option value="highPrice">높은 가격순</option>
            </select>
        </li>
    </ul>
</nav>

<div id="view-list" class="container-fluid contents">
	<!-- 상품목록 -->
	<div class="row item-list">
<% 
	//조회된 상품이 있는 경우
	if(!itemList.isEmpty()) {
		for(int i=0; i<itemList.size(); i++){
			/* 상품번호에 맞는 상품이미지리스트 가져오기 */
			/* 상품번호는 itemNoList로 제어 */
			/* 상품목록에는 IMG01만 보이면 되니까 imgList.get()의 인덱스는 무조건 0임 */
			Item item = itemList.get(i);
			List<ItemImage> imgList = imgMap.get(itemNoList.get(i));
			
			//가격 콤마찍기
			int discountedPrice = (int)Math.ceil((item.getItemPrice()*0.95)/240*14)/100*100; //14일기준
			DecimalFormat dc = new DecimalFormat("###,###,###,###원");
			String dP = dc.format(discountedPrice);
%>
		<div class="col-md-3">
		    <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=categoryNo %>&itemNo=<%=item.getItemNo()%>" class="center-block">
		        <img src="<%=request.getContextPath()%>/images/<%=categoryNo%>/<%=imgList.get(0).getItemImageRenamed()%>" alt="item" class="center-block">
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
		}
		//상품개수가 4의 배수가 아니면 부족한 만큼 빈 박스로 채움
		if(itemList.size()%4!=0){
			int plus = 4 - (itemList.size()%4);
			for(int i=0; i<plus; i++){
	%>
				<div class="col-md-3"></div>
	<% 
			}
		}
	%>
	</div>
	<!-- 페이징바 -->
	<nav class="paging-bar text-center">
	    <ol class="list-unstyled list-inline">
	    	<%=pageBar %>
	    </ol>
	</nav>
</div>
	    
<!-- 맨위로 이동 버튼 -->
<div id="go-to-top" class="btn-bottom">
    <button type="button" id="btn-gotop" class="center-block" onclick="window.scrollTo(0,0);">맨 위로 이동</button>
</div>

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

<%@ include file="/WEB-INF/views/common/footer.jsp"%>