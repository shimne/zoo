package com.shimne.zoopu.admin.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shimne.util.NumberUtil;
import com.shimne.util.StringUtil;
import com.shimne.util.URLEncoder;
import com.shimne.zoopu.admin.entity.Admin;
import com.shimne.zoopu.admin.entity.Department;
import com.shimne.zoopu.admin.service.AdminService;
import com.shimne.zoopu.admin.service.DepartmentService;
import com.shimne.zoopu.admin.service.FunctionService;
import com.shimne.zoopu.common.manage.AdminContext;

@Controller
@RequestMapping("/zu/admin")
public class AdminAction
{
	@Autowired
	private AdminService adminService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private FunctionService functionService;

	@RequestMapping(value = "/left")
	public ModelAndView left(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/admin/left");
	}

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/admin/admin/index");
	}

	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/admin/admin/main");
	}

	@RequestMapping(value = "/inLeft")
	public ModelAndView inLeft(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			return new ModelAndView("manager/admin/admin/left", "data", departmentService.getTreeData());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "部门获取失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/admin/index.do");

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response, String departmentId)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			map.put("department", departmentService.findDepartmentById(NumberUtil.parseLong(departmentId)));
			map.put("functionData", functionService.getTreeData());

			return new ModelAndView("manager/admin/admin/add", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "新增失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + departmentId);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Admin admin, String functionIds)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			adminService.saveAdmin(admin, functionIds, adminContext);
			map.put("message", "新增管理员成功！");
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + admin.getDepartmentId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "新增管理员失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + admin.getDepartmentId());
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/edit" )
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, String id, String departmentId)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			Admin admin = adminService.findAdminById(NumberUtil.parseLong(id));
			Department department = departmentService.findDepartmentById(NumberUtil.parseLong(departmentId));
			List<Long> functionIds = adminService.queryFunctionIdsByAdminId(NumberUtil.parseLong(id));
			String functionData = functionService.getTreeData();
			String departmentData = departmentService.getTreeData();

			map.put("admin", admin);
			map.put("department", department);
			map.put("functionIds", functionIds);
			map.put("functionData", functionData);
			map.put("departmentData", departmentData);

			return new ModelAndView("manager/admin/admin/edit", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "获取用户失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + departmentId);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, Admin admin, String functionIds)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			adminService.updateAdmin(admin, functionIds, adminContext);
			map.put("message", "更新管理员成功！");
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + admin.getDepartmentId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "更新管理员失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + admin.getDepartmentId());
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/editPassword")
	public ModelAndView editPassword(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/admin/admin/editPassword");
	}

	@RequestMapping(value = "/updatePassword")
	public ModelAndView updatePassword(HttpServletRequest request, HttpServletResponse response, String oldPassword, String newPassword)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			adminService.updateAdminPassword(oldPassword, newPassword, adminContext);
			map.put("message", "密码修改成功！");
			map.put("refererURL", "/zu/admin/editPassword.do");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "密码修改失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/admin/editPassword.do");
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/disable")
	public ModelAndView disable(HttpServletRequest request, HttpServletResponse response, String id, String departmentId)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			adminService.disableAdmin(NumberUtil.parseLong(id), adminContext);
			map.put("message", "禁用管理员成功！");
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + departmentId);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "禁用管理员失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + departmentId);
		}
		
		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/enable")
	public ModelAndView enable(HttpServletRequest request, HttpServletResponse response, String id, String departmentId)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			adminService.enableAdmin(NumberUtil.parseLong(id), adminContext);
			map.put("message", "禁用管理员成功！");
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + departmentId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "禁用管理员失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + departmentId);
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, String name, String departmentId, String page)
	{
		if ("GET".equalsIgnoreCase(request.getMethod()))
		{
			if (StringUtil.notTrimEmpty(name))
			{
				name = URLEncoder.decode(name);
			}
		}

		try
		{
			return new ModelAndView("manager/admin/admin/list", "map", adminService.queryAdmin(name, NumberUtil.parseLong(departmentId), NumberUtil.parseInt(page), 20));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "查询失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/admin/list.do?departmentId=" + departmentId);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/checkUsername")
	@ResponseBody
	public void checkUsername(HttpServletRequest request, HttpServletResponse response, String param)
	{
		String message = "";

		if (adminService.checkAdmin(param))
		{
			message = "{\"info\" : \"用户名可用！\", \"status\" : \"y\"}";
		}
		else
		{
			message = "{\"info\" : \"用户名已占用！\", \"status\" : \"n\"}";
		}

		try
		{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(message);
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}