<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
Member m = (Member)request.getAttribute("member");
%>
<div class="container-fluid line-style text-center contents none-nav form-header">
    <p>회원탈퇴</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6 content-wrapper">
            <h2 class="sr-only">회원탈퇴</h2>
            <!-- 탈퇴 전 비밀번호 확인 폼 -->
            <section class="form-wrapper">
                <p class="text-center">회원탈퇴는 비밀번호 확인 후 가능합니다.</p>
                <form action="<%=request.getContextPath()%>/member/memberDeleteEnd" id="leaveFrm">
                    <div class="text-center">
                        <input type="password" name="memberPwd" id="memberPwd" placeholder="비밀번호를 입력해주세요" required>
                    	<input type="hidden" name="memberId" id="memberId" value="<%= m.getMemberId() %>" readonly required>
                    </div>
                    <div class="btnForm-wrapper text-center center-block">
                    	<button type="submit" class="btn-radius btn-leave" onclick="confirmDelete();">회원탈퇴</button>
                    </div>
                </form> 
            </section>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

<script>

function confirmDelete(){
	
	var pwd_chk = $("#memberPwdChk").val().trim(); 
	
	console.log("<%=m.getMemberPassword()%>",pwd_chk);
	

	if("<%=m.getMemberPassword()%>"!=pwd_chk){
		alert("비밀번호가 틀렸습니다.");
		return false;
	}
	else{
	    var bool = confirm("정말 탈퇴하시겠습니까?");
    	if(bool){
	        location.href = "<%=request.getContextPath() %>/member/memberDeleteEnd?memberId=<%=m.getMemberId()%> ";
    	}
	} 
}
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>