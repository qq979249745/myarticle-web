<%@ page import="bean.ArticleBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.ArticleDao" %>
<%@ page import="static util.Util.getTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的个人网站</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body >
    <div class="content_search">
        <form class="search_form" action="search">
            <input class="search_input" type="text" name="search" value="">
            <input class="search_btn" type="submit" value="搜索">
        </form>
    </div>
    <div class="content_article">
        <table border="1" class="table">
            <tbody>
            <tr>
                <td>标题</td>
                <td>发布时间</td>
                <td>浏览数</td>
                <td>评论数</td>
                <td>点赞数</td>
                <td>发布人</td>
            </tr>
            <%
                String pages=request.getParameter("pages");
                String search=request.getParameter("search");
                ArrayList<ArticleBean> beans= (ArrayList<ArticleBean>) request.getAttribute("beans");
                ArticleDao articleDAO = new ArticleDao();
                if(pages==null){
                    pages="1";
                }
                if(null==beans){
                    beans= articleDAO.queryArticleAll("submitTime",0);
                }
                for(int i=0;i<beans.size();i++){
                    out.print("<tr><td><span><span class='c-index'>"+(i+1)+"</span>");
                    out.print("<a  title='"+beans.get(i).getTitle()+"' href='detail?id="+beans.get(i).getId()+"'>"+beans.get(i).getTitle()+"</a></span></td>");
                    out.print("<td >"+getTime(beans.get(i).getSubmitTime())+"</td>");
                    out.print("<td >"+beans.get(i).getWatchNum()+"</td>");
                    out.print("<td >"+beans.get(i).getCommentNum()+"</td>");
                    out.print("<td >"+beans.get(i).getPraiseNum()+"</td>");
                    out.print("<td >"+beans.get(i).getUserName()+"</td><tr>");
                }
                out.print("<tr><td colspan='4'><span>");
                int currentPage=Integer.valueOf(pages);
                int p= (int) Math.ceil(articleDAO.getArticleCount("title",search)/10.0);//向上取整
                for(int i=1;i<=p;i++){
                    if(i==currentPage){
                        out.print("<span style='margin-left:8px'>"+i+"</span>");
                    }else {
                        out.print("<span><a href='nextPage?pages="+i+"'>"+i+"</a></span>");
                    }
                }
                out.print("</span></td>");
                out.print("<td colspan='2'><a href='addArticle'>我要发布文章</a></td></tr>");
            %>
            </tbody>
        </table>
    </div>
</body>
</html>
