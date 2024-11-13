package cn.buk.tms.common.dto.flight;

import cn.buk.common.util.DateUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseRefundOrderDtoTest extends BaseTest {


  @Test
  public void test_fromJson() throws Exception {
    final String jsonStr = getTemplateContent("/refund-order-response.json");

    var rs = createObjectMapper().readValue(jsonStr, BaseRefundOrderDto.class);

    assertNotNull(rs);

    assertEquals(0, rs.getErrcode());
    assertEquals(6765, rs.getId());
    assertEquals("10061105", rs.getOrderNo());
    assertEquals("1007225", rs.getRefundOrderNo());
    assertEquals("781", rs.getBalanceCode());
    assertEquals("6900443136", rs.getTicketNo());
    assertEquals("赵珂", rs.getPsgName());
    assertEquals("412723199909228122", rs.getIdNo());
    assertEquals(0, rs.getReasonCode());
    assertEquals("测试退票", rs.getReasonDesc());

    assertEquals(1790, rs.getParValue());
    assertEquals(0, rs.getTax());
    assertEquals(1860, rs.getTicketAmount());
    assertEquals(0, rs.getServiceCharge());
    assertEquals(1770, rs.getPassengerRefundAmount());
    assertEquals(32, rs.getOrderStatus());
    assertEquals(0, rs.getAirRefundStatus());

    assertEquals(1, rs.getFlights().size());

    var flt = rs.getFlights().stream().findFirst().get();
    assertEquals("SHA", flt.getDport());
    assertEquals("PEK", flt.getAport());
    assertEquals("MU5099", flt.getFlightNo());
    assertEquals("2021-09-21", DateUtil.formatDate(flt.getDdate(), "yyyy-MM-dd"));
    assertEquals("", flt.getDtime());
    assertEquals("", flt.getAtime());
    assertEquals("", flt.getDterm());
    assertEquals("", flt.getAterm());
    assertEquals("Y", flt.getSubclass());


  }
}