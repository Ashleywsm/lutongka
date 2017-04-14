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
<style type="text/css"> @import "css/workspace.css";</style>

<nav class="navbar navbar-fixed-top" role="navigation" id="nav1">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" id="Title1">鲁通卡用户分析系统</a>
        </div>
        <div>
            <form id="form_od">
                <div class="form-group">
                    <input type="date" class="form-control" id="date"/>
                    <input type="text" class="form-control" id="province"/>
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
            <li><a href="">所占比例</a></li>
            <li><a href="">出行路线</a></li>
            <li class="active"><a href="">热门OD</a></li>
        </ul>
    </div>
</nav>

</body>
<script type="text/javascript">
    var geodata;
    var od ;
//按钮操作
    function submit_button(){
        $.ajax({
            type : "POST",  //提交方式
            url : "${ContextPath}/login/workspace/odline/od",//路径
            async:"false",
            dataType:"text",
            data : {
                "date":$('#date').val(),
                "province":$('#province').val()
            },//数据，这里使用的是Json格式进行传输
            success : function(result) {//返回数据根据结果进行相应的处理
                od=eval('('+result+')');
                dealTheMap();
            }
        });
        <%--$.ajax({--%>
            <%--type : "POST",  //提交方式--%>
            <%--url : "${ContextPath}/login/workspace/odline/lnglat",//路径--%>
            <%--async:"false",--%>
            <%--dataType:"text",--%>
            <%--data : {--%>
                <%--"province":$('#province').val()--%>
            <%--},//数据，这里使用的是Json格式进行传输--%>
            <%--success : function(result) {//返回数据根据结果进行相应的处理--%>
                <%--geodata=result;--%>
            <%--}--%>
        <%--});--%>
    }
//地图加载
    function dealTheMap(){

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
                    color: ['gold', 'aqua', 'lime'],
                    title: {
                        text: '热门OD',
                        x: 'center',
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: function(v) {
                            return v[1].replace(':', ' > ');
                        }
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data: ['开始站', '结束站'],
                        selectedMode: 'single',
                        selected: {
                            '结束站': false,
                        },
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        x: 'right',
                        y: 'center',
                        feature: {
                            mark: { show: true },
                            dataView: { show: true, readOnly: false },
                            restore: { show: true },
                            saveAsImage: { show: true }
                        }
                    },
                    dataRange: {
                        min: 0,
                        max: 3000,
                        x: 'right',
                        calculable: true,
                        color: ['#12d6f2','#00a9e9','#2e30de','#601986'],
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    series: [{
                        name: '开始站',
                        type: 'map',
                        mapType: 'none',
                        center: [117.04042, 36.73925],
                        data: [],
                        geoCoord: geodata,

                        markLine: {
                            smooth: true,
                            effect: {
                                show: true,
                                scaleSize: 1,
                                period: 30,
                                color: '#fff',
                                shadowBlur: 10
                            },
                            itemStyle: {
                                normal: {
                                    borderWidth: 1,
                                    lineStyle: {
                                        type: 'solid',
                                        shadowBlur: 10
                                    }
                                }
                            },
                            data: od[0]
                        },

                    }, {
                        name: '结束站',
                        type: 'map',
                        mapType: 'none',
                        data: [],
                        markLine: {
                            smooth: true,
                            effect: {
                                show: true,
                                scaleSize: 1,
                                period: 30,
                                color: '#fff',
                                shadowBlur: 10
                            },
                            itemStyle: {
                                normal: {
                                    borderWidth: 1,
                                    lineStyle: {
                                        type: 'solid',
                                        shadowBlur: 10
                                    }
                                }
                            },
                            data: od[1]
                        },
                    }

                    ]

                };




                var myChart = BMapExt.initECharts(container);
                BMapExt.setOption(option);
                var ecConfig = require('echarts/config');
            });
    };



</script>
</html>

