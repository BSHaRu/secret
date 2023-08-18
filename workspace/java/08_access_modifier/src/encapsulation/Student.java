package encapsulation; // 캡슐화

public class Student {

	public String name;		// 이름
	private int age;		// 나이
	private int grade;		// 학년

	public void setAge(int age) {	// private의 age를 입력 받기 위해서 setAge를 함
		if(age < 1) {
			this.age = 1;
			return;
		} // if문이 검증임 -> private는 검증을 한번 걸친 후 해당 값을 대입시켜주는게 정확하니깐? 안전하니깐?
		this.age = age;
	}
	
	public int getAge() {		// private의 age를 사용 하기 위해서 setAge를 함
		return this.age;
	}
	
	// grade도 age처럼 private로 되어있으니깐 set & get으로 입력 받고 출력함
	public void setGrade(int grade) {
		if(grade < 1 || grade > 3) {
			this.grade = 1;
			return;
		}
		this.grade = grade;
	}
	
	public int getGrade() {
		return this.grade;
	}

	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", grade=" + grade + "]";
	}
}


