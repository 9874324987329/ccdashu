package com.wx.common;

import com.wx.util.PropertiesUtil;

public class Constants {

    //微信appid
    public static final String wx_appId = PropertiesUtil.getProperty("config/common.properties").get("WX_AppID");
    //微信私钥
    public static final String wx_appSecret = PropertiesUtil.getProperty("config/common.properties").get("WX_AppSecret");
    //微信商户号
    public static final String wx_mid=PropertiesUtil.getProperty("config/common.properties").get("WX_Mid");
    //微信key
    public static final String wx_key=PropertiesUtil.getProperty("config/common.properties").get("WX_Key");
    //微信回调地址
    public static final String noty_url= PropertiesUtil.getProperty("config/common.properties").get("noty_url");
    //微信WX_TOKEN
    public static final String user_token= PropertiesUtil.getProperty("config/common.properties").get("WX_TOKEN");

    //微信用户openid
    public static final String WXOPENID = "WXOPENID_20170302";

}
