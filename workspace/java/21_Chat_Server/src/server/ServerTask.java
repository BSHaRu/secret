package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

			// 서버 작업
public class ServerTask implements Runnable {

	// 현재 task에 등록된 client 정보를 저장
	Socket client;
	
	PrintWriter pw; 	// client에게 메세지를 전달할 스트림
	BufferedReader br; 	// client에게 메세지를 전달 받을 스트림
	String userID;		// 현재 작업의 사용자 ID
	
	// server로부터 accept된 Client의 Socket 정보를 
	// 생성자의 매개변수로 전달받아 초기화 진행
	public ServerTask(Socket client) {
		this.client = client;
		
		try {
			// pw = new PrintWriter(기반스트림, autoFlush);
			pw = new PrintWriter(
					new BufferedWriter(			// 출력 부스팅
						new OutputStreamWriter( // 2byte 기반 출력 스트림
							client.getOutputStream())
					),true  // bw end // true를 넣음으로써 autoFlush가됨
				); // pw end
			
			br = new BufferedReader(
					new InputStreamReader(client.getInputStream())
				);
			
			Runnable run = new Runnable() {

				@Override
				public void run() {
					while(true) {
						System.out.println("ID 입력대기");
						try {
							// ID 입력전까지 blocking
							userID = br.readLine();
						} catch (IOException e) {}
						System.out.println(userID);
						
						// userID값이 공백이거나, 중복되면 안되니깐 여기서 확인
						if(userID.trim().equals("") ||
								ChatServer.ht.containsKey(userID)) {
							// 아이디를 입력한 client에게 사용 불가능 한 아이디라고 출력
							pw.println("사용할 수 없는 아이디 입니다. 다시 요청해주세요");
							// server 콘솔에 잘못된 요청이 들어왔다가 출력
							System.err.println(userID+" 사용할 수 없는 아이디로 요청");
							// 아이디를 다시 입력받로고 while문으로 돌아감
							continue;
						} // if end
						
						ChatServer.ht.put(userID, pw); // PrintWriter pw
						System.out.println(userID+ "님이 입장하셨습니다.");
						System.out.println("방인원은 : "+ChatServer.ht.size());
						// 반복문 탈출하기 전에 ServerTask가 들고있는 runnable이 실행
						ChatServer.serverPool.submit(ServerTask.this); 
						break;
					} // while end
				} // run end
			}; // Runnable end
			ChatServer.serverPool.submit(run);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // ServerTask end


	// client에서 출력되는 내용을 입력받는 receive 역할.
	@Override
	public void run() {
		while(true) {
			/*	해당 단어를 입력하면 해당내용 실행.
			 	/quit -> 종료
			 	/w 아이디 메세지 -> 귓속말
			 	나머지는 전체 메세지
			 */
			try {
				// 각 client가 전달하는 메세지를 입력받음
				String receiveData = br.readLine();
				if(receiveData.trim().equals("/quit")) {
					break;
				}else if(receiveData.indexOf("/w") > -1) {
					sendMsg(receiveData);
				}else {
					broadCast(userID+" : "+receiveData);
				}
			} catch (IOException e) {
				System.out.println("Client 통신 오류");
				break;
			}
		} // while end
		
		// 종료한 사용자 정보 Hashtable에서 삭제
		ChatServer.ht.remove(userID); // key값으로 사용하고 있는걸 remove하면 전체 삭제
		broadCast(userID+"님이 나가셨습니다.");
		System.out.println("방인원은 : "+ChatServer.ht.size());
	}

	// message를 전달 받아 연결된 모든 사용자에게 메세지 전달
	public void broadCast(String message) {
		// Hashtable에 저장이 된 value 묶음을 가지고 와서 순회하면서 출력
		for(PrintWriter p : ChatServer.ht.values()) {
			// 현재 사용자가 아닌 사람한테들한테만 해당내용을 출력
			if(this.pw != p) p.println(message);
		}
		// 해당 server에 메시지 출력
		System.out.println(message);
	}
	
	// 특정 사용자에게만 메세지 전달(귓속말)
	// /w 아이디 메세지
	public void sendMsg(String message) {
		int begin = message.indexOf(" ")+1; // 공백이 시작하고 난 다음의 인덱스 번호
		int end = message.indexOf(" ",begin); // indexOf("찾을 특정 문자" , "시작할 위치")
						// -> 첫번째 공백 ~ 그다음 공백이 나오는 곳의 인덱스 번호
							
		if(end != -1) {
			String id = message.substring(begin,end); // 아이디만 잘라냄
			String msg = message.substring(end+1); 	// 아이디 이후 해당 메세지를 잘라냄
						// -> /w 고길동 뭐함?
						// => begin : 고 | end : 동 | id : 고길동 | msg : 뭐~
			PrintWriter pw = ChatServer.ht.get(id);	
			if(pw != null) {
				// 아이디가 일치하는 사용자가 존재 할 경우 해당 메세지 전달.
				pw.println(userID+"님의 귓속말 : " + msg); // pw : if내에 정의한 pw
			}else {
				// 일치하는 사용자 정보가 없음
				this.pw.println(id+"해당 id가 존재하지 않습니다."); // this.pw : 필드에 정의한 pw
			}
		} // if end
	}
	
}
