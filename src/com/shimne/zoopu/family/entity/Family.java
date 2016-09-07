package com.shimne.zoopu.family.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Family implements Serializable
{
	private static final long serialVersionUID = -5752678488968301815L;

	private long id = 0L;						// ID

	private String familyName = "";				// ��
	private String treeName = "";				// ��
	private String hallName = "";				// ��
	private String password = "";				// ����
	private String icon = "";					// ͼƬ
	private String preface = "";				// ǰ��
	private String origin = "";					// ��Դ
	private String teaching = "";				// ��ѵ
	private String seniority = "";				// ����

	private int status = 1;						// 1-������ 2-����

	private long creatorId = 0L;				// ������ID
	private long createTime = 0L;				// ����ʱ��
	private long updaterId = 0L;				// �޸���ID
	private long updateTime = 0L;				// �޸�ʱ��

	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}

	public String getFamilyName()
	{
		return familyName;
	}
	public void setFamilyName(String familyName)
	{
		this.familyName = familyName;
	}

	public String getTreeName()
	{
		return treeName;
	}
	public void setTreeName(String treeName)
	{
		this.treeName = treeName;
	}

	public String getHallName()
	{
		return hallName;
	}
	public void setHallName(String hallName)
	{
		this.hallName = hallName;
	}

	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public String getPreface()
	{
		return preface;
	}
	public void setPreface(String preface)
	{
		this.preface = preface;
	}

	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	public String getTeaching()
	{
		return teaching;
	}
	public void setTeaching(String teaching)
	{
		this.teaching = teaching;
	}

	public String getSeniority()
	{
		return seniority;
	}
	public void setSeniority(String seniority)
	{
		this.seniority = seniority;
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
		return createTime > 0L ? DateUtil.formatString(createTime, "yyyy-MM-dd HH:mm") : "";
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