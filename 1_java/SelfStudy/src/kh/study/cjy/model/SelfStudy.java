package kh.study.cjy.model;

import java.time.LocalDateTime;

public class SelfStudy {
	private int id;
	private String place;
	private String student;
	private LocalDateTime date;
	
	public SelfStudy(int id, String place, String student, LocalDateTime date) {
		super();
		this.id = id;
		this.place = place;
		this.student = student;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "[place=" + place + ", student=" + student + ", date=" + date + "]";
	}
}
