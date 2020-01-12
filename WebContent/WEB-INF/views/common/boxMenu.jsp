<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	String level1 = (String)request.getAttribute("level1");
%>
<div class="container-fluid contents none-nav">
    <ul id="boxmenu" class="row list-unstyled text-center">
        <li class="col-md-4">
            <a href="<%=request.getContextPath()%>/index.jsp">
                <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                 	메인
            </a>
        </li>
        <%
        	if("when".equals(level1)){
        %>
	        <li class="col-md-4"><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT01">반려동물과 함께 할 때</a></li>
	        <li class="col-md-4 three-times"><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT02">육아할 때</a></li>
            <li class="col-md-4"><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT03">파티할 때</a></li>
            <li class="col-md-4"><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT04">운동할 때</a></li>
            <li class="col-md-4 three-times"><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT05">여행갈 때</a></li>
            <li class="col-md-4"><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT06">캠핑갈 때</a></li>
	        <li class="col-md-4"></li>
	        <li class="col-md-4 three-times"></li>
        <% 
        	}
        	if(memberLoggedIn != null && "mypage".equals(level1)){
        %>
        	<li class="col-md-4"><a href="<%=request.getContextPath()%>/mypage/mypageOrderList?memberId=<%=memberLoggedIn.getMemberId()%>">주문조회</a></li>
            <li class="col-md-4 three-times"><a href="<%=request.getContextPath()%>/mypage/mypageWishlist?memberId=<%=memberLoggedIn.getMemberId()%>">위시리스트</a></li>
            <li class="col-md-4"><a href="<%=request.getContextPath()%>/mypage/mypageRentalIng?memberId=<%=memberLoggedIn.getMemberId()%>">계약중인 렌탈</a></li>
            <li class="col-md-4"><a href="<%=request.getContextPath()%>/mypage/mypageRentalFin?memberId=<%=memberLoggedIn.getMemberId()%>">종료중인 렌탈</a></li>
            <li class="col-md-4 three-times"><a href="<%=request.getContextPath()%>/mypage/mypagePoint?memberId=<%=memberLoggedIn.getMemberId()%>">포인트 확인</a></li>
            <li class="col-md-4"><a href="<%=request.getContextPath()%>/mypage/mypageReview?memberId=<%=memberLoggedIn.getMemberId()%>">이용후기 내역</a></li>
            <li class="col-md-4"><a href="<%=request.getContextPath()%>/mypage/mypageOneToOne?memberId=<%=memberLoggedIn.getMemberId()%>">1:1문의 내역</a></li>
            <li class="col-md-4 three-times"><a href="<%=request.getContextPath()%>/member/memberUpdate?memberId=<%=memberLoggedIn.getMemberId()%>">회원정보 수정</a></li> 
            <li class="col-md-4"><a href="<%=request.getContextPath()%>/member/memberDelete?memberId=<%=memberLoggedIn.getMemberId()%>">회원정보 탈퇴</a></li>
            <li class="col-md-4"></li>
	        <li class="col-md-4 three-times"></li>
        <%
        	}
        %>
    </ul>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>