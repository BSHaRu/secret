package a_static_test;

class Student{
	
	static String school;		// static을 남발하면 메모리 공간 활용하기 어려워지기 때문에 적당히써야됨 
	String name;
	int age;
	String grade;
}

public class StaticTest_01 {

	public static void main(String[] args) {
		Student stu1 = new Student();
		stu1.name = "홍길동";
		stu1.age = 30;
		stu1.grade = "A"; // 'A';는 왜 오류임???
		Student.school = "부산대학교";
		
		System.out.println(stu1.age);
		System.out.println(stu1.grade);
		System.out.println(stu1.name);
		System.out.println(stu1.school); // Student.school 해도 뜨는데 
										// stu1(인스턴트 멤버)로 접근해서 school을 찾고 있어서 노란줄이 뜨는거임
										// -> 문법적 오류는 아니긴 함.
		
		Student stu2 = new Student();
		stu2.name = "이순신";
		stu2.age = 20;
		stu2.grade = "B";
		
		System.out.println(stu2.age);
		System.out.println(stu2.grade);
		System.out.println(stu2.name);
		System.out.println(stu2.school);
		
		Student.school = "서울대학교";
		System.out.println(stu1.school);
		System.out.println(stu2.school);
		System.out.println(stu1.school == stu2.school);
		
//		Math.random();  // Math 클래스에서 random이 static으로 들어가있어서 그냥 호출해도 써지는거였음
	}

}
