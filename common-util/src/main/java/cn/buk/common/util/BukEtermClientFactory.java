package cn.buk.common.util;


import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * eterm连接池工厂
 * @author yfdai
 */
public class BukEtermClientFactory extends BasePooledObjectFactory<BukEtermClient>  {

    private static final Logger logger = LogManager.getLogger(BukEtermClientFactory.class);

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final int timeout;

    /**
     * 构造函数
     * @param host host
     * @param port port
     * @param username username
     * @param password password
     * @param timeout 指令超时时间 ms
     */
    public BukEtermClientFactory(String host, int port, String username, String password, int timeout) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.timeout = timeout;
    }



    /**
     * 创建对象
     */
    @Override
    public BukEtermClient create() {
        return new BukEtermClient(this.host, this.port, this.username, this.password, this.timeout);
    }

    /**
     * 包装对象
     */
    @Override
    public PooledObject<BukEtermClient> wrap(BukEtermClient obj) {
        return new DefaultPooledObject<>(obj);
    }

    /**
     * 验证实例的有效性
     * @param p 对象
     * @return false 实例已无效，从连接池中移除; true 有效，保留
     */
    @Override
    public boolean validateObject(PooledObject<BukEtermClient> p) {
        BukEtermClient eterm = p.getObject();

        try {
            String cmdResult = eterm.execCmd("");
            if (cmdResult.substring(7).length() > 0) {
                return true;
            }
        } catch (Exception ex) {
            logger.info("validateObject(will be removed): " + p.getObject().hashCode() + ", " + eterm.getRequestTime());
            logger.error(ex.getMessage());
            return false;
        }

        if (eterm.getRequestTime() != null && DateUtil.getPastSeconds(eterm.getRequestTime()) <= 300) {
            logger.debug("validateObject: " + p.getObject().hashCode() + ", " + eterm.getRequestTime());
            return true;
        } else {
            logger.debug("validateObject(will be removed): " + p.getObject().hashCode() + ", " + eterm.getRequestTime());
            return false;
        }
    }

    /**
     * 在获取对象之前可以进行的操作
     * @param p 对象
     * @throws Exception exception
     */
    @Override
    public void activateObject(PooledObject<BukEtermClient> p) throws Exception {
        BukEtermClient etermClient = p.getObject();

//        System.out.println("activateObject: " + p.hashCode() + ", " + etermClient.isLogined());

        if (!etermClient.isLogined()) {
            etermClient.connect();
            etermClient.login();
        }
        super.activateObject(p);
    }

    // 在归还对象之前可以进行的操作
    @Override
    public void passivateObject(PooledObject<BukEtermClient> p) throws Exception {
//        BukEtermClient etermClient = p.getObject();

//        System.out.println("passivateObject: " + p.hashCode() + ", " + DateUtil.getCurDateTime());
//        p.getObject().close();
        super.passivateObject(p);
    }

    /**
     * 从连接池中销毁一个连接
     * @param p object
     */
    @Override
    public void destroyObject(PooledObject<BukEtermClient> p) {
        logger.debug("destroyObject:" + p.getObject().hashCode() + " is being removed from the pool.");
    }
}
