
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FXMLLayouyMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = FXMLLoader.load(
			getClass().getResource("Sub.fxml")
		);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
