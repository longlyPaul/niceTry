package com.common.utils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xupeng
 * Date: 13-12-4
 * Time: 下午10:04
 * To change this template use File | Settings | File Templates.
 */
public class ConvertUtil {
    public static String encryptMD5(String str) throws Exception {
        byte[] data=str.getBytes();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        byte b[] = md5.digest();
        int x;
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < b.length; i++) {
            x = b[i];
            if (x < 0)
                x += 256;
            if (x < 16)
                buf.append("0");
            buf.append(Integer.toHexString(x));
        }
        System.out.println("32位加密后的字符串: " + buf.toString());// 32位的加密
        return  buf.toString();
    }
    public static String format(String formatStr,Date date)
    {
        if(CheckUtil.isEmpty(date))
        {
            return "";
        }

        return new SimpleDateFormat(formatStr).format(date);
    }
    
    public static float percentNum(float num1, float num2){
    	if(num2 == 0){
    		return 0;
    	}
    	BigDecimal bigDecimal1 = new BigDecimal(num1);
    	BigDecimal bigDecimal2 = new BigDecimal(num2);
    	BigDecimal result = bigDecimal1.divide(bigDecimal2,4,BigDecimal.ROUND_HALF_DOWN);
    	return result.floatValue();
    }

}
