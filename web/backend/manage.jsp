<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
  </head>
	<frameset rows="10%,90%">
		<frame src="${pageContext.request.contextPath }/backend/_top.jsp" noresize="noresize" frameborder="0"/>
		<frameset cols="10%,90%">
			<frame src="${pageContext.request.contextPath }/backend/_left.jsp" noresize="noresize" frameborder="0"/>
			<frame src="${pageContext.request.contextPath }/backend/_right.jsp" noresize="noresize" frameborder="0" name="right_frame"/>
		</frameset>
	</frameset>
  <body>
  </body>
</html>
