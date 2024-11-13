package cn.buk.util;

import cn.buk.common.util.BukEtermClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by yfdai on 2017/1/14.
 */
@Disabled
public class BukEtermClientTest {

    private BukEtermClient eterm;

    @BeforeEach
    public void init() {
        eterm = new BukEtermClient("aws1.buk.cn", 350,"test1", "1test", 3000);
    }

    @Disabled
    @Test
    public void testAvCmd() throws IOException, InterruptedException {
        if (!eterm.isConnected()) {
            eterm.connect();
        }

        if (!eterm.isLogined()) {
            eterm.connect();
            eterm.login();
        }

        if (!eterm.isLogined()) {
            System.out.println("not logined");
            return;
        }

        String rs;

//        rs = eterm.execCmd("AV SHAHAK/.");
//        if (rs != null)
//            System.out.println("rs: " + rs.length() + "\r\n" + rs.substring(7).replaceAll("\r", "\r\n"));
//
//
//        rs = eterm.execCmd("AV SHAHAK/+");
//        System.out.println("rs: " + rs.length() + "\r\n" + rs.substring(7).replaceAll("\r", "\r\n"));

//        rs = eterm.execCmd("FD:HFEPEK/MU");
//        System.out.println("rs: " + rs.length() + "\r\n" + rs.substring(7).replaceAll("\r", "\r\n"));

        rs = eterm.execCmd("NFD:HFEPEK/MU#T");
        System.out.println("rs: " + rs.length() + "\r\n" + rs.substring(7).replaceAll("\r", "\r\n"));

    }



}