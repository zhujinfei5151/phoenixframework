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
            <td>版本1.0</td>
            <td>后台开发</td>
            <td>2015.4.24</td>
        </tr>
        <tr>
            <td>版本2.0</td>
            <td>分机执行端开发</td>
            <td>2015.5.3</td>
        </tr>
        <tr>
            <td>版本3.0</td>
            <td>控制端前端开发</td>
            <td>2015.5.8</td>
        </tr>
        <tr><th colspan="3" align="center">联系方式</th></tr>
        <tr>
           <td>mengfeiyang</td>
           <td colspan="2">mengfeiyang@360.cn</td>
        </tr>
        <tr><th colspan="3" align="center">系统支持</th></tr>
        <tr>
           <td>1</td>
           <td colspan="2">Web GUI自动化、接口测试、Android app GUI自动化</td>
        </tr>
        <tr>
           <td>2</td>
           <td colspan="2">支持其他通用json接口的测试</td>
        </tr>
        
     </table>   
</body>
</html>
