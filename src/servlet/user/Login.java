package servlet.user;

import bean.UserBean;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static util.Util.stringLegal;
//登录的Servlet类
@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        UserBean userBean = UserDao.selectUser(userName);
        if (stringLegal(userName) && stringLegal(password)&&userBean != null&&userBean.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("userBean", userBean);
            resp.sendRedirect("index.jsp");
            //req.getRequestDispatcher("search.jsp").forward(req,resp);
        }else {
            PrintWriter out = resp.getWriter();
            out.println("<script type='text/javascript'>");
            out.println("window.alert('用户名或密码输入错误!');");
            out.println("window.location.href='login.jsp';");
            out.println("</script>");
        }

    }
}
