package sqlUtils;

import server.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Login {
    private DBConnection con = new DBConnection();
    private Statement statement;
    private String id;
    private String pass;

    public Login()throws SQLException {
        statement = this.con.getCon().createStatement();

    }

    //登录
    public String signIn(String text)throws SQLException{
        String[] s = text.substring(1).split(",");
        if(s.length!=2){return "false";}
        String id = s[0];
        String pass = s[1];
        String sql ="SELECT *" +
                    "FROM users "+
                    "where id='"+id+"' and password='"+pass+"'";
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()){
            System.out.println("账号存在！");
            return "true";
        }
        System.out.println("用户或密码错误！");
        return "false";
    }

    //注册
    public String signUp(String text)throws SQLException{
        String[] s = text.substring(1).split(",");
        if(s.length!=2){return "false";}
        String id = s[0];
        String pass = s[1];
        //if exist
        String sql1 ="SELECT *" +
                "FROM users "+
                "where id='"+id+"'";
        ResultSet rs = statement.executeQuery(sql1);
        if(rs.next()){
            System.out.println("账号已存在，注册失败！");
            return "false";
        }

        String sql ="INSERT into users values('"+id+"','"+pass+"')";
        int count = statement.executeUpdate(sql);
        if(count>0){
            System.out.println("成功增加："+id+","+pass);
            return "true";
        }
        System.out.println("增加失败："+id+","+pass);
        return "false";
    }
}
