package cn.buk.common.util;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class BukEtermClientPoolFactory {

    /**
     * 创建BukEtermClient连接池
     * @param host 主机
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @param timeout 超时 ms
     * @param maxTotal 最大连接数
     */
    public static GenericObjectPool<BukEtermClient> createBukEtermClientPool(String host, int port, String username, String password, int timeout, int maxTotal) {
        return createBukEtermClientPool(host, port, username, password, timeout, maxTotal, 4 * 60 * 60 * 1000L);
    }

    /**
     * 创建BukEtermClient连接池
     * @param host 主机
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @param timeout 超时 ms
     * @param maxTotal 最大连接数
     * @param minEvictableIdleTime 最小空闲时间（可以从池中移除）
     */
    public static GenericObjectPool<BukEtermClient> createBukEtermClientPool(String host, int port, String username, String password, int timeout,
                                                                             final int maxTotal, final long minEvictableIdleTime) {
        // 池子配置文件
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);// 整个池最大值
        config.setMaxIdle(maxTotal);// 最大空闲
        config.setMinIdle(1);// 最小空闲


        config.setTestWhileIdle(true);// 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性。true是，默认值是false

        // 连接的空闲的最长时间，需要 testWhileIdle 为true，默认5分钟
        if (minEvictableIdleTime < (1000 * 60)) {
            config.setMinEvictableIdleTimeMillis(1000 * 60 * 10);
        } else {
            config.setMinEvictableIdleTimeMillis(minEvictableIdleTime);
        }

        // 失效检测时间，需要testWhileIdle为true，默认5分钟
        // 每隔都就去检查一下连接是否失效
//        去除线程每 1 分钟执行一次
        config.setTimeBetweenEvictionRunsMillis(1000 * 60);

        // 每次检查连接的数量，需要testWhileIdle为true
        config.setNumTestsPerEvictionRun(maxTotal);

        // 多个任务需要borrow连接时，阻塞时是否采用公平策略，为true时采用，按照先申请先获得的策略进行borrow操作
        config.setFairness(true);

        // 设置为true时，池中无可用连接，borrow时进行阻塞；为false时，当池中无可用连接，抛出NoSuchElementException异常
        config.setBlockWhenExhausted(true);// 当对象池没有空闲对象时，新的获取对象的请求是否阻塞。true阻塞。默认值是true

        // 最大等待时间，当需要borrow一个连接时，最大的等待时间，如果超出时间，抛出NoSuchElementException异常，-1为不限制时间
//        maxWaitMillis 获取资源时的等待时间,单位毫秒。当 blockWhenExhausted 配置为 true 时,此值有效。 -1 代表无时间限制,一直阻塞直到有可用的资源。(long类型)
        config.setMaxWaitMillis(1000 * 30);// 最大等待时间，-1表示一直等


        config.setTestOnBorrow(true);// 在从对象池获取对象时是否检测对象有效，true是；默认值是false
        config.setTestOnReturn(false);// 在向对象池中归还对象时是否检测对象有效，true是，默认值是false


        return new GenericObjectPool<>(new BukEtermClientFactory(host, port, username, password, timeout), config);
    }
}
