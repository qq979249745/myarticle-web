<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎注册</title>
</head>
<body >
<div style="text-align: right;margin: 80px 80px 0 0;">
    已有账号？<a style="color: darkblue" href="login.jsp">立即登录</a>
</div>
<form action="register" method="post">
    <table style="margin: 20px auto;">
        <tr>
            <td>用户名：</td>
            <td><input name="userName" placeholder="6-12个字符"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input name="password" placeholder="6-12个字符"></td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input name="passwordConfirm" placeholder="6-12个字符"></td>
        </tr>
        <tr>
            <td><button type="reset">重输</button></td>
            <td><button type="submit">注册</button></td>
        </tr>
    </table>
</form>
</body>
</html>
