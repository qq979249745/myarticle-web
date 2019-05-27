package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    /**
     * 将时间戳转换成年月日时分秒格式
     * @param t 时间毫秒数
     * @return 年月日时分秒格式时间
     */
    public  static  String getTime(long t){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(t));
    }
    public static  boolean stringLegal(String s){
        if(s!=null&&s.length()>=6&&s.length()<=12){
            return true;
        }
        return false;
    }
    public  static  String getTimeNos(long t){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(t));
    }
}
