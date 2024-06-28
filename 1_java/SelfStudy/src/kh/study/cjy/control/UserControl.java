package kh.study.cjy.control;

import java.util.ArrayList;
import java.util.List;

import kh.study.cjy.model.User;

public class UserControl {
	private List<User> userList = new ArrayList<User>();
	private User u;

	public boolean login(String userId, String userPassword) {
		boolean returnValue = false;

		if (u != null && u.getUserId().equals(userId) && u.getPassword().equals(userPassword)) {
			returnValue = true;
		}

		return returnValue;
	}

	public boolean registUser(String id, String password, String name, int age, 
			char gender, String phone, String email, String type) {
		boolean returnValue = false;

		u = new User(0, id, name, password, age, gender, phone, email, type);// , gender, type);

		return returnValue;
	}

	public boolean changePassword(String currentPassword, String changePassword) {
		boolean returnValue = false;

		if (u.getPassword().equals(currentPassword)) {
			u.setPassword(changePassword);
			returnValue = true;
		}

		return returnValue;
	}

	public String getId() {
		return u.getUserId();
	}

	public String getName() {
		return u.getName();
	}

	public char getGender() {
		return u.getGender();
	}

	public String getEmail() {
		return u.getEmail();
	}

	public String getPhone() {
		return u.getPhone();
	}
	
	public String getType() {
		return u.getType();
	}
}
