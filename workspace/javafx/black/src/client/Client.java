package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	// 연결된 server의 socket 정보
	Socket server;
	// 사용자 닉네임
	public static String nickName;
	
	// server로 데이터 출력
	// send 메소드를 Start에서 불러오기 위해서 static으로 고정시킴
	static PrintWriter pw;
	// server에서 데이터 읽음
	BufferedReader br;
	
	// client 실행
	public void startClient() {
		try {
			// Socket 생성 될 때 ip와 port 번호로 연결 요청을 전달 하고,
			// 연결 수락이 완료 되면 연결된 server의 socket 정보로 저장??
			server = new Socket("192.168.1.104", 5001);
			System.out.println(server);
			System.out.println("[ 연결 완료 : ]" +server.getRemoteSocketAddress());
			
			// client가 server로 메세지 출력스트림
			pw = new PrintWriter(
					new BufferedWriter(
						new OutputStreamWriter(
							server.getOutputStream())	
					),true
				);
			// server에서 출력된 내용을 client가 읽어들일 스르림
			br = new BufferedReader(
					new InputStreamReader(
						server.getInputStream())
				);
		} catch (IOException e) {
			System.out.println("[ 서버와 연결 안됨 ]");
			stopClient();
			return;
		}
	}

	// client 자원 해제
	public void stopClient() {
		try {
			System.out.println("[ Sever 연결 종료 ]");
			if(server != null && !server.isClosed()) {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// server에 데이터 전달
	public static void send(int order, String text) {
		// 0 | 닉네임
		// 1 | text
		pw.println(order + "|" + text);
	}
	
	public static void send(String text) {
		pw.println(text+"님이 게임을 시작하였습니다.");
	}

}
