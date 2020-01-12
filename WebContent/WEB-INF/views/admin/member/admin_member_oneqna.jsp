<%@page import="mypage.model.vo.Qna"%>
<%@page import="member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Qna> list = (List<Qna>)request.getAttribute("list");

	String qnaSearchType = request.getParameter("qnaSearchType");
	String qnaSearchword = request.getParameter("qnaSearchword");

	String pageBar = (String)request.getAttribute("pageBar");	
%>

<style>
.height-45{
   height: 45px;
}
div#search-qnaType {
	display:<%="qnaType".equals(qnaSearchType)||qnaSearchType==null?"":"none"%>;
}
div#search-qnaYN {
	display:<%="qnaYN".equals(qnaSearchType)?"":"none"%>;
}
</style>



<script>
$(()=>{
	var $qnaSearchType = $("#search-qnaType");
	var $qnaSearchword = $("#search-qnaYN");
	
	$("#qnaSearchType").change(function(){
		$qnaSearchType.hide();
		$qnaSearchword.hide();
		console.log($(this).val());
		$("#search-"+$(this).val()).css("display", "inline-block");
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
	            <a href="">관리자페이지</a>
	            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
	        </li> 
	        <li>1:1 문의내역</li>
	    </ul>
	</nav>

  	<div class="container-fluid contents">
        <!-- 회원검색 - 메뉴제목 -->
        <div class="row itemTitle">
          	<div class="col-md"></div>
            <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                <h3>1:1문의 내역</h3>
            </div>
        </div>
        <!-- 한줄 여백 -->
        <div class="row height-45"></div>
        <!-- 회원검색 - 서브제목 -->
        <div class="row">
            <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                <h4>문의 검색</h4>
            </div>
        </div>
      
    	<!-- 문의현황 검색창 -->
    	<div class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
            <!-- 문의검색창 폼 -->
                <div class="form-group col-xs-3" style="padding-top: 7px;">
                    <select id="qnaSearchType" class="form-control">
                        <option value="qnaType" <%="qnaType".equals(qnaSearchType)?"selected":"" %>>문의유형</option>
                        <option value="qnaYN" value="qnaYN" <%="qnaYN".equals(qnaSearchType)?"selected":"" %>>답변여부</option>
                    </select>
                </div>

              	<form class="form-inline" action="<%=request.getContextPath()%>/admin/member/qnaSearch">
                   	<div id="search-qnaType" class="form-group">
                   		<input type="hidden" name="qnaSearchType" value="qnaType"/>
                   		<input type="text" name="qnaSearchword" class="form-control" 
						value="<%="qnaType".equals(qnaSearchType)?qnaSearchword:""%>" placeholder="문의유형 검색" >
                  		<button type="submit" class="btn btn-default">검색</button>
 	               </div>
              	</form>
                      
                <form class="form-inline" action="<%=request.getContextPath()%>/admin/member/qnaSearch">
                    <div id="search-qnaYN" class="form-group">
                 		<input type="hidden" name="qnaSearchType" value="qnaYN"/>
                   		<input name="qnaSearchword" type="text" class="form-control" 
						value="<%="qnaYN".equals(qnaSearchType)?qnaSearchword:""%>" placeholder="답변여부 검색" >
                   		<button type="submit" class="btn btn-default">검색</button>
 	               </div>  
             	</form>
        </div>
            
        <!-- 한줄 여백 -->
        <div class="row height-45"></div>
        <!-- 회원검색 - 검색결과 서브제목 -->
        <div class="row">
            <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                <h4>문의 목록</h4>
            </div>
        </div>



	    <!-- 문의현황 테이블 -->
	    <div class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
	        <table class="table">
                <tr>
                    <th>번호</th>
                    <th>문의유형</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일자</th>
                    <th>답변여부</th>
                </tr>
              	<%if(list==null || list.isEmpty()){ %>
              		<td colspan="9" align="center">등록된 1:1 문의내역이 없습니다.</td>
              	<%}
              	else{
              		for(Qna q : list){
              	%>
              	<tr>
              		<!--  //번호	문의유형	제목	작성자	작성일자	답변여부 -->
              		<td><%=q.getqNo()%></td>
              		<td>
              		<% switch(q.getqTypeNo()){
                           case "QT01": out.println("상품문의"); break;
                           case "QT02": out.println("배송문의"); break;
                           case "QT03": out.println("기타문의"); break;
                       }
                    %>
              		</td>
              		<%if("N".equals(q.getqAns())){ %>
              		<td><a href="<%=request.getContextPath()%>/admin/member/memberQnaForm?qNo=<%=q.getqNo()%>"><%=q.getqTilte()%></a></td>
              		<%}
              		else {%>
              		<td><a href="<%=request.getContextPath()%>/admin/member/memberQnaShow?qNo=<%=q.getqNo()%>"><%=q.getqTilte()%></a></td>
              		
              		<%} %>
              		<td><%=q.getMemberId()%></td>
              		<td><%=q.getqDate()%></td>
              		<td><%=q.getqAns()%></td>
              	</tr>
              	<%
              		}
              	}
              	%>
            
            </table>
	        <div id="pageBar" class="col-md-6 col-sm-6 col-xs-6 col-md-offset-3 text-center">
		        <ul class="pagination">
					<%=pageBar %>
				</ul>
			</div>
		</div>
	</div>
     <!-- 문의현황 테이블 끝 -->
     
     
<%@ include file="/WEB-INF/views/common/footer.jsp"%>