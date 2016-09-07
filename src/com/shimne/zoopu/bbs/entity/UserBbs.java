package com.shimne.zoopu.bbs.entity;

import java.io.Serializable;

/**
 * @author R026344
 *
 */
public class UserBbs implements Serializable
{
	private static final long serialVersionUID = 3524507805871929597L;

	private long userId = 0L;			// PK

	private String sign = "";			// ����ǩ��

	private int topicNum = 0;			// ������������
	private int replyNum = 0;			// �ظ�����

	private long score = 0L;			// ����
	private int level = 0;				// �ȼ�

	private long lastPublishTime = 0L;	// ��󷢲�ʱ��

	public UserBbs()
	{
	}

	public UserBbs(long userId)
	{
		this.userId = userId;
		this.sign = "";
		this.topicNum = 0;
		this.replyNum = 0;
		this.score = 0L;
		this.level = 0;
		this.lastPublishTime = 0L;
	}

	public long getUserId()
	{
		return userId;
	}
	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public String getSign()
	{
		return sign;
	}
	public void setSign(String sign)
	{
		this.sign = sign;
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

	public long getScore()
	{
		return score;
	}
	public void setScore(long score)
	{
		this.score = score;
	}

	public int getLevel()
	{
		return level;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}

	public long getLastPublishTime()
	{
		return lastPublishTime;
	}
	public void setLastPublishTime(long lastPublishTime)
	{
		this.lastPublishTime = lastPublishTime;
	}
}