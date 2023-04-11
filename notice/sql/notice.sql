---------
--공지사항
---------
drop TABLE notice;
drop sequence notice_id_seq;

-- 테이블 생성
create table notice(
id    number(8),
title       varchar2(100),
content     clob,
author      varchar2(12), -- 작성자
hit         number(5) default 0, -- 조회수
cdate       timestamp default systimestamp,
udate       timestamp default systimestamp
);
--기본키생성
alter table notice add Constraint notice_id_pk primary key (id);

--제약조건 not null
alter table notice modify title constraint notice_title_nn not null;
alter table notice modify content constraint notice_content_nn not null;
--alter table notice modify author constraint notice_author_nn not null;

--시퀀스 생성
create sequence notice_id_seq
start with 1
increment by 1
minvalue 0
maxvalue 99999999
nocycle;

--샘플데이터
insert into notice (id, title, content, author, hit, cdate, udate)
values(notice_id_seq.nextval, '제목1', '내용1', '관리자', '1', '23/04/09', '23/04/09');
insert into notice (id, title, content, author, hit, cdate, udate)
values(notice_id_seq.nextval, '제목2', '내용2', '관리자', '2', '23/04/09', '23/04/09');

commit;

select * from notice;