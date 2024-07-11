package kh.study.cjy.model;

public class ClassRoom {
	private int id;
	private String address;
	private String name;
	private int floor;
	private char className;
	
	public ClassRoom() {
	}

	public ClassRoom(int id, String address, String name, int floor, char className) {
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
	public char getClassName() {
		return className;
	}
	public void setClassName(char className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", address=" + address + ", name=" + name + ", floor=" + floor + ", className="
				+ className + "]";
	}
	
}
