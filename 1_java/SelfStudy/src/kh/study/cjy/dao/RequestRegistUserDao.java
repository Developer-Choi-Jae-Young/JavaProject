package kh.study.cjy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kh.study.cjy.database.DataSource;
import kh.study.cjy.database.Functional;
import kh.study.cjy.model.RequestRegistUser;

public class RequestRegistUserDao extends Functional{
	/*
	 * User Table Create
	 */
	public List<RequestRegistUser> selectRequestRegistUser() {
		List<RequestRegistUser> userList = new ArrayList<RequestRegistUser>();

		try {
			CallSqlSelect(DataSource.Connect(), "Select * from RequestRegistUser;");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("userid");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int age = rs.getInt("age");
				char gender = rs.getString("gender").charAt(0);
				String phone = rs.getString("phone"); 
				String email = rs.getString("email");
				String type = rs.getString("type");
				
				userList.add(new RequestRegistUser(id, userId, name, password, age, gender, phone, email, type, false));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
		}

		return userList;
	}
	
	/*
	 * User Table Data Insert
	 */
	public boolean insertRequestRegistUser(RequestRegistUser user) {
		boolean returnValue = false;

		if (CallSqlOther(DataSource.Connect(),
				"INSERT INTO RequestRegistUser (userid, name, password, age, gender, phone, email, type) VALUES " + "(\"" + user.getUserId() + "\", \"" + user.getName() + "\", \"" + user.getPassword(false) + "\"," + user.getAge() + ", \'" + user.getGender() + "', \"" + user.getPhone() + "\", \"" + user.getEmail() + "\", \"" +  user.getType() + "\");") > 0) {
			
			returnValue = true;
		}

		return returnValue;
	}
	
	/*
	 * User Table Data Delete
	 */
	public boolean deleteRequestRegisUser(String userid, String name, int age, String phone) {
		boolean returnValue = false;

		if (CallSqlOther(DataSource.Connect(), "Delete from RequestRegistUser Where userid = \"" + userid + "\" AND name = \"" + name + "\" AND age = " + age + " AND phone = \"" + phone + "\";") > 0) {
			returnValue = true;
		}

		return returnValue;
	}
}
