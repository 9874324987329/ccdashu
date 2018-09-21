<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="domBean" items="${domBeans}">
    ${domBean.key}--${domBean.text}<br/>
</c:forEach>

<hr/>
${obj}

