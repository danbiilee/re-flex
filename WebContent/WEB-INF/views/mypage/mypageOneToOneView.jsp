<%@page import="java.util.*" %>
<%@page import="mypage.model.vo.Qna" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Qna q = (Qna)request.getAttribute("q");

	String ans = (String)request.getAttribute("ans");
%>
<style>
.viewimage{
   display: inline-block;
    margin-left: 112px;
    width: 30%;
}
</style>
<!-- page nav -->
<nav class="line-style page-nav">
    <ul class="list-unstyled list-inline">
        <li class="go-home">
            <a href="<%=request.getContextPath()%>/index.jsp">메인</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li class="go-boxmenu">
            <a href="">마이페이지</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li class="go-boxmenu">
            <a href="<%=request.getContextPath()%>/mypage/mypageOneToOne">1:1문의내역</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li>1:1문의 상세보기</li>
    </ul>
</nav>
<div class="container-fluid contents">
	<div class="row">
	    <div class="col-md-1"></div>
	    <div class="col-md-10 content-wrapper">
	        <h2 class="sr-only">1:1문의등록</h2>
	        <!-- 문의등록 폼 -->
	        <section>
	            <form action="<%=request.getContextPath() %>/mypage/mypageOneToOneUpdate" id="oneToOneFrm" method="post" name="oneToOneFrm">
	                 <input type="hidden" name="qNo" value="<%=q.getqNo()%>" /> 
	                <div class="qSort-wrapper">
	                    <label for="q-sort">문의유형</label>
	                    <select name="q-sort" id="q-sort">
	                    	<%
	                    	switch(q.getqTypeNo()){
	                    	case "QT01": 
	                    	%>
	                        <option value="QT01" <%="QT01".equals(q.getqTypeNo())?"selected":"type='hidden'" %>>상품문의</option>
	                    	<%
	                    		break;
	                    	case "QT02": 
	                    	%>
	                        <option value="QT02" <%="QT02".equals(q.getqTypeNo())?"selected":"type='hidden'" %>>배송문의</option>
	                    	<%	
	                    		break;
	                    	case "QT03":
	                    	%>
	                        <option value="QT03" <%="QT03".equals(q.getqTypeNo())?"selected":"type='hidden'" %>>기타문의</option>
	                    	<%
	                    		break;
	                    	}
	                    	%>
	                    </select>
	                </div>
	                <div class="qTitle-wrapper">
	                    <label for="q-title">문의제목</label>
	                    <input type="text" name="qTitle" id="q-title" value="<%=q.getqTilte() %>" readonly>
	                </div>
	                <div class="qContent-wrapper">
	                    <label for="q-content">문의내용</label>
	                    <textarea name="qContent" id="q-content" cols="50" rows="10" readonly><%=q.getqContent() %></textarea>
	                </div>
	                <div class="file-wrapper">
	                    <label for="up-file">첨부파일</label>
	                    <img src="<%=request.getContextPath()%>/upload/board/<%=q.getqImage() %>" alt="" class="viewimage"/>
	                </div>
	                <div class="memberId-wrapper">
	                    <label for="memberId">문의자아이디</label>
	                    <input type="text" name="memberId" id="memberId" value="<%=memberLoggedIn.getMemberId()%>" value="<%=q.getMemberId() %>" readonly>
	                </div>
	                <div class="aContent-wrapper">
	                    <label for="a-content">문의답변</label>
	                    <textarea name="aContent" id="a-content" cols="50" rows="5" placeholder="답변 대기중입니다." readonly><%=ans%></textarea>
	                </div>
	                <div class="btnForm-wrapper text-center">
	                    <button type="button" class="btn-radius" onclick="return deleteOneToOne();">삭제</button>
	                    <button type="submit" class="btn-radius" onclick="return boardValidate();">수정</button>
	                </div>
	            </form>
	        </section>
	    </div>
	    <script type="text/javascript">
 	    function deleteOneToOne(){
 	    	if(!confirm("삭제하시겠습니까?")){
 	    		return false;
 	    	}
 	    	
 	    	var form = document.oneToOneFrm;
 	    	
 	    	oneToOneFrm.method="post";
 	    	
 	    	oneToOneFrm.action="<%=request.getContextPath()%>/mypage/mypageOneToOneDelete2?qNo=<%=q.getqNo()%>"
 	    	
 	    	oneToOneFrm.submit();
<%-- 	    	location.href="<%=request.getContextPath()%>/mypage/mypageOneToOneDelete?qNo=<%=q.getqNo()%>&qImage='<%=q.getqImage()%>'"; --%>	
	    }
		function boardValidate(){
			$("/mypage/mypageOneToOneUpdate").submit();
		}
		</script>
	    <div class="col-md-1"></div>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>