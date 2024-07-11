package kh.study.cjy.control;

import java.util.ArrayList;
import java.util.List;

import kh.study.cjy.dao.UserDao;
import kh.study.cjy.model.Teacher;
import kh.study.cjy.model.User;

public class UserControl implements IControl<User> {
	private User u;
	private UserDao ud = new UserDao();
	
	public boolean login(String userId, String userPassword) {
		boolean returnValue = false;
		
		for (User user : select()) {
			if (user.getUserId().equals(userId) && user.getPassword(true).equals(userPassword)) {
				u = user;
				returnValue = true;
				break;
			}
		}

		return returnValue;
	}

	public boolean changePassword(String currentPassword, String changePassword) {
		boolean returnValue = false;
		
		if (u != null) {
			if (u.getPassword(true).equals(currentPassword)) {
				u.setPassword(changePassword,true);
				ud.updateUsserPassword(u);
				returnValue = true;
			}
		}
		
		return returnValue;
	}
	
	private boolean duplicationUserId(String userId) {
		boolean returnValue = true;

		for (User user : select()) {
			if (user.getUserId().equals(userId)) {
				returnValue = false;
				break;
			}
		}

		return returnValue;
	}
	
	public User searchTeacher(String teacherId, String teacherName) {
		User teacher = new Teacher();
		
		for (User user : select()) {
			if(user instanceof Teacher) {				
				if (user.getUserId().equals(teacherId) && user.getName().equals(teacherName)) {
					teacher = user;
					break;
				}
			}
		}
		
		return teacher;
	}

	public User getUser() {
		return u;
	}

	@Override
	public List<User> select() {
		List<User> userList = new ArrayList<User>();
		userList = ud.selectUser();
		return userList;
	}

	@Override
	public boolean insert(User type) {
		boolean returnValue = false;

		if (duplicationUserId(type.getUserId())) {
			returnValue = ud.insertUser(type);
		} else {
			returnValue = false;
		}

		return returnValue;
	}

	@Override
	public boolean delete(User type) {
		return ud.deleteUser(type.getUserId(), type.getName(), type.getAge(), type.getPhone());
	}

	@Override
	public boolean update(User type) {
		return ud.updateUser(type);
	}
}
