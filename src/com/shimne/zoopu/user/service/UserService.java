package com.shimne.zoopu.user.service;

import java.util.Map;

import com.shimne.zoopu.common.web.UserContext;
import com.shimne.zoopu.user.entity.User;

public interface UserService
{
	public static final String USER_IS_NULL = "�û�����Ϊ�գ�";
	public static final String USER_NOT_EXIST = "�û�������";
	public static final String USER_PASSWORD_FAIL = "�û��������";

	/**
	 * �����û�
	 * 
	 * @param user
	 * @param ip
	 * @return
	 */
	UserContext saveUser(User user, String ip);

	/**
	 * �����û�
	 * 
	 * @param user
	 * @return
	 */
	User updateUser(User user);

	/**
	 * ����ID��ȡ�û�
	 * 
	 * @param id
	 * @return
	 */
	User findUserById(long id);

	/**
	 * ��¼
	 * 
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	UserContext login(String username, String password, String ip);

	/**
	 * ����������ѯ�û�
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
	 * �ж��û����Ƿ����
	 * 
	 * @param username
	 * @return
	 */
	boolean checkUser(String username);

	/**
	 * �޸�����
	 * 
	 * @param username
	 * @param password
	 * @param newPassword
	 */
	void modifyPassword(String username, String password, String newPassword);

	/**
	 * �����û�
	 * 
	 * @param id
	 */
	void disableUser(long id);

	/**
	 * �����û�
	 * 
	 * @param id
	 */
	void enableUser(long id);
}