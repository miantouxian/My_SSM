<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<header>
    <script type="text/javascript">
        function click_encodeLink(coursetype) {
            location.href = "/myssm/queryChooseCourse?coursetype=" + encodeURIComponent(coursetype) ;
        }

    </script>

    <nav class="navbar navbar-default" role="navigation" style="background-color: white">
        <div class="container-fluid" style="margin-left: 10%">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath }/main">班级工具箱</a>
            </div>
            <div id="nav">

                <!--向左对齐-->
                <ul class="nav navbar-nav navbar-left">

                    <li><a href="${pageContext.request.contextPath }/queryAllConsumlogs">班级账簿</a>
                    </li>

                    <li><a>选课</a>
                        <ul>
                            <c:forEach items="${alltype}" var="item"><li><a href="javascript:click_encodeLink('${item.coursetype}')"rel="external" data-transition="slide">${item.coursetype}</a></li></c:forEach>
                        </ul>

                    </li>
                    <li <c:if test="${tab.tabNameEn == 'creative'}">class="active"
                        </c:if>><a href="${pageContext.request.contextPath }/phoneBook">通讯录</a></li>
                    <li>
                        <a>任务</a>
                        <ul>
                            <li><a href="${pageContext.request.contextPath }/myCreateTask">我的任务</a></li>
                            <li><a href="${pageContext.request.contextPath }/createTask">新建任务</a></li>
                            <li><a href="${pageContext.request.contextPath }/queryAllTask">所有任务</a><br></li>
                        </ul>

                    </li>
                    <li>
                        <a>学习资源</a>
                        <ul>
                            <li><a href="${pageContext.request.contextPath }/createResources">上传</a></li>
                            <li><a href="${pageContext.request.contextPath }/queryAllResources">下载</a></li>
                            <li><a href="${pageContext.request.contextPath }/myFile">我的资源</a></li>
                        </ul>

                    </li>


                </ul>

                <c:if test="${empty sessionScope.user}">
                <!--未登陆-->
                 <ul class="nav navbar-nav navbar-right">
                       <li>
                           <p class="navbar-text"><a href="${pageContext.request.contextPath }/login">登录</a></p>
                       </li>
                       <li>
                               <p class="navbar-text"><a href="/signup">注册</a></p>
                       </li>
                         <li>
                             <p class="navbar-text"></p>
                         </li>
                         <li>
                             <p class="navbar-text"></p>
                         </li>
                         <li>
                             <p class="navbar-text"></p>
                         </li>
                         <li>
                             <p class="navbar-text"></p>
                         </li>
                         <li>
                             <p class="navbar-text"></p>
                         </li>
                         <li>
                             <p class="navbar-text"></p>
                         </li>
                 </ul>
                </c:if>
            <c:if test="${!empty sessionScope.user}">
                   <!--已登陆-->
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <p class="navbar-text"><a href="${pageContext.request.contextPath }/user/${cookie.userid.value}">${sessionScope.user.username}</a></p>
                    </li>
                    <li>
                        <p class="navbar-text"><a href="${pageContext.request.contextPath }/modifyUser">修改</a></p>
                    </li>
                    <li>
                        <p class="navbar-text"><a href="javascript:signout_confirm();">注销</a></p>
                    </li>
                    <li>
                        <p class="navbar-text"></p>
                    </li>
                    <li>
                        <p class="navbar-text"></p>
                    </li>
                    <li>
                        <p class="navbar-text"></p>
                    </li>
                    <li>
                        <p class="navbar-text"></p>
                    </li>
                    <li>
                        <p class="navbar-text"></p>
                    </li>
                    <li>
                        <p class="navbar-text"></p>
                    </li>
                </ul>
            </c:if>
            </div>
        </div>
    </nav>

</header>
<script>
    function signout_confirm()
    {
        var r=confirm("确定退出?")
        if (r==true)
        {
            window.location.href="${pageContext.request.contextPath }/logout";
        }
        else
        {

        }
    }
    $(document).ready(function(){
        $("#nav ul li").children("ul").stop().slideUp();
        $("#nav ul li").hover(function() {
                $(this).children("ul").stop().slideDown();
            },
            function() {
                $(this).children("ul").stop().slideUp();
            });
    });

</script>
<!-- weather -->
<script type="text/javascript">
    WIDGET = {FID: 'DTrL2sdniY'}
</script>
<script type="text/javascript" src="https://apip.weatherdt.com/float/static/js/r.js?v=1111"></script>