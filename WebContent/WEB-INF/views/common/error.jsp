<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage='true' %>
    <%-- 지시어의 속성 isErrorPage="true"를 지정하면, 해당 페이지에서는 exception객체에 선언없이 접근할 수 있다. --%>
<% 
	// http status error code로 에러페이지에 오게되면,
	// exception객체는 null이다.
	int statusCode = response.getStatus();
	System.out.println("statusCode@error.jsp="+statusCode);
	
	//예외가 던져지거나, httpStatusCode값으로 넘어온 경우
	//모두를 처리하는 메세지변수 : exception객체의 null 여부를 판단한다.
	String msg = exception==null?String.valueOf(statusCode):exception.getMessage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom Error Page</title>
<style>
body{
	text-align: center;
}
h1{
	color: red;
	font-size: 50px;
	/* margin: 0 auto; */
	text-align: center;
}
</style>

</head>
<body>
	<h1>오류 : <%=msg %></h1>
	<p>관리자에게 문의하세요.</p>
	<a href="<%=request.getContextPath()%>">
		<img src="<%=request.getContextPath() %>/images/home.png" width="30px" />
	</a>
</body>
</html>