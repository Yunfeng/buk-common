package cn.buk.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author yfdai
 */
public class JsonUtil {

  private JsonUtil() {
    throw new RuntimeException("Util class can't be initialized.");
  }

  private static ObjectMapper createObjectMapper(boolean ignoreUnknown) {
    ObjectMapper objectMapper = new ObjectMapper();
    JavaTimeModule timeModule = new JavaTimeModule();
//    timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
//    timeModule.addDeserializer(Date.class, new JacksonCustomerDateJsonDeserializer());
    objectMapper.registerModule(timeModule);
//    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    if (ignoreUnknown) {
      // 忽略未知字段
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    //反序列化时，空字符串作为null赋值给对象
    objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    //序列化时，允许不包含任何内容的对象
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

    return objectMapper;
  }

  public static <T> T convertJson2Obj(final String jsonStr, Class<T> tClass) {
    return convertJson2Obj(jsonStr, tClass, true);
  }

  public static <T> T convertJson2Obj(final String jsonStr, Class<T> tClass, boolean ignoreUnknown) {
    try {
      return createObjectMapper(ignoreUnknown).readValue(jsonStr, tClass);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static <T, K> T convertJson2Obj(final String jsonStr, Class<T> tClass, Class<K> kClass, boolean ignoreUnknown) throws JsonProcessingException {
    var mapper = createObjectMapper(ignoreUnknown);
    var javaType = mapper.getTypeFactory().constructParametricType(tClass, kClass);
    return mapper.readValue(jsonStr, javaType);
  }

  public static String toJSONString(Object tClass) {
    try {
      return createObjectMapper(true).writeValueAsString(tClass);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}
