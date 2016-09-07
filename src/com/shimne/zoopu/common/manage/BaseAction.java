package com.shimne.zoopu.common.manage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.util.ServletUtil;
import com.shimne.util.StringUtil;
import com.shimne.zoopu.admin.service.AdminService;

@Controller
@RequestMapping("/zu")
public class BaseAction
{
	@Autowired
	private AdminService adminService;

	@RequestMapping(value="/desktop")
	public ModelAndView desktop(HttpServletRequest request)
	{
		return new ModelAndView("manager/desktop");
	}

	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest request)
	{
		return new ModelAndView("manager/main");
	}

	@RequestMapping(value="/norights")
	public ModelAndView norights(HttpServletRequest request, @RequestParam("serverName") String serverName, @RequestParam("requestURI") String requestURI)
	{
		Map<String, Object> returnMap = new HashMap<String, Object>(2);
		returnMap.put("serverName", serverName);
		returnMap.put("requestURI", requestURI);

		return new ModelAndView("manager/norights", "returnMap", returnMap);
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, String username, String password)
	{
		String loginMessage = "";

		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password))
		{
			loginMessage = "请输入用户名和密码！";
		}
		else
		{
			try
			{
				AdminContext adminContext = adminService.login(username, password, ServletUtil.getRemoteAddr(request));
				request.getSession().setAttribute("adminContext", adminContext);
				request.getSession().setMaxInactiveInterval(60 * 60 * 4);
			}
			catch (NestedRuntimeException e)
			{
				e.printStackTrace();
				loginMessage = e.getMessageCode();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				loginMessage = "用户名或密码错误！";
			}
		}

		return new ModelAndView("manager/loginMessage", "loginMessage", loginMessage);
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().removeAttribute("adminContext");

		try
		{
			response.sendRedirect("/zu/index.do");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}