package com.shimne.zoopu.util;

import java.io.File;

import com.shimne.util.StringUtil;

public class AppUtil
{
	private AppUtil()
	{
	}

	// 利用Log4j初使化应用程序根目录
	private static String appRootPath = System.getProperty("webapp.root");

	/**
	 * 获取系统运行根目录。
	 * 
	 * @return
	 */
	public static String getWebRootPath()
	{
		return appRootPath;
	}

	/**
	 * 获取系统运行根目录 + path
	 * 
	 * @return
	 */
	public static String getWebRealPath(String path)
	{
		String realPath = appRootPath;

		if (StringUtil.notTrimEmpty(realPath))
		{
			path = path.trim();
			
			if (path.startsWith(File.separator))
			{
				realPath += path + File.separator;
			}
			else
			{
				realPath +=  File.separator + path +  File.separator;
			}
		}

		return realPath;
	}
}