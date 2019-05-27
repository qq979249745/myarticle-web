package servlet.article;

import dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//删除文章的Servlet类
@WebServlet("/deleteArticle")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ArticleDao articleDAO = new ArticleDao();
        PrintWriter out = resp.getWriter();
        out.println("<script type='text/javascript'>");
        if (articleDAO.deleteArticle(id)) {
            out.println("window.alert('删除成功');");
        } else {
            out.println("window.alert('删除失败');");
        }
        out.println("window.location.href='index.jsp';");
        out.println("</script>");

    }
}
