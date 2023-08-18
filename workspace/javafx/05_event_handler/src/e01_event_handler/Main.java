package e01_event_handler;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			HBox root = new HBox();
			root.setAlignment(Pos.BOTTOM_CENTER);
			root.setSpacing(10.0);
			root.setPadding(new Insets(15));
			
			Button btn1 = new Button("버튼1");
			// 특정 노드에 접근하기 위한 구별 값
			btn1.setId("btn1");
			btn1.setPrefSize(200, 100);
			
			ButtonActionEventHandler handler = new ButtonActionEventHandler();
			btn1.setOnAction(handler);
			
			Button btn2 = new Button("버튼2");
			// 1번처럼 말고 간단하게 익명 구현객체만 정의를 해도 된다.
			EventHandler<ActionEvent> handler2 = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("버튼2 click!!!!");
				}
			};
			btn2.setOnAction(handler2);
			
			// 해당 구현객체를 setOnAction에다가 바로 정의해도 됨.
			Button btn3 = new Button("버튼3");
			btn3.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("버튼3 click!!!#$#$@#$");
				}
			});

			/* 이렇게 추가 해줄 수도 있지만 ObservableList<Node>를 통해서 추가해줄수도 있다.
			root.getChildren().add(btn1);
			root.getChildren().add(btn2);
			root.getChildren().add(btn3);
			*/
			ObservableList<Node> list = root.getChildren(); // Node : 모~~~든 요소 최상위 요소?
			list.addAll(btn1, btn2, btn3);
			
			
			Scene scene = new Scene(root);
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
