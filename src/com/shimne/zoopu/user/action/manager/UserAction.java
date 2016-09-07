package com.shimne.zoopu.user.action.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shimne.util.NumberUtil;
import com.shimne.util.StringUtil;
import com.shimne.util.URLEncoder;
import com.shimne.zoopu.user.service.UserService;

@Controller
@RequestMapping("/zu/user")
public class UserAction
{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, String familyName, String lastName,
			String state, String city, String area, String currentState, String currentCity, String currentArea, String disable,
			String open, String startTime, String endTime, String page)
	{
		if ("GET".equalsIgnoreCase(request.getMethod()))
		{
			if (StringUtil.notTrimEmpty(familyName))
			{
				familyName = URLEncoder.decode(familyName);
			}

			if (StringUtil.notTrimEmpty(lastName))
			{
				lastName = URLEncoder.decode(lastName);
			}

			if (StringUtil.notTrimEmpty(state))
			{
				state = URLEncoder.decode(state);
			}

			if (StringUtil.notTrimEmpty(city))
			{
				city = URLEncoder.decode(city);
			}

			if (StringUtil.notTrimEmpty(area))
			{
				area = URLEncoder.decode(area);
			}

			if (StringUtil.notTrimEmpty(currentState))
			{
				currentState = URLEncoder.decode(currentState);
			}

			if (StringUtil.notTrimEmpty(currentCity))
			{
				currentCity = URLEncoder.decode(currentCity);
			}

			if (StringUtil.notTrimEmpty(currentArea))
			{
				currentArea = URLEncoder.decode(currentArea);
			}
		}

		try
		{
			return new ModelAndView("manager/user/user/list", "map", userService.queryUser(familyName, lastName, state, city, area, currentState, currentCity, currentArea, 
					disable, open, startTime, endTime, NumberUtil.parseInt(page), 20));
		}
		catch (Exception e)
		{
			e.printStackTrace();

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("message", "用户查询失败！");
			map.put("refererURL", "/zu/user/list.do");

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/view")
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response, String id)
	{
		return new ModelAndView("manager/user/user/view", "user", userService.findUserById(NumberUtil.parseLong(id)));
	}

	@RequestMapping(value = "/disable")
	public ModelAndView disable(HttpServletRequest request, HttpServletResponse response, String id)
	{
		userService.disableUser(NumberUtil.parseLong(id));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "禁用管理员成功！");
		map.put("refererURL", "/zu/user/list.do");

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/enable")
	public ModelAndView enable(HttpServletRequest request, HttpServletResponse response, String id)
	{
		userService.enableUser(NumberUtil.parseLong(id));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "启用管理员成功！");
		map.put("refererURL", "/zu/user/list.do");

		return new ModelAndView("manager/message", "map", map);
	}
}