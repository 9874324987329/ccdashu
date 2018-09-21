package com.sp.util;

import org.apache.commons.collections.map.HashedMap;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 土巴兔装修公司
 */
public class To8toCompany {


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println("+++++++++++++++++++++++" + i + "++++++++++++++++++++++++");
            try {
                String url = "http://sh.to8to.com/company/?order=all&page=" + i;
                Map<String, String> paramMap = new HashedMap();
                String html = HttpClientUtil.post_form(paramMap, url);
                //<a href="http://sh.to8to.com/zs/274279/" target="_blank" class="zgscl_name" >
                List<Map> urlList = HtmlUtils.getContextList(html, "<a href=\"(.*?)\" target=\"_blank\" class=\"zgscl_name\" >", true);
                for (Map map : urlList) {
                    try {
                        String cUrl = map.get(0) + "";
                        //<p class="cmt_content">设计师与项目经理沟通还要再多一点就好了，其它没什么问题</p>
                        String chtml = HttpClientUtil.post_form(paramMap, cUrl);
                        List<Map> contextList = HtmlUtils.getContextList(chtml, "<p class=\"cmt_content\">(.*?)</p>", true);
                        for (Map _map : contextList) {
                            try {
                                System.out.println(_map.get(0) + "############");
                                String content = _map.get(0) + "############";
                                FileUtils.writeIntoFile(new File("/Users/ccdashu/Downloads/comments1.txt"), content, true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
