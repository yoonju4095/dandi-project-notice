package com.kh.dandi.domain.search.dao;

import com.kh.dandi.domain.notice.dao.Notice;

import java.util.List;

public interface SearchDAO {

  //검색
  List<Notice> searchWord(String keyword, int startRec, int endRec);
}
