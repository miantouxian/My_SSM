<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<c:if test="${msg!=null }">
    ${msg}
</c:if>


<form name="user" action="${pageContext.request.contextPath }/phoneBook" method="post">

    学生姓名：<input type="text" name="username" <c:if test="${!empty userparam.username}">value="${userparam.username}" </c:if></>
    电话号码：<input type="text" name="phone_num"<c:if test="${!empty userparam.phone_num}">value="${userparam.phone_num}" </c:if>/>


    <input type="submit" value="查询">
    <table width="100%" border=1>
        <tr>
            <td>学生姓名</td>
            <td>电话号码</td>
        </tr>

        <c:forEach items="${userlist}" var="item">
            <tr>
                <td>${item.username}</td>
                <td>${item.phone_num}</td>
            </tr>
        </c:forEach>
    </table>


</form>
</body>
</html>
