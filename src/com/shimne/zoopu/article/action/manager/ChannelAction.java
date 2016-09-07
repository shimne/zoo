package com.shimne.zoopu.article.action.manager;

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
import com.shimne.zoopu.article.entity.Channel;
import com.shimne.zoopu.article.service.ChannelService;
import com.shimne.zoopu.common.manage.AdminContext;

@Controller
@RequestMapping("/zu/channel")
public class ChannelAction
{
	@Autowired
	private ChannelService channelService;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, String id)
	{
		return new ModelAndView("manager/article/channel/index", "id", id);
	}

	@RequestMapping(value = "/left")
	public ModelAndView left(HttpServletRequest request, HttpServletResponse response, String id)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			map.put("id", NumberUtil.parseLong(id));
			map.put("data", channelService.getTreeData());

			return new ModelAndView("manager/article/channel/left", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "频道获取失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/channel/index.do?id=" + id);

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/article/channel/main");
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
				map.put("parentName", "作为一级频道");
			}
			else
			{
				Channel channel = channelService.findChannelById(NumberUtil.parseLong(id));

				map.put("parentId", channel.getId());
				map.put("parentName", channel.getName());
			}

			return new ModelAndView("manager/article/channel/add", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "父频道获取失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/channel/index.do?id=" + id);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Channel channel)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			channelService.saveChannel(channel, adminContext);
			map.put("message", "新增频道成功！");
			map.put("target", "_parent");
			map.put("refererURL", "/zu/channel/index.do?id=" + channel.getParentId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "新增频道失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/channel/index.do?id=" + channel.getParentId());
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, String id, String parentId)
	{
		try
		{
			return new ModelAndView("manager/article/channel/edit", "channel", channelService.findChannelById(NumberUtil.parseLong(id)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "获取频道失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/channel/index.do?id=" + parentId);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, Channel channel)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			channelService.updateChannel(channel, adminContext);

			map.put("message", "修改频道成功！");
			map.put("target", "_parent");
			map.put("refererURL", "/zu/channel/index.do?id=" + channel.getParentId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "修改频道失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/channel/index.do?id=" + channel.getParentId());
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, String id, String parentId)
	{
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			channelService.deleteChannelById(NumberUtil.parseLong(id));

			map.put("message", "删除频道成功！");
			map.put("target", "_parent");
			map.put("refererURL", "/zu/channel/index.do?id=" + parentId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "删除频道失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/channel/index.do?id=" + parentId);
		}

		return new ModelAndView("manager/message", "map", map);
	}
}
