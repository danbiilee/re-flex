<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*" %>
   
   <% 
   		//로그인한 경우
   		Member memberLoggedIn= (Member)session.getAttribute("memberLoggedIn");

   %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>re:flex</title>
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Libre+Barcode+128+Text|Monoton|Permanent+Marker|Righteous|Seymour+One|Stalinist+One&display=swap" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"> <!-- 부트스트랩 -->
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/mypage.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/header.js"></script>
    
</head>
<body>
    <header class="container-fluid navbar-fixed-top">
        <div id="header-wrapper" class="row">
            <div id="logo" class="col-md-4">
                <h1 class="eng"><a href="<%=request.getContextPath()%>/index.jsp">re:flex</a></h1>
            </div>
            <div id="search" class="col-md-4">
                <h2 class="sr-only">상품 검색</h2>
                <form action="<%=request.getContextPath()%>/item/itemSearch">
                    <button type="submit" id="btn-search" aria-label="search products">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    </button>
                    <input type="search" name="search" placeholder="검색">
                </form>
            </div>
            <div id="menu" class="col-md-4">
                <ul class="list-unstyled list-inline text-right">
                    <%if(memberLoggedIn==null){ %>
                    <li class="login-hide"><a href="<%=request.getContextPath()%>/member/memberLogin">로그인</a></li>
                    <li class="login-hide"><a href="<%=request.getContextPath()%>/member/memberEnroll">회원가입</a></li>
                    <%}
                    else{
                    %>

	                <li><span class="strong"><%=memberLoggedIn.getMemberName()%></span>님</li>                    

	                <li>
	                	<a href="<%=request.getContextPath()%>/member/logout">로그아웃</a>
	                </li>
					<% 	} %>

					<%if(memberLoggedIn !=null && !"admin".equals(memberLoggedIn.getMemberId())) {%>
                    <li>
                        <h2 class="sr-only">장바구니</h2>
                        <a href="<%=request.getContextPath()%>/member/memberCart?memberId=<%=memberLoggedIn.getMemberId()%>" aria-label="cart"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
                    </li>
                    <%} %>
                    <li>
                        <button type="button"  id="btn-sidemenu">
                            <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                        </button>
                    </li>
                </ul>
                <!-- side menu -->
                <!-- level1 -->
                <div id="sidemenu-closeLayer">
                <nav id="level1-sidemenu" class="sidemenu-wrapper">
                    <header></header>
                    <ul class="list-unstyled">
                        <li id="recommended" data-target="#level2-recommended"><a href="#">이럴 때 빌려봐<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></li>
                        <% 
                        	//관리자가 로그인한 경우
                        	if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())){ 
                        %>
                        	<li id="adminPage" data-target="#level2-adminPage"><a href="#">관리자페이지<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></li>
						<% 
							} 
                        	//로그인한 회원이 있는 경우 마이페이지 메뉴 
                        	else if(memberLoggedIn!=null && !("admin".equals(memberLoggedIn.getMemberId()))){
						%>
                        	<li id="mypage" data-target="#level2-mypage"><a href="#">마이페이지<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></li>
                        <%
	                        }
                        	//로그인 안했으면 로그인, 회원가입 메뉴 보이게
							else{
                        %>
                        	<li><a href="<%=request.getContextPath()%>/member/memberLogin">로그인</a></li>
                        	<li><a href="<%=request.getContextPath()%>/member/memberEnroll">회원가입</a></li>
                        <%
							}
                        %>
                        <li><a href="#">고객서비스(FAQ)</a></li>
                        <li><a href="#">re:flex 소개</a></li>
                    </ul>
                </nav>
                <!-- level2: 이럴때빌려봐 -->
                <nav id="level2-recommended" class="sidemenu-wrapper">
                    <header class="text-center">
                        <button type="button" class="btn-goLevel1">
                        	<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                              이럴 때 빌려봐
                        </button>
                    </header>
                    <ul class="list-unstyled">
                        <li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT01">반려동물과 함께 할 때</a></li>
                        <li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT02">육아할 때</a></li>
                        <li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT03">파티할 때</a></li>
                        <li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT04">운동할 때</a></li>
                        <li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT05">여행갈 때</a></li>
                        <li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=CT06">캠핑갈 때</a></li>
                    </ul>
                </nav>
                <!-- level2: 마이페이지 -->
                <%
                	if(memberLoggedIn != null){
                %>
                <nav id="level2-mypage" class="sidemenu-wrapper">
                    <header class="text-center">
                        <button type="button" class="btn-goLevel1">
                            <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                            마이페이지
                        </button>
                    </header>
                    <ul class="list-unstyled">
                        <li><a href="<%=request.getContextPath()%>/mypage/mypageOrderList?memberId=<%=memberLoggedIn.getMemberId()%>">주문조회</a></li>
                        <li><a href="<%=request.getContextPath()%>/mypage/mypageWishlist?memberId=<%=memberLoggedIn.getMemberId()%>">위시리스트</a></li>
                        <li><a href="<%=request.getContextPath()%>/mypage/mypageRentalIng?memberId=<%=memberLoggedIn.getMemberId()%>">계약중인 렌탈</a></li>
                        <li><a href="<%=request.getContextPath()%>/mypage/mypageRentalFin?memberId=<%=memberLoggedIn.getMemberId()%>">종료된 렌탈</a></li>
                        <li><a href="<%=request.getContextPath()%>/mypage/mypagePoint?memberId=<%=memberLoggedIn.getMemberId()%>">포인트 확인</a></li>
                        <li><a href="<%=request.getContextPath()%>/mypage/mypageReview?memberId=<%=memberLoggedIn.getMemberId()%>">이용후기 내역</a></li>
                        <li><a href="<%=request.getContextPath()%>/mypage/mypageOneToOne?memberId=<%=memberLoggedIn.getMemberId()%>">1:1문의 내역</a></li>
                   		<li><a href="<%=request.getContextPath()%>/member/memberUpdate?memberId=<%=memberLoggedIn.getMemberId()%>">회원정보 수정</a></li> 
                        <li><a href="<%=request.getContextPath()%>/member/memberDelete?memberId=<%=memberLoggedIn.getMemberId()%>">회원정보 탈퇴</a></li>
                    </ul>
                </nav>
                <%
                	}
                %>

                

                <nav id="level2-adminPage" class="sidemenu-wrapper">
                    <header class="text-center">
                        <button type="button" class="btn-goLevel1">
                            <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                            관리자페이지
                        </button>
                    </header>
                    <ul class="list-unstyled">
                        <li><a href="<%=request.getContextPath()%>/admin/member/memberSearch">회원조회</a></li>
                        <li><a href="<%=request.getContextPath()%>/admin/orderList">배송관리</a></li>
                        <li><a href="<%=request.getContextPath()%>/admin/rentalList">렌탈내역</a></li>
                        <li><a href="<%=request.getContextPath()%>/admin/itemSearch">상품조회</a></li>
                        <li><a href="<%=request.getContextPath()%>/admin/member/memberQna">1:1문의관리</a></li>
                        
                        <li><a href="<%=request.getContextPath()%>/admin/itemEnroll">상품등록</a></li>
                        <li><a href="<%=request.getContextPath()%>/admin/orderOutline">판매현황</a></li>
                    </ul>
                </nav>
                </div>
            </div>
        </div>
    </header>