<%@page import="mypage.model.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Qna q = (Qna)request.getAttribute("q");
%>

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
        <li>1:1문의답변 등록</li>
    </ul>
</nav>
<style>
.viewimage{
   display: inline-block;
    margin-left: 112px;
    width: 30%;
}
</style>
<div class="container-fluid contents">
	<div class="row">
	    <div class="col-md-1"></div>
	    <div class="col-md-10 content-wrapper">
	        <h2 class="sr-only">1:1문의등록</h2>
	        <!-- 문의등록 폼 -->
	        <section>
	            <form action="<%=request.getContextPath() %>/admin/member/qnaFormEnd" id="oneToOneQnaFrm" method="post" >
	                <div class="qSort-wrapper">
	                    <label for="q-sort">문의유형</label>
	                    <span><%=q.getqTypeNo()%></span>
	                </div>
	              	<div>
	              		<span style="margin-right: 63px;">문의자</span>
	              		<span><%=q.getMemberId() %></span>
	              	</div>
	                <div class="qTitle-wrapper">
	            		<label for="q-title">문의제목</label>
	                	<span><%=q.getqTilte()%></span>
	                </div>
	                <div class="qContent-wrapper">
	                    <label for="q-content">문의내용</label>
	                   
	                    <textarea name="qContent" id="q-content" cols="50" rows="10" readonly><%=q.getqContent()%></textarea>
	                 
	                </div>
	            <%--     <div class="file-wrapper">
	                    <label for="up-file">첨부된 파일</label>
	                    
	                    <div style="margin-left: 110px;">
		                    <img src="<%=request.getContextPath()%>/upload/board/<%=q.getqImage()%>" alt="" class="viewimage"/>
	                    </div>
	                    
	                </div> --%>
	                 <div class="file-wrapper">
	                    <label for="up-file">첨부된 파일</label>
	                    <img src="<%=request.getContextPath()%>/upload/board/<%=q.getqImage() %>" alt="" class="viewimage"/>
	                </div>
	                <div class="memberId-wrapper"></div>
	                <div class="aContent-wrapper">
	                    <label for="a-content">문의답변</label>
	                    <textarea name="aContent" id="a-content" cols="50" rows="5" ></textarea>
	                </div>
	                <input type="hidden" value="<%=q.getqNo()%>" name="qNo"/>
                	<div class="btnForm-wrapper text-center">
                    	<button type="button" class="btn-radius" onclick="exit();">취소</button>
                    	<button type="submit" class="btn-radius">등록</button>
                	</div>
	            </form>
	        </section>
	    </div>
	    <script type="text/javascript">
 	    function exit(){
 	    	var bool = confirm("문의조회 페이지로 돌아가시겠습니까?");
 	    	if(bool){
	    		location.href="<%=request.getContextPath()%>/admin/member/memberQna";	
 	    	}
	    }
		/* function qnaValidate(){
			
			//내용검사
			var $content = $("[name=qAnswer]");
			if($content.val().tirm().length == 0){
				alert("문의답변을 입력하세요.");
				return;
			}
			$("/admin/member/qnaFormEnd").submit();
		} */
		</script>
	    <div class="col-md-1"></div>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>