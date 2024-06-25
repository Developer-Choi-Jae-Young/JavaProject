package kh.study.cjy.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class View {
	private Scanner sc = new Scanner(System.in);

	public View() {
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
				loginAfterView();
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
		if (true) {
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
		System.out.print("Gender : ");
		char gender = sc.nextLine().charAt(0);
		System.out.println("======================================");

		return returnValue;
	}

	private boolean loginAfterView() {
		boolean returnValue = false;

		System.out.println("======================================");
		System.out.println("1. 내정보");
		System.out.println("2. 자습현황");
		System.out.println("3. 로그아웃");
		if (false) System.out.println("4. 회원가입 요청처리"); // 관리자라면
		System.out.println("======================================");
		System.out.print("선택 : ");
		int num = sc.nextInt();
		sc.nextLine();

		switch (num) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}

		return returnValue;
	}

	private void printTodaySelfStudySchdule(String type) {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formatedNow = now.format(formatter);

		System.out.println("======================================");
		// 관리자면 날짜별로 기록 확인할수 있도록 아니면 그냥 금일 날짜에 대한 기록만 출력
		if (false) {
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
}
