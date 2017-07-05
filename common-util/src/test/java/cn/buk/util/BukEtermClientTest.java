package cn.buk.util;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by yfdai on 2017/1/14.
 */
public class BukEtermClientTest {

    private BukEtermClient client;

    @Before
    public void init() {
        client = new BukEtermClient();
//        client.setRemoteHost("172.168.1.110");
//        client.setRemotePort(350);
//        client.setUsername(".dyf");
//        client.setPassword("buk");

        client.setRemoteHost("aws1.buk.cn");
        client.setRemotePort(350);
        client.setUsername("free1");
        client.setPassword("free.1");


    }

    @Ignore
    @Test
    public void testCmd() throws Exception {
        client.connect();
        Thread.sleep(3000);
        boolean b = client.isConnected();
        System.out.println(b);

        client.login();

        System.out.println(client.isLogined());

    }

    @Ignore
    @Test
    public void testAvCmd() throws IOException, InterruptedException {
        if (!client.isLogined()) {
            client.connect();
            client.login();
        }

        if (!client.isLogined()) {
            System.out.println("not logined");
            return;
        }

        String rs = client.rt("HZG06X");
        String[] lines = rs.split("\r");
        for (String line : lines)
            System.out.println(line);
    }



}