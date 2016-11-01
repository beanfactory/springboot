package com.zhishangquan.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zhishangquan.server.domain.AnswerPeek;

/**
 * @Title: AnswerPeekRepository.java
 * @Package com.zhishangquan.server.repository
 * @author liyuchang
 * @date 2016年10月26日
 * @version V1.0
 */
public interface AnswerPeekRepository extends JpaRepository<AnswerPeek, Long>, JpaSpecificationExecutor<AnswerPeek> {

}
