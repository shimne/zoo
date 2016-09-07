package com.shimne.zoopu.admin.service;

import java.util.Map;

import com.shimne.zoopu.admin.entity.CustomPage;
import com.shimne.zoopu.common.manage.AdminContext;

public interface CustomPageService
{
	public static final String CUSTOMPAGE_IS_NULL = "";
	public static final String CUSTOMPAGE_NOT_EXISIT = "";

	void saveCustomPage(CustomPage customPage, AdminContext adminContext);

	void updateCustomPage(CustomPage customPage, AdminContext adminContext);

	CustomPage findCustomPageById(long id);

	void deleteCustomPageById(long id);

	Map<String, Object> queryCustomPage(String name, int currentPageNum, int maxPageRowCount);
}