package com.shimne.zoopu.util;

import java.io.File;

import com.shimne.util.StringUtil;

public class AppUtil
{
	private AppUtil()
	{
	}

	// ����Log4j��ʹ��Ӧ�ó����Ŀ¼
	private static String appRootPath = System.getProperty("webapp.root");

	/**
	 * ��ȡϵͳ���и�Ŀ¼��
	 * 
	 * @return
	 */
	public static String getWebRootPath()
	{
		return appRootPath;
	}

	/**
	 * ��ȡϵͳ���и�Ŀ¼ + path
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