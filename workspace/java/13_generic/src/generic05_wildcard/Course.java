package generic05_wildcard;

public class Course<S> {	// Type저장이라 T를 써도 되는데 여기에는 학생들만 들어갈꺼라 그냥 알아보기 쉬워라고 S라고 지정한거임

	private String name; 	// 수강 과목
	private S[] students; 	// 수강생 정보를 저장할 배열
	
	public Course(String name, int capacity) {
		this.name = name;
		this.students = (S[])new Object[capacity];
	}

	// alt + s + r -> select getters 한거임
	public String getName() {
		return name;
	}

	public S[] getStudents() {
		return students;
	}
	
	public void add(S s) {
		for(int i=0; i < students.length; i++) {
			if(students[i] == null) {
				students[i] = s;
				break;
			} // if문 종료
		} // for문 종료
	} // add 종료
	
}
