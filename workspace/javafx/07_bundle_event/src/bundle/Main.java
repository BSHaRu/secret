package bundle;
	
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		// Locale : 각 나라 지역의 [언어][나라]등의 공통된 정보를 담고 있는 class
		// Map 형태로 구현된 데이터의 묶음을 bundle이라고 한다.
		for(Locale locale : Locale.getAvailableLocales()) {
			System.out.printf("Display Language : %s , ", locale.getDisplayLanguage());
			System.out.printf("Language code : %s , ", locale.getLanguage());
			System.out.printf("Display Country : %s , ", locale.getDisplayCountry());
			System.out.printf("Country code : %s, code %s \n", locale.getCountry(), locale.toString());
		}
		
		System.out.println("=====================================================================================================================");
		Locale locale = Locale.getDefault();
		System.out.println(locale);
		
//		locale = new Locale("ja","JP");
		
		ResourceBundle bundle = ResourceBundle.getBundle("prop.s",locale); // prop파일 중 접두어s가 붙은 파일 불러옴
		// ↑ "prop.s" +"_"ko_KR.properties 파일을 bundle에 저장
		System.out.println(bundle.keySet());
		
		try {
			HBox root = (HBox)FXMLLoader.load(getClass().getResource("Bundle.fxml"), bundle);
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
