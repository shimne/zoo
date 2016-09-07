package com.shimne.zoopu.admin.dao;

import java.util.List;

import com.shimne.zoopu.admin.entity.Function;

public interface FunctionDao
{
	void save(Function function);

	void update(Function function);

	Function findById(long id);

	void deleteById(long id);

	List<Function> queryByParentId(long parentId);

	List<Function> queryAll();
}