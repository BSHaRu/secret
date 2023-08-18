package f.cooperation;

public class TakeTrans {

	public static void main(String[] args) {
		Student studentHong = new Student("홍길동",8000);
		Student studentKim = new Student("김유신",2,20000);
		
		Bus bus100 = new Bus(100);
		studentHong.takeBus(bus100);
		bus100.showInfo();
		
		Subway subwayGreen = new Subway("2호선");
		studentKim.takeSubway(subwayGreen);
		studentKim.showInfo();
		subwayGreen.showInfo();
		
		Worker workerLee = new Worker("이순신", 21000000);
		workerLee.takeVehicle(bus100);
		workerLee.showInfo();
		bus100.showInfo();
	}

}
