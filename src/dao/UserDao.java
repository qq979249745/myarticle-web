package dao;

import bean.UserBean;
import util.DBHelper;

import java.io.InputStream;
import java.sql.*;
//用户对应的操作数据库dao的层
public class UserDao {
    private static Connection con= null;
    private static PreparedStatement ps=null;
    private static int i=0;
    private static ResultSet rs=null;
    public static boolean insertUser(UserBean userBean){
        String sql="insert into user values(?,?,null)";
        return DBHelper.execute(sql,userBean.getUserName(),userBean.getPassword());
    }
    public static boolean updateUser(UserBean userBean, InputStream is){
        String sql="update user set userImg=? where userName=?";
        con=DBHelper.getConnect();
        try {
            ps=con.prepareStatement(sql);
            ps.setBlob(1,is);
            ps.setString(2,userBean.getUserName());
            i=ps.executeUpdate();
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
    public static UserBean selectUser(String userName){
        UserBean userBean=null;
        String sql="select * from user where userName=?";
        con=DBHelper.getConnect();
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,userName);
            rs=ps.executeQuery();
            if(rs.next()){
                Blob blob=rs.getBlob(3);
                if(blob==null){
                    userBean=new UserBean(rs.getString(1),
                            rs.getString(2),
                            null);
                }else {
                    userBean=new UserBean(rs.getString(1),
                            rs.getString(2),
                            blob.getBytes(0, (int) blob.length()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBean;
    }
    public static int queryCount(String where){
        con = DBHelper.getConnect();

        String sql = "select count (*) from comment where userName=?";
        int count=0;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, where);
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
}
