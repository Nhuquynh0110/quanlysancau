package controller;

import static Database.DatabaseInfo.password;
import static Database.DatabaseInfo.url;
import static Database.DatabaseInfo.user;

import java.sql.Connection;
import java.sql.DriverManager;

import Database.DatabaseInfo;

public class ConnectDB implements DatabaseInfo {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("connect successfully");
		} catch (Exception e) {
			System.out.println("connect failure");
			e.printStackTrace();
		}
		return conn;
	}

}
