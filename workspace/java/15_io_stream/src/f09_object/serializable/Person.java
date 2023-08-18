package f09_object.serializable;

import java.io.Serializable;

public class Person implements Serializable{ // Serializable : 직렬화가 가능하다는걸 알려줘야됨

	private String name;
	transient private int age; // transient : 직렬화 데이터에서 사라짐
								// cf) 필드에서 사라지는게 아님
	
	// alt + s + r   | alt + s + s + s
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
}
