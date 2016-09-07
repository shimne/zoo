package com.shimne.zoopu.album.service;

import java.util.List;

import com.shimne.zoopu.album.entity.Album;
import com.shimne.zoopu.common.manage.AdminContext;

public interface AlbumService
{
	public static final String ALBUM_IS_NULL = "";
	public static final String ALBUM_NOT_EXISIT = "";
	public static final String ALBUM_HAS_PHOTO = "";

	void saveAlbum(Album album, AdminContext adminContext);

	Album findAlbumById(long id);

	void updateAlbum(Album album, AdminContext adminContext);

	void deleteAlbumById(long id);

	List<Album> queryAlbumByParentId(long parentId);

	List<Album> queryAlbumAll();

	String getTreeData();
}