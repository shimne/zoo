package com.shimne.zoopu.user.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class User implements Serializable
{
	private static final long serialVersionUID = -3366198828829308599L;

	private long id = 0L;				// ����

	private String username = "";		// �û���
	private String password = "";		// ����
	private String idNumber = "";		// ���֤��
	private String familyName = "";		// ��
	private String lastName = "";		// ��
	private String usedName = "";		// ������
	private String sex = "";			// �Ա�
	private String birthday = "";		// ����
	private String mobile = "";			// �ֻ���
	private String qq = "";				// QQ
	private String email = "";			// ����
	private String state = "";			// ����ʡ��
	private String city = "";			// �������
	private String area = "";			// �������
	private String address = "";		// �����ַ
	private String currentState = "";	// ��סʡ��
	private String currentCity = "";	// ��ס����
	private String currentArea = "";	// ��ס����
	private String currentAddress = "";	// ��ס��ַ
	private String postcode = "";		// �ʱ�
	private String photo = "";			// ͷ��

	private boolean open = false; 		// �Ƿ񹫿���ϵ��Ϣ
	private boolean disable = false;	// �Ƿ����
	private long registerTime = 0L;		// ע��ʱ��
	private long updateTime = 0L;		// ����޸�ʱ��

	public User()
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

	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getIdNumber()
	{
		return idNumber;
	}
	public void setIdNumber(String idNumber)
	{
		this.idNumber = idNumber;
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

	public String getPostcode()
	{
		return postcode;
	}
	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}

	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}

	public boolean isOpen()
	{
		return open;
	}
	public void setOpen(boolean open)
	{
		this.open = open;
	}

	public boolean isDisable()
	{
		return disable;
	}
	public void setDisable(boolean disable)
	{
		this.disable = disable;
	}

	public long getRegisterTime()
	{
		return registerTime;
	}
	public String getRegisterTime1()
	{
		if (registerTime > 0L)
		{
			return DateUtil.formatString(registerTime, "yyyy-MM-dd HH:mm");
		}

		return "";
	}
	public void setRegisterTime(long registerTime)
	{
		this.registerTime = registerTime;
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