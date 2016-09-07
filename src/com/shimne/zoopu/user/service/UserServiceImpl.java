package com.shimne.zoopu.user.service;

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
import com.shimne.util.ObjectUtil;
import com.shimne.util.StringUtil;
import com.shimne.util.URLEncoder;
import com.shimne.zoopu.bbs.dao.UserBbsDao;
import com.shimne.zoopu.bbs.entity.UserBbs;
import com.shimne.zoopu.common.web.UserContext;
import com.shimne.zoopu.user.dao.UserDao;
import com.shimne.zoopu.user.entity.User;

@Service("userService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserBbsDao userBbsDao;
	@Autowired
	private PageService pageService;

	@Override
	public UserContext saveUser(User user, String ip)
	{
		Assert.notNull(user, USER_IS_NULL);

		user.setUsername(user.getUsername().trim().toLowerCase());
		user.setPassword(StringUtil.md5String(user.getPassword().trim()));
		user.setRegisterTime(System.currentTimeMillis());
		userDao.save(user);

		UserBbs userBbs = new UserBbs(user.getId());
		userBbsDao.save(userBbs);

		return new UserContext(user.getId(), user.getUsername(), user.getFamilyName(), user.getLastName(), user.getSex(), DateUtil.formatString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"), ip);
	}

	@Override
	public User updateUser(User user)
	{
		Assert.notNull(user, USER_IS_NULL);

		User userTmp = userDao.findById(user.getId());

		userTmp.setIdNumber(user.getIdNumber());
		userTmp.setMobile(user.getMobile());
		userTmp.setSex(user.getSex());
		userTmp.setEmail(user.getEmail());
		userTmp.setState(user.getState());
		userTmp.setCity(user.getCity());
		userTmp.setArea(user.getArea());
		userTmp.setAddress(user.getAddress());
		userTmp.setCurrentState(user.getCurrentState());
		userTmp.setCurrentCity(user.getCurrentCity());
		userTmp.setCurrentArea(user.getCurrentArea());
		userTmp.setCurrentAddress(user.getCurrentAddress());
		userTmp.setPhoto(user.getPostcode());
		userTmp.setPhoto(user.getPhoto());
		userTmp.setUpdateTime(System.currentTimeMillis());

		userDao.update(userTmp);

		return user;
	}

	@Override
	public User findUserById(long id)
	{
		User user = userDao.findById(id);

		if (ObjectUtil.isNull(user))
		{
			throw new NestedRuntimeException(USER_NOT_EXIST);
		}

		return user;
	}

	@Override
	public UserContext login(String username, String password, String ip)
	{
		User user = userDao.findByUsername(username.trim().toLowerCase());

		if (ObjectUtil.isNull(user) || user.isDisable())
		{
			throw new NestedRuntimeException(USER_NOT_EXIST);
		}

		if (!StringUtil.md5String(password.trim()).equals(user.getPassword()))
		{
			throw new NestedRuntimeException(USER_PASSWORD_FAIL);
		}

		return new UserContext(user.getId(), user.getUsername(), user.getFamilyName(), user.getLastName(), user.getSex(), DateUtil.formatString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"), ip);
	}

	@Override
	public Map<String, Object> queryUser(String familyName, String lastName,
			String state, String city, String area, String currentState,
			String currentCity, String currentArea, String disable, String open,
			String startTime, String endTime,
			int currentPageNum, int maxPageRowCount)
	{
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder urlBuilder = new StringBuilder("?page=${pageNum}");

		if (StringUtil.notTrimEmpty(familyName))
		{
			returnMap.put("familyName", familyName);
			params.put("familyName", familyName);
			urlBuilder.append("&familyName=").append(URLEncoder.encode(familyName));
		}

		if (StringUtil.notTrimEmpty(lastName))
		{
			returnMap.put("lastName", lastName);
			params.put("lastName", "%" + lastName + "%");
			urlBuilder.append("&lastName=").append(URLEncoder.encode(lastName));
		}

		if (StringUtil.notTrimEmpty(state))
		{
			returnMap.put("state", state);
			params.put("state", state);
			urlBuilder.append("&state=").append(URLEncoder.encode(state));
		}

		if (StringUtil.notTrimEmpty(city))
		{
			returnMap.put("city", city);
			params.put("city", city);
			urlBuilder.append("&city=").append(URLEncoder.encode(city));
		}

		if (StringUtil.notTrimEmpty(area))
		{
			returnMap.put("area", area);
			params.put("area", area);
			urlBuilder.append("&area=").append(URLEncoder.encode(area));
		}

		if (StringUtil.notTrimEmpty(currentState))
		{
			returnMap.put("currentState", currentState);
			params.put("currentState", currentState);
			urlBuilder.append("&currentState=").append(URLEncoder.encode(currentState));
		}

		if (StringUtil.notTrimEmpty(currentCity))
		{
			returnMap.put("currentCity", currentCity);
			params.put("currentCity", currentCity);
			urlBuilder.append("&currentCity=").append(URLEncoder.encode(currentCity));
		}

		if (StringUtil.notTrimEmpty(currentArea))
		{
			returnMap.put("currentArea", currentArea);
			params.put("currentArea", currentArea);
			urlBuilder.append("&currentArea=").append(URLEncoder.encode(currentArea));
		}

		if (StringUtil.notTrimEmpty(disable))
		{
			returnMap.put("disable", disable);
			params.put("disable", disable);
			urlBuilder.append("&disable=").append(disable);
		}

		if (StringUtil.notTrimEmpty(open))
		{
			returnMap.put("open", open);
			params.put("open", open);
			urlBuilder.append("&open=").append(open);
		}

		if (StringUtil.notTrimEmpty(startTime))
		{
			returnMap.put("startTime", startTime);
			params.put("startTime", DateUtil.formatLong(startTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
			urlBuilder.append("&startTime=").append(URLEncoder.encode(startTime));
		}

		if (StringUtil.notTrimEmpty(endTime))
		{
			returnMap.put("endTime", endTime);
			params.put("endTime", DateUtil.formatLong(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			urlBuilder.append("&endTime=").append(URLEncoder.encode(endTime));
		}

		int count = userDao.count(params);

		if (count > 0)
		{
			Pagination<User> pagination = new Pagination<User>(currentPageNum, maxPageRowCount, count);
			pagination.setUrl(urlBuilder.toString());

			pageService.build(pagination);

			params.put("start", pagination.getStartPageRowCount());
			params.put("max", pagination.getMaxPageRowCount());

			returnMap.put("datas", userDao.query(params));
			returnMap.put("pageContents", pagination.getPageContents());
		}

		return returnMap;
	}

	@Override
	public boolean checkUser(String username)
	{
		User user = userDao.findByUsername(username.trim().toLowerCase());

		if (ObjectUtil.isNull(user))
		{
			return true;
		}

		return false;
	}

	@Override
	public void modifyPassword(String username, String password, String newPassword)
	{
		User user = userDao.findByUsername(username.trim().toLowerCase());

		if (!StringUtil.md5String(password.trim()).equals(user.getPassword()))
		{
			throw new NestedRuntimeException(USER_PASSWORD_FAIL);
		}

		user.setPassword(StringUtil.md5String(newPassword.trim()));
		userDao.update(user);
	}

	@Override
	public void disableUser(long id)
	{
		User user = findUserById(id);

		user.setDisable(true);
		user.setUpdateTime(System.currentTimeMillis());

		userDao.update(user);
	}

	@Override
	public void enableUser(long id)
	{
		User user = findUserById(id);

		user.setDisable(false);
		user.setUpdateTime(System.currentTimeMillis());

		userDao.update(user);
	}
}