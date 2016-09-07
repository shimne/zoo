package com.shimne.zoopu.bbs.dao;

import com.shimne.zoopu.bbs.entity.UserBbs;

public interface UserBbsDao
{
	void save(UserBbs userBbs);

	void update(UserBbs userBbs);

	UserBbs findByUserId(long userId);
}