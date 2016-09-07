package com.shimne.zoopu.family.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Member implements Serializable
{
	private static final long serialVersionUID = -8089741271709745262L;

	private long id = 0L;						// PK
	private long familyId = 0L;					// familyID
	private long parentId = 0L;					// 父ID

	private int type = 1;						// 1-妻 2-子女 
	private int live = 1;						// 1-存 2-亡

	private String familyName = "";				// 姓
	private String lastName = "";				// 名
	private String usedName = "";				// 曾用名
	private String sex = "";					// 性别
	private String birthday = "";				// 生日
	private String customBirthday = "";			// 自定义生日
	private String deathday = "";				// 忌日
	private String customDeathday = "";			// 自定义忌日 
	private String mobile = "";					// 手机号
	private String qq = "";						// QQ
	private String email = "";					// email
	private String state = "";					// 籍贯省份
	private String city = "";					// 籍贯城市
	private String area = "";					// 籍贯地区
	private String address = "";				// 籍贯地址
	private String currentState = "";			// 现住省份
	private String currentCity = "";			// 现住城市
	private String currentArea = "";			// 现住地区
	private String currentAddress = "";			// 现住地址
	private String photo = "";					// 头像
	private String memo = "";					// 备注说明

	private long creatorId = 0L;				// 创建人ID
	private long createTime = 0L;				// 创建时间
	private long updaterId = 0L;				// 修改人ID
	private long updateTime = 0L;				// 修改时间

	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}

	public long getFamilyId()
	{
		return familyId;
	}
	public void setFamilyId(long familyId)
	{
		this.familyId = familyId;
	}

	public long getParentId()
	{
		return parentId;
	}
	public void setParentId(long parentId)
	{
		this.parentId = parentId;
	}

	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}

	public int getLive()
	{
		return live;
	}
	public void setLive(int live)
	{
		this.live = live;
	}

	public String getFamilyName()
	{
		return familyName;
	}
	public void setFamilyName(String familyName)
	{
		this.familyName = familyName;
	}

	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUsedName()
	{
		return usedName;
	}
	public void setUsedName(String usedName)
	{
		this.usedName = usedName;
	}

	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getBirthday()
	{
		return birthday;
	}
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}

	public String getCustomBirthday()
	{
		return customBirthday;
	}
	public void setCustomBirthday(String customBirthday)
	{
		this.customBirthday = customBirthday;
	}

	public String getDeathday()
	{
		return deathday;
	}
	public void setDeathday(String deathday)
	{
		this.deathday = deathday;
	}

	public String getCustomDeathday()
	{
		return customDeathday;
	}
	public void setCustomDeathday(String customDeathday)
	{
		this.customDeathday = customDeathday;
	}

	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getQq()
	{
		return qq;
	}
	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}

	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}

	public String getArea()
	{
		return area;
	}
	public void setArea(String area)
	{
		this.area = area;
	}

	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCurrentState()
	{
		return currentState;
	}
	public void setCurrentState(String currentState)
	{
		this.currentState = currentState;
	}

	public String getCurrentCity()
	{
		return currentCity;
	}
	public void setCurrentCity(String currentCity)
	{
		this.currentCity = currentCity;
	}

	public String getCurrentArea()
	{
		return currentArea;
	}
	public void setCurrentArea(String currentArea)
	{
		this.currentArea = currentArea;
	}

	public String getCurrentAddress()
	{
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress)
	{
		this.currentAddress = currentAddress;
	}

	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}

	public String getMemo()
	{
		return memo;
	}
	public void setMemo(String memo)
	{
		this.memo = memo;
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