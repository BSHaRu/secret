package g.object_array;

import java.util.Scanner;

public class StudentEx {

	public static void main(String[] args) {
		/*
		Student stu = new Student();
		stu.number = 1;
		stu.name = "홍길동";
		stu.score = 100;
		
		String info = stu.toString();
		System.out.println(info);
		 								*/
		Scanner sc = new Scanner(System.in);
		
		Student[] students = null;
		int selectNo = 0;
		int stuCount = 0;
		
		while(true) {
			System.out.println("============================================");
			System.out.println("1.학생수 | 2.정보입력 | 3.정보확인 | 4.분석 | 5.종료 ");
			System.out.println("============================================");
			System.out.println("번호를 선택해 주세요 > ");
			selectNo = sc.nextInt();
			
			if(selectNo == 1) {
				System.out.println("학생 수 입력 : ");
				stuCount = sc.nextInt();
				
				students = new Student[stuCount];
				System.out.println("학생수는 : "+students.length+"입니다.");
			}else if(selectNo == 2) {
				System.out.println("학생 정보 입력 : ");
				for(int i=0; i < students.length; i++) {
//					students[i] = new Student();
//					students[i].number = (i+1);
//					
//					System.out.println(students[i].number+"번째 학생의 이름을 입력해주세요.");
//					students[i].name = sc.next();
//
//					System.out.println(students[i].number+"번째 학생의 점수를 입력해주세요.");
//					students[i].score = sc.nextInt();
					
					Student stu = new Student();
					stu.number = i+1;
					
					System.out.println(stu.number+"번째 학생의 이름을 입력해주세요.");
					stu.name = sc.next();
					
					System.out.println(stu.number+"번째 학생의 점수를 입력해주세요.");
					stu.score = sc.nextInt();
					students[i] = stu;
				}// i-for문 종료
			}else if(selectNo == 3) {
				System.out.println("학생 정보 확인");
				for(Student s : students) {
					String str = s.toString();
					System.out.println(str);
				}
			}else if(selectNo == 4) {
				System.out.println("학생 정보 분석");
				int total = 0;
				double avg = 0;
				Student stuMax, stuMin; // 최고점, 최저점
				stuMax = stuMin = students[0];
				
				for(int i=0; i < students.length; i++) {
					int score = students[i].score;
					total += score;
					if(stuMax.score < score) stuMax = students[i];
					if(stuMin.score > score) stuMin = students[i];
				}
				avg = (double)total / stuCount;
				System.out.println("전체점수 : " +total);
				System.out.printf("평균 점수 : %.1f %n",avg);
				System.out.println("최고점 : "+stuMax.toString());
				System.out.println("최저점 : "+stuMin.toString());
			}else {
				System.out.println("프로그램 종료");
				break;
			}
			
		} // while문 종료
	} // main 종료

}
