package kh.study.cjy.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import kh.study.cjy.database.DataSource;
import kh.study.cjy.database.Functional;
import kh.study.cjy.model.SelfStudy;
import kh.study.cjy.model.User;

public class SelfStudyDao extends Functional{
	/*
	 * User Table Create
	 */
	public List<SelfStudy> selectSelfStudy(String toDay) {
		List<SelfStudy> selfStudyList = new ArrayList<SelfStudy>();

		try {
			CallSqlSelect(DataSource.Connect(), "Select * from SelfStudy where date = \"" + toDay + "\";");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int student = rs.getInt("student_id");
				Date date = rs.getDate("date");
				Time time = rs.getTime("time");
				
				selfStudyList.add(new SelfStudy(id, null, date, time, student));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
		}

		return selfStudyList;
	}
	
	/*
	 * User Table Create
	 */
	public List<SelfStudy> selectSelfStudyFromUser(User u, String toDay) {
		List<SelfStudy> selfStudyList = new ArrayList<SelfStudy>();

		try {
			CallSqlSelect(DataSource.Connect(), "Select * from SelfStudy where student_id = " + u.getId() + " AND date = \"" + toDay + "\";");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int student = rs.getInt("student_id");
				Date date = rs.getDate("date");
				Time time = rs.getTime("time");
				
				selfStudyList.add(new SelfStudy(id, null, date, time, student));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
		}

		return selfStudyList;
	}
	
	/*
	 * User Table Data Insert
	 */
	public boolean insertSelfStudy(SelfStudy selfStudy) {
		boolean returnValue = false;

		if (CallSqlOther(DataSource.Connect(),
				"INSERT INTO SelfStudy (student_id, date, time) VALUES " + "(" + selfStudy.getStudent().getId() + ", \"" + selfStudy.getDate() + "\", \"" + selfStudy.getTime() + "\");") > 0) {
			
			returnValue = true;
		}

		return returnValue;
	}
	
	/*
	 * User Table Data Delete
	 */
	public boolean deleteSelfStudy(SelfStudy selfStudy) {
		boolean returnValue = false;

		if (CallSqlOther(DataSource.Connect(), "Delete from SelfStudy Where student_id = " + selfStudy.getStudentId() + ";") > 0) {
			returnValue = true;
		}

		return returnValue;
	}
}
