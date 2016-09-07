package com.shimne.zoopu.admin.service;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.admin.entity.Admin;
import com.shimne.zoopu.common.manage.AdminContext;

public interface AdminService
{
	public static final String ADMIN_IS_NULL = "����ԱΪ�գ�";
	public static final String ADMIN_NOT_EXIST = "����Ա�����ڣ�";
	public static final String ADMIN_IS_DISABLE = "�û����ѽ��ã�";
	public static final String ADMIN_PASSWORD_FAIL = "���벻��ȷ��";

	/**
	 * ��������Ա
	 * 
	 * @param admin
	 * @return
	 */
	Admin saveAdmin(Admin admin, String functionIds, AdminContext adminContext);

	/**
	 * �༭����Ա
	 * @param id
	 * @return
	 */
	Admin findAdminById(long id);

	/**
	 * �޸����� 
	 * @param oldPassword
	 * @param newPassword
	 * @param adminContext
	 */
	void updateAdminPassword(String oldPassword, String newPassword, AdminContext adminContext);

	/**
	 * ���¹���Ա
	 * 
	 * @param admin
	 * @return
	 */
	void updateAdmin(Admin admin, String functionIds, AdminContext adminContext);

	/**
	 * �����û�����ѯ����Ա
	 * 
	 * @param username
	 * @return
	 */
	boolean checkAdmin(String username);

	/**
	 * ����id���ù���Ա
	 * 
	 * @param id
	 */
	void disableAdmin(long id, AdminContext adminContext);

	/**
	 * ����id���ù���Ա
	 * 
	 * @param id
	 */
	void enableAdmin(long id, AdminContext adminContext);

	/**
	 * ����Ա��¼
	 * 
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	AdminContext login(String username, String password, String ip);

	/**
	 * ����������ѯ����Ա
	 * 
	 * @param name
	 * @param currentPageNum
	 * @param maxPageRowCount
	 * @return
	 */
	Map<String, Object> queryAdmin(String name, long departmentId, int currentPageNum, int maxPageRowCount);

	/**
	 * �����û�ID��ȡ�û���Ȩ��
	 * @param adminId
	 * @return
	 */
	List<Long> queryFunctionIdsByAdminId(long adminId);
}