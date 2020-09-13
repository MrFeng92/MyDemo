<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
  	<title>商品列表页面</title>
  	<style type="text/css">
		body{
			text-align: center;
		}  	
		th{
			background-color: #C6C6C6;
		}
  	</style>
  	<script type="text/javascript">
  		function changeNum(id,newNum){
  			window.location.href="${pageContext.request.contextPath}/servlet/ManageProductUpdate?id="+id+"&newNum="+newNum;
  		}
  	</script>
  </head>
  <body>
  	<h1>商品管理</h1>
  	<a href="${pageContext.request.contextPath }/backend/manageAddProd.jsp">添加商品</a>
  	<hr>
		<table align="center" border="1px" borderColor="#000000" cellspacing="0px" cellpadding="8px" >
			<tr>
				<th>商品图片</th>
				<th>商品id</th>
				<th>商品名称</th>
				<th>商品种类</th>
				<th>商品单价</th>
				<th>库存数量</th>
				<th>描述信息</th>
			</tr>
			<c:forEach items="${requestScope.list }" var="prod">
				<tr>
					<td><img width="120px" height="120px" src="${pageContext.request.contextPath }/servlet/ImgServlet?id=${prod.id}"/></td>
					<td>${prod.id }</td>
					<td>${prod.name }</td>
					<td>${prod.category }</td>
					<td>${prod.price }</td>
					<td><input style="width:50px;" type="text" value="${prod.pnum }" onchange="changeNum('${prod.id }',this.value)"/></td>
					<td>${prod.description }</td>
				</tr>
			</c:forEach>
		</table>
  	<hr>
  </body>
</html>
