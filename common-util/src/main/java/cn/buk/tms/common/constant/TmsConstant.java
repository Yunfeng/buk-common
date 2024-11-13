package cn.buk.tms.common.constant;

/**
 * TMS通用常数
 * @author yfdai
 */
public class TmsConstant {

  private TmsConstant() {
    throw new IllegalArgumentException("Constant Class");
  }

  /**
   * 机场信息：参数为机场三字代码，大写
   */
  public static final String AIRPORT_INFO = "airport:%s";
  /**
   * 航司信息：参数为航司两字代码，大写
   */
  public static final String AIRLINES_INFO = "airlines:%s";
  /**
   * 航司的行李信息
   */
  public static final String AIRLINES_LUGGAGE_INFO = "ownerId:%d:airlines:%s:luggage";
  /**
   * 航司的子舱位退改签信息
   */
  public static final String AIRLINES_TGQ_INFO = "ownerId:%d:airlines:%s:tgq";


  /**
   * 全局加价: 参数为ownerId，应用所属企业id
   */
  public static final String GLOBAL_MARKUP = "ownerId:%d:global:markup";
  /**
   * 企业客户享受的其它项目优惠
   * 参数1：ownerId, 应用所属企业id
   * 参数2：customerEnterpriseId，客户对应的企业id
   */
  public static final String CUSTOMER_OT_DISCOUNT = "ownerId:%d:customerEntId:%d:ot:discount";
  /**
   * 企业客户享受的机票优惠政策
   * 参数1：ownerId, 应用所属企业id
   * 参数2：customerEnterpriseId，客户对应的企业id
   */
  public static final String CUSTOMER_FLIGHT_DISCOUNT = "ownerId:%d:customerEntId:%d:flight:discount";

  /**
   * 对企业客户航班查询使用情况的控制信息
   * 参数1：ownerId, 应用所属企业id
   * 参数2：customerEnterpriseId，客户对应的企业id
   */
  public static final String CUSTOMER_AV_COUNT = "ownerId:%d:customerEntId:%d:av:count";

  /**
   * 航司舱位类型：头等，F
   */
  public static final int AIRLINES_CABIN_TYPE_FIRST = 10;
  /**
   * 航司舱位类型：商务，C
   */
  public static final int AIRLINES_CABIN_TYPE_BUSINESS = 20;
  /**
   * 航司舱位类型：经济，Y
   */
  public static final int AIRLINES_CABIN_TYPE_ECONOMY = 30;
  /**
   * 航司舱位类型：超级经济
   */
  public static final int AIRLINES_CABIN_TYPE_PREMIUM_ECONOMY = 40;


  /**
   * 一类、二类、三类、四类、五类差旅地区
   */
  public static final int TRAVEL_ZONE_FIRST_CLASS = 1;
  public static final int TRAVEL_ZONE_SECOND_CLASS = 2;
  public static final int TRAVEL_ZONE_THIRD_CLASS = 3;
  public static final int TRAVEL_ZONE_FOURTH_CLASS = 4;
  public static final int TRAVEL_ZONE_FIFTH_CLASS = 5;


  /**
   * 证件类型：身份证
   * *  1-身份证 NI
   * 	 *  2-护照 PP
   * 	 *  4-港澳通行证 HTPP
   * 	 *  8-台胞证 MTP
   * 	 * 16-回乡证 ORI
   * 	 * 32-台湾通行证 TPP
   * 	 * 64-军官证 MIL
   * 	 * 65-户口簿 HR
   * 	 * 66-出生证明 BR
   * 	 * 67-外国人永久居住身份证 FPRC
   * 	 * 68-港澳居民居住证 GAT
   * 	 * 69-台湾居民居住证 RPTW
   * 	 * 99-其他 OTHER
   */
  public static final int ID_TYPE_NI = 1;
  /**
   * 证件类型：护照
   */
  public static final int ID_TYPE_PASSPORT = 2;
  /**
   * 证件类型：其它
   */
  public static final int ID_TYPE_OTHER = 99;

  /**
   * 性别：未填写
   */
  public static final int GENDER_NOT_SET = 0;
  /**
   * 性别：男
   */
  public static final int GENDER_MALE = 1;
  /**
   * 性别：女
   */
  public static final int GENDER_FEMALE = 2;


  /**
   * 客票所对应的航段状态, 该乘机人对应的所有的票号和航段状态（都一致的状态, 不一致则用-1）
   * -1 - 未知
   * 0 - 未使用
   * 1 - 已使用
   * 2 - 已改签
   * 3 - 已退票
   * 4 - 已作废
   * 128 - 已是终态（具体什么样的终态，不清楚）
   */
  public static final int FLIGHT_TICKET_STATUS_OPEN_FOR_USE = 0;
  public static final int FLIGHT_TICKET_STATUS_USED = 1;
  public static final int FLIGHT_TICKET_STATUS_EXCHANGED = 2;
  public static final int FLIGHT_TICKET_STATUS_REFUNDED = 3;
  public static final int FLIGHT_TICKET_STATUS_VOID = 4;
  public static final int FLIGHT_TICKET_STATUS_FINAL = 128;

  /**
   * 机票自动出票类型（非自动出票）
   */
  public static final int FLIGHT_AUTO_TICKETING_NONE = 0;
  /**
   * 机票自动出票类型（BSP自动出票）
   */
  public static final int FLIGHT_AUTO_TICKETING_BSP = 1;
  /**
   * 机票自动出票类型（GP自动出票）
   */
  public static final int FLIGHT_AUTO_TICKETING_GP = 2;
  /**
   * 机票自动出票类型（NDC CZ）
   */
  public static final int FLIGHT_AUTO_TICKETING_NDC_CZ = 4;

  /**
   * 企业的参数设置
   * %d - 企业id
   * %s - 企业配置的itemCode
   */
  public static final String OWNER_ENT_CONFIG = "owner_%d_config_%s";

  /**
   * Eterm API接口类型：BUK提供的
   */
  public static final int ETERM_API_BUK = 0;

  /**
   * Eterm API接口类型：苏州快捷提供的
   */
  public static final int ETERM_API_SZKJ = 1;

  /**
   * Eterm API接口类型：停用
   */
  public static final int ETERM_API_DISABLED = 4;
}
