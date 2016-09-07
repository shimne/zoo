package com.shimne.zoopu.article.dao;

import java.util.List;

import com.shimne.zoopu.article.entity.Channel;

public interface ChannelDao
{
	void save(Channel channel);

	void update(Channel channel);

	void deleteById(long id);

	Channel findById(long id);

	List<Channel> queryByParentId(long id);

	List<Channel> queryAll();
}