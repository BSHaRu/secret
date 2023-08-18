package chat_client;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Client.fxml"));
			BorderPane root = loader.load();
			// ?????
			ClientController controller = loader.getController();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Chat Client");
			primaryStage.setResizable(false);
			// 닫기, alt+f4가 눌러지면 client 종료 시켜줌
			primaryStage.setOnCloseRequest((evenet) ->{
				controller.stopClient();
			});
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
