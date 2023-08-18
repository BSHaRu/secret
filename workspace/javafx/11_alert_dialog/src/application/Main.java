package application;
	
import java.util.Optional;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		VBox vBox = new VBox();
		vBox.setPrefWidth(200);
		vBox.setAlignment(Pos.CENTER); // 자식요소 중앙정렬
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(10));
		
		ObservableList<Node> child = vBox.getChildren();
		child.add(new Button("알림"));
		child.add(new Button("경고"));
		child.add(new Button("오류"));
		child.add(new Button("확인"));
		child.add(new Button("입력"));
		child.add(new Button("Custom"));
		
		for(Node n : child) {
			Button b = (Button)n;
			b.setMaxWidth(Double.MAX_VALUE);
			b.setOnAction(event -> {
				handleAlert(b.getText());
			});
		}
		
		primaryStage.setScene(new Scene(vBox));
		primaryStage.show();
	}
	
	public void handleAlert(String text) {
		System.out.println(text);
		switch(text){
			case "알림" :
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle(text);
				alert.setHeaderText("알림창입니다. \n헤더 정보입니다.");
				alert.setContentText("이곳은 컨텐츠 정보입니다. \n내용을 확인하세요.");
				alert.show();
				break;
			case "경고" :
				alert = new Alert(AlertType.WARNING);
				alert.setTitle(text);
				alert.setHeaderText("Header Text");
				alert.setContentText("Content Text");
				alert.show();
				break;
			case "오류" :
				alert = new Alert(AlertType.ERROR);
				alert.setTitle(text);
				alert.setHeaderText(null);
				alert.setContentText("Content Text");
				alert.show();
				break;
			case "확인" :
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("결제 확인");
				alert.setHeaderText("결제 정보 입니다.");
				alert.setContentText("확인 시 3,000원 결제 됩니다.");
				Optional<ButtonType> result = alert.showAndWait();
				System.out.println(result.get());
				if(result.get() == ButtonType.OK) {
					System.out.println("OK 버튼 선택");
				}else if(result.get() == ButtonType.CANCEL) {
					// 취소 or 닫기 or alt+F4를 눌렀을 경우.
					System.out.println("취소 버튼 선택");
				}
				break;
			case "입력" :
				TextInputDialog dialog = new TextInputDialog("홍길동"); // 값을 미리 넣어두고 싶으면 ()에 미리 넣으면됨.
				dialog.setTitle(text);
				dialog.setHeaderText("Header Text");
				dialog.setContentText("이름을 입력해주세요");
				Optional<String> res = dialog.showAndWait(); // 입력 받는 값이 text라서 String으로 지정해줌.
				if(res.isPresent() && !res.get().trim().equals("")) { // 확인버튼을 눌러서 종료를 했고, 공백이 없을 경우.
					res.ifPresent(new Consumer<String>() {
						@Override
						public void accept(String t) {
							System.out.println("작성하신 이름은 : "+t+"입니다.");
						}
					});
				} // if end
				break;
			case "Custom" : 
				alert = new Alert(
						AlertType.INFORMATION,"이곳은 content 정보 입니다. \n내용을 확인하세요",
						ButtonType.OK,
						ButtonType.CANCEL,
						ButtonType.NEXT
				);
				alert.setTitle("알림!");
				alert.setHeaderText(null);
				Optional<ButtonType> result1 = alert.showAndWait();
				if(result1.get() == ButtonType.NEXT) {
					System.out.println("다음");
				}else if(result1.get() == ButtonType.OK) {
					System.out.println("확인");
				}else {
					System.out.println("취소");
				}
				break;
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
