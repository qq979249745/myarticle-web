package bean;

//用户实体JavaBean
public class UserBean {
    private String userName;
    private String password;
    private byte[] userImg;

    public UserBean( String userName, String password, byte[] userImg) {
        this.userName = userName;
        this.password = password;
        this.userImg = userImg;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getUserImg() {
        return userImg;
    }

    public void setUserImg(byte[] userImg) {
        this.userImg = userImg;
    }
}
