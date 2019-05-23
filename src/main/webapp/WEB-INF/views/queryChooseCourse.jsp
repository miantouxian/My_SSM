<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/jquery-3.2.1.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <title>系统</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" >
        var oInpt = document.getElementsByTagName('coursename');

        function click_encodeLink(coursetype,coursename) {
            location.href = "/myssm/chooseCourse?coursetype=" + encodeURIComponent(coursetype) + "&coursename=" + encodeURIComponent(coursename) ;
        }

    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(function(){
                $("#course").find(":checkbox").each(function(){
                    $(this).click(function(){
                        if($(this).is(':checked')){
                            $(this).attr('checked',true).siblings().attr('checked',false);
                        }else{
                            $(this).attr('checked',false).siblings().attr('checked',false);
                        }
                    });
                });
            });
        });

    </script>
</head>
<body>
<%@ include file="header.jsp"%>
<c:if test="${msg!=null }">
    ${msg}
</c:if>



    <table width="100%" border=1>
        <tr>
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
        <input type="hidden" name="coursetype" value="${courselist[0].coursetype}"/>
        <c:forEach items="${courselist}" var="item">
            <tr>
                <td>${item.coursename}</td>
                <td>${item.coursetype}</td>
                <td><fmt:formatDate value="${item.coursedate}" pattern="yyyy-MM-dd"/></td>
                <td>${item.coursebord }</td>
                <td>${item.coursecount}</td>
                <td>${item.coursesy }</td>
                <td>${item.coursepeople }</td>
                <td>${item.cotein }</td>
                <td>${item.courseteacherid }</td>
                <td>
                    <c:choose>
                        <c:when test="${item.jgchoose == '0'}">
                            <a href="javascript:click_encodeLink('${item.coursetype}','${item.coursename}')"rel="external" data-transition="slide">确认选课</a>
                        </c:when>
                        <c:when test="${item.jgchoose == '2'}">
                            <c:out value="没有剩余"></c:out>
                        </c:when>
                        <c:otherwise>
                            <c:out value="已选课"></c:out>
                        </c:otherwise>
                    </c:choose>

                </td>
                
            </tr>
        </c:forEach>


       
    </table>


</body>
</html>
