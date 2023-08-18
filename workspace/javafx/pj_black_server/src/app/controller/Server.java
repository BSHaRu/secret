package app.controller;

import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import server.ServerHelper;

public class Server implements Initializable {

	@FXML
	private Button exit, sSend;

	@FXML
	private TextArea log;
	
	@FXML 
	private TextField msg;

	public ServerHelper sh;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sh = new ServerHelper();
		sh.setLog(log);
		sh.startServer();
		exit.setOnAction(e -> {
			sh.stopServer();
			Window window = exit.getScene().getWindow(); // exit가 있는 현재창
			Stage stage = (Stage)window; // 그 창 == stage
			stage.close();				// 고로 stage(창)을 종료 해준다.
		});
		
		// 전송 버튼 눌렀을때 해당 내용 실행
		sSend.setOnAction((e)->{
			System.out.println(msg.getText());
			for(PrintWriter pw : sh.ht.values()) {
				pw.println("2|Dealer : "+msg.getText());
				sh.printText("Server : "+msg.getText());
			}
			// 추가
			msg.clear();
			msg.requestFocus();
		});
		
		// 엔터키 누르면 전송됨
		msg.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER) {
					sSend.fire();
				}
			}
		});
	}

	public TextArea getLog() {
		return log;
	}

}
