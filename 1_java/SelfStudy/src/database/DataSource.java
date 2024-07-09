package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import etc.FileInputOutput;
import etc.FileList;

public class DataSource {
	private static Map<String, String> prop = new HashMap<String, String>();
	private FileInputOutput fio = new FileInputOutput();
	
	{
		fio.FileOpen(FileList.Setting_SQL.name());
		Properties properties = fio.getData();
		for(String key : properties.stringPropertyNames()) {
			prop.put(key, properties.getProperty(key));
		}
	}
	
	public static Connection Connect() {
		Connection conn = null;
		
		try {
			Class.forName(prop.get("driver"));

			conn = DriverManager.getConnection(prop.get("url"), prop.get("user"), prop.get("password"));
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

}