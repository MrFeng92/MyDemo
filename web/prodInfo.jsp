<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="${pageContext.request.contextPath }/css/prodInfo.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/foot.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<script type="text/javascript">
		function delNum(){
			var buyNumInp = document.getElementById("buyNumInp");
			buyNumInp.value = buyNumInp.value-1 <= 0 ? 1 : buyNumInp.value-1;
		}
		function addNum(){
			var buyNumInp = document.getElementById("buyNumInp");
			buyNumInp.value = parseInt(buyNumInp.value)+1;
		}
		function checkNum(obj){
			if(!/^[1-9][0-9]*$/.test(obj.value)){
				//alert("只能是正整数！");
				obj.value = 1;
			}
		}
		function addCart(){
			var buynum= document.getElementById("buyNumInp").value;
			window.location.href="${pageContext.request.contextPath}/servlet/AddCartServlet?id=${prod.id}&buynum="+buynum;
		}
	</script>
</head>
<body>
	<%@ include file="/_head.jsp" %>
	<div id="warp">
		<div id="left">
			<div id="left_top">
				<img src="${pageContext.request.contextPath }/servlet/ImgServlet?id=${prod.id}"/>
			</div>
			<div id="left_bottom">
				<img id="lf_img" src="${pageContext.request.contextPath }/img/prodInfo/lf.jpg"/>
				<img id="mid_img" src="${pageContext.request.contextPath }/servlet/ImgServlet?id=${prod.id}" width="60px" height="60px"/>
				<img id="rt_img" src="${pageContext.request.contextPath }/img/prodInfo/rt.jpg"/>
			</div>
		</div>
		<div id="right">
			<div id="right_top">
				<span id="prod_name">${prod.name } <br/></span>
				<br>
				<span id="prod_desc">${prod.description }<br/></span>
			</div>
			<div id="right_middle">
				<span id="right_middle_span">
						EasyMall 价：<span class="price_red">￥${prod.price }<br/>
			            运     费：满 100 免运费<br />
			            服     务：由EasyMall负责发货，并提供售后服务<br />
			            购买数量：
	            <a href="javascript:void(0)" id="delNum" onclick="delNum()">-</a>
	            <input id="buyNumInp" name="" type="text" value="1" onkeyup="checkNum(this)">
		        <a href="javascript:void(0)" id="addNum" onclick="addNum()">+</a>
			</div>
			<div id="right_bottom">
				<input class="add_cart_but"  type="button" value="加入购物车" onclick="addCart()" style="cursor: pointer"/>	
			</div>
		</div>
	</div>
	<%@ include file="/_foot.jsp" %>
</body>
</html>