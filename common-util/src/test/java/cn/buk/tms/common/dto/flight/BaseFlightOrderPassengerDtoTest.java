package cn.buk.tms.common.dto.flight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseFlightOrderPassengerDtoTest {

  @Test
  void test_getShowTicketNo() {
    BaseFlightOrderPassengerDto dto = new BaseFlightOrderPassengerDto();
    dto.setTicketNo("784-2115101027");
    dto.setTicketCount(20);
    dto.setTicketNoEnd("2115101046");

    assertEquals("784-2115101027-46", dto.getShowTicketNo());
  }
}