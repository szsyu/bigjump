<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Current Activity Image</title>
</head>
<body>
<img style="position: absolute;top: 0px;left: 0px;" src="../Task/viewProcDefImage?deploymentId=${deploymentId}&imageName=${imageName}">
<!-- 2.根据当前活动的坐标，动态绘制DIV -->
<div style="position: absolute;border:1px solid #ff0000;top:${y}px;left:${x}px;width: ${width}px;height:${height}px;"></div>
</body>
</html>
