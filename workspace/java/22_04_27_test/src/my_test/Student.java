package my_test;

public class Student {

	String school;
	int grade;
	String name;
	
	Student(){
	}

	public Student(String school, int grade, String name) {
		this.school = school;
		this.grade = grade;
		this.name = name;
	}

	public static void main(String[] args) {
		Student kim = new Student();
		kim.school = "고구마";
		kim.grade = 4;
		kim.name= "김유신";

		Student choi = new Student("학교",1,"홍길동");
		System.out.println(choi.school);
		System.out.println(choi.grade);
		System.out.println(choi.name);
	}
}

