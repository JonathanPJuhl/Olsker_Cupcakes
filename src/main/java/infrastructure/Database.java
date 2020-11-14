package infrastructure;

import java.sql.*;

public class Database {
    private final String URL = "jdbc:mysql://localhost:3306/olskercupcakes?serverTimezone=CET";
    private final String USER = "olskercupcakes";

    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, null);
    }

}
