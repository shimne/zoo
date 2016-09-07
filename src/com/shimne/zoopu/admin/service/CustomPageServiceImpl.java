package com.shimne.zoopu.admin.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.page.PageService;
import com.shimne.page.Pagination;
import com.shimne.util.ObjectUtil;
import com.shimne.util.StringUtil;
import com.shimne.util.URLEncoder;
import com.shimne.zoopu.admin.dao.CustomPageDao;
import com.shimne.zoopu.admin.entity.CustomPage;
import com.shimne.zoopu.common.manage.AdminContext;

@Service("customPageService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CustomPageServiceImpl implements CustomPageService
{
	@Autowired
	private CustomPageDao customPageDao;
	@Autowired
	private PageService pageService;

	@Override
	@Transactional(readOnly = false)
	public void saveCustomPage(CustomPage customPage, AdminContext adminContext)
	{
		Assert.notNull(customPage, CUSTOMPAGE_IS_NULL);

		customPage.setCreatorId(adminContext.getAdminId());
		customPage.setCreateTime(System.currentTimeMillis());

		customPageDao.save(customPage);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateCustomPage(CustomPage customPage, AdminContext adminContext)
	{
		Assert.notNull(customPage, CUSTOMPAGE_IS_NULL);

		CustomPage customPageTmp = findCustomPageById(customPage.getId());

		customPageTmp.setName(customPage.getName());
		customPageTmp.setTitle(customPage.getTitle());
		customPageTmp.setKeywords(customPage.getKeywords());
		customPageTmp.setDescription(customPage.getDescription());
		customPageTmp.setContent(customPage.getContent());
		customPageTmp.setUpdaterId(adminContext.getAdminId());
		customPageTmp.setUpdateTime(System.currentTimeMillis());

		customPageDao.update(customPageTmp);
	}

	@Override
	public CustomPage findCustomPageById(long id)
	{
		CustomPage customPage = customPageDao.findById(id);

		if (ObjectUtil.isNull(customPage))
		{
			throw new NestedRuntimeException(CUSTOMPAGE_NOT_EXISIT);
		}

		return customPage;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteCustomPageById(long id)
	{
		customPageDao.deleteById(id);
	}

	@Override
	public Map<String, Object> queryCustomPage(String name, int currentPageNum, int maxPageRowCount)
	{
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder urlBuilder = new StringBuilder("?page=${pageNum}");

		if (StringUtil.notTrimEmpty(name))
		{
			returnMap.put("name", name);
			params.put("name", "%" + name + "%");
			urlBuilder.append("&name=").append(URLEncoder.encode(name));
		}

		int count = customPageDao.count(params);

		if (count > 0)
		{
			Pagination<CustomPage> pagination = new Pagination<CustomPage>();

			pagination.setCurrentPageNum(currentPageNum);
			pagination.setMaxPageRowCount(maxPageRowCount);
			pagination.setTotalRowCount(count);
			pagination.setUrl(urlBuilder.toString());

			pageService.build(pagination);

			params.put("start", pagination.getStartPageRowCount());
			params.put("max", pagination.getMaxPageRowCount());

			returnMap.put("pageContents", pagination.getPageContents());
			returnMap.put("datas", customPageDao.query(params));
		}
		
		return returnMap;
	}
}
