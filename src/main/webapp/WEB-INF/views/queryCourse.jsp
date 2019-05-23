<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统</title>
    <script>
        function deleteCourse(){

                document.courseparam.action="${pageContext.request.contextPath }/deleteCourse";
                document.courseparam.submit();

        };
    </script>

</head>
<body>
<c:if test="${msg!=null }">
    ${msg}
</c:if>

<form name="courseparam" action="${pageContext.request.contextPath }/queryCourse" method="post">

    课程名称：<input name="coursename"<c:if test="${courseparam.coursename != null}">value="${courseparam.coursename}"</c:if>/>
    课程类型：<input name="coursetype"<c:if test="${courseparam.coursetype != null}">value="${courseparam.coursetype}"</c:if>/>
    开课日期：<input name="coursedate " <c:if test="${courseparam.coursedate != null}">value="<fmt:formatDate value="${courseparam.coursedate}" pattern="yyyy-MM-dd"/>"</c:if>/>
    课程学分：<input name="coursebord"<c:if test="${courseparam.coursebord != null}">value="${courseparam.coursebord}"</c:if>/>
    课程学分：<input name="cotein"<c:if test="${courseparam.coursebord != null}">value="${courseparam.coursebord}"</c:if>/>
    课程容量：<input name="coursecount"<c:if test="${courseparam.coursecount != null}">value="${courseparam.coursecount}"</c:if>/>
    剩余容量：<input name="coursesy"<c:if test="${courseparam.coursesy != null}">value="${courseparam.coursesy}"</c:if>/>
    已选人数：<input name="coursepeople"<c:if test="${courseparam.coursepeople != null}">value="${courseparam.coursepeople}"</c:if>/>

    开课老师：<input name="courseteacherid"<c:if test="${courseparam.courseteacherid != null}">value="${courseparam.courseteacherid}"</c:if>/>


    <input type="submit" value="查询">
    <input type="button" value="批量删除" onclick="deleteCourse()">
    <a href="${pageContext.request.contextPath }/addCourse">增加课程</a>

    <table width="100%" border=1>
        <tr>
            <td>课程选择</td>
            <td>课程名称</td>
            <td>课程类型</td>
            <td>开课日期</td>
            <td>课程学分</td>
            <td>课程容量</td>
            <td>剩余容量</td>
            <td>已选人数</td>
            <td>课程需求</td>
            <td>开课老师</td>
            <td>课程操作</td>
        </tr>

        <c:forEach items="${courselist}" var="item">
            <tr>
                <td><input type="checkbox" name="dellist" value="${item.id}"/></td>
                <td>${item.coursename}</td>
                <td>${item.coursetype}</td>
                <td><fmt:formatDate value="${item.coursedate}" pattern="yyyy-MM-dd"/></td>
                <td>${item.coursebord }</td>
                <td>${item.coursecount}</td>
                <td>${item.coursesy }</td>
                <td>${item.coursepeople }</td>
                <td>${item.cotein }</td>
                <td>${item.courseteacherid }</td>
                <td><a href="${pageContext.request.contextPath }/modifyCourse?id=${item.id}">修改</a></td>

            </tr>
        </c:forEach>



    </table>


</form>
</body>

</html>
