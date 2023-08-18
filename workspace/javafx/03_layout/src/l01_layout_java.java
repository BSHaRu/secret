
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class l01_layout_java extends Application {

	@Override
	public void start(Stage primaryStage) {
		// 세로 - 수직으로 내부에 control을 포함하는 container
		VBox root = new VBox();
		root.prefHeight(150);	// VBox 높이 설정 // prefHeight() : ()은 px단위
		root.prefWidth(350);	// VBox 폭 설정
		
		Label label = new Label(); 		// Label Control 생성 // javafx로 import 시켜주기
		label.setText("Hello JavaFX"); 	// label text 설정
		label.setFont(new Font(50));	// font - 글꼴인 글자 크기 등을 설정 // 마찬가지로 javafx로 import 시켜주기
		
		// container에 포함된 자식 요소목록(List)
		root.getChildren().add(label);
		
		Button btn = new Button();		// Button Control 생성 
		btn.setText("확인");				// button의 text 설정
		
		// VBox의 자식요소에 btn 추가
		root.getChildren().add(btn);
		
		// VBox를 root container로 해서 장면을 생성
		Scene scene = new Scene(root); 
		
		// 무대 - window에 장면 추가
		primaryStage.setScene(scene);
		primaryStage.setTitle("AppMain");
		// 무대 - 창을 띄우기
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
