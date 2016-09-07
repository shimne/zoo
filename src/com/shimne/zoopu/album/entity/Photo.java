package com.shimne.zoopu.album.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Photo implements Serializable
{
	private static final long serialVersionUID = -8063005453393778221L;

	private long id = 0L;							// ͼƬ����
	private long albumId = 0L;						// ������

	private String title = "";						// ͼƬ����
	private String text = "";						// ͼƬ����
	private String photoUrl = "";					// ͼƬ��ַ
	private String targetUrl = "";					// �ⲿ����

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

	public long getAlbumId()
	{
		return albumId;
	}
	public void setAlbumId(long albumId)
	{
		this.albumId = albumId;
	}

	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}

	public String getPhotoUrl()
	{
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl)
	{
		this.photoUrl = photoUrl;
	}

	public String getTargetUrl()
	{
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl)
	{
		this.targetUrl = targetUrl;
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