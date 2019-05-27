package servlet.comment;

import bean.CommentBean;
import bean.UserBean;
import dao.ArticleDao;
import dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//添加评论的Servlet类
@WebServlet("/insertcom")
public class InsertCom extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean= (UserBean) req.getSession().getAttribute("userBean");
        int id=Integer.valueOf(req.getParameter("id"));
        String content=req.getParameter("content");
        CommentBean commentBean=new CommentBean(0,id,userBean.getUserName(),content,System.currentTimeMillis());
        PrintWriter out=resp.getWriter();
        out.println("<script type='text/javascript'>");
        if(CommentDao.insert(commentBean)){
            out.println("window.alert('评论成功');");
            ArticleDao articleDao=new ArticleDao();
            System.out.println(articleDao.plusOne(id+"","commentNum"));
        }else {
            out.println("window.alert('评论失败');");
        }
        out.println("window.location.href='detail?id=" + id + "';");
        out.println("</script>");

    }
}
