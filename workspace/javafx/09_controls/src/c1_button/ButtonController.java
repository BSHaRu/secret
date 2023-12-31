package c1_button;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ButtonController implements Initializable {
	
	@FXML private BorderPane root; 
	@FXML private CheckBox chk1, chk2;
	@FXML private ToggleGroup group;
	@FXML private Button btnExit;
	@FXML private Hyperlink hyperLink;
	@FXML private ImageView chkImg, radioImg;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chk1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				handlerChkAction(event);
			}
		});
		chk2.setOnAction(event->handlerChkAction(event));
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				RadioButton value = (RadioButton)newValue;
				String text = value.getText();
				System.out.println(text);
				System.out.println("------------------------------------------------");
				
				// text랑 img랑 이름이 같기때문에 아래처럼 지정해주면 해당 이미지 파일을 불러 올 수 있는거임.
				text = "../images/"+text+".png"; 
				String path = getClass().getResource(text).toString();
				System.out.println(path);
				System.out.println("------------------------------------------------");
				
				Image image = new Image(path);
				radioImg.setImage(image);
			}
		}); // ToggleGroup end
		System.out.println("=======================================================");
		
		btnExit.setOnAction(event->{
			// UI 스레드 강제 종료
			Platform.exit();
		});
		
		hyperLink.setOnAction(e->{
			System.out.println("hyper link!");
			String link = (String)hyperLink.getUserData(); // 이렇게 반환하면 모~~든 Object기때문에 반환타입 지정해줘야됨.
			// 도메인 정보를 가지고 웹 화면을 애플리케이션에 보여주는 view
			WebView webView = new WebView();
			WebEngine we = webView.getEngine(); 
			we.load(link);
//			root.setTop(webView);	// 해당 실행창에서 맨위에 그 링크 화면을 보여주기
			
			// WebView를 보여주기 위한 무대 생성 -> 새로운 창을 열어서 해당 링크 보여줌(새창)
			Stage stage = new Stage();
			BorderPane pane = new BorderPane();
			pane.setCenter(webView);
			stage.setScene(new Scene(pane));
			
			stage.setWidth(1074);
			stage.setHeight(700);
			stage.show();
		});
	}
	
	public void handlerChkAction(ActionEvent e) {
		boolean isCheck1 = chk1.isSelected();
		boolean isCheck2 = chk2.isSelected();
		System.out.println("chk1 : " + isCheck1);
		System.out.println("chk2 : " + isCheck2);
		
		String resource ="";
		if(isCheck1 && isCheck2) {
			resource = "../images/geek-glasses-hair.gif";
		} else if(isCheck1) {
			resource = "../images/geek-glasses.gif";
		} else if(isCheck2) {
			resource = "../images/geek-hair.gif";
		} else {
			resource = "../images/geek.gif";
		}// if end

		chkImg.setImage(
			new Image(getClass().getResource(resource).toString())
		);
		
		
	} // handlerChkAction end
	
}
