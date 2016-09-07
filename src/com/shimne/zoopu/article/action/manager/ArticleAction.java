package com.shimne.zoopu.article.action.manager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shimne.util.DateUtil;
import com.shimne.util.ImageUtil2;
import com.shimne.util.NumberUtil;
import com.shimne.util.StringUtil;
import com.shimne.util.URLEncoder;
import com.shimne.zoopu.article.entity.Article;
import com.shimne.zoopu.article.entity.Channel;
import com.shimne.zoopu.article.service.ArticleService;
import com.shimne.zoopu.article.service.ChannelService;
import com.shimne.zoopu.common.manage.AdminContext;

@Controller
@RequestMapping("/zu/article")
public class ArticleAction
{
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ChannelService channelService;

	@RequestMapping(value = "/left")
	public ModelAndView left(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/article/left");
	}

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/article/article/index");
	}

	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/article/article/main");
	}

	@RequestMapping(value = "/inLeft")
	public ModelAndView inLeft(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			return new ModelAndView("manager/article/article/left", "data", channelService.getTreeData());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "获取频道失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/article/index.do");

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response, String channelId)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			map.put("channel", channelService.findChannelById(NumberUtil.parseLong(channelId)));
			map.put("currentTime", DateUtil.formatString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));

			return new ModelAndView("manager/article/article/add", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "获取频道失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/article/list.do?channelId=" + channelId);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multipartRequest, MultipartFile fileIcon, Article article)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			if (!fileIcon.isEmpty())
			{
				String suffix = ".jpg";
				String fileName = System.currentTimeMillis() + "";
				String basePath = request.getSession().getServletContext().getRealPath("/");
				String fielPath = "/article/icon/" + DateUtil.formatString(System.currentTimeMillis(), "yyyyMMdd") + "/";

				File folder = new File(basePath + fielPath);

				if (!folder.exists())
				{
					folder.mkdirs();
				}

				DiskFileItem df = (DiskFileItem) ((CommonsMultipartFile) fileIcon).getFileItem();

				ImageUtil2.createThumbnail(df.getStoreLocation().getAbsolutePath(), basePath + fielPath + fileName + suffix, 0, 0);
				ImageUtil2.createThumbnail(df.getStoreLocation().getAbsolutePath(), basePath + fielPath + fileName + "_w150" + suffix, 150, 0);

				article.setIcon(fielPath + fileName);
			}

			articleService.saveArtice(article, adminContext);

			map.put("message", "文章新增成功！");
			map.put("refererURL", "/zu/article/list.do?channelId=" + article.getChannelId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "文章新增失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/article/list.do?channelId=" + article.getChannelId());
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, String id, String channelId)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			Article article = articleService.findArticleById(NumberUtil.parseLong(id));
			Channel channel = channelService.findChannelById(NumberUtil.parseLong(channelId));

			map.put("article", article);
			map.put("channel", channel);

			return new ModelAndView("manager/article/article/edit", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("message", "获取文章失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/article/list.do?channelId=" + channelId);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multipartRequest, MultipartFile fileIcon, Article article)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			if (!fileIcon.isEmpty())
			{
				String suffix = ".jpg";
				String fileName = System.currentTimeMillis() + "";
				String basePath = request.getSession().getServletContext().getRealPath("/");
				String fielPath = "/article/icon/" + DateUtil.formatString(System.currentTimeMillis(), "yyyyMMdd") + "/";

				File folder = new File(basePath + fielPath);

				if (!folder.exists())
				{
					folder.mkdirs();
				}

				DiskFileItem df = (DiskFileItem) ((CommonsMultipartFile) fileIcon).getFileItem();

				ImageUtil2.createThumbnail(df.getStoreLocation().getAbsolutePath(), basePath + fielPath + fileName + suffix, 0, 0);
				ImageUtil2.createThumbnail(df.getStoreLocation().getAbsolutePath(), basePath + fielPath + fileName + "_w150" + suffix, 150, 0);

				article.setIcon(fielPath + fileName);
			}

			articleService.updateArtice(article, adminContext);
			
			map.put("message", "文章修改成功！");
			map.put("refererURL", "/zu/article/list.do?channelId=" + article.getChannelId());
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "文章修改失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/article/list.do?channelId=" + article.getChannelId());
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/top")
	public ModelAndView top(HttpServletRequest request, HttpServletResponse response, String id, String channelId)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			articleService.topArticleById(NumberUtil.parseLong(id), adminContext);
			map.put("message", "操作成功！");
			map.put("refererURL", "/zu/article/list.do?channelId=" + channelId);

			return new ModelAndView("manager/message", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "操作失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/article/list.do?channelId=" + channelId);

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/status")
	public ModelAndView updateStatus(HttpServletRequest request, HttpServletResponse response, String ids, String status, String channelId)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, String> map = new HashMap<String, String>();

		try
		{
			articleService.updateArticleStatusByIds(ids, NumberUtil.parseInt(status), adminContext);

			map.put("message", "操作成功！");
			map.put("refererURL", "/zu/article/list.do?channelId=" + channelId);
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "操作失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/article/list.do?channelId=" + channelId);
		}

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, String channelId, String title,
			String status, String top, String startTime, String endTime, String page)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");

		if ("GET".equalsIgnoreCase(request.getMethod()))
		{
			if (StringUtil.notTrimEmpty(title))
			{
				title = URLEncoder.decode(title, "GBK");
			}
		}

		try
		{
			long creatorId = adminContext.isAdministrator() ? 0L : adminContext.getAdminId();

			return new ModelAndView("manager/article/article/list", "map", articleService.queryArticle(creatorId, NumberUtil.parseLong(channelId), title, NumberUtil.parseInt(status), startTime, endTime, NumberUtil.parseInt(page), 20));
		}
		catch (Exception e)
		{
			e.printStackTrace();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "查询失败！<br/>" + e.getMessage());
			map.put("refererURL", "/zu/article/list.do?channelId=" + channelId);
			return new ModelAndView("manager/message", "map", map);
		}
	}
}