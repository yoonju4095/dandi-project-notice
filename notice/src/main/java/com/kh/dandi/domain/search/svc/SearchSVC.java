package com.kh.dandi.domain.search.svc;


import com.kh.dandi.domain.notice.dao.Notice;
import com.kh.dandi.domain.troubleBoard.dao.Trouble;

import java.util.List;

public interface SearchSVC {

  //검색
  List<Notice> searchWord(String keyword, int startRec, int endRec);
}
