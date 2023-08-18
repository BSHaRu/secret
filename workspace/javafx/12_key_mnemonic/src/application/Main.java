package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("Root.fxml")
			); // 이렇게 하면 컨트롤러를 가져 올 수 있음 => 그래서 뭐??
			AnchorPane root = loader.load();
			MainController controller = loader.getController();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			controller.setPrimaryStage(primaryStage);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
