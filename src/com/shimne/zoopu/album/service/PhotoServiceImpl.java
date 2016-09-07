package com.shimne.zoopu.album.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.util.ObjectUtil;
import com.shimne.zoopu.album.dao.PhotoDao;
import com.shimne.zoopu.album.entity.Photo;
import com.shimne.zoopu.common.manage.AdminContext;

@Service("photoService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PhotoServiceImpl implements PhotoService
{
	@Autowired
	private PhotoDao photoDao;

	@Override
	@Transactional(readOnly = false)
	public void savePhoto(Photo photo, AdminContext adminContext)
	{
		Assert.notNull(photo, PHOTO_IS_NULL);

		photo.setCreatorId(adminContext.getAdminId());
		photo.setCreateTime(System.currentTimeMillis());

		photoDao.save(photo);
	}

	@Override
	public Photo findPhotoById(long id)
	{
		Photo photo = photoDao.findById(id);

		if (ObjectUtil.isNull(photo))
		{
			throw new NestedRuntimeException(PHOTO_NOT_EXISIT);
		}

		return photo;
	}

	@Override
	@Transactional(readOnly = false)
	public void updatePhoto(Photo photo, AdminContext adminContext)
	{
		Assert.notNull(photo, PHOTO_IS_NULL);

		Photo photoTmp = findPhotoById(photo.getId());

		photoTmp.setText(photo.getText());
		photoTmp.setTitle(photo.getText());
		photoTmp.setPhotoUrl(photo.getPhotoUrl());
		photoTmp.setTargetUrl(photo.getTargetUrl());
		photoTmp.setUpdaterId(adminContext.getAdminId());
		photoTmp.setUpdateTime(System.currentTimeMillis());

		photoDao.update(photoTmp);
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePhotoById(long id)
	{
		photoDao.deleteById(id);
	}

	@Override
	public int countPhotoByAlbumId(long albumId)
	{
		return photoDao.countByAlbumId(albumId);
	}

	@Override
	public List<Photo> queryPhotoByAlbumId(long albumId)
	{
		return photoDao.queryByAlbumId(albumId);
	}
}