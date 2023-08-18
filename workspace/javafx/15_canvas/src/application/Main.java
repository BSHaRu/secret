package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
	
	// 도화지
	private Canvas canvas;
	
	// 그리기 도구
	private GraphicsContext gc;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Root.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			canvas = (Canvas)root.lookup("#canvas");
			// canvas에 그려줄 그리기 도구
			gc = canvas.getGraphicsContext2D();
			draw();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void draw() {
		// 채우기 색상
		gc.setFill(Color.RED);
		// 가로 위치, 세로 위치, 너비(폭), 높이
		gc.fillRect(100, 100, 100, 100);
		// 외각선 색 지정
		gc.setStroke(Color.BLUE);
		gc.strokeRect(150, 150, 100, 100);
		
		// 호를 그리는 도구
		gc.strokeOval(100, 100, 50, 50);
		
		// 선그리기 도구
		// (시작x, 시작y, 끝x, 끝y)
		gc.strokeLine(200, 200, 200, 250);
		
		// 여러 선을 이어서 그려주는 도구
		gc.beginPath(); // 선 그리기 시작
		gc.setStroke(new Color(0.5,0.5,0.5,1)); // Color(R,G,B,a) : 0~1의 값을 가짐
		gc.lineTo(10, 10); // x, y 좌표 말하는거임
		gc.lineTo(20, 30);
		gc.lineTo(50, 40);
		gc.lineTo(10, 10);
		gc.stroke();
		
		Image image = new Image(
			getClass().getResource("../img/iu.png").toString()
		);
//		gc.drawImage(image, 300, 300); // 좌표 지정
		gc.drawImage(image, 300, 300, 100, 100); // 크기까지 지정 
		gc.clearRect(0, 0, 100, 100);
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		Thread t = new Thread(()->{
			for (int i=0; i<canvas.getWidth(); i++) {
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.drawImage(image, i, i, 100, 100);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
