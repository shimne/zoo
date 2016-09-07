package com.shimne.zoopu.admin.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Function implements Serializable
{
	private static final long serialVersionUID = 4989283441655120651L;

	private long id = 0L;							// PK

	private long parentId = 0L;						// 父ID
	private String name = "";						// 名称
	private String description = "";				// 描述
	private String urls = "";             			// 权限路径			

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

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUrls()
	{
		return urls;
	}
	public void setUrls(String urls)
	{
		this.urls = urls;
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