package com.shimne.zoopu.admin.service;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.admin.entity.Admin;
import com.shimne.zoopu.common.manage.AdminContext;

public interface AdminService
{
	public static final String ADMIN_IS_NULL = "管理员为空！";
	public static final String ADMIN_NOT_EXIST = "管理员不存在！";
	public static final String ADMIN_IS_DISABLE = "用户名已禁用！";
	public static final String ADMIN_PASSWORD_FAIL = "密码不正确！";

	/**
	 * 创建管理员
	 * 
	 * @param admin
	 * @return
	 */
	Admin saveAdmin(Admin admin, String functionIds, AdminContext adminContext);

	/**
	 * 编辑管理员
	 * @param id
	 * @return
	 */
	Admin findAdminById(long id);

	/**
	 * 修改密码 
	 * @param oldPassword
	 * @param newPassword
	 * @param adminContext
	 */
	void updateAdminPassword(String oldPassword, String newPassword, AdminContext adminContext);

	/**
	 * 更新管理员
	 * 
	 * @param admin
	 * @return
	 */
	void updateAdmin(Admin admin, String functionIds, AdminContext adminContext);

	/**
	 * 根据用户名查询管理员
	 * 
	 * @param username
	 * @return
	 */
	boolean checkAdmin(String username);

	/**
	 * 根据id禁用管理员
	 * 
	 * @param id
	 */
	void disableAdmin(long id, AdminContext adminContext);

	/**
	 * 根据id启用管理员
	 * 
	 * @param id
	 */
	void enableAdmin(long id, AdminContext adminContext);

	/**
	 * 管理员登录
	 * 
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	AdminContext login(String username, String password, String ip);

	/**
	 * 根据条件查询管理员
	 * 
	 * @param name
	 * @param currentPageNum
	 * @param maxPageRowCount
	 * @return
	 */
	Map<String, Object> queryAdmin(String name, long departmentId, int currentPageNum, int maxPageRowCount);

	/**
	 * 根据用户ID获取用户的权限
	 * @param adminId
	 * @return
	 */
	List<Long> queryFunctionIdsByAdminId(long adminId);
}