package com.srikanth.testdb;
import java.sql.*;
import java.sql.DriverManager;
public class Example {
	static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://127.0.0.1:3306/web_customer_tracker";
	public static void main(String[] args) {
		try {
			Class.forName(JDBC_DRIVER);
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection conn=DriverManager.getConnection(DB_URL,"springmvcstudent","springmvcstudent");
			
			System.out.println("success");
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
