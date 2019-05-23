<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="Content-Type"  content="text/html;charset=utf-8">
    <title>班级工具箱</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/jquery-3.2.1.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <style>
        li {list-style-type:none;}
        html, body {
            height: 100%;
            font-size: 14px;
            color: #525252;
            font-family: NotoSansHans-Regular,AvenirNext-Regular,arial,Hiragino Sans GB,"Microsoft Yahei","Hiragino Sans GB","WenQuanYi Micro Hei",sans-serif;
            background: #f0f2f5;
        }
        .footer {
            background-color: #fff;
            margin-top: 22px;
            margin-bottom: 22px;
            width: 100%;
            padding-top: 22px;
            color: #8A8A8A;
            display: block;
            height: 200px;
            border: 1px ;
            clear:both
        }

        .container {
            margin-right: 5%;
            margin-left: 5%;
            padding-left: 15px;
            padding-right: 15px;
            width: 40%;
            float: left;
        }
        .info {
            margin-right: 5%;
            width: 10%;
            float: left;
        }
    </style>
</head>
<body>
<!-- 引入header文件 -->
<%@ include file="header.jsp"%>

<div class="panel panel-default" id="main" style="width: 70%;margin:1% 2% 5% 5%;float: left;">
    ${msg}

    <ul class="list-group" style="width: 100%">
        <div class="panel-heading" style="background-color: white">
            <a href="${pageContext.request.contextPath }/main">班级工具箱</a> › 我的任务
        </div>
        <div class="panel-heading" style="background-color: white">
            创建&nbsp;&nbsp;&nbsp;&nbsp;<a style="margin-right: 2%" href="${pageContext.request.contextPath }/myAcceptTask">接受</a>
        </div>

        <c:forEach items="${tasks}" var="task">
            <li class="list-group-item">
                <div style="height: 50px">
                    <div style="float: left;width: 6%;margin-bottom: 5px">
                        <img width="50px" height="50px" src="/temp/${task.user.avatar}" class="img-rounded">
                    </div>
                    <div style="width: 89%;float: left">
                        <a href="${pageContext.request.contextPath }/taskData?id=${task.id}">${task.tasktitle}</a><br/>
                        <div>
                            <a href="${pageContext.request.contextPath }/user/${task.user.userid}"><span class="label label-default" >${task.offeror}</span></a>

                            <small class="text-muted"><fmt:formatDate value="${task.createtime}" pattern="yyyy-MM-dd"/></small>

                        </div>
                    </div>
                    <div style="width: 5%;float: right;text-align: center">
                        <span class="badge">${task.status}</span>
                    </div>
                    <div style="width: 5%;float: right;text-align: center">
                        <c:if test="${task.status == '开启'}">
                            <a href="${pageContext.request.contextPath }/deleteMyTask?id=${task.id}">删除</a>
                        </c:if>
                        <c:if test="${task.status == '待确认'}">
                            <a href="${pageContext.request.contextPath }/offerorConfirm?id=${task.id}">确认完成</a>
                        </c:if>

                    </div>

                </div>
            </li>
        </c:forEach>

    </ul>

</div>


</body>
</html>