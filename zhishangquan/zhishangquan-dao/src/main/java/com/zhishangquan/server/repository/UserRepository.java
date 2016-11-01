package com.zhishangquan.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zhishangquan.server.domain.User;

/**
 * @author jzsong@uworks.cc
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

  @Query("SELECT o FROM User o WHERE o.mobile = :mobile")
  User findByMobile(@Param("mobile") String mobile);

}
