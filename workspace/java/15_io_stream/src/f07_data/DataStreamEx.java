package f07_data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamEx {

	public static void main(String[] args) {
		// 기본타입 8가지 + String
		try {
			FileOutputStream fos = new FileOutputStream("primitive.txt");
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeUTF("홍길동");
			dos.writeDouble(99.9);
			dos.writeInt(100);
			dos.writeBoolean(true);
			
			dos.writeUTF("고길동");
			dos.writeDouble(6.9);
			dos.writeInt(60);
			dos.writeBoolean(false);
			
			dos.flush();
			dos.close();
			
			DataInputStream dis = new DataInputStream(new FileInputStream("primitive.txt"));
			String name = dis.readUTF();
			double score = dis.readDouble();
			int order = dis.readInt();
			boolean checked = dis.readBoolean();
			System.out.println("name : " + name);
			System.out.println("score : " + score);
			System.out.println("order : " + order);
			System.out.println("checked : " + checked);
			System.out.println("============================================");
			
			name = dis.readUTF();
			score = dis.readDouble();
			order = dis.readInt();
			checked = dis.readBoolean();
			System.out.println("name : " + name);
			System.out.println("score : " + score);
			System.out.println("order : " + order);
			System.out.println("checked : " + checked);
			// 입력 받은 타입 순서대로 출력도 그 순서대로 해줘야됨.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
