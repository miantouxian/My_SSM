<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/jquery-3.2.1.js"></script>
    <script src="./js/bootstrap.min.js"></script>
</head>
<body>
<!-- 引入header文件 -->
<%@ include file="header.jsp"%>

<c:if test="${!empty userInfo}">
    <form name="user" action="${pageContext.request.contextPath }/modifyUser" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">

        <div style="width: 70%;margin:1% 2% 1% 5%;float: left;">
            <div class="panel panel-default" id="main" style="">
                <div class="panel-heading" style="background-color: white">
                    <a href="${pageContext.request.contextPath }/main">班级工具箱</a> › 修改资料
                </div>
        <c:choose>
            <c:when test="${userInfo.avatar == ''}">
                <div style="margin-left: 17%">
                    <br/><input type="file"  name="user_img" accept="image/png,image/jpeg,image/jpg" ><br/>
                </div>
            </c:when>
            <c:when test="${userInfo.avatar != null}">
                <div style="margin-left: 17%">
                    <br/><img width="60px" height="60px" src="/temp/${userInfo.avatar}" class="img-rounded">
                    <a href="${pageContext.request.contextPath }/deleteUserAvatar?userid=${userInfo.userid}">删除图片</a>
                </div><br/>

            </c:when>
            <c:otherwise>
                <div style="margin-left: 17%">
                    <br/><input type="file"  name="user_img" accept="image/png,image/jpeg,image/jpg" ><br/>
                </div>
            </c:otherwise>
        </c:choose>
        <div class="form-group">
            <label class="col-sm-2 control-label">姓名</label>
            <input type="hidden" style="width: 200px" class="form-control" id="userid" name="userid" value="${userInfo.userid}"/>
            <input type="hidden" style="width: 200px" class="form-control" id="id" name="id" value="${userInfo.id}"/>
            <div class="col-sm-10">
                <input type="text" style="width: 200px" class="form-control" id="username" name="username"  <c:if test="${userInfo.username != null}">value="${userInfo.username}"</c:if>/>
            </div>
        </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input style="width: 200px" class="form-control" id="password" name="password" name="password"<c:if test="${userInfo.password != null}">value="${userInfo.password}"</c:if>/>
                    </div>
                </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">生日</label>
            <div class="col-sm-10">
                <input style="width: 200px" class="form-control" id="birthday" name="birthday"<c:if test="${userInfo.birthday != null}">value="<fmt:formatDate value="${userInfo.birthday}" pattern="yyyy-MM-dd"/>"</c:if>/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">电话号码</label>
            <div class="col-sm-10">
                <input type="text" style="width: 200px" class="form-control" id="phone_num" name="phone_num"  <c:if test="${userInfo.phone_num != null}">value="${userInfo.phone_num}"</c:if>/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label"></label>
            <div class="col-sm-10">
                <input type="submit" value="修改"/>
            </div>
        </div>
            </div>
        </div>


    </form>
</c:if>


</body>
</html>