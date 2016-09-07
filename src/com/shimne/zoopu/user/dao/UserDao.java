package com.shimne.zoopu.user.dao;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.user.entity.User;

public interface UserDao
{
	void save(User user);

	void update(User user);

	User findById(long id);

	User findByUsername(String username);

	int count(Map<String, Object> params);

	List<User> query(Map<String, Object> params);
}