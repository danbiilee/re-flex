<%@page import="rent.model.vo.rent" %>
<%@page import="java.util.List"%>
<%@page import="item.model.vo.ItemImage"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>


<%
	List<rent>	list= (List<rent>)request.getAttribute("list");
	int cntfin = (int)request.getAttribute("cntfin");
	/* List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap"); */


	//위시리스트 ajax - 회원아이디 담아놓기
	String memberId = "";
	if(memberLoggedIn!=null) memberId = memberLoggedIn.getMemberId();
	else memberId = "null";

%>

              

<script>

$(function(){	
	/*1개월 클릭시 */
	$("#btn-one").click(function(){
		
		$(this).css('background','#aaaaac')
		$("#btn-two").css('background','white')
		$("#btn-three").css('background','white')
		$("#btn-four").css('background','white')
		
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/MypageRentalFinOne?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
				console.log(data)

				$("#rentlistdiv").html(data);
			},
			error: function(jqxhr,textStatus,errorThrown){
				console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
			}
		})
	});
	/*3개월 클릭시 */
	$("#btn-two").click(function(){
	
		$(this).css('background','#aaaaac')
		$("#btn-one").css('background','white')
		$("#btn-three").css('background','white')
		$("#btn-four").css('background','white')
		
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/MypageRentalFinThree?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
			console.log(data)
			
			$("#rentlistdiv").html(data);
		},
		error: function(jqxhr,textStatus,errorThrown){
			console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
		}
		})
	});
	/*6개월 클릭시 */
	$("#btn-three").click(function(){
		
		$(this).css('background','#aaaaac')
		$("#btn-one").css('background','white')
		$("#btn-two").css('background','white')
		$("#btn-four").css('background','white')
		
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/MypageRentalFinSix?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
			console.log(data)
			
			$("#rentlistdiv").html(data);
		},
		error: function(jqxhr,textStatus,errorThrown){
			console.log("ajax처리실패",jqxhr, textStatus, errorThrown);
		}
		})
	});
	/*전체 클릭시 */
	$("#btn-four").click(function(){
		
		$(this).css('background','#aaaaac')
		$("#btn-one").css('background','white')
		$("#btn-two").css('background','white')
		$("#btn-three").css('background','white')
		
		$.ajax({
			url: "<%=request.getContextPath()%>/mypage/MypageRentalFinAll?memberId=<%=memberLoggedIn.getMemberId()%>",
			type: "get",
			dataType: "html",
			success: function(data){
			console.log(data)
			
			$("#rentlistdiv").html(data);
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
            <a href="<%=request.getContextPath()%>/common/boxMenu?level1=mypage">마이페이지</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li>종료된 렌탈</li>
    </ul>
</nav>



<!-- 종료된 렌탈 헤더-->
<div class="container-fluid contents">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <h2 class="sr-only">종료된 렌탈</h2>
            <section class="my-header">
                <h3 class="sr-only">종료된 렌탈 상태 보기</h3>
                <div class="line-style text-center">
                    <p>전체 종료된 렌탈 <span class="em-blue"><%=cntfin %></span>건</p>
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
						<button type="button" id="btn-four"  style="background : #aaaaac">전체</button>
					</li>
				</ul>
			</section>
		</div>
        <div class="col-md-1"></div>
    </div>
</div>



<!-- 종료된 렌탈 리스트 -->
<div class="container-fluid line-style text-center">
    <p>종료된 계약 상품 정보</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper" >
            <section id="rent-list" class="list-wrapper">
                <h3 class="sr-only">종료된 렌탈 리스트</h3>
                <div id="rentlistdiv">
                 <% if (list != null && list.size() > 0) { %>
                <table class="text-center list-tbl">
                    <thead>
                        <tr>
                            <th class="text-center">계약번호[날짜]</th>
                            <th class="text-center">상품정보</th>
                            <th class="text-center">렌탈기간</th>
                            <th class="text-center">상태</th>
                        </tr>
                    </thead>
                     <tbody>
                     
<%
				for (int i =0; list.size() > i; i++) {
					//렌탈기간
					int rentPeriod = 0;
					if("RT01".equals(list.get(i).getRentOptNo())) rentPeriod = 7;
					else if("RT02".equals(list.get(i).getRentOptNo())) rentPeriod = 14;
					else rentPeriod = 30;
%>


                       <tr>
                            <td>
                                <p><%= list.get(i).getItemNo() %></p>
                                <p><%= list.get(i).getItemRentStart() %></p>
                            </td>
                            <td class="item-info">
                                <a href=""><img src="<%=request.getContextPath()%>/images/item.png" class="pull-left" alt=""></a>
                                <p class="text-left pbrand"><%=list.get(i).getItemBrand() %></p>
                                <p class="text-left pname"><%=list.get(i).getItemName() %></p>
                                <p class="text-left price"><%=list.get(i).getItemPrice() %> <span class="rent-period"> 3개월</p>
                                <p class="pull-left rent-type">월청구</p>
                            </td>
                            <td class="rent-period">
                                <p class="finished"><%=list.get(i).getItemRentStart() +"~" + list.get(i).getItemRentEnd()%></p>
                            </td>
                         <td class="em-purple">
                                <p>계약종료</p>
                            </td>
                            
                        </tr> 
<%
				}
%>
                    </tbody> 
                  </table>
<%
					} else {
%>
					<div id="warning-wrapper" class="content-wrapper text-center">
						<p><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>렌탈종료된 상품이 없습니다.</p> 
					</div>
<%   
					}
%>

                </div>
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
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
                    </a>
                </li>
                </ul>
            </nav> 
        </div>
        <div class="col-md-1"></div>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>