package com.shimne.zoopu.album.dao;

import java.util.List;

import com.shimne.zoopu.album.entity.Photo;

public interface PhotoDao
{
	void save(Photo photo);

	void update(Photo photo);

	Photo findById(long id);

	void deleteById(long id);

	int countByAlbumId(long albumId);

	List<Photo> queryByAlbumId(long albumId);
}