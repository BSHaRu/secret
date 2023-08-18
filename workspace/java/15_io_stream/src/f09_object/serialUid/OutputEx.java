package f09_object.serialUid;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class OutputEx {

	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("uid.dat");
		ObjectOutputStream oss = new ObjectOutputStream(fos);
		
		ClassA a = new ClassA();
		a.filed1 = 1;
		oss.writeObject(a);
		oss.flush();
		oss.close();
	}

}
