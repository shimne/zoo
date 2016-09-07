package com.shimne.zoopu.admin.service;

import java.util.List;

import com.shimne.zoopu.admin.entity.Function;
import com.shimne.zoopu.common.manage.AdminContext;

public interface FunctionService
{
	public static final String FUNCTION_IS_NULL = "Ȩ�޲���Ϊ�գ�";
	public static final String FUNCTION_NOT_EXIST = "Ȩ�޲����ڣ�";
	public static final String FUNCTION_HAS_CHILD = "Ȩ�޴�����Ȩ�ޣ�";

	void saveFunction(Function function, AdminContext adminContext);

	Function findFunctionById(long id);

	void updateFunction(Function function, AdminContext adminContext);

	void deleteFunctionById(long id);

	List<Function> queryFunctionByParentId(long parentId);

	List<Function> queryFunctionAll();

	String getTreeData();
}