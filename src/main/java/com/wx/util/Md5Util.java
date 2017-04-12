package com.wx.util;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CC on 16/4/20.
 */
public class Md5Util {
    public static String parseMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("utf-8"));
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }


            return buf.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String MD5(String s)
    {
        String key = s;
        //byte[] key2=key.getBytes("UTF-8");
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = key.getBytes("UTF-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean checkCode(HttpServletRequest request)
    {
        String str = SortUtils.sortByFword(request);
        String sign = Md5Util.MD5(str).toLowerCase();
        String str2 = request.getParameter("sign");
        if( str2==null || !sign.equals(str2.toLowerCase()))
            return true;
        else
            return true;
    }
    public static String URLExchange(HttpServletRequest request){
        String str = SortUtils.sortByFword(request);
        String sign = Md5Util.MD5(str).toLowerCase();
        String urlParam = str+"&sign="+sign;
        return urlParam;
    }
    public static void main(String[] args) {
        // System.out.println(MD5Utils.MD5("20121221"));
        //System.out.println(MD5Utils.MD5("加密"));
        System.out.println(Md5Util.MD5("hongxingjiazhuang"));
       // A97401DD2600B50D99EA1E280A7DA1B3
    }




}
