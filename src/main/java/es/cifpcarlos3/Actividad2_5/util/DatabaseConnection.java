package es.cifpcarlos3.Actividad2_5.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mariadb://localhost:3306/banco";
    private static final String user = "root";
    private static final String pass = "";

    public Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
