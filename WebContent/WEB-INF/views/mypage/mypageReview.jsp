<%@page import="order.model.vo.OrderDetail3"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="item.model.vo.ItemImage"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="java.util.List"%>
<%@ page import="board.model.vo.*" %>
<%@ page import="order.model.vo.OrderDetail" %>

<%
	List<Board> list = (List<Board>)request.getAttribute("list");
	System.out.println("list="+list);
	List<OrderDetail3> list2= (List<OrderDetail3>)request.getAttribute("list2");
	String pageBar = (String)request.getAttribute("pageBar");
	List<Integer> itemNoList = (List<Integer>)request.getAttribute("itemNoList");
	Map<Integer, List<ItemImage>> imgMap = (Map<Integer, List<ItemImage>>)request.getAttribute("imgMap");
%>

<script>
//탭 누르면 내용 보이기
function showContent(btn, sectionId){
    let sectionArr = document.querySelectorAll(".review-wrapper>section");
    let btnArr = document.querySelectorAll(".review-tab button");

    for(let i in sectionArr){
        let section = sectionArr[i];
        let button = btnArr[i];

        button.style.backgroundColor = 'white';

        if(section.id===sectionId){
            $(sectionArr).removeClass('active');
            $(section).addClass('active');
        }
        if(button===btn)
            button.style.backgroundColor = '#aaaaac';
    }
}
</script>
<!-- page nav -->
<nav class="line-style page-nav">
    <ul class="list-unstyled list-inline">
        <li class="go-home">
            <a href="<%=request.getContextPath()%>/index.jsp">메인</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li class="go-boxmenu">
            <a href="">마이페이지</a>
            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
        </li>
        <li>이용후기 내역</li>
    </ul>
</nav>

<!-- 이용후기내역 서브메뉴 -->
<div class="container-fluid contents">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper">
            <h2 class="sr-only">이용후기 내역</h2>
            <section class="my-header review-tab">
                <h3 class="sr-only">이용후기내역 서브메뉴</h3>
                <ul class="row list-unstyled list-inline">
                    <li class="col-md-6 text-center sel">
                        <button type="button" onclick="showContent(this, 'writable-review')">작성가능한 이용후기</button>
                    </li>
                    <li class="col-md-6 text-center">
                        <button type="button" onclick="showContent(this, 'writed-review')">작성한 이용후기</button>
                    </li>
                </ul>
            </section>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<!-- 작성가능한 이용후기: 전체 개수 -->
<div class="container-fluid line-style text-center">
    <p>전체 <span class="em-blue strong"><%=list2.size()%></span>개</p>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10 content-wrapper review-wrapper">
            <!-- 작성가능한 이용후기 -->
            <section id="writable-review" class="list-wrapper active">
                <h3 class="sr-only">이용후기 작성가능한 상품 리스트</h3>
                <% if(list2!=null && !list2.isEmpty()) { %>
                <ul class="list-unstyled wishlist-inner">
	                <%
	                 for(int i=0; i<list2.size(); i++){ 
	                	 OrderDetail3 o = list2.get(i);   	 
	                	 List<ItemImage> imgList = imgMap.get(itemNoList.get(i));
	                %>
                    <li class="row">
                        <div class="item-img col-md-3 text-center">
                            <a href="<%=request.getContextPath()%>/item/itemView?categoryNo=<%=o.getCategoryNo()%>&itemNo=<%=o.getItemNo()%>">
                            	<img src="<%=request.getContextPath()%>/images/<%=o.getCategoryNo()%>/<%=imgList.get(0).getItemImageRenamed()%>" alt="상품이미지">
                            </a>
                        </div>
                        <div class="wish-info item-info col-md-6">
                            <a href="">
                                <p class="text-left pbrand"><%=o.getItemBrand() %></p>
                                <p class="text-left pname"><%=o.getItemName() %></p>
                            </a>
                           
                        </div>
                        <div class="col-md-3">
                            <a href="<%=request.getContextPath() %>/mypage/mypageReviewForm?itemNo=<%=o.getItemNo()%>&orderDetailNo=<%=o.getOrderDetailNo()%>" class="btn-radius btn-qna">구매후기 쓰기</a>
                        </div>
                    </li>       
                	<%} %>
                </ul>
                <!-- 페이징바 -->
	            <nav class="paging-bar text-center">
	                <ol class="list-unstyled list-inline">
						<%=pageBar %>
	                </ol>
	            </nav>
                <%} else{%>
                	<div id="warning-wrapper" class="content-wrapper text-center">
						<p><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>작성가능한 이용후기가 없습니다</p> 
					</div>
                <%} %>
            </section>
            
            
            
            <!-- 작성한 이용후기 -->
            <section id="writed-review" class="list-wrapper">
                <h3 class="sr-only">작성한 이용후기 리스트</h3>
                <% if(list!=null && !list.isEmpty()) { %>
                <ul class="list-unstyled wishlist-inner">
               <% 
               int c=0;
               //System.out.println(list.toString()+"toto");
               for(Board b : list){ 
            	  // System.out.println(c++);
            	 if((memberLoggedIn.getMemberId()).equals(b.getReview_writer())){

            		 %>
                    <li class="row">
                        <section class="review">
                        	<!--이미지 -->
                         	<div class="item-img col-md-2 text-center">
                         		<% for(int i=0; i<b.getReview_star(); i++){ %>
                                <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
    							<%} %>
                            </div>
                            
                            <!-- 상품명자리 우리는 공백 넣어둠 -->
                            <div class="wish-info item-info col-md-7">
                            
                            </div>
                            
                            <!-- 수정하기/삭제하기버튼 -->
                            <div class="col-md-3 reviewBtn-wrapper">
                                <ul class="list-unstyled list-inline">
                                    <li><a href = "<%=request.getContextPath()%>/mypage/mypageReviewUpdate?reviewNo=<%=b.getReview_no()%>"><button type="button" class="btn-radius" >수정하기</button></a></li>
                                    <li><button type="button" class="btn-radius" name="boardDelFrm" onclick="deleteBoard()">삭제하기</button></li>
                                </ul>

                                	<form name="boardDelFrm" action="<%=request.getContextPath()%>/board/boardDelete" method="post">
									    <input type="hidden" name="Review_no" value="<%=b.getReview_no() %>" />
    								</form>
                            </div>
                        </section>

						<!-- 별 자리랑 구매후기내용 -->
                        <section class="review-content">
                        	<%-- <div class="item-img col-md-5 offset ">
                        	 <img src="<%=request.getContextPath()%>/upload/board/<%=b.getReview_image_rename() %>" alt="">
                        	</div> --%>
                            
                            <div class="star col-md-5 ">
                            	<img src="<%=request.getContextPath()%>/upload/board/<%=b.getReview_image_rename() %>" alt="">
                                <p>구매후기:<%=b.getReview_content()%></p>
                            </div>
                        </section>
                    </li>
                <% 
                		}
               		}
                %>
                </ul>
                <!-- 페이징바 -->
            	<nav class="paging-bar text-center">
	                <ol class="list-unstyled list-inline">
						<%=pageBar %>
	                </ol>
	            </nav>
	            <%} else{%>
                	<div id="warning-wrapper" class="content-wrapper text-center">
						<p><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>작성가능한 이용후기가 없습니다</p> 
					</div>
                <%} %>
            </section>
       
        </div>
           
        <div class="col-md-1"></div>
    </div>
</div>
	
<script>

function updateBoard(){
 location.href = "<%=request.getContextPath()%>/mypage/mypageReviewUpdate";	
}
	
   	function deleteBoard(){
        if(!confirm('이 게시글을 정말 삭제하시겠습니까?')) return;
        $("[name=boardDelFrm]").submit();
    }

	
	</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>