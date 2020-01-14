<%@page import="item.model.vo.ItemImage"%>
<%@page import="java.util.Map"%>
<%@page import="item.model.vo.Item"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="filterType" value='${filterType==null?"":filterType}' />
<%
	List<Item> itemList = (List<Item>)request.getAttribute("itemList");
	List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
document.addEventListener('DOMContentLoaded', function(){
	listFilter(); //목록 정렬
	setFilter(); //정렬값에 따라 option값 selected유지
	console.log("${filterType}");
});
function listFilter(){
	let selectFilter = document.querySelector("#filterType");
	
	//정렬
	selectFilter.addEventListener('change', function(){
		let optionVal = selectFilter.options[selectFilter.selectedIndex].value;
		
		//신상품순
		if(optionVal==="upToDate")
			location.href = "${pageContext.request.contextPath}/item/itemList?categoryNo=${categoryNo}";
		//낮은 가격순
		else if(optionVal==="lowPrice")
			location.href = "${pageContext.request.contextPath}/item/itemListByLowPrice?categoryNo=${categoryNo}&filterType=lowPrice";
		//높은 가격순
		else if(optionVal==="highPrice")
			location.href = "${pageContext.request.contextPath}/item/itemListByHighPrice?categoryNo=${categoryNo}&filterType=highPrice";
	});
}
function setFilter(){
	let options = document.querySelectorAll("#filterType option");
	
	options.forEach(function(obj, idx){
		let val = obj.value;
		if(val==="${filterType}"){
			obj.selected = true;
		}
	});
}
</script>
<!-- page nav -->
<nav class="line-style page-nav">
    <ul class="list-unstyled list-inline">
        <li class="go-home">
            <a href='<c:url value="/index.jsp"/>'>메인</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li class="go-boxmenu">
            <a href="<c:url value='/common/boxMenu?level1=when'/>">이럴 때 빌려봐</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <c:if test='${"CT01"==categoryNo}'>
        	<li>반려동물과 함께 할 때</li>
        </c:if>
        <c:if test='${"CT02"==categoryNo}'>
        	<li>육아할 때</li>
        </c:if>
        <c:if test='${"CT03"==categoryNo}'>
        	<li>파티할 때</li>
        </c:if>
        <c:if test='${"CT04"==categoryNo}'>
        	<li>운동할 때</li>
        </c:if>
        <c:if test='${"CT05"==categoryNo}'>
        	<li>여행갈 때</li>
        </c:if>
        <c:if test='${"CT06"==categoryNo}'>
        	<li>캠핑갈 때</li>
        </c:if>
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
	<!-- 조회된 상품이 있는 경우 -->
	<c:if test="${!empty itemList}">
<% 
	//상품번호에 맞는 상품이미지리스트 가져오기
	for(int i=0; i<itemList.size(); i++){
		Item item = itemList.get(i);
		List<ItemImage> imgList = imgMap.get(itemNoList.get(i));
		
		//대표 가격(14일기준)
		int discountedPrice = (int)Math.ceil((item.getItemPrice()*0.95)/240*14)/100*100;
		
		pageContext.setAttribute("item", item);
		pageContext.setAttribute("imgList", imgList);
		pageContext.setAttribute("discountedPrice", discountedPrice);
%>
 		 <div class="col-md-3">
		    <a href='<c:url value="/item/itemView?categoryNo=${categoryNo}&itemNo=${item.itemNo}"/>' class="center-block">
		        <img src="<c:url value='/images/${categoryNo}/${imgList[0].itemImageRenamed}'/>" alt="item" class="center-block">
		        <div class="ptext-wrapper">
		            <p class="pbrand">${item.itemBrand}</p>
		            <p class="pname">${item.itemName}</p>
		            <div class="price-wrapper">
		                <p class="price"><fmt:formatNumber value="${discountedPrice}" groupingUsed="true"/> /<span class="rent-period"> 14일</span></p>
		                <p class="rent-type">일시납</p>
		            </div>
		        </div>
		    </a>
		</div> 
<%			
	}
%>
		<!-- 상품개수가 4의 배수가 아니면 부족한 만큼 빈 박스로 채움 -->
		 <c:if test="${fn:length(itemList)%4 != 0}">
			<c:set var="plus" value="${4-fn:length(itemList)%4}" />
			<c:forEach var="i" begin="1" end="${plus}" step="1">
				<div class="col-md-3"></div>
			</c:forEach>
		</c:if> 
	</div>
	<!-- 페이징바 -->
	<nav class="paging-bar text-center">
	    <ol class="list-unstyled list-inline">
	    	${pageBar}
	    </ol>
	</nav>
</div>
	    
<!-- 맨위로 이동 버튼 -->
<div id="go-to-top" class="btn-bottom">
    <button type="button" id="btn-gotop" class="center-block" onclick="window.scrollTo(0,0);">맨 위로 이동</button>
</div>

</c:if>


<%-- 조회된 상품이 없는 경우 --%>
<c:if test="${empty itemList}">
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
</c:if>

<c:import url="../common/footer.jsp"></c:import>