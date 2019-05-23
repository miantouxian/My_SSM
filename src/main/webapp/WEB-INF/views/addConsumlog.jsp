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
    <link rel="stylesheet" href="css/jquery.richUI.min.css" />
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/jquery.richUI.min.js"></script>
    <script type="text/javascript" src="js/jquery.browser.min.js"></script>
    <script type="text/javascript">
        $(function() {

            $("input[name='type']").richradio();
            $("input[name='consumtype']").richradio();


        });
    </script>
    <title>系统</title>
</head>
<body>
<%@ include file="header.jsp"%>
<form name="consumlog" action="${pageContext.request.contextPath }/addConsumlog" method="post" enctype="multipart/form-data">

    <table width="100%" border=1>
        <tr>
            <td>操作人</td>
            <td><input type="text" name="consumname"></td>
        </tr>
        <tr>
            <td>日期</td>
            <td><input type="text" name="consumdate"></td>
        </tr>
        <tr>
            <td>收入/支出</td>
            <td>
                <input name="type" value="收入" type="radio" lab="收入" checked="checked" />
                <input name="type" value="支出" type="radio" lab="支出" />
            </td>
        </tr>
        <tr>
            <td>金额</td>
            <td><input type="text" name="csmoney"></td>
        </tr>
        <tr>
            <td>备注</td>
            <td><input type="text" name="remark"></td>
        </tr>
        <tr>
            <td>支付方式</td>
            <td>
                <input name="consumtype" value="现金" type="radio" lab="现金" checked="checked" />
                <input name="consumtype" value="支付宝" type="radio" lab="支付宝" />
                <input name="consumtype" value="微信" type="radio" lab="微信" />
            </td>
        </tr>
        <tr>
            <td>余额</td>
            <td><input type="text" name="remainder"></td>
        </tr>
        <tr>
            <td>账单图片</td>
            <td>
                <input type="file"  name="items_pic" accept="image/png,image/jpeg,image/jpg"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"/>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
