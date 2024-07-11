package kh.study.cjy.model;

public class Student extends User implements IStudent{
	private Teacher teacher;
	
	public Student(Integer id, String userId, String name, String password, Integer age, char gender, String phone,
			String email, String type, boolean isEncoding) {
		super(id, userId, name, password, age, gender, phone, email, type, isEncoding);
	}
	
	public Student(Integer id, String userId, String name, String password, Integer age, char gender, String phone,
			String email, String type, boolean isEncoding, Teacher teacher) {
		super(id, userId, name, password, age, gender, phone, email, type, isEncoding);
		this.teacher = teacher;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}