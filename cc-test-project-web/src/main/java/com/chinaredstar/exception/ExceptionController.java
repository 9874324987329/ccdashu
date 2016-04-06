package com.chinaredstar.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ccdashu on 16/3/28.
 */
@Controller
public class ExceptionController {

    @RequestMapping(value = "/error_415", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String error() throws Exception {
//        System.out.println("......"+code);
        return "{\"msg\":\"访问路径不存在\",\"code\":\"http_404\"}";
    }

}
