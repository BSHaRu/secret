package f03_input;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ReadWriteEx {

	public static void main(String[] args) throws Exception {
		File file = new File("src/f03_input/ReadEx.java"); // 경로 맨앞에 뭐가 없으면 현재 폴더(프로젝트)를 말하는거임
		InputStream is = new FileInputStream(file);
		
		int data;
		OutputStream os = System.out; // System.out : console에 있는 OutputStream을 가지고 있다
		while((data = is.read()) != -1){	// new File("src/f03_input/ReadEx.java");을 다 읽으면 while문 종료
			os.write(data);	// write는 int타입을 받지만 출력은 byte타입을 출력하고 있다.
		}					// 원래 그런거니 그러러니 하셈
		os.flush();
		os.close();		// 여기서 console의 OutputStream을 close 했기 때문에 여기서부터는 console에 출력을 할 수 없다. 
		is.close();
		System.out.println("종료!!"); // 더 이상 console에 출력이 안된다.
		
	}

}
