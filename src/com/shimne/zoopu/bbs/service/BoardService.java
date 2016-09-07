package com.shimne.zoopu.bbs.service;

import java.util.List;

import com.shimne.zoopu.bbs.entity.Board;
import com.shimne.zoopu.common.manage.AdminContext;

public interface BoardService
{
	public static final String BOARD_IS_NULL = "�������Ϊ�գ�";
	public static final String BOARD_NOT_EXIST = "������Ͳ����ڣ�";
	public static final String BOARD_HAS_CHILD = "����������ӷ��࣡";
	public static final String BOARD_HAS_TOPIC = "������������⣡";

	void saveBoard(Board board, AdminContext adminContext);

	void updateBoard(Board board);

	Board findBoardById(long id);

	void deleteBoardById(long id);

	List<Board> queryBoardAll();

	List<Board> queryBoardByParentId(long parentId);

	String getTreeData();
}