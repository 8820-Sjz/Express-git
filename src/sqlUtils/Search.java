package sqlUtils;

import obj.TransportInfo;
import server.DBConnection;
import server.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Search {
    private DBConnection con = new DBConnection();
    private Statement statement;

    public Search() throws SQLException {
        statement = this.con.getCon().createStatement();
    }


    public ArrayList<String>  mainDataSearch() throws SQLException {
        String sql = "SELECT Cid,Pid,Rid,createtime,timeliness,company_name " +
                "FROM orders NATURAL JOIN packages NATURAL JOIN delivery NATURAL JOIN loads NATURAL JOIN vehicle NATURAL JOIN boss NATURAL JOIN company";
        ResultSet rs  = statement.executeQuery(sql);
        ArrayList<String>  text = getTest(rs);
        return text;

    }

    public void changeData(String sql) throws SQLException {
        statement.execute(sql);

    }

    public ArrayList<String> corespondingData1(int pid) throws SQLException {
        ArrayList<String> res = new ArrayList<>();
        String sql = "SELECT destination,content FROM packages NATURAL JOIN pdetail NATURAL JOIN detail WHERE Pid = "+pid;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            String destination = rs.getString("destination");
            String content = rs.getString("content");
            res.add(destination);
            res.add(content);
        }
        return res;
    }

    public ArrayList<String> corespondingData2(int pid) throws SQLException {
        ArrayList<String> res = new ArrayList<>();
        String sql = "SELECT reciever_name,phone FROM packages NATURAL JOIN delivery NATURAL JOIN reciever WHERE Pid = "+pid;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            String recipient = rs.getString("reciever_name");
            String phone = rs.getString("phone");
            res.add(recipient);
            res.add(phone);
        }
        return res;
    }

    public ArrayList<String> corespondingData3(int pid) throws SQLException {
        ArrayList<String> res = new ArrayList<>();
        String sql = "SELECT customer_name,city,street,phone FROM packages NATURAL JOIN orders NATURAL JOIN customer WHERE Pid = "+pid;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            String sender = rs.getString("customer_name");
            String city = rs.getString("city");
            String street = rs.getString("street");
            String place = city+" "+ street;
            String phone = rs.getString("phone");
            res.add(sender);
            res.add(phone);
            res.add(place);
        }
        return res;
    }

    public TransportInfo corespondingData4(int pid) throws SQLException {
        String sql = "SELECT nowwhere,yesterdaywhere,vkind,states,gettime FROM detail NATURAL JOIN pdetail " +
                "   NATURAL JOIN packages NATURAL JOIN delivery NATURAL JOIN loads NATURAL JOIN vehicle WHERE pid = "+pid;
        ResultSet rs = statement.executeQuery(sql);
        TransportInfo info = null;
        while (rs.next()){
            String arrivalTime = rs.getString("gettime");
            String previousPos = rs.getString("yesterdaywhere");
            String currentPos = rs.getString("nowwhere");
            String transportation = rs.getString("vkind");
            String parcelStatus = rs.getString("states");
            info = new TransportInfo(arrivalTime,previousPos,currentPos,transportation,parcelStatus);
        }
        return info;
    }

    public ArrayList<String> mainDataSearch(String limit)  throws Exception{
        String keyWord = limit.substring(0,limit.lastIndexOf("=")+1);
        String obj =limit.substring(limit.lastIndexOf("=")+1,limit.length());

        String sql = "SELECT Cid,Pid,Rid,createtime,timeliness,company_name FROM orders NATURAL JOIN packages NATURAL JOIN delivery NATURAL JOIN deal where "+keyWord+"'"+obj+"'";
        ResultSet rs = null;
        rs  = statement.executeQuery(sql);
        ArrayList<String>  text = getTest(rs);

        return text;
    }

    public ArrayList<String>  getTest(ResultSet rs) throws SQLException{
        ArrayList<String>  text = new ArrayList<>();
        while(rs.next()){
            int cid = rs.getInt("Cid");
            int pid = rs.getInt("Pid");
            int rid = rs.getInt("Rid");
            String createtime = rs.getString("createtime");
            String timeliness = rs.getString("timeliness");
            String companyName = rs.getString("company_name");
            String s = String.valueOf(cid)+','+String.valueOf(pid)+','+String.valueOf(rid)+','+
                    createtime+','+timeliness+','+companyName;
            text.add(s);

        }
        return text;
    }

}
