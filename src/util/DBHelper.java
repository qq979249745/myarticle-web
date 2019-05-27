package util;

import java.io.File;
import java.sql.*;
//获取Connection的JavaBean类
public class DBHelper {
    private static String path="D:/temp/db";
	private static Connection con= null;
	private static PreparedStatement ps=null;
	private static int i=0;
	private static ResultSet rs=null;
    static {
    	try {
			Class.forName("org.h2.Driver");
			Connection con1;
			if(new File(path).exists()){
				System.out.println("有D:/temp/db");
			}else {
				path="C:/Users/mi/Desktop/db";
			}
			con1= DriverManager.getConnection("jdbc:h2:"+path+"/16201533","sa","");

			Statement statement=con1.createStatement();
			//建用户表
			String sql="create table if not exists user (userName varchar(32) primary key,password varchar(32),userImg blob)";
			statement.execute(sql);
			//建article表
			sql="create table if not exists article  (articleid char(16) primary key auto_increment,title varchar(128),content text,submitTime bigint,watchNum int,praiseNum int,commentNum int ,userName varchar(32) ,foreign key(userName) references user(userName))";
			statement.execute(sql);
			//建评论表
			sql="create table if not exists  comment (commentid char(16) primary key auto_increment,articleid char(16),userName varchar(32),comment_content varchar(255),comment_time bigint,foreign key(articleid) references article(articleid),foreign key(userName) references user(userName))";
			statement.execute(sql);
			statement.close();
			con1.close();
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败！\n"+e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}   
    }
    public static Connection getConnect(){
        try {
            //jdbc:h2:D:/temp/db/16201533
			con= DriverManager.getConnection("jdbc:h2:"+path+"/16201533","sa","");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("没有找到驱动类，或者数据库被占用！！！");
        }
        return con;
    }
    public static void close(Connection con,Statement statement,ResultSet rs){
		try {
			if(rs!=null&&!rs.isClosed()){
				rs.close();
			}
			if(statement!=null&&!statement.isClosed()){
				statement.close();
			}
			if(con!=null&&!con.isClosed()){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean execute(String sql,String... param){
		con=DBHelper.getConnect();
		try {
			ps=con.prepareStatement(sql);
			for(int i=0;i<param.length;i++){
				ps.setString(i+1,param[i]);
			}
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
}
