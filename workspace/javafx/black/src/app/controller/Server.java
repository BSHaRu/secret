package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import server.ServerHelper;

public class Server implements Initializable {

	@FXML
	private Button exit;
	
	@FXML
	private TextArea log;
	
	ServerHelper sh;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			exit.setOnAction(e -> {
				
//				if(exit.getText().equals("Stop")) {
//					sh.stopServer();
//					log.setText(log.getText().concat("[ 서버 중지 ]"));
//				}else {
//					sh.startServer();
//					log.setText(log.getText().concat("[ 서버 시작 ]"));
//				} // if end
			});	
	}

	public TextArea getLog() {
		return log;
	}
	
}
