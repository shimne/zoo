package com.shimne.zoopu.album.dao;

import java.util.List;

import com.shimne.zoopu.album.entity.Album;

public interface AlbumDao
{
	void save(Album album);

	void update(Album album);

	Album findById(long id);

	void deleteById(long id);

	List<Album> queryByParentId(long parentId);

	List<Album> queryAll();
}