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
	public static String BBS_REPLY = "�ظ�";
	public static String BBS_TOPIC = "����";
	public static String BBS_TOP = "�ö�";
	public static String BBS_ELITE = "����";

	Logger logger = Logger.getLogger(PropertiesTool.class);

	private static final String DEFAULT_FILE = "/WEB-INF/classes/weixin.properties";

	public static Map<String, Integer> scoreMap; 			// ����
	public static Map<Integer, Integer> levelMap;			// �ȼ�
	public static List<String> ills;						// ���ô�
	public static List<String> marks;						// ���ñ�ǩ

	public PropertiesTool()
	{
		scoreMap = new TreeMap<String, Integer>();
		levelMap = new TreeMap<Integer, Integer>(new MyComparator());
		ills = new ArrayList<String>();
		marks = new ArrayList<String>();
	}

	public void init()
	{
		logger.info("��ʼ��BBS���Կ�ʼ��");
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

		logger.info("��ʼ��BBS������Խ�����");
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

	// ��*�Ŵ�����ô�
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

	// ���˶����ǩ
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
		if ((text.indexOf("����") > -1 || text.indexOf("�ṩ") > -1 || text.indexOf("��") > -1 || text.indexOf("��") > -1) 
				&& (text.indexOf("����") > -1 || text.indexOf("�ɶ�") > -1) &&
				(text.indexOf("�绰") > -1 || text.indexOf("�ֻ�") > -1 || text.indexOf("��ϵ��ʽ") > -1 || text.indexOf("��Դ") > -1))
		{
			return true;
		}

		Pattern pattern = Pattern.compile("\\[url.*url\\].*\\[link.*link\\]");
		Matcher matcher = pattern.matcher(text);

		return matcher.find();
	}

	public static boolean illegalName(String name)
	{
		if (name.indexOf("����") > -1 || name.indexOf("��֤") > -1 || 
			name.indexOf("��Ʊ") > -1 || name.indexOf("����") > -1 ||
			name.indexOf("ѧ��") > -1 || name.indexOf("��ƾ") > -1 ||
			name.indexOf("��Ʊ") > -1 || name.indexOf("֤��") > -1)
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