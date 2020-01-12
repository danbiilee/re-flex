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
                                    <p id="text">미리 알면 안심되는</p>
                                    <p id="text2"><strong>케어존 & 배상금 기준</strong></p>
                                </div>
                            </div>
                        </div>
                        <!-- 컨텐트 1-->
                        <div id="block1" class="row">
                            <div class="clo-md-6">
                                <p id="text3"><strong>마음 놓고 쓰려면</strong></p>
                                <p id="text4"><strong>케어존 숙지</strong></p>
                                <div class="circle1"></div>
                                <div class="circle2"></div>
                                <div class="circle3"></div>
                                <div class="circle4"></div>
                                <div class="circle5"></div>
                                <div class="circle6"></div>
                                <div id="text5">
                                    <p>리플렉스 케어존은 사용하다 보면 자연스럽게 생기는 <br>
                                    생활 스크래치나 세탁 가능한 오염 등을 포함합니다. <br>
                                    안심하고 즐겨주세요! <br><br><br>단, 심한 훼손·분실 시에는 <strong>배상금*</strong>이 청구될 수 있습니다.</p>
                                </div>
                            </div>
                        </div>
                        <!-- 컨텐트 2-->
                        <div id="block2" class="row">
                            <div class="clo-md-6">
                                <div class="inner">
                                    <p id="text6"><strong>배상금 기준*</strong></p>
                                    <table border="1">
                                        <thead>
                                            <tr>
                                                <th>구분</th>
                                                <th>내용(예시)</th>
                                                <th>고객 배상금</th>
                                            </tr>
                                        </thead>
                                        <!-- tbody>tr>td*3 -->
                                        <tbody>
                                            <tr>
                                                <td rowspan="4" id="first-td">가방</td>
                                                <td>전문 수선으로 복원 가능한 오염 및 스크래치</td>
                                                <td>50,000~200,000원</td>
                                            </tr>
                                            <tr>
                                                <td>부착된 구성품(나사, 지퍼 손잡이, 스터드 등) 분실 또는 손상</td>
                                                <td>30,000~100,000원</td>
                                            </tr>
                                            <tr>
                                                <td>구성품(스트랩, 참 등) 분실</td>
                                                <td>50,000~300,000원</td>
                                            </tr>
                                            <tr>
                                                <td>분실 또는 수선 불가능한 오염(볼펜,화장품) 찢김, 훼손</td>
                                                <td>구매가 * 배상</td>
                                            </tr>
                    
                                            <tr>
                                                <td rowspan="5" id="first-td">시계/ <br> 액세서리</td>
                                                <td>폴리싱이 필요한 정도의 깊은 스크래치</td>
                                                <td>50,000~100,000원</td>
                                            </tr>
                                            <tr>
                                                <td>오브제 손상, 분실</td>
                                                <td>부품 비용 실비 청구</td>
                                            </tr>
                                            <tr>
                                                <td>전문 수선 필요한 밴드 끊어짐</td>
                                                <td>부품 비용 실비 청구</td>
                                            </tr>
                                            <tr>
                                                <td>시계 유리 깨짐, 문자판 훼손, 시계 본체 침수</td>
                                                <td>구매가 * 배상</td>
                                            </tr>
                                            <tr>
                                                <td>분실 또는 수선 불가능한 오염, 찢김, 깨짐, 훼손</td>
                                                <td>구매가 * 배상</td>
                                            </tr>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td rowspan="4" id="first-td">의류</td>
                                                <td>전문 수선 필요한 밴드 끊어짐</td>
                                                <td>20,000원</td>
                                            </tr>
                                            <tr>
                                                <td>부착된 액세서리 또는 원단 손상(울트임, 경미한 찢김 등)</td>
                                                <td>50,000~200,000원</td>
                                            </tr>
                                            <tr>
                                                <td>구성품(벨트,브로치,리본 등) 분실</td>
                                                <td>50,000~200,000원</td>
                                            </tr>
                                            <tr>
                                                <td>분실 또는 세탁/수선 불가능한 오염, 찢김, 훼손</td>
                                                <td>구매가 * 배상</td>
                                            </tr>
                    
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                </div>
                <div class="col-md-1"></div>

                <style>
                    /* 멤버십 */
                    #wrapper{width: 1500px; height:500px; margin: 0 auto; margin-bottom: 50px;}
                    #wrapper>div{height: 100%; background:purple; }
                    #block1{width: 1000px; height:500px; margin: 0 auto; margin-bottom: 250px;}
                    #block1>div{height: 100%; background:white; }
                    #block2{width: 1000px; margin: 0 auto; margin-top:90px; margin-bottom:500px;}
                    #block2>div{height: 100%; background:white; }
                
                    #text{text-align:center; font-size:25px; color:white; margin-top: 150px;}
                    #text2{text-align:center; font-size:45px; color:white; margin-top:20px;}
                    #text3{text-align:center; font-size:25px;}
                    #text4{text-align:center; font-size:30px; color:purple}
                    #text5{text-align:center; font-size:17px; margin-top:60px;}
                    #text6{text-align:center; font-size:30px; margin-bottom:50px; margin-top:100px;}
                
                    .circle1{border: 1px solid; width:200px; height:200px; -webkit-border-radius:100px; -moz-border-radius:100px; margin-top: 50px; margin-left:150px;}
                    .circle2{border: 1px solid; width:200px; height:200px; -webkit-border-radius:100px; -moz-border-radius:100px; margin-top: -200px; margin-left:400px;}
                    .circle3{border: 1px solid; width:200px; height:200px; -webkit-border-radius:100px; -moz-border-radius:100px; margin-top: -200px; margin-left: 650px;}
                    .circle4{border: 1px solid; width:200px; height:200px; -webkit-border-radius:100px; -moz-border-radius:100px; margin-top: 20px; margin-left:150px;}
                    .circle5{border: 1px solid; width:200px; height:200px; -webkit-border-radius:100px; -moz-border-radius:100px; margin-top: -200px; margin-left:400px;}
                    .circle6{border: 1px solid; width:200px; height:200px; -webkit-border-radius:100px; -moz-border-radius:100px; margin-top: -200px; margin-left:650px;}
                    
                    table{width: 80%; margin-left:auto; margin-right:auto;}
                    th{color:purple; text-align: center; font-size: 18px;}
                    #first-td{color:purple; text-align: center; font-size: 18px;}
                    th{width:100px; height:70px;}
                    td{height:70px;}
                </style>
            </div>
    </div>

    <!-- 맨위로 이동 버튼 -->
    <div id="go-to-top" class="btn-bottom">
        <button type="button" id="btn-gotop" class="center-block">맨 위로 이동</button>
    </div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>