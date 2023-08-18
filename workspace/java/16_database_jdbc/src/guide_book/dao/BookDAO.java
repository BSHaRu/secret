package guide_book.dao;

import java.util.ArrayList;

import guide_book.Book;

// DAO(Database Access Object)
public interface BookDAO {
	
	// 도서 삽입
	int insert(Book book);
	
	// 도서 목록
	ArrayList<Book> select();
	
	// 도서 수정
	int update(Book book);
	
	// 책번호로 Book 정보를 검색
	Book selectBook(int num);
	
	// 삭제
	int delete(int num);
	
	
}

