package servlet.user;

import bean.ArticleBean;
import bean.UserBean;
import dao.ArticleDao;
import dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//显示用户评论和发布文章的Servlet类
@WebServlet("/myinfo")
public class MyInfo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        UserBean userBean= (UserBean) req.getSession().getAttribute("userBean");
        if("submit".equals(action)){
            ArticleDao articleDao=new ArticleDao();
            ArrayList<ArticleBean> articleBeans = articleDao.queryArticle("userName", userBean.getUserName());
            req.setAttribute("articleBeans",articleBeans);
            req.getRequestDispatcher("myarticle.jsp").forward(req,resp);
        }else if("comment".equals(action)){
            ArrayList<HashMap<String, String>> arrayList = CommentDao.queryComment(userBean.getUserName());
            req.setAttribute("arrayList",arrayList);
            req.getRequestDispatcher("mycomment.jsp").forward(req,resp);
        }
    }
}
