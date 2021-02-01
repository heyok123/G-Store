package com.cupdata.auth.util;

import java.util.Locale;

/**
 * 常量类
 */
public class ConstantUtils {
	
	/** 如果是app登录，设置session永久 **/
	public static final String APP_REDIS_SESSION_KEY = "APP_REDIS_SESSION_KEY";

	/** REDIS key 数据字典前缀 **/
	public static final String DATA_DIC = "REDIS_DATA_DIC";

	/** REDIS key 用户前缀 **/
	public static final String USER_SESSION = "USER_SESSION";

	/** REDIS key 图片验证码前缀 **/
	public static final String VER_CODE = "VER_CODE";

	/** APP登陆异常的标志，用于判断是否需要验证码 */
	public static final String APP_LOGIN_ERR_FLAG = "APP_LOGIN_ERR_FLAG";

	/** 验证码不正确 **/
	public static final String VER_CODE_ERROR = "VER_CODE_ERROR";

	/** REDIS key 部门-室 树状数据 **/
	public static final String REDIS_KEY_TREE4_DEPT_ROOM = "REDIS_KEY_TREE4_DEPT_ROOM";

	/** REDIS key 部门-室-人 树状数据 **/
	public static final String REDIS_KEY_TREE4_DEPT_ROOM_USER = "REDIS_KEY_TREE4_DEPT_ROOM_USER";

	/** 语言 **/
	public static final String LOCALE_PARAM_NAME = "locale";

	/** 默认语言 中文 **/
	public static final Locale LOCALE_DEFAULT = Locale.SIMPLIFIED_CHINESE;

	/** 语言 中国 **/
	public static final String LOCALE_ZH_CN = "zh_CN";

	/** 语言 英文 **/
	public static final String LOCALE_EN_US = "en_US";

	/** kafkaTopic 邮件 **/
	public static final String KAFKA_TOPIC_EMAIL = "kafka.topic.email";

	/** kafkaTopic 短信 **/
	public static final String KAFKA_TOPIC_MOBILE = "kafka.topic.mobile";
	
	/** kafkaTopic ios消息推送**/
//	public static final String KAFKA_TOPIC_IOS_PUSH = "kafka.topic.ios.push";

	/** 正则表达式，用于抽取域名 */
	public static final String REGEX_DOMAIN = "(http|https)\\://(.+?)(/|\\:|$)";

	/** 登录短信验证码 **/
	public static final String SHORT_MSGVALUE = "shortMsgValue";

	/** 用户当天获取登录验证码的次数 **/
	public static final String VERIFICATION_TIMES = "verification_times";

	/** 重置密码短信验证码 **/
	public static final String RESET_PASSWORD_CODE = "resetPassWordCode";

	/** 用户当天重置密码获取登录验证码的次数 **/
	public static final String RESET_PASSWORD_TIMES = "reset_password_times";

    /**
     * 用户当天重置密码获取登录验证码的用户信息
     **/
    public static final String RESET_PASSWORD_USERNAME = "reset_password_username";

	/** 和前端约定的请求参数名，用于承载移动设备的识别码，并将该码值作为key，验证码作为value 保存到Redis */
	public static final String DEVICE_TOKEN = "deviceToken";

	/**
	 * 数据库加密Des的key，三个key用逗号隔开 例如：jsauhqh8,7uJKl902,Lhjk62Hb 最好每个key的长度为八位 不能低于八位
	 **/
	public static final String DES_KEY = "8PYUKIsb,8jszx2p9,s7ls2k54";
	
	/**
	 * 生成 notice id 时 redis 使用的前缀      通知公告
	 */
	public static final String NOTICE = "notice";

	/**
	 * 生成 meeting id 时 redis 使用的前缀  会议室预定
	 */
	public static final String MEETING = "meeting";

}
