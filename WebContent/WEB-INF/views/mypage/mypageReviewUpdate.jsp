<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="board.model.vo.*" %>

<% Board b= (Board)request.getAttribute("board"); %>

<!-- page nav -->
<nav class="line-style page-nav">
    <ul class="list-unstyled list-inline">
        <li class="go-home">
            <a href="<%=request.getContextPath()%>/index.jsp">메인</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li class="go-boxmenu">
            <a href="<%=request.getContextPath()%>/common/boxMenu?level1=mypage">마이페이지</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li class="go-boxmenu">
            <a href="<%=request.getContextPath()%>/mypage/mypageReview">이용후기내역</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li>이용후기 수정</li>
    </ul>
</nav>

<div class="container-fluid contents">
	<div class="row">
	    <div class="col-md-1"></div>
	    <div id="reivewUpdate-wrapper" class="col-md-10 content-wrapper">
            <h2 class="sr-only">이용후기 수정</h2>
            <!-- 이용후기 폼 -->
            <section id="reviewFrm-wrapper">
                <form action="<%=request.getContextPath()%>/board/boardUpdateForm" id="oneToOneFrm"  method="post" enctype="multipart/form-data">
                <input type="hidden" name="reviewNo" value="<%=b.getReview_no() %>" />
                <input type="hidden" name="order_details_no" value="<%=b.getOrder_details_no() %>" />
                 <input type="hidden" name="orderDetailNo" value="<%=b.getOrder_details_no() %>" />
                   <input type="hidden" name="itemNo" value="<%=b.getItem_no() %>" />
                    <div class="row">
                        <label for="review-star" id="star-label" class="col-md-1">별점</label>
                        <select name="star" id="review-star">
                            <option value="1">1점</option>
                            <option value="2">2점</option>
                            <option value="3">3점</option>
                            <option value="4">4점</option>
                            <option value="5" selected>5점</option>
                        </select>
                    </div>
                    <div class="qContent-wrapper row">
                        <label for="r-content" class="col-md-1">내용</label>
                        <textarea name="reviewContent"  id="r-content" cols="50" rows="10" required>
                        <%=b.getReview_content() %>
                        </textarea>
                    </div>
                    <div id="reivewUpdateFile-wrapper" class="file-wrapper row">
                        <label for="up-file" class="col-md-1">첨부파일</label>
                        <input type="file" name="upFile" id="upFile">
		                    <span id="fname"><%=b.getReview_image()!=null?b.getReview_image():"" %></span>
							
							<input type="hidden" name="oldOriginalFileName"
								   value="<%=b.getReview_image()!=null?b.getReview_image():""%>" />
							<input type="hidden" name="oldRenamedFileName"
								   value="<%=b.getReview_image_rename()!=null?b.getReview_image_rename():""%>" />    
		                        <!-- 기존파일삭제 체크박스-->
							<% if(b.getReview_image()!=null) {%>
							<br />
							<input type="checkbox" name="delFileChk" id="delFileChk" />
							<label for="delFileChk">첨부파일삭제</label>
					<% } %>
                    </div>
                    <div class="memberId-wrapper row">
                        <label for="memberId" id="id-label" class="col-md-1">아이디</label>
                        <input type="text" name="reviewWriter" id="reviewWriter"  value="<%=memberLoggedIn.getMemberId() %>" readonly>
                    </div>
                    <div class="btnForm-wrapper text-center">
                        <button type="button" class="btn-radius" onclick="exit()" >취소</button>
                        <button type="submit" class="btn-radius" onclick="return boardValidate();" >수정</button>
                    </div>
                </form>
            </section>
        </div>
	    <div class="col-md-1"></div>
	</div>
</div>
<script>
function exit() {
	location.href = "<%=request.getContextPath()%>/mypage/mypageReview";	
}
function boardValidate(){

	
	//내용검사
	var $content = $("[name=reviewContent]");
	if($content.val().trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>