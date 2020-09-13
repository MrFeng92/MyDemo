<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.tarena.com.cn/easyTag" prefix="easyTag" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>欢迎注册EasyMall</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/regist.css"/>
    <script type="text/javascript">
        function changeValiImg(obj) {
            obj.src = "${pageContext.request.contextPath}/servlet/ValiImgServlet?time=" + new Date().getTime();
        }


        function checkForm() {
            var canSub = true;
            //1.非空校验
            canSub = checkNull("username", "用户名不能为空！") && canSub;
            canSub = checkNull("nickname", "昵称不能为空！") && canSub;
            canSub = checkNull("email", "邮箱不能为空！") && canSub;
            canSub = checkNull("valistr", "验证码不能为空！") && canSub;

            //2.两次密码不一致
            canSub = checkPsw() && canSub;

            //3.邮箱格式
            canSub = checkEmail() && canSub;

            return canSub;
        }

        function checkUserName() {
            var username = document.getElementsByName("username")[0].value;
            if (checkNull("username", "用户名不能为空！") == false) return false;
            //发送AJAX请求检验用户名是否已经存在
            //--创建XMLHttpReqeust对象
            var xhr = ajaxFunction();
            //--设置监听
            var data = null;
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200 || xhr.status == 304) {
                        data = xhr.responseText;
                        if (data == 'true') {
                            setMsg("username", "用户名已经存在！");
                        }
                    }
                }
            }
            //--打开链接
            xhr.open("POST", "${pageContext.request.contextPath}/servlet/AJAXHasUserNameServlet", true);
            //--发送请求
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");//通知服务器当前发送的数据时表单数据，请将实体内容中的值当做请求参数来处
            xhr.send("username=" + encodeURI(username));
            return true;
        }


        function checkPsw() {
            var psw1 = document.getElementsByName("password")[0].value;
            var psw2 = document.getElementsByName("password2")[0].value;
            if (checkNull("password", "密码不能为空！") == false & checkNull("password2", "确认密码不能为空！") == false) return false;
            if (psw1 != psw2) {
                setMsg("password2", "两次密码不一致！");
                return false;
            }
            return true;
        }

        function checkEmail() {
            var email = document.getElementsByName("email")[0].value;
            if (checkNull("email", "邮箱不能为空！") == false) return false;
            if (!/^\w+@\w+(\.\w+)+$/.test(email)) {
                setMsg("email", "邮箱格式不正确！");
                return false;
            }
            return true;
        }

        function checkNull(name, msg) {
            setMsg(name, "");
            var obj = document.getElementsByName(name)[0];
            if (obj.value == "") {
                setMsg(name, "<font color='red'>" + msg + "</font>");
                return false;
            }
            return true;
        }

        function setMsg(name, msg) {
            document.getElementById(name + "_msg").innerHTML = msg;
        }
    </script>
</head>
<body>
<h1>欢迎注册EasyMall</h1>
<form action="${pageContext.request.contextPath}/servlet/RegistServlet" method="POST" onsubmit="return checkForm();">
    <easyTag:noReSub/>
    <table>
        <tr>
            <td colspan="2" align="center">
                <font color="red">${requestScope.msg}</font>
            </td>
        </tr>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username" value="${param.username }" onblur="checkUserName()"/>
                <span id="username_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password" onblur="checkNull('password','密码不能为空！')">
                <span id="password_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2" onblur="checkPsw()">
                <span id="password2_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">昵称：</td>
            <td>
                <input type="text" name="nickname" value="${param.nickname }" onblur="checkNull('nickname','昵称不能为空！')"/>
                <span id="nickname_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email" value="${param.email }" onblur="checkEmail()"/>
                <span id="email_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr" onblur="checkNull('valistr','验证码不能为空！')"><img id="yzm_img"
                                                                                                src="${pageContext.request.contextPath}/servlet/ValiImgServlet"
                                                                                                style="cursor: pointer"
                                                                                                onclick="changeValiImg(this)"/>
                <span id="valistr_msg"></span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="注册用户"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
