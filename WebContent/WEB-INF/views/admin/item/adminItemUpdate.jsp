<%@page import="java.util.List"%>
<%@page import="item.model.vo.ItemImage"%>
<%@page import="item.model.vo.Item"%>
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
    span#fname1, span#fname2, span#fname3, span#fname4, span#fname5 {
		position:absolute;
		right: 170px;
		top: 16px;
		width: 200px;
		background-color: #fff;
	}
</style>

<% 
	Item i = (Item)request.getAttribute("item");
	List<ItemImage> list = (List<ItemImage>)request.getAttribute("imgList");
	
	//관심사처리
	String category = i.getCategoryNo()!=null?i.getCategoryNo():"";
	
%>

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
        <li>상품 정보 수정</li>
    </ul>
</nav>

    <!-- 메인 컨텐츠 -->
            <div class="container-fluid contents">
                <!-- 상품수정 - 메뉴제목 -->
                <div class="row itemTitle">
                    <div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 side-border">
                        <h3>상품 정보 수정</h3>
                    </div>
                </div>

                <!-- 빈줄 하나 -->
                <div class="row height-45"></div>

                <!-- 상품수정 폼 -->
                <form action="<%=request.getContextPath()%>/admin/itemUpdateEnd" class="p-0 col-md-8 col-sm-8 col-xs-8 col-md-offset-2" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="itemNo" value="<%=i.getItemNo() %>"/>
                    <input type="hidden" name="category_" value="<%=i.getCategoryNo() %>"/>
                    <!-- 상품수정 - 카테고리 -->
                    <div class="container-fluid" id="category">
                        <div class="subtitle border-all height-45" >
                            <h4>카테고리</h4>
                        </div>
                        <div class="container-fluid border-no-top">
                            <label class="radio-inline col-md-4 m-0 padding-left-25">
                                <input type="radio" name="category" id="inlineCheckbox1" value="CT01" <%="CT01".equals(i.getCategoryNo())?"checked":"" %> disabled> 반려동물과 함께할 때
                            </label>
                        
                            <label class="radio-inline col-md-4 m-0 padding-left-25 border-side">
                                <input type="radio" name="category" id="inlineCheckbox2" value="CT02" <%="CT02".equals(i.getCategoryNo())?"checked":"" %> disabled> 육아할 때
                            </label>

                            <label class="radio-inline col-md-4 m-0 padding-left-25">
                                <input type="radio" name="category" id="inlineCheckbox3" value="CT03" <%="CT03".equals(i.getCategoryNo())?"checked":"" %> disabled> 행사, 파티할 때
                            </label>
                        
                            <label class="radio-inline col-md-4 m-0 padding-left-25 border-top">
                                <input type="radio" name="category" id="inlineCheckbox1" value="CT04" <%="CT04".equals(i.getCategoryNo())?"checked":"" %> disabled> 운동할 때
                            </label>

                            <label class="radio-inline col-md-4 m-0 padding-left-25 border-side border-top">
                                <input type="radio" name="category" id="inlineCheckbox2" value="CT05" <%="CT05".equals(i.getCategoryNo())?"checked":"" %> disabled> 여행갈 때
                            </label>

                            <label class="radio-inline col-md-4 m-0 padding-left-25 border-top">
                                <input type="radio" name="category" id="inlineCheckbox3" value="CT06" <%="CT06".equals(i.getCategoryNo())?"checked":"" %> disabled> 캠핑갈 때
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
                                <input type="text" class="form-control shadow-none no-round-border height-45" id="inputItemBrand" name="itemBrand" placeholder="브랜드를 입력하세요" value=<%=i.getItemBrand() %>>
                            </div>
                    </div>

                    <div class="row height-45 border-side"></div>

                    <!-- 상품수정 - 상품명 -->
                    <div class="container-fluid" id="item-name">
                            <div class="subtitle border-all height-45" >
                                <h4>상품명</h4>
                            </div>
                            <div class="container-fluid border-no-top height-45">
                                <input type="text" class="form-control shadow-none no-round-border height-45" id="inputItemName" name="itemName" placeholder="상품명을 입력하세요" value=<%=i.getItemName() %>>
                            </div>
                    </div>

                    <div class="row height-45 border-side"></div>

                    <!-- 상품수정 - 상품가격 -->
                    <div class="container-fluid" id="item-name">
                        <div class="subtitle border-all height-45" >
                            <h4>상품가격</h4>
                        </div>
                        <div class="form-group border-no-top height-45 no-margin">
                            <div class="input-group height-45">
                                <div class="input-group-addon no-round-border"><div class="glyphicon glyphicon-usd"></div></div>
                                <input type="text" class="form-control shadow-none no-round-border height-45" id="exampleInputAmount" name="itemPrice" placeholder="상품가격을 입력하세요" value=<%=i.getItemPrice() %>>
                                <div class="input-group-addon no-round-border no-margin">원</div>
                            </div>
                        </div>
                    </div>

                    <div class="row height-45 border-side"></div>
                    
                    <!-- 상품수정 - 상품수량 -->
                    <div class="container-fluid" id="item-name">
                        <div class="subtitle border-all height-45" >
                            <h4>상품수량</h4>
                        </div>
                        <div class="container-fluid border-no-top height-45">
                            <input type="number" class="form-control shadow-none no-round-border height-45" id="inputItemCount" name="itemStock" placeholder="상품수량을 입력하세요" value=<%=i.getItemStock() %> Readonly>
                        </div>
                    </div>
                    
                    <div class="row height-45 border-side"></div>
                    
                    <!-- 상품등록 - 상품 상세 설명-->
                    <div class="container-fluid" id="item-name">
                        <div class="subtitle border-all height-45" >
                            <h4>상세 설명</h4>
                        </div>
                        <div class="container-fluid border-no-top">
                            <textarea class="form-control shadow-none no-round-border " rows="5" id="inputItemDetail" name="itemDesc" name="itemDesc" placeholder="상품 상세 설명을 입력하세요"><%=i.getItemDesc() %></textarea>
                        </div>
                    </div>
                    
                    <div class="row height-45 border-side"></div>

                    <!-- 상품등록 - 이미지등록 -->
                    <div class="container-fluid" id="item-name">
                        <div class="subtitle border-all height-45" >
                            <h4>이미지등록</h4>
                        </div>
                        <div class="row container-fluid border-no-top" style="position:relative;">
                            <div class="subtitle col-md-6">대표이미지</div>
                            <input type="file" class="shadow-none no-round-border height-45 col-md-6" id="inputItemImageMain" name="inputItemImageMain">
                            <span id="fname1"><%=list.get(0).getItemImageDefault()!=null?list.get(0).getItemImageDefault():""%></span>
                        	<input type="hidden" name="oldOriginalFileNameMain" value="<%=list.get(0).getItemImageDefault() != null?list.get(0).getItemImageDefault():"" %>" />
							<input type="hidden" name="oldRenamedFileNameMain" value="<%=list.get(0).getItemImageRenamed() != null?list.get(0).getItemImageRenamed():"" %>" />
                        	<input type="hidden" name="mainImg" value=<%= list.get(0).getItemImageNo() %> />
                        </div>
                        <div class="row container-fluid border-no-top" style="position:relative;">
                            <div class="subtitle col-md-6">서브이미지1</div>
                            <input type="file" class="shadow-none no-round-border height-45 col-md-6" id="inputItemImageSub1" name="inputItemImageSub1">
                            <span id="fname2"><%=list.get(1).getItemImageDefault()!=null?list.get(1).getItemImageDefault():""%></span>
                        	<input type="hidden" name="oldOriginalFileNameSub1" value="<%=list.get(1).getItemImageDefault() != null?list.get(1).getItemImageDefault():"" %>" />
							<input type="hidden" name="oldRenamedFileNameSub1" value="<%=list.get(1).getItemImageRenamed() != null?list.get(1).getItemImageRenamed():"" %>" />
                            <input type="hidden" name="sub1Img" value=<%= list.get(1).getItemImageNo() %> />
                        </div>
						<div class="row container-fluid border-no-top" style="position:relative;">
                            <div class="subtitle col-md-6">서브이미지2</div>
                            <input type="file" class="shadow-none no-round-border height-45 col-md-6" id="inputItemImageSub2" name="inputItemImageSub2">
                            <span id="fname3"><%=list.get(2).getItemImageDefault()!=null?list.get(2).getItemImageDefault():""%></span>
                        	<input type="hidden" name="oldOriginalFileNameSub2" value="<%=list.get(2).getItemImageDefault() != null?list.get(2).getItemImageDefault():"" %>" />
							<input type="hidden" name="oldRenamedFileNameSub2" value="<%=list.get(2).getItemImageRenamed() != null?list.get(2).getItemImageRenamed():"" %>" />
                            <input type="hidden" name="sub2Img" value=<%= list.get(2).getItemImageNo() %> />
                        </div>
                        <div class="row container-fluid border-no-top" style="position:relative;">
                            <div class="subtitle col-md-6">서브이미지3</div>
                            <input type="file" class="shadow-none no-round-border height-45 col-md-6" id="inputItemImageSub3" name="inputItemImageSub3">
                            <span id="fname4"><%=list.get(3).getItemImageDefault()!=null?list.get(3).getItemImageDefault():""%></span>
                        	<input type="hidden" name="oldOriginalFileNameSub3" value="<%=list.get(3).getItemImageDefault() != null?list.get(3).getItemImageDefault():"" %>" />
							<input type="hidden" name="oldRenamedFileNameSub3" value="<%=list.get(3).getItemImageRenamed() != null?list.get(3).getItemImageRenamed():"" %>" />
							<input type="hidden" name="sub3Img" value=<%= list.get(3).getItemImageNo() %> />
                        </div>
                        <div class="row container-fluid border-no-top" style="position:relative;">
                            <div class="subtitle col-md-6">상세이미지</div>
                            <input type="file" class="shadow-none no-round-border height-45 col-md-6" id="inputItemImageDetail" name="inputItemImageDetail">
                            <span id="fname5"><%=list.get(4).getItemImageDefault()!=null?list.get(4).getItemImageDefault():""%></span>
                        	<input type="hidden" name="oldOriginalFileNameDetail" value="<%=list.get(4).getItemImageDefault() != null?list.get(4).getItemImageDefault():"" %>" />
							<input type="hidden" name="oldRenamedFileNameDetail" value="<%=list.get(4).getItemImageRenamed() != null?list.get(4).getItemImageRenamed():"" %>" />
                        	<input type="hidden" name="detailImg" value=<%= list.get(4).getItemImageNo() %> />
                        </div>  
                    </div>

                    <!-- 상품수정 - 제출버튼 -->
                    <div class="container-fluid text-right" id="item-submit">
                        <button type="submit" class="btn btn-default col-md-offset-10 col-md-2">상품 수정</button>
                    </div>

                </form>


            </div>
            
            <script>
/*
 * 기존 파일명 시각화 처리
 */
 $("[name=inputItemImageMain]").change(function(){
	 console.log("@@@@@@@@");
	 //수정페이지에서 파일태그에 파일을 추가한 경우
	 if($(this).val() != ""){
		 $("#fname1").hide();
	 }
	 else{
		 $("#fname1").show();
	 }
 });
 $("[name=inputItemImageSub1]").change(function(){
	 //수정페이지에서 파일태그에 파일을 추가한 경우
	 if($(this).val() != ""){
		 $("#fname2").hide();
	 }
	 else{
		 $("#fname2").show();
	 }
 });
 $("[name=inputItemImageSub2]").change(function(){
	 //수정페이지에서 파일태그에 파일을 추가한 경우
	 if($(this).val() != ""){
		 $("#fname3").hide();
	 }
	 else{
		 $("#fname3").show();
	 }
 });
 $("[name=inputItemImageSub3]").change(function(){
	 //수정페이지에서 파일태그에 파일을 추가한 경우
	 if($(this).val() != ""){
		 $("#fname4").hide();
	 }
	 else{
		 $("#fname4").show();
	 }
 });
 $("[name=inputItemImageDetail]").change(function(){
	 //수정페이지에서 파일태그에 파일을 추가한 경우
	 if($(this).val() != ""){
		 $("#fname5").hide();
	 }
	 else{
		 $("#fname5").show();
	 }
 });
</script>
    

<%@ include file="/WEB-INF/views/common/footer.jsp"%>