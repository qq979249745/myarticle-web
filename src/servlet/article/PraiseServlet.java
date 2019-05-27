package servlet.article;

import dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//根据id对文章进行点赞的Servlet类
@WebServlet("/praise")
public class PraiseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        ArticleDao articleDAO =new ArticleDao();
        PrintWriter out=resp.getWriter();
        out.println("<script type='text/javascript'>");
        if(articleDAO.plusOne(id, "praiseNum")){
            out.println("window.alert('点赞成功');");
        }else {
            out.println("window.alert('点赞失败');");
        }
        out.println("window.location.href='detail?id=" + id + "';");
        out.println("</script>");
    }
}
