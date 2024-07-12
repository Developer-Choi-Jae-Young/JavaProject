package kh.study.cjy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kh.study.cjy.database.DataSource;
import kh.study.cjy.database.Functional;
import kh.study.cjy.model.ClassRoom;
import kh.study.cjy.model.Student;
import kh.study.cjy.model.User;

public class ClassRoomDao extends Functional{
	/*
	 * User Table Create
	 */
	public List<ClassRoom> selectClassRoom() {
		List<ClassRoom> classRoomList = new ArrayList<ClassRoom>();

		try {
			CallSqlSelect(DataSource.Connect(), "Select * from ClassRoom;");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String address = rs.getString("address");
				String name = rs.getString("name");
				int floor = rs.getInt("floor");
				char className = rs.getString("class").charAt(0);
				classRoomList.add(new ClassRoom(id, address, name, floor, className));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
		}

		return classRoomList;
	}
	
	/*
	 * User Table Data Insert
	 */
	public boolean insertClassRoom(ClassRoom classRoom) {
		boolean returnValue = false;

		if (CallSqlOther(DataSource.Connect(),
				"INSERT INTO ClassRoom (address, name, floor, class) VALUES " + "(\"" + classRoom.getAddress() + "\", \"" + classRoom.getName() + "\", " + classRoom.getFloor() + ", \'" + classRoom.getClassName() + "\');") > 0) {
			
			returnValue = true;
		}

		return returnValue;
	}
	
	/*
	 * User Table Data Delete
	 */
	public boolean deleteClassRoom(String name, int floor, char className) {
		boolean returnValue = false;

		if (CallSqlOther(DataSource.Connect(), "Delete from ClassRoom Where name = \"" + name + "\" AND floor = " + floor + " AND class = \'" + className + "\';") > 0) {
			returnValue = true;
		}

		return returnValue;
	}
	
	/*
	 * User Table Create
	 */
	public ClassRoom selectFromUser(User user) {
		List<ClassRoom> classRoomList = new ArrayList<ClassRoom>();

		try {
			
			CallSqlSelect(DataSource.Connect(), "Select * from ClassRoom where id = "+ ((Student)user).getClassId()+";");
			while (rs.next()) {
				int id = rs.getInt("id");
				String address = rs.getString("address");
				String name = rs.getString("name");
				int floor = rs.getInt("floor");
				char className = rs.getString("class").charAt(0);
				classRoomList.add(new ClassRoom(id, address, name, floor, className));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
		}
		
		if(classRoomList.isEmpty()) return null;
		else return classRoomList.get(0);
	}
}
