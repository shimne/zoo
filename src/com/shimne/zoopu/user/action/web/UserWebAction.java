package com.shimne.zoopu.user.action.web;

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
import com.shimne.util.ServletUtil;
import com.shimne.zoopu.common.web.UserContext;
import com.shimne.zoopu.user.entity.User;
import com.shimne.zoopu.user.service.UserService;

@Controller
@RequestMapping("/zoopu/user")
public class UserWebAction
{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, User user)
	{
		String ip = ServletUtil.getRemoteAddr(request);

		UserContext userContext = userService.saveUser(user, ip);

		request.getSession().setAttribute("userContext", userContext);
		request.getSession().setMaxInactiveInterval(60 * 60 * 4);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("message", "用户注册成功！");
		map.put("refererURL", "/");

		return new ModelAndView("web/message", "map", map);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		UserContext userContext = (UserContext) request.getSession().getAttribute("userContext");

		return new ModelAndView("web/user/user/edit", "user", userService.findUserById(userContext.getUserId()));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, User user)
	{
		userService.updateUser(user);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("message", "用户信息修改成功！");
		map.put("refererURL", "/");

		return new ModelAndView("web/message", "map", map);
	}

	@RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
	public ModelAndView modifyPassword(HttpServletRequest request, HttpServletResponse response, String password, String newPassword)
	{
		UserContext userContext = (UserContext) request.getSession().getAttribute("userContext");

		userService.modifyPassword(userContext.getUsername(), password, newPassword);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("message", "用户密码修改成功！");
		map.put("refererURL", "/");

		return new ModelAndView("web/message", "map", map);
	}

	@RequestMapping(value =  "/query")
	public ModelAndView query(HttpServletRequest request, HttpServletResponse response, String familyName, String lastName,
			String state, String city, String area, String currentState, String currentCity, String currentArea, 
			String open, String startTime, String endTime, String page)
	{
		return new ModelAndView("manager/user/user/list", "map", userService.queryUser(familyName, lastName, state, city, area, currentState, currentCity, currentArea, 
				"0", open, startTime, endTime, NumberUtil.parseInt(page), 20));
	}
}