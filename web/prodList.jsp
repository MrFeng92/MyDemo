<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${pageContext.request.contextPath }/css/prodList.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/css/head.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/css/foot.css" rel="stylesheet" type="text/css">

	<style type="text/css">
		#page_div{
			text-align:center;
			margin:12px 0px;
			color:#333;
			font-size:12px;
		}
		#page_div a{
			padding:2px 8px;
			border:1px solid #CCCCCC;
			margin:0px 6px;
			color:#333;
			text-decoration:none;
		}
		#page_div a:hover{
			background:#F00;
			color:#FFFFFF;
		}
		#page_div input{
			width:22px;
			height:22px;
			text-align:center;
			margin:0px 4px 0px 4px;
			border:1px solid #CCCCCC;
			line-height:22px;
			vertical-align:middle;
		}
	</style>
	
	<script type="text/javascript">
		function changePage(obj){
			if(!/^[1-9][0-9]*$/.test(obj.value)){
				alert("页码必须是正整数！");
				obj.value = '${requestScope.pi.thispage}';
				return;
			}
			window.location.href="${requestScope.pi.baseurl}&thispage="+obj.value;
		}
	</script>
</head>
<body>
	<%@ include file="/_head.jsp" %>
	<div id="content">
		<div id="search_div">
			<form method="GET" action="${pageContext.request.contextPath }/servlet/FindProByConditions">
				<input type="hidden" name="thispage" value="1"/>
				<span class="input_span">商品名：<input type="text" name="name" value="${param.name }"/></span>
				<span class="input_span">商品种类：
					<select name="category">
						<option></option>
						<option 
							<c:if test="${param.category == '手机数码' }">
								selected='selected'
							</c:if>
						>手机数码</option>
						<option
							<c:if test="${param.category == '家用电器' }">
								selected='selected'
							</c:if>
						>家用电器</option>
						<option
							<c:if test="${param.category == '日用百货' }">
								selected='selected'
							</c:if>
						>日用百货</option>
						<option
							<c:if test="${param.category == '床上用品' }">
								selected='selected'
							</c:if>
						>床上用品</option>
						<option
							<c:if test="${param.category == '服装服饰' }">
								selected='selected'
							</c:if>
						>服装服饰</option>
						<option
							<c:if test="${param.category == '图书杂志' }">
								selected='selected'
							</c:if>
						>图书杂志</option>
					</select>
				</span>
				<span class="input_span">商品价格区间：<input type="text" name="minprice" value="${param.minprice }"/> - <input type="text" name="maxprice" value="${param.maxprice }"/></span>
				<input type="submit" value="查询">
			</form>
		</div>
		<div id="prod_content">
			<c:forEach items="${requestScope.pi.list }" var="prod" varStatus="stat">
				<div id="prod_div" 
					<c:if test="${stat.count%5==0 }">style="margin-right: 0px;"</c:if>
				>
					<a href="${pageContext.request.contextPath }/servlet/ProdInfoServlet?id=${prod.id}">
						<img style="border: none" src="${pageContext.request.contextPath}/servlet/ImgServlet?id=${prod.id}"></img>
					</a>
					<div id="prod_name_div">
						${prod.name }
					</div>
					<div id="prod_price_div">
						￥${prod.price }元
					</div>
					<div>
						<div id="gotocart_div">
							<a href="#">加入购物车</a>
						</div>					
						<div id="say_div">
							133人评价
						</div>					
					</div>
				</div>
			</c:forEach>
		</div>
		<div style="clear: both"></div>
		<!-- 分页逻辑开始 -->
		<div id="page_div">
			共${requestScope.pi.countrow }条记录,共${requestScope.pi.countpage }页
			<a href="${requestScope.pi.baseurl}&thispage=${pi.firstpage}">首页</a>
			<a href="${requestScope.pi.baseurl}&thispage=${pi.prepage}">上一页</a>
			
			<c:set var="begin" value="0"></c:set>
			<c:set var="end" value="0"></c:set>
			<c:if test="${pi.countpage<=5 }">
				<c:set var="begin" value="1"></c:set>
				<c:set var="end" value="${pi.countpage }"></c:set>
			</c:if>
			<c:if test="${pi.countpage>5 }">
				<c:choose>
					<c:when test="${pi.thispage<=3 }">
						<c:set var="begin" value="1"></c:set>
						<c:set var="end" value="5"></c:set>
					</c:when>
					<c:when test="${pi.thispage>=pi.countpage-2 }">
						<c:set var="begin" value="${pi.countpage-4 }"></c:set>
						<c:set var="end" value="${pi.countpage }"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="begin" value="${pi.thispage-2 }"></c:set>
						<c:set var="end" value="${pi.thispage+2 }"></c:set>
					</c:otherwise>
				</c:choose>
			</c:if>
			
			<c:forEach begin="${begin }" end="${end }" step="1" var="i">
				<c:if test="${i == pi.thispage }">${i }</c:if>
				<c:if test="${i != pi.thispage }">
					<a href="${requestScope.pi.baseurl}&thispage=${i}">${i }</a>
				</c:if>
			</c:forEach>
			
			<a href="${requestScope.pi.baseurl}&thispage=${pi.nextpage}">下一页</a>
			<a href="${requestScope.pi.baseurl}&thispage=${pi.lastpage}">尾页</a>
			 跳转到<input type="text" value="${requestScope.pi.thispage }" onchange="changePage(this)"/>页
		</div>	
		<!-- 分页逻辑结束 -->
	</div>
	<div style="clear: both"></div>
	<%@ include file="/_foot.jsp" %>
</body>
</html>
