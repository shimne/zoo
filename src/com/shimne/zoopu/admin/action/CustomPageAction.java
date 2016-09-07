package com.shimne.zoopu.admin.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shimne.util.NumberUtil;
import com.shimne.util.StringUtil;
import com.shimne.util.URLEncoder;
import com.shimne.zoopu.admin.entity.CustomPage;
import com.shimne.zoopu.admin.service.CustomPageService;
import com.shimne.zoopu.common.manage.AdminContext;

@Controller
@RequestMapping("/zu/custom")
public class CustomPageAction
{
	@Autowired
	private CustomPageService customPageService;

	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/admin/customPage/add");
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, CustomPage customPage)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			customPageService.saveCustomPage(customPage, adminContext);
			map.put("message", "新增自定义页面成功！");
			map.put("refererURL", "/zu/custom/list.do");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "新增自定义页面失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/custom/list.do");
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, String id)
	{
		try
		{
			return new ModelAndView("manager/admin/customPage/edit", "customPage", customPageService.findCustomPageById(NumberUtil.parseLong(id)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "获取自定义页面失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/custom/list.do");
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, CustomPage customPage)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			customPageService.updateCustomPage(customPage, adminContext);
			map.put("message", "修改自定义页面成功！");
			map.put("refererURL", "/zu/custom/list.do");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "修改自定义页面失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/custom/list.do");
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, String id)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			customPageService.deleteCustomPageById(NumberUtil.parseLong(id));;
			map.put("message", "删除自定义页面成功！");
			map.put("refererURL", "/zu/custom/list.do");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "删除自定义页面失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/custom/list.do");
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, String name, String page)
	{
		if (request.getMethod().equalsIgnoreCase("GET"))
		{
			if (StringUtil.notTrimEmpty(name))
			{
				name = URLEncoder.decode(name);
			}
		}

		try
		{
			return new ModelAndView("manager/admin/customPage/list", "map", customPageService.queryCustomPage(name, NumberUtil.parseInt(page), 20));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "查询失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/custom/list.do");
			return new ModelAndView("manager/message", "map", map);
		}
	}
}