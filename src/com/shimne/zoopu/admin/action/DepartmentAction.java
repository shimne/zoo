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
import com.shimne.zoopu.admin.entity.Department;
import com.shimne.zoopu.admin.service.DepartmentService;
import com.shimne.zoopu.common.manage.AdminContext;

@Controller
@RequestMapping("/zu/department")
public class DepartmentAction
{
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, String id)
	{
		return new ModelAndView("manager/admin/department/index", "id", id);
	}

	@RequestMapping(value = "/left")
	public ModelAndView left(HttpServletRequest request, HttpServletResponse response, String id)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			map.put("id", NumberUtil.parseLong(id));
			map.put("data", departmentService.getTreeData());

			return new ModelAndView("manager/admin/department/left", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "部门获取失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/department/index.do?id=" + id);

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/admin/department/main");
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
				map.put("parentName", "作为一级部门");
			}
			else
			{
				Department department = departmentService.findDepartmentById(NumberUtil.parseLong(id));

				map.put("parentId", department.getId());
				map.put("parentName", department.getName());
			}

			return new ModelAndView("manager/admin/department/add", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "父部门获取失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/department/index.do?id=" + id);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Department department)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>(3);

		try
		{
			departmentService.saveDepartment(department, adminContext);
			map.put("message", "新增部门成功！");
			map.put("target", "_parent");
			map.put("refererURL", "/zu/department/index.do?id=" + department.getParentId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "新增部门失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/department/index.do?id=" + department.getParentId());
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, String id, String parentId)
	{
		try
		{
			return new ModelAndView("manager/admin/department/edit", "department", departmentService.findDepartmentById(NumberUtil.parseLong(id)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "获取部门失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/department/index.do?id=" + parentId);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, Department department)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			departmentService.updateDepartment(department, adminContext);
			map.put("message", "修改部门成功！");
			map.put("target", "_parent");
			map.put("refererURL", "/zu/department/index.do?id=" + department.getParentId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "修改部门失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/department/index.do?id=" + department.getParentId());
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, String id, String parentId)
	{
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			departmentService.deleteDepartmentById(NumberUtil.parseLong(id));
			map.put("message", "删除部门成功！");
			map.put("target", "_parent");
			map.put("refererURL", "/zu/department/index.do?id=" + parentId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "删除部门失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/department/index.do?id=" + parentId);
		}

		return new ModelAndView("manager/message", "map", map);
	}
}