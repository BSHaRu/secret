package guide_book.dao.pstmt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import guide_book.Book;
import guide_book.dao.BookDAO;
import util.DBHelper;

public class BookPSTMTDAOImpl implements BookDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	@Override
	public int insert(Book book) {
		int result = 0;
		String sql = "INSERT INTO tbl_book(title,author) VALUES(?,?)";
		
		try {
			conn = DBHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(pstmt);
		}
		
		return result;
	}

	@Override
	public ArrayList<Book> select() {
		ArrayList<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM tbl_book ORDER by num DESC";
		
		try {
			conn = DBHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				books.add(new Book(
					rs.getInt(1),		// 도서 번호
					rs.getString(2),	// 책 제목
					rs.getString(3),	// 저자
					rs.getTimestamp(4)	// 날짜
				)); // books 종료
			} // while 종료
			System.out.println("등록된 책 개수 : " + books.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs,pstmt);
		} // try catch 종료
		return books;
	}
	
	@Override
	public Book selectBook(int num) {
		Book book = null;
		String sql = "SELET * FROM tbl_book WHERE num = " + num;
		
		try {
			conn = DBHelper.getConnection();
			pstmt= conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				Date regDate = rs.getTimestamp(4);
				
				book = new Book();
				book.setNum(num);
				book.setTitle(title);
				book.setAuthor(author);
				book.setRegDate(regDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs,pstmt);
		}
		
		return book;
	}

	@Override
	public int update(Book book) {
		int result = 0;
		String sql = "UPDATE tbl_book SET title = ?, author = ? WHERE = ? ";
		
		try {
			conn = DBHelper.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setInt(3, book.getNum());
			
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(pstmt);
		}
		
		return result;
	}

	@Override
	public int delete(int num) {
		String sql = "DELEDT FROM tbl_book WHERE num = ? ";
		conn = DBHelper.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(pstmt);
		}
		return 0;
	}

}
