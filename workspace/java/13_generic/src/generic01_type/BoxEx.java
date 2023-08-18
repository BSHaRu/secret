package generic01_type;

public class BoxEx {

	public static void main(String[] args) {
		Box box1 = new Box();
		Apple apple = new Apple();
//		box1.obj = apple; // private라서 이렇게는 접근이 안됨
		box1.setObj(apple);
		
		Box box2 = new Box();
		Orange orange = new Orange();
		box2.setObj(orange);
		
		if(box1.getObj() instanceof Orange) {
			Orange oran = (Orange)box1.getObj();
			System.out.println("Orange가 들어가 있습니다.");
		}
		
//		Apple ap = (Apple) box2.getObj(); // 이렇게 하면 해당 타입이 다르기때문에 오류가 발생함
		
		// showbox
		ShowBox<Apple> box = new ShowBox<Apple>(); // T라고 지정한걸 Apple 타입으로 지정하는거임
												  // -> 기본 변수, set(), get()이 모두 Apple로 되는거임
//		box.setT(orange); // apple로 지정했기 때문에 orange 들어가있으면 당연히 오류가 뜸.
		box.setT(apple);
		
		Apple apple1 = box.getT();	// 그래서 이게 가능하다.
		
		ShowBox<Orange> orangeBox = new ShowBox<Orange> ();
		orangeBox.setT(orange);
		Orange orange1 = orangeBox.getT();
		
	} // main 종료

}
