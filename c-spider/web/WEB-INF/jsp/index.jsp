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
    微信账号支付
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
    wx.config({
                  debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                  appId: '${JSMAP.appid}', // 必填，公众号的唯一标识
                  timestamp: '${JSMAP.timestamp}', // 必填，生成签名的时间戳
                  nonceStr: '${JSMAP.nonceStr}', // 必填，生成签名的随机串
                  signature: '${JSMAP.signature}',// 必填，签名，见附录1
                  jsApiList: ['chooseWXPay','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone']
              }); // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    wx.ready(function()
             {

             });
    wx.error(function(res)
             {

             });


    function ding(){
        var price=document.getElementById("price").value;
        var requestdata = "wxuserid=${param.wxuserid}&key=${param.key}&name="+name+"&age="+age+"&phone="+phone+"&price=0&couponid="+couponid;
        var AjaxURL= "/acty/ajax/newactity.do";
        $.ajax({
                   url: AjaxURL,
                   data: requestdata,
                   success: function (data)
                   {
                       if(data != '0')
                       {

                           var obj = JSON.parse(data);
                           //alert(data);
                           wx.chooseWXPay({
                                              timestamp: obj.timeStamp, //
                                              nonceStr: obj.nonceStr, // 支付签名随机串，不长于 32 位
                                              package: obj.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                                              signType: obj.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                                              paySign: obj.paySign, // 支付签名
                                              success: function (res)
                                              {
                                                  self.location.href='${pageContext.request.contextPath}/rusult.jsp';

                                              },
                                              cancel: function (res)
                                              {
                                                  //$.get("${pageContext.request.contextPath}/ajax/paycancel.do?wxuserid=${param.wxuserid}&key=${param.key}&orderids="+obj.orderid,null);

                                              },
                                              fail: function (res)
                                              {
                                                  //$.get("${pageContext.request.contextPath}/ajax/payfail.do?wxuserid=${param.wxuserid}&key=${param.key}&orderids="+obj.orderid,null);

                                              }
                                          });

                       }
                       else
                       {
                           alert('操作失败，请联系客服！');

                       }
                       allowSubmit = true;
                       $('.confirm_btn').removeAttr("disabled");
                   }


               })


    }



</script>
</html>
