package bean;
//文章实体JavaBean类
public class ArticleBean {
    private int id;             //文章id
    private String userName;    //发布人
    private String title;       //文章标题
    private String content;     //文章内容
    private long submitTime;    //发布时间
    private int watchNum;       //浏览数
    private int praiseNum;      //点赞数
    private int commentNum;     //评论数

    public ArticleBean(int id, String title, String content, long submitTime, int watchNum, int praiseNum,int commentNum, String userName) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.submitTime = submitTime;
        this.watchNum = watchNum;
        this.commentNum = commentNum;
        this.praiseNum = praiseNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
    }

    public int getWatchNum() {
        return watchNum;
    }

    public void setWatchNum(int watchNum) {
        this.watchNum = watchNum;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
}
