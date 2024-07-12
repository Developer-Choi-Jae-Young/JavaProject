package kh.study.cjy.control;

import java.util.List;

import kh.study.cjy.dao.SelfStudyDao;
import kh.study.cjy.model.SelfStudy;
import kh.study.cjy.model.User;

public class SelfStudyControl implements IControl<SelfStudy> {
	private SelfStudyDao ssd = new SelfStudyDao();

	@Override
	public List<SelfStudy> select() {
		return null;
	}

	@Override
	public boolean insert(SelfStudy type) {		
		return ssd.insertSelfStudy(type);
	}

	@Override
	public boolean delete(SelfStudy type) {
		return ssd.deleteSelfStudy(type);
	}
	
	public SelfStudy searchTodaySelfStudy(User u, String toDay) {
		List<SelfStudy> selfStudyList = ssd.selectSelfStudyFromUser(u, toDay);
		
		if(selfStudyList.isEmpty()) return null;
		else return selfStudyList.get(0);
	}
	
	public List<SelfStudy> selectFromDate(String toDay) {
		return ssd.selectSelfStudy(toDay);
	}
}
