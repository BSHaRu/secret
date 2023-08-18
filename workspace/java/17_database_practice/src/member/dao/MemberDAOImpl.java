package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.vo.BackUpMemberVO;
import member.vo.MemberVO;
import util.DB;

public class MemberDAOImpl implements MemberDAO {

	Connection conn;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	
	// MemberVO 정보를 넘겨받아서 회원가입을 진행하고
	// 가입 완료된 회원의 정보를 검색하여 Member type으로 반환
	@Override
	public MemberVO join(MemberVO vo) {
		MemberVO member = null;
		// 작성
		String psql = "INSERT INTO tbl_member(mName,mId,mPw) VALUES(?,?,?)";
		String ssql = "SELECT * FROM tbl_member WHERE mId = '"+vo.getmId()+"'";
		try {
			psmt = conn.prepareStatement(psql);
			psmt.setString(1, vo.getmName());
			psmt.setString(2, vo.getmId());
			psmt.setString(3, vo.getmPw());
			psmt.executeUpdate();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(ssql);
			rs.next();
			member = getMember(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs,stmt,psmt);
		}
		return member;
	}
	
	// 로그인 - id 와 pw 가 일치하는 사용자일시 정보 전달
	@Override
	public MemberVO selectMember(String mId, String mPw) {
		MemberVO member = null;
		// 작성
		String sql = "SELECT * FROM tbl_member WHERE mId = ? " + " AND mPw = ?";
		try {
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, mId);
			psmt.setString(2, mPw);
			rs = psmt.executeQuery();
			rs.next();
			member = getMember(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs,psmt);
		}
		return member;
	}
	
	// 회원번호로 회원 정보 검색
	@Override
	public MemberVO selectMember(int mNum) {
		MemberVO member = null;
		// 작성
		String sql = "SELECT * FROM tbl_member WHERE mNum = "+mNum;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			member = getMember(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs,stmt);
		}
		return member;
	}

	// 기존에 동일한 아이디가 존재하는지 확인
	// true 사용가능 - 아이디가 존재하지 않으면
	// false 사용불가 - 아이디가 존재하면
	@Override
	public boolean selectMember(String mId) {
		boolean isChecked = true;
		// 작성
		String sql = "SELECT * FROM tbl_member WHERE mId = '"+mId+"'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				isChecked = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs,stmt);
		}
		return isChecked;
	}

	// 전체 회원 목록을 ArrayList에 담아서 반환
	@Override
	public ArrayList<MemberVO> select() {
		ArrayList<MemberVO> members = new ArrayList<>();
		// 작성
		String sql = "SELECT * FROM tbl_member";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				members.add(getMember(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs,stmt);
		}
		return members;
	}
	
	// mNum이 일치하는 사용자의 회원이름 정보 수정
	@Override
	public int update(MemberVO member) {
		int result = 0;
		// 작성
		String sql = "UPDATE tbl_member SET mName = ? WHERE mId = ?"; 
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getmName());
			psmt.setString(2, member.getmId());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(psmt);
		}
		return result;
	}
	
	// 회원 탈퇴 - 회원번호를 입력받아 일치하는 회원 정보 삭제
	@Override
	public int delete(int mNum) {
		int result = 0;
		// 작성
		String sql = "DELETE FROM tbl_member WHERE mNum = "+mNum;
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
		}
		return result;
	}
	
	// 탈퇴한 회원 정보 검색
	@Override
	public ArrayList<BackUpMemberVO> deleteMember() {
		ArrayList<BackUpMemberVO> deletes = new ArrayList<>();
		// 작성
		String sql = "SELECT * FROM back_up_member";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				deletes.add(new BackUpMemberVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getLong(5),
						rs.getTimestamp(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs,stmt);
		}
		return deletes;
	}
	
	private MemberVO getMember(ResultSet rs) {
		MemberVO member = null;
		try {
			member = new MemberVO(rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getLong(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
}












