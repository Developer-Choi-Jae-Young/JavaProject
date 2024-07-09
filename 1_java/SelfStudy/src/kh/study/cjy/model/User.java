package kh.study.cjy.model;

public class User {
	private Integer id;
	private String userId;
	private String name;
	private String password;
	private Integer age;
	private String phone;
	private char gender;
	private String email;
	private String type;
	
	public User() {
		
	}
	
	public User(Integer id, String userId, String name, String password, Integer age, char gender, String phone, 
			String email, String type) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
}
