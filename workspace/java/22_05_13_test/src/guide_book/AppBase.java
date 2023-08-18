package guide_book;

import java.util.List;
import java.util.Scanner;

public abstract class AppBase {
	// 문자열 입력
	Scanner scanLine = new Scanner(System.in);
	// 선택번호 입력
	Scanner scanSelectnum = new Scanner(System.in);
	
	// 도서목록
	List<Book> bookList;
	
	// 프로그램 실행 flag
	boolean isRun = true;
	
	// 메인 메뉴 선택 번호
	int selectNo = 0;
	
	// 도서관리 번호
	int count = 0;	

	public AppBase(){
		// 책정보 추가
		run();
	}
	
	public void run() {
		while(isRun) {
			System.out.println("=======================================================");
			System.out.println("1.도서등록 | 2. 도서목록 | 3.도서수정 | 4.도서삭제 | 5.저장 | 6.종료");
			System.out.println("=======================================================");
			selectNo = getNum("번호를 선택하세요>");
			
			switch(selectNo) {
				case 1 :
					registerBook();
					break;
				case 2 :
					selectBook();
					break;
				case 3 :
					updateBook();
					break;
				case 4 : 
					deleteBook();
					break;
				case 5 : 
					saveBook();
					break;
				case 6 : 
					terminate();
					break;	
				default : 
					System.out.println("등록된 메뉴가 아닙니다.");
			} // switch문 종료
		}// while문 종료
	} // run 종료
	
	// 도서 등록
	public abstract void registerBook();
	
	// 도서 목록 출력
	public abstract void selectBook();

	// 도서 정보 수정
	public abstract void updateBook() ;
	
	// 도서 목록에서 책 정보 삭제
	public abstract void deleteBook();
	
	// 도서 목록 정보 파일에 저장
	public abstract void saveBook();
	
	// 프로그램 종료
	public abstract void terminate();
	
	// 도서관리번호로 책 정보 찾기
	public Book findBook(int num) {
		for(Book book : bookList) {
			if(book.getNum() == num) {
				return book;
			}
		}
		return null;
	}
	
	// 사용자에게 메시지를 전달 받아 출력하고 문자열 값 받아 반환
	String getData(String message) {
		System.out.println(message);
		return scanLine.nextLine();
	}
	
	// 번호 선택 받기
	int getNum(String message) {
		System.out.println(message);
		if(!scanSelectnum.hasNextInt()) {
			System.out.println("숫자를 입력해주세요.");
			scanSelectnum.next();
			return 0;
		}
		return scanSelectnum.nextInt();
	}
}
