package l2_property_bind;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class RootController implements Initializable {

	@FXML private TextArea textArea1, textArea2;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 매개변수로 전달된 두 속성을 양방향으로 연결		
		Bindings.bindBidirectional(textArea1.textProperty(), textArea2.textProperty()); 
	}

}
