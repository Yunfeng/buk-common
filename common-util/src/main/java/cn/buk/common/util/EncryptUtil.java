package cn.buk.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 *
 * @author yfdai
 *  2015-1-12
 */
public class EncryptUtil {

    // MD5加密
    public static String MD5Encoding(String source)
            throws NoSuchAlgorithmException {
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        byte[] input = source.getBytes();
        mdInst.update(input);
        byte[] output = mdInst.digest();

        int i = 0;

        var buf = new StringBuilder("");

        for (int offset = 0; offset < output.length; offset++) {
            i = output[offset];

            if (i < 0) {
                i += 256;
            }

            if (i < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    // BASE64加密
    public static String Base64Encoding(String source)
            throws NoSuchAlgorithmException {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] data = encoder.encode(source.getBytes(UTF_8));
        return new String(data);
    }

    /*
     * unicode编码转中文
     */
    public static String decodeUnicode(final String dataStr) {
        final var buffer = new StringBuilder();

        int index = 0;
        int start = 0;
        String charStr;
        char letter;
        while (dataStr.length() > 6) {
            index = dataStr.indexOf("\\u", start);
            if (index >= 0) {
                if (index > start) {
                    buffer.append(dataStr.subSequence(start, index));
                }

                charStr = dataStr.substring(index + 2, index + 6);
                letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
                buffer.append(new Character(letter).toString());

                start = index + 6;

            } else {
                break;
            }
        }

        if (start < dataStr.length()) {
            buffer.append(dataStr.substring(start));
        }

        return buffer.toString();
    }
}
