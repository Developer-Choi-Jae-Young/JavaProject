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
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
		System.out.println("99. ����");
		System.out.println("======================================");
		System.out.print("���� : ");
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
			System.out.println("���α׷��� �����մϴ�.");
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
		System.out.println("1. �л�");
		System.out.println("2. ����");
		System.out.println("======================================");
		System.out.print("���� : ");
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
		System.out.println("1. ������");
		System.out.println("2. �ڽ���Ȳ");
		System.out.println("3. �α׾ƿ�");
		if (false) System.out.println("4. ȸ������ ��ûó��"); // �����ڶ��
		System.out.println("======================================");
		System.out.print("���� : ");
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
		// �����ڸ� ��¥���� ��� Ȯ���Ҽ� �ֵ��� �ƴϸ� �׳� ���� ��¥�� ���� ��ϸ� ���
		if (false) {
			//���ñ�� print
		} else {
			System.out.print("Ȯ���ϰ� ���� ���� : ");
			int year = sc.nextInt();
			System.out.print("Ȯ���ϰ� ���� �� : ");
			int month = sc.nextInt();
			System.out.print("Ȯ���ϰ� ���� �� : ");
			int day = sc.nextInt();

			// ���ú��� ��¥�� �� ū��� ���� ������ ����ó��
			if (true) {
				//����� print
			} else {
				System.out.println("���ú��� ��¥�� �� Ů�ϴ�. �ٽ� �ѹ� Ȯ�����ּ���.");
			}
		}
	}
}