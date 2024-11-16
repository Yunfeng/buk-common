package cn.buk.redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yfdai
 */
public class RedisDaoImpl implements RedisDao {

  private static final Logger logger = LogManager.getLogger(RedisDaoImpl.class);

  private final String host;
  private final int port;
  /**
   * 0-不启用，
   * 1-启用
   */
  private int used;

  public RedisDaoImpl(
          String host,
          int port,
          int used) {
    this.host = host;
    this.port = port;
    this.used = used;
  }


  private boolean isUsed() {
    return this.used != 0;
  }

  @Override
  public long incr(String key, long expiredSeconds) {
    long retCode = 0;
    if (!isUsed()) {
      return retCode;
    }


    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis != null) {
        retCode =  jedis.incr(key.getBytes());
        jedis.expire(key.getBytes(), expiredSeconds);
      } else {
        logger.error("jedis is null.");
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return retCode;
  }

  /**
   * 增加API的调用次数(有些API的调用单次受到限制)
   * 返回值大于0,表示增加成功
   * 返回值 = 0,表示增加失败
   *
   */
  @Override
  public long incrApiCallCount(final String key, final long expiredSeconds, final int maxCalls) {
    long retCode = 0;
    if (!isUsed()) {
      return retCode;
    }

    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis != null) {
        //检查下该key的ttl的返回值是否正常,这里总是会出现该KEY不自动删除的情况,难道是REDIS的bug? 2016-4-9
        if (jedis.ttl(key) == -1) {
          //-1表示该key没有失效时间,不应该吧
          logger.error(key + " ttl: -1.");
          jedis.del(key);
        }


        String value = jedis.get(key);
        if (value == null) {
          Transaction transaction = jedis.multi();
          transaction.incr(key);
          transaction.expire(key, expiredSeconds);
          transaction.exec();
          retCode = 1;
        } else {
          int intvalue = Integer.parseInt(value);
          if (intvalue < maxCalls) {
            retCode = jedis.incr(key);
          }
        }
      } else {
        logger.error("jedis is null.");
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return retCode;
  }

  @Override
  public String set(String key, byte[] value) {
    if (!isUsed()) {
      return null;
    }

    String setStatus = "";
    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis != null) {
        setStatus = jedis.set(key.getBytes(), value);
        logger.info("jedis set key{" + key + "}: " + setStatus);
      } else {
        logger.error("jedis is null.");
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return setStatus;
  }

  @Override
  public String set(String key, byte[] value, long expiredTime) {
    if (!isUsed()) {
      return null;
    }

    String setStatus = "";
    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis != null) {
        setStatus = jedis.set(key.getBytes(), value);
        jedis.expireAt(key.getBytes(), expiredTime);
        logger.info("jedis set key{" + key + "}: " + setStatus);
      } else {
        logger.error("jedis is null.");
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return setStatus;
  }

  @Override
  public String setAndExpire(String key, byte[] value, final long seconds) {
    if (!isUsed()) {
      return null;
    }

    String setStatus = "";
    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis != null) {
        jedis.setex(key.getBytes(), seconds, value);
      } else {
        logger.error("jedis is null.");
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return setStatus;
  }

  @Override
  public String setAndExpireAt(String key, byte[] value, long unixTime) {
    if (!isUsed()) {
      return null;
    }

    String setStatus = "";
    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis != null) {
        setStatus = jedis.set(key.getBytes(), value);
        jedis.expireAt(key.getBytes(), unixTime);
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return setStatus;
  }

  @Override
  public long addToList(String key, String value) {
    long retCode = 0;
    if (!isUsed()) {
      return 0;
    }

    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis != null) {
        retCode = jedis.rpush(key, value);
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return retCode;
  }

  @Override
  public String getFromList(String key) {
    String retVal = null;
    if (!isUsed()) {
      return retVal;
    }

    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis != null) {
        retVal = jedis.lpop(key);
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return retVal;
  }


  @Override
  public long getListCount(final String key) {
    long retCode = 0;
    if (!isUsed()) {
      return 0;
    }

    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis != null) {
        retCode =  jedis.llen(key);
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return retCode;
  }

  /**
   * 获取redis list中所有的内容,然后清空
   */
  @Override
  public List<String> getAllAndEmptyList(final String key) {
    if (!isUsed()) {
      return new ArrayList<>();
    }


    List<String> results = null;

    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis != null) {
        results = jedis.lrange(key, 0, -1);
        jedis.del(key);
      }
    } catch (Exception ex) {
      logger.warn(ex.getMessage());
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return results;
  }

  @Override
  public byte[] get(String key) {
    if (!isUsed()) {
      return null;
    }

    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    byte[] objByte = new byte[0];
    try {
      if (jedis != null) {
        objByte = jedis.get(key.getBytes());
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

    return objByte;
  }

  @Override
  public String getStr(String key) {
    return getStr(key, StandardCharsets.UTF_8);
  }

  @Override
  public String getStr(String key, Charset charset) {
    byte[] bytes = get(key);

    return bytes == null ? null : new String(bytes, charset);
  }

  @Override
  public Boolean exists(String key) {
    if (!isUsed()) {
      return false;
    }

    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis == null) {
        return false;
      } else {
        return jedis.exists(key);
      }
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }
  }

  @Override
  public long remove(String key) {
    if (!isUsed()) {
      return 0;
    }

    Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
    try {
      if (jedis == null) {
        return 0;
      }

      return  jedis.del(key);
    } finally {
      JedisUtil.getInstance().closeJedis(jedis, host, port);
    }

  }

  @Override
  public Set<String> smembers(String key) {
    Set<String> retVal = null;
    if (isUsed()) {

      Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
      try {
        if (jedis != null) {
          retVal = jedis.smembers(key);
        }
      } finally {
        JedisUtil.getInstance().closeJedis(jedis, host, port);
      }
    }

    return retVal == null ? new HashSet<>() : retVal;
  }

  @Override
  public long scard(String key) {
    long retVal = 0;
    if (isUsed()) {

      Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
      try {
        if (jedis != null) {
          retVal = jedis.scard(key);
        }
      } finally {
        JedisUtil.getInstance().closeJedis(jedis, host, port);
      }
    }

    return retVal;
  }

  @Override
  public long sadd(String key, List<String> elements) {
    long retVal = 0;
    if (isUsed()) {

      Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
      try {
        if (jedis != null) {
          for (String val : elements) {
            retVal += jedis.sadd(key, val);
          }
        }
      } finally {
        JedisUtil.getInstance().closeJedis(jedis, host, port);
      }
    }

    return retVal;
  }

  @Override
  public long sadd(String key, String value) {
    long retVal = 0;
    if (isUsed()) {
      Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
      try {
        if (jedis != null) {
          retVal += jedis.sadd(key, value);
        }
      } finally {
        JedisUtil.getInstance().closeJedis(jedis, host, port);
      }
    }

    return retVal;
  }

  @Override
  public Set<String> sdiff(String key1, String key2) {
    Set<String> retVal = null;
    if (isUsed()) {

      Jedis jedis = JedisUtil.getInstance().getJedis(host, port);
      try {
        if (jedis != null) {
          retVal = jedis.sdiff(key1, key2);
        }
      } finally {
        JedisUtil.getInstance().closeJedis(jedis, host, port);
      }
    }

    return retVal == null ? new HashSet<>() : retVal;
  }

  public String getHost() {
    return host;
  }


  public int getPort() {
    return port;
  }


  public int getUsed() {
    return used;
  }

  public void setUsed(int used) {
    this.used = used;
  }
}
