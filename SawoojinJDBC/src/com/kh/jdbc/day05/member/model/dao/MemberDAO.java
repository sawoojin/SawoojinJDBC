package com.kh.jdbc.day05.member.model.dao;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.day05.member.common.JDBCTemplate;
import com.kh.jdbc.day05.member.model.vo.Member;

public class MemberDAO {
	private JDBCTemplate jdbcTemplate;
	
	public MemberDAO() {
		jdbcTemplate = JDBCTemplate.getInstance();
	}

	public List<Member> selectAllMembers(Connection conn) {
		String query = "SELECT * FROM MEMBER_TBL";
//		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		try {
//			conn = jdbcTemplate.getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			mList = new ArrayList<Member>();
			while(rset.next()) {
				Member member = this.rsetToMember(rset);
				mList.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		return mList;
	}
	// selectall
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

	public int addMember(Member member) {
		String query = "INSERT INTO MEMBER_TBL(?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		int result = -1;
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender()+"");
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby()); 			
			result = pstmt.executeUpdate();
			if(result > 0) {
				// insert 성공
			} else {
				// insert 실패
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteMember(String memberId) throws Exception {
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		int result = -1;
		try {
			Connection conn = jdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			if(result > 0) {
				// commit
			} else {
				// rollback
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
