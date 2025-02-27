package cn.buk.redis;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

/**
 * @author yfdai
 */
public interface RedisDao {


  byte[] get(String key);

  String getStr(String key);

  String getStr(String key, Charset charset);

  long incr(String key, long expiredSeconds);

  long incrApiCallCount(String key, long expiredSeconds, int maxCalls);

  String set(String key, byte[] value);

  String set(String key, byte[] value, long expiredTime);

  String setAndExpire(String key, byte[] value, long seconds);

  String setAndExpireAt(String key, byte[] value, long unixTime);

  long addToList(String key, String value);

  String getFromList(String key);

  long getListCount(String key);

  List<String> getAllAndEmptyList(String key);


  Boolean exists(String key);

  long remove(String key);

  /**
   * 获取集合key中得所有成员
   */
  Set<String> smembers(String key);

  /**
   * 取得集合key中得成员数量
   */
  long scard(String key);

  /**
   * 向集合key中增加元素
   */
  long sadd(String key, List<String> elements);

  long sadd(String key, String value);

  /**
   * 取集合key1和key2的差集
   */
  Set<String> sdiff(String key1, String key2);

  /**
   * SETNX actually means "SET if Not eXists".
   * @param key
   * @param value
   * @param expiredSeconds
   * @return
   */
  long setNx(String key, String value, long expiredSeconds);
}
