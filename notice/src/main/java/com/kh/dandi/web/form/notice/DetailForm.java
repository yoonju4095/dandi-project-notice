package com.kh.dandi.web.form.notice;

import com.kh.dandi.domain.entity.UploadFile;
import lombok.Data;

import java.util.List;

@Data
public class DetailForm {
  private Long id;      // 공지아이디
  private String title;       // 제목
  private String content;    // 본문
  private int hit;           // 조회수
  private String cDate;         // 생성일시

  private List<UploadFile> imagedFiles;

}
