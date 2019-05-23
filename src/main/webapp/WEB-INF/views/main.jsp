<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/jquery-3.2.1.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <title>系统</title>


</head>
<body>
<%@ include file="header.jsp"%>

<a href="${pageContext.request.contextPath }/queryCourse">选课控制</a><br>
<a href="${pageContext.request.contextPath }/queryUserCourse">学生选课</a><br>

</body>

</html>
