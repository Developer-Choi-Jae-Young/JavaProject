package kh.study.cjy.model;

import database.ClassType;
import etc.Grade;

public class User {
	private Integer id;
	private String name;
	private String password;
	private Integer age;
	private String phone;
	private String email;
	//private Grade grade;
	//private ClassType classType;
	
	public User() {
		
	}
	
	public User(Integer id, String name, String password, Integer age, String phone, String email 
			/*,Grade grade, ClassType classType*/) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.phone = phone;
		this.email = email;
		//this.grade = grade;
		//this.classType = classType;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Grade getGrade() {
		//return grade;
		return null;
	}
	public void setGrade(Grade grade) {
		//this.grade = grade;
	}
	public ClassType getClassType() {
		//return classType;
		return null;
	}
	public void setClassType(ClassType classType) {
		//this.classType = classType;
	}	
}