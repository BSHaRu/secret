package f.cooperation;

public class Subway {

	String lineNumber;	// 지하철 노선 번호
	int passengerCount; // 승객수
	int money;			// 수입 금액
	
	public Subway(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	// 승객 탑승 시 금액 지불
	void take(int money) {
		this.money += money;
		passengerCount++; // this.passengerCount를 붙여주는게 좋은데 지금은 따로 뭐 없어서 그냥씀 
	}
	
	// 지하철 정보를 출력하는 method
	void showInfo() {
		System.out.printf(
			"%s의 승객은 %d명이고, 수입은 %d입니다.\n",
			lineNumber, passengerCount, money);
	}
	
}
