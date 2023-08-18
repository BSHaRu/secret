package GUIDE.account;

import java.util.Scanner;

public class BankApplication_T_my {
	
	Account[] account = new Account[100];
	
	Scanner sc = new Scanner(System.in);
	
	BankApplication_T_my(){
		run();
	}
	
	void run() {
		// 1. 계좌생성
		// 2. 계좌조회 
		// 3. 예금
		// 4. 출금
		// 5. 종료
		boolean isRun = true;
		
		
		while(isRun) {
			System.out.println("========================================");
			System.out.println("1.계좌생성 2.계좌조회 3.예금 4.출금 5.종료");
			System.out.println("========================================");
			
			int selectNo = sc.nextInt();
			if(selectNo == 1) {
				// 계좌생성
				createAccount();
			}else if(selectNo ==2) {
				// 계좌조회
				selectAccount();
			}else if(selectNo ==3) {
				// 예금
				deposit();
			}else if(selectNo ==4) {
				// 출금
				withdraw();
			}else if(selectNo ==5) {
				// 종료
				isRun = false;
				sc.close();
			}
		} // while문 종료
		System.out.println("프로그램 종료");
	} // run 종료
	
	
	//계좌생성
	void createAccount() {
		Account acc = new Account();
		
		System.out.println("-----------------");
		System.out.println("계좌 생성");
		System.out.println("-----------------");
		
		System.out.print("계좌주: ");
		acc.owner = sc.next();
		
		System.out.print("계좌번호: ");
		acc.ano = sc.next();
		
		System.out.print("초기입금액: ");
		acc.balance = sc.nextInt();
		
		System.out.print("비밀번호: ");
		acc.password = sc.next();
		
		for(Account a : account) {
			if(a != null && a.ano.equals(acc.ano)) {
				System.out.println("이미 등록된 계좌번호 입니다.");
				return; 
				/* 
				 	return : 함수탈출
				 	break : 가까운 반복문 탈출
				 */
			}
		}
		
		for(int i=0; i<account.length; i++) {
			if(acc == null) {
				account[i] = acc;
				System.out.println("계좌가 등록되었습니다.");
				break;
			}
		} // for문 종료
		
	}
	// 계좌조회
	void selectAccount() {
		System.out.println("-----------------");
		System.out.println("계좌 조회");
		System.out.println("-----------------");
		System.out.print("계좌번호: ");
		String ano = sc.next();
		
		System.out.print("비밀번호: ");
		String password = sc.next();
		
		Account accArrVal = findAccount(ano, password);
		
	}
	// 예금
	void deposit() {
		System.out.println("-------------------");
		System.out.println("입금");
		System.out.println("-------------------");
		
		System.out.print("계좌번호: ");
		String ano = sc.next();
		
		System.out.print("비밀번호: ");
		String password = sc.next();
		
	}
	//출금
	void withdraw() {
		System.out.println("-------------------");
		System.out.println("출금");
		System.out.println("-------------------");
		
		System.out.print("계좌번호: ");
		String ano = sc.next();
		
		System.out.print("비밀번호: ");
		String password = sc.next();
		
	}
	
	// 계좌번호와 비밀번호가 일치하는 Account 객체를 찾아서 반환 
	Account findAccount(String ano, String password) {
		for(int i=0; i < account.length; i++) {
			Account accArrVal = account[i];
			if(accArrVal != null && ano.equals(accArrVal.ano) && password.equals(accArrVal.password)) {
				return accArrVal;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		new BankApplication_T_my();
	}
}


