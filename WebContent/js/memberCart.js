// 장바구니
$(document).ready(function(){
	
	var totalPriceckd = 0;
	var totalitemCount = 0;
	 
	$('.btn-minus').click(function() {
		console.log('click minus Btn!');
		
		var count = parseInt($("#orderNo").val()
				);
	
		if(count >= 2) {
			count --;
		} else {
			count  = 1;
		}
		console.log(count);
		    $("#sel-amount").attr("value",count);
	
	});
	
	$('.btn-plus').click(function() {
		console.log('click plus Btn!');
		
		var count = parseInt($("#orderNo").val());
		count ++;
		console.log(count);
	    $("#sel-amount").attr("value",count);
	});

//////////////////////////////////////////////////////////////////////////// 
//장바구니 삭제
	function deleteCartItem(){
		var checkedCart = [];
		
		for (var i = 1; i < $('table tr').size(); i++) {
			// table 중 tr이 i번째 있는 자식중에 체크박스가 체크중이면
			var chk = $('table tr').eq(i).children().find('input[type="checkbox"]').is(':checked');
	
			if (chk == true) {
			// 그 i 번째 input text의 값을 가져온다.
				var cartNo = $('table tr').eq(i).find('input[name="cartNo"]').val();
				checkedCart.push(cartNo);
			}
		}
		// console.log(checkedCart);
		var delConfirm = confirm('정말 삭제하시겠습니까?');
		if (delConfirm) {
			
			$.ajax({
				url : "./memberCart/delete",
				type : "POST",
				cache : false,
				dataType : "json",
				data : "items=" + checkedCart,
				success : function(data){
					// 새로고침
					window.location.reload();
				},
				error :function(request, status, error){
//					var msg = "ERROR : " + request.status + "<br>"
//					msg += + "내용 : " + request.responseText + "<br>"
//					alert(msg);
					window.location.reload();
				}
			});
			alert('삭제되었습니다.');
		}
		else {
			alert('삭제가 취소되었습니다.');
			return;
		}
	}

	$('.btn-chkDel').click(function() {
		deleteCartItem();
	});

////////////////////////////////////////////////////////////////////
	function chekedprice() {
		$('input:checkbox[id="checkbox_name"]:checked').length 
	};
	

        $("input[type='checkbox']").click(function(){
           if($(this).is(":checked") == true){
            totalPriceckd +=  parseInt($(this).val());
            console.log(totalPriceckd);
           }
           else if($(this).is(":checked") != true){
               totalPriceckd -=  parseInt($(this).val());
               console.log(totalPriceckd);
           }
            
           realTotalPrice = totalPriceckd+4000;
           
           if(totalPriceckd ==0){
        	   $("#DELprice").html("0");
        	   realTotalPrice = totalPriceckd;
           }else{
        	   realTotalPrice = totalPriceckd+4000;
        	   $("#DELprice").html("4000");
           }
           
           totalitemCount = $('input:checkbox[name="delCartlist"]:checked').length;
           console.log("@@@@"+totalitemCount);
           $("#totalitemCount").html(totalitemCount);
           $("#totalPrice").html(totalPriceckd);
           $("#realTotalPrice").html(realTotalPrice);
        });
        
        
   //전체삭제 버튼 클릭한 경우
   $(".btn-chkAllDel").click(function(){
	  if(!confirm("장바구니를 비우시겠습니까?")) return;
	  
	  $("#delAllFrm").submit();
   }); 
   
});

//=============================================================

/*  체크박스 전체선택, 전체해제 */
function checkAll(){
      if( $("#th_checkAll").is(':checked') ){
        $("input[name=delCartlist]").prop("checked", true);
      }else{
        $("input[name=delCartlist]").prop("checked", false);
      }
};

function chekedprice() {
	$('input:checkbox[id="checkbox_name"]:checked').length 
};