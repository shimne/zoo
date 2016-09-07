package com.shimne.zoopu.bbs.dao;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.bbs.entity.Board;

public interface BoardDao
{
	void save(Board board);

	void update(Board board);

	Board findById(long id);

	void deleteById(long id);

	List<Board> queryByParentId(long parentId);

	List<Board> queryAll();

	int count(Map<String, Object> params);

	List<Board> query(Map<String, Object> params);
}