<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
    .itemTitle{
        height: 46px;
        font-weight: bold;
    }
    .height-45{
        height: 45px;
    }
    .m-0{
        margin: 0px !important;
    }
    .padding-left-25{
        padding-left: 25px !important;
        padding-top: 13px !important;
        padding-bottom: 13px !important;
    }
    .no-round-border{
        border-radius: 0;
    }
    .no-margin{
        margin: 0;
    }
</style>

<!-- page nav -->
<nav class="line-style page-nav">
    <ul class="list-unstyled list-inline">
        <li class="go-home">
            <a href="<%=request.getContextPath()%>/index.jsp">메인</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li class="go-boxmenu">
            <a href="">관리자페이지</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li> 
        <li>상품등록</li>
    </ul>
</nav>

			<!-- 메인 컨텐츠 -->
            <div class="container-fluid contents">
                <!-- 상품등록 - 메뉴제목 -->
                <div class="row itemTitle">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 side-border">
                        <h3>상품등록</h3>
                    </div>
                </div>

                <!-- 빈줄 하나 -->
                <div class="row height-45"></div>

                <!-- 상품등록 폼 -->
                <form action="<%=request.getContextPath()%>/admin/itemEnrollEnd" class="p-0 col-md-8 col-sm-8 col-xs-8 col-md-offset-2" method="post" enctype="multipart/form-data" id="itemEnroll">
                    <!-- 상품등록 - 카테고리 -->
                    <div class="container-fluid" id="category">
                        <div class="subtitle border-all height-45" >
                            <h4>카테고리</h4>
                        </div>
                        <div class="container-fluid border-no-top">
                            <label class="radio-inline col-md-4 m-0 padding-left-25">
                                <input type="radio" name="category" id="inlineCheckbox1" value="CT01" required> 반려동물과 함께할 때
                            </label>
                        
                            <label class="radio-inline col-md-4 m-0 padding-left-25 border-side">
                                <input type="radio" name="category" id="inlineCheckbox2" value="CT02"> 육아할 때
                            </label>

                            <label class="radio-inline col-md-4 m-0 padding-left-25">
                                <input type="radio" name="category" id="inlineCheckbox3" value="CT03"> 행사, 파티할 때
                            </label>
                        
                            <label class="radio-inline col-md-4 m-0 padding-left-25 border-top">
                                <input type="radio" name="category" id="inlineCheckbox1" value="CT04"> 운동할 때
                            </label>

                            <label class="radio-inline col-md-4 m-0 padding-left-25 border-side border-top">
                                <input type="radio" name="category" id="inlineCheckbox2" value="CT05"> 여행갈 때
                            </label>

                            <label class="radio-inline col-md-4 m-0 padding-left-25 border-top">
                                <input type="radio" name="category" id="inlineCheckbox3" value="CT06"> 캠핑갈 때
                            </label>
                        </div>
                    </div>

                    <div class="row height-45 border-side"></div>

                    <!-- 상품등록 - 브랜드 -->
                    <div class="container-fluid" id="item-name">
                            <div class="subtitle border-all height-45" >
                                <h4>브랜드</h4>
                            </div>
                            <div class="container-fluid border-no-top height-45">
                                <input type="text" class="form-control shadow-none no-round-border height-45" id="inputItemBrand" name="itemBrand" placeholder="브랜드를 입력하세요">
                            </div>
                    </div>

                    <div class="row height-45 border-side"></div>

                    <!-- 상품등록 - 상품명 -->
                    <div class="container-fluid" id="item-name">
                            <div class="subtitle border-all height-45" >
                                <h4>상품명</h4>
                            </div>
                            <div class="container-fluid border-no-top height-45">
                                <input type="text" class="form-control shadow-none no-round-border height-45" id="inputItemName" name="itemName" placeholder="상품명을 입력하세요">
                            </div>
                    </div>

                    <div class="row height-45 border-side"></div>

                    <!-- 상품등록 - 상품가격 -->
                    <div class="container-fluid" id="item-name">
                        <div class="subtitle border-all height-45" >
                            <h4>상품가격</h4>
                        </div>
                        <div class="form-group border-no-top height-45 no-margin">
                            <div class="input-group height-45">
                                <div class="input-group-addon no-round-border"><div class="glyphicon glyphicon-usd"></div></div>
                                <input type="text" class="form-control shadow-none no-round-border height-45" id="exampleInputAmount" name="itemPrice" placeholder="상품가격을 입력하세요">
                                <div class="input-group-addon no-round-border no-margin">원</div>
                            </div>
                        </div>
                    </div>

                    <div class="row height-45 border-side"></div>
                    
                    <!-- 상품등록 - 상품수량 -->
                    <div class="container-fluid" id="item-name">
                        <div class="subtitle border-all height-45" >
                            <h4>상품수량</h4>
                        </div>
                        <div class="container-fluid border-no-top height-45">
                            <input type="number" class="form-control shadow-none no-round-border height-45" id="inputItemStock" name="itemStock" placeholder="상품수량을 입력하세요">
                        </div>
                    </div>
                    
                    <div class="row height-45 border-side"></div>
                    
                    <!-- 상품등록 - 상품 상세 설명-->
                    <div class="container-fluid" id="item-name">
                        <div class="subtitle border-all height-45" >
                            <h4>상세 설명</h4>
                        </div>
                        <div class="container-fluid border-no-top">
                            <textarea class="form-control shadow-none no-round-border " rows="5" id="inputItemDetail" name="itemDesc" placeholder="상품 상세 설명을 입력하세요"></textarea>
                        </div>
                    </div>
                    
                    <div class="row height-45 border-side"></div>

                    <!-- 상품등록 - 이미지등록 -->
                    <div class="container-fluid" id="item-name">
                        <div class="subtitle border-all height-45" >
                            <h4>이미지등록</h4>
                        </div>
                        <div class="row container-fluid border-no-top">
                            <div class="subtitle col-md-6">대표이미지</div>
                            <input type="file" class="shadow-none no-round-border height-45 col-md-6" id="inputItemImageMain" name="inputItemImageMain" required>
                        </div>
                        <div class="row container-fluid border-no-top">
                            <div class="subtitle col-md-6">서브이미지1</div>
                            <input type="file" class="shadow-none no-round-border height-45 col-md-6" id="inputItemImageSub1" name="inputItemImageSub1" required>
                        </div>
						<div class="row container-fluid border-no-top">
                            <div class="subtitle col-md-6">서브이미지2</div>
                            <input type="file" class="shadow-none no-round-border height-45 col-md-6" id="inputItemImageSub2" name="inputItemImageSub2" required>
                        </div>
                        <div class="row container-fluid border-no-top">
                            <div class="subtitle col-md-6">서브이미지3</div>
                            <input type="file" class="shadow-none no-round-border height-45 col-md-6" id="inputItemImageSub3" name="inputItemImageSub3" required>
                        </div>
                        <div class="row container-fluid border-no-top">
                            <div class="subtitle col-md-6">상세이미지</div>
                            <input type="file" class="shadow-none no-round-border height-45 col-md-6" id="inputItemImageDetail" name="inputItemImageDetail" required>
                        </div>  
                    </div>

                    <!-- 상품등록 - 제출버튼 -->
                    <div class="container-fluid text-right" id="item-submit">
                        <button type="submit" class="btn btn-default col-md-offset-10 col-md-2">상품 등록</button>
                    </div>
                </form>

            </div>
			<script>
			$(document).ready(function(){
		        $("button[type='submit']").click(function(){
		            var radioValue = $("input[name='category']:checked").val();
		            if(radioValue){
		                document.getElementById("itemEnroll").action = "<%=request.getContextPath()%>/admin/itemEnrollEnd/"+radioValue;
		              	
		            }
		        });
		    });			
			</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>