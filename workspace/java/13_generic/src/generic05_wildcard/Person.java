package generic05_wildcard;

public class Person {

	private String name;

	// alt + s + a
	public Person(String name) {
		super();
		this.name = name;
	}

	// alt + s + r
	public String getName() {
		return name;
	}

	// alt + s + s+ s
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
	
}
