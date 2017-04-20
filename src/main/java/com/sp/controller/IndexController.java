package com.sp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping({"/index"})
    public String auth(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("...");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
