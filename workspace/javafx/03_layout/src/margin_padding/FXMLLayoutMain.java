package margin_padding;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLLayoutMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			// Parent == Container의 최상위 객체
			Parent root = FXMLLoader.load(
				getClass().getResource("Root.fxml")
			);
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("여백 확인");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
