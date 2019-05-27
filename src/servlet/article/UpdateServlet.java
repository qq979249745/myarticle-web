package servlet.article;

import bean.ArticleBean;
import bean.UserBean;
import dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//根据id对文章进行修改或者添加的Servlet类
@WebServlet("/updateArticle")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        String title=req.getParameter("title");
        String content=req.getParameter("content");
        UserBean userBean= (UserBean) req.getSession().getAttribute("userBean");
        ArticleBean bean=new ArticleBean(-1,title,content,0,0,0,0,userBean.getUserName());
        ArticleDao articleDAO =new ArticleDao();
        PrintWriter out=resp.getWriter();
        out.println("<script type='text/javascript'>");
        if("-1".equals(id)){
            bean.setSubmitTime(System.currentTimeMillis());
            if(articleDAO.insertArticle(bean)){
                out.println("window.alert('发布成功');");
            }else {
                out.println("window.alert('发布失败');");
            }
            out.println("window.location.href='index.jsp';");
            out.println("</script>");
        }else {
            bean.setId(Integer.valueOf(id));
            if(articleDAO.updateArticle(bean)){
                out.println("window.alert('修改成功');");
            }else {
                out.println("window.alert('修改失败');");
            }
            out.println("window.location.href='detail?id=" + id + "';");
            out.println("</script>");
        }

    }
}
