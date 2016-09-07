package com.shimne.zoopu.user.service;

import java.util.Map;

import com.shimne.zoopu.common.web.UserContext;
import com.shimne.zoopu.user.entity.User;

public interface UserService
{
	public static final String USER_IS_NULL = "用户不能为空！";
	public static final String USER_NOT_EXIST = "用户不存在";
	public static final String USER_PASSWORD_FAIL = "用户密码错误！";

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @param ip
	 * @return
	 */
	UserContext saveUser(User user, String ip);

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 */
	User updateUser(User user);

	/**
	 * 根据ID获取用户
	 * 
	 * @param id
	 * @return
	 */
	User findUserById(long id);

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	UserContext login(String username, String password, String ip);

	/**
	 * 根据条件查询用户
	 * 
	 * @param familyName
	 * @param lastName
	 * @param state
	 * @param city
	 * @param area
	 * @param currentState
	 * @param currentCity
	 * @param currentArea
	 * @param disable
	 * @param open
	 * @param startTime
	 * @param endTime
	 * @param currentPageNum
	 * @param maxPageRowCount
	 * @return
	 */
	Map<String, Object> queryUser(String familyName, String lastName,
			String state, String city, String area, String currentState,
			String currentCity, String currentArea, String disable, String open,
			String startTime, String endTime, int currentPageNum, int maxPageRowCount);

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 * @return
	 */
	boolean checkUser(String username);

	/**
	 * 修改密码
	 * 
	 * @param username
	 * @param password
	 * @param newPassword
	 */
	void modifyPassword(String username, String password, String newPassword);

	/**
	 * 禁用用户
	 * 
	 * @param id
	 */
	void disableUser(long id);

	/**
	 * 启用用户
	 * 
	 * @param id
	 */
	void enableUser(long id);
}