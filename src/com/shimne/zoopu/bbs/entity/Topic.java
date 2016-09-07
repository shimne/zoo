package com.shimne.zoopu.bbs.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Topic implements Serializable
{
	private static final long serialVersionUID = -3869210619411292687L;

	private long id = 0L;							// pk
	private long boardId = 0L;						// 板块ID

	private String title = "";						// 标题
	private String context = "";					// 内容
	private String ipAddr = "";						// ip地址
	private int type = 0;							// 类型 1-普通 2-精华 3-置顶
	private int status = 0;							// 状态 1-正常 2-删除

	private int viewNum = 0;						// 浏览次数
	private int replayNum = 0;						// 回复次数
	private long lastReplyerId = 0L;				// 最后回复ID

	private long creatorId = 0L;					// 创建人代码
	private long createTime = 0L;					// 创建时间
	private long updaterId = 0L;					// 最后修改人ID
	private long updateTime = 0L;					// 最后修改时间

	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}

	public long getBoardId()
	{
		return boardId;
	}
	public void setBoardId(long boardId)
	{
		this.boardId = boardId;
	}

	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContext()
	{
		return context;
	}
	public void setContext(String context)
	{
		this.context = context;
	}

	public String getIpAddr()
	{
		return ipAddr;
	}
	public void setIpAddr(String ipAddr)
	{
		this.ipAddr = ipAddr;
	}

	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}

	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getViewNum()
	{
		return viewNum;
	}
	public void setViewNum(int viewNum)
	{
		this.viewNum = viewNum;
	}

	public int getReplayNum()
	{
		return replayNum;
	}
	public void setReplayNum(int replayNum)
	{
		this.replayNum = replayNum;
	}

	public long getLastReplyerId()
	{
		return lastReplyerId;
	}
	public void setLastReplyerId(long lastReplyerId)
	{
		this.lastReplyerId = lastReplyerId;
	}

	public long getCreatorId()
	{
		return creatorId;
	}
	public void setCreatorId(long creatorId)
	{
		this.creatorId = creatorId;
	}

	public long getCreateTime()
	{
		return createTime;
	}
	public String getCreateTime1()
	{
		if (createTime > 0L)
		{
			return DateUtil.formatString(createTime, "yyyy-MM-dd HH:mm");
		}

		return "";
	}
	public void setCreateTime(long createTime)
	{
		this.createTime = createTime;
	}

	public long getUpdaterId()
	{
		return updaterId;
	}
	public void setUpdaterId(long updaterId)
	{
		this.updaterId = updaterId;
	}

	public long getUpdateTime()
	{
		return updateTime;
	}
	public String getUpdateTime1()
	{
		return updateTime > 0L ? DateUtil.formatString(updateTime, "yyyy-MM-dd HH:mm") : "";
	}
	public void setUpdateTime(long updateTime)
	{
		this.updateTime = updateTime;
	}
}