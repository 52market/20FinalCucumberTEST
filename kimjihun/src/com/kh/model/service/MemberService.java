package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

import static com.kh.common.JDBCTemplate.*;

public class MemberService {

	public Member selectOne(String id) {
		
		Connection conn = getConnection();
		Member m = new MemberDao().selectOne(conn,id);
		
		close(conn);  // static으로 사용가능해짐

		return m;
	}

	public ArrayList<Member> selectAll() {
		Connection conn = getConnection();
		ArrayList<Member> m = new MemberDao().selectAll(conn);
		
		close(conn);
		
		return m;
	}

	public Member selectByName(String name) {
		Connection conn = getConnection();
		Member m = new MemberDao().SelectByName(conn,name);
		
		close(conn);
		return m;
	}

	public int insertMember (Member m) {
		Connection conn = getConnection();
	      
	      int result = new MemberDao().insertMember(conn, m);
	      
	      if(result > 0) {
	         commit(conn); // static으로 import되어있기에 바로 사용할 수 있다.
	      }else {
	         rollback(conn);
	      }
	      
	      close(conn);
	      
	      return result;
	}

	public int updateMember(Member mem) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMember(conn,mem);
		
		if(result > 0) {
	         commit(conn); // static으로 import되어있기에 바로 사용할 수 있다.
	      }else {
	         rollback(conn);
	      }
		
		
		return result;
	}

	public int deleteMember(String id) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn,id);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}

}
