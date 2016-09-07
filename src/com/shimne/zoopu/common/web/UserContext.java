package com.shimne.zoopu.common.web;

import java.io.Serializable;

/**
 * �û���Ϣ
 * 
 * @author zhaoshe
 * @date 2016-01-14
 */
public class UserContext implements Serializable
{
	private static final long serialVersionUID = 8965393375486296893L;

	private long userId = 0L;

	private String username = "";		// �û���
	private String familyName = "";		// ����
	private String lastName = "";		// �ֻ���
	private String sex = "";			// �Ա�
	private String loginTime = "";		// ��¼ʱ��
	private String loginIp = "";		// ��¼IP

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