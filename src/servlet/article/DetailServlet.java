package servlet.article;

import bean.ArticleBean;
import bean.CommentBean;
import dao.ArticleDao;
import dao.ArticleDao;
import dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//显示文章详情的Servlet类
public class DetailServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        if(id==null){
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else {
            ArticleDao articleDAO =new ArticleDao();
            articleDAO.plusOne(id, "watchNum");
            ArrayList<ArticleBean> articleBeans= articleDAO.queryArticle("articleid",id);
            ArrayList<CommentBean> commentBeans= CommentDao.queryAll("articleid",id);
            request.setAttribute("bean", articleBeans.get(0));
            request.setAttribute("commentBeans", commentBeans);
            request.getRequestDispatcher("article.jsp").forward(request, response);
        }
    }
}
