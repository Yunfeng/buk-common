package cn.buk.tms.common.dto.base;

import cn.buk.tms.common.dto.flight.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseOrderDtoTest extends BaseTest {

  @Test
  void toJson() throws JsonProcessingException {
    var dto = new BaseOrderDto();
    var jsonStr = createObjectMapper().writeValueAsString(dto);

    System.out.println(jsonStr);
  }

  @Test
  void fromJson() throws Exception {
    final String jsonStr = getTemplateContent("/base-order-dto.json");
    var dto = createObjectMapper().readValue(jsonStr, BaseOrderDto.class);

    assertEquals(null, dto.getVersion());
    assertEquals(0, dto.getErrcode());
    assertEquals(null, dto.getErrmsg());
    assertEquals(null, dto.getReturnCode());

    assertEquals(65383, dto.getId());
    assertEquals("100160843", dto.getOrderNo());
    assertEquals(0, dto.getOrderType());
    assertEquals(0, dto.getOrderStatus());
    assertEquals("", dto.getCostCenter());
    assertEquals("", dto.getProjectName());
    assertEquals(8, dto.getPayType());
    assertEquals(0, dto.getPayStatus());
    assertEquals(1000, dto.getTotal());
    assertEquals("AAT", dto.getCustomerName());
    assertEquals("6888", dto.getCustomerCode());
    assertEquals(null, dto.getSpecifiedApprover());
    assertEquals(null, dto.getApprovalStatus());
    assertEquals(null, dto.getApprovalDenyReason());
    assertEquals(null, dto.getViolationStatus());
    assertEquals(null, dto.getViolationReason());
  }
}