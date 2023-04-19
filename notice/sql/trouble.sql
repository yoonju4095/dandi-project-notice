drop table CODE CASCADE CONSTRAINTS;
--drop table trouble_board CASCADE CONSTRAINTS;
drop table UPLOADFILE CASCADE CONSTRAINTS;
--drop sequence trouble_board_t_id_seq;
drop sequence UPLOADFILE_UPLOADFILE_ID_SEQ;

-------
--코드
-------
create table code(
    code_id     varchar2(10),       --코드
    decode      varchar2(30),       --코드명
    discript    clob,               --코드설명
--    detail      clob,               --코드설명
    pcode_id    varchar2(10),       --상위코드
    useyn       char(1) default 'Y',            --사용여부 (사용:'Y',미사용:'N')
    cdate       timestamp default systimestamp,         --생성일시
    udate       timestamp default systimestamp          --수정일시
);
--기본키
alter table code add Constraint code_code_id_pk primary key (code_id);

--제약조건
alter table code modify decode constraint code_decode_nn not null;
alter table code modify useyn constraint code_useyn_nn not null;
alter table code add constraint code_useyn_ck check(useyn in ('Y','N'));

--샘플데이터 of code
insert into code (code_id,decode,pcode_id,useyn) values ('F0101','고민게시판','B01','Y');
commit;

------------
--업로드 파일
------------
CREATE TABLE UPLOADFILE(
  UPLOADFILE_ID             NUMBER,          --파일 아이디(내부관리용)
  CODE                      varchar2(11),    --분류 코드(커뮤니티: F0101, 병원후기: F0102, 회원프로필: F0103)
  RID                       varchar2(10),    --참조번호 --해당 첨부파일이 첨부된 게시글의 순번
  STORE_FILENAME            varchar2(50),    --보관파일명
  UPLOAD_FILENAME           varchar2(50),    --업로드파일명
  FSIZE                     varchar2(45),    --파일크기
  FTYPE                     varchar2(50),    --파일유형
  CDATE                     timestamp default systimestamp, --작성일
  UDATE                     timestamp default systimestamp  --수정일
);

--기본키생성
alter table UPLOADFILE add Constraint UPLOADFILE_UPLOADFILE_ID_pk primary key (UPLOADFILE_ID);
--외래키
alter table UPLOADFILE add constraint  UPLOADFILE_CODE_fk
    foreign key(CODE) references CODE(CODE_ID);

--제약조건
alter table UPLOADFILE modify CODE constraint UPLOADFILE_CODE_nn not null;
alter table UPLOADFILE modify RID constraint UPLOADFILE_RID_nn not null;
alter table UPLOADFILE modify STORE_FILENAME constraint UPLOADFILE_STORE_FILENAME_nn not null;
alter table UPLOADFILE modify UPLOAD_FILENAME constraint UPLOADFILE_UPLOAD_FILENAME_nn not null;
-- not null 제약조건은 add 대신 modify 명령문 사용

--시퀀스 생성
create sequence UPLOADFILE_UPLOADFILE_ID_SEQ;

--샘플데이터 of UPLOADFILE
insert into UPLOADFILE (UPLOADFILE_ID, CODE ,RID, STORE_FILENAME, UPLOAD_FILENAME, FSIZE, FTYPE)
 values(UPLOADFILE_UPLOADFILE_ID_SEQ.NEXTVAL ,'F0101','001', 'F0101.png', '고민게시글이미지첨부1.png','100','image/png');

COMMIT;

--테이블 구조 확인
DESC UPLOADFILE;


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