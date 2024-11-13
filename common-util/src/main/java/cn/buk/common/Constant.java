package cn.buk.common;

/**
 *
 * @author yfdai
 */
public class Constant {

    private Constant() {
        throw new IllegalArgumentException("Constant class");
    }

    public static final String SUCCESS = "SUCCESS";

    /**
     * 常用的日期格式
     */
    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 常用的日期格式
     */
    public static final String DATE_YYYYMMDD = "yyyyMMdd";

    public static final String PNR_ENTIRELY_CANCELLED = "THIS PNR WAS ENTIRELY CANCELLED";
    /**
     * DETR结果中航班使用状态
     */
    public static final String DETR_OPEN_FOR_USE = "OPEN FOR USE";
    public static final String DETR_USED_FLOWN = "USED/FLOWN";
    public static final String DETR_EXCHANGED = "EXCHANGED";
    public static final String DETR_REFUNDED = "REFUNDED";

    public static final String SESSION_ENTERPRISE_ID = "enterpriseId";
    public static final String SESSION_USERNAME = "username";
    public static final String SESSION_FULLNAME = "fullname";
    public static final String SESSION_USERID = "userid";
    public static final String SESSION_USER_ISADMIN = "isadmin";
    public static final String SESSION_AMOUNT_BALANCE = "amountBalance";
    public static final String SESSION_CAPTCHA_KEY ="session_captcha_key";
    public static final String SESSION_LOGINED = "logined";


    public static final int ALL_POLICY_TYPE = -1;
    /**
     * 普通政策
     */
    public static final int POLICY_TYPE_NORMAL = 0;

    /**
     * 特殊政策
     */
    public static final int POLICY_TYPE_SPECIAL = 1;
    /**
     * 国际单程政策
     */
    public static final int POLICY_TYPE_INTL_OW = 11;
    public static final int POLICY_TYPE_INTL_RT = 12;
    public static final int POLICY_TYPE_INTL_PUB = 13;

    public static final String PNR_STATUS_HK = "HK";

    public static final String SYSTEM_USERNAME = "sys-auto";


    public static final int TRIP_TYPE_OW = 1; //行程类型,单程
    public static final int TRIP_TYPE_RT = 2; //行程类型,往返

    public static final int MAX_FREE_MONITOR_CTRIP_DOM_POLICY_COUNT = 20; //最大免费监控携程国内政策的数量
    public static final int MAX_FREE_MONITOR_CTRIP_INTL_POLICY_COUNT = 20; //最大免费监控携程国际政策的数量


    //功能列表
    public static final int FUNCTION_CREATE_POLICY_MANAGE = 100;       //创建底价控制
    public static final int FUNCTION_UPDATE_POLICY_MANAGE = 101;       //修改底价控制
    public static final int FUNCTION_DELETE_POLICY_MANAGE = 102;       //删除底价控制
    public static final int FUNCTION_DELETE_ALL_POLICY_MANAGE = 103;   //删除全部底价控制

    public static final int FUNCTION_AUTO_MONITOR_POLICY = 200;         //自动监控政策功能
    public static final int FUNCTION_AUTO_MONITOR_INTL_POLICY = 201;         //自动监控国际政策功能
    public static final int FUNCTION_MANUALLY_TEST_POLICY = 202;         //手工测试政策

    public static final int FUNCTION_UPLOAD_ALL_POLICY_PLAN_CTRIP = 301;  //按照方案集或者全部方案集上传政策
    public static final int FUNCTION_UPLOAD_ALL_POLICY_PLAN_CTRIP_RT = 302;  //按照方案集生成往返政策

    public static final int FUNCTION_SYSTEM_LOGIN = 900;                //登录系统

    public static final int FUNCTION_ADMIN_LOGIN = 1000;                //管理员登录系统
    public static final int FUNCTION_DOWNLOAD_AIRLINE_DATA = 1001;      //下载航空公司网站数据
    public static final int FUNCTION_DOWNLOAD_COUNT_CARRIER_SUBCLASS = 1002;      //定时统计下载数量


    public static final int FD_EXPIRE_DAYS = 35; //本地保存的FD缓存在没有指定有效期时的保鲜期

    public static final double SERVICE_FEE_RATE = 0.002;  //每单收取的服务费率
    public static final int MIN_SERVICE_FEE = 2; //平台每单收取的最小服务费
    public static final int MAX_SERVICE_FEE = 10; //平台每单收取的最大服务费

    public static final int SERVER_ID_ONE1  = 1;
    public static final int SERVER_ID_FOUR = 4;
    public static final int SERVER_TASK = SERVER_ID_FOUR;

    public static final int PSG_TYPE_ADU = 0; // 成人
    public static final int PSG_TYPE_CHD = 1; // 儿童
    public static final int PSG_TYPE_INF = 2; // 婴儿
    public static final int PSG_TYPE_DISABLED_SERVICEMAN_POLICEMAN = 8; // 伤残军人/警察

    public static final int GENDER_MALE = 1; // male 男性
    public static final int GENDER_FEMALE = 2; // female 女性



}
