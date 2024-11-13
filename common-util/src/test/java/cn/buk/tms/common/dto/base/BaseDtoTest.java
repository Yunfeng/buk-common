package cn.buk.tms.common.dto.base;

import cn.buk.tms.common.dto.flight.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseDtoTest extends BaseTest {

  @Test
  void toJson() throws Exception {
    var dto = new BaseDto();
    dto.setVersion(1);
    dto.setErrcode(0);
    dto.setErrmsg("no error message");
    dto.setReturnCode(123);

    var jsonStr = createObjectMapper().writeValueAsString(dto);

    final String jsonStr0 = getTemplateContent("/base-dto.json");

    assertEquals(jsonStr0, jsonStr);
  }

  @Test
  void fromJson() throws Exception {
    final String jsonStr = getTemplateContent("/base-dto.json");
    var dto = createObjectMapper().readValue(jsonStr, BaseDto.class);

    assertEquals(1, dto.getVersion());
    assertEquals(0, dto.getErrcode());
    assertEquals("no error message", dto.getErrmsg());
    assertEquals(123, dto.getReturnCode());
  }
}