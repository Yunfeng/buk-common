package cn.buk.util;

import cn.buk.common.util.BukEtermClient;
import cn.buk.common.util.BukEtermClientFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//测试
public class BukEtermClientFactoryTest {

    public static void main(String[] args) {
        // 池子配置文件
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(1);// 整个池最大值
        config.setMaxIdle(1);// 最大空闲
        config.setMinIdle(1);// 最小空闲

        config.setTestWhileIdle(true);// 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性。true是，默认值是false

        // 连接的空闲的最长时间，需要testWhileIdle为true，默认5分钟
        config.setMinEvictableIdleTimeMillis(1000 * 60 * 5);

        // 失效检测时间，需要testWhileIdle为true，默认5分钟
//        setTimeBetweenEvictionRunsMillis(1000 * 60 * 5);
        config.setTimeBetweenEvictionRunsMillis(1000 * 30);


        // 每次检查连接的数量，需要testWhileIdle为true
        config.setNumTestsPerEvictionRun(5);

        // 多个任务需要borrow连接时，阻塞时是否采用公平策略，为true时采用，按照先申请先获得的策略进行borrow操作
        config.setFairness(true);

        // 设置为true时，池中无可用连接，borrow时进行阻塞；为false时，当池中无可用连接，抛出NoSuchElementException异常
        config.setBlockWhenExhausted(true);// 当对象池没有空闲对象时，新的获取对象的请求是否阻塞。true阻塞。默认值是true

        // 最大等待时间，当需要borrow一个连接时，最大的等待时间，如果超出时间，抛出NoSuchElementException异常，-1为不限制时间
//        maxWaitMillis 获取资源时的等待时间,单位毫秒。当 blockWhenExhausted 配置为 true 时,此值有效。 -1 代表无时间限制,一直阻塞直到有可用的资源。(long类型)
        config.setMaxWaitMillis(1000 * 30);// 最大等待时间，-1表示一直等


        config.setTestOnBorrow(true);// 在从对象池获取对象时是否检测对象有效，true是；默认值是false
        config.setTestOnReturn(false);// 在向对象池中归还对象时是否检测对象有效，true是，默认值是false


        GenericObjectPool<BukEtermClient> pool = new GenericObjectPool<>(new BukEtermClientFactory("aws1.buk.cn", 350, "TEST1", "1test", 10000), config);

        // 线程池测试
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            cachedThreadPool.execute(() -> {
                BukEtermClient eterm = null;
                try {
                    eterm = pool.borrowObject();
                    System.out.println("第" + index + "次获取到了连接对象---------" + eterm);
                    System.err.println("当前线程池活跃对象数：" + pool.getNumActive());
//                    Thread.sleep(300);
                    String rs = eterm.execCmd("DA");
                    System.out.println(rs.substring(7).replaceAll("\r", "\r\n"));
                } catch (Exception ex) {
                    System.out.println("Thread " + index + ": " + ex.getMessage());
                } finally {
                    if (eterm != null) {
                        System.out.println("第" + index + "次返回连接对象---------" + eterm);
                        pool.returnObject(eterm);
                    }
                }
            });
//            try {
////                Thread.sleep(1000 * 5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        cachedThreadPool.shutdown();
        try {
            while (!cachedThreadPool.awaitTermination(50, TimeUnit.SECONDS)) {
                System.out.println("100秒没有执行完，强制关闭线程池");
                pool.close();
                cachedThreadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}