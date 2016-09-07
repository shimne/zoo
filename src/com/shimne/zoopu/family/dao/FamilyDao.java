package com.shimne.zoopu.family.dao;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.family.entity.Family;

public interface FamilyDao
{
	void save(Family family);

	void update(Family family);

	Family findById(long id);

	int count(Map<String, Object> params);

	List<Family> query(Map<String, Object> params);
}
