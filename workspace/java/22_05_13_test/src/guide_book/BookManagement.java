package guide_book;

public class BookManagement extends AppBase{
	
	@Override
	public void registerBook() {
		System.out.println("1. 도서등록");
		String title = getData("등록할 도서의 책 제목을 입력해 주세요 >");
		String author = getData("등록할 도서의 저자를 입력해 주세요 >");
		
	}
	
	@Override
	public void selectBook() {
	}
	
	@Override
	public void updateBook() {
	}
	
	@Override
	public void deleteBook() {
	}
	
	@Override
	public void saveBook() {
	}
	
	@Override
	public void terminate() {
	}
	
	
	
	public static void main(String[] args) {
		new BookManagement();
	} // main 종료
}




