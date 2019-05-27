package servlet.user;

import bean.UserBean;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static util.Util.stringLegal;
//注册用户的Servlet类
@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName=req.getParameter("userName");
        String password=req.getParameter("password");
        String passwordConfirm=req.getParameter("passwordConfirm");
        PrintWriter out=resp.getWriter();
        out.println("<script type='text/javascript'>");
        String message="注册失败";
        String url="register.jsp";
        if(stringLegal(userName)&&stringLegal(password)&&stringLegal(passwordConfirm)){
            if(password.equals(passwordConfirm)){
                if(UserDao.selectUser(userName)==null){
                    UserBean userBean=new UserBean(userName,password,null);
                    if(UserDao.insertUser(userBean)){
                        message="注册成功！请登录";
                        url="login.jsp";
                    }
                }else {
                    message="用户名已存在!";
                }
            }else {
                message="两次密码不一致!";
            }
        }else {
            message="用户名或密码输入有误!";
        }
        out.println("window.alert('"+message+"');");
        out.println("window.location.href='"+url+"';");
        out.println("</script>");
    }
}
