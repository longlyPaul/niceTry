/**
 *   上海华盖科技发展有限公司
 *   FILENAME     :  BaseConstants.java
 *   PACKAGE      :  com.hg.core.constant
 *   CREATE DATE  :  2014年3月20日
 *   MODIFIED BY  :
 *	 DESCRIPTION  :  
 */

package com.common.utils;

import org.springframework.web.context.WebApplicationContext;

/**
 * @文件描述 系统常量定义类
 * @version CVN #V1# #2014年3月20日#
 */
public class BaseConstants {

	/** 登录用户对应的Session Info */
	public static final String USER_SESSION_INFO = "UserSessionInfo";

	/** 日期格式 */
	public static final String DATE_PATTERN_CN = "yyyy年MM月dd日";

	/** 日期格式 ** */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	
	/** 日期格式 用于生成日期格式的目录名称 */
	public static final String DATE_PATTERN_2 = "yyyyMMdd";
	
	/** 日期格式 ** */
	public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/** 数字格式1 */
	public static final String NUMBER_PATTERN_3 = "#.##";
	
	/** 开发模式. */
	public static boolean DEVELOP_MODE = false;

	/** 每页的记录数. */
	public final static int PAGE_SIZE = 20;

	/** PDF打印格式 */
	public final static String PRINT_PDF = "pdf";

	/** XLS打印格式 */
	public final static String PRINT_XLS = "xls";

	/** 错误信息内容保存在session的key ** */
	public final static String ERROR_MSG_SESSION = "ERROR_MSG_SESSION";

	/** 验证错误信息内容保存在session的key ** */
	public final static String FIELD_ERROR_SESSION = "FIELD_ERROR_SESSION";

	/** 提示信息内容保存在session的key ** */
	public final static String HINT_MSG_SESSION = "HINT_MSG_SESSION";

	/** 每张表的公共字段：状态标志 1－有效；. */
	public final static Integer ACTIVE_FLAG = 1;
	/** 每张表的公共字段：状态标志 0－无效；. */
	public final static Integer DACTIVE_FLAG = 0;

	/** 应用程序系统参数资源文件. */
	public static String APPLICATION_RESOURCE_FILE = "application";

	/** 资源文件默认值. */
	public static String APPLICATION_DEFAULT_VALUE = "";

	/** 系统上下文路径. */
	public static String APPLICATION_CONTEXT_PATH = null;

	/** 系统字典表 */
	public final static String APPLICATION_SYS_CODE = "syscode";
	/** 系统字典表分类字段 */
	public final static String APPLICATION_SYS_CODE_NAME = "codeType";

	/** 系统URL路径*/
	public static String APPLICATION_URL_PATH = null;

	/** 每次加载head.jsp的属性文件名称 */
	public static String DEVELOP_MODE_INITHEAD_NAME = "application_devMode_initHead";

	/** 开发模式的属性文件名称 */
	public static String DEVELOP_MODE_NAME = "application_devMode";

	/** 应用程序的页面媒体文件内容. */
	public static String APPLICATION_META = null;

	/** 上下文路径的参数名称 */
	public static String APPLICATION_CONTEXT_PATH_NAME = "ctxPath";

	/** 图片替换关键字. */
	public static String PREDEFINE_KEY_IMG = "#\\{ctx\\}";

	/** Spring上下文对象. */
	public static WebApplicationContext SPRING_APPLICATION_CONTEXT = null;
	/** 系统分割符 */
	public static final String SYSTEM_SEPARATOR = "/";
	/** 页面工具引用名称 */
	public final static String WEB_UTILS_NAME = "utils";

	public final static String WEB_CACHE_NAME = "cache";

	/** 上传文件存放路径，需要和在应用服务器配置xml文件里定义的上下文路径名称一致 * */
	public static final String UPLOAD_DIR = "/uploadFiles";
	public static final String UPLOAD_DIR_1 = "/uploadFiles/upload";

	/**系统属性文件名称*/
	public static final String SYSTEM_PROP_FILE = "ds.properties";

	/**文件存储目录属性名称*/
	public static final String SYSTEM_FILE_DIR = "file.dir";

	/**缓冲区大小*/
	public static final int BUFFER_SIZE = 16 * 1024;

	/** 数字格式0 */
	public static final String NUMBER_PATTERN = "###0.00";

	/** 数字格式1 */
	public static final String NUMBER_PATTERN_1 = "0.00";
	
	public static final String DISCRIMINATOR = "00000000000";

}
