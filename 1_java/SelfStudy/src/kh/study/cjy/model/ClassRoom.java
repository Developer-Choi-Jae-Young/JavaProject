package kh.study.cjy.model;

public class ClassRoom {
	private int id;
	private String address;
	private String name;
	private int floor;
	private String className;
	
	public ClassRoom(int id, String address, String name, int floor, String className) {
		super();
		this.id = id;
		this.address = address;
		this.name = name;
		this.floor = floor;
		this.className = className;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
