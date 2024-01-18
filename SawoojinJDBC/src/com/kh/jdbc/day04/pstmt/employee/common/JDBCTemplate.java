package com.kh.jdbc.day04.pstmt.employee.common;

import java.sql.*;

public class JDBCTemplate {
	private static JDBCTemplate instance;
	private static Connection conn;
	private JDBCTemplate() {}
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USERNAME = "STUDENT";
	final String PASSWORD = "STUDENT";
	
	// 쿼리 연결 모듈
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		if(conn == null || conn.isClosed()) {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);		
		}
		return conn;
	}
}
