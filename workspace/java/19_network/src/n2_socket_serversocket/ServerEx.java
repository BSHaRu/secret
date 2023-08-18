package n2_socket_serversocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEx {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(5001); // ()는 포트 번호를 지정해줘야함.
			System.out.println("[서버 오픈]");				// ->5001번 포트를 할당받아 서비스 오픈
			System.out.println("Client 연결 대기중...");
			Socket client = server.accept();
			System.out.println("연결 수락 완료");
			System.out.println(client.getRemoteSocketAddress());
						
			InputStream is = client.getInputStream();
			String message = "";
			byte[] bytes = new byte[100];
			int readCount = is.read(bytes); 
			message = new String(bytes,0,readCount,"UTF-8");
			System.out.println(client + " : [ 데이터 받기 완료 ]" + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
