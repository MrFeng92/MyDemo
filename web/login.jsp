<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
		<title>EasyMall欢迎您登陆</title>
		<script type="text/javascript">
			window.onload = function(){
				document.getElementsByName("username")[0].value = decodeURI('${cookie.remname.value }');
			}
		</script>
	</head>
	<body>
		<h1>欢迎登陆EasyMall</h1>
		<form action="${pageContext.request.contextPath}/servlet/LoginServlet" method="POST">
			<table>
				<tr>
					<td colspan="2" align="center"><font color="red">${requestScope.msg }</font></td>
				</tr>
				<tr>
					<td class="tdx">用户名:</td>
					<td><input type="text" name="username"/></td>
				</tr>
				<tr>
					<td class="tdx">密码:</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="remname" value="true" 
							<c:if test="${cookie.remname!=null }">checked='checked'</c:if>
						/>记住用户名
						<input type="checkbox" name="autologin" value="true"/>30天内自动登陆
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="登陆"/>
					</td>
				</tr>
			</table>
		</form>		
	</body>
</html>
