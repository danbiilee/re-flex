document.addEventListener('DOMContentLoaded', function(){
    toggleSideMenuLevel1();
    toggleSideMenuLevel2();
    closeMenu();
});

//사이드메뉴 외부영역 클릭시 사이드메뉴 닫기
function closeMenu(){
    let layer = $("#sidemenu-closeLayer");
    $(document).mouseup(function(e){
        //console.log(e.target);
        if(layer.has(e.target).lenght===0)
            layer.hide();
    });
}

function toggleSideMenuLevel1(){
    var btnSideMenu = document.querySelector('#btn-sidemenu');
    var level1SideMenu = document.querySelector('#level1-sidemenu');
    var sideMenuList = document.querySelectorAll('.sidemenu-wrapper');

    btnSideMenu.addEventListener('click', function(){
        sideMenuList.forEach(function(sMenu){
            //레벨1인 경우 그냥 열고 닫기. 
            if(sMenu.id === 'level1-sidemenu'){
                sMenu.classList.toggle('show-sidemenu');
            }
            //레벨2 이상 메뉴가 열려있는 경우
            if(sMenu.id !== 'level1-sidemenu' && sMenu.classList.contains('show-sidemenu')){
                level1SideMenu.classList.remove('show-sidemenu');
                sMenu.classList.remove('show-sidemenu');
            }
        });
    });
}

function toggleSideMenuLevel2(){
    let level1SideMenu = document.querySelector('#level1-sidemenu');
    let level1MenuLi = document.querySelectorAll('#level1-sidemenu li');
    let btnGoBack = document.querySelectorAll('.btn-goLevel1');
    
    level1MenuLi.forEach(function(li){
        li.addEventListener('click', function(){
            let level2Menu = document.querySelector('#level2-'+this.id);

            //레벨2메뉴 토글하기
            if(level2Menu!==null){
                level2Menu.classList.add('show-sidemenu');

                //헤더 클릭하면 레벨1메뉴로 돌아가기
                btnGoBack.forEach(function(btn){
                    btn.addEventListener('click', function(){
                        level2Menu.classList.remove('show-sidemenu');
                        level1SideMenu.classList.add('show-sidemenu');
                    });
                });
            }

        }); //end of li.click
    }); //end of level1MenuLi.click
} //end of toggleSideMenuLevel2()

