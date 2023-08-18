package d.contructor;

public class CarEx {

	public static void main(String[] args) {

		Car basic = new Car();
		System.out.println(basic.company);
		
		Car myCar = new Car("Benz", "s class", "balck");
		Car yourCar = new Car(null, null, 0);
		
		System.out.println(myCar);
		System.out.println(yourCar);
		
		yourCar.company = "Kia";
		yourCar.model = "K-5";
		
		System.out.printf("내 차는 %s에 %s이고 니 차는 %s에 %s다.",myCar.company,myCar.model,yourCar.company,yourCar.model);
		
	}

}
