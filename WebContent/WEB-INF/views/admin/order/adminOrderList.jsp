<%@page import="order.model.vo.OrderDetail2"%>
<%@page import="order.model.vo.OrderSheet"%>
<%@page import="itemRentEach.model.vo.ItemRentEach"%>
<%@page import="java.util.List"%>
<%@page import="item.model.vo.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	
	List<OrderDetail2> list = (List<OrderDetail2>)request.getAttribute("list");

	String pageBar = (String)request.getAttribute("pageBar");
	
	int totalContent = (int)request.getAttribute("totalContent");
	int[] OSArr = (int[])request.getAttribute("OSArr");

	

%>

<<script>
$(()=>{
	var $search_member_id = $("#search_member_id");
	var $search_rent_yn = $("#search_order_status");
	
	$("#searchType").change(function(){
		$search_member_id.hide();
		$search_rent_yn.hide();
		
		$("#search_"+$(this).val()).css("display", "inline-block");
	});
});

function orderUpdate(orderNo) {
	if(!confirm("수정하시겠습니까?")){
		return false;
	}
	var $orderStatus=$("#orderStatus-"+orderNo).val();
	location.href="<%=request.getContextPath()%>/admin/updateOrder?orderNo="+orderNo+"&orderStatus="+$orderStatus;
};
</script>
<style>
    .height-45{
        height: 45px;
    }
    div#search_member_id {
		display:<%="member_id".equals(searchType)||searchType==null?"":"none"%>;
	}
	div#search_order_status {
		display:<%="order_status".equals(searchType)?"":"none"%>;
	}
</style>
		<!-- 메인 컨텐츠 -->
            <div class="container-fluid contents">
                <!-- 상품검색 - 메뉴제목 -->
                <div class="row itemTitle">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                        <h3>배송관리</h3>
                    </div>
                </div>

                <!-- 한줄 여백 -->
                <div class="row height-45"></div>

                <!-- 상품검색 - 상품 판매 개요 -->
                <div class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
                    <table class="table ">
                        <tr>
                            <th>전체 주문 수</th>
                            <th>주문 완료</th>
                            <th>배송준비중</th>
                            <th>배송중</th>
                            <th>배송완료</th>
                            <th>구매확정</th>
                        </tr>
                        <tr>
                            <td><%=totalContent %></td>
                            <td><%=OSArr[0]%></td>
                            <td><%=OSArr[1]%></td>
                            <td><%=OSArr[2]%></td>
                            <td><%=OSArr[3]%></td>
                            <td><%=OSArr[4]%></td>
                        </tr>
                    </table>
                </div>
                
                <!-- 한줄 여백 -->
                <div class="row height-45"></div>
                
				<!-- 상품검색 - 서브제목 -->
                <div class="row">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                        <h4>배송 검색</h4>
                    </div>
                </div>
                
                <!-- 상품검색 - 검색 폼 -->
                <div class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
                    <!-- 상품검색 - 검색 종류 선택 -->
                    <div class="form-group col-xs-3" style="padding-top: 7px;">
                        <select class="form-control " id="searchType">
                            <option value="member_id" <%="member_id".equals(searchType)?"selected":""%>>아이디</option>
                            <option value="order_status" <%="order_status".equals(searchType)?"selected":""%>>배송상태</option>
                        </select>
                    </div>
                    
                    <!-- 상품검색 - 검색어 입력 -->
					<form class="form-inline" action="<%=request.getContextPath()%>/admin/orderListFinder">
                        <div class="form-group" id="search_member_id">
                        	<input type="hidden" name="searchType" value="member_id"/>
                          	<input type="text" class="form-control" name="searchKeyword" placeholder="아이디를 입력하세요" value="<%="member_id".equals(searchType)?searchKeyword:""%>">
	                        <button type="submit" class="btn btn-default">검색</button>
                        </div>

                    </form>

                    <form class="form-inline" action="<%=request.getContextPath()%>/admin/orderListFinder">
                        <div class="form-group" id="search_order_status">
                       	 	<input type="hidden" name="searchType" value="order_status"/>
                          	<input type="radio" name="searchKeyword" value="OS01" <%= "OS01".equals(searchKeyword)?"checked":"" %>/>주문완료 &nbsp;&nbsp;
							<input type="radio" name="searchKeyword" value="OS02" <%= "OS02".equals(searchKeyword)?"checked":"" %>/>배송준비중 &nbsp;&nbsp;
							<input type="radio" name="searchKeyword" value="OS03" <%= "OS03".equals(searchKeyword)?"checked":"" %>/>배송중 &nbsp;&nbsp;
							<input type="radio" name="searchKeyword" value="OS04" <%= "OS04".equals(searchKeyword)?"checked":"" %>/>배송완료 &nbsp;&nbsp;
							<input type="radio" name="searchKeyword" value="OS05" <%= "OS05".equals(searchKeyword)?"checked":"" %>/>구매확정 &nbsp;&nbsp;
	                        <button type="submit" class="btn btn-default">검색</button>
                        </div>
                        
                    </form>
                    
                </div>

                 <!-- 상품검색 - 검색결과 서브제목 -->
                <div class="row">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                        <h4>주문 목록</h4>
                    </div>
                </div>

                <!-- 상품검색 - 상품 검색 결과 -->
                <div class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
                    <table class="table ">
                        <tr>
                            <th>주문번호</th>
                            <th>고객아이디</th>
                            <th>상품수량</th>
                            <th>주문금액</th>
                            <th>주문상태</th>
                            <th></th>
                        </tr>
                        <% if(list==null || list.isEmpty()){ %>
                        <tr>
			                <td colspan="9" align="center"> 검색 결과가 없습니다. </td>
			            </tr>
                        <% 
				            } 
				            else {
				                for(OrderDetail2 od : list){
				                	String orderStatus = "";
				                	switch(od.getOrderStatusNo()){
				                	case "OS01": orderStatus="주문완료";break;
				                	case "OS02": orderStatus="배송준비중";break;
				                	case "OS03": orderStatus="배송중";break;
				                	case "OS04": orderStatus="배송완료";break;
				                	case "OS05": orderStatus="구매확정";break;
				                	}
				        %>
				        <tr>
                            <td><%=od.getOrderNo() %></td>
                            <td><%=od.getMemberId() %></td>
                            <td><%=od.getOrderTotalItemEa() %></td>
                            <td><%=od.getOrderTotalPrice() %></td>
                            <%
                            	if("OS05".equals(od.getOrderStatusNo())){
                            %>
                            <td style="width: 120px;">
  		                        <select class="form-control input-sm" disabled style="margin-top: -3px;margin-bottom: -3px;">
                            		<option value="OS05" selected >구매확정</option>	
                            	</select>
                            </td>
                            <td>
                            	<button type="button" class="btn btn-xs btn-primary updateBtn" disabled="disabled" >변경</button>
                            </td>
                            <%
                            	}
                            	else{
                            %>
                            <td style="width: 120px;">
	                            <select class="form-control input-sm" id="orderStatus-<%=od.getOrderNo() %>" style="margin-top: -3px;margin-bottom: -3px;">
		                            <option value="OS01" <%="주문완료".equals(orderStatus)?"selected":""%>>주문완료</option>
		                            <option value="OS02" <%="배송준비중".equals(orderStatus)?"selected":""%>>배송준비중</option>
		                            <option value="OS03" <%="배송중".equals(orderStatus)?"selected":""%>>배송중</option>
		                            <option value="OS04" <%="배송완료".equals(orderStatus)?"selected":""%>>배송완료</option>
		                        </select>
		                    </td>
                            <td>
                            	<button type="button" id="updateBtn" class="btn btn-xs btn-primary updateBtn" onclick="orderUpdate('<%=od.getOrderNo() %>')" value="<%=od.getOrderNo() %>">변경</button>
                            </td>
                            <%
                            	}
                            %>
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