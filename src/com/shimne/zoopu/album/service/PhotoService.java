package com.shimne.zoopu.album.service;

import java.util.List;

import com.shimne.zoopu.album.entity.Photo;
import com.shimne.zoopu.common.manage.AdminContext;

public interface PhotoService
{
	public static final String PHOTO_IS_NULL = "";
	public static final String PHOTO_NOT_EXISIT = "";

	void savePhoto(Photo photo, AdminContext adminContext);

	Photo findPhotoById(long id);

	void updatePhoto(Photo photo, AdminContext adminContext);

	void deletePhotoById(long id);

	int countPhotoByAlbumId(long albumId);

	List<Photo> queryPhotoByAlbumId(long albumId);
}