package kh.study.cjy.model;

public class Teacher extends User{

	public Teacher(int id, String userId, String name, String password, int age, char gender, String phone,
			String email, String type, boolean isEncoding) {
		super(id, userId, name, password, age, gender, phone, email, type, isEncoding);
	}

	public Teacher() {
		super();
	}
}
