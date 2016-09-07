package com.shimne.zoopu.bbs.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Topic implements Serializable
{
	private static final long serialVersionUID = -3869210619411292687L;

	private long id = 0L;							// pk
	private long boardId = 0L;						// ���ID

	private String title = "";						// ����
	private String context = "";					// ����
	private String ipAddr = "";						// ip��ַ
	private int type = 0;							// ���� 1-��ͨ 2-���� 3-�ö�
	private int status = 0;							// ״̬ 1-���� 2-ɾ��

	private int viewNum = 0;						// �������
	private int replayNum = 0;						// �ظ�����
	private long lastReplyerId = 0L;				// ���ظ�ID

	private long creatorId = 0L;					// �����˴���
	private long createTime = 0L;					// ����ʱ��
	private long updaterId = 0L;					// ����޸���ID
	private long updateTime = 0L;					// ����޸�ʱ��

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