package kh.study.cjy.model;

public class Student extends User{
	private ClassRoom classRoom;
	private Teacher teacher;
	
	public Student() {
	}

	public Student(int id, String userId, String name, String password, int age, char gender, String phone,
			String email, String type, boolean isEncoding) {
		super(id, userId, name, password, age, gender, phone, email, type, isEncoding);
	}
	
	public Student(int id, String userId, String name, String password, int age, char gender, String phone,
			String email, String type, boolean isEncoding, ClassRoom classRoom, Teacher teacher) {
		super(id, userId, name, password, age, gender, phone, email, type, isEncoding);
		this.classRoom = classRoom;
		this.teacher = teacher;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}