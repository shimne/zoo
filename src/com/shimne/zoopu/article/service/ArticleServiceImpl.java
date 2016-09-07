package com.shimne.zoopu.article.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.page.PageService;
import com.shimne.page.Pagination;
import com.shimne.util.DateUtil;
import com.shimne.util.NumberUtil;
import com.shimne.util.ObjectUtil;
import com.shimne.util.StringUtil;
import com.shimne.util.URLEncoder;
import com.shimne.zoopu.article.dao.ArticleDao;
import com.shimne.zoopu.article.entity.Article;
import com.shimne.zoopu.common.manage.AdminContext;

@Service("articleService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ArticleServiceImpl implements ArticleService
{
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private PageService pageService;

	@Override
	@Transactional(readOnly = false)
	public Article saveArtice(Article article, AdminContext adminContext)
	{
		Assert.notNull(article, ARTICLE_IS_NULL);

		article.setCreatorId(adminContext.getAdminId());
		article.setCreateTime(System.currentTimeMillis());

		articleDao.save(article);

		return article;
	}

	@Override
	@Transactional(readOnly = false)
	public Article updateArtice(Article article, AdminContext adminContext)
	{
		Assert.notNull(article, ARTICLE_IS_NULL);

		Article articleTmp = findArticleById(article.getId());

		articleTmp.setChannelId(article.getChannelId());
		articleTmp.setTitle(article.getTitle());
		articleTmp.setSubTitle(article.getSubTitle());
		articleTmp.setKeywords(article.getKeywords());
		articleTmp.setContent(article.getContent());
		articleTmp.setSummary(article.getSummary());
		articleTmp.setAuthor(article.getAuthor());
		articleTmp.setSource(article.getSource());
		articleTmp.setOutUrl(article.getOutUrl());
		articleTmp.setIcon(article.getIcon());
		articleTmp.setArticleTime(article.getArticleTime());
		articleTmp.setStatus(article.getStatus());
		articleTmp.setTop(article.getTop());
		articleTmp.setUpdaterId(adminContext.getAdminId());
		articleTmp.setUpdateTime(System.currentTimeMillis());

		articleDao.update(articleTmp);

		return articleTmp;
	}

	@Override
	public Article findArticleById(long id)
	{
		Article article = articleDao.findById(id);

		if (ObjectUtil.isNull(article))
		{
			throw new NestedRuntimeException(ARTICLE_NOT_EXIST);
		}

		return article;
	}

	@Override
	@Transactional(readOnly = false)
	public void updateArticleStatusByIds(String ids, int status, AdminContext adminContext)
	{
		if (StringUtil.notTrimEmpty(ids))
		{
			String[] idArray = ids.split(",");

			if (ObjectUtil.notEmpty(idArray))
			{
				for (int i = 0; i < idArray.length; i++)
				{
					Article article = articleDao.findById(NumberUtil.parseLong(idArray[i]));

					if (ObjectUtil.notNull(article))
					{
						article.setStatus(status);
						article.setUpdaterId(adminContext.getAdminId());
						article.setUpdateTime(System.currentTimeMillis());

						articleDao.update(article);
					}
				}
			}
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void topArticleById(long id, AdminContext adminContext)
	{
		Article article = articleDao.findById(id);

		if (ObjectUtil.isNull(article))
		{
			throw new NestedRuntimeException(ARTICLE_NOT_EXIST);
		}

		article.setTop(article.getTop() == 1 ? 2 : 1);
		article.setUpdaterId(adminContext.getAdminId());
		article.setUpdateTime(System.currentTimeMillis());

		articleDao.update(article);
	}

	@Override
	@Transactional(readOnly = false)
	public void delteArticleById(long id)
	{
		articleDao.deleteById(id);
	}

	@Override
	public Map<String, Object> queryArticle(long creatorId, long channelId, String title,
			int status, String startTime, String endTime, int pageNum, int maxPageRowCount)
	{
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder urlBuilder = new StringBuilder("?page=${pageNum}");

		startTime = StringUtil.isTrimEmpty(startTime) ? DateUtil.formatString(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000, "yyyy-MM-dd") : startTime;
		endTime = StringUtil.isTrimEmpty(endTime) ? DateUtil.formatString(System.currentTimeMillis(), "yyyy-MM-dd") : endTime;

		if (creatorId > 0L)
		{
			params.put("creatorId",creatorId);
			urlBuilder.append("&creatorId=").append(creatorId);
		}

		if (StringUtil.notTrimEmpty(title))
		{
			returnMap.put("title", title);
			params.put("title", title);
			urlBuilder.append("&title=").append(URLEncoder.encode(title));
		}

		if (status > 0)
		{
			returnMap.put("status", status);
			params.put("status", status);
			urlBuilder.append("&status=").append(status);
		}

		returnMap.put("channelId", channelId);
		returnMap.put("startTime", startTime);
		returnMap.put("endTime", endTime);

		params.put("channelId", channelId);
		params.put("startTime", DateUtil.formatLong(startTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		params.put("endTime", DateUtil.formatLong(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));

		urlBuilder.append("&channelId=").append(channelId);
		urlBuilder.append("&startTime=").append(startTime);
		urlBuilder.append("&endTime=").append(endTime);

		int count = articleDao.count(params);

		if (count > 0)
		{
			Pagination<Article> pagination = new Pagination<Article>();

			pagination.setCurrentPageNum(pageNum);
			pagination.setMaxPageRowCount(maxPageRowCount);
			pagination.setTotalRowCount(count);
			pagination.setUrl(urlBuilder.toString());

			pageService.build(pagination);

			params.put("start", pagination.getStartPageRowCount());
			params.put("max", pagination.getMaxOffSetCount());

			returnMap.put("articles", articleDao.query(params));
			returnMap.put("pageContexts", pagination.getPageContents());
		}

		return returnMap;
	}	
}