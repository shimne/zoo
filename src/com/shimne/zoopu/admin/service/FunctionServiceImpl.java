package com.shimne.zoopu.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.util.ObjectUtil;
import com.shimne.zoopu.admin.dao.AdminDao;
import com.shimne.zoopu.admin.dao.FunctionDao;
import com.shimne.zoopu.admin.entity.Function;
import com.shimne.zoopu.common.manage.AdminContext;

@Service("functionService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class FunctionServiceImpl implements FunctionService
{
	@Autowired
	private FunctionDao functionDao;
	@Autowired
	private AdminDao adminDao;

	@Override
	@Transactional(readOnly = false)
	public void saveFunction(Function function, AdminContext adminContext)
	{
		Assert.notNull(function, FUNCTION_IS_NULL);

		function.setCreatorId(adminContext.getAdminId());
		function.setCreateTime(System.currentTimeMillis());

		functionDao.save(function);
	}

	@Override
	public Function findFunctionById(long id)
	{
		Function function = functionDao.findById(id);

		if (ObjectUtil.isNull(function))
		{
			throw new NestedRuntimeException(FUNCTION_NOT_EXIST);
		}

		return function;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateFunction(Function function, AdminContext adminContext)
	{
		Assert.notNull(function, FUNCTION_IS_NULL);

		Function functionTmp = findFunctionById(function.getId());

		functionTmp.setName(function.getName());
		functionTmp.setDescription(function.getDescription());
		functionTmp.setUrls(function.getUrls());
		functionTmp.setParentId(function.getParentId());
		functionTmp.setUpdaterId(adminContext.getAdminId());
		functionTmp.setUpdateTime(System.currentTimeMillis());

		functionDao.update(functionTmp);
	}

	@Override
	public void deleteFunctionById(long id)
	{
		Function function = functionDao.findById(id);

		if (ObjectUtil.notNull(function))
		{
			List<Function> childs = functionDao.queryByParentId(id);

			if (ObjectUtil.notEmpty(childs))
			{
				throw new NestedRuntimeException(FUNCTION_HAS_CHILD);
			}

			functionDao.deleteById(id);
			adminDao.deleteAdminFunctionByFunctionId(id);
		}
	}

	@Override
	public List<Function> queryFunctionByParentId(long parentId)
	{
		return functionDao.queryByParentId(parentId);
	}

	@Override
	public List<Function> queryFunctionAll()
	{
		return functionDao.queryAll();
	}

	@Override
	public String getTreeData()
	{
		StringBuilder data = new StringBuilder("[");

		List<Function> functions = queryFunctionAll();

		if (ObjectUtil.notEmpty(functions))
		{
			for (int i = 0; i < functions.size(); i++)
			{
				Function function = functions.get(i);

				if (i > 0)
				{
					data.append(",");
				}

				data.append("{id:" + function.getId() + ", pId:" + function.getParentId() + ", name:\"" + function.getName() + "\"}");
			}
		}

		return data.append("]").toString();
	}
}