package servlet.user;

import bean.UserBean;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
//对用户进行修改头像的Servlet类
@MultipartConfig
@WebServlet("/modify")
public class Modify extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserBean userBean= (UserBean) req.getSession().getAttribute("userBean");
        System.out.println(req.getParameter("image"));
        try {
        Part part;
        part = req.getPart("image");
        InputStream is=part.getInputStream();
        PrintWriter out=resp.getWriter();
        out.println("<script type='text/javascript'>");
        if(UserDao.updateUser(userBean,is)){
            userBean=UserDao.selectUser(userBean.getUserName());
            req.getSession().setAttribute("userBean",userBean);
            out.println("window.alert('修改成功');");
            out.println("window.location.href='index.jsp';");
        }else {
            out.println("window.alert('修改失败');");
            out.println("window.location.href='modify.jsp';");
        }
        out.println("</script>");
    } catch (ServletException e) {
        e.printStackTrace();
    }


}
}
