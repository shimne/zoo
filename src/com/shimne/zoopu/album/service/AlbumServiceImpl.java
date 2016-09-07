package com.shimne.zoopu.album.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.util.ObjectUtil;
import com.shimne.zoopu.album.dao.AlbumDao;
import com.shimne.zoopu.album.dao.PhotoDao;
import com.shimne.zoopu.album.entity.Album;
import com.shimne.zoopu.common.manage.AdminContext;

@Service("albumService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AlbumServiceImpl implements AlbumService
{
	@Autowired
	private AlbumDao albumDao;
	@Autowired
	private PhotoDao photoDao;

	@Override
	@Transactional(readOnly = false)
	public void saveAlbum(Album album, AdminContext adminContext)
	{
		Assert.notNull(album, ALBUM_IS_NULL);

		album.setCreatorId(adminContext.getAdminId());
		album.setCreateTime(System.currentTimeMillis());

		albumDao.save(album);
	}

	@Override
	public Album findAlbumById(long id)
	{
		Album album = albumDao.findById(id);

		if (ObjectUtil.isNull(album))
		{
			throw new NestedRuntimeException(ALBUM_NOT_EXISIT);
		}

		return album;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateAlbum(Album album, AdminContext adminContext)
	{
		Assert.notNull(album, ALBUM_IS_NULL);

		Album albumTmp = findAlbumById(album.getId());

		albumTmp.setName(album.getName());
		albumTmp.setDescription(album.getDescription());
		albumTmp.setCover(album.getCover());
		albumTmp.setUpdaterId(adminContext.getAdminId());
		albumTmp.setUpdateTime(System.currentTimeMillis());

		albumDao.update(albumTmp);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteAlbumById(long id) 
	{
		int photoCount = photoDao.countByAlbumId(id);

		if (photoCount > 0)
		{
			throw new NestedRuntimeException(ALBUM_HAS_PHOTO);
		}

		albumDao.deleteById(id);
	}

	@Override
	public List<Album> queryAlbumByParentId(long parentId)
	{
		return albumDao.queryByParentId(parentId);
	}

	@Override
	public List<Album> queryAlbumAll()
	{
		return albumDao.queryAll();
	}

	@Override
	public String getTreeData()
	{
		StringBuilder data = new StringBuilder("[");

		List<Album> albums = queryAlbumAll();

		if (ObjectUtil.notEmpty(albums))
		{
			for (int i = 0; i < albums.size(); i++)
			{
				Album album = albums.get(i);

				if (i > 0)
				{
					data.append(",");
				}

				data.append("{id:" + album.getId() + ", pId:" + album.getParentId() + ", name:\"" + album.getName() + "\"}");
			}
		}

		return data.append("]").toString();
	}
	

//	public Map<String, Object> queryAlbum(String name, int currentPageNum, int maxPageRowCount)
//	{
//		Map<String, Object> returnMap = new HashMap<String, Object>();
//		Map<String, Object> params = new HashMap<String, Object>();
//		StringBuilder urlBuilder = new StringBuilder("?page=${pageNum}");
//
//		if (StringUtil.notTrimEmpty(name))
//		{
//			returnMap.put("name", name);
//			params.put("name", "%" + name + "%");
//			urlBuilder.append("&name=").append(URLEncoder.encode(name));
//		}
//
//		int count = albumDao.count(params);
//
//		if (count > 0)
//		{
//			Pagination<Album> pagination = new Pagination<Album>();
//
//			pagination.setCurrentPageNum(currentPageNum);
//			pagination.setMaxPageRowCount(maxPageRowCount);
//			pagination.setTotalRowCount(count);
//			pagination.setUrl(urlBuilder.toString());
//
//			pageService.build(pagination);
//
//			params.put("start", pagination.getStartPageRowCount());
//			params.put("max", pagination.getMaxPageRowCount());
//
//			returnMap.put("pageContents", pagination.getPageContents());
//			returnMap.put("datas", albumDao.query(params));
//		}
//
//		return returnMap;
//	}
}