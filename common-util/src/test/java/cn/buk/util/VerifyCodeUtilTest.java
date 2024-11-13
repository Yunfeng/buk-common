package cn.buk.util;

import cn.buk.common.util.VerifyCodeUtil;
import org.junit.jupiter.api.Test;

class VerifyCodeUtilTest {

    @Test
    void test_MD5() {
        final String password = "1234567890abcdefghijklmnopqrstuvwxyz";
        String md5Str = VerifyCodeUtil.MD5(password);

        System.out.println(password + ", " + password.length());
        System.out.println(md5Str + ", " + md5Str.length());
    }

    @Test
    void test_MD5_2() {
        final String password = "1234567890";
        String md5Str = VerifyCodeUtil.MD5(password);

        System.out.println(password + ", " + password.length());
        System.out.println(md5Str + ", " + md5Str.length());
    }
}