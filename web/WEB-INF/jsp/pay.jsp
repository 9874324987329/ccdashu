<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/tags.jsp" %>
<html>
<head>
    <title>wx-pay</title>
</head>
<body>
<div class="container-fluid">
    微信支付中....
</div>
<div class="buy1">
    <div class="con">
        <input type="hidden" id="price" value="1">
        <div class="price">
            总价 ￥<span>1.00</span>
        </div>
        <div class="ding" onclick="ding()">立即支付</div>
        <div class="clear"></div>
    </div>
</div>
</body>
<script>
    function onBridgeReady() {
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                "appId":"wx2421b1c4370ec43b",     //公众号名称，由商户传入
                "timeStamp":" 1395712654",         //时间戳，自1970年以来的秒数
                "nonceStr":"e61463f8efa94090b1f366cccfbbb444", //随机串
                "package":"prepay_id=u802345jgfjsdfgsdg888",
                "signType":"MD5",         //微信签名方式：
                "paySign":"70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名
    },
        function (res) {
            if (res.err_msg == "get_brand_wcpay_request：ok") {
            }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        }

    )
        ;
    }
    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    } else {
        onBridgeReady();
    }
</script>
</html>
