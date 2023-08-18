package my_test;

public class Car {

	String color;
	int speed;
	int gear;
	
	Car(){
	}
	
	public Car(String color, int speed, int gear) {
		this.color = color;
		this.speed = speed;
		this.gear = gear;
	}
	
	void speedUp() {
		this.speed += 10;
	}
	
	void speedDown() {
		this.speed -= 10; 
	}
	
	String showInfo(){
		return "Car [color=" + color + ", speed=" + speed + ", gear=" + gear + "]";
	}

	public static void main(String[] args) {
		Car myCar = new Car();
		myCar.color = "red";
		myCar.speed = 0;
		myCar.gear = 1;
		System.out.println(myCar.showInfo());
		
		myCar.speedUp();
		myCar.gear = 2;
		System.out.println(myCar.showInfo());
		
		myCar.speedDown();
		myCar.gear = 1;
		System.out.println(myCar.showInfo());
	}
	
	
}
