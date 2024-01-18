package com.kh.jdbc.day04.pstmt.member.model.dao;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

import com.kh.jdbc.day04.pstmt.member.common.JDBCTemplate;
import com.kh.jdbc.day04.pstmt.member.model.vo.Member;

public class MemberDAO {
	private JDBCTemplate jdbcTemplate;
	public MemberDAO() {
		jdbcTemplate = JDBCTemplate.getInstance();
	}
	
	final String query = "SELECT * FROM MEMBER_TBL";
	// JDBC이용해 오라클 DB에 접속하는 클래서
	
	// JDBC 코딩 절차
	/* 1. 드라이버 등록
	 * 2. DB연결 생성
	 * 3. 쿼리문 실행 준비
	 * 4. 쿼리문 실행 및 5. 결과 받기
	 * 6. 자원 해제(close())
	 */
	
	
	// 멤버 정보 출력 모듈
	private Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
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
		return member;
	}
	
	public Member LoginInfo(Member mOne) {
//		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+mOne.getMemberId()+"' AND MEMBER_PWD = '"+mOne.getMemberPw()+"'";
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?"; // SQL 인덱션 보안 문제
		Member member = null;
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;	
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(query); // 쿼리문을 미리 컴파일시킨다.
			pstmt.setString(1, mOne.getMemberId());
			pstmt.setString(2, mOne.getMemberPw()); // 시작은 1로 물음표 갯수대로 값을 추가해야 한다.
			rset = pstmt.executeQuery(); // 모든 작업 후 쿼리문 실행 (결과받기)
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(query);
			if (rset.next()) {
				member = this.rsetToMember(rset);
			}
//			stmt.close();
			pstmt.close();
			rset.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return member;
	}
	
	public void updateMember(Member member) {
//		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = '"+member.getMemberPw()+"', EMAIL = '"+member.getEmail()+"', PHONE = '"+member.getPhone()+"', ADDRESS = '"+member.getAddress()+"', HOBBY = '"+member.getHobby()+"' WHERE MEMBER_ID = '"+member.getMemberId()+"'";
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE MEMBER_ID = ?";
		try {
			Connection conn = jdbcTemplate.getConnection();
//			Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(query);
//			int result = stmt.executeUpdate(query); // DML 의 경우 int 로 받음
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getHobby());
			pstmt.setString(6, member.getMemberId()); // ? 갯수 위치 확인
			int result = pstmt.executeUpdate();
			if (result > 0 ) {
				// 성공 후 커밋
			}else {
				// 실패 후 롤백
			}
//			stmt.close();
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteMember(String memberId) {
//		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
//		Member member = null;
		try {
			Connection conn = jdbcTemplate.getConnection();
//			Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(query);
//			int result = stmt.executeUpdate(query); // DML 의 경우 int 로 받음
			pstmt.setString(1, memberId);
			int result = pstmt.executeUpdate(); 
//			member = new Member();
			if (result > 0 ) {
				// 성공 후 커밋
			}else {
				// 실패 후 커밋
			}
//			stmt.close();
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Member selectOneById(String memberId) {
//		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		Member member = null;
		try {
			Connection conn = jdbcTemplate.getConnection();
//			Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(query); // 컴파일 우선
//			ResultSet rset = stmt.executeQuery(query);
			pstmt.setString(1, memberId);
			ResultSet rset = pstmt.executeQuery(); // 쿼리 없음
			if(rset.next()) {
				member = this.rsetToMember(rset);
			}
			rset.close();
//			stmt.close();
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return member;
	}
	
	public void insertMember(Member member) {
//		String query = "INSERT INTO MEMBER_TBL "
//				+ "VALUES('"+ member.getMemberId()+"', '"
//							+ member.getMemberPw()+"', '"
//							+ member.getMemberName()+"', '"
//							+ member.getGender()+"','"
//							+ member.getAge()+"','"
//							+ member.getEmail()+"', '"
//							+ member.getPhone()+"', '"
//							+ member.getAddress()+"', '"
//							+ member.getHobby()+"',sysdate)";
		String query = "INSERT INTO MEMBER_TBL(?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		try {
			Connection conn = jdbcTemplate.getConnection();
//			Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender()+"");
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby()); // 위치홀더값
			
//			int result = stmt.executeUpdate(query); // DML 의 경우 호출하는 메소드
			int result = pstmt.executeUpdate();
			if(result > 0) {
				// insert 성공
			} else {
				// insert 실패
			}
		} catch (Exception e) {
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
				Connection conn = jdbcTemplate.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery(query);
				// 전부 출력하는 구분
				mList = new ArrayList<Member>();
				while(rset.next()) {
//					System.out.println("이름 : " + rset.getString("MEMBER_NAME"));
					Member member = this.rsetToMember(rset);
					mList.add(member); // 누락주의 !
					// 후처리 : select한 결과값 자바영역인 리스트에 옮기는 과정
				}
				rset.close();
				stmt.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
}
