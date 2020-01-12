<%@page import="member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Member> list = (List<Member>)request.getAttribute("list");

	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");

	String pageBar = (String)request.getAttribute("pageBar");	
%>
<style>
.height-45{
   height: 45px;
}
div#search-memberId {
	display:<%="memberId".equals(searchType)||searchType==null?"":"none"%>;
}
div#search-memberName {
	display:<%="memberName".equals(searchType)?"":"none"%>;
}
</style>

<script>
$(()=>{
	var $searchMemberId = $("#search-memberId");
	var $searchMemberName = $("#search-memberName");
	
	$("#searchType").change(function(){
		$searchMemberId.hide();
		$searchMemberName.hide();
		
		$("#search-"+$(this).val()).css("display", "inline-block");
	});
});

function confirmDelete(){
	
	var bool = confirm("정말 삭제하시겠습니까?");
 
   	if(bool==false){
   		return false;
   	}
   	else{
        return true;
   	}
}

function orderListSearch(memberId) {
	location.href="<%=request.getContextPath()%>/admin/orderListFinder?searchType=member_id&searchKeyword="+memberId;
}
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
        <li>회원조회</li>
    </ul>
</nav>

        <!-- 메인 컨텐츠 -->
        <div class="container-fluid contents">
            <!-- 회원검색 - 메뉴제목 -->
            <div class="row itemTitle">
            	<div class="col-md"></div>
                <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                    <h3>회원조회</h3>
                </div>
            </div>
            <!-- 한줄 여백 -->
            <div class="row height-45"></div>
            <!-- 회원검색 - 서브제목 -->
            <div class="row">
                <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                    <h4>회원 검색</h4>
                </div>
            </div>
            <!-- 회원검색 - 검색 폼 -->
            
            <div id="search-container" class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
                
                <form action="<%=request.getContextPath()%>/admin/member/memberFinder" class="form-inline" method="get" >
                    <!-- 회원검색 - 검색 종류 선택 -->
                    <div class="form-group">
                        <select id="searchType" class="form-control">
                        	<option value="memberId" <%="memberId".equals(searchType)?"selected":""%>>아이디</option>		
							<option value="memberName" <%="memberName".equals(searchType)?"selected":""%>>회원명</option>
                        </select>
                   
                    <!-- 회원검색 - 검색어 입력 -->
                       <div id="search-memberId" class="form-group">
                    	<form action="<%=request.getContextPath()%>/admin/member/memberFinder">
                      		<input type="hidden" name="searchType" value="memberId"/>
                      		<input type="text" name="searchKeyword"  class="form-control" 
                      		value="<%="memberId".equals(searchType)?searchKeyword:""%>"
                      		placeholder="아이디를 입력하세요">
                    		<button type="submit" class="btn btn-default">검색</button>
                    	</form>
                    </div>
                    
                     <div id="search-memberName" class="form-group">
                     	<form action="<%=request.getContextPath()%>/admin/member/memberFinder">
                     		<input type="hidden" name="searchType" value="memberName"/>
                      		<input type="text" name="searchKeyword" class="form-control" 
                      		value="<%="memberName".equals(searchType)?searchKeyword:""%>"
                      		placeholder="회원명을 입력하세요">
                    		<button type="submit" class="btn btn-default">검색</button>
                     	</form>
                    </div>
                    
                   </div>
                </form>
                
            </div>
            <!-- 한줄 여백 -->
            <div class="row height-45"></div>
             <!-- 회원검색 - 검색결과 서브제목 -->
            <div class="row">
                <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 ">
                    <h4>회원 목록</h4>
                </div>
            </div>
            <!-- 회원검색 - 회원 검색 결과 -->
            <div class="col-md-8 col-sm-6 col-xs-6 col-md-offset-2">
                <table class="table ">
                    <tr>
                        <th>회원아이디</th>
                        <th>이름</th>
                        <th>주소</th>
                        <th>가입일</th>
                        <th>구매내역</th>
                        <th>삭제</th>
                    </tr>
                    <tbody>
                    <% if(list==null || list.isEmpty()){ %>
            <tr>
                <td colspan="9" align="center"> 조회된 회원이 없습니다. </td>
            </tr>
        <% 
            } 
            else {
                for(Member m : list){ 
        %>
       
        
        <tr>
        	<td><%=m.getMemberId()%></td>
        	<td><%=m.getMemberName()%></td>
        	<td><%=m.getMemberAddress()%></td>
        	<td><%=m.getMemberEnrollDate()%></td>

        	<td><button type="button" class="btn btn-primary btn-sm" onclick="orderListSearch('<%=m.getMemberId() %>')" value='<%=m.getMemberId() %>'>조회</button></td>
        	<td>
            	<form action="<%=request.getContextPath()%>/admin/member/memberDelete?memberId=<%=m.getMemberId()%>"
            	onsubmit="return confirmDelete();">
					<input type="hidden" name="memberId" value=<%=m.getMemberId()%> />
					<input class="btn btn-danger btn-sm" type="submit" value="삭제" />
				</form>
        	</td>
            
        	
        </tr>

        <%		} 
            }
        %>
      	</tbody>
                    
    	</table>
               
                
     	<div id="pageBar" class="col-md-6 col-sm-6 col-xs-6 col-md-offset-3 text-center">
     		<ul class="pagination">
				<%=pageBar %>
			</ul>
		</div>
    </div>
</div>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>
