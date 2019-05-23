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

    <title>系统</title>



    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
    </script>
    <script>

        function delteConsumlog() {
            document.consumParam.action="${pageContext.request.contextPath }/deleteConsumlog";
            document.consumParam.submit();
        }
    </script>
    <style>
        *{
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }
        body{
            font-family: Helvetica;
            -webkit-font-smoothing: antialiased;
        }
        h2{
            text-align: center;
            font-size: 18px;
            text-transform: uppercase;
            letter-spacing: 1px;

            padding: 30px 0;
        }

        /* Table Styles */

        .table-wrapper{
            margin: 10px 70px 70px;
            box-shadow: 0px 35px 50px rgba( 0, 0, 0, 0.2 );
        }

        .fl-table {
            border-radius: 5px;
            font-size: 12px;
            font-weight: normal;
            border: none;
            border-collapse: collapse;
            width: 100%;
            max-width: 100%;
            white-space: nowrap;
            background-color: white;
        }

        .fl-table td, .fl-table th {
            text-align: center;
            padding: 8px;
        }

        .fl-table td {
            border-right: 1px solid #f8f8f8;
            font-size: 12px;
        }

        .fl-table thead th {
            color: #ffffff;
            background: #4FC3A1;
        }


        .fl-table thead th:nth-child(odd) {
            color: #ffffff;
            background: #324960;
        }

        .fl-table tr:nth-child(even) {
            background: #F8F8F8;
        }

        /* Responsive */

        @media (max-width: 767px) {
            .fl-table {
                display: block;
                width: 100%;
            }
            .table-wrapper:before{
                content: "Scroll horizontally >";
                display: block;
                text-align: right;
                font-size: 11px;
                color: white;
                padding: 0 0 10px;
            }
            .fl-table thead, .fl-table tbody, .fl-table thead th {
                display: block;
            }
            .fl-table thead th:last-child{
                border-bottom: none;
            }
            .fl-table thead {
                float: left;
            }
            .fl-table tbody {
                width: auto;
                position: relative;
                overflow-x: auto;
            }
            .fl-table td, .fl-table th {
                padding: 20px .625em .625em .625em;
                height: 60px;
                vertical-align: middle;
                box-sizing: border-box;
                overflow-x: hidden;
                overflow-y: auto;
                width: 120px;
                font-size: 13px;
                text-overflow: ellipsis;
            }
            .fl-table thead th {
                text-align: left;
                border-bottom: 1px solid #f7f7f9;
            }
            .fl-table tbody tr {
                display: table-cell;
            }
            .fl-table tbody tr:nth-child(odd) {
                background: none;
            }
            .fl-table tr:nth-child(even) {
                background: transparent;
            }
            .fl-table tr td:nth-child(odd) {
                background: #F8F8F8;
                border-right: 1px solid #E6E4E4;
            }
            .fl-table tr td:nth-child(even) {
                border-right: 1px solid #E6E4E4;
            }
            .fl-table tbody td {
                display: block;
                text-align: center;
            }
        }
    </style>
</head>
<body style="text-align:center;">

<%@ include file="header.jsp"%>
<c:if test="${msg!=null }">
    ${msg}
</c:if>


<form name="consumParam" action="${pageContext.request.contextPath }/queryConsumlogs" method="post">
    操作人：<input name="consumname"<c:if test="${pageBean.consumParam.consumname != null}">value="${pageBean.consumParam.consumname}"</c:if>/>
    日期：<input type="text" name="consumdate" <c:if test="${pageBean.consumParam.consumdate != null}">value="<fmt:formatDate value="${pageBean.consumParam.consumdate}" pattern="yyyy-MM-dd"/>"</c:if>/>
    收入/支出：<input name="type"<c:if test="${pageBean.consumParam.type != null}">value="${pageBean.consumParam.type}"</c:if>/>
    金额：<input name="csmoney"<c:if test="${pageBean.consumParam.csmoney != null}">value="${pageBean.consumParam.csmoney}"</c:if>/>
    备注：<input name="remark"<c:if test="${pageBean.consumParam.remark != null}">value="${pageBean.consumParam.remark}"</c:if>/>
    支付方式：<input name="consumtype"<c:if test="${pageBean.consumParam.consumtype != null}">value="${pageBean.consumParam.consumtype}"</c:if>/>
    余额：<input name="remainder"<c:if test="${pageBean.consumParam.remainder != null}">value="${pageBean.consumParam.remainder}"</c:if>/>

    <input type="submit" value="查询">
    <c:if test="${sessionScope.user.username == '苏浪标'}">
        <input type="button" value="批量删除" onclick="delteConsumlog()">
        <a href="${pageContext.request.contextPath }/addConsumlog">增加记录</a>
    </c:if>
    <dir class="table-wrapper">
        <table width="100%" border=1 class="fl-table">
            <thead>
            <tr>
                <c:if test="${sessionScope.user.username == '苏浪标'}">
                    <th>选择</th>
                </c:if>
                <th>操作人</th>
                <th>日期</th>
                <th>收入/支出</th>
                <th>金额</th>
                <th>备注</th>
                <th>支付方式</th>
                <th>余额</th>
                <th>操作</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageBean.lists}" var="cm">
                <tr>
                    <c:if test="${sessionScope.user.username == '苏浪标'}">
                        <td><div class="list-item"><input type="checkbox" name="delid" value="${cm.id}"/></div></td>
                    </c:if>
                    <td>${cm.consumname}</td>
                    <td><fmt:formatDate value="${cm.consumdate}" pattern="yyyy-MM-dd"/></td>
                    <td>${cm.type }</td>
                    <td>${cm.csmoney}</td>
                    <td>${cm.remark }</td>
                    <td>${cm.consumtype }</td>
                    <td>${cm.remainder }</td>

                    <c:choose>
                        <c:when test="${sessionScope.user.username == '苏浪标'}">
                            <td><a href="${pageContext.request.contextPath }/modifyConsumlog?id=${cm.id}">修改</a><a href="${pageContext.request.contextPath }/consumlogInfo?id=${cm.id}">查看</a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="${pageContext.request.contextPath }/consumlogInfo?id=${cm.id}">查看</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            <tbody>
        </table>
    </dir>

    <a href="${pageContext.request.contextPath }/queryConsumlogs?currentPage=${pageBean.previouspage}">[上一页]</a>
    当前页为${pageBean.currPage},总共${pageBean.totalPage}
    <a href="${pageContext.request.contextPath }/queryConsumlogs?currentPage=${pageBean.nextpage}">[下一页]</a>

    </form>
</body>


</html>
