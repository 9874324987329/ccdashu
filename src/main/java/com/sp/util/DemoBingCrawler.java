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
        JsoupUtils.getJsonDate(page);

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
        String url = "http://localhost:8080/index.jsp";
//        String url = "http://shanghai.anjuke.com/community/p2/";
        int depth = 1;
        DemoBingCrawler crawler = new DemoBingCrawler();
        crawler.addSeed(new CrawlDatum(url).meta("depth", "" + depth));
        crawler.start();
    }


}
