package kh.study.cjy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import database.Functional;
import kh.study.cjy.model.User;

public class UserDao extends Functional {
	/*
	 * User Table Create
	 */
	public List<User> SelectUser() {
		List<User> userList = new ArrayList<User>();

		try {
			while (CallSqlSelect(DataSource.Connect(), "").next()) {
				rs.getInt(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	/*
	 * User Table Data Insert
	 */
	public boolean InsertUser(User user) {
		boolean returnValue = false;
		
		 if(CallSqlOther(DataSource.Connect(), "") > 0) {
			 returnValue = true;
		 }
		 
		 return returnValue;
	}
	
	/*
	 * User Table Data Update
	 */
	public boolean UpdateUser(User user) {
		boolean returnValue = false;
		
		 if(CallSqlOther(DataSource.Connect(), "") > 0) {
			 returnValue = true;
		 }
		 
		 return returnValue;
	}
	
	/*
	 * User Table Data Delete
	 */
	public boolean DeleteUser(User user) {
		boolean returnValue = false;
		
		 if(CallSqlOther(DataSource.Connect(), "") > 0) {
			 returnValue = true;
		 }
		 
		 return returnValue;
	}
}
