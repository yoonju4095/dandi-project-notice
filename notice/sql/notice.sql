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

--샘플 데이터
insert into notice (id, title, content, hit, cdate, udate)
values(notice_id_seq.nextval, '개인정보처리방침 개정안내 v8.33', '
개인정보처리방침 개정 안내

안녕하세요, (주)단디알바 입니다.

저희 서비스를 이용해주시는 회원 여러분께 감사드리며,

2023년 05월 18일자로 개인정보처리방침이 일부 개정됨에 따라 안내 말씀 드립니다.', '1', '23/04/09', '23/04/09');
insert into notice (id, title, content, hit, cdate, udate)
values(notice_id_seq.nextval, '청년내일채움공제관련 내용 공고등록 시 주의 안내', '청년내일채움공제 지원 대상이 과거 전체 업종의 중소기업이었으나

23년부터 제도 개편을 통해 인력난이 심한 제조업·건설업종의 5인 이상 50인 미만 중소기업으로 지원대상이 변경되었습니다.



기업회원께서는 변경된 지원대상을 확인하셔서 청년내일채움공제 지원대상이 아닌 경우

 청년내일채움공제 가입 가능,  청년내일채움공제 사업장 등의 문구를 기재하지 마시기 바랍니다.



지원대상이 아닌 기업에서 청년내일채움공제관련 문구를 활용하여 구인공고 등록 시

허위정보 기재로 서비스 이용에 제한받을 수 있으니 공고등록 시 주의하여 주시기 바랍니다.

 ', '2', '23/04/09', '23/04/09');

insert into notice (id, title, content, hit, cdate, udate)
values(notice_id_seq.nextval, '서비스 일시 점검 안내 (2023년 5월 12일 새벽 0시 ~ 6시)', '
서비스 일시 점검 안내 (2023년 5월 12일 새벽 0시 ~ 6시)
안녕하세요. 단디알바입니다.

2023년 5월 12일, 시스템 점검으로 인해
아래와 같이 서비스가 일시 중지됨을 알려드립니다.', '7', '23/04/09', '23/04/09');

insert into notice (id, title, content, hit, cdate, udate)
values(notice_id_seq.nextval, '2023년 최저임금 적용 안내(시급 9,620원)', '
안녕하세요, 단디알바입니다.

단디알바 이용해주시는 회원님께 진심으로 감사드리며,

2023년 최저임금 적용에 대한 안내 말씀드립니다.


최저임금법 준수를 위해 2022년 12월 30일부터

채용공고 등록을 포함한 알바천국의 모든 서비스에서 급여와 관련된 부분이

2023년 최저임금 기준으로 변경될 예정입니다.


  - 2023년 최저임금: 시급 9,620원

  - 서비스 적용일: 2022년 12월 30일(금) 오전 11:00


관련 문의사항은 아래의 고객센터로 연락주시면 성심껏 답변 드리겠습니다.', '1', '23/07/02', '23/07/02');

insert into notice (id, title, content, hit, cdate, udate)
values(notice_id_seq.nextval, '단디알바 채용담당자를 사칭한 문자 연락 주의 안내', '
안녕하세요, 단디알바입니다.

최근 알바천국 채용담당자를 사칭하여 카톡 등으로 연락을 유도하는 사칭 문자가 확인되어

구직회원의 각별한 주의가 필요합니다.


단디알바에서는 카톡, 텔레그램과 같은 메신저로 채용 진행을 절대 하고 있지 않으며,

입사지원자의 연락처로 전화 연락을 하여 채용을 진행하고 있습니다.


발신 문자가 해외로 확인(국제발신)되는 경우 보이스피싱 범죄와 연관성이 높으니

유사 문자를 수신하신 경우 고객센터(1771-2288)로 즉시 신고해 주시기 바랍니다.


최근 발생한 실제 문자 사례를 공지하니 피해가 발생되지 않도록 주의하시기 바랍니다. ', '12', '23/07/17', '23/07/17');

commit;

select * from notice;


-----------
----공지사항
-----------
--drop TABLE notice;
--drop sequence notice_id_seq;
--
---- 테이블 생성
--create table notice(
--id    number(8),
--title       varchar2(100),
--content     clob,
--author      varchar2(12), -- 작성자
--hit         number(5) default 0, -- 조회수
--cdate       timestamp default systimestamp,
--udate       timestamp default systimestamp
--);
----기본키생성
--alter table notice add Constraint notice_id_pk primary key (id);
--
----제약조건 not null
--alter table notice modify title constraint notice_title_nn not null;
--alter table notice modify content constraint notice_content_nn not null;
----alter table notice modify author constraint notice_author_nn not null;
--
----시퀀스 생성
--create sequence notice_id_seq
--start with 1
--increment by 1
--minvalue 0
--maxvalue 99999999
--nocycle;
--
----샘플데이터
--insert into notice (id, title, content, author, hit, cdate, udate)
--values(notice_id_seq.nextval, '제목1', '내용1', '관리자', '1', '23/04/09', '23/04/09');
--insert into notice (id, title, content, author, hit, cdate, udate)
--values(notice_id_seq.nextval, '제목2', '내용2', '관리자', '2', '23/04/09', '23/04/09');
--
--commit;
--
--select * from notice;