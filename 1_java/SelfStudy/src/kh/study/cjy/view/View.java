package kh.study.cjy.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import kh.study.cjy.control.FactoryMethodControl;
import kh.study.cjy.control.RequestRegistUserControl;
import kh.study.cjy.control.UserControl;
import kh.study.cjy.database.DataSource;
import kh.study.cjy.etc.Banner;
import kh.study.cjy.etc.ControlFactoryList;
import kh.study.cjy.etc.FileInputOutput;
import kh.study.cjy.etc.FileList;
import kh.study.cjy.model.RequestRegistUser;
import kh.study.cjy.model.User;

public class View {
	private Scanner sc = new Scanner(System.in);
	private FactoryMethodControl fc = new FactoryMethodControl(); // 의존관계를 많이 해결했지만 원하는 메소드를 호출을 어떻게 할까?
	private UserControl uc = new UserControl();
	private RequestRegistUserControl rruc = new RequestRegistUserControl();

	public View() {
		Banner.print();
		
		FileInputOutput fio = new FileInputOutput();
		if (!fio.isExist(FileList.SQL_META_DATA.name())) {
			fio.FileSave(FileList.SQL_META_DATA.name(), "SQL_META_DATA");
		}

		DataSource.Init();

		boolean flag = false;
		if (flag) {
			if (uc.registUser("ADMIN", "ADMIN", "ADMIN", 0, '남', "0", "ADMIN", "ADMIN")) {
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
				loginAfterView(uc.getUser().getType());
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
		if (uc.login(id, password)) {
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

		if (rruc.registRequestRegistUser(id, password, name, age, gender, phone, email, type)) {
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
			System.out.println("3. 로그아웃");
			if (uc.getUser().getType().equals("ADMIN"))
				System.out.println("4. 회원가입 요청처리"); // 관리자라면
			if (uc.getUser().getType().equals("ADMIN"))
				System.out.println("5. 회원 삭제"); // 관리자라면
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
				System.out.println("로그아웃 하여 메인으로 돌아갑니다.");
				bflag = true;
				break;
			case 4:
				if (uc.getUser().getType().equals("ADMIN"))
					acceptRequestRegistUser();// 관리자만 가능
				break;
			case 5:
				if (uc.getUser().getType().equals("ADMIN"))
					removeUser();// 관리자만 가능
				break;
			default:
				break;
			}
		}

		return returnValue;
	}

	private void printTodaySelfStudySchdule(String type) {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formatedNow = now.format(formatter);

		System.out.println("======================================");
		// 관리자면 날짜별로 기록 확인할수 있도록 아니면 그냥 금일 날짜에 대한 기록만 출력
		if (!uc.getUser().getType().equals("ADMIN")) {
			// 오늘기록 print
		} else {
			System.out.print("확인하고 싶은 연도 : ");
			int year = sc.nextInt();
			System.out.print("확인하고 싶은 달 : ");
			int month = sc.nextInt();
			System.out.print("확인하고 싶은 일 : ");
			int day = sc.nextInt();

			// 오늘보다 날짜가 더 큰경우 볼수 없으니 예외처리
			if (true) {
				// 모든기록 print
			} else {
				System.out.println("오늘보다 날짜가 더 큽니다. 다시 한번 확인해주세요.");
			}
		}
	}

	private boolean printMyInformation() {
		boolean returnValue = false;

		System.out.println("======================================");
		System.out.println("ID : " + uc.getUser().getId());
		System.out.println("Name : " + uc.getUser().getName());
		System.out.println("Gender : " + uc.getUser().getGender());
		System.out.println("Email : " + uc.getUser().getEmail());
		System.out.println("Phone : " + uc.getUser().getPhone());
		System.out.println("======================================");

		System.out.print("비밀번호를 바꾸시겠습니까?(y/n) : ");
		char select = sc.next().charAt(0);
		sc.nextLine();

		if (select == 'y' || select == 'Y') {
			System.out.print("현재 Password : ");
			String currentPassword = sc.nextLine();
			System.out.print("바꿀 Password : ");
			String changePassword = sc.nextLine();
			uc.changePassword(currentPassword, changePassword);
			System.out.println("비밀번호가 정상적으로 바뀌었습니다.");
			returnValue = true;
		} else {
			System.out.println("비밀번호가 변경을 취소하여 전으로 돌아갑니다.");
		}

		return returnValue;
	}

	private void acceptRequestRegistUser() {
		List userList = rruc.getRequestRegistUser();

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
				if (uc.registUser(rru.getUserId(), rru.getPassword(true), rru.getName(), rru.getAge(), rru.getGender(),
						rru.getPhone(), rru.getEmail(), rru.getType())) {
					if (rruc.deleteRequestRegistUser(rru.getUserId(), rru.getName(), rru.getAge(), rru.getPhone())) {
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
				if (rruc.deleteRequestRegistUser(id, name, age, phone)) {
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
		List userList = uc.getUserList();
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
		if (uc.deleteUser(id, name, age, phone)) {
			System.out.println("해당항목을 회원에서 삭제 완료하였습니다.");
		} else {
			System.out.println("삭제가 실패 되어 이전으로 돌아갑니다.");
		}
	}
}
