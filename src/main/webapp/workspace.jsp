<%--
  Created by IntelliJ IDEA.
  User: ashley_wsm
  Date: 2017/3/7
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang ="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>鲁通卡用户分析系统</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <%--<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">--%>
</head>

<body>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<%--<script src="js/bootstrap-datetimepicker.min.js"></script>--%>
<%--<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>--%>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=t65FFKL6S3wBE51a06KGMyEm"></script>
<script src="js/echarts.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<style type="text/css"> @import "css/workspace.css";</style>

<div class="container">
    <nav class="navbar navbar-fixed-top" role="navigation" id="nav1">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#" id="Title1">鲁通卡用户分析系统</a>
            </div>
        </div>
    </nav>
    <div >

    </div>
    <nav class="navbar navbar-fixed-bottom" role="navigation" id="nav2">
        <div>
            <ul class="nav nav-tabs nav-justified">
                <li><a href="#">所占比例</a></li>
                <li class="active"><a href="#">出行路线</a></li>
                <li><a href="#">热门OD</a></li>
            </ul>
        </div>
    </nav>
</div>

<%--<nav class="navbar navbar-default" role="navigation" id="navi">--%>

    <%--<div class="container-fluid">--%>
        <%--<div class="navbar-header">--%>
            <%--<a class="navbar-brand" href="${ContextPath}/login/Workspace" id="Title1">鲁通卡用户分析系统</a>--%>
        <%--</div>--%>

    <%--</div>--%>
<%--</nav>--%>


</body>
</html>
