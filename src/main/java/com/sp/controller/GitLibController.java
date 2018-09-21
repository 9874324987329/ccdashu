package com.sp.controller;

import com.sp.bean.DomBean;
import com.sp.common.Constants;
import com.sp.util.DataUtils;
import com.sp.util.JsonMapper;
import com.sp.util.JsoupUtils;
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

@Controller
public class GitLibController {

    private static final Logger log = LoggerFactory.getLogger(GitLibController.class);

    @RequestMapping({"/glibSpider"})
    public String glibSpider(HttpServletRequest request, HttpServletResponse response) {
        try {

            System.out.println("....");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "spider";
    }

}
