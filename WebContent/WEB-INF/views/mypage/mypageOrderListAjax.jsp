<%@page import="java.util.Arrays"%>
<%@page import="java.sql.Date"%>
<%@page import="item.model.vo.Item"%>
<%@page import="item.model.vo.ItemImage"%>
<%@page import="java.util.Map"%>
<%@page import="order.model.vo.OrderDetail"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//주문번호
	List<String> orderNoList = (List<String>)request.getAttribute("orderNoList");
	//주문번호에 담긴 상품내역들
	Map<String, List<OrderDetail>> orderListByOrderNo = (Map<String, List<OrderDetail>>)request.getAttribute("orderListByOrderNo");
	//상품내역들의 대여 시작일, 반납일
	Map<String, List<Date[]>> rentDateMap = (Map<String, List<Date[]>>)request.getAttribute("rentDateMap");
	
	//상품 사진
	List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap");
%>

<h3 class="sr-only">주문현황 리스트</h3>
                <table id="odl-tbl" class="text-center list-tbl">
                	<colgroup>
	                    <col width="16%">
	                    <col width="35%">
	                    <col width="7%">
	                    <col width="16%">
	                    <col width="13%">
	                    <col width="13%">
	                </colgroup>
                    <thead>
                        <tr>
                            <th class="text-center">주문번호[날짜]</th>
                            <th class="text-center">상품정보</th>
                            <th class="text-center">수량</th>
                            <th class="text-center">렌탈기간</th>
                            <th class="text-center">상태</th>
                            <th class="text-center">변경</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    	if(orderListByOrderNo!=null && !orderListByOrderNo.isEmpty()){
                    		for(int i=0; i<orderNoList.size(); i++){
                    			String orderNo = orderNoList.get(i); //주문번호
                    			
                    			//한 건의 주문안에 담겨있는 주문상품내역리스트
                    			List<OrderDetail> odList = orderListByOrderNo.get(orderNo);
                    			//대여기간 리스트
                    			List<Date[]> dateList = rentDateMap.get(orderNo);

                    			for(int j=0; j<odList.size(); j++){
                    				OrderDetail od = odList.get(j); //하나의 주문상품내역
                    				Item item = od.getItem(); //그 안에 포함된 하나의 상품
                    				List<ItemImage> imgList = imgMap.get(item.getItemNo()); //그 상품의 이미지리스트
                    				
                    				//렌탈기간
        							int rentPeriod = 0;
        							if("RT01".equals(od.getRentOptNo())) rentPeriod = 7;
        							else if("RT02".equals(od.getRentOptNo())) rentPeriod = 14;
        							else rentPeriod = 30;
                    				
                    				//가격 ,찍기
                    				DecimalFormat dc = new DecimalFormat("###,###,###,###원");
                    				String dpEa = dc.format(od.getPriceByRentOptNo()*od.getItemQuantity());
                    				
                    				//대여기간
                    				Date[] dArr = dateList.get(j);
                    				
                    				//주문상태
                    				String orderStatus = "주문완료";
                    				if("OS02".equals(od.getOrderStatusNo())) orderStatus = "배송준비중";
                    				else if("OS03".equals(od.getOrderStatusNo())) orderStatus = "배송중";
                    				else if("OS04".equals(od.getOrderStatusNo())) orderStatus = "배송완료";
                    				else if("OS05".equals(od.getOrderStatusNo())) orderStatus = "구매확정";
                    %>
                        <tr class="orderRow <%=i%> <%=od.getOrderTotalItemEa()%> <%=orderNo%> <%=od.getOrderDate()%>">
                        	<input type="hidden" name="odListSize" value="<%=odList.size()%>" />
                            <td rowspan="">
                                <p><%=orderNo%></p>
                                <p>[<%=od.getOrderDate()%>]</p>
                            </td>
                            <td class="item-info">
                                <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=item.getCategoryNo()%>&itemNo=<%=item.getItemNo()%>"><img src="<%=request.getContextPath()%>/images/<%=item.getCategoryNo()%>/<%=imgList.get(0).getItemImageRenamed()%>" class="pull-left" alt="상품이미지"></a>
                                <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=item.getCategoryNo()%>&itemNo=<%=item.getItemNo()%>">
                                	<p class="text-left pbrand"><%=item.getItemBrand()%></p>
                                	<p class="text-left pname"><%=item.getItemName()%></p>
                                </a>
                                <p class="text-left price"><%=dpEa%> /<span class="rent-period"> <%=rentPeriod %>일</p>
                                <p class="pull-left rent-type">일시납</p>
                            </td>
                            <td><%=od.getItemQuantity()%></td>
                            <% if(dArr[0]==null || dArr[1]==null){ %>
                            	<td>-</td>
                            <% 
                            	}
                            	else{
                            %>
                           		<td class=""><%=dArr[0]%>~<%=dArr[1]%></td>
                            <% } %>
                            <td class="order-status <%=od.getOrderDetailNo()%>">
                                <p class="ship-status"><%=orderStatus%></p>
                                <button type="button" id="btn-goReview" class="btn-radius" value="<%=od.getOrderStatusNo()%>" onclick="goReview(this,'<%=od.getReviewYn()%>','<%=od.getOrderDetailNo()%>','<%=item.getItemNo()%>');">구매후기</button>
                            </td>
                            <td class="item-change <%=od.getOrderDetailNo()%>">
                            	<% if(od.getOrderCancelNo()==null){ %>
                                <ul class="list-unstyled text-center">
                                    <li><button type="button" id="btn-cancelOrder" class="btn-radius" value="<%=od.getOrderStatusNo()%>" onclick="cancelOrder(this,'<%=od.getOrderDetailNo()%>','<%=item.getItemNo()%>','<%=od.getRentOptNo()%>','<%=od.getMemberId()%>');">주문취소</button></li>
                                    <li><button type="button" id="btn-exchangeItem" class="btn-radius" value="<%=od.getOrderStatusNo()%>" onclick="exchangeItem(this,'<%=od.getOrderDetailNo()%>','<%=item.getItemNo()%>','<%=od.getRentOptNo()%>','<%=od.getMemberId()%>');">상품교환</button></li>
                                    <li><button type="button" id="btn-cancelRent" class="btn-radius" value="<%=od.getOrderStatusNo()%>" onclick="cancelRent(this,'<%=od.getOrderDetailNo()%>','<%=item.getItemNo()%>','<%=od.getRentOptNo()%>','<%=od.getMemberId()%>');">렌탈해지</button></li>
                                </ul>
                                <% } 
                            		else{ 
                                		String cancelNo = ("OC01".equals(od.getOrderCancelNo()))?"주문취소":("OC02".equals(od.getOrderCancelNo()))?"상품교환":"렌탈해지";
                                %>
                                	<%=cancelNo %>
                                <%} %>
                            </td>
                        </tr>
                    <%
                    			}
                    		}
                    	}
                    %>    
                    </tbody>
                </table>