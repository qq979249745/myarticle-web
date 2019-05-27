<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的评论</title>
    <link href="css/mycomment.css" rel="stylesheet" type="text/css">
</head>
<body>
<div style="overflow: hidden">
    <div  style="width: 102%; overflow-y:auto;overflow-x:hidden;height: 100%" >
<%
    ArrayList<HashMap<String, String>> arrayList = (ArrayList<HashMap<String, String>>) request.getAttribute("arrayList");
    if(arrayList.size()==0){%>
<div>还没有评论过一篇文章，<a href="search.jsp">前往评论！</a></div>
    <%}else {
    for (int i=0;i<arrayList.size();i++){%>
<div class="article-list-item">
    <div ><a class="comment" href="detail?id=<%=arrayList.get(i).get("articleid")%>">你的评论是：<%=arrayList.get(i).get("comment_content")%></a></div>
    <div >
        <div class="title">评论的文章：<%=arrayList.get(i).get("title")%></div>
        <div class="item-info">
            <span>时间 <%=Util.getTime(Long.valueOf(arrayList.get(i).get("comment_time")))%></span>
            <span><img src="images/查看.png"><%=arrayList.get(i).get("watchNum")%></span>
            <span><img src="images/赞.png"> <%=arrayList.get(i).get("praiseNum")%></span>
            <span><img src="images/评论.png"> <%=arrayList.get(i).get("commentNum")%></span>
        </div>
    </div>
</div>
    <%}}
%>
    </div>
    </div>
</body>
</html>
