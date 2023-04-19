package com.kh.dandi.domain.notice.svc;

import com.kh.dandi.domain.common.file.svc.UploadFileSVC;
import com.kh.dandi.domain.entity.UploadFile;
import com.kh.dandi.domain.notice.dao.Notice;
import com.kh.dandi.domain.notice.dao.NoticeDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeSVCImpl implements NoticeSVC{

  private final NoticeDAO noticeDAO;

  private final UploadFileSVC uploadFileSVC;

  @Override
  public Long save(Notice notice) {
    return noticeDAO.save(notice);
  }


  @Transactional
  @Override
  public Long save(Notice notice, List<UploadFile> uploadFiles) {
    Long id = save(notice);
    if(uploadFiles.size() > 0) {
      uploadFiles.stream().forEach(file-> file.setRid(id));
      uploadFileSVC.addFiles(uploadFiles);
    }
    return id;
  }

  @Override
  public Optional<Notice> findById(Long id) {
    Optional<Notice> notice = noticeDAO.findById(id);
    noticeDAO.updateHit(id);
    return notice;
  }

  @Override
  public List<Notice> findAll() {
    return noticeDAO.findAll();
  }

  @Override
  public List<Notice> findAllPaging(int startRec, int endRec) {
    return noticeDAO.findAllPaging(startRec, endRec);
  }

  @Override
  public int update(Long id, Notice notice) {
    return noticeDAO.update(id, notice);
  }

  @Override
  public int delete(Long id) {
    return noticeDAO.delete(id);
  }


  // 전체건수
  @Override
  public int totalCount() {
    return noticeDAO.totalCount();
  }

  @Override
  public int increaseHit(Long id) { return noticeDAO.updateHit(id); }
}
