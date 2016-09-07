package com.shimne.zoopu.admin.dao;

import java.util.List;

import com.shimne.zoopu.admin.entity.Department;

public interface DepartmentDao
{
	void save(Department department);

	void update(Department department);

	Department findById(long id);

	void deleteById(long id);

	List<Department> queryByParentId(long parentId);

	List<Department> queryAll();
}