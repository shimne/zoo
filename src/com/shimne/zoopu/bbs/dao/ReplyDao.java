package com.shimne.zoopu.bbs.dao;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.bbs.entity.Reply;

public interface ReplyDao
{
	void save(Reply reply);

	void update(Reply reply);

	Reply findById(long id);

	int countByTopicId(long topicId);

	List<Reply> queryByTopicId(Map<String, Object> params);
}