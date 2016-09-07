package com.shimne.zoopu.article.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shimne.exception.NestedRuntimeException;
import com.shimne.util.ObjectUtil;
import com.shimne.zoopu.article.dao.ArticleDao;
import com.shimne.zoopu.article.dao.ChannelDao;
import com.shimne.zoopu.article.entity.Channel;
import com.shimne.zoopu.common.manage.AdminContext;

@Service("articleCatalogService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ChannelServiceImpl implements ChannelService
{
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private ArticleDao articleDao;

	@Override
	public void saveChannel(Channel channel, AdminContext adminContext)
	{
		Assert.notNull(channel, CHANNEL_IS_NULL);

		channel.setCreatorId(adminContext.getAdminId());
		channel.setCreateTime(System.currentTimeMillis());

		channelDao.save(channel);
	}

	@Override
	public void updateChannel(Channel channel, AdminContext adminContext)
	{
		Assert.notNull(channel, CHANNEL_IS_NULL);

		Channel channelTmp = findChannelById(channel.getId());

		channelTmp.setParentId(channel.getParentId());
		channelTmp.setName(channel.getName());
		channelTmp.setDescription(channel.getDescription());
		channelTmp.setUpdaterId(adminContext.getAdminId());
		channelTmp.setUpdateTime(System.currentTimeMillis());

		channelDao.update(channelTmp);
	}

	@Override
	public Channel findChannelById(long id)
	{
		Channel channel = channelDao.findById(id);

		if (ObjectUtil.isNull(channel))
		{
			throw new NestedRuntimeException(CHANNEL_NOT_EXIST);
		}

		return channel;
	}

	@Override
	public void deleteChannelById(long id)
	{
		Channel channel = channelDao.findById(id);

		if (ObjectUtil.notNull(channel))
		{
			List<Channel> childs = channelDao.queryByParentId(id);

			if (ObjectUtil.notEmpty(childs))
			{
				throw new NestedRuntimeException(CHANNEL_HAS_CHILD);
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("channelId", id);

			if (articleDao.count(params) > 0)
			{
				throw new NestedRuntimeException(CHANNEL_HAS_ARTICLE);
			}

			channelDao.deleteById(id);
		}
	}

	@Override
	public List<Channel> queryChannelAll()
	{
		return channelDao.queryAll();
	}

	@Override
	public List<Channel> queryChannelByParentId(long parentId)
	{
		return channelDao.queryByParentId(parentId);
	}

	@Override
	public String getTreeData()
	{
		StringBuilder data = new StringBuilder("[");

		List<Channel> channels = queryChannelAll();

		if (ObjectUtil.notEmpty(channels))
		{
			for (int i = 0; i < channels.size(); i++)
			{
				Channel channel = channels.get(i);

				if (i > 0)
				{
					data.append(",");
				}

				data.append("{id:" + channel.getId() + ", pId:" + channel.getParentId() + ", name:\"" + channel.getName() + "\"}");
			}
		}

		return data.append("]").toString();
	}
}