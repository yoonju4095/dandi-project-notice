package com.kh.dandi.domain.notice.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
  private Long id;      // 공지아이디
  private String title;       // 제목
  private String content;    // 본문
  private int hit;           // 조회수
  private String cDate;         // 생성일시
}
