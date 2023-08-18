package c3_view_controls;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewController implements Initializable {
	@FXML private ListView<String> listView;
	@FXML private TableView<PhoneVO> tableView;
	@FXML private ImageView imageView;
	@FXML private TextField txtName;
	@FXML private Button btnUpdate;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList(
			"갤럭시S1","갤럭시S2","갤럭시S3","갤럭시S4","갤럭시S5","갤럭시S6","갤럭시S7"
		);
		listView.setItems(list);
		
		// listView 아이템이 선택되었을때 동일한 tableview 항목도 선택 되게 함.
		listView.getSelectionModel().selectedIndexProperty().addListener((target,oldValue,newValue)->{
			System.out.println(newValue);
			int index = newValue.intValue();	// newValue는 number 타입이라서 int로 만들어주고 대입시켜줘야됨.
			System.out.println(list.get(index));
			tableView.getSelectionModel().select(index);
			tableView.scrollTo(index);
		});
		
		// tableView
		ObservableList<PhoneVO> phoneList = FXCollections.observableArrayList();
		for(int i = 1; i<=7; i++) {
			phoneList.add(new PhoneVO("갤럭시S"+i, "phone0"+i+".png"));
		}
		System.out.println(phoneList);
		
		TableColumn<PhoneVO, ?> tColumnName = tableView.getColumns().get(0);
		tColumnName.setCellValueFactory(
			new PropertyValueFactory<>("name") // 여기서 name은 필드 이름을 말함
		);
		
		TableColumn<PhoneVO, ?> tColumnPath = tableView.getColumns().get(1);
		tColumnPath.setCellValueFactory(
			new PropertyValueFactory<>("path")	
		);
		
		tableView.setItems(phoneList);
		
		// tableView에 항목 변경 감지 Listener 추가
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PhoneVO>() {
			@Override
			public void changed(ObservableValue<? extends PhoneVO> observable, PhoneVO oldValue, PhoneVO newValue) {
				if(newValue != null) {
					System.out.println(newValue);
					String path = "../images/" + newValue.getPath();
					URL url = getClass().getResource(path);
					imageView.setImage(new Image(url.toString()));
					
					// text 정보 수정을 위해서 TextField 값을 선택된 항목으로 변경
					txtName.setText(newValue.getName()); 
				}// if end
			}
		});
		
		tableView.getSelectionModel().selectedIndexProperty().addListener((target, oldValue, newValue)->{
			// Platform : ui 스레드 담당.
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					int index = newValue.intValue();
					listView.getSelectionModel().select(index);
					listView.scrollTo(index);
				}
			}); // runLater end
		});  // tableView index property end
		
		btnUpdate.setOnAction(e->{
			String name = txtName.getText();
			PhoneVO phone = tableView.getSelectionModel().getSelectedItem();
			System.out.println(phone);
			
			int index = tableView.getSelectionModel().getSelectedIndex();
			list.set(index, name); // 해당 index번호에 있는거 대체
			
			if(phone != null) phone.setName(name);
			System.out.println(phoneList);
			tableView.refresh(); // refresh() : 정보가 변경되면 새로고침을 해서 ui에 바로 적용시킴
			listView.refresh();
		});
	}

}
