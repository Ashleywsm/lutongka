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
<script src="${ContextPath}/js/jquery.form.min.js"></script>
<%--<script type="text/javascript" src="js/echarts.js"></script>--%>
<style type="text/css"> @import "css/user.css";</style>

<nav class="navbar navbar-fixed-top" role="navigation" id="nav1">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" id="Title1">鲁通卡用户分析系统</a>
        </div>
            <div id = "date_user" >
                <label class="myLabel">起始日期：</label>
                    <input type="date" style="height: 33px;" id="date_start"/>
                <label class="myLabel">结束日期：</label>
                    <input type="date" style="height: 33px;" id="date_end">
                    <button type="submit" id = "submit_btn" class="btn btn-sm" onclick="submit_button()">提交</button>
            </div>


    </div>

</nav>
<div class="main" id="main">
    <div style="float:right">

        <form  method='post' action='${ContextPath}/login/workspace/user/upload'  enctype="multipart/form-data" id='fileForm'>
            <input style="float:left" name='file' id='file' type="file" class="user_upload">
            <button style="float:right" id='submitBtn' type="button" class="btn btn-sm btn-success user_upload" onclick='save()' >上传
            </button>
        </form>
    </div>
</div>
<script type="text/javascript">
    var maindiv = document.getElementById("main");
    maindiv.style.height = document.body.clientHeight-101+"px";
</script>
<nav class="navbar navbar-fixed-bottom" role="navigation" id="nav2">
    <div>
        <ul class="nav nav-tabs nav-justified">
            <li class="active"><a href="user.jsp">用户情况</a></li>
            <li><a href="workspace.jsp">出行路线</a></li>
            <li><a href="od.jsp">热门OD</a></li>
        </ul>
    </div>
</nav>

</body>
<script type="text/javascript">
function save(){
    $("#fileForm").ajaxSubmit({success:function(data){
        alert("保存成功");
    }});
}


    var data;
    function submit_button() {
        $.ajax({
            type:"POST",
            url:"${ContextPath}/login/workspace/user/two",
            async:"false",
            dataType:"text",
            data:{
                "date_start":$('#date_start').val(),
                "date_end":$('#date_end').val()
            },
            success:function (result) {
//                alert("kaishi");
//              data = eval('('+result+')');
              dealwithMap(result);
            }
        });
    };
    function dealwithMap(result) {
        var data = result.split("@");
        data0 = eval('('+data[0]+')');
        data1 = eval('('+data[1]+')');
        data2 = eval('('+data[2]+')');
        data3 = eval('('+data[3]+')');
        var dom = document.getElementById("main");
        var myChart = echarts.init(dom);
        var app = {};
        option={
            backgroundColor:'#002b43',
            title:{
                text:'鲁通卡用户各项比例',
            },
            legend:{
                data:['用户数目/人','消费次数/次','消费金额/千元'],
                textStyle:{
                    color:'#fff'
                }
            },
            radar:{
                shape:'circle',
                indicator:data0,
            },
            series:[{
                name:'用户分布',
                type:'radar',
                data:[
                    {
                        value:data1,
                        name:'用户数目/人'
                    },{
                        value:data2,
                        name:'消费次数/次'
                    },{
                        value:data3,
                        name:'消费金额/千元'
                    }
                ]
            }]
        };
        if(option && typeof option =="object"){
            myChart.setOption(option,true);
        }
    }

</script>
</html>
