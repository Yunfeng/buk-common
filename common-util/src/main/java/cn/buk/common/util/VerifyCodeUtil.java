package cn.buk.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Random;

public class VerifyCodeUtil {
	
	/**
	 * 根据email地址和当前时间随机生成验证码
	 */
	public static  String getEmailVerifyCode(String email) {

		Random random = new Random();
		int k = random.nextInt();
		int j = Math.abs(k % 100000) + 900000;
		
		String oriCode = Calendar.getInstance().toString();
		oriCode += j;
		oriCode += email.toLowerCase();
		
		oriCode = MD5(oriCode);

		return oriCode;
	}

	public static  String getMobileVerifyCode(String mobile) {
		Random random = new Random();
		int k = random.nextInt();
		int j = Math.abs(k % 100000) + 900000;

		return Integer.toString(j);
	}

	/**
	 * MD5加密
	 */
	public static  String MD5(String plainText) {
		String str = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte[] b = md.digest();

			int i;

			StringBuilder buf = new StringBuilder();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
          i += 256;
        }
				if (i < 16) {
          buf.append("0");
        }
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
			// System.out.println("result: " + buf.toString());// 32位的加密
			// System.out.println("result: " + buf.toString().substring(8,
			// 24));// 16位的加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
		return str;
	}
}
