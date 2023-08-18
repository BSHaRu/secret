package a.object.toString;

public class Person {
	
	String name;
	int height;
	int weight;
	
	// alt + s + a 
	public Person(String name, int height, int weight) {
		super();
		this.name = name;
		this.height = height;
		this.weight = weight;
	}

	// alt + s + s + s
	@Override	
	public String toString() {
		return "Person [name=" + name + ", height=" + height + ", weight=" + weight + "]";
	}

	// alt + s + v  - 부모의 (toString)을 재정의함
//	@Override
//	public String toString() {
//		return super.toString();
//	}
	
	
	
	
}
