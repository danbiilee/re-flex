<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="rent.model.vo.rent" %>
<%@page import="item.model.vo.ItemImage"%>
<%@page import="java.util.List"%>
<%@page import="java.io.*" %>
<%@page import="java.util.*" %>
<!-- page nav -->

<%
	List<rent>	list= (List<rent>)request.getAttribute("list");
	int cnt = (int)request.getAttribute("cnt");
	List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap");
	List<Integer> dday = (List<Integer>)request.getAttribute("dday");

	//위시리스트 ajax - 회원아이디 담아놓기
	String memberId = "";
	if(memberLoggedIn!=null) memberId = memberLoggedIn.getMemberId();
	else memberId = "null";

%>


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
        <li>계약중인 렌탈</li>
    </ul>
</nav>

<!-- 계약중인 렌탈 헤더 -->
<div class="container-fluid contents">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <h2 class="sr-only">계약중인 렌탈</h2>
            <!-- 계약중인 렌탈 상태 보기 -->
            <section class="my-header">
                <h3 class="sr-only">계약중인 렌탈 상태</h3>
                <div class="line-style text-center">
                   <p>계약중인 렌탈 <span class="em-blue"><%=cnt %></span>건</p>
                </div>
            </section>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

 

<!-- 계약중인 렌탈 리스트 -->
<div class="container-fluid line-style text-center">
    <p class="strong">계약 상품 정보</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <section id="rent-list" class="list-wrapper">
                <h3 class="sr-only">주문현황 리스트</h3>
                <% if (list != null && list.size() > 0) { %>
                <table id="contracting-tbl" class="text-center list-tbl">
                	<colgroup>
	                    <col width="15%">
	                    <col width="60%">
	                    <col width="17%">
	                    <col width="10%">
	                </colgroup> 
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
						List<ItemImage> imgList = imgMap.get(itemNoList.get(i));

					//렌탈기간
					int rentPeriod = 0;
					if("RT01".equals(list.get(i).getRentOptNo())) rentPeriod = 7;
					else if("RT02".equals(list.get(i).getRentOptNo())) rentPeriod = 14;
					else rentPeriod = 30;
					
					/* //가격 , 붙이기
					DecimalFormat dc = new DecimalFormat("###,###,###,###원");
					String pbyRentOptNo = dc.format(cart.getPriceByRentOptNo()); //1개가격
					String pEa = dc.format(cart.getPriceByRentOptNo()*cart.getItemQuantity()); // *수량 */
%>
                        <tr>
                            <td>
                                <p><%= list.get(i).getItemNo() %> </p>
                                <p><%= "["+list.get(i).getItemRentStart() +"]" %></p>
                            </td>
                            <td class="item-info">
                                <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=list.get(i).getCategoryNo()%>&itemNo=<%=list.get(i).getItemNo()%>">
                                <img src="<%=request.getContextPath()%>/images/<%=list.get(i).getCategoryNo()%>/<%=imgList.get(0).getItemImageRenamed()%>" class="pull-left" alt="상품이미지">
                                </a>
                                <p class="text-left pbrand"><%= list.get(i).getItemBrand() %></p>
                                <p class="text-left pname"><%= list.get(i).getItemName() %></p>
                                <p class="text-left price"><%= list.get(i).getItemPrice() %><span class="rent-period"> / <%=rentPeriod %> 일 </p>
                              <p class="pull-left rent-type">일시납</p> 
                            </td>
                            <td class="rent-period">
                                <p><%=list.get(i).getItemRentStart() +"~" + list.get(i).getItemRentEnd()%></p>
                                <p class="em-blue" id="dday"> 종료일까지 :  <%=dday.get(i) %> 일 </p>
                            </td>
                            <td class="em-purple">
                                <p>계약중</p>
                            </td>
                        </tr>
                    <% } %>
                    </tbody>
               </table>  
<%
				} 
                else {
%>
				<div id="warning-wrapper" class="content-wrapper text-center">
					<p><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>렌탈종료된 상품이 없습니다.</p> 
				</div>
<%
					}
%>
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