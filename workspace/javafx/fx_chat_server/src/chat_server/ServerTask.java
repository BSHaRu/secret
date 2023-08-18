package chat_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

//연결된 client와 통신을 진행할 작업 Task
public class ServerTask implements Runnable {

	// 현재 연결된 client Socket 정보
	Socket client;
	// display 정보가 있는 controller
	ServerController sc;
	// 연결된 client로 출력
	PrintWriter pw;
	//연결된 client에서 데이터 받기
	BufferedReader br;
	
	// 사용자 닉네임
	String nickName; 
	// receive flag
	boolean isRun = true; 
	
	public ServerTask(Socket client, ServerController sc) {
		this.client = client;
		this.sc = sc;
		
		try {
			// 출력 스트림
			OutputStream os = client.getOutputStream(); // 기반 스트림
			OutputStreamWriter osw = new OutputStreamWriter(os); // 보조
			BufferedWriter bw = new BufferedWriter(osw); // 보조
			pw = new PrintWriter(bw,true); // 보조 // true -> autoflush
			
			// 입력스트림 추가
			InputStream is = client.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			
		} catch (IOException e) {
			sc.printText("Client 연결 오류 : "+e.getMessage());
		} // try catch end
	}
	
	// client한테 독립적으로 receive
	@Override
	public void run() {
		// 반복하면서 출력된 내용 입력 받음 -> why?
		while(isRun) {
			try {
				// 사용자가 출력한 내용 읽음 ???
				String receiveData = br.readLine(); // 
				System.out.println(receiveData);
				// 클라이언트와 연결이 끊겼으면 해당 while문 탈출
				if(receiveData == null) break;  
				
				// 0 | 닉네임  
				// 1 | 메세지
				// | 를 잘라내기 위해 split을 씀
				String[] data = receiveData.split("\\|");
				System.out.println(data);
				String order = data[0];
				String txt = data[1];
				switch(order) {
					case "0" :
						// 닉네임을 이용해서 hashtable에 client 정보를 등록
						// 이미 존재하는 닉네임은 재요청 할 수 있도록 연결 종료
						// -> ServerController(sc)에 있는 hashtable(ht)의 containsKey(이건 나중에 찾아봐야될듯 ?)
						if(sc.ht.containsKey(txt)) { 
							// 이미 존재하는 닉네임으로 연결 요청
							this.pw.println("1|사용 할수 없는 닉네임입니다."); // pw : PrintWrinter
							sc.printText(txt + " - 사용할 수 없는 닉네임이 요청 되었습니다."); // 이건 서버에 알려주는거임
							isRun = false;
							this.client.close(); 
							break; 
						}
						// ???????
						this.nickName = txt;
						sc.ht.put(txt, this.pw);
						
						// 사용자 목록 갱신
						String sendData = "";
						// ht에 있는 모든 key값을 가져옴
						for(String s : sc.ht.keySet()) {
							sendData += s+",";
						}
						broadCast(0,sendData); // 모든 사람들에게 해당 메세지 출력
						
						broadCast(1,txt+"님이 입장 하셨습니다. - 방인원은 : "+sc.ht.size()); // 여기서 txt는 nickName
						break;
					case "1" :
						// 메세지 전달
						broadCast(1,this.nickName+" : " + txt); // 여기서 txt는 msg
						break;
				} // switch end
				
			} catch (IOException e) {
				System.out.println("Client 연결 오류 : "+e.getMessage());
				isRun = false; // 오류뜨면 while문 종료
			} // try catch end
		} // while end
		
		if(client != null && !client.isClosed()) {
			try {
				client.close();
			} catch (IOException e) {}
		}
		
		sc.ht.remove(this.nickName);
		String list = "";
		for(String s : sc.ht.keySet()) {
			list += s+",";
		}
		broadCast(0,list);
		broadCast(1,nickName+"님이 나가셨습니다. 방인원 : " + sc.ht.size());
		
	}
	// 연결된 모든 사용자(Client)에 메세지 전달
	public void broadCast(int order, String msg) {
		for(PrintWriter p : sc.ht.values()) { // values() == pw의 order
			p.println(order + "|" + msg);
		}
	}
	
	
}
