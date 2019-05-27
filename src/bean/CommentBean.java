package bean;
//文章评论JavaBean类
public class CommentBean {
    private int commentId;
    private int articleId;
    private String userName;
    private String content;
    private long submitTime;    //发布时间

    public CommentBean(int commentId, int articleId, String userName, String content, long submitTime) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.userName = userName;
        this.content = content;
        this.submitTime = submitTime;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
