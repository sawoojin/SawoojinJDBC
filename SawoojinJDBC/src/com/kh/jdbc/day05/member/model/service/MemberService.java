package com.kh.jdbc.day05.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.day05.member.common.JDBCTemplate;
import com.kh.jdbc.day05.member.model.dao.MemberDAO;
import com.kh.jdbc.day05.member.model.vo.Member;

public class MemberService {
	private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;
	
	public MemberService() {
		mDao = new MemberDAO(); 
		jdbcTemplate = JDBCTemplate.getInstance();
	}
	
	// 1 전체출력
	public List<Member> prtinAllMembers() {
		List<Member> mList = null;
		try {
			Connection conn = jdbcTemplate.getConnection();
			mList = mDao.selectAllMembers(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
}
