<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/jquery-3.2.1.js"></script>
    <script src="/js/bootstrap.min.js"></script>

</head>
<body>
<form name="user" action="${pageContext.request.contextPath }/register" method="post" accept-charset="UTF-8">
    学号：<input type="text" name="userid" /><br/>${msg}
    密码：<input type="password" name="password" /><br/>
    姓名：<input type="text" name="username" /><br/>
    <input type="submit" value="注册"/>
    <td><input type="button" value="返回登录" onclick="window.location='${pageContext.request.contextPath }/login'"/></td>
</form>
<p>如果你点我，我就会消失。</p>
<p>继续点我!</p>
<p>接着点我!</p>
</body>
</html>
