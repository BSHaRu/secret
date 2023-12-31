package account.dao.stmt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import account.dao.AccountDAO;
import account.vo.AccountVO;

// Account
// Statement
// Database Access Object
public class AccountSTMTDAOImpl 
implements AccountDAO{
	
	// DBMS 연결 객체
	private Connection conn;
	// 연결정보를 가지고 질의를 실행하는 객체
	private Statement stmt;
	// 검색질의의 결과를 전달 받는 객체
	private ResultSet rs;
	
	// account의 정보를 tbl_account table에 삽입
	@Override
	public void insert(AccountVO account) {
		// 작성
	}
	
	// 계좌번호가 일치하는 계좌주의 
	// 잔고를 수정
	// UPDATE tbl_account SET balance = 값 WHERE ano = '계좌번호'
	@Override
	public int update(AccountVO account) {
		int result = 0;
		// 작성
		return result;
	}

	// SELECT * FROM tbl_account WHERE ano = '계좌번호';
	@Override
	public AccountVO selectAccount(String ano) {
		AccountVO acc = null;
		// 작성
		return acc;
	}
	
	// SELECT * FROM tbl_account  
	// WHERE ano = '계좌번호' AND password = '비밀번호'; 
	@Override
	public AccountVO selectAccount(String ano, String password) {
		AccountVO acc = null;
		// 작성
		return acc;
	}	
}









