package c.methods;

public class AccountEx {

	public static void main(String[] args) {

		Account kim = new Account();
		kim.deposit(1000000);
		kim.withdraw(100000000);
		
		Account park = new Account();
		park.deposit(1000);
		park.withdraw(100000000);
		park.deposit(-20000);
		
		System.out.println("kim balance : " + kim.balance);
		System.out.println("park balance : " + park.balance);
	}

}
