--=================================
--시퀀스
--=================================
create sequence seq_item_qna;
create sequence seq_item_qna_ans;
create sequence seq_personal_qna;
create sequence seq_personal_qna_ans;
create sequence seq_order_sheet;
create sequence seq_order_detail;
create sequence seq_item_review;
create sequence seq_item_review_image;
create sequence seq_item;
create sequence seq_item_image;
create sequence seq_item_rent_each;
create sequence seq_item_cart;
create sequence seq_item_wishlist;
create sequence seq_member_point;

--==========================
--상품카테고리 테이블-최종
--==========================
CREATE TABLE item_when_category (
    category_no varchar2(30) NOT NULL,
    category_when varchar2(60) NOT NULL,
    constraint pk_item_when_category primary key(category_no)
);

insert into item_when_category values('CT01','반려동물');
insert into item_when_category values('CT02','육아');
insert into item_when_category values('CT03','파티');
insert into item_when_category values('CT04','운동');
insert into item_when_category values('CT05','여행');
insert into item_when_category values('CT06','캠핑');

--==========================
--상품 사진 유형-최종
--==========================
CREATE TABLE item_image_type (
	item_image_type_no varchar2(30) NOT NULL,
	item_image_type_title varchar2(30) NOT NULL,
    constraint pk_item_image_type primary key(item_image_type_no)
);
insert into item_image_type values('IMG01','대표');
insert into item_image_type values('IMG02','서브1');
insert into item_image_type values('IMG03','서브2');
insert into item_image_type values('IMG04','서브3');
insert into item_image_type values('IMG05','상품정보');

--==========================
--렌탈 유형 테이블-최종
--==========================
CREATE TABLE item_rent_option (
    rent_opt_no varchar2(30) NOT NULL,
    rent_opt varchar2(30) NOT NULL,
    rent_opt_disc_rate number NOT NULL,
    constraint pk_item_rent_option primary key(rent_opt_no)
);
insert into item_rent_option values('RT01','7일',2);
insert into item_rent_option values('RT02','14일',5);
insert into item_rent_option values('RT03','30일',10);

--==========================
--상품 테이블-최종
--==========================
CREATE TABLE item (
    item_no number NOT NULL,
    category_no varchar2(30) NOT NULL,
    item_stock number DEFAULT 1 NOT NULL,
    item_brand varchar2(50) NOT NULL,
    item_name varchar2(50) NOT NULL,
    item_price number NOT NULL,
    item_desc varchar2(1000) NOT NULL,
    item_enroll_date date DEFAULT sysdate NOT NULL,
    constraint pk_item primary key(item_no),
    constraint fk_item_category_no foreign key(category_no) references item_when_category(category_no) on delete set null
);

--==========================
--상품이미지 테이블-최종
--==========================
CREATE TABLE item_image (
    item_image_no number NOT NULL,
    item_no number NOT NULL,
    item_image_type_no varchar2(30) NOT NULL,
    item_image_default varchar2(100),
    item_image_renamed varchar2(100),
    constraint pk_item_image primary key(item_image_no),
    constraint fk_item_image_item_no foreign key(item_no) references item(item_no) on delete cascade,
    constraint fk_item_image_type_no foreign key(item_image_type_no) references item_image_type(item_image_type_no) on delete set null
);

--==========================
--상품개별관리 테이블-최종
--==========================
CREATE TABLE item_rent_each (
    item_each_no number NOT NULL,
    item_no number NOT NULL,
    item_rent_yn char(1) DEFAULT 'N',
    item_rent_start date,
    item_rent_end date,
    rent_opt_no varchar2(30),
    item_rent_user varchar2(20),
    constraint pk_item_rent_each primary key(item_each_no),
    constraint fk_item_rent_each_item_no foreign key(item_no) references item(item_no) on delete cascade,
    constraint fk_item_rent_each_rent_opt_no foreign key(rent_opt_no) references item_rent_option(rent_opt_no) on delete set null
);

--==========================
--회원 정보 테이블-최종
--==========================
CREATE TABLE member (
	member_id varchar2(20)	 NOT NULL,
	member_name varchar2(50) NOT NULL,
	member_password varchar2(200) NOT NULL,
	member_phone varchar2(20) NOT NULL,
	member_email varchar2(255) NOT NULL,
	member_postcode number NOT NULL,
	member_address varchar2(255) NOT NULL,
	member_detail_address varchar2(255) NOT NULL,
	member_point number DEFAULT 0 NOT NULL,
	member_hobby1 varchar2(100),
	member_hobby2 varchar2(100),
	member_quit_yn char(1) DEFAULT 'N' NOT NULL,
	member_enroll_date date DEFAULT sysdate NOT NULL,
	constraint PK_MEMBER PRIMARY KEY (member_id)
);

--==========================
--위시리스트 테이블-최종
--==========================
CREATE TABLE item_wishlist (
	wish_no	number NOT NULL,
	member_id	varchar2(20) NOT NULL,
	item_no	number NOT NULL,
    rent_opt_no varchar2(30) NOT NULL,
    CONSTRAINT PK_ITEM_WISHLIST PRIMARY KEY (wish_no),
    CONSTRAINT FK_item_wishlist_member_id FOREIGN KEY (member_id) REFERENCES member (member_id) on delete cascade,
    CONSTRAINT FK_item_wishlist_item_no FOREIGN KEY (item_no) REFERENCES item (item_no) on delete cascade,
    CONSTRAINT FK_item_wishlist_rent_opt_no FOREIGN KEY (rent_opt_no) REFERENCES item_rent_option (rent_opt_no) on delete cascade
);

--==========================
--회원 포인트 정보 테이블-최종
--==========================
CREATE TABLE member_point (
	point_no number NOT NULL,
	member_id varchar2(20) NOT NULL,
	point_status	char(1)	DEFAULT 'A' NOT NULL,
	point_amount number NOT NULL,
	point_change_reason varchar2(1000) NOT NULL,
	point_change_date	date	DEFAULT sysdate NOT NULL,
    CONSTRAINT PK_MEMBER_POINT PRIMARY KEY (point_no),
    CONSTRAINT FK_member_point_member_id FOREIGN KEY (member_id) REFERENCES member (member_id)  on delete cascade
);

--==========================
--상품Q&A 테이블-최종
--==========================
CREATE TABLE item_qna (
	item_qna_no number NOT NULL,
    item_no number NOT NULL,
	member_id varchar2(20) NOT NULL,
	item_qna_content varchar2(3000) NOT NULL,
    item_qna_ans_yn char(1) default 'N' NOT NULL,
	item_qna_date	 date DEFAULT sysdate NOT NULL,
    CONSTRAINT PK_ITEM_QNA PRIMARY KEY (item_qna_no),
    CONSTRAINT FK_item_qna_member_id FOREIGN KEY (member_id) REFERENCES member(member_id)  on delete cascade,
    CONSTRAINT FK_item_qna_item_no FOREIGN KEY (item_no) REFERENCES item(item_no) on delete cascade
);

--==========================
--상품Q&A답변 테이블-최종
--==========================
CREATE TABLE item_qna_ans (
	item_qna_ans_no number NOT NULL,
	item_qna_no number NOT NULL,
	item_qna_ans_writer varchar2(20) NOT NULL,
    item_qna_ans_content varchar2(3000) NOT NULL,
	item_qna_ans_date	date	DEFAULT sysdate NOT NULL,
    CONSTRAINT PK_ITEM_QNA_ANS PRIMARY KEY (item_qna_ans_no),
    CONSTRAINT FK_item_qna_ans_item_qna_no FOREIGN KEY (item_qna_no) REFERENCES item_qna(item_qna_no) on delete cascade
);

--==========================
--장바구니 테이블-최종
--==========================
CREATE TABLE item_cart (
	cart_no number NOT NULL,
	member_id varchar2(20) NOT NULL,
	item_no number NOT NULL,
    rent_opt_no varchar2(30) NOT NULL,
    item_quantity number DEFAULT 1 NOT NULL,
    CONSTRAINT PK_ITEM_CART PRIMARY KEY (cart_no),
    CONSTRAINT FK_item_cart_member_id FOREIGN KEY (member_id) REFERENCES member (member_id)  on delete cascade,
    CONSTRAINT FK_item_cart_item_no FOREIGN KEY (item_no) REFERENCES item (item_no) on delete cascade,
    CONSTRAINT FK_item_cart_rent_opt_no FOREIGN KEY (rent_opt_no) REFERENCES item_rent_option (rent_opt_no) on delete set null
);

--==========================
--1대1문의 유형 테이블//완료
--==========================
CREATE TABLE personal_qna_type (
	p_qna_type_no   varchar2(30)		NOT NULL,
	p_qna_type	varchar2(30)		NOT NULL,
    CONSTRAINT PK_PERSONAL_QNA_TYPE PRIMARY KEY (p_qna_type_no)
);

insert into personal_qna_type values('QT01','상품문의');
insert into personal_qna_type values('QT02','배송문의');
insert into personal_qna_type values('QT03','기타문의');

--==========================
--1대1문의 테이블//완료
--==========================
CREATE TABLE personal_qna (
	p_qna_no	number		NOT NULL,
	member_id	varchar2(20)		NOT NULL,
	p_qna_type_no	varchar2(30)			NOT NULL,
	p_qna_title	varchar2(100)		NOT NULL,
	p_qna_content	varchar2(3000)		NOT NULL,
	p_qna_date	date	DEFAULT sysdate	NOT NULL,
	p_ans_yn	char(1)	DEFAULT 'N'	NOT NULL,
	p_qna_image	varchar2(300)		NULL,
    CONSTRAINT PK_PERSONAL_QNA PRIMARY KEY (p_qna_no),
    CONSTRAINT FK_personal_qna_member_id FOREIGN KEY (member_id) REFERENCES member (member_id)  on delete cascade,
    CONSTRAINT FK_personal_qna_p_qna_type_no FOREIGN KEY (p_qna_type_no) REFERENCES personal_qna_type (p_qna_type_no) on delete cascade
);

--==========================
--1대1문의 답변 테이블//완료
--==========================
CREATE TABLE personal_qna_ans (
	p_qna_ans_no	number		NOT NULL,
	p_qna_no	number		NOT NULL,
	p_qna_ans_writer	varchar2(20)		NOT NULL,
	p_qna_ans_date	date	DEFAULT sysdate	NOT NULL,
	p_qna_ans_content	varchar2(1000)		NOT NULL,
    CONSTRAINT PK_PERSONAL_QNA_ANS PRIMARY KEY (p_qna_ans_no),
    CONSTRAINT FK_personal_qna_p_qna_no FOREIGN KEY (p_qna_no) REFERENCES personal_qna (p_qna_no) on delete cascade
);

--==========================
--주문상태 테이블-최종
--==========================
CREATE TABLE order_status (
	order_status_no varchar2(30)	 NOT NULL,
	order_status varchar2(30) NOT NULL,
    CONSTRAINT PK_ORDER_STATUS PRIMARY KEY (order_status_no)
);

insert into order_status values('OS01','주문완료');
insert into order_status values('OS02','배송준비중');
insert into order_status values('OS03','배송중');
insert into order_status values('OS04','배송완료');
insert into order_status values('OS05','구매확정');

--==========================
--주문취소옵션 테이블-최종
--==========================
CREATE TABLE order_detail_cancel (
	order_cancel_no varchar2(30)	 NOT NULL,
	order_cancel_status varchar2(30) NOT NULL,
    CONSTRAINT PK_ORDER_DETAIL_CANCEL PRIMARY KEY (order_cancel_no)
);

insert into order_detail_cancel values('OC01','주문취소');
insert into order_detail_cancel values('OC02','상품교환');
insert into order_detail_cancel values('OC03','상품반품');

--==========================
--주문-최종
--==========================
CREATE TABLE order_sheet (
	order_no	varchar2(30) NOT NULL, --시퀀스x
	member_id varchar2(20) NOT NULL,
	order_date date DEFAULT sysdate NOT NULL,
	order_pay_method	varchar2(20) NOT NULL,
	order_pay_status char(1) default 'Y' NOT NULL, --Y/N
    order_total_item_ea number NOT NULL, --한 건에 상품(item_no기준) 몇 개 들어있는지 
	order_total_price number NOT NULL,
    order_use_point number default 0 not null, 
    imp_uid varchar2(30), --아임포트 거래고유번호(사전 validation에 실패하면 null),
    CONSTRAINT PK_ORDER_SHEET PRIMARY KEY (order_no),
    CONSTRAINT FK_order_sheet_member_id FOREIGN KEY (member_id) REFERENCES member (member_id) on delete cascade
);

--==========================
--주문상품내역 테이블-최종
--==========================
CREATE TABLE order_detail (
	order_detail_no number NOT NULL,
    order_no varchar2(30) NOT NULL,
    member_id varchar2(20) NOT NULL,
	item_no	number, --상품번호(개별x)
    rent_opt_no varchar2(30), --렌탈유형
    order_quantity number, --상품 한 개당 주문수량
	order_status_no varchar2(30) default 'OS01' NOT NULL,
	review_yn char(1)	DEFAULT 'N'	NOT NULL,
    order_cancel_no varchar2(30),
    CONSTRAINT PK_ORDER_DETAIL PRIMARY KEY (order_detail_no),
    CONSTRAINT FK_order_detail_member_id FOREIGN KEY (member_id) REFERENCES member (member_id) on delete cascade,
    CONSTRAINT FK_order_detail_order_no FOREIGN KEY (order_no) REFERENCES order_sheet (order_no) on delete cascade,
    CONSTRAINT FK_order_detail_item_no FOREIGN KEY (item_no) REFERENCES item (item_no) on delete cascade,
    CONSTRAINT FK_order_detail_rent_opt_no FOREIGN KEY (rent_opt_no) REFERENCES item_rent_option (rent_opt_no) on delete set null,
    CONSTRAINT FK_order_detail_status_no FOREIGN KEY (order_status_no) REFERENCES order_status (order_status_no) on delete set null,
    CONSTRAINT FK_order_detail_cancel_no FOREIGN KEY (order_cancel_no) REFERENCES order_detail_cancel (order_cancel_no) on delete set null
);

--==========================
--상품 리뷰 테이블//완료
--==========================
CREATE TABLE item_review (
	review_no	number		NOT NULL,
	order_detail_no	number		NOT NULL,
	review_writer	varchar2(20)		NOT NULL,
	review_date	date	DEFAULT sysdate	NOT NULL,
	review_star	number	DEFAULT 5	NOT NULL,
	review_content	varchar2(1000)		NOT NULL,
	review_image	varchar2(300)		NULL,
    review_image_rename	varchar2(300)		NULL,
	review_readCount	number	DEFAULT 0	NOT NULL,
    item_no number,
    CONSTRAINT PK_ITEM_REVIEW PRIMARY KEY (review_no),
    CONSTRAINT FK_item_review_details_no FOREIGN KEY (order_detail_no) REFERENCES order_detail (order_detail_no) on delete cascade
);

--===================================================
--트리거(trigger)
--===================================================
--상품->상품개별관리 트리거-최종
--===================================================
create or replace trigger trg_item
    after
    insert on item
    for each row
declare
    counter number := :new.item_stock;
begin    
    for i in 1..counter loop
        insert into item_rent_each values(seq_item_rent_each.nextval, :new.item_no, default, default, default, default, default);
    end loop;
end;
/

--===================================================
--Q&A답변테이블 -> Q&A테이블 답변상태 변경 트리거 -최종
--===================================================
create or replace trigger trg_item_qna
    after
    insert on item_qna_ans
    for each row
begin
    update item_qna set item_qna_ans_yn = 'Y' 
    where item_qna_no = :NEW.item_qna_no;
end;
/

--===================================================
--1:1문의답변테이블 -> 1:1문의테이블 답변상태 변경 트리거 -최종
--===================================================
create or replace trigger trg_personal_qna
    after
    insert on personal_qna_ans
    for each row
begin
    update personal_qna set p_ans_yn = 'Y'
    where p_qna_no = :NEW.p_qna_no;
end;
/


--===========================================================
--주문(order_sheet) -> 주문상품내역(order_detail) 행추가 트리거
--===========================================================
create or replace trigger trg_order_sheet
    after
    insert on order_sheet
    for each row
declare
    vtotal_item_ea number := :new.order_total_item_ea;
begin
    for i in 1..vtotal_item_ea loop
        --주문상품내역(order_detail)에 행 추가
        insert into order_detail values(seq_order_detail.nextval, :new.order_no, :new.member_id, null, null, null, default, default, null);
    end loop;
end;
/

--=============================================================
--주문상품내역(order_detail) 업데이트 -> 개별상품관리(item_rent_each) 트리거
--=============================================================
create or replace trigger trg_order_detail_update
    after
    update on order_detail
    for each row
declare
    vorder_quantity number := :new.order_quantity; --상품 한 개당 반복문 돌 횟수
    vitem_each_no number; --가장 첫번째 개별상품번호
    vrent_period number; --렌탈기간
begin
    --렌탈유형 -> 기간
    if :new.rent_opt_no = 'RT01' then vrent_period := 7;
    elsif :new.rent_opt_no = 'RT02' then vrent_period := 14;
    else vrent_period := 30;
    end if;

    if (:new.order_status_no = :old.order_status_no and :new.review_yn = :old.review_yn and :new.order_cancel_no is null) then 
        --주문수량만큼 개별상품관리 테이블 update
        for i in 1..vorder_quantity loop
            --가장 첫번째 개별상품번호 담기
            select item_each_no
            into vitem_each_no
            from (select rank() over (order by item_each_no) as rnum, E.*
                    from item_rent_each E where item_no = :new.item_no and item_rent_yn = 'N') V
            where rnum = 1;
            
            --상품개별관리 테이블 업데이트
            update item_rent_each set item_rent_yn='Y', item_rent_start=sysdate, item_rent_end=(sysdate+vrent_period), rent_opt_no=:new.rent_opt_no, item_rent_user=:new.member_id
            where item_each_no = vitem_each_no;    
        end loop;
    end if;
end;
/

--=======================================================
--개별상품관리(item_rent_each) 대여여부 -> 상품(item) 재고-1 트리거
--=======================================================
create or replace trigger trg_item_rent_each
    after
    update on item_rent_each
    for each row
begin
    --대여여부가 Y로 바뀌면 상품재고 -1 / N로 바뀌면 상품재고+1
    if :new.item_rent_yn = 'Y' then
        update item set item_stock = item_stock-1 where item_no = :old.item_no;
    else
        update item set item_stock = item_stock+1 where item_no = :old.item_no;
    end if;
end;
/

--===========================================================
--주문(order_sheet) -> 회원포인트(member_point) 구매시 적립(3%) 트리거
--===========================================================
create or replace trigger trg_order_sheet_add_point
    after
    insert on order_sheet
    for each row
declare
    vtotal_price number := :new.order_total_price; --총 결제금액
    vadd_point number; --적립될 포인트
begin
    vadd_point := round(vtotal_price*0.03);
    
    --회원포인트 테이블에 insert
    insert into member_point values(seq_member_point.nextval, :new.member_id, 'A', vadd_point, '구매시 적립', sysdate);
end;
/

--===========================================================
--주문(order_sheet) -> 회원포인트(member_point) 구매시 사용 트리거
--===========================================================
create or replace trigger trg_order_sheet_use_point
    after
    insert on order_sheet
    for each row
declare
    vused_point number := :new.order_use_point; --구매시 사용한 포인트
    vminus_point number := -(vused_point); --차감될 포인트
begin
    --회원포인트 테이블에 insert(사용한 포인트가 0이 아닐 경우)
    if vused_point <> 0 then
        insert into member_point values(seq_member_point.nextval, :new.member_id, 'U', vminus_point, '구매시 사용', sysdate);
    end if;
end;    
/

--===========================================================
--회원(member) -> 회원포인트(member_point) 가입시 적립 트리거
--===========================================================
create or replace trigger trg_member_add_point
    after
    insert on member
    for each row
declare
    vadd_point number := 5000; --적립될 포인트
begin
    --회원포인트 테이블에 insert
    insert into member_point values(seq_member_point.nextval, :new.member_id, 'A', vadd_point, '가입시 적립', sysdate);
end;
/

--===========================================================
--상품리뷰(item_review) -> 주문상품내역(order_detail) 리뷰 작성시 리뷰여부와 주문상태번호 변경 트리거
--===========================================================
create or replace trigger trg_item_review_add
    after
    insert on item_review
    for each row
begin
    --주문상품내역 테이블 수정
    update order_detail set order_status_no = 'OS05', review_yn = 'Y' where order_detail_no = :new.order_detail_no;
end;
/


