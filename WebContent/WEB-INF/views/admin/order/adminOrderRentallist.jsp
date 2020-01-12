<%@page import="itemRentEach.model.vo.ItemRentEach"%>
<%@page import="java.util.List"%>
<%@page import="item.model.vo.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	if(request.getAttribute("item")!=null){
		Item item = (Item)request.getAttribute("item");
	}

	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	
	List<ItemRentEach> list = (List<ItemRentEach>)request.getAttribute("list");

	String pageBar = (String)request.getAttribute("pageBar");
	
	int totalContent = (int)request.getAttribute("totalContent");
	int rentItemYes = (int)request.getAttribute("rentItemYes");
	int rentItemNo = (int)request.getAttribute("rentItemNo");
	
%>

<<script>
$(()=>{
	var $search_item_name = $("#search_item_name");
	var $search_category_no = $("#search_category_no");
	var $search_rent_yn = $("#search_rent_yn");
	
	$("#searchType").change(function(){
		$search_item_name.hide();
		$search_category_no.hide();
		$search_rent_yn.hide();
		
		$("#search_"+$(this).val()).css("display", "inline-block");
	});
});
</script>
<style>
    .height-45{
        height: 45px;
    }
    div#search_item_name {
		display:<%="item_name".equals(searchType)||searchType==null?"":"none"%>;
	}
	div#search_category_no {
		display:<%="category_no".equals(searchType)?"":"none"%>;
	}
	div#search_rent_yn {
		display:<%="rent_yn".equals(searchType)?"":"none"%>;
	}
</style>
		<!-- 메인 컨텐츠 -->
            <div class="container-fluid contents">
                <!-- 상품검색 - 메뉴제목 -->
                <div class="row itemTitle">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                        <h3>렌탈내역</h3>
                    </div>
                </div>

                <!-- 한줄 여백 -->
                <div class="row height-45"></div>

                <!-- 상품검색 - 상품 판매 개요 -->
                <div class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
                    <table class="table ">
                        <tr>
                            <th>전체 상품 수</th>
                            <th>대여 가능한 상품수</th>
                            <th>대여중인 상품수</th>
                        </tr>
                        <tr>
                            <td><%=totalContent %></td>
                            <td><%=rentItemNo %></td>
                            <td><%=rentItemYes %></td>
                        </tr>
                    </table>
                </div>
                
                <!-- 한줄 여백 -->
                <div class="row height-45"></div>
                
				<!-- 상품검색 - 서브제목 -->
                <div class="row">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                        <h4>상품 검색</h4>
                    </div>
                </div>
                
                <!-- 상품검색 - 검색 폼 -->
                <div class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
                    <!-- 상품검색 - 검색 종류 선택 -->
                    <div class="form-group col-xs-3" style="padding-top: 7px;">
                        <select class="form-control " id="searchType">
                            <option value="item_name" <%="item_name".equals(searchType)?"selected":""%>>상품명</option>
                            <option value="category_no" <%="category_no".equals(searchType)?"selected":""%>>카테고리</option>
                            <option value="rent_yn" <%="rent_yn".equals(searchType)?"selected":""%>>대여상태</option>
                        </select>
                    </div>
                    
                    <!-- 상품검색 - 검색어 입력 -->
					<form class="form-inline" action="<%=request.getContextPath()%>/admin/rentalListFinder">
                        <div class="form-group" id="search_item_name">
                        	<input type="hidden" name="searchType" value="item_name"/>
                          	<input type="text" class="form-control" name="searchKeyword" placeholder="상품명을 입력하세요" value="<%="item_name".equals(searchType)?searchKeyword:""%>">
	                        <button type="submit" class="btn btn-default">검색</button>
                        </div>

                    </form>

                    <form class="form-inline" action="<%=request.getContextPath()%>/admin/rentalListFinder">
                        <div class="form-group" id="search_category_no">
                       	 	<input type="hidden" name="searchType" value="category_no"/>
                          	<input type="text" class="form-control" name="searchKeyword" placeholder="대여가능여부를 입력하세요" value="<%="category_no".equals(searchType)?searchKeyword:""%>">
	                        <button type="submit" class="btn btn-default">검색</button>
	                       	<div>
		                       	<span>* 카테고리 : 반려동물 / 육아 / 파티 / 운동 / 여행 / 캠핑</span>
	                       	</div>
                        </div>
                        
                    </form>
                    
                    <form class="form-inline" action="<%=request.getContextPath()%>/admin/rentalListFinder">
                        <div class="form-group" id="search_rent_yn">
                       	 	<input type="hidden" name="searchType" value="rent_yn"/>
                          	<input type="radio" name="searchKeyword" value="N" <%= "N".equals(searchKeyword)?"checked":"" %>/>대여가능 &nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="searchKeyword" value="Y" <%= "Y".equals(searchKeyword)?"checked":"" %>/>대여중 &nbsp;
	                        <button type="submit" class="btn btn-default">검색</button>
                        </div>
                        
                    </form>
                    
                </div>

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
                            <th>상품개별번호</th>
                            <th>카테고리</th>
                            <th>상품명</th>
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
				                	String category = "";
				                	switch(i.getCategoryNo()){
				                	case "CT01": category="반려동물";break;
				                	case "CT02": category="육아";break;
				                	case "CT03": category="파티";break;
				                	case "CT04": category="운동";break;
				                	case "CT05": category="여행";break;
				                	case "CT06": category="캠핑";break;
				                	}
				        %>
				        <tr>
                            <td><%=i.getItemEachNo() %></td>
                            <td><%=category%></td>
                            <td><%=i.getItemName() %></td>
                            <td><%=i.getItemRentYN()=='N'?"가능":"불가능" %></td>
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

				<div id="pageBar" class="col-md-6 col-sm-6 col-xs-6 col-md-offset-3 text-center">
					<ul class="pagination">
						<%= pageBar %>
					</ul>
				</div>


            </div>

<!-- ------------------------------------------------------------- -->


<%@ include file="/WEB-INF/views/common/footer.jsp"%>