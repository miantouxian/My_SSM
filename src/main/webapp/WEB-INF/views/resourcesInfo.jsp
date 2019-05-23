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
    <script>

        function downloadFile(downloadfilename) {
            var hdnFilePath = document.createElement('input');
            hdnFilePath.type = 'hidden';
            hdnFilePath.name = 'downloadfilename';
            hdnFilePath.value = downloadfilename;
            document.file.appendChild(hdnFilePath);
            window.open().href = "/downloadResources";
            document.file.submit();

        }
    </script>
</head>
<body>
<!-- 引入header文件 -->
<%@ include file="header.jsp"%>
${requestScope.msg}
<div style="width: 70%;margin:1% 2% 1% 5%;float: left;">
    <div class="panel panel-default" id="main" style="">
        <div class="panel-heading" style="background-color: white">
            <a href="${pageContext.request.contextPath }/main">班级工具箱</a> ›  ${file.filename}
        </div>

        <div class="panel-body">
            <c:if test="${!empty file}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">资源名称</label>
                        <div class="col-sm-10">
                            <input  type="text" style="width: 200px" class="form-control" id="tasktitle" name="tasktitle" value="${file.filename}" readonly/>
                        </div>
                    </div><br><br>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">创建日期</label>
                        <div class="col-sm-10">
                            <input  type="text" style="width: 200px" class="form-control" id="createtime" name="createtime" value="<fmt:formatDate value="${file.createtime}" pattern="yyyy-MM-dd"/>" readonly/>
                        </div>
                    </div><br><br>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">上传人</label>
                        <div class="col-sm-10">
                            <input  type="text" style="width: 200px" class="form-control" id="createman" name="createman" value="${file.createman}" readonly/>
                        </div>
                    </div><br><br>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">资源描述</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="10" id="description" name="description" readonly >${file.description}</textarea>
                        </div>
                    </div><br><br>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">下载资源</label>
                        <div style="margin-left: 17%">

                            <c:forEach items="${file.download}" var="item">
                                <a href="${pageContext.request.contextPath }/downloadResources?downloadfilename=${item}&id=${file.id}" target="_blank">${item}</a>
                            </c:forEach>
                        </div>
                    </div>
            </c:if>

        </div>
    </div>

</div>

</body>
</html>