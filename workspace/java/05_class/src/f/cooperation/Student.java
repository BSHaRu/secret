package f.cooperation;

public class Student {
	String studentName;	// 학생이름
	int grade;			// 학년
	int money;			// 학생이 가지고 있는 금액
	
	Student(String studentName){
		this(studentName, 3, 10000);
	}
	
	Student(String studentName, int money){
		this(studentName, 3, money);
	}
	
	public Student(String studentName, int grade, int money) {
		this.studentName = studentName;
		this.grade = grade;
		this.money = money;
	}
	
	void takeBus(Bus bus) {
		int pay = getPay();
		/* 중복되어 있으면 함수를 통해서 해결 가능
		int pay = 0;
		
		switch(grade) {
			case 1 : pay = 600;
				break;
			case 2 : pay = 800;
				break;
			case 3 : pay = 1000;
				break;
		} // switch문 종료
		 					*/
		bus.take(pay);
		this.money -= pay;
	}//	takeBus 종료
	
	void takeSubway(Subway subway) {
		int pay = getPay();
		/* 중복되어 있으면 함수를 통해서 해결 가능
		int pay = 0;
		
		switch(grade) {
			case 1 : pay = 600;
				break;
			case 2 : pay = 800;
				break;
			case 3 : pay = 1000;
				break;
		} // switch문 종료
		 					*/
		subway.take(pay);
		this.money -= pay;
	}// takeSubway 종료
	
	int getPay() {
		int pay = 0;
		
		switch(grade) {
			case 1 : pay = 600;
				break;
			case 2 : pay = 800;
				break;
			case 3 : pay = 1000;
				break;
		} // switch문 종료
		return pay;
	}
	
	void showInfo() {
		System.out.printf("%s님의 잔액은 %d 입니다.\n",studentName, money);
	}
}

	
