package kh.study.cjy.control;

import java.util.ArrayList;
import java.util.List;

import kh.study.cjy.dao.RequestRegistUserDao;
import kh.study.cjy.model.RequestRegistUser;

public class RequestRegistUserControl implements IControl{
	private RequestRegistUserDao rrud = new RequestRegistUserDao();
	
	public List<RequestRegistUser> getRequestRegistUser() {
		List<RequestRegistUser> userList = new ArrayList<RequestRegistUser>();
		userList = rrud.selectRequestRegistUser();
		return userList;
	}
	
	public boolean registRequestRegistUser(String id, String password, String name, int age, char gender, String phone, String email,
			String type) {
		boolean returnValue = false;

		if (duplicationUserId(id)) {
			returnValue = rrud.insertRequestRegistUser(new RequestRegistUser(0, id, name, password, age, gender, phone, email, type, true));
		} else {
			returnValue = false;
		}

		return returnValue;
	}
	
	private boolean duplicationUserId(String userId) {
		boolean returnValue = true;
		List<RequestRegistUser> userList = new ArrayList<RequestRegistUser>();
		userList = rrud.selectRequestRegistUser();

		for (RequestRegistUser user : userList) {
			if (user.getUserId().equals(userId)) {
				returnValue = false;
				break;
			}
		}

		return returnValue;
	}
	
	public boolean deleteRequestRegistUser(String id, String name, int age, String phone) {
		return rrud.deleteRequestRegisUser(id, name, age, phone);
	}
}
