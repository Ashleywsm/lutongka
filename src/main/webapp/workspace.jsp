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

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>鲁通卡用户分析系统</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/workspace.js"></script>
<style type="text/css"> @import "css/workspace.css";</style>

<nav class="navbar navbar-default" role="navigation" id="navi">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="workspace.jsp" id="Title1">鲁通卡用户分析系统</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="Title2">省市地区选择&nbsp;&nbsp;<img src="images/right_arrow.png"></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">山东省</a></li>
                        <li><a href="#">河北省</a></li>
                        <li><a href="#">江苏省</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

</nav>
</body>
</html>
