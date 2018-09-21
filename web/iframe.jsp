<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/tags.jsp" %>
<head>
    <title>index</title>
</head>
<body>
<form id="_form" action="${ctx}/c-spider/doSpider" method="post">
    URL:<input type="text" id="url" style="width: 300px"
               value="http://localhost:8080/test_data.jsp"/>
    <button type="button" onclick="loadHtml($('#url').val());">开始</button>
</form>
<div id="res"></div>

<iframe id="_iframe" srcdoc="" width="95%" height="500"></iframe>

</body>
<script type="text/javascript">
    function loadHtml(url) {

        $.ajax({
                   beforeSend: function () {
                       $('#_iframe').attr('srcdoc', '加载中...waiting...');
                       return true;
                   },
                   url: ${ctx}'/c-spider/doFrame?url=' + url,
                   success: function (result) {
                       $('#_iframe').attr('srcdoc', result);
                   }
               })
        ;
    }
    $(function () {

    });
</script>
</html>
