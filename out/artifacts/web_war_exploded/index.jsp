<%@ page import="bean.ArticleBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.ArticleDao" %>
<%@ page import="static util.Util.getTime" %>
<%@ page import="bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script language="JavaScript">

  if (window.location.href ==top.location.href+"index.jsp")
    top.location.href = top.location.href;
</script>
  <head>
    <title>我的个人网站</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script type="text/javascript" src="js/script.js" charset="utf-8"></script>
  </head>
  <body class="body">

  <div class="topbar">
    <div class="topbar_left">欢迎来到我的个人网站</div>
    <div class="topbar_right">
      <div id="time" class="time"></div>
      <audio class="music" src="music/bgmusic.mp3" autoplay loop controls></audio>
    </div>
    <div class="index">
      <a onclick="index()">首页</a>
    </div>
    <div class="login">
    <a onclick="login()">登录</a>
    <a onclick="register()">注册</a>
    </div>
    <div class="userLogin"><%
      UserBean userBean= (UserBean) session.getAttribute("userBean");
      if(userBean==null){
    %><script>document.getElementsByClassName("userLogin")[0].style.display="none";</script><%
    }else {%>
      <script>document.getElementsByClassName("login")[0].style.display="none";</script>
      <div class="loginCenter">
        <img class="login_img" src="displayImg?userName=<%=userBean.getUserName()%>">
        <span><%=userBean.getUserName()%></span>
      </div>
      <div class="userControl">
        <a onclick="myarticle('submit')">我的发布</a>
        <a onclick="myarticle('comment')">我的评论</a>
        <a onclick="modify()">我的资料</a>
        <a onclick="logout()">退出注销</a>
      </div>
      <% }%>
  </div>
  </div>
  <div class="content">
    <iframe frameborder="0" scrolling="no" style="width: 100%;height: 100%;" src="search.jsp">
    </iframe>
  <div class="footer">
    <div>Copyright © 2018 All Rights Reserved 软件学院16201533版权所有</div>
  </div>
  </body>
</html>
