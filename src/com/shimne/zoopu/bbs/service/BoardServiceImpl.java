package com.shimne.zoopu.bbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.util.ObjectUtil;
import com.shimne.zoopu.bbs.dao.BoardDao;
import com.shimne.zoopu.bbs.dao.TopicDao;
import com.shimne.zoopu.bbs.entity.Board;
import com.shimne.zoopu.common.manage.AdminContext;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService
{
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private TopicDao topicDao;

	@Override
	@Transactional(readOnly = false)
	public void saveBoard(Board board, AdminContext adminContext) 
	{
		Assert.notNull(board, BOARD_IS_NULL);

		board.setCreatorId(adminContext.getAdminId());
		board.setCreateTime(System.currentTimeMillis());

		boardDao.save(board);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateBoard(Board board)
	{
		Assert.notNull(board, BOARD_IS_NULL);

		Board boardTmp = findBoardById(board.getId());

		boardTmp.setName(board.getName());
		boardTmp.setIcon(board.getIcon());
		boardTmp.setDescription(board.getDescription());
		boardTmp.setTopicNum(board.getTopicNum());
		boardTmp.setReplyNum(board.getReplyNum());
		boardTmp.setLastTopicId(board.getLastTopicId());
		boardTmp.setUpdaterId(board.getUpdaterId());
		boardTmp.setUpdateTime(board.getUpdateTime());

		boardDao.update(boardTmp);
	}

	@Override
	public Board findBoardById(long id)
	{
		Board board = boardDao.findById(id);

		if (ObjectUtil.isNull(board))
		{
			throw new NestedRuntimeException(BOARD_NOT_EXIST);
		}

		return board;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteBoardById(long id)
	{
		Board board = boardDao.findById(id);

		if (ObjectUtil.notNull(board))
		{
			List<Board> childs = boardDao.queryByParentId(id);

			if (ObjectUtil.notEmpty(childs))
			{
				throw new NestedRuntimeException(BOARD_HAS_CHILD);
			}
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("boardId", id);

		if (topicDao.count(params) > 0)
		{
			throw new NestedRuntimeException(BOARD_HAS_TOPIC);
		}

		boardDao.deleteById(id);
	}

	@Override
	public List<Board> queryBoardAll()
	{
		return boardDao.queryAll();
	}

	@Override
	public List<Board> queryBoardByParentId(long parentId)
	{
		return boardDao.queryByParentId(parentId);
	}

	@Override
	public String getTreeData()
	{
		StringBuilder data = new StringBuilder("[");

		List<Board> boards = queryBoardAll();

		if (ObjectUtil.notEmpty(boards))
		{
			for (int i = 0; i < boards.size(); i++)
			{
				Board board = boards.get(i);

				if (i > 0)
				{
					data.append(",");
				}

				data.append("{id:" + board.getId() + ", pId:" + board.getParentId() + ", name:\"" + board.getName() + "\"}");
			}
		}

		return data.append("]").toString();
	}
}