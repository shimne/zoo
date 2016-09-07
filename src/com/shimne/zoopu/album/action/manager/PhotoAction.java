package com.shimne.zoopu.album.action.manager;

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
import com.shimne.zoopu.album.entity.Album;
import com.shimne.zoopu.album.entity.Photo;
import com.shimne.zoopu.album.service.AlbumService;
import com.shimne.zoopu.album.service.PhotoService;
import com.shimne.zoopu.common.manage.AdminContext;

@Controller
@RequestMapping("/zu/photo")
public class PhotoAction
{
	@Autowired
	private PhotoService photoService;
	@Autowired
	private AlbumService albumService;

	@RequestMapping(value = "/left")
	public ModelAndView left(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/album/left");
	}

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/album/photo/index");
	}

	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("manager/album/photo/main");
	}

	@RequestMapping(value = "/inLeft")
	public ModelAndView inLeft(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			return new ModelAndView("manager/album/photo/left", "data", albumService.getTreeData());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "ªÒ»°œ‡≤· ß∞‹£°<br/>" + e.getMessage());
			map.put("refererURL", "/zu/photo/index.do");

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response, String albumId)
	{
		try
		{
			return new ModelAndView("manager/album/photo/add", "albumId", albumId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "ªÒ»°œ‡≤· ß∞‹£°<br/>" + e.getMessage());
			map.put("refererURL", "/zu/photo/list.do?albumId=" + albumId);
			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multipartRequest, MultipartFile image, Photo photo)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			if (!image.isEmpty())
			{
				String suffix = ".jpg";
				String fileName = System.currentTimeMillis() + "";
				String basePath = request.getSession().getServletContext().getRealPath("/");
				String fielPath = "/album/photo/" + DateUtil.formatString(System.currentTimeMillis(), "yyyyMMdd") + "/";

				File folder = new File(basePath + fielPath);

				if (!folder.exists())
				{
					folder.mkdirs();
				}

				DiskFileItem df = (DiskFileItem) ((CommonsMultipartFile) image).getFileItem();

				ImageUtil2.createThumbnail(df.getStoreLocation().getAbsolutePath(), basePath + fielPath + fileName + suffix, 0, 0);
				ImageUtil2.createThumbnail(df.getStoreLocation().getAbsolutePath(), basePath + fielPath + fileName + "_W120" + suffix, 120, 0);

				photo.setPhotoUrl(fielPath + fileName);
			}

			photoService.savePhoto(photo, adminContext);

			map.put("message", "Õº∆¨±£¥Ê≥…π¶£°");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "Õº∆¨±£¥Ê ß∞‹£°<br/>" + e.getMessage());
		}

		map.put("refererURL", "/zu/photo/list.do");

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/edit")
	public ModelAndView eidt(HttpServletRequest request, HttpServletResponse response, String id, String albumId)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			Photo photo = photoService.findPhotoById(NumberUtil.parseLong(id));
			Album album = albumService.findAlbumById(NumberUtil.parseLong(albumId));

			map.put("photo", photo);
			map.put("album", album);

			return new ModelAndView("manager/album/photo/edit", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "Õº∆¨ªÒ»° ß∞‹°£<br/>" + e.getMessage());
			map.put("refererURL", "/zu/photo/list.do?albumId=" + albumId);

			return new ModelAndView("manager/message", "map", map);
		}
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multipartRequest, MultipartFile image, Photo photo)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			if (!image.isEmpty())
			{
				String suffix = ".jpg";
				String fileName = System.currentTimeMillis() + "";
				String basePath = request.getSession().getServletContext().getRealPath("/");
				String fielPath = "/album/photo/" + DateUtil.formatString(System.currentTimeMillis(), "yyyyMMdd") + "/";

				File folder = new File(basePath + fielPath);

				if (!folder.exists())
				{
					folder.mkdirs();
				}

				DiskFileItem df = (DiskFileItem) ((CommonsMultipartFile) image).getFileItem();

				ImageUtil2.createThumbnail(df.getStoreLocation().getAbsolutePath(), basePath + fielPath + fileName + suffix, 0, 0);
				ImageUtil2.createThumbnail(df.getStoreLocation().getAbsolutePath(), basePath + fielPath + fileName + "_W120" + suffix, 120, 0);

				photo.setPhotoUrl(fielPath + fileName);
			}

			photoService.updatePhoto(photo, adminContext);

			map.put("message", "Õº∆¨–ﬁ∏ƒ≥…π¶£°");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "Õº∆¨–ﬁ∏ƒ ß∞‹£°<br/>" + e.getMessage());
		}

		map.put("refererURL", "/zu/photo/list.do?albumId=" + photo.getAlbumId());

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, String id, String albumId)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			photoService.deletePhotoById(NumberUtil.parseLong(id));

			map.put("message", "Õº∆¨…æ≥˝≥…π¶£°");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "Õº∆¨…æ≥˝ ß∞‹£°<br/>" + e.getMessage());
		}

		map.put("refererURL", "/zu/photo/list.do?albumId=" + albumId);

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/cover")
	public ModelAndView cover(HttpServletRequest request, HttpServletResponse response, String id, String albumId)
	{
		AdminContext adminContext = (AdminContext) request.getAttribute("adminContext");
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			Photo photo = photoService.findPhotoById(NumberUtil.parseLong(id));
			Album album = albumService.findAlbumById(NumberUtil.parseLong(albumId));

			album.setCover(photo.getPhotoUrl());

			albumService.updateAlbum(album, adminContext);

			map.put("message", "œ‡≤·∑‚√Ê…Ë÷√≥…π¶£°");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "œ‡≤·∑‚√Ê…Ë÷√ ß∞‹°£<br/>" + e.getMessage());
		}

		map.put("refererURL", "/zu/photo/list.do?albumId=" + id);

		return new ModelAndView("manager/message", "map", map);
	}

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, String albumId)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		try
		{
			map.put("albumId", albumId);
			map.put("photos", photoService.queryPhotoByAlbumId(NumberUtil.parseLong(albumId)));
			
			return new ModelAndView("manager/album/photo/list", "map", map);
		}
		catch (Exception e)
		{
			e.printStackTrace();

			map.put("message", "Õº∆¨¡–±Ì ß∞‹£°<br/>" + e.getMessage());
			map.put("refererURL", "/zu/photo/list.do?albumId=" + albumId);

			return new ModelAndView("manager/message", "map", map);
		}
	}
}