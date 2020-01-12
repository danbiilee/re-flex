//스크롤 관련 변수들
let didScroll; //스크롤 했는지 여부
let lastScrollTop = 0; //
let delta = 5; //
let viewHeight;

document.addEventListener('DOMContentLoaded', function(){
    //스크롤관련
    viewHeight = document.querySelector("#view-wrapper").scrollHeight-110;

    $(window).scroll(()=>{
        didScroll = true;
    });

    setInterval(function(){
        if(didScroll){
            hasScrolled();
            didScroll = false;
        }
    }, 250);
    
    //옵션영역 가격 변하게 하기
    changePrice();

    //현재 선택된 탭이 상품Q&A이면 펼치기 함수 적용
    $(".qna-view").hide();
    viewAnswer();
//    if($("#details-qna").hasClass('active')){
//        viewAnswer();
//    }
}); 

function hasScrolled(){
    let cp = $(this).scrollTop(); //현재 스크롤 위치

    //delta값보다 스크롤이 더 많이 되었는가
    if(Math.abs(lastScrollTop-cp)<=delta)
        return;

    //헤더높이보다 더 스크롤이 되었는가
    if(cp>lastScrollTop && cp>viewHeight){
        //스크롤다운
        $('#opt-header').addClass('nav-up');
    }
    else{
        //스크롤업
        // if(cp+$(window).height()<$(document).height()){
            $('#opt-header').removeClass('nav-up');
        // }
    }

    lastScrollTop = cp;
}

//옵션 영역 가격 변하게 하기
function changePrice(){
	let totalPrice = document.querySelector("#total-price"); //맨 위에 찍히게 될 총가격
	let inputOrderNo = document.querySelector("#orderNo"); //수량
	let rentType = document.querySelector("#rent-type"); //렌탈유형 선택
	let rentPrice = 0; //렌탈유형별 가격(정수형)
	
	//1.렌탈유형선택 후 수량 1인 경우를 기본으로 가격 띄움
	//2.수량 변경되면 가격 변경
	//3.렌탈유형을 바꾸면 수량을 다시 1로 바꾸고 그 가격 띄움
	
	//렌탈유형 선택
	rentType.addEventListener('change', function(){
		let ptxt = rentType.options[rentType.selectedIndex].text;
		let delIdx = ptxt.indexOf('원');
		rentPrice = ptxt.substr(0, delIdx).replace(',','');
		rentPrice *= 1; //선택한 렌탈유형의 가격(정수형)
		
		//수량1 셋팅, 수량1에 대한 가격 띄움
		inputOrderNo.value = "1";
		totalPrice.innerText = rentPrice.toLocaleString()+"원";
	});
	
	//수량선택
	let oldVal = inputOrderNo.value;
	inputOrderNo.addEventListener('propertychange change keyup paste input', function(){
		console.log(this.value);
		/*let curVal = this.value;
		if(curVal == oldVal) 
			return;
		oldVal = curVal;*/
	});
	//let price = rentPrice*orderNo; //렌탈옵션*수량
	//totalPrice.innerText = price.toLocaleString()+"원";
}

//하단 탭 누르면 내용 보이기
function showContent(btn, sectionId){
    let sectionArr = document.querySelectorAll(".dc-inner>section");
    let btnArr = document.querySelectorAll(".details-tab button");

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

//상품Q&A 펼치기
function viewAnswer(){
    $(".qna-title button").on("click", function(){
        let $title = $(this);
        let $qnaView = $(this).parent().parent().next();
        $qnaView.slideToggle(1000);   

        $('.qna-view').each(function(){
            if($(this).is($qnaView)){
                $(this).slideDown();
            }
            else{
                $(this).slideUp();
            }
        });
    });
}

