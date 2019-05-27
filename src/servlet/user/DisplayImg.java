package servlet.user;

import bean.UserBean;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//输出图片的Servlet类
@WebServlet("/displayImg")
public class DisplayImg extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        resp.setHeader("context-type", "image/jpeg");
        OutputStream out = resp.getOutputStream(); //得到输出流
        if (userName == null||"".equals(userName)) {
            writeImg(out);
        } else {
            UserBean userBean = UserDao.selectUser(userName);
            byte[] bs = userBean.getUserImg();
            if (bs == null) {
                writeImg(out);
            } else {
                out.write(bs);
            }
        }
        out.flush();
    }
    private void writeImg(OutputStream out) {
        try {
            InputStream in = this.getServletContext().getResourceAsStream("images/default.png");
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len); //将图片输出到浏览器
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
