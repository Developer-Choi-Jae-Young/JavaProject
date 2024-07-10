package kh.study.cjy.control;

import java.util.ArrayList;
import java.util.List;

import kh.study.cjy.dao.UserDao;
import kh.study.cjy.model.RequestRegistUser;
import kh.study.cjy.model.User;

public class UserControl implements IControl {
	private User u;
	private UserDao ud = new UserDao();
	
	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		userList = ud.selectUser();
		return userList;
	}
	
	public boolean login(String userId, String userPassword) {
		boolean returnValue = false;
		List<User> userList = new ArrayList<User>();
		userList = ud.selectUser();

		for (User user : userList) {
			if (user.getUserId().equals(userId) && user.getPassword().equals(userPassword)) {
				u = user;
				returnValue = true;
				break;
			}
		}

		return returnValue;
	}

	public boolean registUser(String id, String password, String name, int age, char gender, String phone, String email,
			String type) {
		boolean returnValue = false;

		if (duplicationUserId(id)) {
			returnValue = ud.insertUser(new User(0, id, name, password, age, gender, phone, email, type));
		} else {
			returnValue = false;
		}

		return returnValue;
	}

	public boolean changePassword(String currentPassword, String changePassword) {
		boolean returnValue = false;

		if (u != null) {
			if (u.getPassword().equals(currentPassword)) {
				u.setPassword(changePassword);
				ud.updateUsserPassword(u, changePassword);
				returnValue = true;
			}
		}

		return returnValue;
	}

	private boolean duplicationUserId(String userId) {
		boolean returnValue = true;
		List<User> userList = new ArrayList<User>();
		userList = ud.selectUser();

		for (User user : userList) {
			if (user.getUserId().equals(userId)) {
				returnValue = false;
				break;
			}
		}

		return returnValue;
	}

	public User getUser() {
		return u;
	}

	public boolean deleteUser(String id, String name, int age, String phone) {
		return ud.deleteUser(id, name, age, phone);
	}
}
