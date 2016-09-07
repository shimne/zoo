package com.shimne.zoopu.bbs.dao;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.bbs.entity.Topic;

public interface TopicDao
{
	void save(Topic topic);

	void update(Topic topic);

	Topic findById(long id);

	int count(Map<String, Object> params);

	List<Topic> query(Map<String, Object> params);
}