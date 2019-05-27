package servlet.article;

import bean.ArticleBean;
import bean.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//添加文章的Servlet类
@WebServlet("/addArticle")
public class AddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean= (UserBean) req.getSession().getAttribute("userBean");
        ArticleBean bean=new ArticleBean(-1,"","",0,0,0,0,userBean.getUserName());
        req.setAttribute("bean", bean);
        req.getRequestDispatcher("article.jsp").forward(req, resp);
    }
}
