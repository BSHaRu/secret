package omok;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppMain extends Application {
	
	Canvas canvas;
	GraphicsContext gc;
	
	// 돌이 놓이는 위치를 저장할 이차원 배열
	int[][] map = null; // 0은 빈자리
	
	int doll_state = 1; // 흑 1 | 백 2
	
	Stage primaryStage; // 여기에 필드로 선언해야 게임중에 팝업창이나, 승패여부 후 팝업창을 띄워 줄 수 있음
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Omok.fxml"));
			canvas = (Canvas)root.lookup("#canvas");
			gc = canvas.getGraphicsContext2D();
			this.primaryStage = primaryStage; // 그렇기 때문에 여기엔 해당 스테이지라고 표시 해주는거임
			
			// 바둑판 초기화
			initCanvas();
			
			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 승패 결정 후 게임 초기화
	private void initCanvas() {
		
		doll_state = 1;
		map = new int[19][19]; // 바둑판은 가로 19 | 세로 19줄
		
		// 승패 결정 후 canvas를 초기화 할려면 이코드가 반드시 필요함
		// 없어도 게임 플레이는 지장없는데 canvas가 좀 더러움
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		// 바둑판 가로줄 긋기
		for(int i=30; i<canvas.getWidth(); i+=30) { // 바둑판 맨 좌측 꼭지점(시작점) 좌표를 30부터 하는거임.
			gc.strokeLine(30, i, canvas.getWidth()-30, i);	// 바둑판 사각형 크기가 30
		}
		
		// 바둑판 세로줄 긋기
		for(int i=30; i<canvas.getHeight(); i+=30) {
			gc.strokeLine(i, 30, i, canvas.getHeight()-30);
		}
		
		// 선 색
		gc.setStroke(new Color(0,0,0,1));
		
		canvas.setOnMouseClicked(e -> {
			// canvas에서 마우스 클릭 할 때 x,y 좌표
			double x = e.getX();
			double y = e.getY();
			int offsetX = (int)(x+15)/30;	// 시작점이 30이고, 사각형 크기가 30이라서 사각형 크기 절반의 좌표 어디에 누르면 
			int offsetY = (int)(y+15)/30;	// 해당 꼭지점에 돌을 둘 수 있게 만듬
			System.out.println(x+":"+y);	// -> 30을 나눠주는건 그래야 시작점이 index 0번이 될 수 있어서
			System.out.println(offsetX+" : "+offsetY);
			draw(offsetX, offsetY);
		});
	}
	
	// 바둑판에 흑백 돌 그리기
	private void draw(int x, int y) {
		
		int drawX = (x*30)-15;
		int drawY = (y*30)-15;
		
		// 0이면 빈자리 | 흑색이면 1 | 백색이면 2
		if(map[--y][--x] != 0) {
			System.out.println("이미 돌이 있는 자리입니다.");
			return;
		}
		
		if(doll_state == 1) {
			System.out.println("검은돌");
			gc.setFill(Color.BLACK);
			map[y][x] = 1;
		}else {
			System.out.println("흰돌");
			gc.setFill(Color.WHITE);
			map[y][x] = 2;
		}
		gc.strokeOval(drawX, drawY, 28, 28);
		gc.fillOval(drawX, drawY, 28, 28); // 사각형 크기가 30이니깐 양쪽 외각선 1씩빼서 28씩 지정해주는거임
		
		victory(x,y);
		doll_state = (doll_state == 1) ? 2 : 1; // 검은돌이면 흰돌 | 흰돌이면 검은돌
	
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.printf("[%d]",map[i][j]);
			}
			System.out.println();
		}
	}
	
	// 승리 확인
	private void victory(int x, int y) {
		// cnt1 : 가로 | cnt2 : 세로 | cnt3 : \ 방향 대각선 | cnt4 : / 방향 대각선
		// 개수를 확인하고 5개면 승리 할 수 있는 알림창 호출
		int cnt1 = 1, cnt2 = 1, cnt3 = 1, cnt4 = 1; 
		
		// 배열에서 열(가로 뒤) 동일한 색의 돌 개수 추가
		for(int i=1; i<5; i++) {
			if(x+i < 19 && map[y][x+i] == doll_state) {
				cnt1++;
			}else {
				break; 
			} // if end
		} // for end
		
		// 배열에서 열(가로 앞) 개수 세기
		for(int i=1; i<5; i++) {
			if(x-1 >=0 && map[y][x-i] == doll_state) {
				cnt1++;
			}else {
				break;
			}
		} // for end
		
		// 배열에서 행(세로 아래) 개수 세기
		for(int i=1; i<5; i++) {
			if(y + i < 19 && map[y+i][x] == doll_state) {
				cnt2++;
			}else {
				break;
			}
		} // for end
		
		// 배열에서 행(세로 위) 개수 세기
		for(int i=1; i<5; i++) {
			if(y - i >= 0 && map[y-i][x] == doll_state ) {
				cnt2++;
			}else {
				break;
			}
		} // for end
		
		// 왼쪽 대각선 위 개수 세기
		for(int i=1; i<5; i++) {
			if(x - i >= 0 && y- 1 >= 0 && map[y-i][x-i] == doll_state ) {
				cnt3++;
			}else {
				break;
			}
		} // for end
		
		// 오른쪽 대각선 아래 개수 세기
		for(int i=1; i<5; i++) {
			if(x + i < 19 && y +i < 19 && map[y+i][x+i] == doll_state) {
				cnt3++;
			} else {
				break;
			}
		}
		
		// 오른쪽 대각선 위 개수 세기
		for(int i=1; i<5; i++) {
			if(x+i < 19 && y-i >=0 && map[y-i][x+i] == doll_state) {
				cnt4++;
			}else {
				break;
			}
		} // for end
		
		// 왼쪽 대각선 아래 개수 세기
		for(int i=1; i<5; i++) {
			if(x-i >= 0 && y+i < 19 && map[y+i][x-i] == doll_state) {
				cnt4++;
			}else {
				break;
			}
		} // for end
		
		// 게임 승리 알림창 호출
		if(cnt1 >= 5 || cnt2 >=5 || cnt3 >=5 || cnt4 >= 5) {
			String text = doll_state == 1 ? "흑" : "백";
			System.out.println(text+"승리");
			showDialog(text);
		}
		
	}
	
	// 알림창
	private void showDialog(String text) {
		Stage stage = new Stage(StageStyle.UNDECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		
		AnchorPane a = new AnchorPane();
		a.setPrefSize(200, 100);
		Label label = new Label(text+"승리!");
		label.setStyle("-fx-font-size:35");
		label.setLayoutX(10);
		label.setLayoutY(25);
		
		Button btn = new Button("확인");
		btn.setLayoutX(140);
		btn.setLayoutY(40);
		
		btn.setOnAction(e ->{
			stage.close();
			initCanvas(); // 해당 btn을 누르면 canvas 초기화
		});
		
		a.getChildren().addAll(label,btn);
		Scene scene = new Scene(a);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
