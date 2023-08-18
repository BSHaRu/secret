package f09_object;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class ObjectStreamEx {

	public static void main(String[] args) {
		// try with resources 절 : try()안에 있는 것을 자동으로 close를 해줌
		try(FileInputStream fis = new FileInputStream("object.dat");
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);	
			// 자동으로 close해주기때문에 입력이 먼저 close하고 출력이 나중에 close되어야되니 여기선 입력을 먼저 써줘야됨 
			FileOutputStream fos = new FileOutputStream("object.dat");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);		){
					
			
			oos.writeObject(new Integer(100));		// Number에 상속되어 있는 친구들은 다 Number에 Serializable에 되어있음
			oos.writeObject(new Double(3.14));
			oos.writeObject(new int[] {1,2,3,4});
			oos.writeObject(new String("홍길동"));
			oos.flush();
			
			Integer obj1 = (Integer)ois.readObject(); // obj로 들고오기 때문에 무슨 타입으로 오는지 모르기때문에 해당 타입 변환해줘야됨
			Double obj2 = (Double)ois.readObject();	//	readObject()에서 해당 class를 못찾을 수 있으니 예외처리 같이 해줘야됨
			int[] obj3 = (int[])ois.readObject();
			String obj4 = (String)ois.readObject();
			
			System.out.println(obj1);
			System.out.println(obj2);
			System.out.println(Arrays.toString(obj3));
			System.out.println(obj4);
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}//main 종료

}
