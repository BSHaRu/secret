package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

	public static final String IP = "192.168.1.104";
	public static final int PORT = 5001;
	
	// port 할당 및 client 연결 수락
	ServerSocket serverSocket;
	
	// client들의 정보를 저장
	// id(key), 출력스트림(value)로 저장
	static Hashtable<String, PrintWriter> ht;
	
	// 연결된 client의 receive 스레드를 저장할 스레드 풀
	static ExecutorService serverPool;
	
	// 연결 수락 및 회원 관리
	public ChatServer() {
		try {
			serverPool = Executors.newFixedThreadPool(20);	// 스레드풀 최대 20개까지 생성해서 관리
//			serverSocket = new ServerScoket(PORT);
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(IP,PORT));
		} catch (IOException e) {
			System.out.println("bind 할 수 없는 IP 또는 PORT번호 입니다." + e.getMessage());
			return;
		}
		
		ht = new Hashtable<>();
		System.out.println("****** 채팅 서버 오픈 ******");
		while(true) {
			try {
				System.out.println("[ Client 연결 기다리는 중...]");
				// client 연결 수락 완료 될때까지 blocking
				Socket client = serverSocket.accept();
				// 연결 요청이 들어온 client의 IP 주소를 받아옴
				String clientIP = client.getInetAddress().getHostAddress();
				System.out.println(clientIP +" - 연결 수락");
				
				new ServerTask(client);
			} catch (IOException e) {
				System.out.println("Server 통신 오류 : " + e.getMessage());
				ht.clear();
				serverPool.shutdownNow();
				if(serverSocket != null && !serverSocket.isClosed()) {
					try {
						serverSocket.close();
					} catch (IOException e1) {}
				} // if end
				break;
			}
		} // while 종료
	} // ChatSever 종료
	
	
	public static void main(String[] args) {
		new ChatServer();
		
		
	}

}
