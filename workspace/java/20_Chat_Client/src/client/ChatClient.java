package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

	public static final String IP = "192.168.1.104";
	public static final int PORT = 5001;
	
	// server에 대한 정보를 저장하는 Socket
	Socket server;
	// server로 출력할 스트림
	PrintWriter pw;
	// server에서 입력받을 스트림
	BufferedReader br;
	// console에서 출력 메세지를 입력받을 스트림
	BufferedReader read;
	
	// 입력 스트림 반복문 종료용 flag
	boolean isRun = true;
	
	ChatClient(){
		try {
			connect();
		} catch (Exception e) {
			System.out.println("서버와 연결이 종료되어있습니다.");
		}
	}
	
	// 서버와 연결
	public void connect() throws Exception{
		System.out.println("****** 클라이언트 ******");
		System.out.println("연결 요청 : "+IP+" : "+ PORT);
		server = new Socket(IP,PORT);
		String serverIP = server.getInetAddress().getHostAddress();
		System.out.println("연결 성공 : "+ serverIP);
		
		// console에서 문자열 입력 받을꺼임
		InputStream is = System.in;
		// 1byte를 2byte 문자열로 읽어옴
		InputStreamReader reader = new InputStreamReader(is);
		// console에서 한줄 단위로 읽어올 수 있게 해줌
		read = new BufferedReader(reader);
		
		// server에서 출력된 내용을 읽어들일 스트림
		InputStream sis = server.getInputStream();
		InputStreamReader sisr = new InputStreamReader(sis);
		br = new BufferedReader(sisr);
		
		// client가 server로 출력
		OutputStream os = server.getOutputStream();
		// 1byte기반을 2byte 문자열로 바꿔서 출력
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter sbw = new BufferedWriter(osw);
		pw = new PrintWriter(sbw,true);
		
		// server에서 전달된 데이터를 받을 준비
		receive();
		
		// server에 데이터 전달
		System.out.println("사용할 이름(아이디)을 입력해주세요");
		while(isRun) {
			// console에서 사용자가 서버에 전달할 데이터를 입력 받음
			String sendData = read.readLine();
			// 사용자가 입력한 data를 서버에 출력(전달)
			pw.println(sendData);
		} // while end
	} // connect end
	
	// server에서 메세지를 전달 받음
	public void receive() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					String receiveData;
					try {
						if((receiveData = br.readLine()) != null) {
							System.out.println(receiveData);
						} // while > if end
					} catch (IOException e) {
						System.out.println("서버 통신 오류 : " + e.getMessage());
						stopClient();
						break;
					} // try catch end
				}// while end
			} // run end
		}); // thread end
		t.start();
	}
	
	// server와 연결 종료 및 자원 해제
	public void stopClient() {
		try {
			if(server != null && !server.isClosed()) {
				server.close();
				System.out.println("서버 닫힘");
			}
		} catch (IOException e) {}
		isRun = false;	// 이걸 안해주면 server에 데이터 전달에 있는 while문이 종료없이 무한 반복함 
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}

}
