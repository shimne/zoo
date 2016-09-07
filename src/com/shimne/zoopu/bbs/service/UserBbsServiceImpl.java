package com.shimne.zoopu.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shimne.zoopu.bbs.dao.UserBbsDao;
import com.shimne.zoopu.bbs.entity.UserBbs;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class UserBbsServiceImpl implements UserBbsService
{
	@Autowired
	private UserBbsDao userBbsDao;

	@Override
	@Transactional(readOnly = true)
	public void updateUserBbs(UserBbs userBbs)
	{
		UserBbs userBbsTmp = findUserBbsByUserId(userBbs.getUserId());

		userBbsTmp.setTopicNum(userBbs.getTopicNum());
		userBbsTmp.setReplyNum(userBbs.getReplyNum());
		userBbsTmp.setSign(userBbs.getSign());
		userBbsTmp.setScore(userBbs.getScore());
		userBbsTmp.setLevel(userBbs.getLevel());

		userBbsDao.update(userBbsTmp);
	}

	@Override
	public UserBbs findUserBbsByUserId(long userId)
	{
		return userBbsDao.findByUserId(userId);
	}
}