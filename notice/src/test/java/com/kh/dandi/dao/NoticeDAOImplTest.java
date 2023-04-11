package com.kh.dandi.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@Slf4j
@SpringBootTest
public class NoticeDAOImplTest {

  @Autowired
  private NoticeDAO noticeDAO;

  // 등록
  @Test
  @Order(1)
  @DisplayName("공지등록")
  void save(){
    Notice notice = new Notice();

    notice.setTitle("타이틀");
    notice.setContent("내용");

    Long save = noticeDAO.save(notice);

//    Assertions.assertThat(save.getTitle()).isEqualTo("타이틀");
//    log.info("save={}", save);

  }
//
//  //수정
//  @Test
//  @Order(2)
//  @DisplayName("공지수정")
//  void update(){
//    Long id = 1L;
//    Notice notice = new Notice();
//    notice.setTitle("제목22");
//    notice.setContent("내용22");
//    int updatedRowCount = noticeDAO.update(id, notice);
//    Optional<Notice> findedNotice = noticeDAO.findById(id);
//
//    Assertions.assertThat(updatedRowCount).isEqualTo(1);
//    Assertions.assertThat(findedNotice.get().getTitle()).isEqualTo(notice.getTitle());
//    Assertions.assertThat(findedNotice.get().getContent()).isEqualTo(notice.getContent());
//  }
//
//  // 삭제
//  @Test
//  @DisplayName("공지삭제")
//  void delete(){
//    Long id = 121L;
//    int deletedRowCount = noticeDAO.delete(id);
//    Optional<Notice> findedNotice = noticeDAO.findById(id);
//
//    Assertions.assertThatThrownBy(()->findedNotice.orElseThrow())
//            .isInstanceOf(NoSuchMethodException.class);
//  }
//
//  @Test
//  @DisplayName("게시글 단건 조회")
//  void findById() {
//    Long id = 3L;
//    Optional<Notice> findedItem = noticeDAO.findById(id);
//    Assertions.assertThat(findedItem.get().getTitle()).isEqualTo("dd");
//  }

  @Test
  @DisplayName("공지목록")
  void findAll(){
    List<Notice> list = noticeDAO.findAll();

    Assertions.assertThat(list.size()).isGreaterThan(0);
    for (Notice notice : list){
//      log.info(notice.toString());
    }
  }

//  @Test
//  @DisplayName("조회수 증가")
//  void increaseHitCount() {
//    Long id = 3L;
//    //조회전 조회수
//    int beforeHitCount = noticeDAO.findById(id).getHit();
//    //조회
//    noticeDAO.increaseHitCount(3L);
//    //조회후 조회수
//    int afterHitCount = noticeDAO.findById(id).getHit();
//    //조회후 조회수 - 조회전 조회수 = 1
//    Assertions.assertThat(afterHitCount - beforeHitCount).isEqualTo(1);
//  }

  @Test
  @DisplayName("전체건수")
  void totalCount(){

    int size = noticeDAO.findAll().size();
    int i = noticeDAO.totalCount();

    Assertions.assertThat(i).isEqualTo(size);

  }
}
