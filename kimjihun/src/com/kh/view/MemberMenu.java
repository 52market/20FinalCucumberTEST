package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

public class MemberMenu {
	 private Scanner sc = new Scanner(System.in);
	   private MemberController mController = new MemberController();
	   
	   private static Member loginMem;
	   
	   public void mainMenu() {
	      int menu;
	      
	      while(true) {
	         System.out.println("\n========== 회원 관리 프로그램 ===========");
	         System.out.println("1. 회원 전체 조회");
	         System.out.println("2. 회원 아이디 조회");
	         System.out.println("3. 회원 이름으로 검색");
	         System.out.println("4. 회원 가입");
	         System.out.println("5. 회원 정보 변경");
	         System.out.println("6. 회원 탈퇴");
	         System.out.println("7. 로그인");
	         System.out.println("9. 프로그램 끝내기");
	         System.out.print("번호 선택 : ");
	         menu = sc.nextInt();
	         sc.nextLine(); // nextInt()의 개행문자를 제거해준다.
	         
	         switch(menu) {
	         case 1: // 기능요청 메소드
	            mController.selectAll();
	            break;
	         case 2:
	            mController.selectOne(inputMemberId());
	            break;
	         case 3:
	            mController.selectByName(inputMemberName());
	            break;
	         case 4:
	            mController.insertMember(inputMember()); //inputMember() → 메소드 / Member가 리턴값이다.
	            break;
	         case 5:
	            Member mem = updateMember();
	            mController.updateMember(mem);
	            break;
	         case 6:
	            if(loginMem != null) {
	               mController.deleteMember(loginMem.getUserId()); // 로그인 한 상태라면!!
	            }else {
	               mController.deleteMember(inputMemberId()); // 로그인 하지 않은 상태라면!!
	            }
	            break;
	         case 7:
	           // mController.login(loginMember()); // String 두개로 전달해도 되지만 Member객체로 전달을 시킨다. 아이디 비밀번호만 있으면 된다.
	            break;
	         case 9:
	            System.out.println("정말로 끝내겠습니까?(y/n) : ");
	            if(sc.next().toLowerCase().charAt(0) == 'y'){ // 문자열 비교는 == 가능하다. / String.eqauls(주소값 비교) / 일반문자는 기본자료형처럼 == 가능
	               return; // return을 만나면 실행중인 메소드를 종료하고 메인으로 넘어간다.
	            }
	            break;
	         default:
	            System.out.println("번호를 잘못 입력하셨습니다.");
	         }
	      }
	   }
	   
	   public Member loginMember() {
	      
	      Member m = new Member();
	      
	      System.out.println("아이디를 입력하시오 : ");
	      m.setUserId(sc.nextLine());
	      System.out.println("비밀번호를 입력하시오 : ");
	      m.setPassword(sc.nextLine());
	      
	      return m;
	   }
	   
	   private Member updateMember() {
	      Member m = new Member();
	      
	      m.setUserId(inputMemberId());
	      System.out.print("변경할 암호 : ");
	      m.setPassword(sc.next());
	      System.out.print("변경할 이메일 : ");
	      m.setEmail(sc.next());
	      System.out.print("변경할 전화번호(-빼고 입력) : ");
	      m.setPhone(sc.next());
	      System.out.print("변경할 주소 : ");
	      sc.nextLine();
	      m.setAddress(sc.nextLine());
	      return m;
	   }

	   public String inputMemberId() {
	      System.out.println("조회할 회원 아이디 입력 : ");
	      return sc.nextLine(); // nextLine()의 리턴값은 String이다. 사용자에게 입력받은 String을 넘겨준다.
	   }
	   
	   public String inputMemberName() {
	      System.out.println("조회할 회원 이름 입력 : ");
	      return sc.nextLine();
	   }

	   public Member inputMember() { // 회원가입
	        System.out.print("아이디 : ");
	        String id = sc.nextLine();
	        System.out.print("암호 : ");
	        String pwd = sc.nextLine();
	        System.out.print("이름 : ");
	        String name = sc.nextLine();
	        System.out.print("성별(M/F) : ");
	        String gender = sc.nextLine().toUpperCase();
	        System.out.print("나이 : ");
	        int age = sc.nextInt();
	        sc.nextLine();
	        System.out.print("이메일 : ");
	        String email = sc.nextLine();
	        System.out.print("전화번호(-빼고입력): ");
	        String phone = sc.nextLine();
	        System.out.print("주소 : ");
	        String address = sc.nextLine(); 
	        System.out.print("취미(,로 공백없이 나열): ");
	        String hobby = sc.nextLine();
	      
	      Member m = new Member(id, pwd, name, gender, age, email, phone, address, hobby);
	      
	      return m;
	   }
	   
	   /**
	    * 회원아이디로 검색된 회원 출력하는 메소드
	    * @param m
	    */
	   public void displayMember(Member m) {
	      System.out.println("\n조회된 회원정보는 다음과 같습니다.");
	      System.out.println("아이디\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일"); // \t가 tab인듯?
	      System.out.println("----------------------------------------------------------------------");
	      System.out.println(m);
	      // 출력구문
	   }
	   
	   /**
	    * 사용자 요청에 의한 회원 전체 조회 시 메세지 출력하는 메소드
	    * @param
	    */
	   public void displayMemberList(ArrayList<Member> list) {
	      System.out.println("\n조회된 회원정보는 다음과 같습니다.");
	      System.out.println("아이디\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일"); // \t가 tab인듯?
	      System.out.println("----------------------------------------------------------------------");
	      for(Member m : list) {
	         System.out.println(m);
	      }
	   }
	   
	   /**
	    * 사용자 요청 성공에 따른 메세지 출력하는 메소드
	    * @param message
	    */
	   public void displaySuccess(String message) {
	      System.out.println("서비스 요청 성공 : " + message);
	   }
	   
	   /**
	    * 사용자 요청 실패에 따른 메세지 출력하는 메소드
	    * @param message
	    */
	   public void displayError(String message) {
	      System.out.println("서비스 요청 실패 : " + message);
	   }

	   public void displayDeleteSuccess(String message) {
	      System.out.println("서비스 요청 성공 : " + message);
	      loginMem = null;
	   }

	   public void displayMyPage(Member loginMem) {
	      System.out.println("loginMem : " + loginMem);
	      
	      MemberMenu.loginMem = loginMem;
	      System.out.println("\n" + loginMem.getUserName() + "님 환영합니다.");
	      
	   }

}
