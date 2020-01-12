<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	String orderNo = (String)request.getAttribute("orderNo");
%>
<script>
document.addEventListener('DOMContentLoaded', function(){
	let btnGoOrderStatus = document.querySelector("#btn-goOrderStatus"); //주문조회하기 버튼
	
	btnGoOrderStatus.addEventListener("click", function(){
		location.href = "<%=request.getContextPath()%>/mypage/mypageOrderList?memberId=<%=memberLoggedIn.getMemberId()%>";
	});
});
</script>

<!-- 주문성공 헤더 -->
<div id="order-header" class="container-fluid line-style text-center contents none-nav">
    <h2 class="sr-only">주문성공</h2>
    <p>주문 성공</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div id="orderAfter-wrapper" class="col-md-10 content-wrapper">
            <span class="glyphicon glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
            <div class="success-wrapper">
                <p id="success-title" class="strong">주문이 정상적으로 완료되었습니다.</p>
                <p id="success-orderNo">주문번호 <%=orderNo %></p>
                <p>주문내역 확인은 마이페이지의 "주문조회"에서 하실 수 있습니다.</p>
            </div>
            <button type="button" id="btn-goOrderStatus" class="btn-radius center-block">주문조회하기</button>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>