package e.poly;

// db와 연결된 기능을 수행하는 객체
public interface DataBaseAccessObject {

	void connect();		// db 연결
	void select();		// 검색
	void insert();		// 정보 삽입
	void update(); 		// 정보 수정
	void delete();		// 정보 삭제
	void close(); 		// 연결 종료 - 자원해제
}
