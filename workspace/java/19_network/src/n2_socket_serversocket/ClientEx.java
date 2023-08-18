package n2_socket_serversocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientEx {

	public static void main(String[] args) {
		System.out.println("[ 서버에 연결 요청 ]");
		try {
			Socket socket = new Socket("192.168.1.104",5001); // (ip주소, 연결할 포트번호) 
			System.out.println("[ 서버 연결 완료 ]");
			
			OutputStream os = socket.getOutputStream();
			String message = "메에ㅔ에에에로로로로롱";
			byte[] bytes = message.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			// 여기서 os.close();를 바로 해버리면 연결이 끊김
			System.out.println("데이터 전송 완료");
			
			
		} catch (UnknownHostException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		
	}

}
