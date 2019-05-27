<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎登录</title>
</head>
<body>
<div style="text-align: right;margin: 80px 80px 0 0;">
    还没有账号？<a style="color: darkblue" href="register.jsp">立即注册</a>
</div>
    <form method="post" action="login">
    <table style="margin: 20px auto;">
        <tr>
            <td>用户名：</td>
            <td><input name="userName"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input name="password"></td>
        </tr>
        <tr>
            <td><button type="reset">重输</button></td>
            <td><button type="submit">登录</button></td>
        </tr>
    </table>
    </form>
</body>
</html>
