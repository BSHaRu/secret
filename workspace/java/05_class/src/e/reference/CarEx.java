package e.reference;

public class CarEx {

	public static void main(String[] args) {
		Car car = new Car();
		System.out.println(car.company);
		System.out.println(car.model);
		System.out.println(car.maxSpeed);
		System.out.println(car.engine);
		
		car.company = "포르쉐";
		car.model = "포르쉐 911";
		car.maxSpeed = 400;
		
		Engine engine = new Engine(); // engine을 생성함
		car.engine = engine;		// engine을 car에 달아줌
		System.out.println(engine == car.engine);
		car.engine.rpm = 7000;
		System.out.println(engine.rpm);
		
		Car car2 = new Car();
		car2.company = "KIA";
		car2.model = "모닝";
		car2.engine = new Engine();			 // new Engine으로 새로 만들어서 새로운 Engine 위치를 받음
		System.out.println(car2.engine.rpm); // -> new Engine은 rpm을 지정 안했기때문에 초기값인 0을 반환함 
		car2.engine = engine;				 // 기존에 있는 engine으로 바꿔줌
		System.out.println(car2.engine.rpm); // -> 위에 지정한 rpm과 engine의 위치가 동일하기 때문에 그대로 받음
		
	}

}
