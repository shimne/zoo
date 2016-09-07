package com.shimne.zoopu.util.ueditor.define;

import java.util.HashMap;
import java.util.Map;

public class ActionMap
{
	public static final Map<String, Integer> mapping;
	// ��ȡ��������
	public static final int CONFIG = 0;
	public static final int UPLOAD_IMAGE = 1;
	public static final int UPLOAD_SCRAWL = 2;
	public static final int UPLOAD_VIDEO = 3;
	public static final int UPLOAD_FILE = 4;
	public static final int CATCH_IMAGE = 5;
	public static final int LIST_FILE = 6;
	public static final int LIST_IMAGE = 7;
	
	static {
		mapping = new HashMap<String, Integer>()
		{
			private static final long serialVersionUID = -1119034283858468621L;
			{
				put( "config", ActionMap.CONFIG );
				put( "uploadimage", ActionMap.UPLOAD_IMAGE );
				put( "uploadscrawl", ActionMap.UPLOAD_SCRAWL );
				put( "uploadvideo", ActionMap.UPLOAD_VIDEO );
				put( "uploadfile", ActionMap.UPLOAD_FILE );
				put( "catchimage", ActionMap.CATCH_IMAGE );
				put( "listfile", ActionMap.LIST_FILE );
				put( "listimage", ActionMap.LIST_IMAGE );
			}
		};
	}
	
	public static int getType ( String key ) {
		return ActionMap.mapping.get( key );
	}
	
}