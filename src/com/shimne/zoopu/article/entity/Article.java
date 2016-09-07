package com.shimne.zoopu.article.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Article implements Serializable
{
	private static final long serialVersionUID = 7857298430541623552L;

	private long id = 0L;					// PK

	private String channelId = "";			// Ƶ��ID
	private String title = "";				// ����
	private String subTitle = "";			// ������
	private String keywords = "";			// �ؼ��� 
	private String content = "";			// ����
	private String summary = "";			// ժҪ
	private String author = "";				// ����
	private String source = "";				// ��Դ
	private String outUrl = "";				// �ⲿ����
	private String icon = "";				// ͼ��
	private String articleTime = "";		// ����ʱ��

	private int top = 0;					// 1-��ͨ 2-�ö�
	private int status = 0;					// 1-���� 2-����
	private long creatorId = 0L;			// ������ID
	private long createTime = 0L;			// ����ʱ��
	private long updaterId = 0L;			// ����޸���ID
	private long updateTime = 0L;			// ����޸�ʱ��

	public Article()
	{
	}

	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}

	public String getChannelId()
	{
		return channelId;
	}
	public void setChannelId(String channelId)
	{
		this.channelId = channelId;
	}

	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getSubTitle()
	{
		return subTitle;
	}
	public void setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
	}

	public String getKeywords()
	{
		return keywords;
	}
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}

	public String getSummary()
	{
		return summary;
	}
	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getSource()
	{
		return source;
	}
	public void setSource(String source)
	{
		this.source = source;
	}

	public String getOutUrl()
	{
		return outUrl;
	}
	public void setOutUrl(String outUrl)
	{
		this.outUrl = outUrl;
	}

	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public String getArticleTime()
	{
		return articleTime;
	}
	public void setArticleTime(String articleTime)
	{
		this.articleTime = articleTime;
	}

	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getTop()
	{
		return top;
	}
	public void setTop(int top)
	{
		this.top = top;
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
		return createTime > 0L ? DateUtil.formatString(createTime, "yyyy-MM-dd HH:mm:ss") : "";
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