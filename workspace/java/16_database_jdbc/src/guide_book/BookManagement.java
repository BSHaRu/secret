package guide_book;

import java.util.ArrayList;

import guide_book.dao.BookDAO;
import guide_book.dao.pstmt.BookPSTMTDAOImpl;

public class BookManagement extends AppBase{
	
	public BookManagement(BookDAO dao) {
		super(dao);
	}

	// 도서 등록
	public void registerBook() { 
		// title & author 정보 입력 받아서 DB에 저장
		String title = getData("등록할 도서의 책 제목을 입력해 주세요 >");
		String author = getData("등록할 도서의 저자를 입력해 주세요 >");
		Book vo = new Book(title, author);
		
		int result = dao.insert(vo);
		
		if(result > 0) {
			System.out.println("등록 완료");
		}else {
			System.out.println("등록 실패");
		}
	}
	
	@Override 	// 도서 검색
	public void selectBook() {
		ArrayList<Book> books = dao.select();
		for(Book b : books) {
			System.out.println(b);
		}
	}
	
	@Override
	public void updateBook() {
		int bookNum = getNum("수정하실 도서의 관리번호를 입력해주세요.");
		Book book = dao.selectBook(bookNum);
		if(book == null) {
			System.out.println("입력하신 번호의 책이 존재하지 않습니다.");
			return;
		}
		
		boolean isUpdate = true;
		while(isUpdate) {
			System.out.println("==============================");
			System.out.println("1.제목수정 | 2.저자수정 | 3.수정완료");
			System.out.println("==============================");
			
			selectNo = getNum("번호 입력 > ");
			switch(selectNo) {
				case 1:
					System.out.println("제목 수정");
					String title = getData("제목 입력 >");
					book.setTitle(title);
					break;
				case 2:
					System.out.println("저자 수정");
					String author = getData("저자 입력 >");
					book.setAuthor(author);
					break;
				case 3:
					System.out.println("수정 완료");
					dao.update(book);
					isUpdate = false;
					break;
			} // switch 종료
		} // while 종료
	} // update 종료
	
	@Override
	public void deleteBook() {
		int bookNum = getNum("삭제할 도서의 관리번호를 입력해 주세요.");
		int result = dao.delete(bookNum);
		
		if(result > 0) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("존재하지 않는 번호입니다.");
		}
		
	} // deleteBook 종료
	
	@Override // 실시간 저장이라서 saveBook은 여기선 입력안해도됨
	public void saveBook() {
	}
	
	@Override
	public void terminate() {
		System.out.println("프로그램 종료");
		isRun = false;
	}
	
	
	public static void main(String[] args) {
//		new BookManagement(new BookSTMTDAOImpl());
		new BookManagement(new BookPSTMTDAOImpl()); 
	} // main 종료
}




