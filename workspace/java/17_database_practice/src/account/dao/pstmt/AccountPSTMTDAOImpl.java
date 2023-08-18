package account.dao.pstmt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.x.protobuf.MysqlxConnection.CapabilitiesGetOrBuilder;

import account.dao.AccountDAO;
import account.vo.AccountVO;
import util.DB;

public class AccountPSTMTDAOImpl implements AccountDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public void insert(AccountVO account) {
		String sql = "INSERT INTO tbl_account(ano,owner,balance,password) VALUES(?,?,?,?)";
		
		conn = DB.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account.getAno());
			pstmt.setString(2, account.getOwner());
			pstmt.setInt(3, account.getBalance());
			pstmt.setString(4, account.getPassword());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
		}
	}

	@Override
	public int update(AccountVO account) {
		int result = 0;
		String sql = "UPDATE tbl_account SET balance = ? WHERE ano = ?"; 
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account.getBalance());
			pstmt.setString(2, account.getAno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
		}
		
		return result;
	}

	@Override
	public AccountVO selectAccount(String ano) {
		AccountVO account = null;
		String sql = "SELECT * FROM tbl_account WHERE ano = " + ano;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, ano);
			rs = pstmt.executeQuery();
			/*
			if(rs.next()) {
				ano = rs.getString(1);
				String owner = rs.getString(2);
				int balance = rs.getInt(3);
				String password = rs.getString(4);
				
				account = new AccountVO();
				account.setAno(ano);
				account.setOwner(owner);
				account.setBalance(balance);
				account.setPassword(password);
			}
			*/
			account = getAccount(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
		}
		
		return account;
	}
	
	public AccountVO getAccount(ResultSet rs) throws SQLException {
		AccountVO account = null;
		/*
		if(rs.next()) {
			String ano = rs.getString(1);
			String owner = rs.getString(2);
			int balance = rs.getInt(3);
			String password = rs.getString(4);
			
			account = new AccountVO();
			account.setAno(ano);
			account.setOwner(owner);
			account.setBalance(balance);
			account.setPassword(password);
		}
		*/
		if(rs.next()) {
			account = new AccountVO(
					rs.getString(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getString(4)
					);
		}
		return account;
	}
	
	@Override
	public AccountVO selectAccount(String ano, String password) {
		AccountVO account = null;
		String sql = "SELECT * FROM tbl_account WHERE ano = ? AND password = ?";
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ano);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			account = getAccount(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs,pstmt);
		}
		
		return account;
	}

}







