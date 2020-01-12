<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
    $(()=>{
        $(".faq_content").hide();
        $(".faq").on("click", function(){

            $(this).next().slideToggle();

            console.log("이벤트핸들러 안 : ", this); //클릭한 div
            var $div = $(this);
        });
    });

    function showContent(sectionId){
    let sectionArr = document.querySelectorAll(".faq-container");
    console.log(sectionArr);
    for(let i in sectionArr){
    let section = sectionArr[i];
    if(section.id===sectionId){
        $(sectionArr).removeClass('is-selected');
        $(section).addClass('is-selected');
        }
    }
}
</script>

<div class="container-fluid line-style text-center contents none-nav faqboard">
        <p>자주 묻는 질문</p>
    </div>

    <div class="container-fluid">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10 content-wrapper">
                        <div id="wrapper" class="row">
                                <br>
                                <p id="category">카테고리 선택</p>
                                <br>
                                <ul class="list-inline list-unstyled text-center">
                                    <li data-tab-ref="shopping" class="is-selected"><button type="button" onclick="showContent('shopping');">쇼핑</button></li>
                                    <li data-tab-ref="payment"><button type="button" onclick="showContent('payment');">결제</button></li>
                                    <li data-tab-ref="delivery"><button type="button" onclick="showContent('delivery');">배송</button></li>
                                    <li data-tab-ref="my-account"><button type="button" onclick="showContent('my-account');">나의 계정</button></li>
                                    <li data-tab-ref="sustainability"><button type="button" onclick="showContent('sustainability');">환경</button></li>
                                </ul>
                            

                                    <!-- 쇼핑 -->
                                    <div class="faq-container is-selected" id="shopping">
                                    <div class="shopping">
                                        <p class="text-center">쇼핑</p>                     
                                    </div>
                                    <div class="faq">&nbsp;&nbsp;REFLEX.COM에서 주문
                                        <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                    </div>
                                    <!-- 1 -->
                                    <p class="faq_content">
                                        Balenciaga.com에서 제품을 구매하시려면 가입해주시기 바랍니다. 회원으로 가입하시면, 발렌시아가 고객만을 위한 특별한 페이지 및 서비스를 이용하실 수 있습니다.
                                        <br> 
                                        제품 검색
                                        <br>
                                        보다 간편하게 카탈로그를 살펴보시려면, 탐색 메뉴에서 카테고리를 선택하세요. 특정 제품을 찾고 싶으실 경우, 필터나 웹사이트 내부 검색을 이용하세요.
                                        <br> 
                                        <br>
                                            제품 정보
                                        <br>
                                            각 제품 페이지에서 구매 가능한 모든 사이즈 및 컬러, 제품 설명 및 구성을 확인하실 수 있습니다. 제품 이미지를 클릭하여 확대해 모든 디테일을 확인하세요.
                                        <br>
                                        <br>
                                            주문 방법:
                                        <br>  
                                            – 제품 페이지에서 원하는 컬러와 사이즈를 선택하세요. <br>
                                            – 제품을 장바구니에 추가하세요. <br>
                                            – 쇼핑을 마치면 장바구니를 클릭하세요. <br>
                                            – 배송 방법과 결제 방법을 선택하세요. <br>
                                            – 배송 정보를 입력하고 다음 페이지에서 결제 정보를 입력합니다. <br>
                                            – 정보가 정확하게 입력되었는지 확인하고 “구매하기”를 클릭합니다. <br>
                                        <br>    
                                        <br>
                                            몇 분 후, 주문 내역을 확인할 수 있는 이메일이 발송됩니다. 이후 물류센터가 모든 배송 과정을 담당하며, 주문 제품이 배송된 직후 다시 한번 확인 이메일이 발송됩니다.
                                        Reflex.com에서 제품을 구매하시려면 가입해주시기 바랍니다. 회원으로 가입하시면, 발렌시아가 고객만을 위한 특별한 페이지 및 서비스를 이용하실 수 있습니다.
                                        <br>
                                            제품 검색
                                        <br>
                                            보다 간편하게 카탈로그를 살펴보시려면, 탐색 메뉴에서 카테고리를 선택하세요. 특정 제품을 찾고 싶으실 경우, 필터나 웹사이트 내부 검색을 이용하세요.
                                        <br>
                                        <br>  
                                            제품 정보
                                        <br>
                                            각 제품 페이지에서 구매 가능한 모든 사이즈 및 컬러, 제품 설명 및 구성을 확인하실 수 있습니다. 제품 이미지를 클릭하여 확대해 모든 디테일을 확인하세요.
                                        <br>    
                                        <br>    
                                            주문 방법:
                                        <br>    
                                            – 제품 페이지에서 원하는 컬러와 사이즈를 선택하세요. <br>
                                            – 제품을 장바구니에 추가하세요. <br>
                                            – 쇼핑을 마치면 장바구니를 클릭하세요. <br>
                                            – 배송 방법과 결제 방법을 선택하세요. <br>
                                            – 배송 정보를 입력하고 다음 페이지에서 결제 정보를 입력합니다. <br>
                                            – 정보가 정확하게 입력되었는지 확인하고 “구매하기”를 클릭합니다. <br>
                                        <br>    
                                        <br>    
                                            몇 분 후, 주문 내역을 확인할 수 있는 이메일이 발송됩니다. 이후 물류센터가 모든 배송 과정을 담당하며, 주문 제품이 배송된 직후 다시 한번 확인 이메일이 발송됩니다. <br></p>
                                    <!-- 2 -->
                                    <div class="faq">&nbsp;&nbsp;주문 완료 후 24시간 이상 지났는데 아직까지 주문 확인 메일을 받지 못했습니다. 어떻게 해야 하나요?
                                            <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                    </div>
                                    <p class="faq_content">스팸 폴더를 확인하여 이메일 계정에서 <br>
                                            confirmation@store.reflex.com 및 shipping@store.reflex.com의 이메일 주소를 안전한 발송자로 분류하여 해당 주소에서 발송한 메일을 안전하게 받을 수 있도록 하십시오. <br>
                                            <br>
                                            이렇게 함으로써, 안티 스팸 필터에 의해 이메일 커뮤니케이션이 차단되지 않습니다. 여전히 문제가 발생할 경우, 등록한 이메일 주소에 오류가 있을 수 있으므로 리플렉스로 연락해주시기 바랍니다. <br></p>
                                    <!-- 3 -->
                                    <div class="faq">&nbsp;&nbsp;주문을 변경하고 다른 상품을 추가하거나 삭제할 수 있나요?
                                            <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                    </div>
                                    <p class="faq_content">죄송합니다만 불가능합니다. 각각의 주문은 자동적으로 진행이 되어 일단 주문이 확정되면 세부 내용은 수정이 불가능합니다. 추가로 아이템을 구매하기 원하시면 새로운 주문을 진행하셔야 합니다. 반품을 원하실 경우, 저희 반품정책을 참조해 주세요. <br></p>
                                    <!-- 4 -->
                                    <div class="faq">&nbsp;&nbsp;프리 오더는 무엇이며 어떻게 진행되나요?
                                            <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                    </div>
                                    <p class="faq_content">‘사전 주문’은 아직 판매를 시작하지 않았지만, 곧 판매 예정인 제품을 예약하는 방법입니다. 예상 배송일은 각 사전 주문 아이템의 제품 페이지 내에 명시되어 있습니다. 예상 배송일은 사정에 따라 변경될 수 있습니다. 이 경우, 이메일로 새로운 배송일을 알려드립니다. <br>
                                            <br>
                                            – 현재 구매 가능한 제품과 사전 주문 아이템을 포함한 주문: 제품은 두 번에 걸쳐 배송되지만, 배송비는 한 번만 청구됩니다. <br>
                                            – 서로 다른 배송일이 예상되는 사전 주문 아이템을 포함한 주문: 최신 예상 배송일에 제품이 함께 배송됩니다. <br>
                                            <br>
                                            <br>
                                            모든 경우, 상품이 배송되자마자 확인 이메일이 발송됩니다. <br>
                                            사전 주문 제품은 신용카드로만 구매하실 수 있습니다. 주문을 완료하면, 고객의 은행에 제공된 정보를 확인합니다. 신용카드 유효성은 호주 주문의 경우 2호주 달러, 홍콩 주문의 경우 10홍콩 달러, 다른 모든 국가는 1유로의 지불 인증으로 확인할 수 있으며, 인증은 별도로 비용이 청구되지 않습니다. Balenciaga.com은 상품이 배송되는시점에만 카드 비용을 청구합니다. <br></p>
                                    <!-- 5 -->
                                    <div class="faq">&nbsp;&nbsp;기프트 옵션
                                            <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                    </div>
                                    <p class="faq_content">리플렉스의 모든 제품은 로고 박스에 담겨 발송됩니다. 기프트 옵션을 선택하시면 기프트 영수증이 발송되며, 기프트 메시지를 남기실 수 있습니다호주 주문의 경우 2호주 달러, 홍콩 주문의 경우 10홍콩 달러, 다른 모든 국가는 1유로의 지불 인증으로 확인할 수 있으며, 인증은 별도로 비용이 청구되지 않습니다. Reflex.com은 상품이 배송되는시점에만 카드 비용을 청구합니다. <br></p>
                                    <!-- 6 -->
                                    <div class="faq">&nbsp;&nbsp;쇼핑백에 추가한 아이템이 사라진 이유가 무엇인가요?
                                            <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                    </div>
                                    <p class="faq_content">쇼핑백에 담아둔 아이템일지라도 결제를 완료하기 이전에는 웹사이트에 구매 가능한 상태로 남아 있기 때문에 다른 고객님께서 먼저 구매할 수 있으므로 재고가 소진될 수도 있습니다. 만약 이러한 경우가 발생하면, 고객님께서 선택한 아이템의 재고가 소진되었음을 알려드립니다. <br></p>
                                    <!-- 7 -->
                                    <div class="faq">&nbsp;&nbsp;주문을 취소할 수 있나요?
                                            <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                    </div>
                                    <p class="faq_content">죄송합니다. 주문이 확정된 이후에는 모든 과정이 자동으로 처리되어 취소가 불가합니다. <br>
                                            온라인 반품 정책에 따라 상품을 반품하실 수 있습니다. <br></p>
                                    <!-- 8 -->
                                    <div class="faq">&nbsp;&nbsp;주문현황
                                            <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                    </div>
                                    <p class="faq_content">주문 준비 단계: 주문이 확정되고 주문 번호와 함께 확인 이메일을 이미 발송한 상태입니다. <br>
                                            주문 배송: 창고에서 주문 제품이 배송된 직후 배송을 추적할 수 있는 추적 번호와 함께 확인 이메일을 발송합니다. <br>
                                            <br>
                                            주문 확인 페이지에 주문 번호를 입력하여 언제든지 주문 상태를 확인할 수 있습니다. 회원가입을 완료한 경우 ‘나의 계정’에서 주문과 관련된 모든 정보를 확인할 수 있습니다. <br></p>

                        </div> 
                        </div>

                            <!-- 결제 -->
                            <div class="faq-container" id="payment">
                            <div class="payment">
                                <p class="text-center">결제</p>                     
                            </div>
                            <div class="faq">&nbsp;&nbsp;한 건의 주문에 중복 청구가 되었는데 어떻게 해야 하나요?
                                    <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                </div>
                                <!-- 1 -->
                                <p class="faq_content">
                                        Reflex에서는 고객님의 신용카드로 결제하신 금액을 한 번만 청구합니다. 명세서에 두 번 청구된 것으로 보일 수 있지만 이 경우, 첫 번째는 주문 시 발생한 결제 승인 요청에 해당하는 것이고, 두 번째가 실제 청구 금액입니다. <br></p>
                                <!-- 2 -->
                                <div class="faq">&nbsp;&nbsp;결제 보안
                                        <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                </div>
                                <p class="faq_content">Reflex은(는) Trustwave 및 Geotrust 인증 보안 서버를 이용하고 최첨단 암호화 서비스(SSL)를 구현하여 모든 구매에 대해 최고 수준의 보안을 보장합니다. 결제 시스템은 브라우저 주소 표시줄의 “https” 웹 주소와 잠금 기호로 표시되는 안전한 연결 방법만을 채택하여 사용합니다. <br>
                                        <br>
                                        <br>
                                        안전한 신용카드 구매를 위해 결제 시 CVV 코드를 반드시 입력해야 합니다. 저희 결제 담당 부서에서 거래를 승인하기 전에 사기행각을 방지하기 위한 검사를 수행할 수 있으며, 보안을 더욱 강화하기 위해 주문을 확인하기 전 고객님께 세부 정보를 문의할 수도 있습니다. <br></p>
                                <!-- 3 -->
                                <div class="faq">&nbsp;&nbsp;결제가 되지 않는 이유는 무엇인가요?
                                        <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                </div>
                                <p class="faq_content">선택하신 결제 방법에 대한 세부 정보를 정확하게 입력했는지 확인하세요. <br>
                                        <br>
                                        신용카드 결제 시 계속해서 문제가 발생하는 경우 신용카드 회사에 문의하시기 바랍니다. <br>
                                        <br>
                                        문의사항이 있거나 도움이 필요하시면 연락주시기 바랍니다. <br></p>
                                <!-- 4 -->
                                <div class="faq">&nbsp;&nbsp;신용카드로 결제하면 언제 청구되나요?
                                        <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                </div>
                                <p class="faq_content">주문을 완료하면 신용카드의 유효성과 사용 한도 금액을 확인하기 위해 필요한 결제 승인이 자동으로 요청됩니다. 금액은 이때 결제되는 것이 아니라, 주문 발송 시 결제되고 동시에 고객님께 이메일로 알려드립니다. <br></p>
                            </div>

                            <!-- 배송 -->
                            <div class="faq-container" id="delivery">
                            <div class="delivery">
                                <p class="text-center">배송</p>                     
                            </div>
                            <div class="faq">&nbsp;&nbsp;어디에서 주문 진행 상황을 알 수 있나요?
                                    <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                </div>
                                <!-- 1 -->
                                <p class="faq_content">
                                    Reflex에서는 고객님의 신용카드로 결제하신 금액을 한 번만 청구합니다. 명세서에 두 번 청구된 것으로 보일 수 있지만 이 경우, 첫 번째는 주문 시 발생한 결제 승인 요청에 해당하는 것이고, 두 번째가 실제 청구 금액입니다. <br></p>
                                <!-- 2 -->
                                <div class="faq">&nbsp;&nbsp;주문한 제품이 아직 배송되지 않았는데 어떻게 해야 하나요?
                                        <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                </div>
                                <p class="faq_content">주문한 제품이 아직 배송되지 않았는데 어떻게 해야 하나요? <br>
                                    주문한 제품이 예상 기간 내에 배송되지 않은 경우 다음 사항을 참고하시기 바랍니다. <br>
                                    <br>
                                    <br>
                                    – 주문 현황을 확인하세요. 가입하신 고객님께서는 주문 섹션에서 확인하실 수 있으며, 또한 주문 확인 이메일에 기재된 주문 번호를 나의 주문정보 페이지에 입력하여 확인하실 수 있습니다. <br>
                                    <br>
                                    – 지정된 배송지의 주소가 정확한지 확인하세요. <br>
                                    <br>
                                    – 배송을 놓쳐 받지 못한 경우 (으)로 UPS에 문의하세요. <br>
                                    <br>
                                    <br>
                                    문의사항이 있거나 도움이 필요하시면 연락주시기 바랍니다. <br></p>
                                <!-- 3 -->
                                <div class="faq">&nbsp;&nbsp;배송 제한
                                        <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                </div>
                                <p class="faq_content">유치 우편, 우편 사서함, 우체국 임치제에 대한 배송은 지원하지 않습니다. 이러한 주소로 주문할 경우, 주문은 자동으로 취소됩니다. <br>
                                    <br>
                                    주문의 배송 주소는 반드시 주문이 이루어진 지역과 일치해야 합니다. 배송 주소와 다른 지역에서 이루어진 주문은 자동으로 취소됩니다. 주문하시려면 모든 페이지의 끝부분에 있는 링크에서 해당하는 지역을 바르게 선택해 주세요. <br></p>
                                </div>

                            <!-- 나의 계정  -->
                            <div class="faq-container" id="my-account">
                            <div class="my-account">
                                    <p class="text-center">나의 계정</p>                     
                            </div>
                            <div class="faq">&nbsp;&nbsp;나의 계정에 로그인이 안됩니다. 어떻게 해야 하나요?
                                    <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                </div>
                                <!-- 1 -->
                                <p class="faq_content">
                                    비밀번호를 잊으신 경우 로그인 페이지의 “비밀번호를 잊으셨나요?” 링크를 클릭하여 비밀번호 변경 절차를 진행하시기 바랍니다. <br>
                                    <br>
                                    <br>
                                    문의사항이 있거나 도움이 필요하시면 연락주시기 바랍니다. <br></p>
                                <!-- 2 -->
                                <div class="faq">&nbsp;&nbsp;계정은 어떻게 삭제하나요?
                                        <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                </div>
                                <p class="faq_content">나의 계정의 삭제를 원하시면 저희에게 연락주시기 바랍니다. <br></p>
                            
                            </div>

                            <!-- 환경 -->
                            <div class="faq-container" id="sustainability">
                            <div class="sustainability">
                                <p class="text-center">환경</p>                     
                            </div>
                            <div class="faq">&nbsp;&nbsp;지속 가능한 미래를 위하여
                                    <span class="glyphicon glyphicon-menu-down menudown" aria-hidden="true"></span>
                                </div>
                                <!-- 1 -->
                                <p class="faq_content">
                                    지속가능성에 대한 신념을 바탕으로 우수한 품질의 제품을 제공하고 사업을 운영하고 있는 리플렉스는 좀 더 나은 세상과 지속 가능한 미래를 위한 브랜드의 노력을 반영하는 컬렉션을 창조합니다. <br>
                                    <br>
                                    리플렉스는 지속가능성에 대한 의무감을 가지고, 의식 있는 럭셔리 패션을 추구하는 고객에게 완벽하게 호응할 수 있도록 지속적인 노력을 기울이고 있습니다. <br>
                                    <br>
                                    브랜드의 모회사 케어링과 동일한 비전을 가지고 리플렉스는 다음의 5개 주요 사항을 실천하고 있습니다. <br>
                                    <br>
                                    – 그룹 활동을 통한 전 직원의 지속가능성 지원 기획의 참여 도모 <br>
                                    – 사업 활동과 제품 생산 및 공급 체계에 있어 환경 오염의 최소화 (온실 가스, 수자원, 폐기물) <br>
                                    – 환경에 어떠한 악영향도 미치지 않는 제품의 제공 <br>
                                    – 관련 납품회사의 지속가능성 기획을 지원하여 사회 및 환경에 공헌하도록 유도 <br>
                                    – 천연자원 보호 – 리플렉스의 창조력과 혁신의 원천을 이루는 자연을 위해 생물 다양성 보존과 자연 및 동물 보호 <br>
                                    <br>
                                    저희 브랜드의 지속가능성 지원기획에 대한 자세한 정보는 sustainability@kr.reflex.com으로 문의해주시기 바랍니다. <br></p>
                                
                                </div>
                            </div>

                <div class="col-md-1"></div>

            </div>
    </div>
    <style>
            .faqboard{border-top:0;}
            #category{text-align:center;}
            #wrapper li{text-align:center; margin-left:10px; margin-bottom: 10px;}
            .faq{height:50px; line-height:50px; border-top:1px solid;}
            .faq-container{display:none;}
            .faq-container.is-selected{display: block;}
            .shopping{height:50px; line-height:50px; border-top:1px solid;}
            .payment{height:50px; line-height:50px; border-top:1px solid;}
            .delivery{height:50px; line-height:50px; border-top:1px solid;}
            .my-account{height:50px; line-height:50px; border-top:1px solid;}
            .sustainability{height:50px; line-height:50px; border-top:1px solid;}
            .menudown{float:right; padding-top:15px; margin-right: 20px;};

            </style>

    <!-- 맨위로 이동 버튼 -->
    <div id="go-to-top" class="btn-bottom">
        <button type="button" id="btn-gotop" class="center-block">맨 위로 이동</button>
    </div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>