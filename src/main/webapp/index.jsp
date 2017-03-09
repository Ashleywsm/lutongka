<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <button type="submit" onclick="test()">测试</button>
</div>

<script src="${ContextPath}/js/jquery-1.10.2.min.js"></script>
<script>


        function test() {
            $.post("${ContextPath}/login/controller",
                {"username":'admin', "password":'admin'},
                function(data){
                    console.log(data);
                },
                'text'
            );
    }
</script>
