package com.wx.util;

import org.apache.poi.ss.formula.functions.T;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.regex.Pattern;


public class StringUtil {
    /**
     * @param str
     * @return 字符串是否为空
     */
    public static boolean isNull(String str) {
        return (str == null);
    }

    /**
     * @param str
     * @return 字符串是否为空字符串
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isInteger(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param str
     * @param b   比较值
     * @return
     */
    public static boolean isNotEqualAndNotEmpty(String str, String b) {
        return StringUtil.isNotEmpty(str) && (!b.equals(str));
    }

    /**
     * uft-8
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getUtf8tring(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes(), "UTF-8");
    }


    public static String convertExceptionToString(Throwable t) {
        final String DEFAULT_LINE_SEPARATOR = "\r\n";
        return convertExceptionToString(t, DEFAULT_LINE_SEPARATOR);
    }

    /**
     * 将异常对象转换
     *
     * @param t
     */
    public static String convertExceptionToString(Throwable t, final String LINE_SEPARATOR) {
        StringBuffer sb = new StringBuffer();
        sb.append(t.toString() + LINE_SEPARATOR);
        StackTraceElement[] traces = t.getStackTrace();
        for (int i = 0; i < traces.length; i++) {
            sb.append("\t" + "at " + traces[i] + LINE_SEPARATOR);
        }
        Throwable cause = t.getCause();
        if (cause != null) {
            sb.append(LINE_SEPARATOR + "Caused By" + LINE_SEPARATOR + LINE_SEPARATOR);
            sb.append(convertExceptionToString(cause, LINE_SEPARATOR));
        }
        return sb.toString();
    }

    public static String getArrToStr(String arr[]) {
        String str = "";
        if (arr.length > 0 && arr != null) {
            for (int i = 0; i < arr.length; i++) {
                str += arr[i];
                if (i + 1 != arr.length) {
                    str += ",";
                }
            }
        }
        return str;
    }

    public static String removeAllSpace(String str) {
        return str.replaceAll("\\s*", "");
    }

    public static void main(String args[]) {
        System.out.println(splitStrToUpper("abc_dsdfs_dfasdf","_"));
    }

    /**
     * bean 属性 拼接 sql
     */
    public static String reflectBeanToInsertSql(T t) {
        String sql = " (";
        Field fields[] = t.getClass().getDeclaredFields();
        String[] name = new String[fields.length];
        Object[] value = new Object[fields.length];
        try {

            Field.setAccessible(fields, true);
            for (int i = 0; i < name.length; i++) {
                name[i] = fields[i].getName();
                value[i] = fields[i].get(t);
                if (!name[i].equals("id")) {
                    sql += " " + name[i] + " ";
                    if (i + 1 != name.length) {
                        sql += " , ";
                    }
                }
            }
            sql += ")";
            System.out.println(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String formatStringA(String str) {
        try {
            if (str.length() > 0) {
                String firstStr = str.substring(0, 1);
                str = firstStr.toUpperCase() + str.substring(1, str.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 分割字符串并且首字母大写,变成驼峰形式
     *
     * @param str
     * @param splitStr
     * @return
     */
    public static String splitStrToUpper(String str, String splitStr) {
        try {
            String[] strArray = str.split(splitStr);
            if (strArray.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (String tmpStr : strArray) {
                    tmpStr = formatStringA(tmpStr);
                    sb.append(tmpStr);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
