/*
 * Copyright (C) 2015 hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.sp.util;

import com.sp.bean.DomBean;
import com.sp.common.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hfut.dmic.webcollector.model.Page;

/**
 * @author hu
 */
public class JsoupUtils {

    private static final Logger log = LoggerFactory.getLogger(JsoupUtils.class);

    public static Document getDoc(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Chrome") // 设置 User-Agent
                    .cookie("auth", "token") // 设置 cookie
                    .timeout(3000)           // 设置连接超时时间
                    .get();                 // 使用 POST 方法访问 URL
        } catch (Exception e) {
            log.error("e:", e);
        }
        return doc;
    }

    public static void analyzeData(Page page){

        Document doc = page.doc();
        Elements elements = doc.children();




    }




    /**
     * 获取页面URL
     */
    public static List<String> getPageUrls(Document doc, String url) {
        try {
            List<String> urlArray = new ArrayList<String>();
            Elements links = doc.getElementsByTag("a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                if (linkHref.startsWith(url)) {
                    System.out.println(linkHref);
                    urlArray.add(linkHref);
                }
            }
            return urlArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    public static List<DomBean> getJsonDate(Page page) {
        List<DomBean> domBeans = new ArrayList<DomBean>();
        try {
            Document doc = page.doc();
            Elements elements = doc.children();
            for (Element element : elements) {
                domBeans.add(iteratorNode(element, domBeans));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return domBeans;
    }

    public static DomBean iteratorNode(Element element, List<DomBean> domBeans) {
        DomBean domBean = new DomBean();
        int index = element.elementSiblingIndex();
        String tagName = element.tagName();
        String url = element.baseUri();
        String key = getElementKey(element) + index;
        String text = null;
        String link = null;
        String img = null;
        text = element.ownText();
        if (tagName.equals("a")) {
            link = element.attr("href");
            link = link.startsWith("http") ? link : (url + link);
        }
        domBean.setKey(key);
        domBean.setText(text);
        domBean.setType(Constants.type_text);
        if (tagName.equals("img")) {
            img = element.attr("src");
            domBean.setText(img);
            domBean.setType(Constants.type_img);
        }
        domBean.setLink(link);
        Elements elements = element.children();
        if (elements.size() > 0) {
            for (Element _element : elements) {
                domBeans.add(iteratorNode(_element, domBeans));
            }
        }
        return domBean;
    }

    public static String getElementKey(Element element) {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> keyList = new ArrayList<String>();
            while (null != element.parent()) {
                keyList.add(element.tagName());
                element = element.parent();
            }
            if (keyList.size() > 0) {
                for (int i = keyList.size() - 1; i >= 0; i--) {
                    sb.append(keyList.get(i));
                    sb.append("_");
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void formatData(List<DomBean> domBeans) {
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
