package database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kh.study.cjy.model.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;

public class Functional {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/server?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private String user = "root";
	private String password = "root";

	public Statement Connect() {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// Connection & Statement 어떻게 해제할건데?
		return stmt;
	}

	public ResultSet CallSql(Statement stmt, String strSql) {
		try {
			return stmt.executeQuery(strSql);
		} catch (Exception e) {
			e.printStackTrace();
			// Connection & Statement 어떻게 해제할건데?
			return null;
		}
	}

	public List<Object> ResultSetToArray(ResultSet resultSet, Class clazz, Object... args) {
		List<Object> returnValue = new ArrayList<Object>();
		
		try {
			while (resultSet.next()) {
				// Mysql 컬럼이랑 DtoClass의 속성변수들과 어떻게 맵핑 시킬까?
				/*
				String userId = resultSet.getString("user_id");
				String userPw = resultSet.getString(2);
				String name = resultSet.getString("name");
				String phone = resultSet.getString("phone");
				String grade = resultSet.getString("grade");
				int age = resultSet.getInt("age");
				*/
				List<Map<String, Class>> arrField = GetField(clazz);

				List<Object> listCol = new ArrayList<Object>();

				for (Map<String, Class> str : arrField) {
					for (String str1 : str.keySet()) {

						switch (str.get(str1).toString()) {
						case "class java.lang.String":
							listCol.add(resultSet.getString(str1));
							break;
						case "class java.lang.Integer":
							listCol.add(resultSet.getInt(str1));
							break;
						default:
							throw new IllegalArgumentException("Unexpected value: " + str.get(str1));
						}

						//System.out.println("키 : " + str1);
						//System.out.println("값 : " + str.get(str1).toString());
					}
				}

				Object[] arrayObj = new Object[listCol.size()];

				int cnt = 0;

				for (Object o : listCol) {
					arrayObj[cnt] = o;
					cnt++;
				}

				returnValue.add(GetObject(clazz, arrayObj));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// ResultSet 어떻게 해제할건데?
		}
		return returnValue;
	}

	public List<Map<String, Class>> GetField(Class clazz) {
		List<Map<String, Class>> returnValue = new ArrayList<Map<String, Class>>();

		try {
			Class<?> class1 = Class.forName(clazz.getName());
			Field[] field = class1.getDeclaredFields();

			for (Field f : field) {
				Map<String, Class> m = new HashMap<>();
				m.put(f.getName(), Class.forName(f.getType().getName().toString()));
				returnValue.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnValue;
	}

	public Object GetObject(Class clazz, Object... args) {
		Object returnValue = null;

		List<Class<?>> list = new ArrayList<Class<?>>();
		Class<?>[] claz;
		for (Object o : args) {
			list.add(o.getClass());
		}

		claz = new Class<?>[list.size()];
		
		int cnt = 0;
		for (Class<?> c : list) {
			claz[cnt] = c;
			cnt++;
		}

		try {
			Class<?> class1 = Class.forName(clazz.getName());
			returnValue = class1.getConstructor(claz).newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnValue;
	}
}
