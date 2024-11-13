package cn.buk.tms.common.dto.flight;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BaseTest {

  protected ObjectMapper createObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    JavaTimeModule timeModule = new JavaTimeModule();
//    timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
//    timeModule.addDeserializer(Date.class, new JacksonCustomerDateJsonDeserializer());
    objectMapper.registerModule(timeModule);
//    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

//    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 忽略未知字段

    return objectMapper;
  }

  protected String getTemplateContent(final String fileName) throws Exception {
    InputStream inputStream = getClass().getResourceAsStream(fileName);
    assertNotNull(inputStream, "Test file missing." + fileName);

    int length = inputStream.available();
    byte bytes[] = new byte[length];
    inputStream.read(bytes);
    inputStream.close();
    String str = new String(bytes, StandardCharsets.UTF_8);

    return str;
  }
}
