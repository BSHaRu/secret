package app.application;

import app.controller.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader serverLoader = new FXMLLoader(getClass().getResource("../page/server.fxml"));
			Parent root = serverLoader.load();
			primaryStage.getIcons().add(new Image("/app/image/blackjack.png"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("btn.css").toExternalForm());
			System.setProperty("prism.lcdtext", "false");
			Font.loadFont(getClass().getResource("/app/page/BMJUA.ttf").toString(),16);
			primaryStage.setTitle("Black Jack Server");
			primaryStage.show();
			
			Server serverController = serverLoader.getController();
			primaryStage.setOnCloseRequest((e)->{
				serverController.sh.stopServer();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
