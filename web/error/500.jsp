<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<style type="text/css">
			{
			border: 1px solid red;
			}
			body{
				background:url(${pageContext.request.contextPath}/img/error/500.jpg) top center no-repeat;
				text-align:center;
			}
			#wrong {
				height:500px;
				width:800px;
				margin:200px auto 0px;
				text-align: left;
				width: 820px;
				border: solid 1px red;
			}
			#wrong div{
				height:500px;
			}
			#wrong h1 {
				border-bottom: 1px solid #999;
				padding-bottom: 12px;
				margin-bottom: 12px;
				font-size: 26px;
			}
		</style>
	</head>
	<body>
		<div id="wrong">
			<h1>${pageContext.exception.message }</h1>
			<div>
				<c:forEach items="${pageContext.exception.stackTrace }" var="ste">
					${ste }
				</c:forEach>
			</div>
		</div>
	</body>
</html>

