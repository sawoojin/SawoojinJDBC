package com.kh.jdbc.day02.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day02.member.controller.MemberController;
import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberView {
	MemberController mController;
	
	public MemberView() {
		mController = new MemberController();
	}
	public void startProgram() {
		int choice = 0;
		Member member = null;
		String memberId = "";
		do {
			choice = this.printMainMenu();
			switch(choice) {
			case 1 :
				List<Member> mList = mController.printAll();
				this.printAllMembers(mList);				
				break;
			case 2 :
				memberId = this.inputMemberId();
				member = mController.printOneById(memberId);
				this.printOneById(member);
				break;
			case 3 :
				member = this.inputMember();
				mController.registerMember(member);
				break;
			case 4 :
				memberId = this.inputMemberId();
				member = mController.printOneById(memberId);
				if(member != null) {
					member = this.ModifyMember();
					member.setMemberId(memberId);
					mController.modifyMember(member);
				}else {
					System.out.println("존재하지 않는 아이디");
				}
				break;
			case 5 :
				memberId = this.inputMemberId();
				mController.deleteMember(memberId);
				break;
			}
		}while(choice != -1);
	}
	public int printMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======= 회원 관리 프로그램 =======");
		System.out.println("1. 회원 전체 조회");
		System.out.println("2. 회원 아이디로 조회");
		System.out.println("3. 회원 정보 등록");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 정보 삭제");
		System.out.println("-1. 프로그램 종료");
		System.out.println("메뉴 선택 : ");
		int value = sc.nextInt();
		return value;
	}
	
	public Member ModifyMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======= 회원 정보 수정 =======");
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		System.out.print("이메일 입력 : ");
		String email = sc.next();
		System.out.print("전화번호 입력 : ");
		String phone = sc.next();
		System.out.print("주소 입력 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 입력 : ");
		String hobby = sc.next();
		Member mOne = new Member(memberPw, email, phone, address, hobby);
		return mOne;
	}
	public String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
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
	
	public void printAllMembers(List<Member>mList) {
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
	public void printOneById(Member member) {
		System.out.println("======= 회원 정보 전체 출력(아이디로 검색) =======");
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
