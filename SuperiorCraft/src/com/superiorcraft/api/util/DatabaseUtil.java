package com.superiorcraft.api.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.superiorcraft.SuperiorCraft;

public class DatabaseUtil {
	
	private Connection connection;
	
	public DatabaseUtil(String dbfile) {
		Connection c = null;
	      
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + dbfile);
			c.setAutoCommit(false);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		this.connection = c;
		System.out.println("Opened database successfully");
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet execQuery(String query) {
			Statement stmt;
			try {
				stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				//rs.close();
				//stmt.close();
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
	}
	
}
