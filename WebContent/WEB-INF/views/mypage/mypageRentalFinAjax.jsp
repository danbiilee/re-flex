<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="rent.model.vo.rent" %>

<%
	List<rent>	list= (List<rent>)request.getAttribute("list");
	int cntfin = (int)request.getAttribute("cntfin");
%>
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
//리스트에 담기
if (list != null && list.size() > 0) {
	for (int i =0; list.size() > i; i++) {

	
	//렌탈기간
	int rentPeriod = 0;
	if("RT01".equals(list.get(i).getRentOptNo())) rentPeriod = 7;
	else if("RT02".equals(list.get(i).getRentOptNo())) rentPeriod = 14;
	else rentPeriod = 30;
%>


                       <tr>
                            <td>
                                <p><%=list.get(i).getItemNo() %></p>
                                <p><%= "["+ list.get(i).getItemRentStart() +"]"%></p>
                            </td>
                            <td class="item-info">
                                <a href=""><img src="<%=request.getContextPath()%>/images/item.png" class="pull-left" alt=""></a>
                                <p class="text-left pbrand"><%=list.get(i).getItemBrand() %></p>
                                <p class="text-left pname"><%=list.get(i).getItemName() %></p>
                                <p class="text-left price"><%=list.get(i).getItemPrice() %> <span class="rent-period"> / <%=rentPeriod %>일</p>
                                <p class="pull-left rent-type">일시납</p>
                            </td>
                            <td class="rent-period">
                                <p class="finished"><%=list.get(i).getItemRentStart() +"~" + list.get(i).getItemRentEnd()%></p>
                            </td>
                         <td class="em-purple">
                                <p>계약종료</p>
                            </td>
                            
                        </tr> 
                    </tbody> 
<%
						}
					} else {
					    out.println("<td>");
					    out.println("렌탈 종료된 상품이 없습니다.");
					    out.println("</td>");
					   
					}
%>
</table>