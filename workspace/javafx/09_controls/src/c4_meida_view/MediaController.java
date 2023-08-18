package c4_meida_view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MediaController implements Initializable {
	@FXML private Label lblTime;
	@FXML private Button btnPlay, btnPause, btnStop;
	@FXML private ProgressBar progressBar;
	@FXML private ProgressIndicator progressIndicator;
	@FXML private Slider sliderVolume, sliderPlay;
	// media 재생할 공간
	@FXML private MediaView mediaView;
	
	// 파일의 끝까지 재생완료를 확인할 flag
	private boolean endOfMedia;
	// 재생해야할 resource 정보를 저장하는 객체
	private Media media;
	// MediaView를 통해 재생되는 resource를 제어하는 객체
	private MediaPlayer mediaPlayer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sliderVolume.setValue(50); 			// 0 ~ 100
		progressBar.setProgress(0.5);		// 0 ~ 1
		progressIndicator.setProgress(0.5); // 0 ~ 1
		
		media = new Media(
			getClass().getResource("../media/video.m4v").toString()	
		);
		init();
	}

	// 재생할 Resource를 넘겨받아 MediaPlay를 초기화하는 메소드
	public void init() {
		// mediaPlayer가 존재하면 초기화
		if(mediaPlayer != null) {
			// 재생중지
			mediaPlayer.stop();
			mediaPlayer = null;
		}
		
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		setProgress(0.0,"0/0 sec");
		setMediaPlayer();
		
		sliderVolume.valueProperty().addListener((target,oldValue,newValue)->{
			double volume = newValue.doubleValue();	
			// mediaPlayer의 volume 조절
			// method = 0 ~ 1.0
			volume = (volume / 100.0);
			System.out.println("volume : " + volume);
			mediaPlayer.setVolume(volume);
		});
		
		// 재생 위치 제어
		sliderPlay.valueProperty().addListener((tagert, oldValue, newVlaue)->{
			Duration totalDuration = mediaPlayer.getTotalDuration(); // 전체 재생시간 가져옴
			double value = sliderPlay.getValue() / 100.0; 	// 0 ~ 100의 값을 가지고 있어서 100으로 나눠준다는데?
			// 전체 재생시간을 1/1000단위로 반환
			double totalValue = totalDuration.toMillis();
			double now = totalValue * value;
			mediaPlayer.seek(new Duration(now));
			mediaPlayer.play();
		});
		
		
		// 재생, 일시정지, 종료 버튼 초기화
		btnPlay.setOnAction(e->{
			// 영상 재생
			if(endOfMedia) {
				mediaPlayer.stop();
			}
			endOfMedia = false;
			mediaPlayer.play();
			
			mediaPlayer.currentTimeProperty().addListener((target,oldValue,newValue)->{
				// 현재 재생중인 파일의 전체 재생시간을 초단위로 가져옴.
				double totalTime = mediaPlayer.getTotalDuration().toSeconds();
				// 현재 재생 위치 시간을 초단위로 가져옴
				double currentTime = mediaPlayer.getCurrentTime().toSeconds();
				String lblText = (int)currentTime + "/" + (int)totalTime + " sec"; // double을 int로 바꿔서 진행.
				double progress = currentTime / totalTime;
				setProgress(progress, lblText);
			});
		});
		
		btnPause.setOnAction(e->{
			// 일시 정지
			mediaPlayer.pause();
		});
		
		btnStop.setOnAction(e->{
			// 재생 종료
			mediaPlayer.stop();
		});
	}
	
	// MediaPlayer 초기화
	public void setMediaPlayer() {
		// 재생 준비가 완료 되었을 때
		mediaPlayer.setOnReady(new Runnable() { 
			@Override
			public void run() {
				btnPlay.setDisable(false);
				btnPause.setDisable(true);
				btnStop.setDisable(true);
			}
		}); // -> 재생버튼만 활성화, 일시정지와 종료는 비활성화 상태 만듬
		
		// 재생 중일 때
		mediaPlayer.setOnPlaying(()->{
			btnPlay.setDisable(true);
			btnPause.setDisable(false);
			btnStop.setDisable(false);
		}); // -> 재생버튼만 비활성화, 나머지는 활성화
		
		// 일시 정지일 때
		mediaPlayer.setOnPaused(()->{
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(false);
		}); // -> 일시정지 버튼만 비활성화, 나머지 활성화
		
		// 모든 media 재생이 완료가 되었을 때
		mediaPlayer.setOnEndOfMedia(()->{
			endOfMedia = true;
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
		});
		
		// 종료가 호출 되었을 때
		mediaPlayer.setOnStopped(()->{
			// mediaPlayer에 등록된 미디어의 재생 시작 시간을 가져옴
			Duration duration = mediaPlayer.getStartTime(); // Duration : 시간을 저장하는 친구
			mediaPlayer.seek(duration); // 재생 시간을 설정 하는 method
			
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
		});
		
	}
	
	// progress 초기화
	public void setProgress(double p, String lblText) {
		progressBar.setProgress(p);
		progressIndicator.setProgress(p);
		lblTime.setText(lblText);
		
	}
	
	// Media 교체 이벤트 처리 - 재생 파일 변경
	public void changeResource(ActionEvent e) {
		Button btn = (Button)e.getTarget();
		String text = btn.getText();
		String path = "";
		switch(text) {
			case "영상1" :
				path = "../media/video.m4v";
				break;
			case "영상2" :
				path = "../media/video.mp4";
				break;	
			case "음악1" :
				path = "../media/audio.mp3";
				break;
			case "음악2" :
				path = "../media/audio.wav";
				break;
		}
		URL url = getClass().getResource(path);
		media = new Media(url.toString());
		init();
	}
	
}
