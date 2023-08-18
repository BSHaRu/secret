package n3_chat.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientEx {

	Scanner sc = new Scanner(System.in);
	
	// server 연결 정보를 저장
	Socket socket;

	ChatClientEx(){
		startClient();
	}
	
	// server에서 전달된 message 수신
	public void receive() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					InputStream is = null;
					byte[] bytes = new byte[100];
					try {
						is = socket.getInputStream();
						int readBytes = is.read(bytes);
						String message = new String(bytes,0,readBytes,"UTF-8");
						System.out.println(message);
					} catch (IOException e) {
						System.out.println(" 서버와 연결 종료");
						try {
							if(is != null) is.close();
						} catch (IOException e1) {}
						stopClient();
						break;
					}
				}
			}
			
		});
		t.start();
	}
	
	// server에 message 전달
	public void send(String message) {
		OutputStream os = null;
		
		byte[] bytes = message.getBytes();
		try {
			os = socket.getOutputStream();
			os.write(bytes);
			os.flush();
		} catch (IOException e) {
			System.out.println("서버 통신 안됨");
			try {
				if(os != null) os.close();
			} catch (IOException e1) {}
			stopClient();
		}
	}
	
	// client 실행
	public void startClient() {
		
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress("192.168.1.104",5002));
			System.out.println("연결된 정보 : "+ socket.getRemoteSocketAddress());
			// 서버에서 전달되는 메세지 전달 시작
			receive();
			
			while(true) {
				System.out.println("메세지 입력 : ");
				String message = sc.nextLine();
				send(message);
			}
			
		} catch (IOException e) {
			System.out.println("서버와 통신 안됨 : " + e.getMessage());
			stopClient();
			return;
		}
	}
	
	// client 종료 - 자원해제
	public void stopClient() {
		System.out.println("연결 종료");
		if(socket != null && !socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {}
		} // if 종료
	}
	
	public static void main(String[] args) {
		new ChatClientEx();
	}

}
