package com.shimne.zoopu.admin.entity;

import java.io.Serializable;

import com.shimne.util.DateUtil;

public class Admin implements Serializable
{
	private static final long serialVersionUID = 1725413121776927861L;

	private long id = 0L;							// 管理员代码

	private String username = "";					// 用户名
	private String password = "";					// 密码
	private String name = "";						// 名称
	private String sex = "";						// 性别
	private String phone = "";						// 电话
	private String mobile = "";						// 手机
	private String email = "";						// 邮箱
	private long departmentId = 0L;					// 部门ID

	private boolean administrator = true;			// 是否是超级管理员
	private boolean disable = false;				// 是否禁用

	private long creatorId = 0L;					// 创建人代码
	private long createTime = 0L;					// 创建时间
	private long updaterId = 0L;					// 最后修改人ID
	private long updateTime = 0L;					// 最后修改时间

	public Admin()
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

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

	public long getDepartmentId()
	{
		return departmentId;
	}
	public void setDepartmentId(long departmentId)
	{
		this.departmentId = departmentId;
	}

	public boolean isAdministrator()
	{
		return administrator;
	}
	public void setAdministrator(boolean administrator)
	{
		this.administrator = administrator;
	}

	public boolean isDisable()
	{
		return disable;
	}
	public void setDisable(boolean disable)
	{
		this.disable = disable;
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