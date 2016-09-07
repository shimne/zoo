package com.shimne.zoopu.album.action.manager;

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
import com.shimne.zoopu.album.entity.Album;
import com.shimne.zoopu.album.service.AlbumService;
import com.shimne.zoopu.common.manage.AdminContext;

@Controller
@RequestMapping("/zu/album")
public class AlbumAction
{
	@Autowired
	private AlbumService albumService;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, String id)
	{
		return new ModelAndView("manager/album/album/index", "id", id);
	}

	@RequestMapping(value = "/left")
	public ModelAndView left(HttpServletRequest request, HttpServletResponse response, String id)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			map.put("id", NumberUtil.parseLong(id));
			map.put("data", albumService.getTreeData());

			return new ModelAndView("manager/album/album/left", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "频道相册失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/album/index.do?id=" + id);

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/album/album/main");
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
				Album album = albumService.findAlbumById(NumberUtil.parseLong(id));

				map.put("parentId", album.getId());
				map.put("parentName", album.getName());
			}

			return new ModelAndView("manager/album/album/add", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "父相册获取失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/album/index.do?id=" + id);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Album album)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			albumService.saveAlbum(album, adminContext);
			map.put("message", "保存相册成功！");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "保存相册错误。<br/>" + e.getMessage());
		}

		map.put("target", "_parent");
		map.put("refererURL", "/zu/album/index.do?id=" + album.getId());

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, String id)
	{
		try
		{
			return new ModelAndView("manager/album/album/edit", "album", albumService.findAlbumById(NumberUtil.parseLong(id)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "相册获取失败<br>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/album/index.do?id=" + id);

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, Album album)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			albumService.updateAlbum(album, adminContext);

			map.put("message", "相册更新成功！");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "相册更新失败<br>" + e.getMessage());

		}

		map.put("target", "_parent");
		map.put("refererURL", "/zu/album/index.do?id=" + album.getId());

		return new ModelAndView();
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, String id, String parentId)
	{
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			albumService.deleteAlbumById(NumberUtil.parseLong(id));

			map.put("message", "删除相册成功！");
			map.put("target", "_parent");
			map.put("refererURL", "/zu/album/index.do?id=" + parentId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "删除相册失败！<br/>" + e.getMessage());
			map.put("target", "_parent");
			map.put("refererURL", "/zu/album/index.do?id=" + parentId);
		}

		return new ModelAndView("manager/message", "map", map);
	}
/*
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, String name, String page)
	{
		try
		{
			if (request.getMethod().equalsIgnoreCase("GET"))
			{
				if (StringUtil.notTrimEmpty(name))
				{
					name = URLEncoder.decode(name);
				}
			}

			return new ModelAndView("manager/album/album/list", "map", albumService.queryAlbum(name, NumberUtil.parseInt(page), 20));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "相册查询失败。<br/>" + e.getMessage());
			map.put("refererURL", "/zu/album/list.do");

			return new ModelAndView("manager/message", "map", map);
		}
	}*/
}