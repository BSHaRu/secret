package f09_object.serializable;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SerializableInputEx {

	public static void main(String[] args) throws Exception {
		ObjectInputStream ois 
			= new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("person.dat")
					) // new BufferedInputStream 괄호
			); // new ObjectInputStream 괄호
		
		ArrayList<Person> list = new ArrayList<>();
		
		while(true) {
			try {	// 해당 파일의 크기와 개수를 모르니깐 try catch로 받아주고 다 읽었을경우 EOF를 통해 예외처리 해준다.
				Person p = (Person)ois.readObject();
				list.add(p);
				System.out.println(p);
			} catch (EOFException e) {
				System.out.println("파일 다 읽었습니다.");
				break;
			} // try catch 종료
		} // while문 종료
		System.out.println(list);
		ois.close();
		
	} // main 종료

}
