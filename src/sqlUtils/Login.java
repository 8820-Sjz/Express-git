package sqlUtils;

import server.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class Login {
    private DBConnection con = new DBConnection();
    private Statement statement;
    private String id;
    private String pass;
    //private Random random = new Random();

    public Login()throws SQLException {
        statement = this.con.getCon().createStatement();

    }

    //登录
    public String signIn(String text,char r)throws SQLException{
        String[] s = text.substring(1).split(",");
        if(s.length!=2){return "false";}
        String id = s[0];
        String pass = s[1];
        System.out.println("ROOT: "+r);
        String t = "users";
        if(r=='1')t = "administrator";
        String sql ="SELECT *" +
                    "FROM "+t+" "+
                    "where id='"+id+"' and password='"+pass+"'";
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()){
            System.out.println("user exist!");
            return "true";
        }
        System.out.println("user/pass error!");
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
            System.out.println("user exist!");
            return "false";
        }
        //insert into users and customer
        String sql ="INSERT into users values('"+id+"','"+pass+"')";
        int count = statement.executeUpdate(sql);
        if(count>0){
            //System.out.println("�ɹ�����user��"+id+","+pass);
            String sql2 = "INSERT into Customer values('"+id+"','"+id+"','Wushan','Guangzhou','"+id+"')";
            int count2 = statement.executeUpdate(sql2);
            if(count2>0){
                //System.out.println("�ɹ�����customer��"+id);
                return "true";
            }
            return "true";
        }
        //System.out.println("����ʧ�ܣ�"+id+","+pass);
        return "false";
    }
}
