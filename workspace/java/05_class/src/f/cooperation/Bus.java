package f.cooperation;

public class Bus {

	int busNumber;			// 버스 번호 
	int passengerCount;		// 승객 수
	int money;				// 버스 수입
	
	// 버스 번호를 매개변수로 넘겨 받는 생성자 생성.
	Bus(int busNumber){
		this.busNumber = busNumber;
	}
	
	// 승객 탑승 시 금액 지불
	void take(int money) {
		this.money += money;
		passengerCount++; // this.passengerCount를 붙여주는게 좋은데 지금은 따로 뭐 없어서 그냥씀 
	}
	
	// 버스 정보를 출력하는 method
	void showInfo() {
		System.out.printf(
			"버스 %d번의 승객은 %d명이고, 수입은 %d입니다.\n",
			busNumber, passengerCount, money);
	}
}
