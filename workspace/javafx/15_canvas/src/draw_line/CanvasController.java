package draw_line;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CanvasController implements Initializable {

	@FXML private Canvas canvas;
	@FXML private TextArea textArea;
	@FXML private ColorPicker pick;
	@FXML private Slider slider;
	@FXML private Button btnClear;
	
	// 그리기 도구
	GraphicsContext gc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK); // 선 색깔
		gc.setLineWidth(1); // 선 굵기
		slider.setMin(1);
		slider.setMax(100);
		textArea.setEditable(false); // == read only : 읽기 전용
		
		// canvas위에서 마우스가 움직일 때
		canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// x, y 좌표 얻기
				double x = event.getSceneX();
				double y = event.getSceneY();
				textArea.appendText("x 좌표 : " + x + "y 좌표 : " + y + "\n");
			}
		});
		
		// 마우스가 눌러졌을때
		canvas.setOnMousePressed(e-> {
			gc.beginPath();
			gc.lineTo(e.getX(), e.getY());
		});
		
		// 마우스가 드래그 되는 동안
		canvas.setOnMouseDragged(e->{
			double x = e.getX();
			double y = e.getY();
			textArea.appendText(x + ": " + y+ "\n");
			gc.lineTo(x, y);
			gc.stroke();
		});
		
		btnClear.setOnAction(e -> {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			
			pick.setValue(Color.WHITE);
			slider.setValue(1);
			gc.setLineWidth(1);
			gc.setStroke(Color.BLACK);
		});
		
		pick.valueProperty().addListener((target,oldValue,newValue)->{
			gc.setStroke(newValue);
		});
		
		slider.valueProperty().addListener((target, oldValue, newValue) ->{
			int value = newValue.intValue();
			double result = value / 10; // 선굵기가 100이되면 너무 굵으니깐 10으로 나눠주는거임
			gc.setLineWidth(result);
		});
	}

}
