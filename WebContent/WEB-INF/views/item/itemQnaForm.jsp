<%@page import="item.model.vo.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String categoryNo = (String)request.getAttribute("categoryNo");
	Item item = (Item)request.getAttribute("item");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
$(function(){
	$("#btn-enrollQna").click(function(){
		let $content = $("[name=qnaContent]");
		
		//유효성검사
		if($content.val().trim().length===0){
			alert("문의내용을 입력해 주세요!");
			return;
		}
		
		$("[name=qnaFrm]").submit();
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
            <a href="<%=request.getContextPath()%>/common/boxMenu?level1=when">이럴 때 빌려봐</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <% 
        	if("CT01".equals(categoryNo)){
        %>
        	<li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=<%=categoryNo%>">반려동물과 함께 할 때</a><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
        <%
        	}
        	if("CT02".equals(categoryNo)){
        %>
        	<li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=<%=categoryNo%>">육아할 때</a><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
        <%
        	}
        	if("CT03".equals(categoryNo)){
        %>
        	<li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=<%=categoryNo%>">파티할 때</a><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
        <%
        	}
        	if("CT04".equals(categoryNo)){
        %>
        	<li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=<%=categoryNo%>">운동할 때</a><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
        <%
        	}
        	if("CT05".equals(categoryNo)){
        %>
        	<li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=<%=categoryNo%>">여행갈 때</a><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
        <%
        	}
        	if("CT06".equals(categoryNo)){
        %>
        	<li><a href="<%=request.getContextPath()%>/item/itemList?categoryNo=<%=categoryNo%>">캠핑갈 때</a><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
        <%
        	}
        %>
        <li><a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=categoryNo%>&itemNo=<%=item.getItemNo()%>"><%=item.getItemName()%></a><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
        <li>상품Q&A작성</li>
    </ul>
</nav>

<div class="container-fluid contents">
	<div class="row">
	    <div class="col-md-1"></div>
	    <div class="col-md-10 content-wrapper">
	        <h2 class="sr-only">상품Q&A작성</h2>
	        <!-- 상품Q&A작성 폼 -->
	        <section id="qnaForm">
	            <form action="<%=request.getContextPath()%>/item/itemQnaFormEnd" name="qnaFrm" method="post" id="oneToOneFrm">
	            	<input type="hidden" name="categoryNo" value="<%=categoryNo%>" />
	            	<input type="hidden" name="itemNo" value="<%=item.getItemNo()%>" />
	                <div class="qContent-wrapper">
	                    <label for="q-content">문의내용</label>
	                    <textarea name="qnaContent" id="q-content" cols="50" rows="10" required></textarea>
	                </div>
	                <div class="memberId-wrapper">
	                    <label for="memberId">문의자아이디</label>
	                    <input type="text" name="memberId" id="memberId" value="<%=memberLoggedIn.getMemberId()%>" readonly>
	                </div>
	                <div class="btnForm-wrapper text-center">
	                    <button type="reset" class="btn-radius">취소</button>
	                    <button type="submit" id="btn-enrollQna" class="btn-radius">등록</button>
	                </div>
	            </form>
	        </section>
	    </div>
	    <div class="col-md-1"></div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>