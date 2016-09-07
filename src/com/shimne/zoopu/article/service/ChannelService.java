package com.shimne.zoopu.article.service;

import java.util.List;

import com.shimne.zoopu.article.entity.Channel;
import com.shimne.zoopu.common.manage.AdminContext;

public interface ChannelService
{
	public static final String CHANNEL_IS_NULL = "文章类型为空！";
	public static final String CHANNEL_NOT_EXIST = "文章类型不存在！";
	public static final String CHANNEL_HAS_CHILD = "文章分类存在子分类！";
	public static final String CHANNEL_HAS_ARTICLE = "文章分类存在文章！";

	void saveChannel(Channel channel, AdminContext adminContext);

	void updateChannel(Channel channel, AdminContext adminContext);

	Channel findChannelById(long id);

	void deleteChannelById(long id);

	List<Channel> queryChannelAll();

	List<Channel> queryChannelByParentId(long parentId);

	String getTreeData();
}
