package c01_selector;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("Root.fxml"));
			Scene scene = new Scene(root);
			/* 이렇게 추가해도 하나씩 추가해도 되지만, 아래처럼 한꺼번에 추가 할 수도 있다.
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // toExternalForm()랑 
			scene.getStylesheets().add(getClass().getResource("state.css").toString()); 			// toString() 반환하는값 동일
			*/
			/* fxml 파일에서 css를 추가해 줄 수도 있다.
			scene.getStylesheets().addAll(
				getClass().getResource("application.css").toString(),
				getClass().getResource("state.css").toString()	
			);
			*/
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
