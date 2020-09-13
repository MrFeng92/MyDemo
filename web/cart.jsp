<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<link href="${pageContext.request.contextPath }/css/cart.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/foot.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
		<script type="text/javascript">
			function delNum(id,oldNum){
				if(oldNum>1){
					//数量-1
					window.location.href="${pageContext.request.contextPath}/servlet/UpdateCartServlet?id="+id+"&buynum="+(oldNum-1);
					return;
				}else{
					//提示用户是否删除
					if(confirm("确定要删除吗？")){
						//删除次商品
						window.location.href="${pageContext.request.contextPath}/servlet/DelCartServlet?id="+id;
					}
				}
			}
			function addNum(id,oldNum){
				//数量+1
				window.location.href="${pageContext.request.contextPath}/servlet/UpdateCartServlet?id="+id+"&buynum="+(parseInt(oldNum)+1);
			}
			function checkNum(id,obj,oldNum){
				if(!/^[1-9][0-9]*$/.test(obj.value)){
					//alert("只能是正整数！");
					obj.value = oldNum;
				}else{
					window.location.href="${pageContext.request.contextPath}/servlet/UpdateCartServlet?id="+id+"&buynum="+obj.value;
				}
			
			}
		</script>
	</head>
	<body>
		<%@ include file="_head.jsp" %>
		<div id="warp">
			<!-- 标题信息 -->
			<div id="title">
				<input name="allC" type="checkbox" value="" onclick=""/>
				<span id="title_checkall_text">全选</span>
				<span id="title_name">商品</span>
				<span id="title_price">单价（元）</span>
				<span id="title_buynum">数量</span>
				<span id="title_money">小计（元）</span>
				<span id="title_del">操作</span>
			</div>
			<!-- 购物信息 -->
			<c:set var="money" value="0"></c:set>
			<c:forEach items="${sessionScope.cartmap }" var="entry">
				<div id="prods">
					<input name="prodC" type="checkbox" value="" onclick=""/>
					<img src="${pageContext.request.contextPath }/servlet/ImgServlet?id=${entry.key.id}" width="90" height="90" />
					<span id="prods_name">${entry.key.name }</span>
					<span id="prods_price">${entry.key.price }</span>
					<span id="prods_buynum"> 
						<a href="javascript:void(0)" id="delNum" onclick="delNum('${entry.key.id}','${entry.value}')" >-</a>
						<input id="buyNumInp" type="text" value="${entry.value }" onchange="checkNum('${entry.key.id }',this,'${entry.value }')">
						<a href="javascript:void(0)" id="addNum" onclick="addNum('${entry.key.id}','${entry.value}')">+</a>
					</span>
					<span id="prods_money">${entry.key.price * entry.value }</span>
					<span id="prods_del"><a href="${pageContext.request.contextPath }/servlet/DelCartServlet?id=${entry.key.id}">删除</a></span>
				</div>
				<c:set var="money" value="${money +  entry.key.price * entry.value}"></c:set>
			</c:forEach>
			<!-- 总计条 -->
			<div id="total">
				<div id="total_1">
					<input name="allC" type="checkbox" value=""/> 
					<span>全选</span>
					<a id="del_a" href="#">删除选中的商品</a>
					<span id="span_1">总价：</span>
					<span id="span_2">￥${money }</span>
				</div>
				<div id="total_2">	
					<a id="goto_order" href="${pageContext.request.contextPath }/addOrder.jsp">去结算</a>
				</div>
			</div>
		</div>
		<div style="clear:both;"></div>
		<%@ include file="_foot.jsp" %>
	</body>
</html>