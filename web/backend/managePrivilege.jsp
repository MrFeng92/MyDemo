<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
	<title>权限管理</title>
	<style type="text/css">
		#warp{
			width:800px;
			margin: 0px auto;
		}
		#warp select{
			margin-left: 0px;
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
	<script type="text/javascript">
		function changeSelc(selc){
			var resources = document.getElementById("resources");
			var role_id = selc.value;
			if(role_id==0){
				return;
			}
		
			//发送ajax请求 ，根据选择的角色，查询该角色对应的管理的资源路径
			//--创建XMLHttpReqeust对象
			var xhr = ajaxFunction();
			//--设置监听
			var data = null;
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					if(xhr.status==200||xhr.status==304){
						resources.value = "";
						data = xhr.responseText;
						var arr = eval(data);
						for(var i=0;i<arr.length;i++){
							resources.value += arr[i]+"\r\n";
						}
					}
				}
			}
			//--打开链接
			xhr.open("POST","${pageContext.request.contextPath}/servlet/AJAXGetResourceByRoleServlet",true);
			//--发送请求
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");//通知服务器当前发送的数据时表单数据，请将实体内容中的值当做请求参数来处
			xhr.send("role_id="+role_id);
		}
		function updatePrivilege(obj){
			var path = obj.value;
			var role_id = document.getElementsByTagName("select")[0].value;
			var msg_div = document.getElementById("msg_div");
			//发送ajax请求 ，修改角色管理的资源
			//--创建XMLHttpReqeust对象
			var xhr = ajaxFunction();
			//--设置监听
			var data = null;
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					if(xhr.status==200||xhr.status==304){
						data = xhr.responseText;
						if(data=='true'){
							msg_div.innerHTML="<font color='red'>更新成功！</font>";
							window.setTimeout(function(){
								msg_div.innerHTML="";
							},500)
						}
					}
				}
			}
			//--打开链接
			xhr.open("POST","${pageContext.request.contextPath}/servlet/AJAXUpdatePrivilegeServlet",true);
			//--发送请求
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");//通知服务器当前发送的数据时表单数据，请将实体内容中的值当做请求参数来处
			xhr.send("role_id="+role_id+"&path="+path);
		}
	</script>
  </head>
  <body>
  <h1 style="text-align: center">权限管理</h1>
  	<div id="msg_div" align="center"></div>
  	<hr>
  		<div id="warp">
	  		<select onchange="changeSelc(this)">
	  			<option value="0">--选择角色--</option>
				<c:forEach items="${list }" var="role">
		  			<option value="${role.id }">${role.name }</option>
				</c:forEach>
	  		</select>
	  		<br>
	  		<textarea id="resources" rows="20" cols="80" onchange="updatePrivilege(this)"></textarea>
  		</div>
  	<hr>
  </body>
</html>
