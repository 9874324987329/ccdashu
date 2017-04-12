package com.wx.controller;

import com.wx.common.Constants;
import com.wx.util.Decript;
import com.wx.util.WXUtils;
import com.wx.util.XMLUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    //授权方法
    @RequestMapping({"/auth"})
    public String auth(HttpServletRequest request, HttpServletResponse response) {
        try {
//            System.out.print("——开始授权验证——");
//            String signature = request.getParameter("signature");
//            String timestamp = request.getParameter("timestamp");
//            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
//            ArrayList<String> array = new ArrayList<String>();
//            array.add(signature);
//            array.add(timestamp);
//            array.add(nonce);
//            //排序
//            String sortString = WXUtils.sort(Constants.user_token, timestamp, nonce);
//            //加密
//            String mytoken = Decript.SHA1(sortString);
//            //校验签名
//            if (mytoken != null && mytoken != "" && mytoken.equals(signature)) {
                System.out.println("成功授权地址");
                response.getWriter().println(echostr); //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
//            } else {
//                log.info("授权失败-签名错误");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //微信支付回调接口
    @RequestMapping("/notify")
    @ResponseBody
    public String payNotify(HttpServletRequest request) {
        try {
            StringBuffer jb = new StringBuffer();
            String line = "";
            java.io.BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line + "\n");
            System.out.println("+++notify+++++\n" + jb.toString()
                    + "++++++++++");

            if (jb.toString().length() > 0) {//正式环境回调接口

                Map<String, String> map = XMLUtils.doXMLParse(jb.toString());
                String appid = map.get("appid");
                String mechid = map.get("mch_id");
                String resultcode = map.get("result_code");
                if ("SUCCESS".equals(resultcode)) {

                    String[] temps = map.get("out_trade_no").split("_");
                    if (temps.length < 2) {
                        return null;
                    }
                    String orderid = temps[0];
                    System.out.println("-------" + appid + "-----------");
                    System.out.println("-------" + mechid + "-----------");
                    System.out.println("-------" + orderid + "-----------");

                    try {
                        String fee = map.get("cash_fee");
                        int[] orderids = new int[temps.length - 1];
                        for (int i = 0; i < temps.length - 1; i++) {
                            orderids[i] = Integer.parseInt(temps[i]);

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "SUCCESS";// 回调结束返回标志
                }
                return null;
            } else {
                //内网回调接口处理

                try {
                    String orderid = request.getParameter("orderid");
                    String[] s = orderid.split("_");
                    int[] ids = new int[s.length];
                    for (int i = 0; i < s.length; i++) {
                        ids[i] = Integer.parseInt(s[i]);
                    }
                    System.out.print(ids);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //回调获取code
    @RequestMapping("/getcode")
    @ResponseBody
    public String getCode(HttpServletRequest request) {
        try {

            //获取code
            String code = request.getParameter("code");

            //根据Code获取openid
            WXUtils wxUtils = new WXUtils();
            String opendID = wxUtils.getUserOpenid(code);
            if(null!=opendID){
                request.getSession().setAttribute(Constants.WXOPENID,opendID);
            }else{
                //如果获取失败则相应处理,跳转首页授权或者再次获取
            }

            StringBuffer paramsStr = new StringBuffer();
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues.length == 1) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() != 0) {
                        paramsStr.append(paramName).append("=").append(paramValue).append(",");
                    }
                }
            }
            System.out.println(">>>>" + paramsStr.toString());

        } catch (Exception e) {

        }
        return null;
    }

}
