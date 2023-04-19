package com.kh.dandi.web.form.notice;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UpdateForm {
  private Long id;
  @NotNull //모든 타입에 대해 null 허용 안함
  private String title;       // 제목
  @NotNull
  private String content;    // 본문
  private int hit;           // 조회수

  private List<MultipartFile> imageFiles;

}
