package com.javaweb.check;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CollectionJDBCUtil {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic"; 
    private static final String USER = "root";  
    private static final String PASS = "Daoanh123!"; 
    
    public static Connection getConnection() {
    	Connection conn = null;
    	try {
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		return conn;
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
		return conn;
    }
}
