select * from bbs6 

create table bbs6(
   bbs_num number(38) primary key,
   bbs_name varchar2(50) not null,
   bbs_pass varchar2(30) not null,
   bbs_subject varchar2(100) not null,
   bbs_content varchar2(4000) not null,
   bbs_file varchar2(100),
   bbs_re_ref number,
   bbs_re_lev number,
   bbs_re_seq number,
   bbs_readcount number,
   bbs_date date,
   bbs_original varchar2(100)
)

drop table bbs6

create sequence bbs6_num_seq
increment by 1 start with 1