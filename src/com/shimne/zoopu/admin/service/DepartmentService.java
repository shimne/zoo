package com.shimne.zoopu.admin.service;

import java.util.List;

import com.shimne.zoopu.admin.entity.Department;
import com.shimne.zoopu.common.manage.AdminContext;

public interface DepartmentService
{
	public static final String DEPARTMENT_IS_NULL = "����Ϊ�գ�";
	public static final String DEPARTMENT_NOT_EXIST = "���Ų����ڣ�";
	public static final String DEPARTMENT_HAS_CHILD = "���Ŵ����Ӳ���";
	public static final String DEPARTMENT_HAS_ADMIN = "���Ŵ�����Ա";

	void saveDepartment(Department department, AdminContext adminContext);

	Department findDepartmentById(long id);

	void updateDepartment(Department department, AdminContext adminContext);

	void deleteDepartmentById(long id);

	List<Department> queryDepartmentByParentId(long parentId);

	List<Department> queryDeparentAll();

	String getTreeData();
}