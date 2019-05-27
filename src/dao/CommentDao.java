package dao;

import bean.CommentBean;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
//文章评论的操作数据库dao的层
public class CommentDao {
    private static Connection con= null;
    private static PreparedStatement ps=null;
    private static int i=0;
    private static ResultSet rs=null;
    public static boolean insert(CommentBean commentBean){
        String sql="insert into comment values(null,?,?,?,?)";
        return DBHelper.execute(sql,commentBean.getArticleId()+"",commentBean.getUserName(),commentBean.getContent(),commentBean.getSubmitTime()+"");
    }
    public static ArrayList<CommentBean> queryAll(String colum, String where){
        ArrayList<CommentBean> list=new ArrayList<>();
        String sql="select * from comment where "+colum+"=?";
        con=DBHelper.getConnect();
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,where);
            rs = ps.executeQuery();
            while (rs.next()){
                CommentBean commentBean=new CommentBean(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getLong(5));
                list.add(commentBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.close(con,ps,rs);
        }
        return list;
    }
    public static ArrayList<HashMap<String,String>> queryComment(String where){
        ArrayList<HashMap<String,String>> list=new ArrayList<>();

        String sql="select * from comment,article where comment.articleid=article.articleid and comment.username=? order by COMMENT_TIME desc ";
        con=DBHelper.getConnect();
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,where);
            rs = ps.executeQuery();
            while (rs.next()){
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("articleid",rs.getString("articleid"));
                hashMap.put("comment_content",rs.getString("comment_content"));
                hashMap.put("comment_time",rs.getString("comment_time"));
                hashMap.put("title",rs.getString("title"));
                hashMap.put("watchNum",rs.getString("watchNum"));
                hashMap.put("praiseNum",rs.getString("praiseNum"));
                hashMap.put("commentNum",rs.getString("commentNum"));
                list.add(hashMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.close(con,ps,rs);
        }
        return list;
    }
    public void queryCount(){

    }
}
