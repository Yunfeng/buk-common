package cn.buk.util;

import java.util.Random;
import java.util.StringTokenizer;

public class Common {


	public static String addBr(String Content) {
		StringConvert xx = new StringConvert();
		Content = xx.replace(Content, "[img]", "<img src=");
		Content = xx.replace(Content, "[/img]", ">");
		Content = xx.replace(Content, "[a]", "<a target=_blank href=");
		Content = xx.replace(Content, "[/a]", ">链接</a>");
		String makeContent = "";
		StringTokenizer strToken = new StringTokenizer(Content, "\n");
		while (strToken.hasMoreTokens()) {
			if (makeContent.length() > 0)
				makeContent = makeContent + "<br>" + strToken.nextToken();
			else
				makeContent = strToken.nextToken();
		}

		return makeContent;
	}
	
	public static String getRandomCode() {
		Random random = new Random();
		int k = random.nextInt();
		int j = Math.abs(k % 10000000) + 90000000;
		
		String aa = Integer.toString(j);
		return aa;
	}
}

