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
//根据pages显示下一页文章的Servlet类
@WebServlet("/nextPage")
public class NextPageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pages=req.getParameter("pages");
        String search=req.getParameter("search");
        if(pages==null){
            pages="1";
        }
        int start=(Integer.valueOf(pages)-1)*10;
        ArticleDao articleDao=new ArticleDao();
        ArrayList<ArticleBean> beans=articleDao.queryArticle("title",search, start);
        req.setAttribute("beans", beans);
        req.getRequestDispatcher("search.jsp").forward(req, resp);
    }
}
