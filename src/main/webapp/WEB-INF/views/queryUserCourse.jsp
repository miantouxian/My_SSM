<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统</title>

    <script type="text/javascript">
    </script>
</head>
<body>
<c:if test="${msg!=null }">
    ${msg}
</c:if>


<form name="course" action="${pageContext.request.contextPath }/queryUserCourse" method="post">

    学生姓名：<input name="username"<c:if test="${usercourse.username != null}">value="${usercourse.username}"</c:if>/>
    课程名称：<input name="coursename"<c:if test="${usercourse.coursename != null}">value="${usercourse.coursename}"</c:if>/>


    <input type="submit" value="查询">
    <table width="100%" border=1>
        <tr>
            <td>学生姓名</td>
            <td>课程名称</td>
        </tr>

        <c:forEach items="${uslist}" var="item">
            <tr>
                <td>${item.username}</td>
                <td>${item.coursename}</td>
            </tr>
        </c:forEach>



    </table>


</form>
</body>
</html>
