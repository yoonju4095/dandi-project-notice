package com.kh.dandi.web;

import com.kh.dandi.dao.Notice;
import com.kh.dandi.svc.NoticeSVC;
import com.kh.dandi.web.form.notice.DetailForm;
import com.kh.dandi.web.form.notice.SaveForm;
import com.kh.dandi.web.form.notice.UpdateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeSVC noticeSVC;

  // 등록양식
  @GetMapping("/add")
  public String saveForm(Model model){
    model.addAttribute("saveForm", new SaveForm());
    return "notice/saveForm";
  }

  // 등록처리
  @PostMapping("/add")
  public String save(
      @Valid @ModelAttribute SaveForm saveForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
    ){
    log.info("saveForm={}", saveForm);

    // 데이터 검증
    // 어노테이션 기반 검증
    if (bindingResult.hasErrors()){
      log.info("bindingResult={}", bindingResult);
      return "notice/saveForm";
    }

    // 등록
    Notice notice = new Notice();
    notice.setId(saveForm.getId());
    notice.setTitle(saveForm.getTitle());
    notice.setContent(saveForm.getContent());
//    notice.setHit(saveForm.getHit());
//    notice.setCDate(saveForm.getCDate());
//    notice.setUDate(saveForm.getUDate());

    Long saveId = noticeSVC.save(notice);
    redirectAttributes.addAttribute("id", saveId);

    return "redirect:/notice/{id}/detail";
  }

  // 조회
  @GetMapping("/{id}/detail")
  public String findById(
          @PathVariable("id") Long id,
          Model model
  ){
    Optional<Notice> findedNotice = noticeSVC.findById(id);
    Notice notice = findedNotice.orElseThrow();

    DetailForm detailForm = new DetailForm();
    detailForm.setId(notice.getId());
    detailForm.setTitle(notice.getTitle());
    detailForm.setContent(notice.getContent());
    detailForm.setHit(notice.getHit());
//    detailForm.setCDate(notice.getCDate());
//    detailForm.setUDate(notice.getUDate());

    model.addAttribute("detailForm", detailForm);
    return "notice/detailForm";
  }

//   수정양식
  @GetMapping("/{id}/edit")
  public String updateForm(
          @PathVariable("id") Long id,
          Model model
  ){
    Optional<Notice> findedNotice = noticeSVC.findById(id);
    Notice notice = findedNotice.orElseThrow();

    UpdateForm updateForm = new UpdateForm();
    updateForm.setTitle(notice.getTitle());
    updateForm.setContent(notice.getContent());

    model.addAttribute("updateForm", updateForm);
    return "notice/updateForm";
  }

  // 수정
  @PostMapping("/{id}/edit")
  public String update(
          @PathVariable("id") Long id,
          @Valid @ModelAttribute UpdateForm updateForm,
          BindingResult bindingResult,
          RedirectAttributes redirectAttributes
  ){
    // 데이터 검증
    if (bindingResult.hasErrors()){
      log.info("bindingResult={}", bindingResult);
      return "notice/updateForm";
    }

    // 정상처리
    Notice notice = new Notice();
    notice.setId(id);
    notice.setTitle(updateForm.getTitle());
    notice.setContent(updateForm.getContent());

    noticeSVC.update(id, notice);

    redirectAttributes.addAttribute("id", id);
    return "redirect:/notice/{id}/detail";
  }

  // 삭제
  @GetMapping("/{id}/del")
  public String deleteById(@PathVariable("id") Long id){

    noticeSVC.delete(id);

    return "redirect:/notice";
  }

  //목록
  @GetMapping
  public String findAll(Model model){
    List<Notice> notices = noticeSVC.findAll();
    model.addAttribute("notices", notices);
    if (notices.size() == 0) {
//      throw new BizException("등록된 상품정보가 없습니다");
    }
    return "notice/all";
  }


}
