package com.kh.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
   // 기존의 방식에서
   // jdbc driver 로딩, DB연결을 위한 정보(url,id,password)를 직접 코드에 작성을 했었다.

   // 정적코딩
   // --> 추후 DB자체 변경 또는 연결정보가 변경되는 경우에는 코드를 직접 수정하고 적용해서 다시 컴파일 해야한다.
   // --> 유지보수 불편 -> 일반 관리자(사이트관리자)가 코드를 볼 줄 몰라서 찾아서 수정하기도 어렵다.

   // 이를 해결하기 위해 properties파일을 만들어 프로그램 실행 시 동적으로 DB연결 정보를 불러오도록 코딩해보자

   public static Connection getConnection() {
      Connection conn = null;

      try {
         Properties prop = new Properties();
         prop.load(new FileReader("resources/driver.properties"));

         String driver = prop.getProperty("driver");
         String url = prop.getProperty("url");
         String user = prop.getProperty("user");
         String password = prop.getProperty("password");

         //Class.forName("oracle.jdbc.driver.OracleDriver");-->
         Class.forName(driver);
         //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","JDBC","JDBC");-->
         conn = DriverManager.getConnection(url,user,password);

         conn.setAutoCommit(false);
      }catch (ClassNotFoundException e) {
         e.printStackTrace();
      }catch(SQLException e) {
         e.printStackTrace();
      }catch(IOException e) {
         e.printStackTrace();
      }

      return conn;
   }

   // 자원을 반납해주는 메소드
   // 전달받은 Connection객체 반납해주는 메소드
   public static void close(Connection conn) {
      try {
         if(conn !=null && !conn.isClosed()) {
            // conn이 생성되어있고, 객체가 안닫혀 있는 경우 닫아라
            conn.close();
         }
      }catch(SQLException e) {
         e.printStackTrace();
      }
   }

   // PreparedStatement, Statement 반납해주는 메소드
   // PreparedStatement는 Statement의 하위클래스
   public static void close(Statement stmt) {
      try {
         if(stmt !=null && !stmt.isClosed()) {
            stmt.close();
         }
      }catch(Exception e) {
         e.printStackTrace();
      }
   }

   public static void close(ResultSet rset) {
      try {
         if(rset != null && !rset.isClosed()) {
            rset.close();
         }
      }catch(Exception e) {
         e.printStackTrace();
      }
   }

   // 트랜잭션 관련처리
   public static void commit(Connection conn) {
      try {
         if(conn != null && !conn.isClosed()) {
            conn.commit();
         }
      }catch(Exception e) {
         e.printStackTrace();
      }
   }

   public static void rollback(Connection conn) {
      try {
         if(conn != null && !conn.isClosed()) {
            conn.rollback();
         }
      }catch(Exception e) {
         e.printStackTrace();
      }
   }

}