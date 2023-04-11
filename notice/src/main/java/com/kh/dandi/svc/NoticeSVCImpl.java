package com.kh.dandi.svc;

import com.kh.dandi.dao.Notice;
import com.kh.dandi.dao.NoticeDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeSVCImpl implements NoticeSVC{

  private final NoticeDAO noticeDAO;

  @Override
  public Long save(Notice notice) {
    return noticeDAO.save(notice);
  }

  @Override
  public Optional<Notice> findById(Long bbshId) {
    Optional<Notice> bbsh = noticeDAO.findById(bbshId);
    noticeDAO.updateHit(bbshId);
    return bbsh;
  }

  @Override
  public int update(Long id, Notice notice) {
    return noticeDAO.update(id, notice);
  }

  @Override
  public int delete(Long id) {
    return noticeDAO.delete(id);
  }

  @Override
  public List<Notice> findAll() {
    return noticeDAO.findAll();
  }

  // 전체건수
  @Override
  public int totalCount() {
    return noticeDAO.totalCount();
  }

  @Override
  public int increaseHit(Long id) { return noticeDAO.updateHit(id); }
}
