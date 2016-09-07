package com.shimne.zoopu.bbs.service;

import java.util.Map;

import com.shimne.zoopu.bbs.entity.Topic;
import com.shimne.zoopu.common.web.UserContext;

public interface TopicService
{
	public static final String TOPIC_IS_NULL = "主题不能为空！";
	public static final String USER_IS_NULL = "请先登录！";
	public static final String TOPIC_NOT_EXIST = "主题不存在！";
	public static final String BBS_TIMEOUT = "发帖时间间隔1分钟以上！";

	void saveTopic(Topic topic, UserContext userContext);

	void updateTopic(Topic topic, UserContext userContext);

	Topic findTopicById(long id);

	Map<String, Object> query(long boardId, String title, int type, int status, String cloum, String startTime, String endTime, int pageNum, int maxPageRowCount);
}