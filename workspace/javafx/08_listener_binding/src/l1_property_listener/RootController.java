package l1_property_listener;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RootController implements Initializable{

	@FXML private ImageView img;
	@FXML private Slider slider;
	@FXML private ToggleButton toggle;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(slider.valueProperty());
		double value = slider.valueProperty().doubleValue();
		setImageWidth(value);
		
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println(observable);
				System.out.println("변경되기 전 값 : " + oldValue);
				System.out.println("변경된 값 : " + newValue);
			}
		});
		
		// 이 코드를 작성함으로써 slider를 움직일때마다 이미지 크기가 변경됨
		slider.valueProperty().addListener((target,oldValue,newValue)->{
			setImageWidth(newValue.doubleValue());
		});
		
		// toggle버튼을 누르면 이미지 변경
		toggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				System.out.println(newValue);
				if(!newValue) {
					String path = getClass().getResource("./iu/iu.png").toString();
					
					Image image = new Image(path);
					img.setImage(image); 
				}else {
					img.setImage(new Image(getClass().getResource("./iu/iu2.jpg").toString()));
				}
			} // Override end
		});
	}
	
	// 이미지 크기 변경을 하기위해서 따로 빼둠
	public void setImageWidth(double value) {
		double width = ((double)350/100) * value; // width값을 350으로 지정해놨기때문에 최대값을 지정하는거임.
		img.setFitWidth(width == 0 ? 1 : width);
	}
	
}
