package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {

	public void selectOne(String id) {
		Member m = new MemberService().selectOne(id);
		
		if(m != null) {
			new MemberMenu().displayMember(m);
		}else {
			new MemberMenu().displayError(id + "에 해당되는 데이터가 없습니다.");
		}
	}

	public void selectAll() {
		ArrayList<Member> list = new MemberService().selectAll();

	      if(list.isEmpty()) {
	         new MemberMenu().displayError("해당되는 정보가 없습니다.");
	      }else {
	         new MemberMenu().displayMemberList(list);
	      }
		
	}

	public void selectByName(String name) {
		Member m = new MemberService().selectByName(name);
		
		if(m != null) {
			new MemberMenu().displayMember(m);
		}else {
			new MemberMenu().displayError(name+"없는 이름");
		}
		
		
		
	}

	public void insertMember(Member m) {
		int result = new MemberService().insertMember(m);
	      
	      if(result > 0) {
	         new MemberMenu().displaySuccess("회원 가입 성공 !");
	      }else {
	         new MemberMenu().displayError("회원 가입 실패 !");
	      }
	}

	public void updateMember(Member mem) {
		
		int result = new MemberService().updateMember(mem);
		if(result > 0) {
	         new MemberMenu().displaySuccess("회원 정보 수정 성공 !");
	      }else {
	         new MemberMenu().displayError("회원 정보 수정 실패 !");
	      }
		
	}

	public void deleteMember(String id) {
		int result = new MemberService().deleteMember(id);
		if(result>0) {
			new MemberMenu().displaySuccess("탈퇴 완료");
		}else {
			new MemberMenu().displayError("다음기회에 다시 도전");
		}
	}

}
