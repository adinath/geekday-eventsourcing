package com.geekday;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:hsqldb:mem:customer", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
