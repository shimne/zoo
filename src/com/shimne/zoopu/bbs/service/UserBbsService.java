package com.shimne.zoopu.bbs.service;

import com.shimne.zoopu.bbs.entity.UserBbs;

public interface UserBbsService
{
	void updateUserBbs(UserBbs userBbs);

	UserBbs findUserBbsByUserId(long userId);
}