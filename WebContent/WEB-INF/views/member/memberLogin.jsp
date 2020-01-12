<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%@ page import="member.model.vo.*" %>

<% 
   		//로그인한 경우
   		Member memberloggedIn= (Member)session.getAttribute("memberLoggedIn");
   		//쿠기관련
   		boolean saveId = false;
		String memberId = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			System.out.println("----------------------------");
			for(Cookie c : cookies){
				String k = c.getName();
				String v = c.getValue();
				System.out.println(k + " = " + v);
				if("saveId".equals(k)){
					saveId = true;
					memberId = v;
				}
				
			}
			
			System.out.println("----------------------------");
		}
   %>
<div class="container-fluid line-style text-center contents none-nav form-header">
    <p>로그인</p>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6 content-wrapper">
            <h2 class="sr-only">로그인</h2>
            <!-- 로그인 폼 -->
            <section class="form-wrapper">
             <%if(memberLoggedIn==null){ %>
                <form action="<%=request.getContextPath() %>/member/memberLoginEnd " method="post" id="loginFrm">
                    <div class="text-center">
                        <input type="text" name="memberId" id="memberId" value="<%=saveId?memberId:""%>" placeholder="아이디를 입력해주세요" required><br/>
                        <input type="password" name="memberPwd" id="memberPwd" placeholder="비밀번호를 입력해주세요" required>
                    </div>
                    <div class="check-wrapper text-center">
                        <input type="checkbox" 
									   name="saveId" 
									   id="saveId" 
									   <%=saveId?"checked":""%>/>
                        <label for="loginChk">아이디 저장</label>
                    </div>
                    <div class="btnForm-wrapper text-center center-block">
                        <button type="submit" class="btn-radius btn-login">로그인</button><br>
                        <button type="button" class="btn-radius btn-signup">회원가입</button>
                    </div>
                    <%}
                    else{
                    %>
                    <li>
						<%=memberLoggedIn.getMemberName() %>님, 안녕하세요.
                    
                    </li>                    
                    <li>
							<input type="button" value="로그아웃" 
									class="login-show"
								   onclick="location.href='<%=request.getContextPath()%>/member/logout'"/>             
                    </li>
				
					<% 	} %>
                </form>
            </section>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>