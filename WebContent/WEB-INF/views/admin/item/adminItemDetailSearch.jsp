<%@page import="itemRentEach.model.vo.ItemRentEach"%>
<%@page import="item.model.vo.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Item item = (Item)request.getAttribute("item");

	List<ItemRentEach> list = (List<ItemRentEach>)request.getAttribute("list");

	String pageBar = (String)request.getAttribute("pageBar");
	
	int totalContent = (int)request.getAttribute("totalContent");
	int rentItemYes = (int)request.getAttribute("rentItemYes");
	int rentItemNo = (int)request.getAttribute("rentItemNo");
	
%>

<style>
    .height-45{
        height: 45px;
    }
</style>

		<!-- 메인 컨텐츠 -->
            <div class="container-fluid contents">
                <!-- 상품검색 - 메뉴제목 -->
                <div class="row itemTitle">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                        <h3>개별상품현황</h3>
                    </div>
                </div>

                <!-- 한줄 여백 -->
                <div class="row height-45"></div>

                <!-- 상품검색 - 상품 판매 개요 -->
                <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2">
                    <table class="table ">
                        <tr>
                            <th>전체 상품 수</th>
                            <th>대여중인 상품수</th>
                            <th>대여 가능한 상품수</th>
                        </tr>
                        <tr>
                            <td><%=totalContent %></td>
                            <td><%=rentItemYes %></td>
                            <td><%=rentItemNo %></td>
                        </tr>
                    </table>
                </div>
                
                <!-- 한줄 여백 -->
                <div class="row height-45"></div>

                 <!-- 상품검색 - 검색결과 서브제목 -->
                <div class="row">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                        <h4>상품 정보</h4>
                    </div>
                </div>

                <!-- 상품검색 - 서브제목 -->
                <div class="row">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                        <table class="table ">
                        <tr>
                        	<th>상품번호</th>
                        	<th>카테고리</th>
                        	<th>브랜드</th>
                        	<th>상품명</th>
                        	<th>상품가격</th>
                        	<th>상품수량</th>
                        </tr>
                        <tr>
                        	<td><%=item.getItemNo() %></td>
                        	<td><%=item.getCategoryNo() %></td>
                        	<td><%=item.getItemBrand() %></td>
                        	<td><%=item.getItemName() %></td>
                        	<td><%=item.getItemPrice() %></td>
                        	<td><%=item.getItemStock() %></td>
                        </tr>
                        </table>
                    </div>
                </div>

                <!-- 한줄 여백 -->
                <div class="row height-45"></div>

                 <!-- 상품검색 - 검색결과 서브제목 -->
                <div class="row">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                        <h4>상품 목록</h4>
                    </div>
                </div>

                <!-- 상품검색 - 상품 검색 결과 -->
                <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2">
                    <table class="table ">
                        <tr>
                            <th>상품개별번호</th>
                            <th>상품번호</th>
                            <th>대여여부</th>
                            <th>렌탈유형</th>
                            <th>대여시작일</th>
                            <th>대여종료일</th>
                            <th>고객아이디</th>
                        </tr>
                        <% if(list==null || list.isEmpty()){ %>
                        <tr>
			                <td colspan="9" align="center"> 검색 결과가 없습니다. </td>
			            </tr>
                        <% 
				            } 
				            else {
				                for(ItemRentEach i : list){ 
				        %>
				        <tr>
                            <td><%=i.getItemEachNo() %></td>
                            <td><%=i.getItemNo() %></td>
                            <td><%=i.getItemRentYN() %></td>
                            <td>
                            	<% 
                            	if(i.getRentOptNo() == null){
                            	%>
                            	-
                            	<%
                            	}
                            	else{
	                            	switch(i.getRentOptNo()){
	                            	case "RT01": out.println("7일"); break;
	                            	case "RT02": out.println("14일"); break;
	                            	case "RT03": out.println("30일"); break;
	                            	}
                            	}
                            	%>
                            </td>
                            <td><%=(i.getItemRentStart()==null)?"-":i.getItemRentStart() %></td>
                            <td><%=(i.getItemRentEnd()==null)?"-":i.getItemRentEnd() %></td>
                            <td><%=(i.getItemRentUser()==null)?"-":i.getItemRentUser() %></td>
                        </tr>
                        <%		} 
				            }
				        %>
                    </table>
                </div>

				<div id="pageBar" class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2 text-center">
					<ul class="pagination">
						<%= pageBar %>
					</ul>
				</div>


            </div>




<%@ include file="/WEB-INF/views/common/footer.jsp"%>