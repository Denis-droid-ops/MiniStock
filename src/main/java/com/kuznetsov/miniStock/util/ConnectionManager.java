package com.kuznetsov.miniStock.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

     private ConnectionManager(){
     }

     public static Connection getConnection() throws SQLException {
         return DriverManager.getConnection(PropertiesUtil.get("db.url")
                                           ,PropertiesUtil.get("db.user")
                                           ,PropertiesUtil.get("db.password"));
     }
}
