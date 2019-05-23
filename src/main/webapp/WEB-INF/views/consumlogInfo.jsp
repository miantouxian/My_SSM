<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="index.css">
    <script src="jquery.min.js"></script>
    <script src="date.js"></script>
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

<script type="text/javascript">
</script>


    <table width="100%" border=1>
        <tr>
            <input type="hidden" name="id"value="${consumlog.id}"/>
            <td>操作人</td>
            <td><input name="consumname"<c:if test="${consumlog.consumname != null}">value="${consumlog.consumname}"</c:if>/></td>
        </tr>
        <tr>
            <td>日期</td>
            <td><input name="consumdate"<c:if test="${consumlog.consumdate != null}">value="<fmt:formatDate value="${consumlog.consumdate}" pattern="yyyy-MM-dd"/>"</c:if>/></td>
        </tr>
        <tr>
            <td>收入/支出</td>
            <td>
                <input name="type" value="收入" type="radio" lab="收入" <c:if test="${consumlog.type != '收入'}"> checked="checked" </c:if> />
                <input name="type" value="支出" type="radio" lab="支出" <c:if test="${consumlog.type != '支出'}"> checked="checked" </c:if>  />
            </td>
        </tr>
        <tr>
            <td>金额</td>
            <td><input name="csmoney"<c:if test="${consumlog.csmoney != null}">value="${consumlog.csmoney}"</c:if>/></td>
        </tr>
        <tr>
            <td>备注</td>
            <td><input name="remark"<c:if test="${consumlog.remark != null}">value="${consumlog.remark}"</c:if>/></td>
        </tr>
        <tr>
            <td>支付方式</td>
            <td>
                <input name="consumtype" value="现金" type="radio" lab="现金" <c:if test="${consumlog.consumtype != '现金'}">checked="checked" </c:if> />
                <input name="consumtype" value="支付宝" type="radio" lab="支付宝" <c:if test="${consumlog.consumtype != '支付宝'}">checked="checked" </c:if> />
                <input name="consumtype" value="微信" type="radio" lab="微信" <c:if test="${consumlog.consumtype != '微信'}">checked="checked" </c:if> />
            </td>
        </tr>
        <tr>
            <td>余额</td>
            <td><input name="remainder"<c:if test="${consumlog.remainder != null}">value="${consumlog.remainder}"</c:if>/></td>
        </tr>
        <tr>
            <td>上传图片</td>
            <td>

                <c:choose>
                    <c:when test="${consumlog.imghref == ''}">
                        <input type="file"  name="items_pic" accept="image/png,image/jpeg,image/jpg"/>
                    </c:when>
                    <c:when test="${consumlog.imghref != null}">
                        <img src="/temp/${consumlog.imghref}"/>
                        <a href="${pageContext.request.contextPath }/deleteImg?id=${consumlog.id}">删除图片</a>
                    </c:when>
                    <c:otherwise>
                        没有图片
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

    </table>

</body>
</html>
