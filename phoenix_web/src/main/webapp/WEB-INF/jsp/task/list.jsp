<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
   		 JSer.url(basePath+"/task/start/"+id).ajax({
			    method:"POST", 
			    success:function(d){
			    	obj = JSON.parse(d);//字符串转为json对象
			    	if(obj.result == 1){
						art.dialog({
							icon : 'succeed',
							title : '提示',
							drag : true,
							resize : false,
							content : "任务启动成功！<br>可在 <a href='tasklist.jsp'><strong>任务列表检视</strong></a> 模块查看该任务的状态",
							ok : true,
						});
			    	} else {
						art.dialog({
							icon : 'error',
							title : '提示',
							drag : true,
							resize : false,
							content : d,
							ok : true,
						});
			    	}
			    } 
			});
      }
   </script>
</head>
<body>
<form class="form-inline definewidth m20" action="index.jsp" method="get">  
    任务名称：
    <input type="text" name="rolename" id="rolename"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">添加任务</button>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>任务编号</th>
        <th>任务名称</th>
        <th>任务类型</th>
        <th>SlaveIP</th>
        <th>任务数据</th>
        <th>任务状态</th>
        <th>执行参数</th>
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
			<td>${ts.slaveIP }</td>
			<td>${ts.taskData }</td>
			<td>${ts.taskStatusType }</td>
			<td>${ts.taskParameter }</td>
            <td><fmt:formatDate value="${ts.startTime }" pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate></td>
            <td><fmt:formatDate value="${ts.endTime }" pattern="yyyy-MM-dd HH:mm:ss" ></fmt:formatDate></td>
            <td>
                  <a href="update/${ts.id}">编辑任务</a>&nbsp;&nbsp;
                  <a href="delete/${ts.id}">删除任务</a>&nbsp;&nbsp;
                  <a href="javascript:start('${ts.id }');">启动任务</a>&nbsp;&nbsp;
                  <a href="stop/${ts.id}">停止任务</a>&nbsp;&nbsp;
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
