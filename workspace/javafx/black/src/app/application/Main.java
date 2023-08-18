package app.application;

import app.controller.Root;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Root root = Root.getRoot();
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image("/app/image/blackjack.png"));
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("btn.css").toExternalForm());
			primaryStage.setTitle("Black Jack");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
