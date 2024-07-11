package kh.study.cjy.control;

import kh.study.cjy.etc.ControlFactoryList;

public class FactoryMethodControl {
	public IControl createContorl(ControlFactoryList type) {
		IControl control = null;
		
		switch (type) {
		case USER:
			control = new UserControl();
			break;

		case SELF_STUDY:
			control = new SelfStudyControl();
			break;

		case REQUEST_REGIST_USER:
			control = new RequestRegistUserControl();
			break;
		
		case CLASS_ROOM_CONTROL:
			control = new ClassRoomControl();
			break;
			
		default:
			break;
		}

		return control;
	}
}
