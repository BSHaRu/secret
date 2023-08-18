package n3_chat.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class ChatServerEx {

	// 필드(전역변수)로 쓰기 위해서 맨위에 선언
	ServerSocket serverSocket; 
	// 연결된 client를 list로 만듬
	static List<Client> clients = new Vector<>(); 
	
	public ChatServerEx() {
		startServer();
	}
	
	// server 포트를 할당받아 시작하고 client의 연결 담당
	public void startServer() {
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(
				new InetSocketAddress("192.168.1.104",5002)
			);
			System.out.println("[Server Open ]"+serverSocket.getLocalSocketAddress());
		} catch (IOException e) {}
		while(true) {
			try {
				System.out.println("[ Client 연결 대기중.. ]");
				Socket client = serverSocket.accept();
				// client의 ip와 port를 isa에 담음
				InetSocketAddress isa 
					= (InetSocketAddress)client.getRemoteSocketAddress(); 
				// isa에 있는 ip값만 host에 담음
				String host = isa.getHostName(); 
				System.out.println("[연결 수락 : "+host+"]");
				
				// 지속적으로 사용하려고 list 저장
				clients.add(new Client(client)); 
				System.out.println("연결된 클라이언트 수 : " + clients.size());
			} catch (IOException e) { 
				// accept()에서 문제가 발생하면 
				// 더이상 server를 받을 수 없으니깐 서버 종료 시킴
				System.out.println("서버 종료 : " + e.getMessage());
				stopServer();
				break;
			} // try catch 종료
		} // while 종료
	} // startServer 종료
	
	// server가 안전하게 종료될 수 있도록 자원 해제
	public void stopServer() {
		System.out.println("서버 종료");
		try {
			for(Client client : clients) {
				if(!client.socket.isClosed()) {
					client.socket.close();
				} // client 소켓 닫기
			} // for문 종료
			
			// serverSocket 닫기
			if(!serverSocket.isClosed()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} //
	
	public static void main(String[] args) {
		new ChatServerEx();
	}

}
