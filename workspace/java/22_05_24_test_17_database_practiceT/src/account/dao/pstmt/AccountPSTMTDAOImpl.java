package account.dao.pstmt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import account.dao.AccountDAO;
import account.vo.AccountVO;
import util.DBUtil;

public class AccountPSTMTDAOImpl implements AccountDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	@Override
	public void insert(AccountVO account) {
		// 작성
		String sql = "INSERT INTO tbl_account VALUES(?,?,?,?)";
		try {
			conn = DBUtil.getConnection();
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, account.getAno());
			pstmt.setString(2, account.getOwner());
			pstmt.setInt(3, account.getBalance());
			pstmt.setString(4, account.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
		}
	}

	@Override
	public int update(AccountVO account) {
		int result = 0;
		// 작성
		String sql = "UPDATE tbl_account SET balance = ? WHERE ano = ?";
		
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account.getBalance());
			pstmt.setString(2, account.getAno());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
		}
		
		return result;
	}

	@Override
	public AccountVO selectAccount(String ano) {
		AccountVO account = null;
		// 작성
		String sql = "SELECT * FROM tbl_account WHERE ano = ?";
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ano);
			
			rs = pstmt.executeQuery();
			account = getAccount(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs,pstmt);
		}
		
		return account;
	}
	
	public AccountVO getAccount(ResultSet rs) throws SQLException {
		AccountVO account = null;
		// 작성
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
		// 작성
		String sql = "SELECT * FROM tbl_account WHERE ano = ? AND password = ?";
		
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ano);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			account = getAccount(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs,pstmt);
		}
		
		return account;
	}

}







