<%@page import="item.model.vo.Item"%>
<%@page import="item.model.vo.ItemImage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="member.model.vo.*" %>
<%@ page import="member.model.service.*" %>
<%@ page import="java.util.*" %>
<%
	String memberId = "";
	if (memberLoggedIn == null) {
		memberId = " ";
	} else {
		memberId = memberLoggedIn.getMemberId();	
	}

	List<Cart> cartList = (List<Cart>)request.getAttribute("cartList");
	List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap");
	
	//총 가격 먼저 구해두기: 배송비 결정
	int rentPriceEa = 0; //priceByRentOptNo*수량
	int totalPrice = 0; //총 가격
	
	if (cartList != null && cartList.size() > 0) {
		for(int i=0; i<cartList.size(); i++){
			Cart cart = cartList.get(i);
	    	rentPriceEa = cart.getPriceByRentOptNo() * cart.getItemQuantity(); //가격*수량
			totalPrice += rentPriceEa; //총가격
		} 
	}
%>
<script src="<%=request.getContextPath()%>/js/memberCart.js"></script>
<script>


document.addEventListener('DOMContentLoaded', function(){
	plusShipPrice(); //배송비 td 추가하기
	showTotalPrice(); //전체상품 가격보기
	chkAll(); //체크박스 전체선택
	
	//선택주문 버튼 클릭한 경우
	$("#btn-orderChk").click(function(){
		let noArr = new Array(); //선택중인 카트번호 담을 배열
		
		for (let i = 1; i < $('table tr').size(); i++) {
			let chk = $('table tr').eq(i).children().find('input[type="checkbox"]').is(':checked');
	
			if (chk == true) {
				let cartNo = $('table tr').eq(i).find('input[name="cartNo"]').val();
				noArr.push(cartNo);
			}
		}
		
		location.href="<%=request.getContextPath()%>/order/orderManyChk?memberId=<%=memberId%>&cartNo="+noArr;
	});
	
	//전체주문 버튼 클릭한 경우
	$("#btn-orderAll").click(function(){
	   
	   location.href="<%=request.getContextPath()%>/order/orderManyAll?memberId=<%=memberId%>";
	});
});
function plusShipPrice(){
	let fisrtCartRow = document.querySelector(".cartRow:first-of-type");
	
	//총 가격이 50000원 이상이면 배송비 무료
	if(<%=totalPrice%>>50000)
		$(fisrtCartRow).append("<td rowspan='<%=cartList.size()%>' id='cart-shipPrice'>무료</td>");
	//50000원 미만이면 배송비 4000원
	else
		$(fisrtCartRow).append("<td rowspan='<%=cartList.size()%>' id='cart-shipPrice'>4,000원</td>");
}
function showTotalPrice(){
	let dpEaArr = document.querySelectorAll(".dpEa"); //주문상품금액들
	let shipPrice = document.querySelector("#cart-shipPrice").innerText; //배송비
	let sum = 0; //총 가격
	
	//바뀌게 될 전체상품 가격보기 변수들
	let totalDpEa = document.querySelector("#total-dpEa"); //상품금액
	let totalShipPrice = document.querySelector("#total-shipPrice"); //배송비
	let totalPrice = document.querySelector("#total-price"); //결제예상금액
	
	dpEaArr.forEach(function(obj, idx){
		let price = obj.innerText;
		
		price = price.replace(",", "").replace("원", "")*1;
		console.log(price);
		sum += price;
	});
	
	totalDpEa.innerText = sum.toLocaleString()+"원"; //상품금액 변경
	
	if(shipPrice==="4,000원"){
		totalShipPrice.innerText = shipPrice; //배송비 변경
		
		shipPrice = shipPrice.replace(",", "").replace("원", "")*1;
		sum += shipPrice;
	}
	else{
		totalShipPrice.innerText = "0원"; //배송비 변경
	}
	
	totalPrice.innerText = sum.toLocaleString()+"원"; //결제예상금액 변경
}

function chkAll(){
	let btnChkAll = document.querySelector(".btn-chkAll");
	let chkboxArr = document.querySelectorAll(".item-chk>input");
	
	//전체선택 버튼 클릭
	btnChkAll.addEventListener("click", function(){
		chkboxArr.forEach(function(obj, idx){
			obj.checked = true;
		});	
	});
}
</script>
<!-- page nav -->
<nav class="line-style page-nav">
    <ul class="list-unstyled list-inline">
        <li class="go-home">
            <a href="<%=request.getContextPath()%>/index.jsp">메인</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li>장바구니</li>
    </ul>
</nav>

<!-- 장바구니 -->
<div class="container-fluid contents">
    <div class="row">
        <div class="col-md-1"></div>
        <%
        	if (cartList != null && cartList.size() > 0) {
        %>
        <div class="col-md-10 content-wrapper">
            <h2 class="sr-only">장바구니</h2>
            <!-- 장바구니 목록 -->
	        <section class="cart-wrapper">
	            <h3 class="sr-only">장바구니 목록 보기</h3>
	            <table class="text-center list-tbl">
	                <colgroup>
	                    <col width="5%">
	                    <col width="50%">
	                    <col width="15%">
	                    <col width="10%">
	                    <col width="10%">
	                    <col width="10%">
	                </colgroup>
	                <thead>
	                    <tr>
	                        <th class="text-center item-chk">
	                            <input type="checkbox" name="delAllCartlist" id="th_checkAll" onclick="checkAll();"/>
	                        </th>
	                        <th class="text-center">상품정보</th>
	                        <th class="text-center">수량</th>
	                        <th class="text-center">기간</th>
	                        <th class="text-center">주문상품금액</th>
	                        <th class="text-center">배송비</th>
	                    </tr>
	                </thead>
	                <tbody>
					<%
						for (int i=0; i<cartList.size(); i++) {
							Cart cart = cartList.get(i);
							Item item = cart.getItem();
							List<ItemImage> imgList = imgMap.get(itemNoList.get(i));
							
							//렌탈기간
							int rentPeriod = 0;
							if("RT01".equals(cart.getRentOptNo())) rentPeriod = 7;
							else if("RT02".equals(cart.getRentOptNo())) rentPeriod = 14;
							else rentPeriod = 30;
							
							//가격 , 붙이기
							DecimalFormat dc = new DecimalFormat("###,###,###,###원");
							String pbyRentOptNo = dc.format(cart.getPriceByRentOptNo()); //1개가격
							String pEa = dc.format(cart.getPriceByRentOptNo()*cart.getItemQuantity()); // *수량
					%>
					
	                    <tr class ="cartRow">
	                        <td class="item-chk">
	                            <input type="checkbox" name="delCartlist" id="<%=item.getItemNo() %>" value="<%=cart.getPriceByRentOptNo() * cart.getItemQuantity() %>"/>
	                        </td>
	                        <td class="item-info">
	                            <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=item.getCategoryNo()%>&itemNo=<%=item.getItemNo()%>">
	                            	<img src="<%=request.getContextPath()%>/images/<%=item.getCategoryNo()%>/<%=imgList.get(0).getItemImageRenamed()%>" class="pull-left" alt="상품이미지">
	                            </a>
	                            <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=item.getCategoryNo()%>&itemNo=<%=item.getItemNo()%>">
	                                <p class="text-left pbrand"><%=item.getItemBrand() %></p>
	                                <p class="text-left pname"><%=item.getItemName() %></p>
	                            </a>
	                            <p class="text-left price">렌탈료 <span class="em-price"><%=pbyRentOptNo%></span>/<%=rentPeriod%>일</p>
	                        </td>
	                        <td>
	                            <div id="sel-amount">
	                                <label for="" class="sr-only">구매수량</label>
	                                <button type="button" class="btn-minus-<%=cart.getCartNo() %>"><span id="btn-minus" class="glyphicon glyphicon-minus" aria-hidden="true"></span></button>
	                                <input type="text" name="orderNo" id="orderNo" class="text-center" value="<%=cart.getItemQuantity() %>" disabled />
	                                <button type="button" class="btn-plus"><span id="btn-plus" class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	                            </div>
 	                            <button type="button" class="btn-radius btn-change">변경</button>
	                        </td>
	                        <td><%=rentPeriod%>일</td>
	                        <td class="dpEa"><%=pEa%></td>
	                        <input type="hidden" name="cartNo" class="hidden-cartNo" value="<%=cart.getCartNo() %>"/>
	                    </tr>
					<%
						} //end of for
					%>
	                </tbody>
	            </table>
	        </section>
        	<!-- 장바구니 선택옵션 -->
	        <section class="itemChk-wrapper">
	            <h3 class="sr-only">장바구니 선택옵션</h3>
	            <p>선택상품을</p>
	            <button type="button" class="btn-radius btn-chkDel">삭제하기</button>
	            <div class="btnChkAll-wrapper pull-right">
	                <button type="button" class="btn-radius btn-chkAll" >전체선택</button>
	                <button type="button" class="btn-radius btn-chkAllDel">전체삭제</button>
					<form action="<%=request.getContextPath()%>/member/memberCartDeleteAll" id="delAllFrm" method="post">
	                	<input type="hidden" name="memberId" value="<%=memberId%>" class="input-cartNo"/>
	                </form>
	            </div>
	        </section>
	        <!-- 장바구니 선택/전체상품 가격보기 -->
            <section id="cartPrice-wrapper" class="row">
                <h3 class="sr-only">장바구니 선택/전체상품 가격보기</h3>
                <div class="col-md-3">
                    <p>장바구니 상품 <span class="strong em-blue"><%=cartList.size() %></span>개의</p>
                </div>
                <div class="col-md-9">
                    <ul class="list-unstyled list-inline row">
                        <li class="col-md-4 text-center cartPrice-inner">
                            <p>상품금액</p>
                            <p id="total-dpEa" class="strong price">0</p>
                            <span class="symbol">+</span>
                        </li>
                        <li class="col-md-4 text-center cartPrice-inner">
                            <p>배송비</p>
                            <p id="total-shipPrice" class="strong price">0</p>
                            <span class="symbol">=</span>
                        </li>
                        <li class="col-md-4 text-center cartPrice-inner">
                            <p>결제예상금액</p>
                            <p id="total-price" class="strong em-pink price">0</p>
                        </li>
                    </ul>
                </div>
                <!-- 주문하기 버튼 -->
                <div class="col-md-12 btnOrder-wrapper text-center">
                    <button type="button" id="btn-orderChk" class="btn-radius">선택주문</button>
                    <button type="button" id="btn-orderAll" class="btn-radius">전체주문</button>
                </div>
            </section>
        </div>
        <%
			} //end of if
			else{
        %>
        	<div id="warning-wrapper" class="col-md-10 content-wrapper text-center">
				<p><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>등록된 상품이 없습니다.</p> 
			</div>
        <%
			}
        %>
        <div class="col-md-1"></div>
    </div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>