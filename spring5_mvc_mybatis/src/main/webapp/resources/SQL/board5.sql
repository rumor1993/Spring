create table board5(
	board_num number(38) primary key,
	board_name varchar2(50) not null,
	board_pass varchar2(30) not null,
	board_subject varchar2(100) not null,
	board_content varchar2(4000) not null,
	board_re_ref number, /* 글 그룹번호 : 답변글 */
	board_re_lev number, /* 답변글 레벨 순서 */
	board_re_seq number, /* 답변글 화면에 보이는 위치 */
	board_readcount number, /* 조회수 */
	board_date date /* 등록날짜 */
);

create sequence board5_num_seq 
increment by 1
start with 1 nocache;
