package guide_book;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
	
	// 도서관리 번호
	private int num;
	// 도서 제목
    private String title;
	// 도서 저자
	private String author;
	// 도서 등록일
	private long regDate;
	
	public Book() {}

	public Book(int num, String title, String author, long regDate) {
		this.num = num;
		this.title = title;
		this.author = author;
		this.regDate = regDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getRegDate() {
		return new SimpleDateFormat("yyyy-MM-dd a hh:mm").format(new Date(this.regDate));
	}

	public void setRegDate(long regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Book) {
			Book b = (Book)obj;
			if(b.getTitle().equals(this.title)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Book [num=" + num + ", title=" + title + ", author=" + author + ", regDate=" + getRegDate() + "]";
	}

}
