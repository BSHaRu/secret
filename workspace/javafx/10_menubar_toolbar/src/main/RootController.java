package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class RootController implements Initializable {

	@FXML private TextArea textArea;
	@FXML private ComboBox<String> comboBox;
	
	private Stage primary;
	
	public void setStage(Stage stage) {
		this.primary = stage;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBox.getSelectionModel().selectedIndexProperty().addListener((target, oldValue, newValue)->{
			int index = newValue.intValue();
			System.out.println("select comboBox : " + index);
			switch(index) {
				case 0 : 
		    		// directory chooser (폴더 선택 상자) -> 확장자 지정안해줘도 됨
					DirectoryChooser chooser = new DirectoryChooser();
					File selectedDir = chooser.showDialog(primary);
					if(selectedDir != null) {
						for(File f : selectedDir.listFiles()) {
							textArea.appendText(f.getName() + "\n");
						} // if > for end
					} // if end
		    		break;
		    	case 1 : 
		    		// popUp window
		    		handlePopup();
		    		break;
		    	case 2 : 
		    		// custom Window
		    		handleCustom();
		    		break;
		    	case 3 : 
		    		// custom dialog
		    		break;
	    	} // switch end
		});
	}

	// case 1
	private void handlePopup() {
		System.out.println("POPUP");
		Popup popUp = new Popup();
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("Popup.fxml"));
		} catch (IOException e) {}
		ImageView imageView = (ImageView)parent.lookup("#imgMessage"); // id 는 #
		
		String imagePath = "../images/dialog-info.png";
		URL url = getClass().getResource(imagePath); 
		Image image = new Image(url.toString());
		imageView.setImage(image);
		
		imageView.setOnMouseClicked(event -> {
			System.out.println("image mouse click");
			popUp.hide();
		});
		
		Label lblMessage = (Label)parent.lookup(".lblMessage");		// class는 . -> css할때 했던거임
		lblMessage.setText("메세지 알림");
		
		popUp.getContent().add(parent);
		popUp.setAutoHide(true);
		popUp.show(primary);
	}

	// case 2
	private void handleCustom() {
		// 새로운 무대 생성
		// DECORATED 일반적인 윈도우 스타일, 흰배경, 제목줄에 장식(아이콘, 타이틀, 축소, 복원, 닫기)
//		Stage stage = new Stage(StageStyle.DECORATED);
//		// UNDECORATED : 흰배경에 제목줄x => 닫을라면 Alt+F4해야됨 
//		stage = new Stage(StageStyle.UNDECORATED);
//		// UTILITY : 흰배경, 제목줄에 타이틀, 종료버튼만 존재
//		stage = new Stage(StageStyle.UTILITY);
//		// TRANSPARENT : 투명 배경에 제목줄x
//		stage = new Stage(StageStyle.TRANSPARENT);
//		// DECORATED 상태랑 동일함. => 기본값으로 되어있음
//		stage = new Stage();
		final Stage stage = new Stage(); // final로 지정해줘야 서로다른 스레드에서 발생하는 event가 
										// 발생할 경우 해당값이 변경되면 안되니깐 final로 지정하는거임
		
		// initModality : 창의 소유권 지정
		// WINDOW_MODAL(기본값) : 소유권자가 없으면 자유롭게 창 이동 가능 
		// APPLICATION_MODAL : 소유자가 없더라도 무조건 해당창이 소유권을 가짐
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primary); // -> 새로운 창이 뜨면 그 창의 작업이 종료되어야 다른 창을 움직일 수 있음
		
		stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.setTitle("입력 창");
		Parent parent = null;
		
		try {
			parent = FXMLLoader.load(getClass().getResource("Custom.fxml"));
		} catch (IOException e) {}
		
		TextField textField = (TextField) parent.lookup("#textTitle");
		TextArea textArea = (TextArea) parent.lookup("#textContent");
		Button btnOk = (Button) parent.lookup("#btnOk");
		Button btnCancel = (Button) parent.lookup("#btnCancel");
		
		btnOk.setOnAction(event -> {
			String title = textField.getText();
			String content = textArea.getText();
			stage.close();
			RootController.this.textArea.appendText(title + "\n");
			RootController.this.textArea.appendText(content + "\n");
			// comboBox 선택 초기화
			comboBox.getSelectionModel().clearSelection();
		});
		
		btnCancel.setOnAction(event -> {
			stage.close();
		});
		
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setResizable(false);
		// 창닫기 이벤트(닫기 버튼 or Alt+F4를 눌렀을때 발생하는 이벤트)
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.out.println("Close 이벤트");
				// 닫기 이벤트 무시
				event.consume(); // -> 닫기버튼으로 해당 창이 종료가 안됨 => 등록버튼 눌렀을 때만 닫기됨
			}
		}); 
		stage.show();
	}

	public void handleNew(ActionEvent e) throws IOException {
		System.out.println("new");
		textArea.appendText("New \n");
		
		Runtime rt = Runtime.getRuntime();
		rt.exec("notepad"); // 실행창에서 "notepad"를 입력하면 메모장뜨는 그거 말하는거임
	}						//-> 즉, 지금 new 누르면 메모장이 뜸
	
	public void handleOpen(ActionEvent e) throws IOException {
		System.out.println("Opne");
		textArea.appendText("Open \n");
		
		// FileChooser : 파일 선택 창
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll( 		// getExtensionFilters() : 확장자 정해주는거임
			new ExtensionFilter("Text files","*.txt")	// ExtensionFilter(어떤 파일을 불러올껀지 대충 이름적음, 확장자)
		);												// -> txt 파일만 보이게 설정함
		File selectedFile = fileChooser.showOpenDialog(primary);
		if(selectedFile != null) {
			textArea.appendText(selectedFile.getPath() + "\n");
			FileReader reader = new FileReader(selectedFile);
			BufferedReader bReader = new BufferedReader(reader);
			
			String message = null;
			while((message = bReader.readLine()) != null) {
				textArea.appendText(message + "\n");
			}
			bReader.close();
			reader.close();
		}
	}
	
	public void handleSave(ActionEvent e) throws Exception {
		System.out.println("Save");
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(
			new ExtensionFilter("text files", "*.txt")
		);
		
		File selectedFile = fileChooser.showSaveDialog(primary);
		System.out.println(selectedFile.getPath());
		if(selectedFile != null) {
			FileWriter writer = new FileWriter(selectedFile);
			BufferedWriter bWriter = new BufferedWriter(writer);
			String message = textArea.getText();
			bWriter.write(message);
			bWriter.flush();
			bWriter.close();
			writer.close();
		}
	}
	
	public void handleClose(ActionEvent e) {
		System.out.println("Close");
		
		primary.close(); // 무대 종료
	}
	
}
