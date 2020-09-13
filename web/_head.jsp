<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="common_head">
	<div id="line1">
		<div id="content">
			<c:if test="${sessionScope.user == null }">
				<a href="${pageContext.request.contextPath}/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/regist.jsp">注册</a>
		
			</c:if>
			<c:if test="${sessionScope.user != null }">
				欢迎回来！${sessionScope.user.username }|
				<a href="${pageContext.request.contextPath }/backend/manage.jsp">后台</a> 
				 <a href="${pageContext.request.contextPath}/servlet/LogoutServlet">注销</a>
			</c:if>
		
		</div>
	</div>
	<div id="line2">
		<img id="logo" src="${pageContext.request.contextPath }/img/head/logo.jpg"/>
		<input type="text" name=""/>
		<input type="button" value="搜索"/>
		<span id="goto">
			<a id="goto_order" href="${pageContext.request.contextPath }/servlet/OrderListServlet">我的订单</a>
			<a id="goto_cart" href="${pageContext.request.contextPath}/cart.jsp">我的购物车</a>
		</span>
		<img id="erwm" src="${pageContext.request.contextPath }/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/ProdListServlet?thispage=1">全部商品</a></li>
				<li><a href="#">手机数码</a></li>
				<li><a href="#">电脑平板</a></li>
				<li><a href="#">家用电器</a></li>
				<li><a href="#">汽车用品</a></li>
				<li><a href="#">食品饮料</a></li>
				<li><a href="#">图书杂志</a></li>
				<li><a href="#">服装服饰</a></li>
				<li><a href="#">理财产品</a></li>
			</ul>
		</div>
	</div>
</div>