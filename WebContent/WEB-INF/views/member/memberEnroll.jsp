<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(()=> {
	//비밀번호 일치 검사
	$('#memberPwdChk').blur(function(){
		var $pwd1 = $("#memberPwd");
		var $pwd2 = $("#memberPwdChk");
		
		if($pwd1.val() != $pwd2.val()){
			alert("패스워드가 일치하지 않습니다.");
			$pwd1.val('').focus();
			$pwd2.val('');
		}
	});
	
	//아이디 중복검사 이후 아이디를 변경한 경우
	//change이벤트는 blur할 경우 값의 변경내역을 감지한다.
	$("#memberId").change(function (){
		$("#idValid").val(0);
	});
	
	//체크박스 개수 2개로 제한
	$("input[type='checkbox']").on("click" , function(){
		var count = $("input:checked[type='checkbox']").length;
		if(count > 2){
			$(this).attr("checked" , false);
			alert("2개까지만 선택할 수 있습니다.");
		}
	});
});

//회원가입 유효성 검사 함수
function enrollValidate(){
	var $memberId = $("#memberId");
	
	if($memberId.val().trim().length < 4){
		alert("아이디는 4글자 이상 가능합니다.");
		return false;
	}
	
	var $idValid = $("#idValid");
	
	console.log($idValid);
	
	if($idValid.val() == 0){
		alert("아이디 중복검사를 눌러주세요.");
		return false;
	}
	
	return true;
};

//아이디 중복 검사 함수 : 팝업창 처리
function checkIdDuplicate(){
	var $memberId = $("#memberId");
	if($memberId.val().trim().length < 4){
		alert("아이디는 4글자 이상 가능합니다.");
		return;
	}
	
	//폼을 팝업창에 제출
	//frm.target = 팝업창이름
	var url = "<%=request.getContextPath()%>/member/memberIdCheckDuplicate";
	var title = "checkIdDuplicatePopup";
	var spec = "left=500px, top=100px, width=300px, height=200px;";
	var popup = open("", title, spec);
	
	var frm = document.checkIdDuplicateFrm;
	frm.action = url;
	frm.target = title;
	frm.method = "post";
	frm.memberId.value = $memberId.val().trim();
	frm.submit();
	
};

//우편번호 검색
function Postcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
	}).open();
}
</script>

<!-- 아이디 중복검사폼 -->
<form name="checkIdDuplicateFrm">
	<input type="hidden" name="memberId" />
</form>
	
<div class="container-fluid line-style text-center contents none-nav form-header">
    <p>회원가입</p>
</div>

<div class="container-fluid">
	<div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6 content-wrapper">
			<h2 class="sr-only">회원가입</h2>
            <!-- 회원가입 폼 -->
          	<section class="form-wrapper">
            	<form action="<%=request.getContextPath()%>/member/memberEnrollEnd" name="memberEnrollFrm" id="signUpFrm" method="post" onsubmit="return enrollValidate();">
	                <div class="row">
	                    <label for="memberName" class="col-md-3">이름<span class="req">*</span></label>
	                    <input type="text" name="memberName" id="memberName" placeholder="이름을 입력해주세요" required>
	                </div>
	                <div class="row">
	                    <label for="memberId" class="col-md-3">아이디<span class="req">*</span></label>
	                    <input type="text" name="memberId" id="memberId" placeholder="아이디를 입력해주세요" required>
	                    <button type="button" id="btn-idcheck" class="btn-radius" onclick="checkIdDuplicate();">아이디 중복검사</button>
	                    <input type="hidden" id="idValid" value="0" />
	                </div>
	                <div class="row">
	                    <label for="memberPwd" class="col-md-3">비밀번호<span class="req">*</span></label>
	                    <input type="password" name="memberPwd" id="memberPwd" placeholder="비밀번호를 입력해주세요" required>
	                </div>
	                <div class="row">
	                    <label for="memberPwdChk" class="col-md-3">비밀번호 확인<span class="req">*</span></label>
	                    <input type="password" name="memberPwdChk" id="memberPwdChk" placeholder="비밀번호를 확인해주세요" required>
	                </div>
	                <div class="row">
	                    <label for="tel1" class="col-md-3">연락처<span class="req">*</span></label>
	                    <select name="tel1" id="tel1">
	                        <option value="010">010</option>
	                        <option value="011">011</option>
	                        <option value="016">016</option>
	                        <option value="017">017</option>
	                        <option value="018">018</option>
	                        <option value="019">019</option>
	                        <option value="070">070</option>
	                    </select>
	                    <input type="text" name="tel2" id="tel2" class="phone" placeholder="'-'제외하고 입력해주세요" required>
	                </div>
	                <div class="row">
	                    <label for="memberEmail" class="col-md-3">이메일<span class="req">*</span></label>
	                    <input type="email" name="memberEmail" id="memberEmail" placeholder="이메일을 입력해주세요" required>
	                </div>
	                <div class="row">
	                    <label for="postcode" class="col-md-3">주소<span class="req">*</span></label>
	                    <input type="text" name="memberPostcode" id="postcode" placeholder="우편번호" required readonly>
	                    <button type="button" id="btn-postcode" class="btn-radius" onclick="Postcode()">우편번호 찾기</button><br>
	                    <input type="text" name="memberAddress1" id="address" placeholder="주소" required readonly><br>
	                    <input type="text" name="memberAddress2" id="detailAddress" placeholder="상세주소" required>
	                  </div>
	                  <div class="hobby-wrapper">
	                      <p class="col-md-3">관심사<span class="req">*최대 2개</span></p>
	                      <div class="hobby-inner">
	                          <input type="checkbox" name="hobby" id="hobby1" value="육아">
	                          <label for="hobby1">육아</label>
	                          <input type="checkbox" name="hobby" id="hobby2" value="캠핑">
	                          <label for="hobby2">캠핑</label>
	                          <input type="checkbox" name="hobby" id="hobby3" value="여행">
	                          <label for="hobby3">여행</label>
	                      </div>
	                      <div class="hobby-inner">
	                          <input type="checkbox" name="hobby" id="hobby4" value="행사">
	                          <label for="hobby4">행사</label>
	                          <input type="checkbox" name="hobby" id="hobby5" value="운동">
	                          <label for="hobby5">운동</label>
	                          <input type="checkbox" name="hobby" id="hobby6" value="반려동물">
	                          <label for="hobby6">반려동물</label>
	                      </div>
	                  </div>
	                  <div class="btnForm-wrapper text-center btn-bottom">
	                      <button type="submit" class="btn-radius">가입하기</button>
	                  </div>
				</form>
			</section>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
    
<%@ include file="/WEB-INF/views/common/footer.jsp" %>