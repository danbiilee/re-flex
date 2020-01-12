<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>


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
        <li>1:1문의등록</li>
    </ul>
</nav>

<div class="container-fluid contents">
	<div class="row">
	    <div class="col-md-1"></div>
	    <div class="col-md-10 content-wrapper">
            <h2 class="sr-only">이용후기등록</h2>
            <!-- 이용후기 폼 -->
            <section id="reviewFrm-wrapper">
                <form action="<%=request.getContextPath()%>/board/boardFormEnd" id="oneToOneFrm"  method="post" enctype="multipart/form-data">
                    <div>
                        <label for="review-star">별점</label>
                        <select name="star" id="review-star">
                            <option value="1">1점</option>
                            <option value="2">2점</option>
                            <option value="3">3점</option>
                            <option value="4">4점</option>
                            <option value="5" selected>5점</option>
                        </select>
                    </div>
                    <div class="qContent-wrapper">
                        <label for="r-content">내용</label>
                        <textarea name="reviewContent"  id="r-content" cols="50" rows="10" required></textarea>
                    </div>
                    <div class="file-wrapper">
                        <label for="up-file">첨부파일</label>
                        <input type="file" name="upFile" id="upFile">
                    </div>
                    <div class="memberId-wrapper">
                        <label for="memberId">아이디</label>
                        <input type="text" name="reviewWriter" id="reviewWriter"  value="<%=memberLoggedIn.getMemberId() %>" readonly>
                    </div>
                    <div class="btnForm-wrapper text-center">
                        <button type="button" class="btn-radius">취소</button>
                        <button type="submit" class="btn-radius" onclick="return boardValidate();" >등록</button>
                    </div>
                </form>
            </section>
        </div>
	    <div class="col-md-1"></div>
	</div>
</div>
<script>
function boardValidate(){
	//제목검사

	
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