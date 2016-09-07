package com.shimne.zoopu.common.manage;

import java.io.Serializable;
import java.util.List;

import com.shimne.zoopu.admin.entity.Department;

public class AdminContext implements Serializable
{
	private static final long serialVersionUID = -6540374601457391465L;

	private long adminId;					// 管理员代码

	private String name;					// 管理员姓名
	private String loginTime;				// 登陆时间
	private String loginIp;					// 登陆IP地址
	private boolean administrator;			// 是否是超级管理员
	private Department department;			// 所属部门
	private List<String> rights;			// 权限地址

	public AdminContext()
	{
	}

	public AdminContext(long adminId,
			String name,
			String loginTime,
			String loginIp,
			boolean administrator,
			Department department,
			List<String> rights)
	{
		this.adminId = adminId;
		this.name = name;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
		this.administrator = administrator;
		this.department = department;
		this.rights = rights;
	}

	public long getAdminId()
	{
		return adminId;
	}
	public void setAdminId(long adminId)
	{
		this.adminId = adminId;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
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

	public boolean isAdministrator()
	{
		return administrator;
	}
	public void setAdministrator(boolean administrator)
	{
		this.administrator = administrator;
	}

	public Department getDepartment()
	{
		return department;
	}
	public void setDepartment(Department department)
	{
		this.department = department;
	}

	public List<String> getRights()
	{
		return rights;
	}
	public void setRights(List<String> rights)
	{
		this.rights = rights;
	}
}