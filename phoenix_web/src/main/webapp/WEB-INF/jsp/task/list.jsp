<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>任务列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Css/style.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/JSer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/Js/artDialog/artDialog.js?skin=default"></script>

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
    
    <script type="text/javascript">
      function start(id){
    	var obj = null;
		var localObj = window.location;
		var contextPath = localObj.pathname.split("/")[1];
		var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
		var myDialog = 	art.dialog({
			icon : 'face-smile',
			title : '提示',
			drag : true,
			resize : false,
			content : '任务发布成功，等待分机响应....',
			ok : true,
		});

   		 JSer.url(basePath+"/task/start/"+id).ajax({
			    method:"POST", 
			    success:function(d){
			    	obj = JSON.parse(d);//字符串转为json对象
			    	myDialog.content(obj.msg);
			    },
			});
      }
   </script>
</head>
<body>
<form class="form-inline definewidth m20" action="select" method="post">  
    任务类型：
        <select name="type">
           <option value="">显示全部</option>
           <c:forEach items="${types }" var="t">
                <c:choose>
                	<c:when test="${t.key eq type }">
                		<option value="${t.key }" selected="selected">${t.value }</option>
                	</c:when>
                	<c:otherwise>
                		<option value="${t.key }">${t.value }</option>
                	</c:otherwise>
                </c:choose>
           </c:forEach>
        </select>
    &nbsp;&nbsp;  
  任务状态：
        <select name="tstatus">
           <option value="">显示全部</option>
           <c:forEach items="${status }" var="s">
           		<c:choose>
                	<c:when test="${s.key eq tstatus }">
                		<option value="${s.key }" selected="selected">${s.value }</option>
                	</c:when>
                	<c:otherwise>
                		<option value="${s.key }">${s.value }</option>
                	</c:otherwise>
                </c:choose>
           </c:forEach>
        </select>
     &nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">添加任务</button>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>任务编号</th>
        <th>任务名称</th>
        <th>任务类型</th>
        <th>SlaveIP</th>
        <th>任务数据Id</th>
        <th>任务状态</th>
        <th>执行参数</th>
        <th>定时器状态</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>管理操作</th>
    </tr>
    </thead>
    <tbody>
       <c:forEach items="${datas.datas}" var="ts">
	     <tr>
            <td>${ts.id }</td>
            <td>${ts.taskName }
            <td>${ts.taskType }</td>
			<td>${ts.slaveModel.slaveIP }</td>
			<td>${ts.taskData } - ${ts.beanName }</td>
			<td>${ts.taskStatusType }</td>
			<td>${ts.taskParameter }</td>
			<td>${ts.jobStatus }<br><fmt:formatDate value="${ts.lastTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            <td><fmt:formatDate value="${ts.startTime }" pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate></td>
            <td><fmt:formatDate value="${ts.endTime }" pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate></td>
            <td>
                  <a href="update/${ts.id}">编辑</a>&nbsp;&nbsp;
                  <a href="delete/${ts.id}">删除</a>&nbsp;&nbsp;
                  <a href="javascript:start('${ts.id }');">启动</a><br>
                   <a href="startJob/${ts.id}">StartJob</a>&nbsp;&nbsp;
                  <a href="stopJob/${ts.id}">StopJob</a>&nbsp;&nbsp;
            </td>
        </tr>
        </c:forEach>
        </tbody>
     </table>
<div class="inline pull-right page">
		<jsp:include page="/jsp/pager.jsp">
			<jsp:param value="${datas.total }" name="totalRecord"/>
			<jsp:param value="list" name="url"/>
		</jsp:include>
 </div>  
 <script>
    $(function () {
		$('#addnew').click(function(){
				window.location.href="add";
		 });
    });

	function del(id)
	{
		if(confirm("确定要删除吗？"))
		{
			var url = "index.jsp";
			window.location.href=url;		
		}
	
	}
</script>     
</body>
</html>
