package com.sp.controller;

import com.sp.bean.DomBean;
import com.sp.common.Constants;
import com.sp.util.DataUtils;
import com.sp.util.JsonMapper;
import com.sp.util.StringUtil;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.Page;

import com.sp.util.JsoupUtils;

@Controller
public class SpiderController {

    private static final Logger log = LoggerFactory.getLogger(SpiderController.class);

    @RequestMapping({"/doSpider"})
    public String doSpider(HttpServletRequest request, HttpServletResponse response) {
        try {

            String url = request.getParameter("url");

            Document doc = JsoupUtils.getDoc(url);
            Page page = new Page(new CrawlDatum(url), null);
            page.setDoc(doc);
            List<DomBean> domBeanList = JsoupUtils.getJsonDate(page);
            List<DomBean> domBeans = new ArrayList<DomBean>();
            Map detailMap = new LinkedHashMap<>();
            for (DomBean domBean : domBeanList) {
                if (StringUtil.isNotEmpty(domBean.getText())) {
                    domBeans.add(domBean);
                    System.out.println(domBean.getKey() + "---" + domBean.getText());
                }
            }
            request.setAttribute("domBeans", domBeans);

            Map<String, Object> obj = DataUtils.formatData(domBeans);
            request.setAttribute("obj", JsonMapper.toJson(obj, true));
            System.out.println(domBeanList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "spider";
    }

    @RequestMapping({"/doSpiderList"})
    public String doSpiderList(HttpServletRequest request, HttpServletResponse response) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }


    @RequestMapping({"/doFrame"})
    public String doFrame(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = request.getParameter("url");
            Document doc = JsoupUtils.getDoc(url);
            String html = doc.html();
            html = html.replace("</head>", Constants.installFile + "</head>");
            response.getWriter().write(html);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
