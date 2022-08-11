create table reply(
	id varchar(20), --작성자
	title varchar(50), --제목
	content varchar(300), --내용
	write_group number(10), --게시글 일치시키기 위한 그룹
	write_date date default sysdate, --작성일
	constraint fk_test1 foreign key(write_group) references mvc_board(write_no) on delete cascade,
	constraint fk_test2 foreign key(id) references membership(id) on delete cascade
	--외래키와 대칭되는 키를 묶어놓아서 해당 정보가 삭제가 되면 참조되는 정보 삭제 
);