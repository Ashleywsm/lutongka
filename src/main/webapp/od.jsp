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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/bootstrap-theme.min.css" rel="stylesheet">

    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=t65FFKL6S3wBE51a06KGMyEm"></script>
    <script src="js/echarts.js"></script>
    <script src="js/station.js"></script>
    <title>鲁通卡用户分析系统</title>
</head>

<body>
<script type="text/javascript" src="json/station.json"></script>
<style type="text/css"> @import "css/od.css";</style>

<nav class="navbar navbar-fixed-top" role="navigation" id="nav1">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" id="Title1">鲁通卡用户分析系统</a>
        </div>
        <div id="date_user">
            <%--<div>--%><label class="myLabel">日期：</label>
                <input type="date" style="height: 33px;" id="date"/>
                <label class="myLabel">省份：</label>
                <input type="text" style="height: 33px;" id="province"/>
            <%--</div>--%>
            <button type="submit" class="btn btn-sm" onclick="submit_button()">提交</button>
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
            <li><a href="user.jsp">用户情况</a></li>
            <li><a href="workspace.jsp">出行路线</a></li>
            <li class="active"><a href="od.jsp">热门OD</a></li>
        </ul>
    </div>
</nav>

</body>
<script type="text/javascript">
    var geodata = getgeodata();
    var odzero;
    var odone;
    var stringod;
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
                dealTheMap(result);
            }
        });
    };
//地图加载
    function dealTheMap(result){
        stringod = result.split("@");
        odzero = eval('('+stringod[0]+')');
        odone = eval('('+stringod[1]+')');
        require.config({
            paths: {
                echarts: 'js'
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
                $('#main').css({
                    height:$('body').height()+"px",
                    width:$('body').width()
                });
//                var mapcon = document.getElementById("main");
                var BMapExt = new BMapExtension($('#main')[0], BMap, echarts,{
                    enableMapClick: false
                });
                var map = BMapExt.getMap();
                var container = BMapExt.getEchartsContainer();

                var startPoint = {x:118.881323,y:36.670064};
                var point = new BMap.Point(startPoint.x, startPoint.y);
                map.centerAndZoom(point, 6);
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
                    color: ['gold', 'aqua'],
                    title: {
                        text: '月热门OD',
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
                        max: 200,
                        x: 'right',
                        calculable: true,
                        color: ['#601986','#2e30de','#00a9e9','#12d6f2'],
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
                            data: odzero
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
                            data: odone
                        },
                    }

                    ]

                };

                var myChart = BMapExt.initECharts(container);
                BMapExt.setOption(option);
            });
    };



</script>
</html>

