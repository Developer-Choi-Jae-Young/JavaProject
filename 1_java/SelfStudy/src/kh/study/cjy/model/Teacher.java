package kh.study.cjy.model;

public class Teacher extends User implements ITeacher{

	public Teacher(Integer id, String userId, String name, String password, Integer age, char gender, String phone,
			String email, String type, boolean isEncoding) {
		super(id, userId, name, password, age, gender, phone, email, type, isEncoding);
	}
}
