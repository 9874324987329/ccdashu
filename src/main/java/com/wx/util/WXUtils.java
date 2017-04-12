package com.wx.util;


import com.wx.bean.WxToken;
import com.wx.common.Constants;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Mr.xu
 * User:徐强
 * Date:2017/2/24
 * Time:13:32
 * 用途:
 */
@Component
public class WXUtils {

    WxToken wxtoken = new WxToken();

    public static String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);

        StringBuilder sbuilder = new StringBuilder();
        for (String str : strArray) {
            sbuilder.append(str);
        }

        return sbuilder.toString();
    }


    //获取token
    public String getAccessToken() {

        String jsonStr = "";
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Constants.wx_appId + "&secret=" + Constants.wx_appSecret;
            jsonStr = loadUrlData(url, "");
            JSONObject obj = JSONObject.fromObject(jsonStr);
            if (!jsonStr.isEmpty()) {
                if (obj.has("access_token")) {
                    String accesstoken = obj.getString("access_token");
                    return accesstoken;
//                    int expires_in = obj.getInt("expires_in");
//                    wxtoken.setAccessToken(accesstoken);
//                    wxtoken.setExpires_in(expires_in);
//                    wxtoken.setCdate(new Date());
//                    this.getJSTicket();
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }

    //获取openId
    public String getUserOpenid(String wxcode) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + Constants.wx_appId + "&secret=" + wxtoken.getAppsecret()
                + "&code=" + wxcode + "&grant_type=authorization_code";

        System.out.println(url);
        String jsonStr = "";
        try {
            jsonStr = HttpKit.get(url);
            JSONObject obj = JSONObject.fromObject(jsonStr);
            if (obj != null && obj.has("openid")) {
                //为什么要set?
//                if (userinfo_auth) {
//                    wxtoken.setAccessToken(obj.getString("access_token"));
//                }
                return obj.getString("openid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //更新token的方法
    public void getJSTicket() {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                + wxtoken.getAccessToken() + "&type=jsapi";
        String jsonStr = "";
        try {
            jsonStr = HttpKit.get(url);
            if (!StringUtil.isEmpty(jsonStr)) {
                JSONObject obj = JSONObject.fromObject(jsonStr);
                if (obj != null || obj.has("ticket")) {

                    wxtoken.setJsTicket(obj.getString("ticket"));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //往页面回调的方法
    public WxToken getPaySign(String prepayid) {
//		timestamp: 0, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
//	    nonceStr: '', // 支付签名随机串，不长于 32 位
//	    package: '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
//	    signType: '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
//	    paySign: '', // 支付签名
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1 = "appId=" + Constants.wx_appId + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepayid + "&signType=MD5" +
                "&timeStamp=" + timestamp + "&key=" + Constants.wx_key;

        //appId="+appId + "&nonceStr="+noncestr + "&package=prepay_id=wx2015041419450958e073ca4a0071648005&signType=MD5&timeStamp=" + timestamp + "&key="+key
        //appId=wx0116b7b150f1aa21&nonceStr=d2056256-2f96-4e8c-b714-8ecfda4df881&package=prepay_id=null&signType=MD5&timeStamp=1441438549&key=shzongshi2015kejiyouxiangongsiok
        System.out.println(string1);
        String sign = Md5Util.MD5(string1).toUpperCase();
        WxToken wxToken = new WxToken();
        wxToken.setAppID(Constants.wx_appId);
        wxToken.setNonceStr(nonce_str);
        wxToken.setPackageId("prepay_id=" + prepayid);
        wxToken.setSignType("MD5");
        wxToken.setTimeStamp(timestamp);
        wxToken.setPaySign(sign);
        return wxToken;
    }

    /**
     * 微信统一下单方法
     */
    public static String getPrepayid(String sip, String body, String orderid, Long price, String openID) {
        String notifyurl = Constants.noty_url;
        String appId = Constants.wx_appId;
        String nonceStr = WXUtils.create_nonce_str();
        String stimestamp = WXUtils.create_timestamp();
        price = price * 100;
        orderid = orderid + stimestamp;
        String url = "" +
                "appid=" + Constants.wx_appId + "" +
                "&body=" + body +
                "&mch_id=" + Constants.wx_mid + "" +
                "&nonce_str=" + nonceStr + "" +
                "&notify_url=" + notifyurl +
                "&openid=" + openID + "" +
                "&out_trade_no=" + orderid + "" +
                "&spbill_create_ip=" + sip + "" +
                "&total_fee=" + price +
                "&trade_type=JSAPI" +
                "&key=" + Constants.wx_key;

        String md5 = Md5Util.MD5(url).toUpperCase();
        String xml = "<xml>" +
                "<appid>" + Constants.wx_appId + "</appid>   " +
                "<body>" + body + "</body>" +
                "<mch_id>" + Constants.wx_mid + "</mch_id>   " +
                "<nonce_str>" + nonceStr + "</nonce_str>" +
                "<notify_url>" + notifyurl + "</notify_url>   " +
                "<openid>" + openID + "</openid>" +
                "<out_trade_no>" + orderid + "</out_trade_no>   " +
                "<spbill_create_ip>" + sip + "</spbill_create_ip>   " +
                "<total_fee>" + price + "</total_fee>" +
                "<trade_type>JSAPI</trade_type>" +
                "<sign>" + md5 + "</sign>" +
                "</xml>";

        try {
            System.out.println(xml);
            String res = HttpKit.post("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
            System.out.println(res);
            Map<String, String> map = XMLUtils.doXMLParse(res);
            String prepayid = map.get("prepay_id");
            return prepayid;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String create_nonce_str() {
        return UUID.randomUUID().toString().substring(0, 30);
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {

        String nonceStr = WXUtils.create_nonce_str();
        int price = 1;
        String body = "XQtest";
        String appId = "wxe34d871de7c26207";
        String mid = "1441614102";
        String openid = "ofOVUswj_Y7Jap4Iriz78ju7aQXw";
        String orderid = "12";
        String sip = "192.168.124.1";
        String key = "hongxingjiazhuang";
        String notifyurl = "http://api.jiazhuang.uat1.rs.com/c/remodelingBooking/notify";
        String url = "appid=" + appId +
                "&body=" + body +
                "&mch_id=" + mid +
                "&nonce_str=" + nonceStr +
                "&notify_url=" + notifyurl +
                "&openid=" + openid +
                "&out_trade_no=" + orderid +
                "&spbill_create_ip=" + sip +
                "&total_fee=" + price +
                "&trade_type=JSAPI" +
                "&key=" + key;
        System.out.println(url + "\n");
        String md5 = Md5Util.MD5(url).toUpperCase();
        System.out.println(md5);
        String xml = "<xml>" +
                "<appid>" + appId + "</appid>" +
                "<body>" + body + "</body>" +
                "<mch_id>" + mid + "</mch_id>   " +
                "<nonce_str>" + nonceStr + "</nonce_str>" +
                "<notify_url>" + notifyurl + "</notify_url>   " +
                "<openid>" + openid + "</openid>" +
                "<out_trade_no>" + orderid + "</out_trade_no>   " +
                "<spbill_create_ip>" + sip + "</spbill_create_ip>   " +
                "<total_fee>" + price + "</total_fee>" +
                "<trade_type>JSAPI</trade_type>" +
                "<sign>" + md5 + "</sign>" +
                "</xml>";

        try {
            String res = HttpKit.post("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
            System.out.println(res);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private String loadUrlData(String url, String param) {
        String result = "";

        if (StringUtil.isEmpty(param)) {
            while (StringUtil.isEmpty(result)) {
                try {
                    System.out.println("-loadUrlData---");
                    result = HttpKit.get(url);
                    System.out.println("--" + result + "--");
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        } else {
            while (StringUtil.isEmpty(result)) {
                try {
                    System.out.println("-loadUrlData---");
                    result = HttpKit.post(url, param);
                    System.out.println("--" + result + "--");
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }


        System.out.println("WXUtil [" + url + "," + param + "]");
        return result;

    }
}
