package encapsulation;

public class Student_Ex {

	public static void main(String[] args) {
		
		Student kim = new Student();
		kim.name = "김유신";
//		kim.age = 40; 	// age는 private로 막아놨기때문에 그냥 age로는 안됨
		kim.setAge(-40);
		kim.setGrade(6);
		
		System.out.println(kim.getAge());
		System.out.println(kim.getGrade());
		
		kim.setAge(20);
		kim.setGrade(3);
		String kimInfo = kim.toString();
		System.out.println(kimInfo);
	}

}
