package com.shimne.zoopu.article.service;

import java.util.List;

import com.shimne.zoopu.article.entity.Channel;
import com.shimne.zoopu.common.manage.AdminContext;

public interface ChannelService
{
	public static final String CHANNEL_IS_NULL = "��������Ϊ�գ�";
	public static final String CHANNEL_NOT_EXIST = "�������Ͳ����ڣ�";
	public static final String CHANNEL_HAS_CHILD = "���·�������ӷ��࣡";
	public static final String CHANNEL_HAS_ARTICLE = "���·���������£�";

	void saveChannel(Channel channel, AdminContext adminContext);

	void updateChannel(Channel channel, AdminContext adminContext);

	Channel findChannelById(long id);

	void deleteChannelById(long id);

	List<Channel> queryChannelAll();

	List<Channel> queryChannelByParentId(long parentId);

	String getTreeData();
}
