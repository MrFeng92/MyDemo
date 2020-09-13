<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
  <style type="text/css">
		body {
			background-color: whitesmoke;
			text-align: center;
		}
		table {
			text-align: center;
		}
		th{
			background-color: silver;
		}
  </style>
  </head>
  <body>
  	<h1>销售榜单管理</h1>
  	<a href="${pageContext.request.contextPath }/servlet/ManageDownloadSaleListServlet">下载销售榜单</a>
  	<hr>
  	<table align="center" bordercolor="black" border="1" width="90%" cellspacing="0px" cellpadding="5px">
  		<tr>
  			<th>商品id</th>
  			<th>商品名称</th>
  			<th>销售总量</th>
  		</tr>
  		<c:forEach items="${requestScope.list }" var="si">
  			<tr>
  				<td>${si.prod_id }</td>
  				<td>${si.prod_name }</td>
  				<td>${si.sale_num }</td>
  			</tr>
  		</c:forEach>
  	</table>
  </body>
</html>
