<%@page import="mypage.model.vo.MyPage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<MyPage> list = (List<MyPage>)request.getAttribute("list");
%>

<table class="text-center list-tbl">
	<thead>
		<tr class="row">
			<th class="col-md-2 text-center">일자</th>
			<th class="col-md-2 text-center">유형</th>
			<th class="col-md-6 text-center">상세내용</th>
			<th class="col-md-2 text-center">포인트</th>
		</tr>
	</thead>
	<tbody>
		
			<% if(list==null || list.isEmpty()){ %>
			<tr>
				<td colspan="9" align="center">검색 결과가 없습니다.</td>
			</tr>
			<% 
           					} 
            				else{
                				for(MyPage mm : list){ 
        				%>
			<tr class="row">
				<td class="col-md-2"><%=mm.getPointChangeDate()%></td>
				<td class="col-md-2"><%='A'==mm.getPointStatus()?"적립":"사용"%></td>
				<td class="col-md-6"><%=mm.getPointChangeReason()%></td>
				<td class="col-md-2"><%=mm.getPointAmount()%></td>
			</tr>


			<%		} 
            				}
        				%>
		
	</tbody>
</table>


















