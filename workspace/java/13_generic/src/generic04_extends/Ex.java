package generic04_extends;

public class Ex {

	public static void main(String[] args) {
		Parent<String, String> p1 = new Parent<>();
		p1.setKind("TV");
		p1.setModel("울트라 컬러 뭐시깽이");
		
		Child<String, String, String> ch1 = new Child<>();
		ch1.setKind("Car");
		ch1.setModel("S class");
		ch1.setCompany("Benz");
		
		System.out.println("=========================================");
		
		Storage<Integer> storage = new StorageImpl<>(10);
		storage.add(200, 0); 		// 200이란 값을 0번째 인덱스에 값을 넣어줌
		System.out.println(storage.get(0));
		storage.add(300, 1);
		System.out.println(storage.get(1));
		
		
	}

}
