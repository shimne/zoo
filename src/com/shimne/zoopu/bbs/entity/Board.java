package com.shimne.zoopu.bbs.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Board implements Serializable
{
	private static final long serialVersionUID = -7405743628123726363L;

	private long id = 0L;							// PK
	private long parentId = 0L;						// ��ID

	private String name = "";						// ����
	private String icon = "";						// ͼƬ��ַ
	private String description = "";				// ˵��

	private int topicNum = 0;						// �������� 
	private int replyNum = 0;						// �ظ�����
	private long lastTopicId = 0L;					// �������ID

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

	public long getParentId()
	{
		return parentId;
	}
	public void setParentId(long parentId)
	{
		this.parentId = parentId;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getTopicNum()
	{
		return topicNum;
	}
	public void setTopicNum(int topicNum)
	{
		this.topicNum = topicNum;
	}

	public int getReplyNum()
	{
		return replyNum;
	}
	public void setReplyNum(int replyNum)
	{
		this.replyNum = replyNum;
	}

	public long getLastTopicId()
	{
		return lastTopicId;
	}
	public void setLastTopicId(long lastTopicId)
	{
		this.lastTopicId = lastTopicId;
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