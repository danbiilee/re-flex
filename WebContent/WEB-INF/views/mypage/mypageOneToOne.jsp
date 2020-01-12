<%@page import="mypage.model.vo.Qna"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
List<Qna> list = (List<Qna>)request.getAttribute("list");

String pageBar = (String)request.getAttribute("pageBar");
%>

<script>
$(function(){	
	/*1개월 클릭시 */
	$("#btn-one").click(function(){
		$(this).css('background','#AAAAAC')
		$("#btn-two").css('background','white')
		$("#btn-three").css('background','white')
		$("#btn-four").css('background','white')
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/pointOne?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
				console.log(data)
				$("#pointListDiv").html(data);
			},
			error: function(jqxhr,textStatus,errorThrown){
				console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
			}
		})
	});
	/*3개월 클릭시 */
	$("#btn-two").click(function(){
		$(this).css('background','#AAAAAC')
		$("#btn-one").css('background','white')
		$("#btn-three").css('background','white')
		$("#btn-four").css('background','white')
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/pointThree?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
			console.log(data)
			$("#pointListDiv").html(data);
		},
		error: function(jqxhr,textStatus,errorThrown){
			console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
		}
		})
	});
	/*6개월 클릭시 */
	$("#btn-three").click(function(){
		$(this).css('background','#AAAAAC')
		$("#btn-one").css('background','white')
		$("#btn-two").css('background','white')
		$("#btn-four").css('background','white')
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/pointSix?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
			console.log(data)
			$("#pointListDiv").html(data);
		},
		error: function(jqxhr,textStatus,errorThrown){
			console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
		}
		})
	});
	/*전체 클릭시 */
	$("#btn-four").click(function(){
		$(this).css('background','#AAAAAC')
		$("#btn-one").css('background','white')
		$("#btn-two").css('background','white')
		$("#btn-three").css('background','white')
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/pointAll?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
			console.log(data)
			$("#pointListDiv").html(data);
		},
		error: function(jqxhr,textStatus,errorThrown){
			console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
		}
		})
	});
});
</script>

<!-- page nav -->
<nav class="line-style page-nav">
    <ul class="list-unstyled list-inline">
        <li class="go-home">
            <a href="<%=request.getContextPath()%>/index.jsp">메인</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li class="go-boxmenu">
            <a href="">마이페이지</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li>1:1문의내역</li>
    </ul>
</nav>
<!-- 문의글 등록 버튼 -->
<div class="container-fluid contents">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <h2 class="sr-only">1:1문의내역</h2>
            <!-- 문의글 등록하기 -->
             <section class="my-header">
                <h3 class="sr-only">문의글 등록하기</h3>
                <div class="line-style text-center">
                    <a href="<%=request.getContextPath()%>/mypage/mypageOneToOneForm" class="btn-radius btn-qna">1:1문의 등록하기</a>
                </div>
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
            <section class="my-header search-date">
                <h3 class="sr-only">주문현황 기간검색하기</h3>
				<ul class="row list-inline list-unstyled">
					<li class="col-md-3 text-center">
						<button type="button" id="btn-one">1개월</button>
					</li>
					<li class="col-md-3 text-center sel">
						<button type="button" id="btn-two" style="background:  white;">3개월</button>
					</li>
					<li class="col-md-3 text-center">
						<button type="button" id="btn-three">6개월</button>
					</li>
					<li class="col-md-3 text-center">
						<button type="button" id="btn-four">전체</button>
					</li>
				</ul>
			</section>
		</div>
        <div class="col-md-1"></div>
    </div>
</div>
<!-- 1:1문의내역보기 헤더 -->
<div class="container-fluid line-style text-center">
    <p class="strong">1:1문의내역보기</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div id="oneToOne-wrapper" class="col-md-10 content-wrapper">
            <section id="point-list" class="list-wrapper">
                <h3 class="sr-only">문의내역 리스트</h3>
                <%if(list==null || list.isEmpty()) { %>
                <div id="warning-wrapper" class="content-wrapper text-center">
					<p><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>1:1문의 내역이 없습니다.</p> 
				</div>
				<%
                	}
                	else {
				%>
                <table class="text-center list-tbl">
                    <thead>
                        <tr class="row">
                            <th class="col-md-2 text-center">유형</th>
                            <th class="col-md-6 text-center">제목</th>
                            <th class="col-md-2 text-center">답변상태</th>
                            <th class="col-md-2 text-center">문의일자</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% 
                     for(Qna q : list) { 
                    	 String typeName="";
                    	 switch(q.getqTypeNo()){
                    	 case "QT01": typeName="상품문의"; break;
                    	 case "QT02": typeName="배송문의"; break;
                    	 case "QT03": typeName="기타문의"; break;
                    	 }
                     
                     %>
						<tr class="row">
                            <td class="col-md-2"><%= typeName %></td>
                            <td class="col-md-6"><a href="<%=request.getContextPath()%>/mypage/mypageOneToOneView?qNo=<%=q.getqNo()%>"><%=q.getqTilte() %></a></td>
                            <td class="col-md-2"><%=q.getqAns() %></td>
                            <td class="col-md-2"><%=q.getqDate() %></td>
                        </tr> 
                     <% } %>
                    </tbody>
                </table>
            </section>
            <!-- 페이징바 -->
            <nav class="paging-bar text-center">
                    <ul class="list-unstyled list-inline">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                        </a>
                    </li>
                    <li class="cPage"><a href="#">1</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
                        </a>
                    </li>
                    </ul>
            </nav>
            <% } %>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>