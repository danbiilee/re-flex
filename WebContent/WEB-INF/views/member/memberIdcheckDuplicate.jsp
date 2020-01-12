<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean isUsable = (boolean)request.getAttribute("isUsable");
	String memberId = request.getParameter("memberId");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 검사</title>
<script src="<%=request.getContextPath() %>/js/jquery-3.4.1.js"></script>
<style>
	#checkId-container{
		text-align: center;
		padding-top: 50px;
	}
	span#duplicated{
		color: red;
		font-weight: bold;
	}
</style>
<script>
	//아이디 중복 검사 함수 : 팝업창 처리
	function checkIdDuplicate(){
		var $memberId = $("#memberId_");
		if($memberId.val().trim().length < 4){
			alert("아이디는 4글자 이상 가능합니다.");
			return;
		}
		
		//폼을 팝업창에 제출
		//frm.target = 팝업창이름
		var url = "<%=request.getContextPath()%>/member/checkIdDuplicate";
		
		var frm = document.checkIdDuplicateFrm;
		frm.memberId.value = $memberId.val().trim();
		frm.submit();
	}
	
	function setMemberId(){
		var memberId = "<%=memberId%>";
		console.log(memberId);
		//부모창의 폼 접근
		//memberEnroll.jsp의 form이름이 memberEnrollFrm에 접근
		var frm = opener.memberEnrollFrm;
		frm.memberId.value = memberId;
		frm.idValid.value = 1;
		
		frm.memberPwd.focus();
		
		self.close();
	}
</script>
</head>
<body>
	<div id="checkId-container">
	<% if(isUsable){%>
		[<span><%=memberId %></span>]는 사용가능합니다.
		<br /><br />
		<button type="button" onclick="setMemberId()">사용</button>
	<% }
	   else{
	%>
		[<span id="duplicated"><%=memberId %></span>]는 이미 사용중입니다.
		<br /><br />
	
		<form action="<%=request.getContextPath()%>/member/memberIdCheckDuplicate" name="checkIdDuplicateFrm" method="post">
			<input type="text" name="memberId" id="memberId_" placeholder="아이디를 입력하세요."/>
			&nbsp;&nbsp;
			<input type="button" value="아이디 중복 검사" onclick="checkIdDuplicate();"/>
		</form>
	<% } %>
	</div>
</body>
</html>