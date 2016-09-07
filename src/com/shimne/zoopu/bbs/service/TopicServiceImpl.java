package com.shimne.zoopu.bbs.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.page.PageService;
import com.shimne.util.ObjectUtil;
import com.shimne.zoopu.bbs.dao.BoardDao;
import com.shimne.zoopu.bbs.dao.TopicDao;
import com.shimne.zoopu.bbs.dao.UserBbsDao;
import com.shimne.zoopu.bbs.entity.Board;
import com.shimne.zoopu.bbs.entity.Topic;
import com.shimne.zoopu.bbs.entity.UserBbs;
import com.shimne.zoopu.common.manage.PropertiesTool;
import com.shimne.zoopu.common.web.UserContext;

@Service("topicService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class TopicServiceImpl implements TopicService
{
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private UserBbsDao userBbsDao;
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private PageService pageService;

	@Override
	public void saveTopic(Topic topic, UserContext userContext)
	{
		Assert.notNull(topic, TOPIC_IS_NULL);
		Assert.notNull(userContext, USER_IS_NULL);

		UserBbs userBbs = userBbsDao.findByUserId(userContext.getUserId());

		if ((System.currentTimeMillis() - userBbs.getLastPublishTime()) < 60000)
		{
			throw new NestedRuntimeException(BBS_TIMEOUT);
		}

		Board board = boardDao.findById(topic.getBoardId());

		if (ObjectUtil.isNull(board))
		{
			throw new NestedRuntimeException(BoardService.BOARD_IS_NULL);
		}

		topic.setCreatorId(userContext.getUserId());
		topic.setCreateTime(System.currentTimeMillis());

		topicDao.save(topic);

		board.setLastTopicId(topic.getId());
		board.setTopicNum(board.getTopicNum() + 1);
		boardDao.update(board);

		userBbs.setLastPublishTime(System.currentTimeMillis());
		userBbs.setTopicNum(userBbs.getTopicNum() + 1);
		userBbs.setScore(userBbs.getScore() + PropertiesTool.getScoreByKey(PropertiesTool.BBS_TOPIC));
		userBbs.setLevel(PropertiesTool.getLevelByKey(userBbs.getScore()));
		userBbsDao.update(userBbs);
	}

	@Override
	public void updateTopic(Topic topic, UserContext userContext)
	{
		Assert.notNull(topic, TOPIC_IS_NULL);

		Topic topicTmp = findTopicById(topic.getId());

		topicTmp.setTitle(topic.getTitle());
		topicTmp.setContext(topic.getContext());

		topicTmp.setUpdaterId(userContext.getUserId());
		topicTmp.setUpdateTime(System.currentTimeMillis());

		topicDao.update(topicTmp);
	}

	@Override
	public Topic findTopicById(long id)
	{
		Topic topic = topicDao.findById(id);

		if (ObjectUtil.isNull(topic) || topic.getStatus() == 2)
		{
			throw new NestedRuntimeException(TOPIC_NOT_EXIST);
		}

		return topic;
	}

	@Override
	public Map<String, Object> query(long boardId, String title, int type,
			int status, String cloum, String startTime, String endTime, int pageNum, int maxPageRowCount)
	{
		
		return null;
	}
}