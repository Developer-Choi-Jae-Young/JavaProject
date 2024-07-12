package kh.study.cjy.view;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import kh.study.cjy.control.ClassRoomControl;
import kh.study.cjy.control.FactoryMethodControl;
import kh.study.cjy.control.IControl;
import kh.study.cjy.control.SelfStudyControl;
import kh.study.cjy.control.UserControl;
import kh.study.cjy.database.DataSource;
import kh.study.cjy.etc.Banner;
import kh.study.cjy.etc.ControlFactoryList;
import kh.study.cjy.etc.FileInputOutput;
import kh.study.cjy.etc.FileList;
import kh.study.cjy.model.ClassRoom;
import kh.study.cjy.model.RequestRegistUser;
import kh.study.cjy.model.SelfStudy;
import kh.study.cjy.model.Student;
import kh.study.cjy.model.Teacher;
import kh.study.cjy.model.ToDayTime;
import kh.study.cjy.model.User;

public class View {
	private Scanner sc = new Scanner(System.in);
	private IControl[] controlList = new IControl[ControlFactoryList.values().length];
	private ToDayTime dayTime;

	public View() {
		Banner.print();

		FileInputOutput fio = new FileInputOutput();
		if (!fio.isExist(FileList.SQL_META_DATA.name())) {
			fio.FileSave(FileList.SQL_META_DATA.name(), "SQL_META_DATA");
		}

		DataSource.Init();

		FactoryMethodControl fc = new FactoryMethodControl();
		for (int i = 0; i < controlList.length; i++) {
			controlList[i] = fc.createContorl(ControlFactoryList.values()[i]);
		}

		dayTime = new ToDayTime(0, 0, 0);
		dayTime.start();

		// ADMIN 계정은 처음에 어떻게 추가하고 관리할까? 고민좀 해보자
		boolean flag = false;
		if (flag) {
			if (controlList[ControlFactoryList.USER.ordinal()]
					.insert(new User(0, "ADMIN", "ADMIN", "ADMIN", 0, '남', "0", "ADMIN", "Admin", true))) {
				System.out.println("회원가입을 완료하였습니다.");
			} else {
				System.out.println("중복된 아이디가 존재해 회원가입을 취소합니다.");
			}
		}

		while (true) {
			if (mainView()) {
				dayTime.stopThread();
				break;
			}
		}
	}

	private boolean mainView() {
		boolean returnValue = false;

		System.out.println("======================================");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("99. 종료");
		System.out.println("======================================");
		System.out.print("선택 : ");
		int num = sc.nextInt();
		sc.nextLine();

		switch (num) {
		case 1:
			if (loginView()) {
				loginAfterView(((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType());
			}
			break;
		case 2:
			registUserView();
			break;
		case 3:
			break;
		case 99:
			System.out.println("프로그램을 종료합니다.");
			returnValue = true;
			break;
		default:
			break;
		}

		return returnValue;
	}

	private boolean loginView() {
		boolean returnValue = false;

		System.out.println("======================================");
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String password = sc.nextLine();
		System.out.println("======================================");

		String result = "Result : ";
		if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).login(id, password)) {
			if(((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser() instanceof Student) {
				((ClassRoomControl) controlList[ControlFactoryList.CLASS_ROOM_CONTROL.ordinal()]).setClassRoomFromUser(((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser());				
				((UserControl) controlList[ControlFactoryList.USER.ordinal()]).setTeacherFromUser(((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser());
			}
			result += "Login Success";
			returnValue = true;
		} else {
			result += "Login Fail";
		}

		System.out.println(result);
		return returnValue;
	}

	private boolean registUserView() {
		boolean returnValue = false;

		System.out.println("======================================");
		System.out.println("1. 학생");
		System.out.println("2. 강사");
		System.out.println("======================================");
		System.out.print("선택 : ");
		int num = sc.nextInt();
		sc.nextLine();

		switch (num) {
		case 1:
			registUserDetailView("Student");
			break;
		case 2:
			registUserDetailView("Teacher");
			break;
		default:
			break;
		}

		return returnValue;
	}

	private boolean registUserDetailView(String type) {
		boolean returnValue = false;

		System.out.println("======================================");
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String password = sc.nextLine();
		System.out.print("Name : ");
		String name = sc.nextLine();
		System.out.print("Age : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("Phone : ");
		String phone = sc.nextLine();
		System.out.print("Email : ");
		String email = sc.nextLine();
		System.out.print("Gender : ");
		char gender = sc.nextLine().charAt(0);
		System.out.println("======================================");

		if (controlList[ControlFactoryList.REQUEST_REGIST_USER.ordinal()]
				.insert(new RequestRegistUser(0, id, password, name, age, gender, phone, email, type, true))) {
			System.out.println("회원가입을 완료하였습니다.");
		} else {
			System.out.println("중복된 아이디가 존재해 회원가입을 취소합니다.");
		}

		return returnValue;
	}

	private boolean loginAfterView(String type) {
		boolean returnValue = false;

		boolean bflag = false;

		while (true) {
			if (bflag)
				break;

			System.out.println("======================================");
			System.out.println("1. 내정보");
			System.out.println("2. 자습현황");
			if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Student"))
				System.out.println("3. 자습신청"); // 학생이면
			if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Student"))
				System.out.println("4. 자습삭제"); // 학생이면
			if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
				System.out.println("3. 회원가입 요청처리"); // 관리자라면
			if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
				System.out.println("4. 회원 삭제"); // 관리자라면
			if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
				System.out.println("5. 회원 강의실 지정"); // 관리자라면
			if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
				System.out.println("6. 회원 강사 지정"); // 관리자라면
			if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
				System.out.println("7. 강의실 관리"); // 관리자라면
			System.out.println("9. 로그아웃");
			System.out.println("======================================");
			System.out.print("선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				if (printMyInformation()) {
					bflag = true;
				}
				break;
			case 2:
				printTodaySelfStudySchdule(type);
				break;
			case 3:
				if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
					acceptRequestRegistUser();// 관리자만 가능
				if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType()
						.equals("Student"))
					insertSelfStudySchdule(); // 학생만 가능
				break;
			case 4:
				if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
					removeUser();// 관리자만 가능
				if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType()
						.equals("Student"))
					removeSelfStudySchdule(); // 학생만 가능
				break;
			case 5:
				if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
					insertClassRoomForUser();// 관리자만 가능
				break;
			case 6:
				if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
					insertTeacher();// 관리자만 가능
				break;
			case 7:
				if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
					managementClassRoom();// 관리자만 가능
				break;
			case 9:
				System.out.println("로그아웃 하여 메인으로 돌아갑니다.");
				bflag = true;
				break;
			default:
				break;
			}
		}

		return returnValue;
	}

	private void printTodaySelfStudySchdule(String type) {
		System.out.println("======================================");
		String toDay = String.format("%d-%d-%d", dayTime.getYear(), dayTime.getMonth(), dayTime.getDay());
		if (!((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin")) {
			for (Object ss : ((SelfStudyControl) controlList[ControlFactoryList.SELF_STUDY.ordinal()])
					.selectFromDate(toDay)) {
				
				Student st = (Student)((UserControl) controlList[ControlFactoryList.USER.ordinal()]).setUserFromSelfStudy((SelfStudy) ss).getStudent();
				((ClassRoomControl) controlList[ControlFactoryList.CLASS_ROOM_CONTROL.ordinal()]).setClassRoomFromUser(st);
				
				if(st.getClassRoom().getName().equals(((Student)((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser()).getClassRoom().getName()) && ((Student)((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser()).getClassRoom().getFloor() == st.getClassRoom().getFloor()) {
					System.out.println(((UserControl) controlList[ControlFactoryList.USER.ordinal()]).setUserFromSelfStudy((SelfStudy) ss)); // 오늘 &&해당 건물의 층의 자습대장 출력
				}
			}
		} else {
			System.out.print("확인하고 싶은 연도 : ");
			int year = sc.nextInt();
			System.out.print("확인하고 싶은 달 : ");
			int month = sc.nextInt();
			System.out.print("확인하고 싶은 일 : ");
			int day = sc.nextInt();
			sc.nextLine();
			System.out.print("확인하고 싶은 건물 이름 : ");
			String buildingName = sc.nextLine();
			System.out.print("확인하고 싶은 건물의 층수 : ");
			int floor = sc.nextInt();
			sc.nextLine();
			
			String checkDay = String.format("%d-%d-%d", year,month,day);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day);
			Date currentDay = new Date();
			
			if (currentDay.compareTo(cal.getTime()) != -1) {
				for (Object ss : ((SelfStudyControl) controlList[ControlFactoryList.SELF_STUDY.ordinal()])
						.selectFromDate(checkDay)) {
					
					Student st = (Student)((UserControl) controlList[ControlFactoryList.USER.ordinal()]).setUserFromSelfStudy((SelfStudy) ss).getStudent();
					((ClassRoomControl) controlList[ControlFactoryList.CLASS_ROOM_CONTROL.ordinal()]).setClassRoomFromUser(st);
					
					if(st.getClassRoom().getName().equals(buildingName) && st.getClassRoom().getFloor() == floor) {
						System.out.println(((UserControl) controlList[ControlFactoryList.USER.ordinal()]).setUserFromSelfStudy((SelfStudy) ss)); // 오늘 &&해당 건물의 층의 자습대장 출력
					}
				}
			} else {
				System.out.println("오늘보다 날짜가 더 큽니다. 다시 한번 확인해주세요.");
			}
		}
	}
	
	private void removeSelfStudySchdule() {
		System.out.println("======================================");
		String toDay = String.format("%d-%d-%d", dayTime.getYear(), dayTime.getMonth(), dayTime.getDay());
		//1. 오늘 기록한적이 있는가? 있으면 삭제 진행 없으면 바로 return
		//2. 있다면 오늘 날짜 기준으로 
		SelfStudy ss = ((UserControl) controlList[ControlFactoryList.USER.ordinal()]).setUserFromSelfStudy(
				((SelfStudyControl) controlList[ControlFactoryList.SELF_STUDY.ordinal()]).searchTodaySelfStudy(
						((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser(), toDay));
		
		if (ss == null) {
			System.out.println("오늘 자습을 신청한 기록이 없어 삭제를 할수없습니다.");
				return;
		}
		
		if(((SelfStudyControl) controlList[ControlFactoryList.SELF_STUDY.ordinal()]).delete(((SelfStudyControl) controlList[ControlFactoryList.SELF_STUDY.ordinal()]).searchTodaySelfStudy(((UserControl)controlList[ControlFactoryList.USER.ordinal()]).getUser(), toDay))) {
			System.out.println("삭제에 성공하였습니다.");
		} else {
			System.out.println("삭제에 실패하였습니다.");
		}
		System.out.println("======================================");
	}
	
	private void insertSelfStudySchdule() {
		System.out.println("======================================");
		Date currentDay = new Date(dayTime.getYear(), dayTime.getMonth(), dayTime.getDay(), 17, 51);
		String toDay = String.format("%d-%d-%d", dayTime.getYear(), dayTime.getMonth(), dayTime.getDay());

		SelfStudy ss = ((UserControl) controlList[ControlFactoryList.USER.ordinal()]).setUserFromSelfStudy(
				((SelfStudyControl) controlList[ControlFactoryList.SELF_STUDY.ordinal()]).searchTodaySelfStudy(
						((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser(), toDay));
		if (ss != null)
			System.out.println("오늘 자습을 신청한 기록이 있어 추가 신청이 불가합니다. 이전으로 화면으로 돌아갑니다.");

		while (ss == null) {
			System.out.print("시간 : ");
			int hour = sc.nextInt();
			System.out.print("분 : ");
			int minute = sc.nextInt();

			Date inputDay = new Date(dayTime.getYear(), dayTime.getMonth(), dayTime.getDay(), hour, minute);

			if (currentDay.compareTo(inputDay) == 1) {
				System.out.println("시간을 잘못입력하였습니다. 다시 입력해주세요.");
			} else {
				if (controlList[ControlFactoryList.SELF_STUDY.ordinal()].insert(new SelfStudy(0,
						(Student) ((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser(),
						java.sql.Date.valueOf(
								String.format("%d-%d-%d", dayTime.getYear(), dayTime.getMonth(), dayTime.getDay())),
						Time.valueOf(String.format("%d:%d:00", hour, minute))))) {
					System.out.println("자습대장을에 기록을 완료하였습니다.");
				} else {
					System.out.println("자습대장을에 기록을 실패하여 이전 화면으로 돌아갑니다.");
				}
				break;
			}
		}
		System.out.println("======================================");
	}

	private void managementClassRoom() {
		while (true) {
			System.out.println("======================================");
			System.out.println("1. 강의실 확인");
			System.out.println("2. 강의실 추가");
			System.out.println("3. 강의실 삭제");
			// System.out.println("4. 강의실 수정");
			System.out.println("9. 이전 화면으로");
			System.out.println("======================================");
			System.out.print("선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				searchClassRoom();
				break;
			case 2:
				insertClassRoom();
				break;
			case 3:
				deleteClassRoom();
				break;
			case 4:
				// updateClassRoom();
				break;
			case 9:
				System.out.println("이전 화면으로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못 입력하였습니다. 다시 한번 확인해주세요.");
				break;
			}
		}
	}

	private void searchClassRoom() {
		System.out.println("======================================");
		for (Object cr : controlList[ControlFactoryList.CLASS_ROOM_CONTROL.ordinal()].select()) {
			System.out.println((ClassRoom) cr);
		}
		System.out.println("======================================");
	}

	private void deleteClassRoom() {
		System.out.println("======================================");
		searchClassRoom();

		System.out.print("주소 : ");
		String address = sc.nextLine();
		System.out.print("건물 이름 : ");
		String building = sc.nextLine();
		System.out.print("층 : ");
		int floor = sc.nextInt();
		sc.nextLine();
		System.out.print("반 이름 : ");
		char className = sc.nextLine().charAt(0);

		if (controlList[ControlFactoryList.CLASS_ROOM_CONTROL.ordinal()]
				.delete(new ClassRoom(0, address, building, floor, className))) {
			System.out.println("데이터 삭제에 성공하였습니다.");
		} else {
			System.out.println("데이터 삭제에 실패하였습니다.");
		}
		System.out.println("======================================");
	}

	private void insertClassRoom() {
		System.out.println("======================================");
		System.out.print("주소 : ");
		String address = sc.nextLine();
		System.out.print("건물 이름 : ");
		String building = sc.nextLine();
		System.out.print("층 : ");
		int floor = sc.nextInt();
		sc.nextLine();
		System.out.print("반 이름 : ");
		char className = sc.nextLine().charAt(0);

		if (controlList[ControlFactoryList.CLASS_ROOM_CONTROL.ordinal()]
				.insert(new ClassRoom(0, address, building, floor, className))) {
			System.out.println("데이터 입력에 성공하였습니다.");
		} else {
			System.out.println("데이터 입력에 실패하였습니다.");
		}
		System.out.println("======================================");
	}

	private void updateClassRoom() {
		System.out.println("======================================");
		System.out.println("기능 구현 의미가 있을까?");
		System.out.println("======================================");
	}

	private void insertClassRoomForUser() {
		System.out.println("======================================");
		List userList = controlList[ControlFactoryList.USER.ordinal()].select();
		for (Object u : userList) {
			if (u instanceof Student) {
				System.out.println((User) u);
			}
		}

		System.out.print("강의실 지정할 회원 아이디 : ");
		String userId = sc.nextLine();
		System.out.print("강의실 지정할 회원 이름 : ");
		String name = sc.nextLine();
		System.out.print("지정할 강의실 (반) : ");
		char classRoom = sc.nextLine().charAt(0);

		for (Object u : userList) {
			if (((User) u).getUserId().equals(userId) && ((User) u).getName().equals(name)) {
				if (u instanceof Student) {
					((Student) u).setClassRoom(
							((ClassRoomControl) controlList[ControlFactoryList.CLASS_ROOM_CONTROL.ordinal()])
									.searchClassRoom(classRoom));
					controlList[ControlFactoryList.USER.ordinal()].update(u);
				}
			}
		}

		System.out.println("======================================");
	}

	private void insertTeacher() {
		System.out.println("======================================");
		List userList = controlList[ControlFactoryList.USER.ordinal()].select();
		for (Object u : userList) {
			System.out.println((User) u);
		}

		System.out.print("강사 지정할 회원 아이디 : ");
		String userId = sc.nextLine();
		System.out.print("강사 지정할 회원 이름 : ");
		String name = sc.nextLine();

		for (Object u : userList) {
			if (u instanceof Teacher) {
				System.out.println((User) u);
			}
		}

		System.out.print("지정할 강사 아이디 : ");
		String teacherId = sc.nextLine();
		System.out.print("지정할 강사 이름 : ");
		String teacherName = sc.nextLine();
		// 이부분 과연 View에 있어도 되는걸까? 고민좀 해보자!!
		for (Object u : userList) {
			if (((User) u).getUserId().equals(userId) && ((User) u).getName().equals(name)) {
				if (u instanceof Student) {
					((Student) u).setTeacher((Teacher) ((UserControl) controlList[ControlFactoryList.USER.ordinal()])
							.searchTeacher(teacherId, teacherName));
					controlList[ControlFactoryList.USER.ordinal()].update(u);
				}
			}
		}

		System.out.println("======================================");
	}

	private boolean printMyInformation() {
		boolean returnValue = false;

		System.out.println("======================================");
		System.out.println("ID : " + ((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getId());
		System.out.println(
				"Name : " + ((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getName());
		System.out.println(
				"Gender : " + ((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getGender());
		System.out.println(
				"Email : " + ((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getEmail());
		System.out.println(
				"Phone : " + ((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getPhone());
		System.out.println("======================================");

		System.out.print("비밀번호를 바꾸시겠습니까?(y/n) : ");
		char select = sc.next().charAt(0);
		sc.nextLine();

		if (select == 'y' || select == 'Y') {
			System.out.print("현재 Password : ");
			String currentPassword = sc.nextLine();
			System.out.print("바꿀 Password : ");
			String changePassword = sc.nextLine();
			((UserControl) controlList[ControlFactoryList.USER.ordinal()]).changePassword(currentPassword,
					changePassword);
			System.out.println("비밀번호가 정상적으로 바뀌었습니다.");
			returnValue = true;
		} else {
			System.out.println("비밀번호가 변경을 취소하여 전으로 돌아갑니다.");
		}

		return returnValue;
	}

	private void acceptRequestRegistUser() {
		List userList = controlList[ControlFactoryList.REQUEST_REGIST_USER.ordinal()].select();

		System.out.println("======================================");
		for (Object rru : userList) {
			System.out.println((RequestRegistUser) rru);
		}
		System.out.println("======================================");

		if (userList.size() > 0) {
			System.out.print("회원가입 수락/삭제할 번호 : ");
			int num = sc.nextInt();
			sc.nextLine();
			System.out.print("회원가입 수락(y) or 삭제(n) : ");
			char select = sc.nextLine().charAt(0);

			RequestRegistUser rru = (RequestRegistUser) userList.get(num);
			if (select == 'y' || select == 'Y') {
				if (controlList[ControlFactoryList.USER.ordinal()].insert(rru)) {
					if (controlList[ControlFactoryList.REQUEST_REGIST_USER.ordinal()].delete(rru)) {
						System.out.println("회원가입을 완료하였습니다.");
					} else {
						System.out.println("처리중 문제가 발생");
					}
				} else {
					System.out.println("중복된 아이디가 존재해 회원가입을 취소합니다.");
				}
			} else if (select == 'n' || select == 'N') {
				System.out.printf("삭제할 대상의 아이디 : ");
				String id = sc.nextLine();
				System.out.printf("삭제할 대상의 이름 : ");
				String name = sc.nextLine();
				System.out.printf("삭제할 대상의 나이 : ");
				int age = sc.nextInt();
				sc.nextLine();
				System.out.printf("삭제할 대상의 전화번호 : ");
				String phone = sc.nextLine();
				// if(rruc.deleteRequestRegistUser(rru.getUserId(), rru.getName(), rru.getAge(),
				// rru.getPhone())) {
				if (controlList[ControlFactoryList.REQUEST_REGIST_USER.ordinal()]
						.delete(new RequestRegistUser(id, name, age, phone))) {
					System.out.println("해당항목을 목록에서 삭제 완료하였습니다.");
				} else {
					System.out.println("삭제가 실패 되어 이전으로 돌아갑니다.");
				}
			} else {
				System.out.println("잘못입력하여 아무것도 수행하지 않고 이전 화면으로 돌아갑니다.");
			}
		}
	}

	public void removeUser() {
		List userList = controlList[ControlFactoryList.USER.ordinal()].select();
		System.out.println("======================================");
		for (Object u : userList) {
			if ((u instanceof Student) || (u instanceof Teacher)) {
				System.out.println((User) u);
			}
		}
		System.out.println("======================================");

		System.out.printf("삭제할 대상의 아이디 : ");
		String id = sc.nextLine();
		System.out.printf("삭제할 대상의 이름 : ");
		String name = sc.nextLine();
		System.out.printf("삭제할 대상의 나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.printf("삭제할 대상의 전화번호 : ");
		String phone = sc.nextLine();
		// if(uc.deleteUser(rru.getUserId(), rru.getName(), rru.getAge(),
		// rru.getPhone())) {
		if (controlList[ControlFactoryList.USER.ordinal()].delete(new User(id, name, age, phone))) {
			System.out.println("해당항목을 회원에서 삭제 완료하였습니다.");
		} else {
			System.out.println("삭제가 실패 되어 이전으로 돌아갑니다.");
		}
	}
}
