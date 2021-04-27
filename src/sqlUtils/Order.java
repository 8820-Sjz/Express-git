package sqlUtils;

import server.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Order {
    private DBConnection con = new DBConnection();
    private Statement statement;

    private String[] delivery = new String[3];//pid,rid,timeliness
    private String did = null;

    private String[] vehicle = {"4001","4002","4003","4004","4005",
                                "4006","4007","4008", "4009","1721","4010"};
    private int v = 10;
    private String[] company = {"SF","SF","SF","SF","YT",
                                "ZT","ST","YD","SF","YT","EMS"};
    private Random r = new Random();

    public Order()throws SQLException {
       statement = this.con.getCon().createStatement();
    }

    public String newPackage(String line)throws SQLException{
        String pid = "2018"+r.nextInt(250101);
        String[] s =line.split(",");
        if(s.length!=6){return "false"; }
        delivery[0] = pid;
        delivery[2] = s[5];
        String sql = "insert into  Packages values ('"+pid+"','"+s[0]+"','"+s[1]+
                "','"+s[2]+"','"+s[3]+"','"+s[4]+"','"+s[5]+"')";
        int count = statement.executeUpdate(sql);
        if(count>0){
            System.out.println("success to insert into package!");
            return "true";
        }
        return "false";
    }
    public String newReciver(String line)throws SQLException{
        String rid = "2018"+r.nextInt(520101);
        String[] s =line.split(",");
        if(s.length!=2){return "false"; }
        delivery[1] = rid;
        String sql = "insert into reciever values('"+rid+"','"+s[0]+"','"+s[1]+"')";
        int count = statement.executeUpdate(sql);
        if(count>0){
            System.out.println("success to insert into receiver!");
            return "true";
        }
        return "false";
    }

    public String newDelivery()throws SQLException{
        String sql = "insert into  Delivery values ('"+delivery[0]+"','"+delivery[1]+
                "','"+delivery[2]+"',null,null)";
        int count = statement.executeUpdate(sql);
        if(count>0){
            System.out.println("success to insert into delivery!");
            return "true";
        }
        return "false";
    }

    public String newDetail(String line)throws SQLException{
        did = "2018"+r.nextInt(520101);
        String sql = "insert into  Detail values ('"+did+
                "',null,null,'"+line+"')";
        int count = statement.executeUpdate(sql);
        if(count>0){
            System.out.println("success to insert into Detail!");
            return "true";
        }
        return "false";
    }

    public String newPDetail()throws SQLException{
        String sql = "insert into  Pdetail values ('"+delivery[0]+"','"+did+"')";
        int count = statement.executeUpdate(sql);
        if(count>0){
            System.out.println("success to insert into pdetail!");
            return "true";
        }
        return "false";
    }

    public String newLoads()throws SQLException{
        if(delivery[2].equals("overnight"))v = r.nextInt(5);
        else if(delivery[2].equals("second day"))v = 5+r.nextInt(5);
        else v = 10;
        String sql = "insert into  Loads values ('"+delivery[0]+"','"+vehicle[v]+"')";
        int count = statement.executeUpdate(sql);
        if(count>0){
            System.out.println("success to insert into Loads!");
            return "true";
        }
        return "false";
    }

    public String newOrders(String money,String cid)throws SQLException{
        String sql = "insert into  Orders values ('"+delivery[0]+"','"+cid+"',"+money+")";
        int count = statement.executeUpdate(sql);
        if(count>0){
            System.out.println("success to insert into Orders!");
            return "true";
        }
        return "false";
    }

    public String newDeal(String cid)throws SQLException{

        //if exist
        String sql1 ="select * " +
                "from Deal " +
                "where cid = '"+cid+"' and company_name = '"+company[v]+"';";
        ResultSet rs = statement.executeQuery(sql1);
        if(rs.next()){
            System.out.println("this deal is exist��");
            return "true";
        }

        //insert
        String sql = "insert into  Deal values ('"+cid+"','"+company[v]+"','account')";
        int count = statement.executeUpdate(sql);
        if(count>0){
            System.out.println("success to insert into Deal!");
            return "true";
        }
        return "false";
    }
}
