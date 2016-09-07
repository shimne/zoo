package com.shimne.zoopu.bbs.service;

import java.util.List;

import com.shimne.zoopu.bbs.entity.Board;
import com.shimne.zoopu.common.manage.AdminContext;

public interface BoardService
{
	public static final String BOARD_IS_NULL = "板块类型为空！";
	public static final String BOARD_NOT_EXIST = "板块类型不存在！";
	public static final String BOARD_HAS_CHILD = "板块分类存在子分类！";
	public static final String BOARD_HAS_TOPIC = "板块分类存在主题！";

	void saveBoard(Board board, AdminContext adminContext);

	void updateBoard(Board board);

	Board findBoardById(long id);

	void deleteBoardById(long id);

	List<Board> queryBoardAll();

	List<Board> queryBoardByParentId(long parentId);

	String getTreeData();
}