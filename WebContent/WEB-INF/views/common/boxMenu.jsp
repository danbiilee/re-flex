<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="container-fluid contents none-nav">
    <ul id="boxmenu" class="row list-unstyled text-center">
        <li class="col-md-4">
            <a href='<c:url value="/index.jsp"/>'>
                <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                 	메인
            </a>
        </li>
    <c:if test='${"when" eq level1}'>
	    <li class="col-md-4"><a href="<c:url value='/item/itemList?categoryNo=CT01'/>">반려동물과 함께 할 때</a></li>
	    <li class="col-md-4 three-times"><a href="<c:url value='/item/itemList?categoryNo=CT02'/>">육아할 때</a></li>
        <li class="col-md-4"><a href="<c:url value='/item/itemList?categoryNo=CT03'/>">파티할 때</a></li>
        <li class="col-md-4"><a href="<c:url value='/item/itemList?categoryNo=CT04'/>">운동할 때</a></li>
        <li class="col-md-4 three-times"><a href="<c:url value='/item/itemList?categoryNo=CT05'/>">여행갈 때</a></li>
	    <li class="col-md-4"><a href="<c:url value='/item/itemList?categoryNo=CT06'/>">캠핑갈 때</a></li>
	    <li class="col-md-4"></li>
	    <li class="col-md-4 three-times"></li>
	</c:if>
	<c:if test='${sessionScope.memberLoggedIn != null && "mypage" eq level1}'>
    	<li class="col-md-4"><a href="<c:url value='/mypage/mypageOrderList?memberId=${memberLoggedIn.memberId}'/>">주문조회</a></li>
        <li class="col-md-4 three-times"><a href="<c:url value='/mypage/mypageWishlist?memberId=${memberLoggedIn.memberId}'/>">위시리스트</a></li> 
        <li class="col-md-4"><a href="<c:url value='/mypage/mypageRentalIng?memberId=${memberLoggedIn.memberId}'/>">계약중인 렌탈</a></li>
        <li class="col-md-4"><a href="<c:url value='/mypage/mypageRentalFin?memberId=${memberLoggedIn.memberId}'/>">종료중인 렌탈</a></li>
        <li class="col-md-4 three-times"><a href="<c:url value='/mypage/mypagePoint?memberId=${memberLoggedIn.memberId}'/>">포인트 확인</a></li>
        <li class="col-md-4"><a href="<c:url value='/mypage/mypageReview?memberId=${memberLoggedIn.memberId}'/>">이용후기 내역</a></li>
        <li class="col-md-4"><a href="<c:url value='/mypage/mypageOneToOne?memberId=${memberLoggedIn.memberId}'/>">1:1문의 내역</a></li>
        <li class="col-md-4 three-times"><a href="<c:url value='/member/memberUpdate?memberId=${memberLoggedIn.memberId}'/>">회원정보 수정</a></li> 
        <li class="col-md-4"><a href="<c:url value='/member/memberDelete?memberId=${memberLoggedIn.memberId}'/>">회원정보 탈퇴</a></li>
        <li class="col-md-4"></li>
	    <li class="col-md-4 three-times"></li>
	</c:if>
    </ul>
</div>

<c:import url="footer.jsp"></c:import>