package com.kh.dandi.domain.common.code.svc;

import com.kh.dandi.domain.entity.Code;

import java.util.List;

public interface CodeSVC {
  /**
   * 하위코드 반환 by 부모코드
   * @param pcodeId 부모코드
   * @return 하위코드
   */
  List<Code> findCodeByPcodeId(String pcodeId);
}
