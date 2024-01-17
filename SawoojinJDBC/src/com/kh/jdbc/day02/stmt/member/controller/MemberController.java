package com.kh.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {
	MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	public List<Member> printAll() {
//		mDao.selectAll();
		List<Member> mList = mDao.selectAll();
		return mList;
	}
	public void registerMember(Member member) {
		mDao.insertMember(member);
	}
	public Member printOneById(String memberId) {
		Member member = mDao.selectOneById(memberId);
		return member;
	}
	public void deleteMember(String memberId) {
		mDao.deleteMember(memberId);
	}
	public void modifyMember(Member member) {
		mDao.updateMember(member);
	}
}
