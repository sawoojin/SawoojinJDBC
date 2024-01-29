package com.kh.jdbc.day05.member.controller;

import java.util.List;

import com.kh.jdbc.day05.member.model.dao.MemberDAO;
import com.kh.jdbc.day05.member.model.service.MemberService;
import com.kh.jdbc.day05.member.model.vo.Member;

public class MemberController {
	
	private MemberService mService;
	private MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}

	// 1 전체출력
	public List<Member> prtinAllMembers() {
		List<Member> mList = mService.prtinAllMembers();
		return mList;
	}
	// 2 등록
	public int inputMember(Member member) {
		int result = mDao.addMember(member);
		return result;
	}
	// 3
	// 4 삭제
	public int removeMember(String memberId) throws Exception {
		int result = mDao.deleteMember(memberId);
		return result;
	}

}
