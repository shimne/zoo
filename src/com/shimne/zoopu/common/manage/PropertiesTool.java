package com.shimne.zoopu.common.manage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.shimne.util.NumberUtil;
import com.shimne.util.ObjectUtil;
import com.shimne.zoopu.util.AppUtil;

public class PropertiesTool
{
	public static String BBS_REPLY = "回复";
	public static String BBS_TOPIC = "发贴";
	public static String BBS_TOP = "置顶";
	public static String BBS_ELITE = "精华";

	Logger logger = Logger.getLogger(PropertiesTool.class);

	private static final String DEFAULT_FILE = "/WEB-INF/classes/weixin.properties";

	public static Map<String, Integer> scoreMap; 			// 分数
	public static Map<Integer, Integer> levelMap;			// 等级
	public static List<String> ills;						// 禁用词
	public static List<String> marks;						// 禁用标签

	public PropertiesTool()
	{
		scoreMap = new TreeMap<String, Integer>();
		levelMap = new TreeMap<Integer, Integer>(new MyComparator());
		ills = new ArrayList<String>();
		marks = new ArrayList<String>();
	}

	public void init()
	{
		logger.info("初始化BBS属性开始。");
		String path = AppUtil.getWebRealPath(DEFAULT_FILE);

		File file = new File(path);

		if (file.isFile())
		{
			InputStream is = null;

			try
			{
				is = new BufferedInputStream(new FileInputStream(file));

				Properties properties = new Properties();
				properties.load(is);

				String[] scores = properties.getProperty("bbs.score").split(",");

				if (ObjectUtil.notEmpty(scores))
				{
					for (String score : scores)
					{
						String[] scoreTemps = score.split(":");

						if (ObjectUtil.notEmpty(scoreTemps))
						{
							scoreMap.put(scoreTemps[0], NumberUtil.parseInt(scoreTemps[1]));
						}
					}
				}

				String[] levels = properties.getProperty("bbs.level").split(",");

				if (ObjectUtil.notEmpty(levels))
				{
					for (String level : levels)
					{
						String[] levelTemps = level.split(":");

						if (ObjectUtil.notEmpty(levelTemps))
						{
							levelMap.put(NumberUtil.parseInt(levelTemps[0]), NumberUtil.parseInt(levelTemps[1]));
						}
					}
				}

				String[] illsArray = properties.getProperty("bbs.ill").split(",");

				if (ObjectUtil.notEmpty(illsArray))
				{
					ills = Arrays.asList(illsArray);
				}

				String[] markArray = properties.getProperty("bbs.mark").split(",");

				if (ObjectUtil.notEmpty(markArray))
				{
					marks = Arrays.asList(markArray);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if (is != null)
					{
						is.close();
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		logger.info("初始化BBS相关属性结束。");
	}

	public static int getScoreByKey(String key)
	{
		if (scoreMap.containsKey(key))
		{
			return scoreMap.get(key);
		}

		return 0;
	}

	public static int getLevelByKey(long score)
	{
		for (Entry<Integer, Integer> entry : levelMap.entrySet())
		{
			if (score >= entry.getKey())
			{
				return entry.getValue();
			}
		}

		return 1;
	}

	// 用*号代替禁用词
	public static String delIllega(String content)
	{
		for (String ill : ills)
		{
			String tempStr = "";

			if (content.contains(ill.trim()))
			{
				for(int j = 0; j < ill.trim().length(); j++)
				{
					tempStr += "*";
				}

				content = content.replace(ill.trim(), tempStr);
			}
		}

		return content;
	}

	// 过滤恶意标签
	public static String delMark(String content)
	{
		String[] markArray = {"<", ">", "/>", "</"};

		for (String mark : marks)
		{
			if ((content.contains((markArray[0].trim()) + mark.trim())) && (((content.contains(markArray[1])) || (content.contains(markArray[2])) || (content.contains(markArray[3])))))
			{
				content = content.replace(markArray[0].trim(), "&lt;");
				content = content.replace(markArray[1].trim(), "&gt;");
			}
		}

		return content;
	}

	public static boolean isAdvText(String text)
	{
		if ((text.indexOf("出售") > -1 || text.indexOf("提供") > -1 || text.indexOf("售") > -1 || text.indexOf("卖") > -1) 
				&& (text.indexOf("股民") > -1 || text.indexOf("股东") > -1) &&
				(text.indexOf("电话") > -1 || text.indexOf("手机") > -1 || text.indexOf("联系方式") > -1 || text.indexOf("资源") > -1))
		{
			return true;
		}

		Pattern pattern = Pattern.compile("\\[url.*url\\].*\\[link.*link\\]");
		Matcher matcher = pattern.matcher(text);

		return matcher.find();
	}

	public static boolean illegalName(String name)
	{
		if (name.indexOf("刻章") > -1 || name.indexOf("办证") > -1 || 
			name.indexOf("发票") > -1 || name.indexOf("代办") > -1 ||
			name.indexOf("学历") > -1 || name.indexOf("文凭") > -1 ||
			name.indexOf("彩票") > -1 || name.indexOf("证件") > -1)
		{
			return true;
		}

		return false;
	}

	static class MyComparator implements Comparator<Integer>
	{
		public int compare(Integer o1, Integer o2) 
		{
			if (o1 > o2)
			{
				return -1;
			}
			else if (o1 == o2)
			{
				return 0;
			}
			else 
			{
				return 1;
			}
		}
	}
}