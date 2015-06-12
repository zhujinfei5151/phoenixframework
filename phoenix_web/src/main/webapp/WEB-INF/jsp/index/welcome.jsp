<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>系统说明</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/style.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }

    </style>
</head>
<body>
<table class="table table-bordered table-hover definewidth m10" >
        <tr>
           <th colspan="3" align="center">版本说明</th>
        </tr>
	     <tr>
            <td>版本1.2.6</td>
            <td>phoenix_webdriver:新增直接使用定位数据定位和使用数据库数据进行定位。<br>
直接使用定位数据时，无需先将其录入的数据库中，若使用数据库中的数据，则需要先将定位信息录入到数据库。<br>
增加了phantomjs驱动，可以使用无浏览器执行方式。js兼容性方面比httpunit好。修复了2个小bug。
				phoenix_web:修复通过页面无法新增新用户的bug。增加了定时任务时间配置说明
            </td>
            <td>2015.6.11</td>
        </tr>
        <tr>
            <td>版本1.2.5</td>
            <td>phoenix_web:与spring集成消息池管理、邮件管理、附件管理、quartz定时任务功能</td>
            <td>2015.6.5</td>
        </tr>
        <tr><th colspan="3" align="center">联系方式</th></tr>
        <tr>
           <td>mengfeiyang</td>
           <td colspan="2">5156meng.feiyang@163.com</td>
        </tr>
        <tr>
           <td>网站</td>
           <td colspan="2"><a href="http://www.cewan.la" target="_blank">http://www.cewan.la</a>（测完啦！）</td>
        </tr>
        <tr>
           <td>QQ群</td>
           <td colspan="2">246776066</td>
        </tr>
        <tr><th colspan="3" align="center">系统支持类型</th></tr>
        <tr>
           <td>目前最新版本</td>
           <td colspan="2">支持Web GUI自动化测试、Web GUI自动监控与报警</td>
        </tr>
        <tr>
           <td>下一版本计划</td>
           <td colspan="2">增加接口测试/接口并发测试/接口监控功能</td>
        </tr>
        <tr><th colspan="3" align="center">平台说明</th></tr>
        <tr>
           <td>部署方式</td>
           <td colspan="2">J2EE，Jenkins，maven，J2SE，分布式部署，Jetty部署</td>
        </tr>
        <tr>
           <td>技术说明</td>
           <td colspan="2">Apache quartz，Webmagic，httpunit，selendroid，
selenide，Spring+SpringMVC+Hibernate4，Executor，Forkjoin，Maven项目管理，
Bootstrap，JQuery，JDK动态编译+反射+执行，DWR，highchat </td>
        </tr>
        <tr>
           <td>模块划分</td>
           <td colspan="2">
            phoenix_develop：用例代码开发模块<br>
			phoenix_node:分布式执行node节点<br>
			phoenix_web:平台控制端<br>
			phoenix_webdriver:webGUI自动化测试模块<br>
			phoenix_mobiledriver:移动设备测试模块<br>
			phoenix_interface：接口测试系统<br>
			phoenix_db:数据库操作模块，对hibernate4的封装<br>
		</td>
        </tr>
     </table>   
</body>
</html>
