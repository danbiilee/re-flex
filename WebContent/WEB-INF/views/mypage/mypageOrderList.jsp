<%@page import="java.util.Arrays"%>
<%@page import="java.sql.Date"%>
<%@page import="item.model.vo.Item"%>
<%@page import="item.model.vo.ItemImage"%>
<%@page import="java.util.Map"%>
<%@page import="order.model.vo.OrderDetail"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//주문번호
	List<String> orderNoList = (List<String>)request.getAttribute("orderNoList");
	//주문번호에 담긴 상품내역들
	Map<String, List<OrderDetail>> orderListByOrderNo = (Map<String, List<OrderDetail>>)request.getAttribute("orderListByOrderNo");
	//상품내역들의 대여 시작일, 반납일
	Map<String, List<Date[]>> rentDateMap = (Map<String, List<Date[]>>)request.getAttribute("rentDateMap");
	
	//상품 사진
	List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap");
	
	int totalPage = (int)request.getAttribute("totalPage");
	
	//js tr태그 추가용: 주문번호당 몇 개 상품 들어있는지
	int[] eaArr = new int[orderNoList.size()];
	
	//주문현황 상태 카운트용
	int os02 = 0; //배송준비중
	int os03 = 0; //배송중
	int os04 = 0; //배송완료
	int ocNum = 0; //취소반품교환
	if(orderListByOrderNo!=null && !orderListByOrderNo.isEmpty()){
	    for(int i=0; i<orderNoList.size(); i++){
	    	String orderNo = orderNoList.get(i); //주문번호
	    	List<OrderDetail> odList = orderListByOrderNo.get(orderNo); //한 건의 주문안에 담겨있는 주문상품내역리스트
	    	System.out.println("odList"+odList);
	    	eaArr[i] = odList.get(0).getOrderTotalItemEa(); //한 건의 주문에 담겨있는 상품 수 
	    	
	    	for(int j=0; j<odList.size(); j++){
	    		OrderDetail od = odList.get(j);
	    		String osNo = od.getOrderStatusNo(); //주문상태번호
	    		String ccNo = od.getOrderCancelNo(); //주문취소번호
	    		if("OS02".equals(osNo)) os02++;
	    		else if("OS03".equals(osNo)) os03++;
	    		else if("OS04".equals(osNo)) os04++;
				
	    		if(ccNo!=null) ocNum++;
	    	}
	    }
	}
	
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
document.addEventListener('DOMContentLoaded', function(){
	plusTdTag(); //주문번호td태그 추가 
	
});
function plusTdTag(){
	let trArr = document.querySelectorAll(".orderRow");
	let plus = 1; //비교 클래스값
	let cnt = 0; //같은 주문번호의 tr이 몇 행있는지
	
	trArr.forEach(function(obj, idx){
		let strArr = obj.className.split(" ");
		if(strArr[1]===cnt){
			let sameTrArr = new Array();
			sameTrArr.push(this);
		}
	});
}
function goReview(btn, yn, odNo, itemNo){
	let val = btn.value;
	
	//구매후기 버튼 클릭: 주문상태 OS04일 경우만
	if(yn!=="N" || val==="OS05") alert("이미 구매후기를 작성한 상품입니다.");
	else if(val==="OS04" && yn==="N"){
		if(!confirm("구매후기를 작성하시겠습니까? ")) return;
		
		location.href = "<%=request.getContextPath()%>/mypage/mypageReviewForm?itemNo="+itemNo+"&orderDetailNo="+odNo;
	}
	else if(val==="OS01" || val==="OS02" || val==="OS03"){
		alert("구매후기는 배송완료일 때만 작성가능합니다.");
	}
}
function cancelOrder(btn, odNo, itemNo, optNo, memId){
	let val = btn.value;
	
	//주문완료, 배송준비중일 때만 취소가능
	if(val==="OS01" || val==="OS02"){
		if(!confirm("주문을 취소하시겠습니까?")) return;
			
		$.ajax({
			url: "<%=request.getContextPath()%>/order/orderCancel",
			type: "post",
			data: {
				odNo: odNo,
				itemNo: itemNo,
				optNo: optNo,
				memberId: memId
			},
			dataType: "json",
			success: data => {
				console.log(data);
				if(data.result===1)
					alert("주문이 정상적으로 취소되었습니다.");
				else
					alert("주문이 취소되지 않았습니다.\n다시 시도해 주세요.");
				
				window.location.reload();
			},
			error: (jqxhr, textStatus, errorThrown)=>{
				console.log(jqxhr, textStatus, errorThrown);
			} 
		});
	}
	else{
		alert("주문취소는 배송시작 전까지만 가능합니다.");
	}
}
function exchangeItem(btn, odNo, itemNo, optNo, memId){
	let val = btn.value;
	
	//배송완료, 구매확정일 때만 교환가능
	if(val==="OS04" || val==="OS05"){
		if(!confirm("상품을 교환하시겠습니까?")) return;
			
		$.ajax({
			url: "<%=request.getContextPath()%>/order/exchangeItem",
			type: "post",
			data: {
				odNo: odNo,
				itemNo: itemNo,
				optNo: optNo,
				memberId: memId
			},
			dataType: "json",
			success: data => {
				console.log(data);
				if(data.result===1)
					alert("상품교환 신청이 정상적으로 접수되었습니다.\n반납일이 10일 추가되었으니 확인해 주세요.");
				else
					alert("상품교환 신청이 접수되지 않았습니다.\n다시 시도해 주세요.");
				
				window.location.reload();
			},
			error: (jqxhr, textStatus, errorThrown)=>{
				console.log(jqxhr, textStatus, errorThrown);
			} 
		});
	}
	else{
		alert("상품교환은 배송완료 후부터 가능합니다.");
	}

}
function cancelRent(btn, odNo, itemNo, optNo, memId){
	let val = btn.value;
	
	//배송완료, 구매확정일 때만 렌탈해지가능
	if(val==="OS04" || val==="OS05"){
		if(!confirm("렌탈을 해지하시겠습니까?")) return;
			
		$.ajax({
			url: "<%=request.getContextPath()%>/order/cancelRent",
			type: "post",
			data: {
				odNo: odNo,
				itemNo: itemNo,
				optNo: optNo,
				memberId: memId
			},
			dataType: "json",
			success: data => {
				console.log(data);
				if(data.result===1)
					alert("렌탈해지 신청이 정상적으로 접수되었습니다.\n반납일이 오늘로부터 5일 뒤 종료됩니다.");
				else 
					alert("렌탈해지 신청이 접수되지 않았습니다.\n다시 시도해 주세요.");
				
				window.location.reload();
			},
			error: (jqxhr, textStatus, errorThrown)=>{
				console.log(jqxhr, textStatus, errorThrown);
			} 
		});
	}
	else{
		alert("렌탈해지는 배송완료 후부터 가능합니다.");
	}
}
function checkByPeriod(btn, val){
	let btnLiArr = document.querySelectorAll(".btn-li");
	let parentLi = btn.parentNode;

	//.sel을 갖고 있으면 삭제 
	btnLiArr.forEach(function(obj, idx){
		if(obj.className.indexOf('sel')){
			obj.classList.remove('sel');
		};
	});
	//현재 클릭한 버튼에만 .sel 추가
	parentLi.classList.toggle('sel');
	
	//1개월 검색
	if(val===1){
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/mypageOrderListByOneM?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
				console.log(data)

				$("#order-list").html(data);
			},
			error: function(jqxhr,textStatus,errorThrown){
				console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
			}
		});
	}
	//3개월 검색
	else if(val===3){
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/mypageOrderListByThreeM?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
				console.log(data)

				$("#order-list").html(data);
			},
			error: function(jqxhr,textStatus,errorThrown){
				console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
			}
		});
	}
	//6개월 검색
	else if(val===6){
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/mypageOrderListBySixM?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
				console.log(data)

				$("#order-list").html(data);
			},
			error: function(jqxhr,textStatus,errorThrown){
				console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
			}
		});
	}
	//전체 검색
	else{
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/mypageOrderListByAll?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
				console.log(data)

				$("#order-list").html(data);
			},
			error: function(jqxhr,textStatus,errorThrown){
				console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
			}
		});
	}

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
            <a href="<%=request.getContextPath()%>/common/boxMenu?level1=mypage">마이페이지</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li>주문현황</li>
    </ul>
</nav>

<!-- 주문현황 상태 -->
<div class="container-fluid contents">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <h2 class="sr-only">주문현황</h2>
            <!-- 주문상태 보기 -->
            <section id="parcel" class="my-header">
                <h3 class="sr-only">주문상태 보기</h3>
                <ul class="row list-unstyled list-inline">
                    <li class="col-md-3 text-center">배송준비중 <span class="em-blue"><%=os02 %></span></li>
                    <li class="col-md-3 text-center">배송중 <span class="em-blue"><%=os03 %></span></li>
                    <li class="col-md-3 text-center">배송완료 <span class="em-blue"><%=os04 %></span></li>
                    <li class="col-md-3 text-center">취소/반품/교환 <span class="em-blue"><%=ocNum %></span></li>
                </ul>
            </section>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<!-- 기간검색 -->
<div class="container-fluid line-style text-center">
    <p class="strong">기간검색</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <section class="my-header">
                <h3 class="sr-only">주문현황 기간검색하기</h3>
                <ul class="row list-inline list-unstyled">
                    <li class="col-md-3 text-center btn-li"><button type="button" onclick="checkByPeriod(this, 1);">1개월</button></li>
                    <li class="col-md-3 text-center btn-li sel"><button type="button" onclick="checkByPeriod(this, 3);">3개월</button></li>
                    <li class="col-md-3 text-center btn-li"><button type="button" onclick="checkByPeriod(this, 6);">6개월</button></li>
                    <li class="col-md-3 text-center btn-li"><button type="button" onclick="checkByPeriod(this, all);">전체</button></li>
                </ul>
            </section>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<!-- 주문현황 리스트 -->
<div class="container-fluid line-style text-center">
    <p class="strong">주문 상품 정보</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <section id="order-list" class="list-wrapper">
                <h3 class="sr-only">주문현황 리스트</h3>
                <table id="odl-tbl" class="text-center list-tbl">
                	<colgroup>
	                    <col width="16%">
	                    <col width="35%">
	                    <col width="7%">
	                    <col width="16%">
	                    <col width="13%">
	                    <col width="13%">
	                </colgroup>
                    <thead>
                        <tr>
                            <th class="text-center">주문번호[날짜]</th>
                            <th class="text-center">상품정보</th>
                            <th class="text-center">수량</th>
                            <th class="text-center">렌탈기간</th>
                            <th class="text-center">상태</th>
                            <th class="text-center">변경</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    	if(orderListByOrderNo!=null && !orderListByOrderNo.isEmpty()){
                    		for(int i=0; i<orderNoList.size(); i++){
                    			String orderNo = orderNoList.get(i); //주문번호
                    			
                    			//한 건의 주문안에 담겨있는 주문상품내역리스트
                    			List<OrderDetail> odList = orderListByOrderNo.get(orderNo);
                    			//대여기간 리스트
                    			List<Date[]> dateList = rentDateMap.get(orderNo);

                    			for(int j=0; j<odList.size(); j++){
                    				OrderDetail od = odList.get(j); //하나의 주문상품내역
                    				Item item = od.getItem(); //그 안에 포함된 하나의 상품
                    				List<ItemImage> imgList = imgMap.get(item.getItemNo()); //그 상품의 이미지리스트
                    				
                    				//렌탈기간
        							int rentPeriod = 0;
        							if("RT01".equals(od.getRentOptNo())) rentPeriod = 7;
        							else if("RT02".equals(od.getRentOptNo())) rentPeriod = 14;
        							else rentPeriod = 30;
                    				
                    				//가격 ,찍기
                    				DecimalFormat dc = new DecimalFormat("###,###,###,###원");
                    				String dpEa = dc.format(od.getPriceByRentOptNo()*od.getItemQuantity());
                    				
                    				//대여기간
                    				Date[] dArr = dateList.get(j);
                    				
                    				//주문상태
                    				String orderStatus = "주문완료";
                    				if("OS02".equals(od.getOrderStatusNo())) orderStatus = "배송준비중";
                    				else if("OS03".equals(od.getOrderStatusNo())) orderStatus = "배송중";
                    				else if("OS04".equals(od.getOrderStatusNo())) orderStatus = "배송완료";
                    				else if("OS05".equals(od.getOrderStatusNo())) orderStatus = "구매확정";
                    %>
                        <tr class="orderRow <%=i%> <%=od.getOrderTotalItemEa()%> <%=orderNo%> <%=od.getOrderDate()%>">
                        	<input type="hidden" name="odListSize" value="<%=odList.size()%>" />
                            <td rowspan="">
                                <p><%=orderNo%></p>
                                <p>[<%=od.getOrderDate()%>]</p>
                            </td>
                            <td class="item-info">
                                <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=item.getCategoryNo()%>&itemNo=<%=item.getItemNo()%>"><img src="<%=request.getContextPath()%>/images/<%=item.getCategoryNo()%>/<%=imgList.get(0).getItemImageRenamed()%>" class="pull-left" alt="상품이미지"></a>
                                <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=item.getCategoryNo()%>&itemNo=<%=item.getItemNo()%>">
                                	<p class="text-left pbrand"><%=item.getItemBrand()%></p>
                                	<p class="text-left pname"><%=item.getItemName()%></p>
                                </a>
                                <p class="text-left price"><%=dpEa%> /<span class="rent-period"> <%=rentPeriod %>일</p>
                                <p class="pull-left rent-type">일시납</p>
                            </td>
                            <td><%=od.getItemQuantity()%></td>
                            <% if(dArr[0]==null || dArr[1]==null){ %>
                            	<td>-</td>
                            <% 
                            	}
                            	else{
                            %>
                           		<td class=""><%=dArr[0]%>~<%=dArr[1]%></td>
                            <% } %>
                            <td class="order-status <%=od.getOrderDetailNo()%>">
                                <p class="ship-status"><%=orderStatus%></p>
                                <button type="button" id="btn-goReview" class="btn-radius" value="<%=od.getOrderStatusNo()%>" onclick="goReview(this,'<%=od.getReviewYn()%>','<%=od.getOrderDetailNo()%>','<%=item.getItemNo()%>');">구매후기</button>
                            </td>
                            <td class="item-change <%=od.getOrderDetailNo()%>">
                            	<% if(od.getOrderCancelNo()==null){ %>
                                <ul class="list-unstyled text-center">
                                    <li><button type="button" id="btn-cancelOrder" class="btn-radius" value="<%=od.getOrderStatusNo()%>" onclick="cancelOrder(this,'<%=od.getOrderDetailNo()%>','<%=item.getItemNo()%>','<%=od.getRentOptNo()%>','<%=od.getMemberId()%>');">주문취소</button></li>
                                    <li><button type="button" id="btn-exchangeItem" class="btn-radius" value="<%=od.getOrderStatusNo()%>" onclick="exchangeItem(this,'<%=od.getOrderDetailNo()%>','<%=item.getItemNo()%>','<%=od.getRentOptNo()%>','<%=od.getMemberId()%>');">상품교환</button></li>
                                    <li><button type="button" id="btn-cancelRent" class="btn-radius" value="<%=od.getOrderStatusNo()%>" onclick="cancelRent(this,'<%=od.getOrderDetailNo()%>','<%=item.getItemNo()%>','<%=od.getRentOptNo()%>','<%=od.getMemberId()%>');">렌탈해지</button></li>
                                </ul>
                                <% } 
                            		else{ 
                                		String cancelNo = ("OC01".equals(od.getOrderCancelNo()))?"주문취소":("OC02".equals(od.getOrderCancelNo()))?"상품교환":"렌탈해지";
                                %>
                                	<%=cancelNo %>
                                <%} %>
                            </td>
                        </tr>
                    <%
                    			}
                    		}
                    	}
                    %>    
                    </tbody>
                </table>
            </section>
            <!-- 페이징바 -->
            <%-- <nav class="paging-bar text-center">
                <ol class="list-unstyled list-inline">
                <%=pageBar %>
                </ol>
            </nav> --%>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<!-- 더보기 버튼 -->
<div id="go-to-top" class="btn-bottom">
    <button type="button" id="btn-more" class="center-block" onclick="">더 로드하기</button>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>