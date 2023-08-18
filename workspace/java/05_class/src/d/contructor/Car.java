package d.contructor;

public class Car {

	String company;
	String model;
	String color;
	int maxSpeed;
	
	int speed;
	
	// 생성자 : 반환 타입이 없이 생성된 친구
	// -> 생성자는 클래스명과 동일해야됨
	Car(){
		System.out.println("기본 생성자 호출");
	} 
//	생성자를 아에 정의를 하지 않거나, 기본 생성자를 만들어주지 않는 이상 
//	new Car()를 할 경우 컴파일러는 해당 생성자를 찾지 못함. 
//	-> 다른 생성자를 정의 할 경우 무조건 기본 생성자도 정의를 해줘야 사용가능
	
	// 생성자는 매개변수에 따라서 여러개 생성 가능
	Car(String c, String m, String cc){
		company = c;
		model = m;
		color = cc;
	}
	
	// 변수이름은 이렇게 일치 시켜줘야 다른사람이 작업할 때 해당값이 뭘 의미하는지 알 수 있다.
	Car(String company, String model, int speed){
		this.company = company;
		this.model = model;
		this.speed = speed;
	} // this = 생성자 자기 자신을 의미

	// 생성자 생성 단축키 : alt + s + a  
	public Car(String company, String model, String color, int maxSpeed, int speed) {
		this.company = company;
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
		this.speed = speed;
	}
	
}
