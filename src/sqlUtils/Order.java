package sqlUtils;

import server.DBConnection;

import java.sql.SQLException;
import java.sql.Statement;

public class Order {
    private DBConnection con = new DBConnection();
    private Statement statement;

    public Order()throws SQLException {
       statement = this.con.getCon().createStatement();
    }

    public String newPackage(String line){
        //int
        return null;
    }
}
