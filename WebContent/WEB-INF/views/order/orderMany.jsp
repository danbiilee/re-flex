<%@page import="java.util.Map"%>
<%@page import="item.model.vo.ItemImage"%>
<%@page import="java.util.List"%>
<%@page import="item.model.vo.Item"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member m = (Member)request.getAttribute("m");
	List<Cart> cartList = (List<Cart>)request.getAttribute("cartList"); //memberCart.jsp에서 넘어오는 리스트
	List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap");
	int usablePoint = (int)request.getAttribute("usablePoint"); //사용가능한 포인트 
	
	//총 가격 먼저 구해두기: 배송비 결정
	int totalPrice = 0; //총 가격
	
	if (cartList != null && !cartList.isEmpty()) {
		for(int i=0; i<cartList.size(); i++){
			Cart cart = cartList.get(i);
			totalPrice += cart.getPriceByRentOptNo() * cart.getItemQuantity(); //총가격
		} 
	}
	
	//핸드폰 번호 - 추가
	String tel = m.getMemberPhone();
	String tel1 = tel.substring(0, 3);
	String tel2 = tel.substring(3, 7);
	String tel3 = tel.substring(7);
	tel = tel1+"-"+tel2+"-"+tel3;
	
	//보유포인트 , 추가
	DecimalFormat dc = new DecimalFormat("###,###,###,###원");
	String uPoint = dc.format(usablePoint);
	
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script src="<%=request.getContextPath()%>/js/itemOrder.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function(){
	let btnGoPay = document.querySelector("#btn-goPay"); 
	
	//주문하기 버튼 클릭
	btnGoPay.addEventListener('click', function(){
		let userTotalPrice = document.querySelector("#userTotalPrice").innerText.replace(",", "")*1; //총 결제금액
		let userPoint = document.querySelector("#userPoint").innerText.replace(",", "")*1; //총 사용포인트
		
		let $radioChk = $("input[type=radio]:checked").val();
		//결제수단 선택 유효성검사
		if($radioChk===undefined){
			alert("결제수단을 선택해주세요.");
			return;
		}
		
		//아임포트 변수 초기화
		let IMP = window.IMP;
		IMP.init('imp74518584');
		
		let hiddenItemNoArr = document.querySelectorAll(".hidden-itemNo");
		let hiddenRentOptArr = document.querySelectorAll(".hidden-rentOpt");
		let hiddenQuantityArr = document.querySelectorAll(".hidden-quantity");
		let itemNoArr = new Array();
		let rentOptArr = new Array();
		let quantityArr = new Array();
		
		hiddenItemNoArr.forEach(function(obj, idx){
			itemNoArr[idx] = obj.value;
			rentOptArr[idx] = hiddenRentOptArr[idx].value;
			quantityArr[idx] = hiddenQuantityArr[idx].value;
		});
		
		if($radioChk==="card"){
			IMP.request_pay({
				pg : 'inicis', 
				pay_method : 'card',
				merchant_uid : 'reflex' + new Date().getTime(),
				name : '<%=cartList.get(0).getItem().getItemName()%> 외...',
				amount : userTotalPrice,
				buyer_email : '<%=m.getMemberEmail()%>',
				buyer_name : '<%=m.getMemberName()%>',
				buyer_tel : '<%=tel%>',
				buyer_addr : '<%=m.getMemberAddress()%>',
				buyer_postcode : '<%=m.getMemberPostcode()%>'
			}, function(rsp) {
				//결제 성공 시
				if ( rsp.success ) {
					$.ajax({
						url: "<%=request.getContextPath()%>/order/manyPaymentsComplete",
						type: "post",
						data: {
							merchant_uid: rsp.merchant_uid,
							imp_uid: rsp.imp_uid,
							memberId: "<%=m.getMemberId()%>",
							payMethod: "card",
							totalItemEa: <%=cartList.size()%>,
							totalPrice: userTotalPrice,
							usePoint: userPoint,
							itemNo: itemNoArr.join(","),
							rentType: rentOptArr.join(","),
							ea: quantityArr.join(",")
						},
						dataType: "json"
					}).done(function(data){
						var msg = '결제가 완료되었습니다.\n';
						msg += '고유ID : ' + rsp.imp_uid+"\n";
						msg += '상점 거래ID : ' + rsp.merchant_uid+"\n";
						msg += '결제 금액 : ' + rsp.paid_amount+"\n";
						msg += '카드 승인번호 : ' + rsp.apply_num+"\n";
						alert(msg);
					});
					//성공 시 이동
					location.href="<%=request.getContextPath()%>/order/orderSuccess?orderNo="+rsp.merchant_uid;
				} 
				//결제 실패 시
				else {
					var msg = '결제에 실패하였습니다.\n';
					msg += '에러내용 : ' + rsp.error_msg;
					alert(msg);
					
					//실패 시 이동
					location.href="<%=request.getContextPath()%>/order/orderFail";
				}
			}); //end of ajax
		} //end of card click
		
		//계좌이체 요청
		if($radioChk==="trans"){
			IMP.request_pay({
				pg : 'inicis', 
				pay_method : 'trans',
				merchant_uid : 'reflex' + new Date().getTime(),
				name : '<%=cartList.get(0).getItem().getItemName()%> 외...',
				amount : userTotalPrice,
				buyer_email : '<%=m.getMemberEmail()%>',
				buyer_name : '<%=m.getMemberName()%>',
				buyer_tel : '<%=tel%>',
				buyer_addr : '<%=m.getMemberAddress()%>',
				buyer_postcode : '<%=m.getMemberPostcode()%>'
			}, function(rsp) {
				//결제 성공 시
				if ( rsp.success ) {
					$.ajax({
						url: "<%=request.getContextPath()%>/order/manyPaymentsComplete",
						type: "post",
						data: {
							merchant_uid: rsp.merchant_uid,
							imp_uid: rsp.imp_uid,
							memberId: "<%=m.getMemberId()%>",
							payMethod: "card",
							totalItemEa: <%=cartList.size()%>,
							totalPrice: userTotalPrice,
							usePoint: userPoint,
							itemNo: itemNoArr.join(","),
							rentType: rentOptArr.join(","),
							ea: quantityArr.join(",")
						},
						dataType: "json"
					}).done(function(data){
						var msg = '결제가 완료되었습니다.\n';
						msg += '고유ID : ' + rsp.imp_uid+"\n";
						msg += '상점 거래ID : ' + rsp.merchant_uid+"\n";
						msg += '결제 금액 : ' + rsp.paid_amount+"\n";
						msg += '카드 승인번호 : ' + rsp.apply_num+"\n";
						alert(msg);
					});
					//성공 시 이동
					location.href="<%=request.getContextPath()%>/order/orderSuccess?orderNo="+rsp.merchant_uid;
				} 
				//결제 실패 시
				else {
					var msg = '결제에 실패하였습니다.\n';
					msg += '에러내용 : ' + rsp.error_msg;
					alert(msg);
					
					//실패 시 이동
					location.href="<%=request.getContextPath()%>/order/orderFail";
				}
			});
		} //end of trans
		
	}); //end of btnGoPay click
	
	plusShipPrice(); //배송비td 추가하기
	calShipItemPrice(); //배송비, 상품금액 계산
	usePoint(); //포인트 계산
});
function plusShipPrice(){
	let fisrtCartRow = document.querySelector(".cartRow:first-of-type");
	
	//총 가격이 50000원 이상이면 배송비 무료
	if(<%=totalPrice%>>50000)
		$(fisrtCartRow).append("<td rowspan='<%=cartList.size()%>' class='shipPrice'>무료</td>");
	//50000원 미만이면 배송비 4000원
	else
		$(fisrtCartRow).append("<td rowspan='<%=cartList.size()%>' class='shipPrice'>4,000원</td>");
}
</script>
<!-- page nav -->
<nav class="line-style page-nav">
    <ul class="list-unstyled list-inline">
        <li class="go-home">
            <a href="<%=request.getContextPath()%>/index.jsp">메인</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li>주문/결제</li>
    </ul>
</nav>

<!-- 주문서 헤더 -->
<div id="order-header" class="container-fluid line-style text-center contents">
    <h2 class="sr-only">주문서</h2>
    <p class="strong">주문서</p>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <!-- 주문자 정보 -->
            <div id="orderer-header" class="container-fluid line-style text-center">
                <h3 class="sr-only">주문자 정보</h3>
                <p>주문자 정보</p>
            </div>
            <ul id="orderer-content" class="list-inline list-unstyled row text-center">
                <li class="col-md-4">
                    <span class="strong">주문자</span>
                    <span class="dd"><%=m.getMemberName() %></span>
                </li>
                <li class="col-md-4">
                    <span class="strong">연락처</span>
                    <span class="dd"><%=tel%></span>
                </li>
                <li class="col-md-4">
                    <span class="strong">이메일</span>
                    <span class="dd"><%=m.getMemberEmail() %></span>
                </li>
            </ul>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<!-- 결제페이지 - 주문상품 헤더 -->
<div class="container-fluid line-style text-center">
    <h3 class="sr-only">주문상품</h3>
    <p class="strong">주문상품 (<span class="em-blue"><%=cartList.size()%></span>개)</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div id="order-item" class="col-md-10 content-wrapper">
            <!-- 주문상품 내역 -->
            <table class="text-center list-tbl">
                <colgroup>
                    <col width="50%">
                    <col width="10%">
                    <col width="10%">
                    <col width="15%">
                    <col width="15%">
                </colgroup>
                <thead>
                    <tr>
                        <th class="text-center">상품정보</th>
                        <th class="text-center">수량</th>
                        <th class="text-center">기간</th>
                        <th class="text-center">주문상품금액</th>
                        <th class="text-center">배송비</th>
                    </tr>
                </thead>
                <tbody>
                <%
               	if(cartList!=null && !cartList.isEmpty()){
	               	for(int i=0; i<cartList.size(); i++){
	               		Cart cart = cartList.get(i);
	               		Item item = cart.getItem();
	               		List<ItemImage> imgList = imgMap.get(itemNoList.get(i));
	               		
	               		//렌탈기간 구하기
	               		int periodbyCart = 0;
	               	    if("RT01".equals(cart.getRentOptNo())) periodbyCart = 7;
	               	    else if("RT02".equals(cart.getRentOptNo())) periodbyCart = 14;
	               	    else periodbyCart = 30;
	               		
	               		//가격 , 붙이기
						String pbyRentOptNo = dc.format(cart.getPriceByRentOptNo()); //1개가격
						String pEa = dc.format(cart.getPriceByRentOptNo()*cart.getItemQuantity()); // *수량
                %>
                <tr class="cartRow">
                    <td class="item-info">
                        <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=item.getCategoryNo() %>&itemNo=<%=item.getItemNo()%>">
                        	<img src="<%=request.getContextPath() %>/images/<%=item.getCategoryNo()%>/<%=imgList.get(0).getItemImageDefault() %>" class="pull-left" alt="상품 이미지">
                        </a>
                        <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=item.getCategoryNo() %>&itemNo=<%=item.getItemNo()%>">
                            <p class="text-left pbrand"><%=item.getItemBrand() %></p>
                            <p class="text-left pname"><%=item.getItemName() %></p>
                        </a>
                        <p class="text-left price">렌탈료 <span class="em-price"><%=pbyRentOptNo %></span>/<%=periodbyCart %>일</p>
                    </td>
                    <td class="order-no"><%=cart.getItemQuantity() %>개</td>
                    <td><%=periodbyCart %>일</td>
                    <td class="itemPrice"><%=pEa %></td>
                    <input type="hidden" class="hidden-itemNo" value="<%=item.getItemNo()%>" />
                    <input type="hidden" class="hidden-rentOpt" value="<%=cart.getRentOptNo()%>" />
                    <input type="hidden" class="hidden-quantity" value="<%=cart.getItemQuantity()%>" />
                </tr>
                <%
                	}
                }
                %>
                </tbody>
            </table>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<!-- 결제페이지 - 배송지정보 헤더 -->
<div class="container-fluid line-style text-center">
    <h3 class="sr-only">배송지정보</h3>
    <p class="strong">배송지정보</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <!-- 배송지정보 -->
            <section id="order-section" class="form-wrapper">
                <form action="" id="orderFrm">
                    <div class="row">
                        <label for="ordererName" class="col-md-3">수령인</label>
                        <input type="text" name="ordererName" id="ordererName" value="<%=m.getMemberName()%>" readonly>
                    </div>
                    <div class="row">
                        <label for="tel1" class="col-md-3">연락처</label>
                        <select name="tel1" id="tel1">
                            <option value="010" selected>010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="018">018</option>
                            <option value="019">019</option>
                            <option value="070">070</option>
                        </select>
                        <input type="text" name="tel2" id="tel2" class="phone" value="<%=m.getMemberPhone().substring(3)%>" readonly>
                    </div>
                    <div class="row">
                        <label for="postcode" class="col-md-3">주소</label>
                        <input type="text" id="postcode" placeholder="우편번호" value="<%=m.getMemberPostcode()%>" readonly>
                        <input type="button" id="btn-postcode" class="btn-radius" onclick="Postcode()" value="우편번호 찾기"><br>
                        <input type="text" id="address" placeholder="주소" value="<%=m.getMemberAddress()%>" readonly><br>
                        <input type="text" id="detailAddress" placeholder="상세주소" value="<%=m.getMemberDetailAddress()%>" readonly>
                    </div>
                    <div class="row">
                        <label for="msg" class="col-md-3">배송메세지</label>
                        <select name="msg" id="msg">
                            <option value="배송 전에 미리 연락바랍니다.">배송 전에 미리 연락바랍니다.</option>
                            <option value="부재시 경비실에 맡겨주세요">부재시 경비실에 맡겨주세요.</option>
                            <option value="부재시 문 앞에 놓아주세요.">부재시 문 앞에 놓아주세요.</option>
                            <option value="부재시 휴대폰으로 연락바랍니다.">부재시 휴대폰으로 연락바랍니다.</option>
                        </select>
                    </div>
                </form>
            </section>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<!-- 결제페이지 - 금액정보 헤더 -->
<div class="container-fluid line-style text-center">
    <h3 class="sr-only">결제금액</h3>
    <p class="strong">주문시 결제금액</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <!-- 금액정보 -->
            <div id="price-wrapper" class="row price-inner">
                <ul class="list-unstyled col-md-8">
                    <li>
                        <p class="price-text">
                            	주문금액
                            <span class="ship-price">(배송비포함)</span>
                            <span class="strong em-pink ab-right">￦<span id="showPrice"></span></span>
                        </p>
                    </li>
                    <li class="point-wrapper">
                        <span class="point-title">포인트</span>
                        <ul class="list-unstyled point-inner">
                            <li>
                                <span class="have-point">보유포인트</span>
                                <span id="memberHavePoint"><%=uPoint %></span>
                            </li>
                            <li>
                                <span class="use-point">사용포인트</span>
                                <input type="text" id="inputPoint" class="text-right" value="0">
                                <button type="button" id="btn-useAll" class="btn-radius">전액사용</button>
                            </li>
                        </ul>
                        <span id="total-point" class="ab-right em-blue">￦<span id="showUsePoint">0</span></span>
                    </li>
                </ul>
                <!-- 최종 금액정보 -->
                <div class="col-md-4 totalP-inner">
                    <h3 class="sr-only">최종결제금액</h3>
                    <p class="line-style text-center">최종결제 금액확인</p>
                    <ul class="list-unstyled">
                        <li>주문상품<span class="ab-right">￦<span id="userItemPrice"></span></span></li>
                        <li>배송비<span class="ab-right">￦<span id="userShipPrice"></span></span></li>
                        <li>포인트 사용<span class="em-blue ab-right">￦<span id="userPoint">0</span></span></li>
                    </ul>
                    <div id="tt-price" class="line-style">
                        <p>최종결제금액 <span id="ttPrice-inner" class="ab-right em-pink strong">￦<span id="userTotalPrice"></span></span></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 결제페이지 - 결제수단 헤더 -->
<div class="container-fluid line-style text-center">
    <h3 class="sr-only">결제 수단</h3>
    <p class="strong">주문시 결제 수단</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper row">
            <!-- 결제수단 -->
            <div id="payType-wrapper" class="col-md-8">
                <input type="radio" name="payType" id="payType" value="card"> 
                <label for="payType">신용카드</label>
                <input type="radio" name="payType" id="payType" value="trans"> 
                <label for="payType">실시간 계좌이체</label>
            </div>
            <div id="goPay-wrapper" class="col-md-4">
                <button type="button" id="btn-goPay" class="color-reflex">주문하기</button>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>