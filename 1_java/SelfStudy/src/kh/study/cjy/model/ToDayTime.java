package kh.study.cjy.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDayTime extends Thread{
	private int year;
	private int month;
	private int day;
	private boolean threadRun;
	
	public ToDayTime(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.threadRun = true;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	public void stopThread() {
		threadRun = false;
    }
	
	@Override
	public void run() {
		while(threadRun) {
			try {
				Thread.sleep(100);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
				Date currentDay = new Date();
				String[] strList = sdf.format(currentDay).split("-");
				this.year = Integer.parseInt(strList[0]);
				this.month = Integer.parseInt(strList[1]);
				this.day = Integer.parseInt(strList[2]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
