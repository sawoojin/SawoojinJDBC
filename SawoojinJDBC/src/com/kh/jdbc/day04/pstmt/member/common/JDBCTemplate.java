package com.kh.jdbc.day04.pstmt.member.common;

import java.sql.Connection;
import java.sql.DriverManager;

class Singletone {
	private static Singletone instance;
	private Singletone() {
		
	}
	public static Singletone getInstance() {
		if(instance == null) {
			instance = new Singletone();
		}
		return instance;
	}
}

public class JDBCTemplate {
	// 연결 작업은 시간이 오래 걸리는 작업이므로 최소한으로 작동시켜야 합니다.
	// 각기 다른 소프트웨어 모듀르 기능을 자긴 응용 sw를 개발할 때 공통되는 설계 문제를 해결하기 
	// 위해 사용되는 패턴 (디자인 패턴) 을 적용시켜야 함
	// 1. 생성 패턴 : 싱글톤 패턴, 추상 팩토리, 팩토리 메서드
	// 2. 구조 패턴 : 컴포지트, 데코레이트 등
	// 3. 행위 패턴 : 옵저버, 스테이트, 전략, 템플릿 메서드 등
	// 싱글톤 패턴 적용 ----------
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
	
	// 쿼리 연결 모듈 (로그인)
	public Connection getConnection() throws Exception {
		if(conn == null || conn.isClosed()) {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return conn;
	}
}
