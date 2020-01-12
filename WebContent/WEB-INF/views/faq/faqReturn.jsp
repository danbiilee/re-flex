<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="container-fluid contents none-nav">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10 content-wrapper">
                        <div id="wrapper" class="row">
                                <br>
                                <!-- 헤더 -->
                                <div class="col-md-12">
                                    <div class="inner center-block">
                                        <p id="text">한눈에 보는 배송 안내</p>
                                        <p id="text2"><strong>편한 배송 쉬운 반납</strong></p>
                                    </div>
                                </div>
                                </div>
                            
                            <!-- 컨텐트 1-->
                            <div class="container-fluid contents"></div>
                            <div id="block1" class="row">
                               <div class="clo-md-6">
                                   <div class="inner">
                                       <p id="box_title1"><strong>처음 받을때?</strong></p>
                                       <p id="box_content1">주문 이후 2~3일 내에 택배 배송됩니다. <br>
                                        (서울 일부 지역은 리본즈에서 직접 배송합니다.)</p>
                                   </div>
                                   <div class="inner">
                                       <p id="box_title2"><strong>교환할 때?</strong></p>
                                       <p id="box_content2">교환 신청일 이후 1~2일 내에. <br>
                                        택배 기사님이 방문해 상품을 수거합니다. <br> 
                                        교환을 신청한 상품은 1~2일 내에 택배 배송됩니다.</p>
                                   </div>
                                   <div class="inner">
                                       <p id="box_title3"><strong>반납할 때?</strong></p>
                                       <li id="box_content3"><strong>프리미엄/라이트 정기 이용권</strong>
                                        <p>홈페이지,고객센터를 통해 신청한 반납일 익일에 <br>
                                        택배 기사님이 방문해 상품을 수거합니다.</p></li>
                        
                                        <li id="box_content3"><strong>단기 이용권</strong>
                                        <p>각 상품별로 정해진 이용 기간 종료일 다음날, <br>
                                        택배 기사님이 방문해 상품을 수거합니다.</p>
                                        </li>
                        
                                   </div>
                               </div>
                            </div>
                                <!-- 컨텐트 2-->
    <div class="container-fluid contents"></div>
    <div id="block2" class="row">
       <div class="clo-md-6">
           <div class="inner">
               <p id="box_title4"><strong>직접 방문 수령</strong></p>
               <p id="box_content4">원하시는 경우, 주문 시 배송 요청사항에 <br>
            '직접 방문 수령'이라고 적어주시면 <br>유선으로 안내해드립니다.</p>
           </div>
           <div class="inner">
               <p id="box_title5"><strong>퀵 서비스 수령</strong></p>
               <p id="box_content5">주문 시 배송 요청사항에 <br>
                '퀵 서비스 수령'이라고 적어주시거나 <br>고객 센터를 통해 요청해주세요.</p>
                <div class="inner2 center-block">
                    <p id="text3">고객 센터 안내:1588-1234/ 퀵 서비스 수령은 서울만 가능하며 출고 비용은 고객 부담입니다(일부 상품 제외)</p>
                </div>
            </div>
    </div>
                    </div>

        <style>
                /* 멤버십 */
                #wrapper{width: 1500px; height:500px; margin: 0 auto; margin-bottom: 150px;}
                #wrapper>div{height: 100%; background:gray; }
                #block1{width: 1000px; height:500px; margin: 0 auto; margin-bottom: 150px;}
                #block1>div{height: 100%; border: 1px solid; background:white; }
                #block2{width: 1000px; height:350px; margin: 0 auto; margin-bottom: 150px;}
                #block2>div{height: 100%; border: 1px solid; background:white; }
            
                #text{text-align:center; font-size:25px; color:white; margin-top: 150px;}
                #text2{text-align:center; font-size:45px; color:white; margin-top:20px;}
                #text3{text-align:center; font-size:15px; color:white;}
            
                #box_title1{text-align:left; font-size:20px;  margin-top:85px; margin-left:50px;}
                #box_content1{text-align:left; font-size:17px; margin-top:10px; margin-left: 50px;}
                #box_title2{text-align:left; font-size:20px;  margin-top:80px; margin-left:50px;}
                #box_content2{text-align:left; font-size:17px; margin-top:10px; margin-left: 50px;}
                #box_title3{text-align:right; font-size:20px;  margin-top:-275px; margin-right:350px;}
                #box_content3{text-align:left; font-size:17px; margin-top:10px; margin-left: 550px;}
                #box_title4{text-align:center; font-size:20px;  margin-top: 150px; margin-right:450px;}
                #box_content4{text-align:center; font-size:17px; margin-top:10px; margin-right:450px;}
                #box_title5{text-align:center; font-size:20px; margin-top: -110px; margin-left:400px;}
                #box_content5{text-align: center; font-size:17px; margin-top:10px; margin-left: 400px;}
            
                .inner2{width: 100%; height: 50px; background: gray; margin-top: 44px;}
                
                </style>
    <!-- 맨위로 이동 버튼 -->
    <div id="go-to-top" class="btn-bottom">
        <button type="button" id="btn-gotop" class="center-block">맨 위로 이동</button>
    </div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>