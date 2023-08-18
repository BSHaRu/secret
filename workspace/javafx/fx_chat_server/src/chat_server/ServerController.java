package chat_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerController implements Initializable {

	@FXML private TextArea displayText;
	@FXML private TextField textPort;
	@FXML private Button btnStartStop;
	
	// client receive thread를 관리할 스레드 풀
	ExecutorService serverPool;
	
	// 운영체제에서 사용할 수 있는 PORT를 할당받아
	// client socket 연결 관리를 할 객체
	ServerSocket server;
	
	// client 사용자 정보를 저장할 map
	//<사용자 닉네임(key), client socket 출력 스트림>
	Hashtable<String, PrintWriter> ht;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnStartStop.setOnAction(e->{
			if(btnStartStop.getText().equals("START")) {
				startServer();
			}else {
				stopServer();
			} // if end
		});
	}
	
	// 서버 시작
	public void startServer() {
		serverPool = Executors.newFixedThreadPool(20); // 스레드 풀 최대 20개까지 생성
		ht = new Hashtable<>(); // client 저장 관리 할 ht생성
		
		try {
			String port = textPort.getText(); 
			
			if(!checkNumber(port)) {
				displayText.appendText("사용할 수 없는 port번호 입니다.");
				return;
			}
			int portNumber = Integer.parseInt(port); // port는 숫자기 때문에 integer로 변환시켜줌 
			
			// 위의 if를 통해서 양의 정수만 들어올 수 있게 확인 했기때문에 음수는 따로 확인 안해도됨.
			if(portNumber > 65535) { // port 최대 번호가 65,535임
				displayText.appendText("65,535 이하의 port 번호만 사용 가능합니다.");
			}
			
			server = new ServerSocket(portNumber);
			displayText.appendText("[ 서버 시작 ]");
		} catch (IOException e) {
			displayText.appendText("서버 연결 오류 : " +e.getMessage());
			stopServer();
			return;
		}
		
		// 스레드를 사용하기 위해선 runnable이 필요
		// -> runnable로 스레드를 사용하면 스레드 재사용 가능.
		// 스레드풀(serverPool)에 
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				// UI 변경 op Popup창 띄우면 오류 발생하니깐 platfrom.runLater 해줌.
				Platform.runLater(()->{
					btnStartStop.setText("STOP");
				});
				
				while(true) {
					try {
						printText("** Client 연결 대기중 **");
						Socket client = server.accept();
						// 어디서 연결 왔는지 정보를 가져오는거임
						String address = client.getRemoteSocketAddress().toString(); 
						String message = "[연결 수락 : "+address+"]";
						ServerTask sc = new ServerTask(client, ServerController.this); // ServerTask가 호출되면 runnable에 있는 run이 실행.
						serverPool.submit(sc);
						printText(message);
					} catch (IOException e) {
						stopServer();
						break;
					}
				} // while end
			}
			
		};
		serverPool.submit(runnable);
		
	}
	
	// printText에서 출력할 때 하나하나 "\n" 넣어주기 귀찮으니깐 
	// 호출되면 해당 문구 보여주고 자동 줄바꿈 하기위해서 만듬 
	public void printText(String text) {
		Platform.runLater(()->{		
			displayText.appendText(text+"\n");
		});
	}
	
	// 서버 중지
	public void stopServer() {
		try {
			// ht이 정보가 없으면 pw 닫아줌
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
				serverPool.shutdownNow();
			}

			printText("[ 서버 중지 ]");
			btnStartStop.setText("START");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkNumber(String text) {
		if(text.trim().length() == 0) {
			return false;
		}
		
		for(char c : text.toCharArray()) {
			if(c < 48 || c > 57) { // 48~59 : 아스키코드 0 ~ 9
				return false; 		// => 숫자가 아닌 값이 들어갈 경우 false값 반환 
			}
		} // for end
		return true;
	}
}
