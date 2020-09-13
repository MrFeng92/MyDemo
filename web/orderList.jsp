<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${pageContext.request.contextPath }/css/orderList.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/css/head.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/css/foot.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@include file="_head.jsp" %>
	<div id="warp">
		<c:forEach items="${requestScope.list }" var="oi">
			<dl class="Order_information">
				<dt>
					<h3>订单信息</h3>
				</dt>
				<dd>
					订单编号：${oi.order.id }<br />
					 下单时间：${oi.order.ordertime }<br /> 
					 订单金额：${oi.order.money }<br /> 
					 支付状态：
					 	<c:if test="${oi.order.paystate == 0 }">
							<font color="red">未支付</font>&nbsp;&nbsp;&nbsp;
							<a href="${pageContext.request.contextPath }/servlet/DelOrderServlet?id=${oi.order.id}"><img src="${pageContext.request.contextPath }/img/orderList/sc.jpg" width="69" height="19"></a> 
					 		<a href="${pageContext.request.contextPath }/pay.jsp?id=${oi.order.id}&money=${oi.order.money}"> <img src="${pageContext.request.contextPath }/img/orderList/zx.jpg" width="69" height="19"></a><br />
				 		</c:if> 
					 	<c:if test="${oi.order.paystate == 1 }">
							<font color="blue">已支付</font>
				 		</c:if> 
					 所属用户：${oi.user.username }<br/> 
					 收货地址：zz${oi.order.receiverinfo }<br/> 
					支付方式：在线支付
				</dd>
			</dl>
		
			<table width="1200" border="0" cellpadding="0"
				cellspacing="1" style="background:#d8d8d8;color:#333333">
				<tr>
					<th width="276" height="30" align="center" valign="middle" bgcolor="#f3f3f3">商品图片</th>
					<th width="247" align="center" valign="middle" bgcolor="#f3f3f3">商品名称</th>
					<th width="231" align="center" valign="middle" bgcolor="#f3f3f3">商品单价</th>
					<th width="214" align="center" valign="middle" bgcolor="#f3f3f3">购买数量</th>
					<th width="232" align="center" valign="middle" bgcolor="#f3f3f3">总价</th>
				</tr>
				<c:forEach items="${oi.map }" var="entry">
					<tr>
						<td align="center" valign="middle" bgcolor="#FFFFFF">
							<img src="${pageContext.request.contextPath }/servlet/ImgServlet?id=${entry.key.id}" width="90" height="105">
						</td>
						<td align="center" valign="middle" bgcolor="#FFFFFF">${entry.key.name }</td>
						<td align="center" valign="middle" bgcolor="#FFFFFF">${entry.key.price }元</td>
						<td align="center" valign="middle" bgcolor="#FFFFFF">${entry.value }件</td>
						<td align="center" valign="middle" bgcolor="#FFFFFF">${entry.key.price * entry.value }元</td>
					</tr>
				</c:forEach>
			</table>
			<div class="Order_price">${oi.order.money }元</div>
		</c:forEach>
	</div>
	<%@include file="_foot.jsp" %>
</body>
</html>
