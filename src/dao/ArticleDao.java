package dao;

import bean.ArticleBean;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//文章对应的操作数据库dao的层
public class ArticleDao {
    /**
     * 插入
     * @param bean 要插入的对象
     * @return 插入成功返回true，否则false
     */
    public boolean insertArticle(ArticleBean bean){
        Connection con = DBHelper.getConnect();
        PreparedStatement ps=null;
        int i=0;
        String sql = "insert into article values(null,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getTitle());
            ps.setString(2, bean.getContent());
            ps.setLong(3, bean.getSubmitTime());
            ps.setInt(4, bean.getWatchNum());
            ps.setInt(5, bean.getPraiseNum());
            ps.setInt(6, bean.getCommentNum());
            ps.setString(7, bean.getUserName());
            i= ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.close(con,ps,null);
        }
        if(i>0){
            return true;
        }
        return false;
    }
    public boolean deleteArticle(String id){
        String sql = "delete comment  where articleid=?";
        DBHelper.execute(sql,id+"");
            sql = "delete article  where articleid=?";
            if (DBHelper.execute(sql,id+"")) {
                return true;
            }

        return false;
    }
    /**
     * 更新
     * @param bean 跟新的对象
     * @return 更新成功返回true，否则false
     */
    public boolean updateArticle(ArticleBean bean){
        String sql = "update  article set title=? ,content =? where articleid=?";
        return DBHelper.execute(sql,bean.getTitle(),bean.getContent(),bean.getId()+"");
    }

    /**
     * 根据列名降序排序
     * @param column 列名
     * @param start 页数
     * @return 查询结果
     */
    public ArrayList<ArticleBean> queryArticleAll(String column,int start){
        String sql = "select * from article order by "+column+" desc limit "+start+",10";
        return execute(sql,null);
    }

    /**
     * 查询行数
     * @param column 列名
     * @param where 查询关键字
     * @return 行数
     */
    public int getArticleCount(String column,String where){
        Connection con = DBHelper.getConnect();
        PreparedStatement ps=null;
        ResultSet rs=null;
        if(null==column){
            column="title";
        }
        if(null==where){
            where="";
        }
        where=where.toLowerCase();
        String sql = "select count (*) from article where lower("+column+") like ?";
        int count=0;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+where+"%");
            rs = ps.executeQuery();
            if(rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.close(con,ps,rs);
        }
        return count;
    }

    /**
     * 查询
     * @param column 要查询的列名
     * @param where 查询关键字
     * @param start 查询页数
     * @return 查询结果
     */
    public ArrayList<ArticleBean> queryArticle(String column, String where, int start){
        if(null==column){
            column="title";
        }
        if(null==where){
            where="";
        }
        where=where.toLowerCase();
        String sql = "select * from article where lower("+column+") like ? order by watchNum desc,submitTime desc limit "+start+",10";
        return execute(sql,"%"+where+"%");
    }

    /**
     * 根据id查询
     * @param where id
     * @return articleJavaBean对象。没有找到返回null
     */
    public ArrayList<ArticleBean> queryArticle(String column,String where){
        String sql = "select * from article where "+column+"=? ";
        return execute(sql,where);
    }

    private ArrayList<ArticleBean> execute(String sql,String... param){
        ArrayList<ArticleBean> articleBeans=new ArrayList<>();
        Connection con = DBHelper.getConnect();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(sql);
            if(param!=null)
            for(int i=0;i<param.length;i++){
                ps.setString(i+1, param[i]);
            }
            rs = ps.executeQuery();
            while (rs.next()){
                ArticleBean bean=new ArticleBean(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getInt(5),
                        rs.getInt(6) ,
                        rs.getInt(7) ,
                        rs.getString(8));
                articleBeans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBHelper.close(con,ps,rs);
        }
        return articleBeans;
    }
    /**
     * 将赞或浏览数加一
     * @param id 要修改的id
     * @param column 要增加一的列
     * @return 修改成功返回true，否则false
     */
    public boolean plusOne(String id, String column){
        if(id==null)
            return false;
        String sql = "update article set "+column+"="+column+"+1 where articleid=?";
        return DBHelper.execute(sql,id);
    }

}
