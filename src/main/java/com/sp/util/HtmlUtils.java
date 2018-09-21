package com.sp.util;

import org.apache.commons.lang.StringEscapeUtils;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Steve on 15/7/16.
 */
public class HtmlUtils {

    public static String cleanStyle(String html) {
        html = html.replaceAll("</?[^/?(img)][^><]*>", "");
        return html;
    }

    public static String cleanHtmlTag(String html) {

        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);

        Matcher m_html = p_html.matcher(html);

        html = m_html.replaceAll(""); // 过滤html标签

        // 过滤html特殊符号
        html = html.replace("&lt;", "<");
        html = html.replace("&gt;", ">");
        html = html.replace("&amp;", "&");
        html = html.replace("&quot;", "\"");
        html = html.replace("&ensp;", "  ");
        html = html.replace("&emsp;", "  ");
        html = html.replace("&nbsp;", "  ");
        html = html.replace(" ", "");
        html = html.replace("   ", "");
        html = html.replace("\t", "");
        return html;
    }

    public static void main(String[] args) {
        try {
            //http://lvyou.baidu.com/destination/ajax/jingdian?format=ajax&cid=0&playid=0&seasonid=5&surl=shanghai&pn=1&rn=18
            String html = "abA1defghiB1klmn-abA2defghiB2klmn-abA3defghiglmn-abcdefghigklmn-abcdefghigklmn-abcdefghigklmn-abcdefghigklmn-abcdefghigklmn";
//            String reg = "ab([^de]*)(.*?)hi([^k]*)";
            String reg = "A1(.*?)mn";
            System.out.println(getContext(html, "", false));
//            List<Map> list = getContextList(html, reg, false);
//            //开始字符串([^结束字符串]*)
//
//            for (Map map : list) {
//                System.out.println(map);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String getContext(String html, String pattern, boolean decode) {
        String res = "";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(html);
        while (m.find()) {
            res = m.group(1);
            try {
                if (decode) {
                    res = URLDecoder.decode(StringEscapeUtils.unescapeJava(res), "UTF-8");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return res;
    }

    public static List<Map> getContextList(String html, String pattern, boolean decode) {
        List<Map> resultList = new ArrayList();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(html);
        while (m.find()) {
            int gc = m.groupCount();
            Map map = new HashMap();
            int j = 0;
            for (int i = 1; i <= gc; i++) {
//                System.out.println("group " + i + " :" + m.group(i));
                if (i % 2 == 1) {
                    String str = m.group(i);
                    try {
                        if (decode) {
                            str = URLDecoder.decode(StringEscapeUtils.unescapeJava(str), "UTF-8");
                        }
                        map.put(j, str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    j++;
                }
            }
            resultList.add(map);
        }
        return resultList;
    }

}
