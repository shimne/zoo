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
import com.shimne.zoopu.admin.entity.Function;
import com.shimne.zoopu.admin.service.FunctionService;
import com.shimne.zoopu.common.manage.AdminContext;

@Controller
@RequestMapping("/zu/function")
public class FunctionAction
{
	@Autowired
	private FunctionService functionService;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, String id)
	{
		return new ModelAndView("manager/admin/function/index", "id", id);
	}

	@RequestMapping(value = "/left")
	public ModelAndView left(HttpServletRequest request, HttpServletResponse response, String id)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			map.put("id", NumberUtil.parseLong(id));
			map.put("data", functionService.getTreeData());

			return new ModelAndView("manager/admin/function/left", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "权限获取失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/function/index.do?id=" + id);

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/admin/function/main");
	}

	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response, String id)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			if (StringUtil.isTrimEmpty(id))
			{
				map.put("parentId", 0);
				map.put("parentName", "作为一级权限");
			}
			else
			{
				Function function = functionService.findFunctionById(NumberUtil.parseLong(id));

				map.put("parentId", function.getId());
				map.put("parentName", function.getName());
			}

			return new ModelAndView("manager/admin/function/add", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "父权限获取失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/function/index.do?id=" + id);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Function function)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>(3);

		try
		{
			functionService.saveFunction(function, adminContext);
			map.put("message", "新增权限成功！");
			map.put("target", "_parent");
			map.put("refererURL", "/zu/function/index.do?id=" + function.getParentId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "新增权限失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/function/index.do?id=" + function.getParentId());
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, String id, String parentId)
	{
		try
		{
			return new ModelAndView("manager/admin/function/edit", "function", functionService.findFunctionById(NumberUtil.parseLong(id)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "获取权限失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/function/index.do?id=" + parentId);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, Function function)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			functionService.updateFunction(function, adminContext);
			map.put("message", "修改权限成功！");
			map.put("target", "_parent");
			map.put("refererURL", "/zu/function/index.do?id=" + function.getParentId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "修改权限失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/function/index.do?id=" + function.getParentId());
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, String id, String parentId)
	{
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			functionService.deleteFunctionById(NumberUtil.parseLong(id));
			map.put("message", "删除权限成功！");
			map.put("target", "_parent");
			map.put("refererURL", "/zu/function/index.do?id=" + parentId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "删除权限失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/function/index.do?id=" + parentId);
		}

		return new ModelAndView("manager/message", "map", map);
	}
}