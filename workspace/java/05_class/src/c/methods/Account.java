package c.methods;

public class Account {

	// 최소 예금액
	int minDeposit;
	// 최대 예금액
	int maxDeposit = 1000000;
	// 잔고
	int balance;
	
	// class에서 ;으로 종료는 초기화만 가능하다. 
	
	// 예금
	void deposit(int amount) {
		if(amount > maxDeposit || amount < minDeposit) {
			System.out.println("예금 할 수 있는 금액이 아닙니다.");
			return;
		}
		balance += amount;
	}
	//출금
	void withdraw(int amount) {
		int result = balance - amount;
		if(result < 0) {
			System.out.println("출금 할 수 없는 금액입니다.");
		}else {
			balance = result;
		}
	}
	
}
