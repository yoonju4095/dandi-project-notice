package com.kh.dandi.web.form.notice;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class SaveForm {
  private Long id;      // 공지아이디
  private String title;       // 제목
  private String content;    // 본문
  private Long hit;           // 조회수

  private List<MultipartFile> imageFiles;

}
