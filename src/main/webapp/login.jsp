<%--
  Created by IntelliJ IDEA.
  User: ashley_wsm
  Date: 2017/3/7
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<head>
    <meta charset="UTF-8">
    <title>鲁通卡用户分析系统</title>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>

<div id="login">
    <h1>ETC鲁通卡</h1>
    <form>
        <input type="text" required="required" placeholder="用户名" id="username"></input>
        <input type="password" required="required" placeholder="密码" id="password"></input>
        <button class="but" type="submit" onclick="login_button()">登录</button>
        <%--<button class="but" type="submit" onclick="test()">测试</button>--%>
    </form>
</div>

<script src="${ContextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
    function login_button() {

        $.post("${ContextPath}/login/login_action",
            {"username":$('#username').val(),
                "password":$('#password').val()},
            function(data){
                if(data=="success"){

                    window.location.href="${ContextPath}/login/toWorkspace";
                }else {

                    console.log("error");
                }
            }
        );
    }

    <%--function test() {--%>
        <%--var username = $('#username').val();--%>
        <%--var password = $('#password').val();--%>
        <%--alert(username);--%>
        <%--alert(password);--%>
        <%--$.post("${ContextPath}/login/controller",--%>
            <%--{"username":username, "password":password},--%>
            <%--function(data){--%>
                <%--console.log(data);--%>
            <%--},--%>
            <%--'text'--%>
        <%--);--%>
    <%--}--%>
</script>
