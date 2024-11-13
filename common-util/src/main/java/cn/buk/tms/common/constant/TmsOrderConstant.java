package cn.buk.tms.common.constant;

/**
 * TMS中和订单相关的参数
 * @author yfdai
 */
public class TmsOrderConstant {

  private TmsOrderConstant() {
    throw new IllegalArgumentException("Constant Class");
  }

  //订单以1开头
  /**
   * 机票订单前缀
   */
  public static final String ORDER_NO_FLIGHT = "1001";
  /**
   * 自助机票订单前缀
   */
  public static final String ORDER_NO_FLIGHT_SELF_SERVICE = "1006";
  /**
   * 机票退票单前缀
   */
  public static final String ORDER_NO_FLIGHT_REFUND = "1002";
  /**
   * 自助机票退票单前缀
   */
  public static final String ORDER_NO_FLIGHT_REFUND_SELF_SERVICE = "1007";
  /**
   * 机票改签单前缀
   */
  public static final String ORDER_NO_FLIGHT_CHANGE = "1005";
  /**
   * 自助机票改签单前缀
   */
  public static final String ORDER_NO_FLIGHT_CHANGE_SELF_SERVICE = "1008";
  /**
   * 机票订单前缀(微信公众号）
   */
  public static final String ORDER_NO_FLIGHT_WX = "1011";
  /**
   * 机票订单前缀(企业微信）
   */
  public static final String ORDER_NO_FLIGHT_WW = "1012";

  /**
   * 酒店订单前缀
   */
  public static final String ORDER_NO_HOTEL = "1101";
  /**
   * 自助酒店订单前缀
   */
  public static final String ORDER_NO_HOTEL_SELF_SERVICE = "1102";

  public static final String ORDER_NO_TRAIN = "1201"; // 火车票订单前缀
  public static final String ORDER_NO_TRAIN_REFUND = "1202"; // 火车票退票单前缀

  public static final String ORDER_NO_INSURANCE = "1501"; // 保险订单前缀
  public static final String ORDER_NO_VAS = "1601"; // 增值服务订单前缀 value added service

  public static final String ORDER_NO_RENTAL_CAR = "1701"; // 租车订单前缀
  public static final String ORDER_NO_RENTAL_CAR_SELF_SERVICE = "1702"; // 租车订单前缀（EOTMS）
  /**
   * 景点门票订单号前缀
   */
  public static final String ORDER_NO_TICKET = "1800";
  //账单以2开头
  public static final String BILL_NO_FLIGHT = "2001"; //机票账单前缀
  public static final String BILL_NO_FLIGHT_REFUND = "2002"; //机票退票账单前缀
  public static final String BILL_NO_FLIGHT_CHANGE = "2005"; //机票改签账单前缀
  public static final String BILL_NO_VAS = "2006"; //服务账单前缀

  public static final String BILL_NO_HOTEL = "2101"; //酒店账单前缀
  public static final String BILL_NO_TRAIN = "2201"; //火车票账单前缀

  public static final String ENT_RECEIPT_NO = "8001"; //企业收款单前缀

  /**
   * 数据模板
   */
  public static final String DATA_TEMPLATE_NO = "9001";

  /**
   * 配送单单号前缀
   */
  public static final String DELIVERY_NOTE_NO = "9000";

  public static final String BILL_TYPE_FLIGHT = "0"; //机票
  public static final String BILL_TYPE_CREDENCE = "1"; //换开凭证
  public static final String BILL_TYPE_HOTEL = "2"; //酒店
  public static final String BILL_TYPE_TRAIN = "3"; //火车票
  public static final String BILL_TYPE_CAR = "4"; //租车
  public static final String BILL_TYPE_FLIGHT_REFUND = "5"; //飞机票退票
  public static final String BILL_TYPE_VAS = "6"; //增值服务
  public static final String BILL_TYPE_FLIGHT_CHANGE = "7"; //飞机票改签

  public static final String BILL_AUDITED = "1"; // 账单已审核
  public static final String BILL_NOT_AUDITED = "0"; // 账单未审核

  public static final String MANUAL_WRITE_OFF_REMARK = "手工单张销账"; //手工销账备注

  //订单类型（原则上一个实体类一个订单类型）
  /**
   * 机票订单
   */
  public static final int ORDER_TYPE_FLIGHT = 1000; //机票预订单
  /**
   * 机票订单的取消请求
   */
  public static final int FLIGHT_ORDER_CANCEL_REQUEST = 1040;
  public static final int ORDER_TYPE_FLIGHT_REFUND = 1001; //机票退票单
  public static final int ORDER_TYPE_FLIGHT_CHANGE = 1002; //机票改签单

  public static final int ORDER_TYPE_HOTEL = 2000; //酒店预订单

  public static final int ORDER_TYPE_TRAIN = 3000; //火车票预订单

  public static final int ORDER_TYPE_CAR = 4000; //用车预订单

  public static final int ORDER_TYPE_SERVICE = 5000; //服务预订单
  public static final int ORDER_TYPE_INSURANCE = 6000; //保险预订单
  /**
   * 订单类型：门票订单
   */
  public static final int ORDER_TYPE_TICKET = 8000;


  /**
   * 订单付款方式：现金
   */
  public static final int PAY_TYPE_CASH = 1;
  /**
   * 订单付款方式：信用卡
   */
  public static final int PAY_TYPE_CC = 2;
  /**
   * 订单付款方式：支票
   */
  public static final int PAY_TYPE_CHECK = 4;
  /**
   * 订单付款方式：记账
   */
  public static final int PAY_TYPE_BILL = 8;


  /**
   * 付款状态: 待付款/未付款
   */
  public static final int PAY_STATUS_WAITING = 0;
  /**
   * 付款状态：已付款
   */
  public static final int PAY_STATUS_PAID = 1;
  /**
   * 付款状态：已销账
   */
  public static final int PAY_STATUS_WRITTEN_OFF = 2;


  /**
   * 成人价格类型
   */
  public static final int PRICE_TYPE_ADULT = 0;
  /**
   * 儿童价格类型
   */
  public static final int PRICE_TYPE_CHILD = 1;
  /**
   * 婴儿价格类型
   */
  public static final int PRICE_TYPE_INFANT = 2;


  /**
   * 通用的订单状态：待处理
   */
  public static final int ORDER_STATUS_WAITING = 0;
  /**
   * 通用的订单状态：处理中
   */
  public static final int ORDER_STATUS_PROCESSING = 1;
  /**
   * 通用的订单状态：已处理
   */
  public static final int ORDER_STATUS_DONE = 2;
  /**
   * 通用的订单状态：已取消
   */
  public static final int ORDER_STATUS_CANCELED = 4;


  /**
   * 酒店订单状态：未提交
   * 等待客户(或业务员）提交
   */
  public static final int HOTEL_ORDER_STATUS_WAITING = 0;
  /**
   * 酒店订单状态：已提交，待审核
   * 如果客户不需要审核，则这一步自动完成，同机票订单类似
   */
  public static final int HOTEL_ORDER_STATUS_SUBMITTED = 10;
  /**
   * 酒店订单状态：已审核，待处理
   */
  public static final int HOTEL_ORDER_STATUS_WAIT_PROCESSING = 20;
  /**
   * 酒店订单状态：处理中
   */
  public static final int HOTEL_ORDER_STATUS_PROCESSING = 1;
  /**
   * 酒店订单状态：已确认
   * 处理完成
   */
  public static final int HOTEL_ORDER_STATUS_DONE = 2;
  /**
   * 酒店订单状态：已取消
   */
  public static final int HOTEL_ORDER_STATUS_CANCELED = 4;


  /**
   * 机票订单状态：等待客户(或业务员）提交开票
   */
  public static final int FLIGHT_ORDER_STATUS_WAITING = 0;
  /**
   * 机票订单状态：已提交，待审核
   */
  public static final int FLIGHT_ORDER_STATUS_SUBMITTED = 1;
  /**
   * 机票订单状态：已提交，审核通过，等待开票
   */
  public static final int FLIGHT_ORDER_STATUS_WAIT_TICKETING = 2;
  /**
   * 机票订单状态：已认领出票
   */
  public static final int FLIGHT_ORDER_STATUS_TICKETING_ACCEPTED = 8;
  /**
   * 机票订单状态：出票完成
   */
  public static final int FLIGHT_ORDER_STATUS_TICKETED = 16;
  /**
   * 机票订单状态：订单完成
   */
  public static final int FLIGHT_ORDER_STATUS_FINISHED = 32;
  /**
   * 机票订单状态：已取消
   */
  public static final int FLIGHT_ORDER_STATUS_CANCELED = 4;


  /**
   * 租车订单状态：未提交状态
   */
  public static final int RENTAL_CAR_ORDER_STATUS_NOT_SUBMITTED = 0;
  /**
   * 租车订单状态：已提交，待审批
   */
  public static final int RENTAL_CAR_ORDER_STATUS_SUBMITTED = 10;
  /**
   * 租车订单状态：已提交，已审批，待处理
   */
  public static final int RENTAL_CAR_ORDER_STATUS_WAITING = 1;
  /**
   * 租车订单状态：处理中
   */
  public static final int RENTAL_CAR_ORDER_STATUS_PROCESSING = 2;
  /**
   * 租车订单状态：已取消
   */
  public static final int RENTAL_CAR_ORDER_STATUS_CANCELED = 4;
  /**
   * 租车订单状态：处理完成
   */
  public static final int RENTAL_CAR_ORDER_STATUS_DONE = 8;

  /**
   * 门票订单状态：未提交，待付款
   */
  public static final int TICKET_ORDER_STATUS_NOT_SUBMITTED = 0;
  /**
   * 门票订单状态：已提交，待处理
   */
  public static final int TICKET_ORDER_STATUS_WAITING = 1;
  /**
   * 门票订单状态：处理中
   */
  public static final int TICKET_ORDER_STATUS_PROCESSING = 2;
  /**
   * 门票订单状态：已处理
   */
  public static final int TICKET_ORDER_STATUS_DONE = 8;
  /**
   * 门票订单状态：已取消
   */
  public static final int TICKET_ORDER_STATUS_CANCELED = 4;


  /**
   * 操作对象类型：机票订单
   */
  public static final int OPERATING_OBJECT_FLIGHT_ORDER = 0;
  /**
   * 操作对象类型：机票退票单
   */
  public static final int OPERATING_OBJECT_FLIGHT_REFUND_ORDER = 1;
  /**
   * 操作对象类型：服务单
   */
  public static final int OPERATING_OBJECT_VAS_ORDER = 2;
  /**
   * 操作对象类型：机票改签单
   */
  public static final int OPERATING_OBJECT_FLIGHT_CHANGE_ORDER = 3;
  /**
   * 操作对象类型：酒店订单
   */
  public static final int OPERATING_OBJECT_HOTEL_ORDER = 4;
  /**
   * 操作对象类型：火车票订单
   */
  public static final int OPERATING_OBJECT_TRAIN_ORDER = 5;
  /**
   * 操作对象类型：火车票退票单
   */
  public static final int OPERATING_OBJECT_TRAIN_REFUND_ORDER = 5001;
  /**
   * 操作对象类型：租车订单
   */
  public static final int OPERATING_OBJECT_CAR_ORDER = 7;
  /**
   * 操作对象类型: 门票订单
   */
  public static final int OPERATING_OBJECT_TICKET_ORDER = 8;
  /**
   * 操作对象类型：账单
   */
  public static final int OPERATING_OBJECT_BILL = 10;
  /**
   * 操作对象类型：结算单
   */
  public static final int OPERATING_OBJECT_SETTLEMENT = 11;
  /**
   * 操作对象类型：收款单
   */
  public static final int OPERATING_OBJECT_RECEIPT = 12;
  /**
   * 操作对象类型：配送单
   */
  public static final int OPERATING_OBJECT_DELIVERY = 20;
  /**
   * 操作对象类型：机票追位订单
   */
  public static final int OPERATING_OBJECT_FLIGHT_CATCH_ORDER = 100;
  /**
   * 操作对象类型：团队，包机订单
   */
  public static final int OPERATING_OBJECT_FLIGHT_CHARTER_ORDER = 200;
  /**
   * 操作对象类型：携程tbooking 机票订单
   */
  public static final int OPERATING_OBJECT_TBOOKING_FLIGHT_ORDER = 300;
  /**
   * 操作对象类型：基础数据
   */
  public static final int OPERATING_OBJECT_BASIC_DATA = 10001;
  /**
   * 操作对象类型：客户的基础数据（员工资料、部门等）
   */
  public static final int OPERATING_OBJECT_CUSTOMER_BASIC_DATA = 10002;

  /**
   * 操作对象类型：任务
   */
  public static final int OPERATING_OBJECT_TMS_TASK = 10003;


  /**
   * 供应商类型：综合
   */
  public static final int SUPPLIER_TYPE_ALL = 0;
  /**
   * 供应商类型：机票
   */
  public static final int SUPPLIER_TYPE_FLIGHT = 1;
  /**
   * 供应商类型：保险
   */
  public static final int SUPPLIER_TYPE_INSURANCE = 2;
  /**
   * 供应商类型：酒店
   */
  public static final int SUPPLIER_TYPE_HOTEL = 3;
  /**
   * 供应商类型：旅游
   */
  public static final int SUPPLIER_TYPE_TRAVEL = 4;
  /**
   * 供应商类型：火车
   */
  public static final int SUPPLIER_TYPE_TRAIN = 5;
  /**
   * 供应商类型：租车
   */
  public static final int SUPPLIER_TYPE_RENTAL_CAR = 6;


  /**
   * 产品类型：国内机票
   */
  public static final int PRODUCT_TYPE_DOM_FLIGHT = 1;
  /**
   * 产品类型：国际机票
   */
  public static final int PRODUCT_TYPE_INTL_FLIGHT = 2;
  /**
   * 产品类型：国内酒店
   */
  public static final int PRODUCT_TYPE_DOM_HOTEL = 4;
  /**
   * 产品类型：国际酒店
   */
  public static final int PRODUCT_TYPE_INTL_HOTEL = 8;
  /**
   * 产品类型：保险
   */
  public static final int PRODUCT_TYPE_INSURANCE = 16;
  /**
   * 产品类型：国内租车
   */
  public static final int PRODUCT_TYPE_DOM_CAR = 32;
  /**
   * 产品类型：国际租车
   */
  public static final int PRODUCT_TYPE_INTL_CAR = 64;
  /**
   * 产品类型：机场服务
   */
  public static final int PRODUCT_TYPE_VAAS = 128;


  /**
   * 自动出票：非自动出票
   */
  public static final int AUTO_PRINT_TICKET_NONE = 0;
  /**
   * 自动出票：等待自动出票
   */
  public static final int AUTO_PRINT_TICKET_WAITING = 1;
  /**
   * 自动出票：自动出票中
   */
  public static final int AUTO_PRINT_TICKET_PROCESSING = 2;
  /**
   * 自动出票：自动出票完成
   */
  public static final int AUTO_PRINT_TICKET_DONE = 8;


  /**
   * 显示状态：综合退票、改签、DETR信息显示给客户的状态
   * 机票订单中的全部机票使用状态：部分使用
   */
  public static final int FLIGHT_ORDER_TICKET_PARTIAL_USED = 1;
  /**
   * 机票订单中的全部机票使用状态：全部使用
   */
  public static final int FLIGHT_ORDER_TICKET_WHOLE_USED = 2;
  /**
   * 机票订单中的全部机票使用状态：部分改签
   */
  public static final int FLIGHT_ORDER_TICKET_PARTIAL_EXCHANGED = 4;
  /**
   * 机票订单中的全部机票使用状态：全部改签
   */
  public static final int FLIGHT_ORDER_TICKET_WHOLE_EXCHANGED = 8;
  /**
   * 机票订单中的全部机票使用状态：部分退票
   */
  public static final int FLIGHT_ORDER_TICKET_PARTIAL_REFUNDED = 16;
  /**
   * 机票订单中的全部机票使用状态：全部退票
   */
  public static final int FLIGHT_ORDER_TICKET_WHOLE_REFUNDED = 32;


  /**
   *  订单价格校验状态: 未校验
   */
  public static final int ORDER_PRICE_VERIFY_STATE_NONE = 0;
  /**
   * 订单价格校验状态: 已校验
   */
  public static final int ORDER_PRICE_VERIFY_STATE_DONE = 1;
  /**
   * 订单价格校验状态: 校验失败, 需要人工干预
   */
  public static final int ORDER_PRICE_VERIFY_STATE_FAILED = 4;


  /**
   * 改签前的航班
   */
  public static final int FLIGHT_BEFORE_CHANGED = 0;
  /**
   * 改签后的航班
   */
  public static final int FLIGHT_AFTER_CHANGED = 1;
}
