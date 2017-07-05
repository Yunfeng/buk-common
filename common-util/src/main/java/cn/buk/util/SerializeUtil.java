package cn.buk.util;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by yfdai on 2015-3-28.
 */
public class SerializeUtil {
    private static Logger logger = Logger.getLogger(SerializeUtil.class);

    public static byte[] serialize(Object object) throws IOException {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);

        oos.writeObject(object);
        byte[] bytes = baos.toByteArray();

        return bytes;
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;

        try {
            //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);

            return ois.readObject();

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}

