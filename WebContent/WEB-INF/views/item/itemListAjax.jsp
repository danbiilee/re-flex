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
<%
	List<Item> itemList = (List<Item>)request.getAttribute("itemList");
	List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap");
%>

<div class="row item-list">
<c:if test="${!empty itemList}">
<%
	for(int i=0; i<itemList.size(); i++){
		Item item = itemList.get(i);
		List<ItemImage> imgList = imgMap.get(itemNoList.get(i));
		
		//대표 가격(14일기준)
		int discountedPrice = (int)Math.ceil((item.getItemPrice()*0.95)/14)/100*100;
		
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
	<c:if test="${fn:length(itemList)%4 != 0}">
		<c:set var="plus" value="${4-fn:length(itemList)%4}" />
		<c:forEach var="i" begin="1" end="${plus}" step="1">
			<div class="col-md-3"></div>
		</c:forEach>
	</c:if>
</div>
<%-- 페이징바 --%>
<nav class="paging-bar text-center">
    <ol class="list-unstyled list-inline">
    	${pageBar}
    </ol>
</nav>
</c:if>
