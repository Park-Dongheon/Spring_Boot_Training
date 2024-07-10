package com.rubypaper.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionManager {
	private String url;
	private String username;
	private String password;
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "JDBCConnectionManager [url=" + url + ", username=" + username + ", password=" + password + "]";
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
}
