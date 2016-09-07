package com.shimne.zoopu.util.interceptors;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shimne.zoopu.common.manage.AdminContext;

public class AdminAuthInterceptor implements HandlerInterceptor
{
	private static List<String> openRights = new ArrayList<String>();

	static
	{
		openRights.add("/zu/admin/editBase.sn");
		openRights.add("/zu/admin/updateBase.sn");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{		
		AdminContext adminContext = new AdminContext();
		adminContext.setAdminId(1L);
		adminContext.setName("系统管理员");
		adminContext.setAdministrator(true);
		adminContext.setLoginIp("127.0.0.1");

		/*AdminContext adminContext = (AdminContext) request.getSession().getAttribute("adminContext");

		if (adminContext == null)
		{
			response.sendRedirect("/");

			return false;
		}*/

//		if (!adminContext.isAdministrator())
//		{
//			if (!openRights.contains(request.getRequestURI()))
//			{
//				if (ObjectUtil.isEmpty(adminContext.getRights()) || !adminContext.getRights().contains(request.getRequestURI()))
//				{
//					request.setAttribute("serverName", request.getServerName());
//					request.setAttribute("requestURI", request.getRequestURI());
//
//					response.sendRedirect("/norights.do?serverName=" + request.getServerName() + "&requestURI=" + request.getRequestURI());
//
//					return false;
//				}
//			}
//		}

		if (request.getParameter("page") == null)
		{
			request.setAttribute("page", 0);
		}

		request.setAttribute("adminContext", adminContext);
		request.setAttribute("refererURL", request.getHeader("REFERER"));

		return true;
	}
}