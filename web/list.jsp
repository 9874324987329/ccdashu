<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/tags.jsp" %>
<head>
    <title>list</title>
</head>
<body>
<form id="_form" action="${ctx}/c-spider/doSpider" method="post">
    URL:<input type="text" name="url" style="width: 300px" value="http://localhost:8080/test_data.jsp"/>
    <button type="button" onclick="$('#_form').submit();">抓取列表</button>
</form>
<div id="res"></div>

</body>
<script>
    $(function () {
        $('#_form').ajaxForm({
                                 beforeSubmit: function () {
                                     $('#res').html("加载中...waiting...");
                                     return true;
                                 },
                                 success: function (data) {
                                     $('#res').html(data);
                                 }
                             });

    });
</script>
</html>
