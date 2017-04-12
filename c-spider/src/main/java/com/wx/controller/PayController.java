package com.wx.controller;

import com.wx.common.Constants;
import com.wx.util.Md5Util;
import com.wx.util.WXUtils;

import org.apache.bcel.classfile.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("pay")
public class PayController {

    private static final Logger log = LoggerFactory.getLogger(PayController.class);

    @RequestMapping({"dopay"})
    public String dopay(HttpServletRequest request, HttpServletResponse response) {
        try {
            String sip = request.getRemoteAddr();
            long timeStamp = new Date().getTime();
            String nonceStr = "kajsdlfj1231kjdoiusdjf";
            //设置APPID
            request.setAttribute("appId", Constants.wx_appId);
            //设置时间搓
            request.setAttribute("timeStamp", timeStamp);
            //nonceStr随机串
            request.setAttribute("nonceStr", nonceStr);// TODO: 17/3/2 这里需要生成不可预测的随机串

            //package 统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
            //生成系统订单
            String body = "局装-用户升级VIP服务";
            String orderId = "20170220abc";
            long price = 100;
            String openId = (String) request.getSession().getAttribute(Constants.WXOPENID);
            String prepayid = WXUtils.getPrepayid(sip, body, orderId, price, openId);//未通
            String _package = "prepay_id=" + prepayid;
            request.setAttribute("package", _package);

            //paySign 签名，详见
            String string1 = "appId=" + Constants.wx_appId + "&timeStamp=" + timeStamp + "&nonceStr=" + nonceStr + "&signType=MD5" +
                    "&package=" + _package;
            System.out.println(string1);
            String sign = Md5Util.MD5(string1).toUpperCase();
            request.setAttribute("paySign", sign);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pay";
    }

}
