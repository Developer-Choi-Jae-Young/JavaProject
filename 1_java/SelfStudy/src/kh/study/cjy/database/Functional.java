package kh.study.cjy.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Functional {
	protected ResultSet rs;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	
	public ResultSet CallSqlSelect(Connection conn, String strSql) {
		try {
			String[] strList = strSql.split(" ");
			
			if(strList[0].toUpperCase().equals("SELECT")) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(strSql);
				Commit(conn);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			Rollback(conn);
		} finally {
			//Close(rs);
			//Close(stmt);
		}
		
		return rs;
	}
	
	public int CallSqlOther(Connection conn, String strSql) {
		int returnValue = 0;
		
		try {
			String[] strList = strSql.split(" ");
			
			if(strList[0].toUpperCase().equals("INSERT") || 
					strList[0].toUpperCase().equals("UPDATE") || 
					strList[0].toUpperCase().equals("DELETE")){
				stmt = conn.createStatement();
				returnValue = stmt.executeUpdate(strSql); 
				Commit(conn);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			Rollback(conn);
		} finally {
			if(stmt != null) Close(stmt);
		}
		
		return returnValue;
	}
	
	public static void Close(Statement st) {
		try {
			st.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void Close(ResultSet rs) {
		try {
			rs.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
