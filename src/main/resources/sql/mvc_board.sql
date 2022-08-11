create table mvc_board(
Write_no number(10) primary key,
Title varchar2(100),
Content varchar2(300),
Savedate date default sysdate,
Hit number(10) default 0,
Image_file_name varchar(100),
Id varchar(20) not null,
constraint fk_test foreign key(id) references membership(id) on delete cascade
);
--제약조건의 이름으로 fk_test설정. 외래키로 아이디를 설정. 외래키가 참조하는 위치는 membership의 id를 참조. membership에 있는 id로만 현재 테이블에 추가가 가능. on delete cascade: 부모테이블(membership)의 id가 삭제되면 현재테이블에 존재하는 해당 아이디 내용도 같이 삭제.
create sequence mvc_board_seq;