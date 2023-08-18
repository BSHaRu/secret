package guide_book.dao.stmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import guide_book.Book;
import guide_book.dao.BookDAO;

public class BookSTMTDAOImpl implements BookDAO {

	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	public BookSTMTDAOImpl() {
		// connection 연결 정보 초기화
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata","bigdata","12345");
			System.out.println("Driver 연결 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver 정보가 없음");
		} catch (SQLException e) {
			System.out.println("연결정보 오류");
		}
	}

	@Override
	public int insert(Book book) {
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO tbl_book(title, author) "
					+" VALUES('"+book.getTitle()+"','"+book.getAuthor()+"')";
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(stmt != null) stmt.close();
				} catch (SQLException e) { }
		}
		return result;
	}

	@Override
	public ArrayList<Book> select() {
		ArrayList<Book> books = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM tbl_book ORDER by num DESC";
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int num = rs.getInt(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				Date regDate = rs.getTimestamp(4);
				
				Book book = new Book(num, title, author, regDate);
				books.add(book);
			} // while문 종료
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) { }
		}
		return books;
	}

	@Override
	public Book selectBook(int num) {
		String sql = "SELECT * FROM tbl_book WHERE num="+num;
		Book book = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {		// Primary key는 1개만 존재 하기때문에 if로 하는거
				book = new Book(
					rs.getInt(1),		// num
					rs.getString(2),	// title
					rs.getString(3),	// author
					rs.getTimestamp(4)	// regdate
				); // book 종료
			} // if 종료
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {}
		} // try catch 종료
		return book;
	}

	@Override
	public int update(Book book) {
		int result = 0;
		try {
			stmt = conn.createStatement();
			String sql = "UPDATE tbl_book SET "
					+" title = '"+book.getTitle()+"', "
					+" author = '"+book.getAuthor()+"' "
					+" WHERE num = "+book.getNum();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) { }
		} // try catch 종료
		return result;
	}
	
	@Override
	public int delete(int num) {
		int result = 0;
		
		try {
			String sql = "DELETE FROM tbl_book WHERE num = " + num;
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) {}
		}
		return result;
	} // delete 종료

}
