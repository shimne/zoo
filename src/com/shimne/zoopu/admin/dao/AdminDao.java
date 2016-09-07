package com.shimne.zoopu.admin.dao;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.admin.entity.Admin;

public interface AdminDao
{
	/**
	 * �������Ա
	 * 
	 * @param admin
	 */
	void save(Admin admin);

	/**
	 * ���¹���Ա
	 * 
	 * @param admin
	 */
	void update(Admin admin);

	/**
	 * ���ݹ���Աid��ȡ����Ա
	 * 
	 * @param id
	 */
	Admin findById(long id);

	/**
	 * �����û�����ȡ����Ա
	 * 
	 * @param username
	 */
	Admin findByUsername(String username);

	/**
	 * ����������ѯ����Ա����
	 * @param params
	 * @return
	 */
	int count(Map<String, Object> params);

	/**
	 * ����������ѯ����Ա
	 * @param params
	 * @return
	 */
	List<Admin> query(Map<String, Object> params);

	/**
	 * �����û���Ȩ����Ϣ
	 * @param map
	 */
	void saveAdminFucntion(Map<String, Long> map);

	/**
	 * �����û�ID��ȡ����Ȩ��ID
	 * @param adminId
	 * @return
	 */
	List<Long> queryFunctionIdsByAdminId(long adminId);

	/**
	 * �����û�IDɾ���û�Ȩ����Ϣ
	 * @param adminId
	 */
	void deleteAdminFunctionByAdminId(long adminId);

	/**
	 * ����Ȩ��IDɾ���û�Ȩ����Ϣ
	 * @param functionId
	 */
	void deleteAdminFunctionByFunctionId(long functionId);
}