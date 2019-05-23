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
${requestScope.msg}
<div style="width: 70%;margin:1% 2% 1% 5%;float: left;">
    <div class="panel panel-default" id="main" style="">
        <div class="panel-heading" style="background-color: white">
            <a href="${pageContext.request.contextPath }/main">班级工具箱</a> ›  ${task.tasktitle}
        </div>

        <div class="panel-body">
            <c:if test="${!empty task}">
                <form name="task" action="${pageContext.request.contextPath }/chooseTask" method="post"class="form-horizontal" role="form">

                    <input type="hidden" name="id" value="${task.id}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">任务标题</label>
                        <div class="col-sm-10">
                            <input  type="text" style="width: 200px" class="form-control" id="tasktitle" name="tasktitle" value="${task.tasktitle}" readonly/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">创建日期</label>
                        <div class="col-sm-10">
                            <input  type="text" style="width: 200px" class="form-control" id="createtime" name="createtime" value="<fmt:formatDate value="${task.createtime}" pattern="yyyy-MM-dd"/>" readonly/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">任务详情</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="10" id="taskdetail" name="taskdetail" readonly >${task.taskdetail}</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">任务图片</label>
                        <div style="margin-left: 17%">
                            <c:if test="${task.taskimg != null}">
                                <img src="/temp/${task.taskimg}" class="img-rounded">
                            </c:if>
                            <c:if test="${task.taskimg == null}">
                                没有图片
                            </c:if>

                        </div>
                    </div>
                    <c:if test="${task.status =='开启'}">
                        <div class="form-group" style="text-align: center">
                            <input type="submit" value="接受">
                        </div>
                    </c:if>

            </form>
            </c:if>

        </div>
    </div>
</div>


</body>
</html>