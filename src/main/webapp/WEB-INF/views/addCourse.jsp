<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="index.css">
    <script src="jquery.min.js"></script>
    <script src="date.js"></script>
    <title>系统</title>
</head>
<body>
<c:if test="${cookie.username!=null }">
    <a href="${pageContext.request.contextPath }/logout">注销</a>
</c:if>
<script type="text/javascript">
</script>
<form name="course" action="${pageContext.request.contextPath }/addCourse" method="post">

    <table width="100%" border=1>
        <tr>
            <td>课程名称</td>
            <td><input type="text" name="coursename"></td>
        </tr>
        <tr>
            <td>课程类型</td>
            <td><input type="text" name="coursetype"></td>
        </tr>
        <tr>
            <td>开课日期</td>
            <td><input type="text" name="coursedate"></td>
        </tr>
        <tr>
            <td>课程学分</td>
            <td><input type="text" name="coursebord"></td>
        </tr>
        <tr>
            <td>课程容量</td>
            <td><input type="text" name="coursecount"></td>
        </tr>
        <tr>
            <td>剩余容量</td>
            <td><input type="text" name="coursesy"></td>
        </tr>
        <tr>
            <td>已选人数</td>
            <td><input type="text" name="coursepeople"></td>
        </tr>

        <tr>
            <td>课程需求</td>
            <td><input type="text" name="cotein"></td>
        </tr>
        <tr>
            <td>开课老师</td>
            <td><input type="text" name="courseteacherid"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
