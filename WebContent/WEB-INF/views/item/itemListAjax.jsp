<%@page import="item.model.vo.ItemImage"%>
<%@page import="java.util.Map"%>
<%@page import="item.model.vo.Item"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String categoryNo = (String)request.getAttribute("categoryNo");
	List<Item> itemList = (List<Item>)request.getAttribute("itemList");
	List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<div class="row item-list">
<%
if(!itemList.isEmpty()) {
	for(int i=0; i<itemList.size(); i++){
		Item item = itemList.get(i);
		List<ItemImage> imgList = imgMap.get(itemNoList.get(i));
		
		//원화 콤마찍기
		int discountedPrice = (int)Math.ceil((item.getItemPrice()*0.95)/14)/100*100; //14일기준
		DecimalFormat dc = new DecimalFormat("###,###,###,###원");
		String dP = dc.format(discountedPrice);
%>
	<div class="col-md-3">
	    <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=categoryNo%>&itemNo=<%=item.getItemNo()%>" class="center-block">
	        <img src="<%=request.getContextPath()%>/images/<%=categoryNo%>/<%=imgList.get(0).getItemImageDefault()%>" alt="item" class="center-block">
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
<%
}
%>