<%--
  Created by IntelliJ IDEA.
  User: ashley_wsm
  Date: 2017/3/14
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>鲁通卡用户分析系统</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=t65FFKL6S3wBE51a06KGMyEm"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<style type="text/css"> @import "css/workspace.css";</style>

<nav class="navbar navbar-fixed-top" role="navigation" id="nav1">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" id="Title1">鲁通卡用户分析系统</a>
        </div>
        <div>
            <form id="form_user">
                <div class="form-group">
                    <input type="date" class="form-control" id="date_start"/>
                    <input type="date" class="form-control" id="date_end">
                </div>
            </form>
            <button type="submit" onclick="submit_button()">提交</button>
        </div>
    </div>

</nav>
<div class="main" id="main"></div>
<script type="text/javascript">
    var maindiv = document.getElementById("main");
    maindiv.style.height = document.body.clientHeight-101+"px";
</script>
<nav class="navbar navbar-fixed-bottom" role="navigation" id="nav2">
    <div>
        <ul class="nav nav-tabs nav-justified">
            <li class="active"><a href="#">所占比例</a></li>
            <li><a href="#">出行路线</a></li>
            <li><a href="#">热门OD</a></li>
        </ul>
    </div>
</nav>

</body>
<script type="text/javascript">
    var data;
    function submit_button() {
        $.ajax({
            type:"POST",
            url:"${ContextPath}/login/workspace/user",
            async:"false",
            dataType:"text",
            data:{
                "date_start":$('#date_start').val(),
                "date_end":$('#date_end').val()
            },
            success:function (result) {
              data = result;
              dealwithMap();
            }
        })
    }
    function dealwithMap() {
        var dom = document.getElementById("main");
        var myChart = echarts.init(dom);
        var app = {};
        option={
            title:{
                text:'鲁通卡用户各项比例',

            },
            legend:{
                data:['用户数目/人','消费次数/次','消费金额/万元']
            },
            radar:{
                indicator:data[0],
            },
            series:[{
                name:'',
                type:'radar',
                data:[
                    {
                        value:data[1],
                        name:'用户数目/人'
                    },{
                        value:data[2],
                        name:'消费次数/次'
                    },{
                        value:data[3],
                        name:'消费金额/万元'
                    }
                ]
            }]
        };;
        if(option && typeof option =="object"){
            myChart.setOption(option,true);
        }
    }

</script>
</html>
