package com.shimne.zoopu.bbs.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shimne.page.PageService;
import com.shimne.zoopu.bbs.dao.ReplyDao;
import com.shimne.zoopu.bbs.entity.Reply;
import com.shimne.zoopu.common.web.UserContext;

@Service("replyService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ReplyserviceImpl implements ReplyService
{
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private PageService pageService;

	@Override
	public void saveReply(Reply reply, UserContext userContext)
	{
		
	}

	@Override
	public void updteReply(Reply reply, UserContext userContext)
	{
		
	}

	@Override
	public Reply findReplyById(long id)
	{
		return null;
	}

	@Override
	public Map<String, Object> queryReply(long topicId, int pageNum, int maxPageRowCount)
	{
		return null;
	}
}