package com.sachin.CricketGame.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection conn = null;

        static
        {
            try {
                Properties prop = new Properties();
                InputStream in = new FileInputStream("src/main/java/com/sachin/CricketGame/util/config.properties");
                prop.load(in);
                Class.forName(prop.getProperty("driver"));
                conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
            }
            catch (ClassNotFoundException | SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        public static Connection getConnection()
        {
            return conn;
        }
        public static void closeConnection() throws SQLException {
            conn.close();
        }
}
