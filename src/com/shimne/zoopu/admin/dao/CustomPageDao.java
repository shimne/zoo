package com.shimne.zoopu.admin.dao;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.admin.entity.CustomPage;

public interface CustomPageDao
{
	void save(CustomPage customPage);

	void update(CustomPage customPage);

	CustomPage findById(long id);

	void deleteById(long id);

	int count(Map<String, Object> params);

	List<CustomPage> query(Map<String, Object> params);
}