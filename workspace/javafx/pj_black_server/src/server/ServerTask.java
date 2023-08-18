package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

// 연결된 client와 통신을 진행할 작업
public class ServerTask implements Runnable {

	// 현재 연결된 client Socket 정보
	Socket client;
	// display 정보가 있어야 될 helper
	ServerHelper sh;
	// 연결된 client로 출력
	PrintWriter pw;
	// 연결된 client에서 데이터 받기
	BufferedReader br;
	
	// 사용자 닉네임
	String nickName;
	// receive flag
	boolean isRun = true;
	
	public ServerTask(Socket client, ServerHelper sh) {
		this.client = client;
		this.sh = sh;
		
		try {
			pw = new PrintWriter(
					new BufferedWriter(
						new OutputStreamWriter(
							client.getOutputStream())	
					),true // autoflush
				);
			br = new BufferedReader(
					new InputStreamReader(
						client.getInputStream())
				);
			
		} catch (IOException e) {
			System.out.println("Client 연결 오류 : "+e.getMessage());
		}
	}

	// client한테 receive
	@Override
	public void run() {
		while(isRun) {
			try {
				String receiveData = br.readLine();
				System.out.println(receiveData);
				// client와 연결 끊겼으면 해당 while문 탈출
				if(receiveData == null) break;
				
				String[] data = receiveData.split("\\|");
				if(data.length < 2) {
					sh.printText(receiveData);
					continue;
				}
				System.out.println(data);
				// 0| 닉네임 
				// 1| 딜러 text
				// 2| 유저 text
				String order = data[0];
				String text = data[1];
				
				switch(order) {
					case "0" :
						// 닉네임을 이용해서 hashtable에 client 정보를 등록
						// 이미 존재하는 닉네임은 재요청 할 수 있도록 종료
						if(sh.ht.containsKey(text)) {
							// 이미 존재하는 닉네임
							this.pw.println("0|사용할 수 없는 닉네임입니다.");
							sh.printText(text + " - 사용 할 수 없는 닉네임이 요청 되었습니다.");
							isRun = false;
							this.client.close();
							break;
						}
						// 중복 닉네임이 없을 경우
						this.nickName = text;
						sh.ht.put(text, this.pw);
						/* 
						String sendData = "";
						// ht에 있는 모든 key값을 가져옴
						for(String s : sh.ht.keySet()) {
							sendData += s;
						}
						broadCast(0,sendData);
						*/
						sh.printText(text+"이 접속하였습니다.");
						break;
					case "1":
						// 메세지 전달
						broadCast(1, "Dealer : " + text);
						break;
					case "2" :
						String msg = this.nickName+" : " + text;
						broadCast(2, msg);
						sh.printText(msg);
						break;
				} // switch end
			} catch (IOException e) {
				System.out.println("Client 연결 오류 : "+e.getMessage());
				isRun = false; // 오류뜨면 while문 종료
			} // try catch end
		}// while end 
		
		// -> client한테 receive 받을게 없으면 이하구문들 실행
		if(client != null && !client.isClosed()) {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // if end
		
		sh.ht.remove(this.nickName);
		String list = "";
		for(String s : sh.ht.keySet()) {
			list += s+",";
		}
		broadCast(0,list);
		broadCast(1,nickName+"님이 나가셨습니다.");
		
		sh.printText(nickName+"님이 나가셨습니다.");
	}

	// 연결된 모든 client에게 메세지 전달
	private void broadCast(int order, String msg) {
		for(PrintWriter p : sh.ht.values()) { // values() == pw의 order
			p.println(order + "|" + msg);
		}
	}
}
