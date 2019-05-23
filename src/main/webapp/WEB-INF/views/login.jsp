<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="./css/bootstrap.min.css">
    <script src='https://libs.baidu.com/jquery/1.10.2/jquery.min.js'></script>


    <title>系统登陆</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/login" method="post">

<div class="panel panel-default" id="login" style="width: 20%;margin-left: 40%;margin-top: 5%;margin-bottom: 5%">
    <div class="panel-heading" style="background-color: #fff">
        <h3 class="panel-title">登录</h3>
    </div>
    <div class="panel-body">

        <div class="form-group">
            <label for="userid">用户名</label>
            <input type="text" class="form-control" id="userid" name="userid" placeholder="请输入学号" required="required"><c:if test="${requestScope.msg != null}">${requestScope.msg}</c:if>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" required="required">
        </div>

        <p style="text-align: right;color: red;position: absolute" id="info"></p><br/>

        <input class="btn btn-success btn-block" type="submit" value="登陆"/>
        <td><input class="btn btn-success btn-block" type="button" value="注册" onclick="window.location='${pageContext.request.contextPath }/register'"/></td>
        </input>

    </div>
</div>
</form>

</body>
</html>