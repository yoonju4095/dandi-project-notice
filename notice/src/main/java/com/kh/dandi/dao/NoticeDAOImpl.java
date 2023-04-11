package com.kh.dandi.dao;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NoticeDAOImpl implements NoticeDAO{

  private final NamedParameterJdbcTemplate template;

  /**
   * 등록
   *
   * @param notice
   * @return
   */
  @Override
  public Long save(Notice notice) {

    StringBuffer sb = new StringBuffer();
    sb.append("insert into notice(id, title, content) ");
    sb.append("values(notice_id_seq.nextval, :title, :content ) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(notice);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sb.toString(),param,keyHolder,new String[]{"id"});

    long id = keyHolder.getKey().longValue(); //상품아이디
//    notice.setId(id);

    return id;
  }

  /**
   * @param id
   * @return
   */
  @Override
  public Optional<Notice> findById(Long id) {
    StringBuffer sb = new StringBuffer();
    sb.append("select id, title, content, hit ");
    sb.append("  from notice ");
    sb.append(" where id = :id ");

    try {
      Map<String, Long> param = Map.of("id", id);

      Notice notice = template.queryForObject(
              sb.toString(), param, BeanPropertyRowMapper.newInstance(Notice.class)
      );
      return Optional.of(notice);
    }catch (EmptyResultDataAccessException e){
      return Optional.empty();
    }
  }

  /**
   * @param id
   * @param notice
   * @return
   */
  @Override
  public int update(Long id, Notice notice) {
    StringBuffer sb = new StringBuffer();
    sb.append("update notice ");
    sb.append("   set title = :title, ");
    sb.append("       content = :content ");
//    sb.append("       hit = :hit");
//    sb.append("       cdate = :cdate");
//    sb.append("       udate = :udate");
    sb.append(" where id = :id ");

    SqlParameterSource param = new MapSqlParameterSource()
            .addValue("title", notice.getTitle())
            .addValue("content", notice.getContent())
            .addValue("id",id);
//            .addValue("hit", notice.getHit())
//            .addValue("cdate", notice.getCDate())
//            .addValue("udate", notice.getUDate());

    return template.update(sb.toString(),param);
  }

  /**
   * @param id
   * @return
   */
  @Override
  public int delete(Long id) {
    String sql = "delete from notice where id = :id ";
    return template.update(sql,Map.of("id",id));
  }

  /**
   * @return 공지목록
   */
  @Override
  public List<Notice> findAll() {

    StringBuffer sb = new StringBuffer();
    sb.append("select id, title, content, hit ");
    sb.append("  from notice ");

    List<Notice> list = template.query(
            sb.toString(),
            BeanPropertyRowMapper.newInstance(Notice.class)  // 레코드 컬럼과 자바객체 멤버필드가 동일한 이름일경우, camelcase지원
    );

    return list;
  }

  /**
   * @return 조회수
   */
  @Override
  public int updateHit(Long id) {
    String sql = "update notice set hit = NVL(hit, 0) + 1 where id = :id ";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", id);

    // id에 해당하는 레코드가 notice 테이블에 존재하는지 확인
    String checkSql = "select count(*) from notice where id = :id ";
    int count = template.queryForObject(checkSql, params, Integer.class);
    if (count == 0) {
      throw new IllegalArgumentException("id not found in notice table");
    }

    int affectedRows = template.update(sql, params);
    return affectedRows;
  }

  @Override
  public int totalCount() {
    return 0;
  }


  //수동 매핑
  private RowMapper<Notice> noticeRowMapper() {
    return (rs, rowNum) -> {
      Notice notice = new Notice();
      notice.setId(rs.getLong("id"));
      notice.setTitle(rs.getString("title"));
      notice.setContent(rs.getString("content"));
//      notice.setHit(rs.getLong("hit"));
//      notice.setCDate(rs.getLong("cdate"));
//      notice.setUDate(rs.getLong("udate"));
      return notice;
    };
  }
}
