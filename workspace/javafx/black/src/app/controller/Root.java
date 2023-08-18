package app.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import client.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.ServerHelper;

public class Root extends Pane {
	public static Root root = new Root();
	private Socket client;
	private Pane screen;

	private Root() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../page/root.fxml"));
		try {
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			isClient();

			this.getChildren().add(screen);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (client != null)
					client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}// Root end
	
	// Root 현재 싱글턴 getRoot로 가져와서 사용
		public static Root getRoot() {
			return root;
		}
	
	// ip와 server가 동일한지 비교 후 소켓 생성 및 페이지 분리
	private void isClient() throws IOException {
		String ip = InetAddress.getLocalHost().getHostAddress();
		System.out.println(ip);
		if (ip.equals("192.168.1.104")) {
			System.out.println("client");
			
//			FXMLLoader clientLoader = new FXMLLoader(getClass().getResource("../page/start.fxml"));
//			screen = clientLoader.load();
//			Client cleintCont = clientLoader.getController();
//			primaryStage.setOnCloseRequest(e->{
//				cleintCont.stopClient();
//			});
			screen = FXMLLoader.load(getClass().getResource("../page/start.fxml"));
			Client clientConn = new Client();
			clientConn.startClient();

		} else {
			System.out.println("server");
			
			FXMLLoader serverLoader = new FXMLLoader(getClass().getResource("../page/server.fxml"));
			screen = serverLoader.load();
			Server serverCont = serverLoader.getController();
			TextArea log =  serverCont.getLog();
			
			ServerHelper sh = new ServerHelper();
			sh.setLog(log);
			sh.startServer();
		}
	}

	// 페이지 전환
	public void toStart() {
		try {
			screen = FXMLLoader.load(getClass().getResource("../page/start.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getChildren().remove(0);
		this.getChildren().add(screen);
	}

	public void toGame() {
		try {
			screen = FXMLLoader.load(getClass().getResource("../page/game.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getChildren().remove(0);
		this.getChildren().add(screen);
	}

	public void toEnd() {
		try {
			screen = FXMLLoader.load(getClass().getResource("../page/end.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getChildren().remove(0);
		this.getChildren().add(screen);
	}

	

}
