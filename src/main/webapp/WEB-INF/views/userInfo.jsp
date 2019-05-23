<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.2.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>

</head>
<body>
<!-- 引入header文件 -->
<%@ include file="header.jsp"%>

<div style="width: 70%;margin:1% 2% 1% 5%;float: left;">
    <div class="panel panel-default" id="main" style="">
        <div class="panel-heading" style="background-color: white">
            <a href="${pageContext.request.contextPath }/main">班级工具箱</a> › ${userInfo.username}
        </div>

        <div class="panel-body">
            <c:if test="${!empty userInfo}">
            <form class="form-horizontal" role="form">
                <div style="margin-left: 17%">
                    <img width="60px" height="60px" src="/temp/${userInfo.avatar}" class="img-rounded">
                </div><br/>
                <div class="form-group">
                    <label class="col-sm-2 control-label">学号</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${userInfo.userid}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${userInfo.username}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">生日</label>
                    <div class="col-sm-10">
                        <p class="form-control-static"><fmt:formatDate value="${userInfo.birthday}" pattern="yyyy-MM-dd"/></p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">电话号码</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${userInfo.phone_num}</p>
                    </div>
                </div>
            </form>
            </c:if>

        </div>
    </div>
</div>


</body>
</html>