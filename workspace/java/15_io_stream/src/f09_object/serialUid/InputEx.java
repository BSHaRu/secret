package f09_object.serialUid;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class InputEx {

	public static void main(String[] args) throws Exception{
		ObjectInputStream ois 
			= new ObjectInputStream(
					new FileInputStream("uid.dat")
				);
		
		ClassA a = (ClassA)ois.readObject();
		System.out.println(a.filed1);
		
		ois.close();
	}

}
