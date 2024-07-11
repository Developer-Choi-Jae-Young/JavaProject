package kh.study.cjy.control;

import java.util.ArrayList;
import java.util.List;

import kh.study.cjy.dao.RequestRegistUserDao;
import kh.study.cjy.model.RequestRegistUser;

public class RequestRegistUserControl implements IControl<RequestRegistUser>{
	private RequestRegistUserDao rrud = new RequestRegistUserDao();
		
	private boolean duplicationUserId(String userId) {
		boolean returnValue = true;

		for (RequestRegistUser user : select()) {
			if (user.getUserId().equals(userId)) {
				returnValue = false;
				break;
			}
		}

		return returnValue;
	}
	
	@Override
	public List<RequestRegistUser> select() {
		List<RequestRegistUser> userList = new ArrayList<RequestRegistUser>();
		userList = rrud.selectRequestRegistUser();
		return userList;
	}

	@Override
	public boolean insert(RequestRegistUser type) {
		boolean returnValue = false;

		if (duplicationUserId(type.getUserId())) {
			returnValue = rrud.insertRequestRegistUser(type);
		} else {
			returnValue = false;
		}

		return returnValue;
	}

	@Override
	public boolean delete(RequestRegistUser type) {
		return rrud.deleteRequestRegisUser(type.getUserId(), type.getName(), type.getAge(), type.getPhone());
	}
}
