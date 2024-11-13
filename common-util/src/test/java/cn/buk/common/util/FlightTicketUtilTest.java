package cn.buk.common.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightTicketUtilTest {

  @Test
  void verifyTicketNo() {
    assertTrue(FlightTicketUtil.verifyTicketNo("781-1234567890"));
    assertTrue(FlightTicketUtil.verifyTicketNo("7811234567890"));
    assertFalse(FlightTicketUtil.verifyTicketNo("781-123456789"));
    assertFalse(FlightTicketUtil.verifyTicketNo("781123456789"));
  }

  @Test
  void verifyConnectionTicketNo() {
    assertTrue(FlightTicketUtil.verifyConnectionTicketNo("781-1234567890-91"));
    assertTrue(FlightTicketUtil.verifyConnectionTicketNo("7811234567890-91"));
    assertFalse(FlightTicketUtil.verifyConnectionTicketNo("781-123456789-790"));
    assertFalse(FlightTicketUtil.verifyConnectionTicketNo("781123456789-790"));
  }

  @Test
  void processTicketNo_1() {
    List<String> tickets = FlightTicketUtil.processTicketNo("781-1234567890");
    assertEquals(1, tickets.size());
    assertEquals("781-1234567890", tickets.get(0));

    tickets = FlightTicketUtil.processTicketNo("7811234567890");
    assertEquals(1, tickets.size());
    assertEquals("781-1234567890", tickets.get(0));

    tickets = FlightTicketUtil.processTicketNo("781123456789");
    assertEquals(0, tickets.size());
  }

  @Test
  void processTicketNo_2() {
    List<String> tickets = FlightTicketUtil.processTicketNo("781-1234567890-1");
    assertEquals(2, tickets.size());
    assertEquals("781-1234567890", tickets.get(0));
    assertEquals("781-1234567891", tickets.get(1));

    tickets = FlightTicketUtil.processTicketNo("7811234567890-1");
    assertEquals(2, tickets.size());
    assertEquals("781-1234567890", tickets.get(0));
    assertEquals("781-1234567891", tickets.get(1));

    tickets = FlightTicketUtil.processTicketNo("7811234567888-90");
    assertEquals(3, tickets.size());
    assertEquals("781-1234567888", tickets.get(0));
    assertEquals("781-1234567889", tickets.get(1));
    assertEquals("781-1234567890", tickets.get(2));

    tickets = FlightTicketUtil.processTicketNo("7819934567888-90");
    assertEquals(3, tickets.size());
    assertEquals("781-9934567888", tickets.get(0));
    assertEquals("781-9934567889", tickets.get(1));
    assertEquals("781-9934567890", tickets.get(2));

  }

  @Test
  void processTicketNo_3() {
    List<String> tickets = FlightTicketUtil.processTicketNo("781-6054767961-62");
    assertEquals(2, tickets.size());
    assertEquals("781-6054767961", tickets.get(0));
    assertEquals("781-6054767962", tickets.get(1));
  }

  @Test
  void processTicketNo_4() {
    List<String> tickets = FlightTicketUtil.processTicketNo("781-1234567890", 1);
    assertEquals(1, tickets.size());
    assertEquals("781-1234567890", tickets.get(0));

    tickets = FlightTicketUtil.processTicketNo("7811234567890", 1);
    assertEquals(1, tickets.size());
    assertEquals("781-1234567890", tickets.get(0));
    assertEquals("1234567890", tickets.get(tickets.size()-1).substring(4));

    tickets = FlightTicketUtil.processTicketNo("7811234567888", 2);
    assertEquals(2, tickets.size());
    assertEquals("781-1234567888", tickets.get(0));
    assertEquals("781-1234567889", tickets.get(1));

    tickets = FlightTicketUtil.processTicketNo("781-9034567888", 3);
    assertEquals(3, tickets.size());
    assertEquals("781-9034567888", tickets.get(0));
    assertEquals("781-9034567889", tickets.get(1));
    assertEquals("781-9034567890", tickets.get(2));
    assertEquals("9034567890", tickets.get(tickets.size()-1).substring(4));
  }

  @Test
  void processTicketNo_5() {
    var tickets = FlightTicketUtil.processTicketNo("781-1234567399-00");
    assertEquals(2, tickets.size());
    assertEquals("781-1234567399", tickets.get(0));
    assertEquals("781-1234567400", tickets.get(1));
  }

}