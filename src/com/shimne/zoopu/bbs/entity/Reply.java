package com.shimne.zoopu.bbs.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Reply implements Serializable
{
	private static final long serialVersionUID = 9087710337130510190L;

	private long id = 0L;							// PK
	private long topicId = 0L;						// ����ID

	private String title = "";						// ����
	private String content = "";					// ����
	private int status = 0;							// ״̬ 1-���� 2-ɾ�� 

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

	public long getTopicId()
	{
		return topicId;
	}
	public void setTopicId(long topicId)
	{
		this.topicId = topicId;
	}

	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}

	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
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