package com.kh.dandi.web.common;

public enum AttachFileType {

F0101("공지사항 이미지");

  private String description;

  AttachFileType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
