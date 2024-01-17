package com.kh.jdbc.day03.pstmt.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberDAO {
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USERNAME = "STUDENT";
	final String PASSWORD = "STUDENT";
	final String query = "SELECT * FROM MEMBER_TBL";
	// JDBC이용해 오라클 DB에 접속하는 클래서
	
	// JDBC 코딩 절차
	/* 1. 드라이버 등록
	 * 2. DB연결 생성
	 * 3. 쿼리문 실행 준비
	 * 4. 쿼리문 실행 및 5. 결과 받기
	 * 6. 자원 해제(close())
	 */
	public Member LoginInfo(Member mOne) {
//		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+mOne.getMemberId()+"' AND MEMBER_PWD = '"+mOne.getMemberPw()+"'";
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?"; // SQL 인덱션 보안 문제
		Member member = null;
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;	
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(query); // 쿼리문을 미리 컴파일시킨다.
			pstmt.setString(1, mOne.getMemberId());
			pstmt.setString(2, mOne.getMemberPw()); // 시작은 1로 물음표 갯수대로 값을 추가해야 한다.
			rset = pstmt.executeQuery(); // 모든 작업 후 쿼리문 실행 (결과받기)
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(query);
			if (rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPw(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER").charAt(0));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			conn.close();
//			stmt.close();
			pstmt.close();
			rset.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	
	public void updateMember(Member member) {
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = '"+member.getMemberPw()+"', EMAIL = '"+member.getEmail()+"', PHONE = '"+member.getPhone()+"', ADDRESS = '"+member.getAddress()+"', HOBBY = '"+member.getHobby()+"' WHERE MEMBER_ID = '"+member.getMemberId()+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			
			int result = stmt.executeUpdate(query); // DML 의 경우 int 로 받음
			if (result > 0 ) {
				// 성공 후 커밋
			}else {
				// 실패 후 커밋
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteMember(String memberId) {
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
//		Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query); // DML 의 경우 int 로 받음
//			member = new Member();
			if (result > 0 ) {
				// 성공 후 커밋
			}else {
				// 실패 후 커밋
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Member selectOneById(String memberId) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			member = new Member();
			
			if(rset.next()) {
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPw(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER").charAt(0));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return member;
	}
	
	public void insertMember(Member member) {
		String query = "INSERT INTO MEMBER_TBL "
				+ "VALUES('"+ member.getMemberId()+"', '"
							+ member.getMemberPw()+"', '"
							+ member.getMemberName()+"', '"
							+ member.getGender()+"','"
							+ member.getAge()+"','"
							+ member.getEmail()+"', '"
							+ member.getPhone()+"', '"
							+ member.getAddress()+"', '"
							+ member.getHobby()+"',sysdate)";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query); // DML 의 경우 호출하는 메소드
			if(result > 0) {
				// insert 성공
			} else {
				// insert 실패
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 클래스 바로 밑에 코드 쓰기 불가능
	// 메소드로 감싸줘야 함 안에 코드 작성
	// 필요할 때 호출해서 사용
	public List<Member> selectAll() {
		List<Member> mList = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery(query);
				// 전부 출력하는 구분
				mList = new ArrayList<Member>();
				while(rset.next()) {
//					System.out.println("이름 : " + rset.getString("MEMBER_NAME"));
					Member member = new Member();
					String memberId = rset.getString("MEMBER_ID");
					String memberPwd = rset.getString("MEMBER_PWD");
					String memberName = rset.getString("MEMBER_NAME");
					member.setMemberId(memberId);
					member.setMemberPw(memberPwd);
					member.setMemberName(memberName);
					
					member.setGender(rset.getString("GENDER").charAt(0));
					member.setAge(rset.getInt("AGE"));
					member.setEmail(rset.getString("EMAIL"));
					member.setPhone(rset.getString("PHONE"));
					member.setAddress(rset.getString("ADDRESS"));
					member.setHobby(rset.getString("HOBBY"));
					member.setEnrollDate(rset.getDate("ENROLL_DATE"));
					mList.add(member); // 누락주의 !
					// 후처리 : select한 결과값 자바영역인 리스트에 옮기는 과정
				}
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
}
