package com.shimne.zoopu.article.service;

import java.util.Map;

import com.shimne.zoopu.article.entity.Article;
import com.shimne.zoopu.common.manage.AdminContext;

public interface ArticleService
{
	public static final String ARTICLE_IS_NULL = "文章不能为空！";
	public static final String ARTICLE_NOT_EXIST = "文章不存在！";

	Article saveArtice(Article article, AdminContext adminContext);

	Article updateArtice(Article article, AdminContext adminContext);

	Article findArticleById(long id);

	void updateArticleStatusByIds(String ids, int status, AdminContext adminContext);

	void topArticleById(long id, AdminContext adminContext);

	void delteArticleById(long id);

	Map<String, Object> queryArticle(long creatorId, long channelId, String title, int status, String startTime, String endTime, int pageNum, int maxPageRowCount);
}