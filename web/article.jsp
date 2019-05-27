<%@ page import="bean.ArticleBean" %>
<%@ page import="bean.UserBean" %>
<%@ page import="bean.CommentBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        ArticleBean bean= (ArticleBean) request.getAttribute("bean");
        if(bean==null){
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    %>
    <title><%=bean.getTitle()%></title>
    <link href="css/article.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
        function submit() {
            var title=document.getElementsByTagName("textarea")[0];
            var content=document.getElementsByTagName("textarea")[1];
            if(title.value==""||content.value==""){
                alert("标题或内容不能为空！")
            }else
            document.getElementById("formId").submit();
        }
        function edit() {
            var me=document.getElementById("edit");
            var title=document.getElementsByTagName("textarea")[0];
            var content=document.getElementsByTagName("textarea")[1];
            var h1=document.getElementsByTagName("h1")[0];
            var div=document.getElementsByClassName("table_div")[0];
            var del=document.getElementById("delete");
            if("修改"==me.innerText){
                me.innerText="取消";
                h1.style.display="none";
                title.style.display="block";
                title.value=h1.innerText;
                title.style.fontSize="40px";
                div.style.display="none";
                content.style.display="block";
                content.value=div.innerText;
                del.innerText="保存";
                del.href="javascript:submit()";
            }else {
                me.innerText="修改";
                title.style.display="none";
                content.style.display="none";
                h1.style.display="block";
                div.style.display="block";
                del.innerText="删除";
                del.href="deleteArticle?id=<%=bean.getId()%>";
            }
        }
        function comment() {
            var dom=document.getElementsByClassName("comment-content")[0];
            if(dom.value==""){
                alert("请输入评论")
            }else
                document.getElementById("comment").submit();
        }
    </script>
</head>
<body class="body">
<div style="overflow: hidden">
    <div  style="width: 102%; overflow-y:auto;overflow-x:hidden;height: 100%" >
    <form id="formId" action="updateArticle?id=<%=bean.getId()%>" method="post">
    <table class="table" border="1">
        <tr>
            <td colspan="2">
                <h1><%=bean.getTitle()%></h1>
                <textarea class="table_h1" name="title" placeholder="请输入题目" maxlength="25"></textarea>
            </td>
        </tr>
        <%
            UserBean userBean= (UserBean) session.getAttribute("userBean");
            if(bean.getId()!=-1){%>
        <tr>
            <td colspan="2" class="table_td">
                <div>
                    <span class="user">
                    <%=bean.getUserName()+"发布于    "+Util.getTime(bean.getSubmitTime())%>
                </span>
                    <span class="table_span">
                    <img src="images/查看.png"><%=bean.getWatchNum()%>
                    <img src="images/评论.png"><%=bean.getCommentNum()%>
                    <a href="praise?id=<%=bean.getId()%>"><img src="images/赞.png"></a><%=bean.getPraiseNum()%>
                </span>
                </div>
                <div class="article_copyright">
                    版权声明：本文为作者原创文章，未经作者允许不得转载。
                </div>
            </td>
        </tr>
            <%}
        %>

        <tr>
            <td colspan="2">
                <div class="table_div"><%=bean.getContent()%></div>
                <textarea class="table_textarea" name="content" placeholder="请输入文章内容"></textarea>
            </td>
        </tr>
        <%
            if(userBean!=null&&userBean.getUserName().equals(bean.getUserName())){%>
        <tr>
            <td >
                <div id="editAndDel"><a id="edit" href="javascript:edit()">修改</a></div>
            </td>
            <td >
                <div><a id="delete" href="deleteArticle?id=<%=bean.getId()%>">删除</a></div>
            </td><%
            if(bean.getId()==-1){%>
            <script type="text/javascript">
                edit();
                document.getElementById("editAndDel").style.display="none";
            </script>
            <%}%>
        </tr>
            <%}
        %>
    </table>
    </form>
    <%
    if(bean.getId()!=-1){%>
<form id="comment" style="height: 140px;" action="insertcom?id=<%=bean.getId()%>" method="post">
    <img class="user-img" src="displayImg?userName=<%=userBean==null?"":userBean.getUserName()%>">
    <textarea class="comment-content" name="content" placeholder="想对作者说点什么"></textarea>
    <input type="button"  class="btn-comment" onclick="comment()" value="发表评论">
</form>

<%}
        ArrayList<CommentBean> commentBeans= (ArrayList<CommentBean>) request.getAttribute("commentBeans");
        if(commentBeans!=null)
        for(int i=0;i<commentBeans.size();i++){%>
<div class="comment">
    <div class="comment_icon">
        <img src="displayImg?userName=<%=commentBeans.get(i).getUserName()%>">
        <div><%=commentBeans.get(i).getUserName()%>说：</div>
    </div>
    <div class="comment_content">
        <div class="comment_content1"><%=commentBeans.get(i).getContent()%></div>
        <div class="comment_time">#<%=i+1%>楼  <%=Util.getTimeNos(commentBeans.get(i).getSubmitTime())%> </div>
    </div>
</div>
        <%}
    %>
    </div></div>
</body>
</html>
