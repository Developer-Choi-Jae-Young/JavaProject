package kh.study.cjy.model;

import java.sql.Date;
import java.sql.Time;

public class SelfStudy {
	private int id;
	private Student student;
	private Date date;
	private Time time;
	private int studentId;
	
	public SelfStudy(int id, Student student, Date date, Time time) {
		this.id = id;
		this.student = student;
		this.date = date;
		this.time = time;
	}
	
	public SelfStudy(int id, Student student, Date date, Time time, int studentId) {
		this.id = id;
		this.student = student;
		this.date = date;
		this.time = time;
		this.studentId = studentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "[student=" + student + ", date=" + date + ", time=" + time +"]";
	}
}
