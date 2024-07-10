package kh.study.cjy.control;

import java.util.ArrayList;
import java.util.List;

import kh.study.cjy.dao.UserDao;
import kh.study.cjy.model.User;

public class UserControl implements IControl {
	private List<User> userList = new ArrayList<User>();
	private User u;
	private UserDao ud = new UserDao();

	public boolean login(String userId, String userPassword) {
		boolean returnValue = false;
		userList = ud.SelectUser();
		
		for(User user : userList) {
			if( user.getUserId().equals(userId) && user.getPassword().equals(userPassword) ) {				
				returnValue = true;
			}
		}

		return returnValue;
	}

	public boolean registUser(String id, String password, String name, int age, 
			char gender, String phone, String email, String type) {
		return ud.InsertUser(new User(0, id, name, password, age, gender, phone, email, type));
	}

	public boolean changePassword(String currentPassword, String changePassword) {
		boolean returnValue = false;

		if (u.getPassword().equals(currentPassword)) {
			u.setPassword(changePassword);
			returnValue = true;
		}

		return returnValue;
	}
	
	public User getUser() {
		return u;
	}
}
