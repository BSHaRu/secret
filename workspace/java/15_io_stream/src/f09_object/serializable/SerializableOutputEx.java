package f09_object.serializable;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializableOutputEx {

	public static void main(String[] args) throws Exception {
		File file = new File("person.dat");
		
		
		ObjectOutputStream oos = null;
		if(!file.exists() || file.length() == 0) {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream("person.dat",true)
							) // new BufferedOutputStream 괄호
					); // new ObjectOutputStream 괄호
		}else {
			oos = new ObjectAppendStream(
					new BufferedOutputStream(
							new FileOutputStream("person.dat",true)
							) // new BufferedOutputStream 괄호
					); // new ObjectOutputStream 괄호
		} // if문 종료
		
		Person p = new Person();
		p.setName("이순신");
		p.setAge(500);
		oos.writeObject(p);
		
		Person p1 = new Person();
		p1.setName("홍길동");
		p1.setAge(30);
		oos.writeObject(p1);
		
		oos.flush();
		oos.close();
	}

}
