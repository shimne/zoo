package com.shimne.zoopu.bbs.service;

import java.util.Map;

import com.shimne.zoopu.bbs.entity.Reply;
import com.shimne.zoopu.common.web.UserContext;

public interface ReplyService
{
	void saveReply(Reply reply, UserContext userContext);

	void updteReply(Reply reply, UserContext userContext);

	Reply findReplyById(long id);

	Map<String, Object> queryReply(long topicId, int pageNum, int maxPageRowCount);
}