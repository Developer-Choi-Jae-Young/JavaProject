package kh.study.cjy.run;

import kh.study.cjy.view.View;

public class Run {

	public static void main(String[] args) {
		System.out.println("프로그램 시작 ...");
		
		new View();
		/*
		Functional fi = new Functional();
		List<Object> o = fi.ResultSetToArray(fi.CallSql(fi.Connect(),"Select * from User;"), User.class, null);
		
		for(Object oo : o) {
			User u = (User)oo;
			System.out.println(u.getAge());
			System.out.println(u.getEmail());
			System.out.println(u.getId());
			System.out.println(u.getName());
			System.out.println(u.getPassword());
		}*/
	}
}
