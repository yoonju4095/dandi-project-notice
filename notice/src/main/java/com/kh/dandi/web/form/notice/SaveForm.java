package com.kh.dandi.web.form.notice;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveForm {
//  @NotEmpty // null,빈문자열(""),공백문자(" ") 허용안함
  private Long id;      // 공지아이디
  @NotNull //모든 타입에 대해 null 허용 안함
  private String title;       // 제목
  @NotNull
  private String content;    // 본문
  private int hit;           // 조회수
//  @NotNull
//  private Long cDate;         // 생성일시
//  @NotNull
//  private Long uDate;         // 변경일시
}
