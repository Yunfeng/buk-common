package cn.buk.tms.common.dto.flight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BaseFlightOrderDtoTest extends BaseTest {


  @Test
  public void loadFromJson() throws Exception {
    final String jsonStr = getTemplateContent("/base-flight-order-dto.json");
    var requestDto = createObjectMapper().readValue(jsonStr, BaseFlightOrderDto.class);

    assertEquals("", requestDto.getCostCenter());
    assertEquals("", requestDto.getProjectName());
    assertEquals("", requestDto.getLinkman());
    assertEquals("", requestDto.getLinkPhone());
    assertEquals(0, requestDto.getIntlTicket());
    assertEquals(8, requestDto.getPayType());
    assertEquals(0, requestDto.getItineraryType());
    assertEquals("上海市普陀区武威东路", requestDto.getAddress());
    assertEquals("", requestDto.getRemark());
    assertNull(requestDto.getPolicyCode());
    assertEquals(0, requestDto.getGpTicket());
    assertEquals(940, requestDto.getTotalAmount());

    var price = requestDto.getPrices().get(0);
    assertEquals(0, price.getPriceType());
    assertEquals(890, price.getPrice());
    assertEquals(890, price.getParValue());
    assertEquals(50, price.getTax());
    assertEquals(0, price.getServiceCharge());
    assertEquals(0, price.getDiscount());
  }
}