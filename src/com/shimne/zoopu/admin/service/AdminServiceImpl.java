package com.shimne.zoopu.admin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.page.PageService;
import com.shimne.page.Pagination;
import com.shimne.util.DateUtil;
import com.shimne.util.NumberUtil;
import com.shimne.util.ObjectUtil;
import com.shimne.util.StringUtil;
import com.shimne.util.URLEncoder;
import com.shimne.zoopu.admin.dao.AdminDao;
import com.shimne.zoopu.admin.dao.DepartmentDao;
import com.shimne.zoopu.admin.dao.FunctionDao;
import com.shimne.zoopu.admin.entity.Admin;
import com.shimne.zoopu.admin.entity.Department;
import com.shimne.zoopu.admin.entity.Function;
import com.shimne.zoopu.common.manage.AdminContext;

@Service("adminService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private FunctionDao functionDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private PageService pageService;

	@Override
	@Transactional(readOnly = false)
	public Admin saveAdmin(Admin admin, String functionIds, AdminContext adminContext)
	{
		Assert.notNull(admin, ADMIN_IS_NULL);

		admin.setUsername(admin.getUsername().trim().toLowerCase());
		admin.setPassword(StringUtil.md5String(admin.getPassword()));
		admin.setCreatorId(adminContext.getAdminId());
		admin.setCreateTime(System.currentTimeMillis());

		adminDao.save(admin);

		if (!admin.isAdministrator())
		{
			List<String> functionIdList = null;

			if (StringUtil.notTrimEmpty(functionIds))
			{
				functionIdList = new ArrayList<String>();

				functionIdList.addAll(Arrays.asList(functionIds.split(",")));
			}

			saveAdminFunction(admin.getId(), functionIdList);
		}

		return admin;
	}

	/**
	 * 根据ID获取需要编辑的用户
	 * 
	 */
	@Override
	public Admin findAdminById(long id)
	{
		Admin admin = adminDao.findById(id);

		if (ObjectUtil.isNull(admin))
		{
			throw new NestedRuntimeException(ADMIN_NOT_EXIST);
		}

		return admin;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateAdminPassword(String oldPassword, String newPassword, AdminContext adminContext)
	{
		Admin admin = findAdminById(adminContext.getAdminId());

		if (!admin.getPassword().equals(StringUtil.md5String(oldPassword)))
		{
			throw new NestedRuntimeException(ADMIN_PASSWORD_FAIL);
		}

		admin.setPassword(StringUtil.md5String(newPassword));
		admin.setUpdaterId(adminContext.getAdminId());
		admin.setUpdateTime(System.currentTimeMillis());

		adminDao.update(admin);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateAdmin(Admin admin, String functionIds, AdminContext adminContext)
	{
		Assert.notNull(admin, ADMIN_IS_NULL);

		Admin adminTmp = findAdminById(admin.getId());

		if (StringUtil.notEmpty(admin.getPassword()))
		{
			adminTmp.setPassword(StringUtil.md5String(admin.getPassword()));
		}

		adminTmp.setName(admin.getName());
		adminTmp.setSex(admin.getSex());
		adminTmp.setPhone(admin.getPhone());
		adminTmp.setMobile(admin.getMobile());
		adminTmp.setEmail(admin.getEmail());
		adminTmp.setAdministrator(admin.isAdministrator());
		adminTmp.setDepartmentId(admin.getDepartmentId());
		adminTmp.setUpdaterId(adminContext.getAdminId());
		adminTmp.setUpdateTime(System.currentTimeMillis());

		adminDao.update(adminTmp);

		delteAdminFunctionByAdminId(admin.getId());

		if (!admin.isAdministrator())
		{
			List<String> functionIdList = null;

			if (StringUtil.notTrimEmpty(functionIds))
			{
				functionIdList = new ArrayList<String>();

				functionIdList.addAll(Arrays.asList(functionIds.split(",")));
			}

			saveAdminFunction(admin.getId(), functionIdList);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void disableAdmin(long id, AdminContext adminContext)
	{
		Admin admin = findAdminById(id);

		admin.setDisable(true);
		admin.setUpdaterId(adminContext.getAdminId());
		admin.setUpdateTime(System.currentTimeMillis());

		adminDao.update(admin);
	}

	@Override
	@Transactional(readOnly = false)
	public void enableAdmin(long id, AdminContext adminContext)
	{
		Admin admin = findAdminById(id);

		admin.setDisable(false);
		admin.setUpdaterId(adminContext.getAdminId());
		admin.setUpdateTime(System.currentTimeMillis());

		adminDao.update(admin);
	}

	@Override
	public AdminContext login(String username, String password, String ip)
	{
		Admin admin = adminDao.findByUsername(username.trim().toLowerCase());
		List<String> rights = null;
		Department department = null;

		if (ObjectUtil.isNull(admin))
		{
			throw new NestedRuntimeException(ADMIN_NOT_EXIST);
		}

		if (admin.isDisable())
		{
			throw new NestedRuntimeException(ADMIN_IS_DISABLE);
		}

		if (!admin.getPassword().equals(StringUtil.md5String(password)))
		{
			throw new NestedRuntimeException(ADMIN_PASSWORD_FAIL);
		}

		department = departmentDao.findById(admin.getDepartmentId());

		if (!admin.isAdministrator())
		{
			rights = new ArrayList<String>();

			List<Long> functionIds = adminDao.queryFunctionIdsByAdminId(admin.getId());

			if (ObjectUtil.notEmpty(functionIds))
			{
				for (long functionId : functionIds)
				{
					Function function = functionDao.findById(functionId);

					if (ObjectUtil.notNull(function))
					{
						String urls = function.getUrls();

						if (!StringUtil.isEmpty(urls))
						{
							String[] urlArray = urls.split("\\r\\n");

							for (String url : urlArray)
							{
								rights.add(url.trim());
							}
						}
					}
				}
			}
		}

		return new AdminContext(admin.getId(), admin.getName(), DateUtil.formatString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"), ip, admin.isAdministrator(), department, rights);
	}

	@Override
	public Map<String, Object> queryAdmin(String name, long departmentId, int currentPageNum, int maxPageRowCount)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		StringBuilder url = new StringBuilder("?page=${pageNum}");

		if (StringUtil.notTrimEmpty(name))
		{
			returnMap.put("name", name);
			params.put("name", "%" + name.trim() + "%");
			url.append("&name=").append(URLEncoder.encode(name));
		}

		if (departmentId > 0L)
		{
			returnMap.put("departmentId", departmentId);
			params.put("departmentId", departmentId);
			url.append("&departmentId=").append(departmentId);
		}

		int count = adminDao.count(params);

		if (count > 0)
		{
			Pagination<Admin> pagination = new Pagination<Admin>();

			pagination.setCurrentPageNum(currentPageNum);
			pagination.setMaxPageRowCount(maxPageRowCount);
			pagination.setTotalRowCount(count);
			pagination.setUrl(url.toString());

			pageService.build(pagination);

			params.put("start", pagination.getStartPageRowCount());
			params.put("max", pagination.getMaxPageRowCount());

			returnMap.put("pageContents", pagination.getPageContents());
			returnMap.put("datas", adminDao.query(params));
		}

		return returnMap;
	}
	
	@Override
	public boolean checkAdmin(String username)
	{
		Admin admin = adminDao.findByUsername(username.trim().toLowerCase());

		if (ObjectUtil.isNull(admin))
		{
			return true;
		}

		return false;
	}

	@Override
	public List<Long> queryFunctionIdsByAdminId(long adminId)
	{
		return adminDao.queryFunctionIdsByAdminId(adminId);
	}

	private void saveAdminFunction(long adminId, List<String> functionIds)
	{
		if (ObjectUtil.notEmpty(functionIds))
		{
			for (String functionId : functionIds)
			{
				Map<String, Long> map = new HashMap<String, Long>();
				map.put("adminId", adminId);
				map.put("functionId", NumberUtil.parseLong(functionId));
				adminDao.saveAdminFucntion(map);
			}
		}
	}

	private void delteAdminFunctionByAdminId(long adminId)
	{
		adminDao.deleteAdminFunctionByAdminId(adminId);
	}
}