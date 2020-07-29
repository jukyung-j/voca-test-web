package voca_test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class TestDAO {
protected Connection con = null;
	
	protected abstract void connectDB() throws SQLException;

	protected void disconnectDB() throws SQLException {
		if(con != null) {
			con.close();
			con = null;
		}
	}
	public VocaTestDO getList(int idx) throws SQLException {
		VocaTestDO voca = null;
		
		connectDB();
		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql="select * from voca where idx=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idx);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				voca = new VocaTestDO();
				voca.setIdx(rs.getInt("idx"));
				voca.setEng(rs.getString("eng"));
				voca.setKor(rs.getString("kor"));
			}
		}catch(SQLException e) {
			throw e;
		} finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();  
			disconnectDB();
		}
		return voca;
	}
	public List<String> getKor() throws  SQLException{
		ArrayList<String> Lists = null;
		
		connectDB();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			String sql="select kor from voca";
			rs = stmt.executeQuery(sql);
			
			if(rs.isBeforeFirst()) {
				Lists = new ArrayList<String>();
				while(rs.next()) {
					Lists.add(rs.getString("kor"));
					
				}
			}
		} catch(SQLException e) {
			throw e;
		} finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();  
			disconnectDB();
		}
			return Lists;
		
	}
	public MemberDO Login(String id) throws SQLException {
		MemberDO member = null;
		
		connectDB();
		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql="select * from member where id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberDO();
				member.setId(rs.getString("id"));
				member.setPwd(rs.getString("pwd"));
				member.setName(rs.getString("name"));
			}
		}catch(SQLException e) {
			throw e;
		} finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();  
			disconnectDB();
		}
		return member;
	}
	public int insert(MemberDO member) throws SQLException {
		connectDB();
		
		int result = 0;
		PreparedStatement stmt = null;
		try {	
			
			String sql= "insert into member(id, pwd, name) values (?,?,?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, member.getId());
			stmt.setString(2, member.getPwd());
			stmt.setString(3, member.getName());
			result = stmt.executeUpdate();	
		} catch(SQLException e) {
			throw e;
		}finally {
			if(stmt != null) stmt.close();
			disconnectDB();
		}
		return result;
	} 
	
}
