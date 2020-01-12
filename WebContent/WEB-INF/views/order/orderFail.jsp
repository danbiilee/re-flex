<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- 주문실패 헤더 -->
<div id="order-header" class="container-fluid line-style text-center contents none-nav">
    <h2 class="sr-only">주문실패</h2>
    <p>주문 실패</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div id="orderAfter-wrapper" class="col-md-10 content-wrapper">
            <span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
            <div class="success-wrapper">
                <p id="success-title" class="strong">주문처리에 실패했습니다.</p>
                <p>죄송합니다. 알 수 없는 오류발생으로 결제가 불가능합니다.</p> 
                <p>잠시 후 다시 이용해 주시기 바랍니다.</p>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>