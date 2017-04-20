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

import com.alibaba.fastjson.JSON;

import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.ram.RamCrawler;

/**
 *
 */
public class DemoBingCrawler extends RamCrawler {

    @Override
    public void visit(Page page, CrawlDatums next) {
        String url = page.url();

//        System.out.println(url);

//        getPageUrls(page);
        getJsonDate(page);

//        System.out.println("visiting:" + url + "\tdepth=" + page.meta("depth"));
        //        Document doc = page.doc();
//
//        String _class=doc.attr("class");
//
//
//
//        Elements divContent = doc.getElementsByClass("li-itemmod");
//
//        for(Element linkStr:divContent){
//            String surl=url+linkStr.getElementsByTag("a").attr("href");
//            System.out.println("surl:"+surl);
//        }
    }

    @Override
    protected void afterParse(Page page, CrawlDatums next) {
        //当前页面的depth为x，则从当前页面解析的后续任务的depth为x+1
        int depth;
        //如果在添加种子时忘记添加depth信息，可以通过这种方式保证程序不出错
        if (page.meta("depth") == null) {
            depth = 1;
        } else {
            depth = Integer.valueOf(page.meta("depth"));
        }
        depth++;
        for (CrawlDatum datum : next) {
            datum.meta("depth", depth + "");
        }
    }

    public static void main(String[] args) throws Exception {
        //http://shanghai.anjuke.com/community/p2/
        //http://shanghai.anjuke.com/community/view/*/
        String url = "http://shanghai.anjuke.com/";
        int depth = 1;
        DemoBingCrawler crawler = new DemoBingCrawler();
        crawler.addSeed(new CrawlDatum(url).meta("depth", "" + depth));
        crawler.start();
    }

    public static List<String> getPageUrls(Page page) {
        try {
            String url = page.url();
            List<String> urlArray = new ArrayList<String>();
            Document doc = page.doc();
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

    public static void getJsonDate(Page page) {
        try {

            Document doc = page.doc();

            List<Node> nodes = doc.childNodes();

            int depth = 1;
            for (Node node : nodes) {
                iteratorNode(node);
            }

//            System.out.println(">>>>"+doc);
//            Elements elements = doc.getAllElements();
//            System.out.println(">>>>"+elements);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> iteratorNode(Node node) {
        Map<String, Object> result = new HashedMap();
        List<Node> nodes = node.childNodes();
        System.out.println(">>" + node.nodeName());
        if (nodes.size() > 0) {
            for (Node snode : nodes) {
                Map<String,Object> res = iteratorNode(snode);
            }
        }
        return result;
    }


}
