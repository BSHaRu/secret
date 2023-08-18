package c3_map;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesEx {

	public static void main(String[] args) {
		Properties prop = new Properties();	// class Properties extends Hashtable<Object,Object>
		prop.put("key", "value");	
		prop.put("score", 10);
		System.out.println(prop.get("score"));
		prop.clear();
		System.out.println(prop.size());
		prop.setProperty("key", "key value"); // setProperty("문자열","문자열"); String으로 제한되어있음
		String value = prop.getProperty("key");
		System.out.println("value : " + value);
		System.out.println(prop.getProperty("김유신","김유신 존재안함")); // getProperty("A","B"); 
											// -> A가 없으면 B를 반환함
		System.out.println("==========================================");
		// 경고
		String path = PropertiesEx.class.getResource("db.properties").getPath();
		System.out.println(path);	// 이게 안나오면 workspace 문제 있는거임(한글 or 공백 등등)
		
		Properties prop2 = new Properties();
		try {
			prop2.load(new FileReader(path)); // 해당 경로가 없을 수도 있으니깐 try catch 써야된다네
			System.out.println(prop2.getProperty("driver"));
			System.out.println(prop2.getProperty("url"));
			System.out.println(prop2.getProperty("username"));
			System.out.println(prop2.getProperty("password"));
			System.out.println(prop2.getProperty("hangul"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	} // main 종료
}
