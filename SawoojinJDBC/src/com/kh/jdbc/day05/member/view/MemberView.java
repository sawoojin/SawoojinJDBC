package com.kh.jdbc.day05.member.view;

import java.util.*;

import com.kh.jdbc.day05.member.controller.MemberController;
import com.kh.jdbc.day05.member.model.vo.Member;

public class MemberView {

	private MemberController mController;

	public MemberView() {
		mController = new MemberController();
	}

	public void startProgram() throws Exception {
		List<Member> mList = null;
		Member member = null;
		end: 
			while (true) {
			int choice = this.mainMenu();
			int result = -1;
			switch (choice) {
			case 0:
				break;
			case 1:
				mList = mController.prtinAllMembers();
				this.displayAllMember(mList);
				break;
			case 2:
				member = this.inputMember();
				result = mController.inputMember(member);
				if(result > 0) {
					this.displayMessage("등록 성공");				
				}else {
					this.displayMessage("등록 실패");
				}
				break;
			case 3:
				
				break;
			case 4:
				String memberId = this.selectOneMember();
				result = mController.removeMember(memberId);
				if(result > 0) {
					this.displayMessage(memberId + "가 삭제되었습니다.");
				}else {
					this.displayMessage("회원정보를 확인할 수 없습니다.");
				}
				break;
			case 9:
				this.displayMessage("프로그램이 종료되었습니다.");
				break end;
			}
		}
	}


	private int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======= 회원 관리 프로그램 =======");
		System.out.println("0. 회원 로그인");
		System.out.println("1. 회원 정보 전체 출력");
		System.out.println("2. 회원 정보 등록");
		System.out.println("3. 회원 정보 수정");
		System.out.println("4. 회원 정보 삭제");
		System.out.println("9. 프로그램 종료");
		System.out.print("메뉴 선택 >> ");
		int choice = sc.nextInt();
		return choice;
	}

	private void displayMessage(String string) {
		System.out.println(string);
	}

	private void displayAllMember(List<Member> mList) {
		{
			System.out.println("======= 회원 정보 전체 출력 =======");
			for(Member member : mList) {
				System.out.printf("이름 : %s, 나이 : %d, 아이디 : %s, 성별 : %s,이메일 : %s,"
						+ "전화번호 : %s, 주소 : %s, 취미 : %s, 가입날짜 : %s\n"
						,member.getMemberName()
						,member.getAge()
						,member.getMemberId()
						,member.getGender()
						,member.getEmail()
						,member.getPhone()
						,member.getAddress()
						,member.getHobby()
						,member.getEnrollDate());
			}
		}
	}
	public Member inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======= 회원정보 입력 =======");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별(M / F) : ");
		char gender = sc.next().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		Member member = new Member(memberId, memberPw, memberName, gender, age, email, phone, address, hobby);
		return member;
	}
	private String selectOneMember() {
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}
}
