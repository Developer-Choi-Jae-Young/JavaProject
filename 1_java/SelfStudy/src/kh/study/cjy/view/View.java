package kh.study.cjy.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import etc.Banner;
import etc.FileInputOutput;
import etc.FileList;
import kh.study.cjy.control.UserControl;

public class View {
	private Scanner sc = new Scanner(System.in);
	private UserControl uc = new UserControl();
	
	public View() {
		Banner.print();
		
		FileInputOutput fio = new FileInputOutput();
		if(!fio.isExist(FileList.Setting_SQL.name())) {
			fio.FileSave(FileList.Setting_SQL.name(), "SQL_META_DATA");
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
		uc.registUser(id, password, name, age, gender, phone, email, type);
		
		System.out.println("회원가입을 완료하였습니다.");
		return returnValue;
	}

	private boolean loginAfterView(String type) {
		boolean returnValue = false;
		
		boolean bflag = false;
		
		while(true) {
			if(bflag) break;
			
			System.out.println("======================================");
			System.out.println("1. 내정보");
			System.out.println("2. 자습현황");
			System.out.println("3. 로그아웃");
			if (uc.getUser().getType().equals("ADMIN")) System.out.println("4. 회원가입 요청처리"); // 관리자라면
			System.out.println("======================================");
			System.out.print("선택 : ");
			int num = sc.nextInt();
			sc.nextLine();

			switch (num) {
			case 1:
				if(printMyInformation()) {
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
				if(uc.getUser().getType().equals("ADMIN")) acceptRequestRegistUser();//관리자만 가능
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
			//오늘기록 print
		} else {
			System.out.print("확인하고 싶은 연도 : ");
			int year = sc.nextInt();
			System.out.print("확인하고 싶은 달 : ");
			int month = sc.nextInt();
			System.out.print("확인하고 싶은 일 : ");
			int day = sc.nextInt();

			// 오늘보다 날짜가 더 큰경우 볼수 없으니 예외처리
			if (true) {
				//모든기록 print
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
		System.out.println("Gender : "+ uc.getUser().getGender());
		System.out.println("Email : "+ uc.getUser().getEmail());
		System.out.println("Phone : "+ uc.getUser().getPhone());
		System.out.println("======================================");
		
		System.out.print("비밀번호를 바꾸시겠습니까?(y/n) : ");
		char select = sc.next().charAt(0);
		sc.nextLine();
		
		if(select == 'y' || select == 'Y') {
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
		System.out.println("======================================");
		//회원가입 요청 리스트 출력
		System.out.println("======================================");
		
		System.out.print("회원가입 수락할 번호 : ");
		int num = sc.nextInt();
		sc.next();
		
		//인덱스 찾아가서 해당 인덱스의 정보만 쿼리문으로 insert하기
	}
}
