package com.shimne.zoopu.user.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class User implements Serializable
{
	private static final long serialVersionUID = -3366198828829308599L;

	private long id = 0L;				// 主键

	private String username = "";		// 用户名
	private String password = "";		// 密码
	private String idNumber = "";		// 身份证号
	private String familyName = "";		// 姓
	private String lastName = "";		// 名
	private String usedName = "";		// 曾用名
	private String sex = "";			// 性别
	private String birthday = "";		// 生日
	private String mobile = "";			// 手机号
	private String qq = "";				// QQ
	private String email = "";			// 邮箱
	private String state = "";			// 籍贯省份
	private String city = "";			// 籍贯城市
	private String area = "";			// 籍贯地区
	private String address = "";		// 籍贯地址
	private String currentState = "";	// 现住省份
	private String currentCity = "";	// 现住城市
	private String currentArea = "";	// 现住地区
	private String currentAddress = "";	// 现住地址
	private String postcode = "";		// 邮编
	private String photo = "";			// 头像

	private boolean open = false; 		// 是否公开联系信息
	private boolean disable = false;	// 是否禁用
	private long registerTime = 0L;		// 注册时间
	private long updateTime = 0L;		// 最后修改时间

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