# myarticle-web
web第三次作业发布文章的个人网站
以Tomcat为服务器，基于Model 1（JSP + Java Bean+servlet+mvc）的方式实现，数据库采用H2数据库。网站主要浏览文章，并且实现了增、删、改、查、分页等功能。
数据库结构：表名 user（用户） (userName varchar(32) primary key,password varchar(32),userImg blob);
表名article（文章）， (articleid char(16) primary key auto_increment,title varchar(128),content text,submitTime bigint,watchNum int,praiseNum int,commentNum int ,userName varchar(32) ,foreign key(userName) references user(userName));
用户评论表 comment (commentid char(16) primary key auto_increment,articleid char(16),userName varchar(32),comment_content varchar(255),comment_time bigint,foreign key(articleid) references article(articleid),foreign key(userName) references user(userName));

包含的类：
Util.java工具类，将时间戳转换成年月日时分秒格式
MyFilter.java 过滤器类，设置request和response编码为utf-8
ArticleBean.Java 文章实体JavaBean类
CommentBean.java 文章评论JavaBean类
UserBean.java 用户实体JavaBean
ArticleDao.java 文章对应的操作数据库dao的层
CommentDao.java 文章评论的操作数据库dao的层
UserDao.java 用户对应的操作数据库dao的层
Servlet类
AddServlet.java 添加文章的Servlet类
DeleteServlet.java 删除文章的Servlet类
DetailServlet.java 显示文章详情的Servlet类
NextPageServlet.java 根据pages显示下一页文章的Servlet类
PraiseServlet.java 根据id对文章进行点赞的Servlet类
SearchServlet.java 根据search对文章进行搜索的Servlet类
UpdateServlet.java 根据id对文章进行修改或者添加的Servlet类
InsertCom.java 添加评论的Servlet类
DisplayImg.java 输出图片的Servlet类
Login.java 登录的Servlet类
Logout.java 退出登录。注销的Servlet类
Modify.java 对用户进行修改头像的Servlet类
MyInfo.java 显示用户评论和发布文章的Servlet类
Register.java 注册用户的Servlet类
JSP页面：
index.jsp 网站的首页，对文章的浏览和搜索
article.jsp 显示文章的详细信息，每进一次会对文章的浏览数加一，还可以对文章进行点赞，并且还可以添加文章或者对文章进行修改。
login.jsp 登录
modify.jsp 修改头像界面
myarticle.jsp 用户发布的文章
mycomment.jsp 用户评论过的文章
register.jsp 注册
search.jsp
--------------------- 
作者：qq_38275941 
来源：CSDN 
原文：https://blog.csdn.net/qq_38275941/article/details/85301757 
版权声明：本文为博主原创文章，转载请附上博文链接！
