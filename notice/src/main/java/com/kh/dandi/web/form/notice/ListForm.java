package com.kh.dandi.web.form.notice;

import lombok.Data;

@Data
public class ListForm {
  private Long id;      // 공지아이디
  private String title;       // 제목
  private String content;    // 본문
  private int hit;           // 조회수
  private String cDate;         // 생성일시
}

