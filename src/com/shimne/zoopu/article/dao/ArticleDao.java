package com.shimne.zoopu.article.dao;

import java.util.List;
import java.util.Map;

import com.shimne.zoopu.article.entity.Article;

public interface ArticleDao
{
	void save(Article article);

	void update(Article article);

	Article findById(long id);

	void deleteById(long id);

	int count(Map<String, Object> params);

	List<Article> query(Map<String, Object> params);
}