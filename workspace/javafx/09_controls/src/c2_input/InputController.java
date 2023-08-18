package c2_input;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class InputController implements Initializable {
	@FXML private TextField txtTitle;
	@FXML private PasswordField txtPass;
	@FXML private ColorPicker colorPicker;
	@FXML private ComboBox<String> comboPublic; // 기본 정의가 ComboBox<T>라서 타입 지정해줘야됨.
	@FXML private DatePicker datePicker;
	@FXML private TextArea txtContent;
	@FXML private Button btnReg, btnCancel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnReg.setOnAction(e->{
			String titleText = txtTitle.getText(); // TextField에 작성된 문자열
			System.out.println("제목 : " +titleText);
			String pass= txtPass.getText();
			System.out.println("pass : " + pass);
			
			// ComboBox에 선택된 값을 반환 (지정된 제네릭타입으로 반환)
			// -> ComboBox<String>로 지정했기때문에 문자열로 반환
			String comboData = comboPublic.getValue(); 
			System.out.println("combobox : " + comboData);
			
			// Color : 색상에 대한 정보를 저장하는 객체(RGBA)
			Color color = colorPicker.getValue();
			System.out.println("color : " + color);
			System.out.println("RED : " + color.getRed());
			System.out.println("GREEN : " + color.getGreen());
			System.out.println("BLUE : " + color.getBlue());
			System.out.println("Alpha : " + color.getBrightness());
			
			// LocalData : 날짜 정보를 yyyy-MM-dd 형태로 제공
			LocalDate date = datePicker.getValue();
			System.out.println(date);
			if(date != null) {
				System.out.println("연도 : " + date.getYear());
				System.out.println("월 : " + date.getMonth()); // 영어로 달을 보여줌
				System.out.println("월 : " + date.getMonthValue()); // 숫자로 달을 보여줌
				System.out.println("일 : " + date.getDayOfMonth());
			}
			
			String content = txtContent.getText();
			System.out.println("content : " + content);
		}); // btnReg(등록) action event end
		
		btnCancel.setOnAction(e->{
			// 기존 값을 새로운 텍스트로 대체
			txtTitle.setText(null);
			txtPass.setText("");
			txtContent.clear();
			
			// ComboBox 선택 목록
			ObservableList<String> oldList = comboPublic.getItems();
			ObservableList<String> newList 
				= FXCollections.observableArrayList("밥밥밥","줘줘줘"); // <FXCollections fx:factory="observableArrayList">같은말
			oldList.add("비밀글");
			oldList.remove("비공개"); // 취소버튼을 누르면 해당내용 사라짐
			comboPublic.setItems(newList); // 취소버튼 누르면 기존내용 사라지고, 해당내용이 새로 추가됨
			
			colorPicker.setValue(new Color(1,1,1,1)); // 흰색으로 초기화 시킴
			datePicker.setValue(null); 	// 초기값을 따로 지정안해줬기때문에 그냥 null로 초기화.
		}); // btnCancel(취소) action event end
		
		txtTitle.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println(event.getCode());
				if(event.getCode() == KeyCode.ENTER) {
					// requestFocus() : 포커스를 강제로 이동 
					txtPass.requestFocus(); 
					// fire() : event 강제 실행
//					btnCancel.fire();
				} // if end
			}
		}); // KeyPressed end
		
		txtPass.textProperty().addListener((target,oldValue,newValue)->{
			System.out.println(newValue);
			// appendText() : 기존값에 이어서 텍스트 추가
			txtContent.appendText(newValue + "\n"); // + "\n"을 넣음으로써 줄바꿈도 같이됨
		}); // txtPass end
		
	}

}
