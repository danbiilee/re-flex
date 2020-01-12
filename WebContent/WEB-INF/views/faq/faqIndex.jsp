<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
    /* 멤버십 */
    .content-wrapper{border-bottom: 1px solid;}
    #wrapper{height:500px; border:1px solid; margin-bottom: 100px;}
    #wrapper>div{ height: 100%; border: 1px solid; background:lightgray;}
    .inner{width: 75%; height: 30%; background: purple; margin-top: 50px;}
    #text{text-align:center; font-size:25px; color:white;}
    #text2{text-align:center; font-size:20px; color:white;}
    #text3{text-align:center; font-size: 20px; margin-top: 15px;}
    #text4{text-align:center; font-size: 15px; color: white;}
    a{text-align:center; font-size: 15px; color: white;}
    #ticket{text-align:center; margin: 20px;}
    .inner2{width: 80%; height: 13%; background: gray; margin-top: 50px;}
    #faq{text-align:center; margin: 20px;}

    /* 자주하는 질문 */
    #wrapper2{width: 1100px; margin: 0 auto;  margin-bottom: 100px;}
    #wrapper2>div{height: 568px; border: 1px solid; background:whitesmoke;}
    #faq1{text-align: center; margin-top: 70px; font-size:23px;}
    #faq2{text-align: center; margin-top: 70px; font-size:20px; color:purple}
    #faq3{text-align: center; font-size:16px;  margin-top:10px;}
    #hr{border:0; height:2px; background-color: lightgray;}
    .inner3{width: 80%; height: 13%; background: purple; margin-top: 50px;}
    
    #block2{margin-bottom: 100px;}
    #text5{text-align:center; font-size: 17px; color: white; padding-top:20px; padding-bottom: 20px;}
    .inner4{width: 450px; height: 100px; margin: 0 auto; background: purple;}
</style>

<div class="container-fluid contents none-nav">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10 content-wrapper">
                <h1 id="ticket">RE:FLEX Membership</h1>
                <h4 id="ticket">리플렉스를 즐기는 3가지 방법</h4>
                <!-- 1 -->
                <div id="wrapper" class="row">
                        <div class="col-md-4">
                            <div class="inner center-block">
                                <br>
                                <br>
                                <p id="text"><strong>PREMIUM 정기이용권</strong></p>
                                <p id="text2">월 79,000원</p>
                            </div>
                            <br>
                            <p id="text3">* 정기결제시 월1회 무료 교환 <br>
                                (이후, 추가 10,000원/회)</p>
                            <p id="text3"><strong>* 프리미엄 상품만 렌트 가능</strong></p>
                            <div class="inner2 center-block" >
                                <p id="text4">
                                <br>
                                    프리미엄 상품 보러가기>
                                </p>
                            </div>
                        </div>
                        <!-- 2 -->
                        <div class="col-md-4">
                            <div class="inner center-block">
                                <br>
                                <br>
                                <p id="text"><strong>LIGHT 정기이용권</strong></p>
                                <p id="text2">월 79,000원</p>
                            </div>
                            <br>
                            <p id="text3">* 정기결제시 월1회 무료 교환 <br>
                                (이후, 추가 10,000원/회)</p>
                            <p id="text3"><strong>* 라이트 상품만 렌트 가능</strong></p>
                            <div class="inner2 center-block" >
                                <p id="text4">
                                <br>
                                    라이트 상품 보러가기>
                                </p>
                            </div>
                        </div>
                        <!-- 3 -->
                        <div class="col-md-4">
                            <div class="inner center-block">
                                <br>
                                <br>
                                <p id="text"><strong>단기 이용권</strong></p>
                                <p id="text2">일 4,900원~</p>
                            </div>
                            <br>
                            <p id="text3">* 정기결제시 월1회 무료 교환 <br>
                                (이후, 추가 10,000원/회)</p>
                            <p id="text3"><strong>* 프리미엄 상품만 렌트 가능</strong></p>
                            <div class="inner2 center-block" >
                                <p id="text4">
                                <br>
                                    전체 상품 보러가기>
                                </p>
                                
                            </div>
                        </div>
                </div>
                <!-- 자주묻는 질문 -->
                <!-- <div id="view-list2" class="container-fluid contents"></div> -->
                <h1 id="faq">FAQ</h1>
                <h4 id="faq">자주 묻는 질문</h4>
                <div id="wrapper2" class="row">
                        <!-- 1 -->
                        <div class="col-md-6">
                            <p id="faq1"><strong> 01 <br>
                            "반납은 어떻게 하나요?"</strong></p>
                            <hr id="hr">
                            <p id="faq2"> 상품을 받으셨던 주소로 택배 기사님이 방문합니다. </p>
                            <p id="faq3">방문 예정일은 정기이용 상품의 경우 반납 신청일 익일,<br>
                            단기전용 상품은 렌트 기간 종료일 익일입니다. <br>
                            만약 다른 장소에서 반납하기를 원하신다면 고객센터로 알려주세요. <br>
                            고객센터(1588-1234), 카카오톡(ID: 리플렉스렌트) </p>
                            <div class="inner3 center-block" >
                                    <p id="text4">
                                    <br>
                                    <a href="<%=request.getContextPath()%>/faq/faqReturn">배송/반납 상세보기></a>
                                    </p>
                                </div>
                        </div>
                        <!-- 2 -->
                        <div class="col-md-6">
                            <p id="faq1"><strong> 02 <br>
                            "진짜 스크래치 나도 괜찮아요?"</strong></p>
                            <hr id="hr">
                            <p id="faq2"> 네, 생활 스크래치는 괜찮습니다. </p>
                            <p id="faq3">원래 애정을 가질수록 흔적이 남는 법이죠. 일상적으로 쓰다 보면 생기는,<br>
                            눈에 잘 띄지 않는 작은 흠집이나 살짝 긁힌 정도는 괜찮아요. <br>
                            하지만 대충 봐도 보일 만한 크기의 가죽 손상이나 이염, 변형, <br>
                            찢김 등이 발생하면 감정 후 적정한 복구 비용이 청구될 수 있습니다.</p>
                            <div class="inner3 center-block" >
                                    <p id="text4">
                                    <br>
                                    <a href="<%=request.getContextPath()%>/faq/faqRep">배상금 기준 상세보기></a>
                                    </p>
                                </div>
                        </div>
                        <br>
                        <!-- 3 -->
                        <div class="col-md-6">
                            <p id="faq1"><strong> 03 <br>
                            "제가 원하는 상품은 계속 렌트 중이에요"</strong></p>
                            <hr id="hr">
                            <p id="faq2"> 입고 알림 신청해보세요. </p>
                            <p id="faq3">원하시는 상품이 렌트 가능한 상태가 되는 즉시<br>
                            카톡과 앱 푸시를 통해 알려드려요.<br>
                            알림을 받고 난 후 누구보다 빠르게 GET하세요! </p>
                            <div class="inner3 center-block" >
                                    <p id="text4">
                                    <br>
                                        입고알림 상세보기>
                                    </p>
                                </div>
                        </div>
                        <!-- 4 -->
                        <div class="col-md-6">
                            <p id="faq1"><strong> 04 <br>
                            "부득이하게 연체시 비용을 얼마나 내야하나요?"</strong></p>
                            <hr id="hr">
                            <p id="faq2"> 연체료는 1일 이용료에 따라 다릅니다. </p>
                            <p id="faq3">연체일 X일 단기 렌트 이용료가 연체료로 부과됩니다.<br>
                            모두의 원활한 서비스 이용을 위해 반납일은 꼭 준수해주세요. :) </p>
                            <div class="inner3 center-block" >
                                    <p id="text4">
                                    <br>
                                        1:1 문의 바로가기>
                                    </p>
                                </div>
                        </div>
                </div>
                <!-- 컨텐트 2-->
                <div id="block2">
                    <div class="inner4 text-center center-block">
                    <p id="text5"><strong> <a href="<%=request.getContextPath()%>/faq/faqBoard">더 자세한 이야기는? <br>
                        FAQ 게시판에서 확인하세요! </a> </strong></p>
                    </div>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
    

<!-- 맨위로 이동 버튼 -->
<div id="go-to-top" class="btn-bottom">
    <button type="button" id="btn-gotop" class="center-block">맨 위로 이동</button>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>