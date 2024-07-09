package etc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileInputOutput {
	private final String PATH = "./resource/";  
	
	private Properties prop = new Properties();
	
	{
		addData("driver", "com.mysql.cj.jdbc.Driver");
		addData("url", "jdbc:mysql://127.0.0.1:3306/server?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8");
		addData("user", "root");
		addData("password", "root");
	}
	
	public void FileSave(String fileName, String memo) {		
		try {
			prop.storeToXML(new FileOutputStream(PATH + fileName), memo);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void FileOpen(String fileName) {
		try {
			prop.loadFromXML(new FileInputStream(PATH + fileName));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExist(String fileName) {
		boolean returnValue = false;
		File file = new File(fileName);
		
		if(file.exists()) {
			returnValue = true;
		}
		
		return returnValue;
	}
	
	public <K, V> void addData(K key, V value) {
		prop.put(key, value);
	}
	
	public <T> void removeData(T key) {
		prop.remove(key);
	}
	
	public <K, V> V findData(K key) {
		 V returnValue = (V)prop.get(key);
		 return returnValue;
	}
	
	public Properties getData() {
		return prop;
	}
}
