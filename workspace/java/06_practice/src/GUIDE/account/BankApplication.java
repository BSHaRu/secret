package GUIDE.account;

import java.util.Scanner;

public class BankApplication {
	
	Account[] account = new Account[100];
	
	Scanner sc = new Scanner(System.in);
	
	BankApplication(){
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
			}
		}
		
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
		
		for(Account a : account) {
			if(a != null && ano.equals(a.ano) && password.equals(a.password)) {
				String str = a.toString();
				System.out.println(str);
				break;
			}else {
				System.out.println("계좌번호와 비밀번호를 다시 확인하고 입력해주세요");
				continue;
			}
		} // for문 종료
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
		
		for(Account a : account) {
			if(a != null && ano.equals(a.ano) && password.equals(a.password)) {
				System.out.print("예금액: ");
				int balance = sc.nextInt();
				System.out.printf("%s님의 계좌에 %d원 입금이 완료 되었습니다.\n",a.owner, balance);
				a.balance += balance;
				break;
			}else {
				System.out.println("계좌번호와 비밀번호를 다시 확인하고 입력해주세요");
			}
		} //  for문 종료
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
		
		for(Account a : account) {
			if(ano.equals(a.ano) && password.equals(a.password)) {
				System.out.print("출금액: ");
				int balance = sc.nextInt();
				
				if(a.balance > balance || a.balance < 0 ) {
					System.out.println("금액을 다시 입력해주세요.");
					continue;
				} // for > if > if 종료
				
				System.out.printf("%s님의 계좌에 %d원 출금이 완료 되었습니다.\n",a.owner, balance);
				a.balance -= balance;
				break;
			}else {
				System.out.println("계좌번호와 비밀번호를 다시 확인하고 입력해주세요");
				continue;
			}
		}
	}
	
	// 계좌번호와 비밀번호가 일치하는 Account 객체를 찾아서 반환 
	Account findAccount(String ano, String password) {
		return null;
	}
	
	public static void main(String[] args) {
		new BankApplication();
	}
}


