package com.shimne.zoopu.admin.service;

import java.util.List;

import com.shimne.zoopu.admin.entity.Department;
import com.shimne.zoopu.common.manage.AdminContext;

public interface DepartmentService
{
	public static final String DEPARTMENT_IS_NULL = "部门为空！";
	public static final String DEPARTMENT_NOT_EXIST = "部门不存在！";
	public static final String DEPARTMENT_HAS_CHILD = "部门存在子部门";
	public static final String DEPARTMENT_HAS_ADMIN = "部门存在人员";

	void saveDepartment(Department department, AdminContext adminContext);

	Department findDepartmentById(long id);

	void updateDepartment(Department department, AdminContext adminContext);

	void deleteDepartmentById(long id);

	List<Department> queryDepartmentByParentId(long parentId);

	List<Department> queryDeparentAll();

	String getTreeData();
}