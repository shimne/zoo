package com.shimne.zoopu.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.util.ObjectUtil;
import com.shimne.zoopu.admin.dao.AdminDao;
import com.shimne.zoopu.admin.dao.DepartmentDao;
import com.shimne.zoopu.admin.entity.Department;
import com.shimne.zoopu.common.manage.AdminContext;

@Service("departmentService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DepartmentServiceImpl implements DepartmentService
{
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private AdminDao adminDao;

	@Override
	@Transactional(readOnly = false)
	public void saveDepartment(Department department, AdminContext adminContext)
	{
		Assert.notNull(department, DEPARTMENT_IS_NULL);

		department.setCreatorId(adminContext.getAdminId());
		department.setCreateTime(System.currentTimeMillis());

		departmentDao.save(department);
	}

	@Override
	public Department findDepartmentById(long id)
	{
		Department department = departmentDao.findById(id);

		if (ObjectUtil.isNull(department))
		{
			throw new NestedRuntimeException(DEPARTMENT_NOT_EXIST);
		}

		return department;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateDepartment(Department department, AdminContext adminContext)
	{
		Assert.notNull(department, DEPARTMENT_IS_NULL);

		Department departmentTmp = findDepartmentById(department.getId());

		departmentTmp.setName(department.getName());
		departmentTmp.setDescription(department.getDescription());
		departmentTmp.setParentId(department.getParentId());
		departmentTmp.setUpdaterId(adminContext.getAdminId());
		departmentTmp.setUpdateTime(System.currentTimeMillis());

		departmentDao.update(departmentTmp);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteDepartmentById(long id)
	{
		Department department = departmentDao.findById(id);

		if (ObjectUtil.notNull(department))
		{
			List<Department> childs = departmentDao.queryByParentId(id);

			if (ObjectUtil.notEmpty(childs))
			{
				throw new NestedRuntimeException(DEPARTMENT_HAS_CHILD);
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("departmentId", id);

			if (adminDao.count(params) > 0)
			{
				throw new NestedRuntimeException(DEPARTMENT_HAS_ADMIN);
			}

			departmentDao.deleteById(id);
		}
	}

	@Override
	public List<Department> queryDepartmentByParentId(long parentId)
	{
		return departmentDao.queryByParentId(parentId);
	}

	@Override
	public List<Department> queryDeparentAll()
	{
		return departmentDao.queryAll();
	}

	@Override
	public String getTreeData()
	{
		StringBuilder data = new StringBuilder("[");

		List<Department> departments = queryDeparentAll();

		if (ObjectUtil.notEmpty(departments))
		{
			for (int i = 0; i < departments.size(); i++)
			{
				Department department = departments.get(i);

				if (i > 0)
				{
					data.append(",");
				}

				data.append("{id:" + department.getId() + ", pId:" + department.getParentId() + ", name:\"" + department.getName() + "\"}");
			}
		}

		return data.append("]").toString();
	}
}
