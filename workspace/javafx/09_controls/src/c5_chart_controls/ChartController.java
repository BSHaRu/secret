package c5_chart_controls;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class ChartController implements Initializable {
	
	@FXML private PieChart pieChart;
	@FXML private BarChart<String,Integer> barChart; // <x좌표,y좌표>
	@FXML private AreaChart<String,Integer> areaChart;
	@FXML private BubbleChart<Integer,Integer> bubbleChart;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// list = PieChart.Data 객체를 저장
		ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
		list.add(new PieChart.Data("AWT", 5)); 		// PieChart.Data(이름, 값) 
		list.add(new PieChart.Data("Swing", 25));	// -> 값은 꼭 100을 안맞춰도됨
		list.add(new PieChart.Data("SWT", 30));		// => 해당 값은 총합에 따라서 알아서 조절해줌
		list.add(new PieChart.Data("JavaFX", 60));
		pieChart.setData(list);
		
		barChart.setTitle("평균 키");
		XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
		series1.setName("남성");
		
		XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
		series2.setName("여성");
		
		ObservableList<XYChart.Data<String, Integer>> listBar 
			= FXCollections.observableArrayList();
		listBar.add(new XYChart.Data<>("2019",168));
		listBar.add(new XYChart.Data<>("2020",170));
		listBar.add(new XYChart.Data<>("2021",172));
		listBar.add(new XYChart.Data<>("2022",174));
		series1.setData(listBar);
		
		listBar = FXCollections.observableArrayList();
		listBar.add(new XYChart.Data<>("2019",155));
		listBar.add(new XYChart.Data<>("2020",158));
		listBar.add(new XYChart.Data<>("2021",160));
		listBar.add(new XYChart.Data<>("2022",162));
		series2.setData(listBar);
		
		barChart.getData().add(series1);
		barChart.getData().add(series2);
		
		// AreaChart 
		// 지역별 평균 온도
		areaChart.setTitle("평균 온도");
		XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
		series3.setName("서울");
		series3.setData(FXCollections.observableArrayList(
			new XYChart.Data<String, Integer>("2016",26),
			new XYChart.Data<String, Integer>("2017",27),
			new XYChart.Data<String, Integer>("2018",24),
			new XYChart.Data<String, Integer>("2019",28),
			new XYChart.Data<String, Integer>("2020",27)
		)); // 노란줄은 Data에 제네릭 선언 안했는데, 안에선 Data에 제네릭 넣어서 뜨는거 -> 신경안써도 된다고함
		areaChart.getData().add(series3);
		
		XYChart.Series<String, Integer> series4 = new XYChart.Series<>();
		series4.setName("부산");
		series4.setData(FXCollections.observableArrayList(
			new XYChart.Data<String, Integer>("2016",27),
			new XYChart.Data<String, Integer>("2017",28),
			new XYChart.Data<String, Integer>("2018",26),
			new XYChart.Data<String, Integer>("2019",28),
			new XYChart.Data<String, Integer>("2020",29)
		));
		areaChart.getData().add(series4);
		
		// BubbleChart
		// 체류 시간별 상품 구매 수와 판매 금액
		XYChart.Series<Integer, Integer> seriesA = new XYChart.Series<>();
		seriesA.setName("40대");
		
		seriesA.getData().add(new XYChart.Data<>(5,0,0)); // (x, y, scale) -> (체류시간, 금액, 구매수)
		seriesA.getData().add(new XYChart.Data<>(10,1,5));
		seriesA.getData().add(new XYChart.Data<>(20,3,7));
		seriesA.getData().add(new XYChart.Data<>(30,5,1));		
		bubbleChart.getData().add(seriesA);
	}

}
