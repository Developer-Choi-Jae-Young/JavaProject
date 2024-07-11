package kh.study.cjy.view;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import kh.study.cjy.control.ClassRoomControl;
import kh.study.cjy.control.FactoryMethodControl;
import kh.study.cjy.control.IControl;
import kh.study.cjy.control.RequestRegistUserControl;
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
import kh.study.cjy.model.User;

public class View {
	private Scanner sc = new Scanner(System.in);
	private FactoryMethodControl fc = new FactoryMethodControl(); // 의존관계를 많이 해결했지만 원하는 메소드를 호출을 어떻게 할까?
	private RequestRegistUserControl rruc = new RequestRegistUserControl();
	private IControl[] controlList = new IControl[ControlFactoryList.values().length];

	public View() {
		Banner.print();

		FileInputOutput fio = new FileInputOutput();
		if (!fio.isExist(FileList.SQL_META_DATA.name())) {
			fio.FileSave(FileList.SQL_META_DATA.name(), "SQL_META_DATA");
		}

		DataSource.Init();

		for (int i = 0; i < controlList.length; i++) {
			controlList[i] = fc.createContorl(ControlFactoryList.values()[i]);
		}

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
			if (mainView())
				break;
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
					insertSelfStudySchdule();
				break;
			case 4:
				if (((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin"))
					removeUser();// 관리자만 가능
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
		// 관리자면 날짜별로 기록 확인할수 있도록 아니면 그냥 금일 날짜에 대한 기록만 출력
		if (!((UserControl) controlList[ControlFactoryList.USER.ordinal()]).getUser().getType().equals("Admin")) {
			for (Object ss : controlList[ControlFactoryList.SELF_STUDY.ordinal()].select()) {
				System.out.println((SelfStudy) ss); // 오늘 &&해당 건물의 층의 자습대장 출력
			}
		} else {
			System.out.print("확인하고 싶은 연도 : ");
			int year = sc.nextInt();
			System.out.print("확인하고 싶은 달 : ");
			int month = sc.nextInt();
			System.out.print("확인하고 싶은 일 : ");
			int day = sc.nextInt();

			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day);
			Date currentDay = new Date();
			// 오늘보다 날짜가 더 큰경우 볼수 없으니 예외처리
			if (currentDay.compareTo(cal.getTime()) == 1) {
				// 해당 날짜의 모든기록 print
			} else {
				System.out.println("오늘보다 날짜가 더 큽니다. 다시 한번 확인해주세요.");
			}
		}
	}

	private void insertSelfStudySchdule() {
		System.out.println("======================================");
		System.out.println("======================================");
	}

	private void managementClassRoom() {
		while (true) {
			System.out.println("======================================");
			System.out.println("1. 강의실 확인");
			System.out.println("2. 강의실 추가");
			System.out.println("3. 강의실 삭제");
			//System.out.println("4. 강의실 수정");
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
				//updateClassRoom();
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
		for(Object cr : controlList[ControlFactoryList.CLASS_ROOM_CONTROL.ordinal()].select()) {
			System.out.println((ClassRoom)cr);
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
		
		if(controlList[ControlFactoryList.CLASS_ROOM_CONTROL.ordinal()].delete(new ClassRoom(0, address, building, floor, className))) {
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
		
		if(controlList[ControlFactoryList.CLASS_ROOM_CONTROL.ordinal()].insert(new ClassRoom(0, address, building, floor, className))) {
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
			System.out.println((User) u);
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
			System.out.println((User) u);
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
