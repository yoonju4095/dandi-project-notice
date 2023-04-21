package com.kh.dandi.web;

import com.kh.dandi.domain.common.paging.FindCriteria;
import com.kh.dandi.domain.notice.dao.Notice;
import com.kh.dandi.domain.notice.svc.NoticeSVC;
import com.kh.dandi.domain.search.svc.SearchSVC;
import com.kh.dandi.domain.troubleBoard.dao.Trouble;
import com.kh.dandi.domain.troubleBoard.svc.TroubleSVC;
import com.kh.dandi.web.form.notice.ListForm;
import com.kh.dandi.web.form.trouble.ListForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/searches")
@RequiredArgsConstructor
public class SearchController {

  private final SearchSVC searchSVC;
  private final NoticeSVC noticeSVC;

  @Autowired
  @Qualifier("fc10")
  private FindCriteria fc;

  //검색-텍스트
  @GetMapping({"/{keyword}","/{keyword}/{reqPage}","/{keyword}/{reqPage}//"})
  public String searchWord(
    @PathVariable("keyword") String keyword,
    @PathVariable(required = false) Optional<Integer> reqPage,
    Model model
  ) {

    fc.getRc().setReqPage(reqPage.orElse(1));
    fc.setTotalRec(noticeSVC.totalCount());
    List<Notice> searchWords = searchSVC.searchWord(keyword, fc.getRc().getStartRec(), fc.getRc().getEndRec());

    List<ListForm> partOfList = new ArrayList<>();
    for (Notice notice : searchWords) {
      ListForm listForm = new ListForm();
      BeanUtils.copyProperties(notice, listForm);
      partOfList.add(listForm);
    }
    model.addAttribute("troubleLists", partOfList);
    model.addAttribute("fc", fc);
    model.addAttribute("searchWords", searchWords);

    return "notice/searchNotice";
  }

}
