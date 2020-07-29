package voca_test;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TestDbcpDao extends TestDAO{
private String dbcpResourceName;  
	
	public TestDbcpDao(String dbcpResourceName) {
		this.dbcpResourceName = dbcpResourceName;

	}
	
	protected void connectDB() throws SQLException {
		try {
			DataSource ds = (DataSource)(new InitialContext()).lookup("java:comp/env/"+dbcpResourceName);
			con = ds.getConnection();
			
		} catch (NamingException e) {
			throw new SQLException(e);
		}
	}
}
