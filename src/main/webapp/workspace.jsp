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
</head>

<body>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=t65FFKL6S3wBE51a06KGMyEm"></script>
<script src="js/echarts.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src = "json/jiedian.json"></script>
<style type="text/css"> @import "css/workspace.css";</style>

    <nav class="navbar navbar-fixed-top" role="navigation" id="nav1">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#" id="Title1">鲁通卡用户分析系统</a>
            </div>
            <div id="date_user">
                <%--<form id="myform">--%>
                    <%--<div >--%>
                    <label class="myLabel">日期：</label>
                        <input type="date" style="height: 33px;" id="date"/>
                    <label class="myLabel">省份：</label>
                        <input type="text" style="height: 33px;" id="province" />
                    <%--</div>--%>
                <%--</form>--%>
                <button id="zkbzkbzkb" class="btn btn-sm" type = "button" onclick="submit_button()">提交</button>
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
                <li><a href="user.jsp">所占比例</a></li>
                <li class="active"><a href="workspace.jsp">出行路线</a></li>
                <li><a href="od.jsp">热门OD</a></li>
            </ul>
        </div>
    </nav>

</body>
<script type="text/javascript">
    var odmax0;
    function submit_button(){
        console.log(zones);
        $.ajax({
            type : "POST",  //提交方式
            url : "${ContextPath}/login/workspace/highwayline/two",//路径
            async:"false",
            dataType:"text",
            data : {
                "date":$('#date').val(),
                "province":$('#province').val()
            },//数据，这里使用的是Json格式进行传输
            success : function(result) {//返回数据根据结果进行相应的处理
//                alert("END");
                odmax0=eval('('+result+')');
//                zones = eval('('+result+')');
                dealTheMap();
            }
        });
    };
    function dealTheMap(){
        var lines0 = odmax0.map(function(o){
            return o.map(function(p){
                return [{ name : p.o},{ name : p.d , value : p.flow}];
            });
        });

        var geodata = {};
        zones.forEach(function(o){
            _name = o.name;
            _centerX = o.centerX;
            _centerY = o.centerY;
            geodata[_name] = [_centerX,_centerY];
        });
        var datearr = [];
        for (var i=0;i < 24; i++){
            if(i<10){
                datearr.push("2016-04-19 0"+i+":00:00");
            }else{
                datearr.push("2016-04-19 "+i+":00:00");
            }

        }

        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            },
            packages: [
                {
                    name: 'BMap',
                    location: 'js',
                    main: 'main'
                }
            ]
        });
        // 使用
        require(
            [
                'echarts',
                'BMap',
                'echarts/chart/map' //
            ],

            function (echarts, BMapExtension) {
                // 基于准备好的dom，初始化echarts图表
                // 初始化地图
                var mapcon = document.getElementById("main");
                var BMapExt = new BMapExtension(mapcon, BMap, echarts,{
                    enableMapClick: false
                });
                var map = BMapExt.getMap();
                var container = BMapExt.getEchartsContainer();

                var startPoint = {x:118.881323,y:36.670064};
                var point = new BMap.Point(startPoint.x, startPoint.y);
                map.centerAndZoom(point, 9);
                map.enableScrollWheelZoom(true);
                // 地图自定义样式
                map.setMapStyle({
                    styleJson: [
                        {
                            "featureType": "water",
                            "elementType": "all",
                            "stylers": {
                                "color": "#404a59"
                            }
                        },
                        {
                            "featureType": "land",
                            "elementType": "all",
                            "stylers": {
                                "color": "#212121"
                            }
                        },
                        {
                            "featureType": "boundary",
                            "elementType": "geometry",
                            "stylers": {
                                "color": "#212121"
                            }
                        },
                        {
                            "featureType": "railway",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "highway",
                            "elementType": "geometry",
                            "stylers": {
                                "color": "#404a59"
                            }
                        },
                        {
                            "featureType": "highway",
                            "elementType": "geometry.fill",
                            "stylers": {
                                "color": "#404a59",
                                "lightness": 1
                            }
                        },
                        {
                            "featureType": "highway",
                            "elementType": "labels",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "arterial",
                            "elementType": "geometry",
                            "stylers": {
                                "color": "#404a59"
                            }
                        },
                        {
                            "featureType": "arterial",
                            "elementType": "geometry.fill",
                            "stylers": {
                                "color": "#404a59"
                            }
                        },
                        {
                            "featureType": "poi",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "green",
                            "elementType": "all",
                            "stylers": {
                                "color": "#404a59",
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "subway",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "manmade",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "local",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "arterial",
                            "elementType": "labels",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "boundary",
                            "elementType": "geometry.fill",
                            "stylers": {
                                "color": "#404a59"
                            }
                        },
                        {
                            "featureType": "building",
                            "elementType": "all",
                            "stylers": {
                                "color": "#404a59"
                            }
                        },
                        {
                            "featureType": "label",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        }
                    ]
                });
                option = {
                    timeline:{
                        data:datearr,
                        label : {
                            formatter : function(s) {
                                return s.slice(11, 16);
                            },
                            textStyle : {
                                color: '#fff'
                            }
                        },
                        autoPlay : false,
                        playInterval : 3000
                    },
                    options:[
                        {
                            title:{
                                text: '高速车流量',
                                x:'center',
                                textStyle : {
                                    "color": "#fff",
                                    "fontSize": 30,
                                    "fontFamily": "黑体"
                                },
                            },
                            tooltip : {
                                trigger: 'item',
                                formatter: '{b}'
                            },
                            toolbox: {
                                show : true,
                                orient : 'vertical',
                                x: 'right',
                                y: 'center',
                                feature : {
                                    mark : {show: true},
                                    dataView : {show: true, readOnly: false},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                            dataRange: {
                                min : 1,
                                max : 10,
                                calculable : true,
                                x: 'right',
                                y: document.body.clientHeight-250,
                                color: ['#601986','#2e30de','#00a9e9','#12d6f2'],
                                textStyle:{
                                    color:'#fff'
                                }
                            },
                            series : [
                                {
                                    name: '当天',
                                    type: 'map',
                                    mapType: 'none',
                                    itemStyle:{
                                        normal:{
                                            borderColor:'rgba(100,149,237,1)',
                                            borderWidth:0.5,
                                            areaStyle:{
                                                color: '#1b1b1b'
                                            }
                                        }
                                    },
                                    data:[],
                                    markLine : {
                                        smooth:true,
                                        symbol: ['circle', 'circle'],
                                        symbolSize : 1,
                                        effect : {
                                            show: true,
                                            scaleSize: 1,
                                            period: 30,
                                            color: '#fff',
                                            shadowBlur: 10
                                        },
                                        itemStyle : {
                                            normal: {
                                                borderWidth:1,
                                                lineStyle: {
                                                    type: 'solid',
                                                    shadowBlur: 5
                                                },
                                                label:{
                                                    show:false
                                                }
                                            }
                                        },
                                        data : lines0[0]

                                    },
                                    geoCoord: geodata
                                }
                            ]
                        }]
                };

                for (var i=1;i<23;i++){
                    option.options.push({series:[
                        {
                            markLine:{data:lines0[i]}
                        }
                    ]});
                }



                var myChart = BMapExt.initECharts(container);
                BMapExt.setOption(option);
                var ecConfig = require('echarts/config');

            });
    };



</script>
</html>
