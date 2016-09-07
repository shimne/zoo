package com.shimne.zoopu.admin.dao;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.admin.entity.Admin;

public interface AdminDao
{
	/**
	 * 保存管理员
	 * 
	 * @param admin
	 */
	void save(Admin admin);

	/**
	 * 更新管理员
	 * 
	 * @param admin
	 */
	void update(Admin admin);

	/**
	 * 根据管理员id获取管理员
	 * 
	 * @param id
	 */
	Admin findById(long id);

	/**
	 * 根据用户名获取管理员
	 * 
	 * @param username
	 */
	Admin findByUsername(String username);

	/**
	 * 根据条件查询管理员数量
	 * @param params
	 * @return
	 */
	int count(Map<String, Object> params);

	/**
	 * 根据条件查询管理员
	 * @param params
	 * @return
	 */
	List<Admin> query(Map<String, Object> params);

	/**
	 * 保存用户的权限信息
	 * @param map
	 */
	void saveAdminFucntion(Map<String, Long> map);

	/**
	 * 根据用户ID获取所有权限ID
	 * @param adminId
	 * @return
	 */
	List<Long> queryFunctionIdsByAdminId(long adminId);

	/**
	 * 根据用户ID删除用户权限信息
	 * @param adminId
	 */
	void deleteAdminFunctionByAdminId(long adminId);

	/**
	 * 根据权限ID删除用户权限信息
	 * @param functionId
	 */
	void deleteAdminFunctionByFunctionId(long functionId);
}