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

    <form name="file" action="${pageContext.request.contextPath }/createFile" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
${msg}
        <div style="width: 70%;margin:1% 2% 1% 5%;float: left;">
            <div class="panel panel-default" id="main" style="">
                <div class="panel-heading" style="background-color: white">
                    <a href="${pageContext.request.contextPath }/main">班级工具箱</a> › 创建资源
                </div><br>
                <div class="form-group">
                    <label class="col-sm-2 control-label">资源名称</label>
                    <div class="col-sm-10">
                        <input type="text" style="width: 200px" class="form-control" id="filename" name="filename"/>
                    </div>
                </div>
                 <div class="form-group">
                     <label class="col-sm-2 control-label">资源描述</label>
                      <div class="col-sm-10">
                          <textarea class="form-control" rows="10" id="description" name="description" ></textarea>
                      </div>
                 </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">上传资源</label>
                    <div style="margin-left: 17%">
                        <br/><input type="file"  name="resources"  multiple="multiple" ><br/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>

                    <div class="col-sm-10" style="text-align: center">
                        <input type="submit" value="上传"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </div>
        </div>

    </form>

</body>
</html>