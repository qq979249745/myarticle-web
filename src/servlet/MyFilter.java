package servlet;

import bean.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFilter implements Filter {
    private List<String> list;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        list=new ArrayList<>();
        list.add("/addArticle");
        list.add("/deleteArticle");
        list.add("/praise");
        list.add("/updateArticle");
        list.add("/modify");
        list.add("/modify.jsp");
        list.add("/myinfo");
        list.add("/myarticle.jsp");
        list.add("/mycomment.jsp");
        list.add("/insertcom");
    }

    //设置request和response编码为utf-8
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        // 得到用户请求的URI
        // 得到web应用程序的上下文路径
        // 去除上下文路径，得到剩余部分的路径
        String uri = req.getRequestURI().substring(req.getContextPath().length());
        // 判断用户访问的是否是登录页面
        UserBean userBean= (UserBean) req.getSession().getAttribute("userBean");
        if(userBean==null&&list.contains(uri)){
            ((HttpServletResponse) servletResponse).setHeader("refresh", "3;url='login.jsp'");
            servletResponse.getWriter().write("请登录！页面将在3秒后自动跳转，如果没有，请点击<a href='login.jsp'>回到登录页面</a>");
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
