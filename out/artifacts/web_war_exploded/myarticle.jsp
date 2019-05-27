<%@ page import="bean.ArticleBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/myarticle.css" type="text/css">
</head>
<body>
<div style="overflow: hidden">
    <div  style="width: 102%; overflow-y:auto;overflow-x:hidden;height: 100%" >
<%
    ArrayList<ArticleBean> articleBeans= (ArrayList<ArticleBean>) request.getAttribute("articleBeans");
    if(articleBeans.size()==0){
        %><div>还有没一篇文章，<a href="addArticle">前往发布！</a></div><%
    }
    for(int i=0;i<articleBeans.size();i++){%>
<div class="article-list-item">
    <div ><a class="title" href="detail?id=<%=articleBeans.get(i).getId()%>"><%=articleBeans.get(i).getTitle()%></a></div>
    <div >
        <div class="item-info">
            <span>时间 <%=Util.getTime(articleBeans.get(i).getSubmitTime())%></span>
            <span><img src="images/查看.png"><%=articleBeans.get(i).getWatchNum()%></span>
            <span><img src="images/赞.png"> <%=articleBeans.get(i).getPraiseNum()%></span>
            <span><img src="images/评论.png"> <%=articleBeans.get(i).getCommentNum()%></span>
        </div>
    </div>
</div>
    <%}
%>
    </div>
    </div>
</body>
</html>
