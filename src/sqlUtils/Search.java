package sqlUtils;

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
                "FROM orders NATURAL JOIN packages NATURAL JOIN delivery NATURAL JOIN deal";
        ResultSet rs  = statement.executeQuery(sql);
        ArrayList<String>  text = getTest(rs);

        return text;

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
