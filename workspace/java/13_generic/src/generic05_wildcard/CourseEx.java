package generic05_wildcard;

import java.util.Arrays;

public class CourseEx {

	public static void registerCoures(Course<?> course) {	//Course를 받을껀데 어떤타입인지 몰라서 <?>를 하는거임
		System.out.println("["+course.getName()+" 수강생]");
		for(int i=0; i<course.getStudents().length; i++) {
			System.out.print(course.getStudents()[i]+ " ");
		}
		System.out.println();
		
	}
	
	public static void registerStudent(Course<? extends Student> course) {
		System.out.println("["+course.getName()+" 수강생]");
		System.out.println(Arrays.toString(course.getStudents()));
	}
	
	public static void registerWorker(Course<? super Worker> course) {
		System.out.println("["+course.getName()+" 수강생]");
		System.out.println(Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
		Course<Person> personCourse = new Course<>("일반인 과정", 5); // (String name, length);
		personCourse.add(new Person("일반인"));
		personCourse.add(new Student("학생"));
		personCourse.add(new HighStudent("고등학생"));
		personCourse.add(new Worker("직장인"));
		personCourse.add(new Person("일반인2"));
		
		Course<Student> studentCours = new Course<>("학생과정",5);
		studentCours.add(new Student("일반 학생"));
		studentCours.add(new HighStudent("고등학생"));
//		studentCours.add(new Person("일반인"));	// 이건 못들어감
		
		Course<HighStudent> highStudentCours = new Course<>("고등학생 과정",5);
		highStudentCours.add(new HighStudent("고등학생"));
//		highStudentCours.add(new Student("일반 학생")); // 이거 안됨
		
		Course<Worker> workerCourse = new Course<>("직장인 과정", 5);
		workerCourse.add(new Worker("홍길동"));
		
		System.out.println("=======================================================");
		registerCoures(personCourse);
		registerCoures(workerCourse);
		registerCoures(studentCours);
		registerCoures(highStudentCours);
		
		System.out.println("=======================================================");
		registerStudent(studentCours);
		registerStudent(highStudentCours);
		// registerStudent(workerCourse);
		
		System.out.println("=======================================================");
		// worker 거나 worker의 상위 객체만 가능
		registerWorker(workerCourse);
		registerWorker(personCourse);
		Course<Object> objCourse = new Course<>("전체 교육과정",5);	// 최상위 객체가 obj라서 가능함
		
	} // main 종료
}
