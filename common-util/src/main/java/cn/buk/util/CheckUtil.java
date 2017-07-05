/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
	
	//private static final Logger logger = LoggerFactory.getLogger(CheckUtil.class);
	
	public static boolean isEmail(String email) {
		String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);     
        Matcher m = p.matcher(email);     
        //logger.info(m.matches()+"---" + email);
        return m.matches();     
	}

    public static boolean isMobileNo(String mobile){
        //Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Pattern p = Pattern.compile("^(1)\\d{10}$");
        Matcher m = p.matcher(mobile);
        //System.out.println(m.matches()+"---");
        return m.matches();
    }

}
