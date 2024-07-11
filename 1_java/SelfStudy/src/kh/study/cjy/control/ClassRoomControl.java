package kh.study.cjy.control;

import java.util.ArrayList;
import java.util.List;

import kh.study.cjy.dao.ClassRoomDao;
import kh.study.cjy.model.ClassRoom;
import kh.study.cjy.model.User;

public class ClassRoomControl implements IControl<ClassRoom>{
	private ClassRoomDao crd = new ClassRoomDao();
	
	@Override
	public List<ClassRoom> select() {
		List<ClassRoom> classRoomList = new ArrayList<ClassRoom>();
		classRoomList = crd.selectClassRoom();
		return classRoomList;
	}

	@Override
	public boolean insert(ClassRoom type) {
		boolean returnValue = false;

		if (duplicationClassRoom(type)) {
			returnValue = crd.insertClassRoom(type);
		} else {
			returnValue = false;
		}

		return returnValue;
	}

	@Override
	public boolean delete(ClassRoom type) {
		return crd.deleteClassRoom(type.getName(), type.getFloor(), type.getClassName());
	}
	
	private boolean duplicationClassRoom(ClassRoom type) {
		boolean returnValue = true;

		for (ClassRoom cr : select()) {
			if (cr.getFloor() == type.getFloor() && cr.getAddress().equals(type.getAddress()) && cr.getName().equals(type.getName())) {
				returnValue = false;
				break;
			}
		}

		return returnValue;
	}
	
	public ClassRoom searchClassRoom(char classRoomName) {
		ClassRoom classRoom = new ClassRoom();
		
		for(ClassRoom cr : select()) {
			if(cr.getClassName() == classRoomName) {
				classRoom = cr;
				break;
			}
		}
		
		return classRoom;
	}
}