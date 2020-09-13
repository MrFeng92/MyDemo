<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
	<title>添加商品</title>
  </head>
  <body>
  <h1 style="text-align: center">添加商品</h1>
  	<hr>
  		<form action="${pageContext.request.contextPath }/servlet/ManageAddProduct" method="POST" enctype="multipart/form-data">
  			<table align="center" border="1px" cellspacing="0px" cellpadding="4px">
  				<tr>
  					<td>商品名称</td>
  					<td><input type="text" name="name"/></td>
  				</tr>
  				<tr>
  					<td>商品单价</td>
  					<td><input type="text" name="price"/></td>
  				</tr>
  				<tr>
  					<td>商品种类</td>
  					<td>
  						<select name="category">
  							<option>手机数码</option>
  							<option>家用电器</option>
  							<option>日用百货</option>
  							<option>床上用品</option>
  							<option>服装服饰</option>
  							<option>图书杂志</option>
  						</select>
  					</td>
  				</tr>
  				<tr>
  					<td>库存数量</td>
  					<td><input type="text" name="pnum"/></td>
  				</tr>
  				<tr>
  					<td>商品图片</td>
  					<td><input type="file" name="fx"/></td>
  				</tr>
  				<tr>
  					<td>描述信息</td>
  					<td><textarea name="description" rows="5" cols="30"></textarea></td>
  				</tr>
  				<tr>
  					<td colspan="2"><input type="submit" value="添加商品"/></td>
  				</tr>
  			</table>
  		</form>
  	<hr>
  </body>
</html>
