package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class ServerHelper {
	
	private TextArea log;
	
	// 운영체제에서 사용할 수 있는 PORT를 할당받아
	// client socket 연결 관리를 할 객체
	ServerSocket server;
	
	// client receive thread를 관리할 스레드 풀
	ExecutorService serverPool;
	
	// client 사용자 정보를 저장할 map
	// <사용자 닉네임(Key), client socket 출력 스트림>
	Hashtable<String, PrintWriter>ht;
	
	// xtml파일이 여기서 호출되는게 아니라 밖에서 호출되기때문에 set으로 불러옴
	public void setLog(TextArea log) {
		this.log = log;
	}
	
	public void startServer() {
		serverPool = Executors.newFixedThreadPool(10); // 스레드 풀 최대 10
		try {
			server = new ServerSocket();
			server.bind(new InetSocketAddress("192.168.1.104", 5001));
			log.appendText("[ 서버 시작 ]"+"\n");
			System.out.println("서버 시작");
		} catch (IOException e) {
			log.appendText("서버 연결 오류 : " + e.getMessage());
			System.out.println("서버 연결 오류 : " + e.getMessage());
			stopServer();
			return;
		}
		ht = new Hashtable<>();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while(true) {
					// client가 server에 연결
					try {
						Socket client = server.accept();
						// 어디서 연결 되었는지 정보 확인
						String address = client.getRemoteSocketAddress().toString();
						String message = "[연결 수락 : " +address+"]";
						ServerTask sc = new ServerTask(client, ServerHelper.this);
						serverPool.submit(sc);
						printText(message);
					} catch (IOException e) {
						ht.clear();
						stopServer();
						break;
					} 
				}// while end
			}
		}; // runnable end
		serverPool.submit(runnable);
	}

	public void printText(String text) {
		Platform.runLater(()->{
			log.appendText(text+"\n");
		});
	}
	
	public void stopServer() {
		try {
			if(ht != null) {
				for(PrintWriter pw : ht.values()) {
					if(pw != null) {
						pw.close();
					}
				}
			} 
			
			if(server != null && !server.isClosed()) {
				server.close();
			}
			
			if(serverPool != null && !serverPool.isShutdown()) {
				serverPool.getClass();
			}
			
			printText("[ 서버 중지!! ]");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
