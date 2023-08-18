package return_test;

public class Car {

	int gas;	// 필드값
	
	
	void setGas(int gas) {
		this.gas = gas;
	} // 메소드로 매개값을 받아서 gas의 필드값을 변경.
	
	boolean isLeftGas() {
		if(gas == 0) {					
			System.out.println("gas가 없습니다.");
			return false;
		}
		System.out.println("gas가 있습니다.");
		return true;
	}
	
	void run() {
		while(true) {
			if(gas > 0) {
				System.out.printf("달립니다.(gas잔량 : %d) %n",gas);
				gas -= 1;
			}else {
				System.out.printf("멈춥니다.(gse잔량 : %d) %n",gas);
				return;
			} // else문 종료
		} // while문 종료
	}
}
