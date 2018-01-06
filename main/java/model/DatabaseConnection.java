package model;

import java.sql.*;
import java.util.ResourceBundle;

public enum DatabaseConnection {
    INSTANCE;
    public Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        String url =resourceBundle.getString("url");
        String user = resourceBundle.getString("user");
        String password = resourceBundle.getString("password");
        return DriverManager.getConnection(url,user,password);
    }
}
