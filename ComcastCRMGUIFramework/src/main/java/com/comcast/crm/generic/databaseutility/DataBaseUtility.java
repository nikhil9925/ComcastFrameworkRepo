package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection conn;

	public void getDbConnection(String url, String username, String password) throws SQLException {
		try {
			Driver d = new Driver();
			DriverManager.registerDriver(d);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {

		}
	}

	public void getDbConnection() throws SQLException {
		try {
			Driver d = new Driver();
			DriverManager.registerDriver(d);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		} catch (Exception e) {

		}
	}
	
	public void closeConnection() throws SQLException {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}

	public ResultSet executeSelectQuery(String Query) throws SQLException {
		ResultSet result = null;
		try {
			Statement s = conn.createStatement();
			result = s.executeQuery(Query);
		} catch (Exception e) {

		}
		return result;
	}

	public int executeNonSelectQuery(String Query) {
		int result = 0;
		try {
			Statement s = conn.createStatement();
			result = s.executeUpdate(Query);
		} catch (Exception e) {

		}
		return result;
	}
}