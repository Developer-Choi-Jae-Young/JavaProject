package kh.study.cjy.model;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kh.study.cjy.etc.AESCrypto;

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
	
	public User(String userId, String name, Integer age, String phone) {
		this.userId = userId;
		this.name = name;
		this.age = age;
		this.phone = phone;
	}
	
	public User(Integer id, String userId, String name, String password, Integer age, char gender, String phone,
			String email, String type, boolean isEncoding) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		if (isEncoding) {
			try {
				this.password = AESCrypto.encrypt(password);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}
		} else {
			this.password = password;
		}

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

	public String getPassword(boolean isEncoding) {
		String returnValue = null;
		if (isEncoding) {
			try {
				returnValue = AESCrypto.decrypt(password);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}
		} else {
			returnValue = password;
		}

		return returnValue;
	}

	public void setPassword(String password, boolean isEncoding) {
		if (isEncoding) {
			try {
				this.password = AESCrypto.encrypt(password);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}
		} else {
			this.password = password;
		}
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

	@Override
	public String toString() {
		return "[id=" + id + ", userId=" + userId + ", name=" + name + ", password=" + password + ", age=" + age
				+ ", phone=" + phone + ", gender=" + gender + ", email=" + email + ", type=" + type + "]";
	}
}
