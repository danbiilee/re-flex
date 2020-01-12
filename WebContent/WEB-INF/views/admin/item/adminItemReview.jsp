<%@page import="board.model.vo.Board"%>
<%@page import="itemRentEach.model.vo.ItemRentEach"%>
<%@page import="item.model.vo.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Item item = (Item)request.getAttribute("item");
	double itemStar = (double)request.getAttribute("itemStar");
	List<Board> list = (List<Board>)request.getAttribute("list");

	String pageBar = (String)request.getAttribute("pageBar");
	
	int totalContent = (int)request.getAttribute("totalContent");
	
	
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
                        <h3>상품 리뷰</h3>
                    </div>
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
                        	<th>상품평점</th>
                        </tr>
                        <tr>
                        	<td><%=item.getItemNo() %></td>
                        	<td><%=item.getCategoryNo() %></td>
                        	<td><%=item.getItemBrand() %></td>
                        	<td><%=item.getItemName() %></td>
                        	<td><%=item.getItemPrice() %></td>
                        	<td><%=item.getItemStock() %></td>
                        	<td><%=itemStar %></td>
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
                <div class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
                    <table class="table ">
                        <tr>
                            <th>리뷰번호</th>
                            <th>상품번호</th>
                            <th>리뷰작성자</th>
                            <th>리뷰내용</th>
                            <th>리뷰이미지</th>
                            <th>리뷰날짜</th>
                        </tr>
                        <% if(list==null || list.isEmpty()){ %>
                        <tr>
			                <td colspan="9" align="center"> 검색 결과가 없습니다. </td>
			            </tr>
                        <% 
				            } 
				            else {
				                for(Board b : list){ 
				        %>
				        <tr>
                            <td><%=b.getReview_no()%></td>
                            <td><%=b.getItem_no()%></td>
                            <td><%=b.getReview_writer() %></td>
                            <td><%=b.getReview_content() %></td>
                            <td><%=b.getReview_image_rename() %></td>
                            <td><%=b.getReview_date() %></td>
                        </tr>
                        <%		} 
				            }
				        %>
                    </table>
                </div>

				<div id="pageBar" class="col-md-6 col-sm-6 col-xs-6 col-md-offset-3 text-center">
					<ul class="pagination">
						<%= pageBar %>
					</ul>
				</div>


            </div>




<%@ include file="/WEB-INF/views/common/footer.jsp"%>