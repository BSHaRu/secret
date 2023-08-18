package c7_comparable;

public class Person implements Comparable<Person>{
	
	private String name;
	private int age;
	
	// 생성자 and toString
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Person o) { // 제네릭으로 Person으로 지정했기때문에 Person으로 나옴
		int result = 0;				// 제네릭 안하면 Object로 나와서 타입 다시 지정해주고 해야됨
		// 내가 가진 값이 크면 음수
		// 매개변수로 넘겨받은 객체의 age가 크면 양수
		result = o.age - this.age;
		System.out.println(o.age +"-"+ this.age);
		/* 이렇게하면 오름차순으로 정렬 됨
		result = this.age - o.age;
		System.out.println(this.age +"-"+ o.age);
		*/
		System.out.println(result);
		return result;
	}

}
