package com.zhishangquan.server.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhishangquan.server.constants.Errors;
import com.zhishangquan.server.domain.User;
import com.zhishangquan.server.enums.UserEnum.UserRegistSource;
import com.zhishangquan.server.enums.UserEnum.UserStatus;
import com.zhishangquan.server.repository.UserRepository;
import com.zhishangquan.server.request.UserLoginRequestBean;
import com.zhishangquan.server.request.UserRegisterRequestBean;
import com.zhishangquan.server.util.DateUtil;
import com.zhishangquan.server.util.ExceptionUtil;
import com.zhishangquan.server.util.PasswordUtil;
import com.zhishangquan.server.util.StringUtil;

/**
 * @author jzsong@uworks.cc
 */
@Service("userService")
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class UserService {
	@Resource
	private UserValidateService userValidateService;
	@Resource
	private UserRepository userRepository;

	public User findByMobile(String mobile) {
		if (StringUtil.isBlank(mobile)) {
			return null;
		}
		return userRepository.findByMobile(mobile);
	}

	/**
	 * 注册
	 * 
	 * @param bean
	 * @return
	 */
	public User register(UserRegisterRequestBean bean) {
		User oldUser = findByMobile(bean.getMobile());
		userValidateService.register(bean, oldUser);
		User user = new User();
		user.setPassword(PasswordUtil.encode(bean.getPassword()));
		user.setMobile(bean.getMobile());
		user.setTotalBalance(0);
		user.setUseableBalance(0);
		user.setRegistSource(UserRegistSource.MOBILE.code);
		user.setCreateTime(DateUtil.getCurrentTime());
		user.setStatus(UserStatus.NORMAL.code);
		return userRepository.save(user);
	}

	/**
	 * 登录
	 * 
	 * @param bean
	 * @return
	 */
	@Transactional(readOnly = true)
	public User login(UserLoginRequestBean bean) {
		User user = findByMobile(bean.getMobile());
		if (user == null) {
			ExceptionUtil.throwException(Errors.USER_LOGIN_ERROR);
		}
		if (!PasswordUtil.checkPassword(bean.getPassword(), user.getPassword())) {
			ExceptionUtil.throwException(Errors.USER_LOGIN_ERROR);
		}
		return user;
	}

}
