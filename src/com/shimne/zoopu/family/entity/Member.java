package com.shimne.zoopu.family.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Member implements Serializable
{
	private static final long serialVersionUID = -8089741271709745262L;

	private long id = 0L;						// PK
	private long familyId = 0L;					// familyID
	private long parentId = 0L;					// ��ID

	private int type = 1;						// 1-�� 2-��Ů 
	private int live = 1;						// 1-�� 2-��

	private String familyName = "";				// ��
	private String lastName = "";				// ��
	private String usedName = "";				// ������
	private String sex = "";					// �Ա�
	private String birthday = "";				// ����
	private String customBirthday = "";			// �Զ�������
	private String deathday = "";				// ����
	private String customDeathday = "";			// �Զ������ 
	private String mobile = "";					// �ֻ���
	private String qq = "";						// QQ
	private String email = "";					// email
	private String state = "";					// ����ʡ��
	private String city = "";					// �������
	private String area = "";					// �������
	private String address = "";				// �����ַ
	private String currentState = "";			// ��סʡ��
	private String currentCity = "";			// ��ס����
	private String currentArea = "";			// ��ס����
	private String currentAddress = "";			// ��ס��ַ
	private String photo = "";					// ͷ��
	private String memo = "";					// ��ע˵��

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