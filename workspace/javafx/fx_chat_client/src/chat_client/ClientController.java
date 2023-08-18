package chat_client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController implements Initializable {

	@FXML private TextArea textDisplay;
	@FXML private ListView<String> userID;
	@FXML private Button btnConn, btnSend;
	@FXML private TextField textIP, textNick, textInput;
	
	// 연결된 서버의 소켓 정보
	Socket server;
	// 연결 요청을 보낼 server ip 주소
	InetAddress ip;
	// 사용자 닉네임
	String nickName;
	
	// server로 데이터 출력
	PrintWriter pw;
	// server에서 데이터 읽음
	BufferedReader br;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(()->{
			textIP.requestFocus();
		});
		
		btnConn.setOnAction(e->{
			try {
				String txtIP = textIP.getText().trim();
				// 문자열 ipv4 주소 값을 받아서 address로 반환
				// 정상적인 ip주소나 Domain이 아니면 예외 발생
				ip = InetAddress.getByName(txtIP);
				String txtNick = textNick.getText().trim();
				// 닉네임도 작성해야 채팅 할 수 있게 만듬.
				if(txtNick.equals("")) {
					displayText("닉네임을 작성해 주세요");
					textNick.requestFocus();
					return;
				}
				nickName = txtNick;
				startClient(); // ip값과 닉네임을 입력되면 start.
				
			} catch (UnknownHostException e1) {
				displayText("사용 할 수 없는 주소입니다. 주소를 확인해 주세요");
				textIP.requestFocus();
			}
		});
		
		btnSend.setOnAction(e ->{
			String txt = textInput.getText().trim(); // 좌우공백 없애기
			if(txt.equals("")) {
				displayText("메세지를 입력해주세요");
				textInput.requestFocus();
				return;
			}
			// 0 | 닉네임
			// 1 | 메세지
			send(1,txt);
		});
		
	} // initilize end
	
	// client 실행
	public void startClient() {
		try {
			// Socket이 생성 될 때 해당 ip와 port 번호로
			// 연결 요청을 전달하고, 연결 수락이 완료되면 Socket 정보를 저장.
			server = new Socket(ip,5001);
			displayText("[ 연결 완료 : ] " +server.getRemoteSocketAddress());
			
			pw = new PrintWriter(
					new BufferedWriter(
						new OutputStreamWriter(
							server.getOutputStream()))
					,true);
			
			br = new BufferedReader(
					new InputStreamReader(
						server.getInputStream())
					);
			
			// 연결이 완료 되었으면 btn을 활성화 시켜줌
			btnSend.setDisable(false); 
			send(0,nickName);
			
		} catch (IOException e) {
			displayText("[서버와 연결 안됨]");
			stopClient();
			return;
		}
		// 서버에서 전달된 내용을 읽을 수 있도록 receive 호출
		receive();
	}
	
	// client 자원 해제
	public void stopClient() {
		try {
			displayText("[ Server 연결 종료] ");
			if(server != null && !server.isClosed()) {
				server.close();
			} 
		}catch (IOException e) {}
	}
	
	// server에 데이터 전달
	public void send(int order, String txt) {
		// order 번호에 따라서 server에서 구분지어서 전달
		// 0 | 닉네임
		// 1 | 메세지
		pw.println(order + "|" + txt);
		textInput.clear();
		textInput.requestFocus();
	}
	
	// server에서 데이터를 읽음
	public void receive() {
		Thread t = new Thread(()->{
			while(true) {
				try {
					String receiveData = br.readLine();
					if(receiveData == null) break;
					// 0| 사용자 목록
					// 1| 메세지
					String[] data = receiveData.split("\\|");
					String order = data[0];
					String txt = data[1];
					
					if(order.equals("0")) {
						// 사용자 목록 갱신
						// txt : [홍길동, 김유신, 이몽룡...] 
						Platform.runLater(()->{
							String[] nameList = txt.split("\\,");
							userID.setItems(
								FXCollections.observableArrayList(nameList)
							);
						});
						
					}else if(order.equals("1")) {
						displayText(txt);
					}
				} catch (IOException e) {
					stopClient(); // 오류나면 client 자원 해제 후 while 탈출
					break;
				}
			} // while end
		});
		t.start();
	}
	
	// textDisplay textArea에 text 추가
	public void displayText(String txt) {
		Platform.runLater(()->{
			textDisplay.appendText(txt+"\n");
		});
	}
	
}









