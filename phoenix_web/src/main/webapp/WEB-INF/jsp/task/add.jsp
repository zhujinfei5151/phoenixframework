<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>增加任务</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/style.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/xheditor/xheditor-1.2.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/xheditor/xheditor_lang/zh-cn.js"></script>
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
<sf:form method="post" action="add" modelAttribute="taskModel" id="addForm">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">任务名称</td>
        <td><sf:input path="taskName"/><sf:errors path="taskName"/></td>
    </tr>
    <tr>
        <td class="tableleft">任务类型</td>   
        <td>
        <%-- <sf:radiobuttons path="taskType" items="${types }"/> --%>
        <sf:select path="taskType">
           <sf:options items="${types }"/>
        </sf:select>
        </td>
    </tr>
    <tr>
        <td class="tableleft">任务数据</td>  
        <td><sf:input path="taskData"/><sf:errors path="taskData"/></td>
    </tr>  
    <tr>
        <td class="tableleft">选择执行机</td>   
        <td>
           <select name="slaveIP">
              <option value="localhost:8080">localhost:8080</option>
              <option value="127.0.0.1:8080">127.0.0.1:8080</option>
              <option value="192.168.1.11:8080">192.168.1.11:8080</option>
           </select>
              &nbsp;&nbsp;列表为空，代表暂无可用执行机
        </td>
    </tr>  
    <tr>
        <td class="tableleft">执行参数</td>   
        <td>
            <sf:input path="taskParameter"/>
              &nbsp;&nbsp;若不输入，则默认仅执行一次
        </td>
    </tr> 
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary">提交</button>&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</sf:form>
<script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
				window.location.href="list";
		 });

    });
</script>
</body>
</html>
