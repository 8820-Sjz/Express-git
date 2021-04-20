package sqlUtils;

import server.DBConnection;

import java.sql.Statement;

public class Order {
    private DBConnection con = new DBConnection();
    private Statement statement;

    public Order(){
       // statement = this.con.getCon().createStatement();
    }
}
