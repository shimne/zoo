package com.shimne.zoopu.common.web;

import java.io.Serializable;

/**
 * 用户信息
 * 
 * @author zhaoshe
 * @date 2016-01-14
 */
public class UserContext implements Serializable
{
	private static final long serialVersionUID = 8965393375486296893L;

	private long userId = 0L;

	private String username = "";		// 用户名
	private String familyName = "";		// 密码
	private String lastName = "";		// 手机号
	private String sex = "";			// 性别
	private String loginTime = "";		// 登录时间
	private String loginIp = "";		// 登录IP

	public UserContext()
	{
	}

	public UserContext(long userId,
			String username,
			String familyName,
			String lastName,
			String sex,
			String loginTime,
			String loginIp)
	{
		this.userId = userId;
		this.username = username;
		this.familyName = familyName;
		this.lastName = lastName;
		this.sex = sex;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
	}

	public long getUserId()
	{
		return userId;
	}
	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
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

	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getLoginTime()
	{
		return loginTime;
	}
	public void setLoginTime(String loginTime)
	{
		this.loginTime = loginTime;
	}

	public String getLoginIp()
	{
		return loginIp;
	}
	public void setLoginIp(String loginIp)
	{
		this.loginIp = loginIp;
	}
}