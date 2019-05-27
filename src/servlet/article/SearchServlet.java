package servlet.article;

import bean.ArticleBean;
import dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
//根据search对文章进行搜索的Servlet类
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search=req.getParameter("search");
        ArticleDao articleDao=new ArticleDao();
        ArrayList<ArticleBean> beans=articleDao.queryArticle("title",search, 0);
        req.setAttribute("beans", beans);
        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }
}
