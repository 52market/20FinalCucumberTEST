package com.kh.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import static com.kh.common.JDBCTemplate.*;

import com.kh.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	public MemberDao() {
		try {
			prop.load(new FileReader("resources/query.properties"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(Connection conn, String id) {
		
		Member mem = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// String sql = "SELECT *FROM MEMBER WHERE USERID=?";
		String sql = prop.getProperty("selectOne");
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) { // StringTokenizer와 비슷하다. // 한개일때는 if문으로 next()로 한다. next()의 리턴값은 boolean이다.!

	             mem = new Member();
	            mem.setUserId(rset.getString("USERID"));
	            mem.setPassword(rset.getString("PASSWORD"));
	            mem.setUserName(rset.getString("USERNAME"));
	            mem.setGender(rset.getString("GENDER"));
	            mem.setAge(rset.getInt("AGE"));
	            mem.setEmail(rset.getString("EMAIL"));
	            mem.setPhone(rset.getString("PHONE"));
	            mem.setAddress(rset.getString("ADDRESS"));
	            mem.setHobby(rset.getString("HOBBY"));
	            mem.setEnrollDate(rset.getDate("ENROLLDATE"));
	          }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return mem;
	}

	public ArrayList<Member> selectAll(Connection conn) {
		ArrayList<Member> list = new ArrayList<>();
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String sql = prop.getProperty("selectAll");
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         rset = pstmt.executeQuery();
	         while(rset.next()) {
	             Member m = new Member();
	             m.setUserId(rset.getString("USERID"));
	             m.setPassword(rset.getString("PASSWORD"));
	             m.setUserName(rset.getString("USERNAME"));
	             m.setGender(rset.getString("GENDER"));
	             m.setAge(rset.getInt("AGE"));
	             m.setEmail(rset.getString("EMAIL"));
	             m.setPhone(rset.getString("PHONE"));
	             m.setAddress(rset.getString("ADDRESS"));
	             m.setHobby(rset.getString("HOBBY"));
	             m.setEnrollDate(rset.getDate("ENROLLDATE"));
	             list.add(m);
	         }
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close(pstmt);
	         close(rset);
	      }
	      return list;
	}

	public Member SelectByName(Connection conn, String name) {
		Member m = new Member();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectByName");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name + "%");
			rset =pstmt.executeQuery();
			while(rset.next()) { // StringTokenizer와 비슷하다. // 한개일때는 if문으로 next()로 한다. next()의 리턴값은 boolean이다.!

	            
	            m.setUserId(rset.getString("USERID"));
	            m.setPassword(rset.getString("PASSWORD"));
	            m.setUserName(rset.getString("USERNAME"));
	            m.setGender(rset.getString("GENDER"));
	            m.setAge(rset.getInt("AGE"));
	            m.setEmail(rset.getString("EMAIL"));
	            m.setPhone(rset.getString("PHONE"));
	            m.setAddress(rset.getString("ADDRESS"));
	            m.setHobby(rset.getString("HOBBY"));
	            m.setEnrollDate(rset.getDate("ENROLLDATE"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		
		return m;
	}

	public int insertMember(Connection conn, Member m) {
		 int result = 0;
		 PreparedStatement pstmt = null;
		 String sql = prop.getProperty("insertMember");
		 
		 	
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, m.getUserId());
	         pstmt.setString(2, m.getPassword());
	         pstmt.setString(3, m.getUserName());
	         pstmt.setString(4, m.getGender());
	         pstmt.setInt(5, m.getAge());
	         pstmt.setString(6, m.getEmail());
	         pstmt.setString(7, m.getPhone());
	         pstmt.setString(8, m.getAddress());
	         pstmt.setString(9, m.getHobby());
	         
	         result = pstmt.executeUpdate();
	         
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close(pstmt);
	      }
	      
	      return result;
	   }

	public int updateMember(Connection conn, Member mem) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, mem.getPassword());
	         pstmt.setString(2, mem.getEmail());
	         pstmt.setString(3, mem.getPhone());
	         pstmt.setString(4, mem.getAddress());
	         pstmt.setString(5, mem.getUserId());
	         
	         result = pstmt.executeUpdate();
	         
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close(pstmt);
	      }
	      
	      return result;
	}

	public int deleteMember(Connection conn, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	

}
