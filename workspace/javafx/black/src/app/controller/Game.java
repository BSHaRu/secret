package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import app.game.BlackJack;
import app.game.Card;
import client.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Game implements Initializable {
	@FXML
	private Button start, exit, hit, stay;
	@FXML
	private TextArea log;
	@FXML
	private TextField chat;
	@FXML
	private Label player1, player2;
	@FXML
	private ImageView playerImg1, playerImg2, gameTable;
	@FXML
	private HBox uCard, dCard;

	private BlackJack bj;
	private ArrayList<Card> dealerCard;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		disabledBtn();

		start.setOnAction(e -> {
			isRun();
		});

		exit.setOnAction(e -> {
			Root.getRoot().toEnd();
		});

		hit.setOnAction(e -> {
			bj.hit();
			insertUCard();
		});

		stay.setOnAction(e -> {
			bj.stay();
		});
	}

	
	private void isRun() {
		if (!uCard.getChildren().isEmpty()) {
			for (int i = 0; i < uCard.getChildren().size();) {
				uCard.getChildren().remove(i);
			}
		}
		if (!dCard.getChildren().isEmpty()) {
			for (int i = 0; i < dCard.getChildren().size();) {
				dCard.getChildren().remove(i);
			}
		}

		log.clear();
		log.appendText("게임을 시작하지\n");
		hit.setDisable(false);
		stay.setDisable(false);
		
		bj = new BlackJack(log, this);
		createStartCard();
		
		Client.send(Client.nickName);
//		sh.printText(Client.nickName+"님이 시작하였습니다.");
	}

	public void disabledBtn() {
		hit.setDisable(true);
		stay.setDisable(true);
	}

	private void createStartCard() {
		
		dealerCard = bj.dealerCard;
		String dDeck1 = dealerCard.get(0).getDeck();
		String dDeck2 = dealerCard.get(1).getDeck();
		dDeck1 = dDeck1.replaceAll(" ","");
		dDeck2 = dDeck2.replaceAll(" ","");
		
		ImageView dCard1;
		ImageView dCard2;
		String path = "../cardimg/cardBack.png"; // ex)DIAMOND1.png
		URL url = getClass().getResource(path);
		dCard1 = new ImageView(url.toString());
		dCard1.setPreserveRatio(true); // 가로세로 비율 유지
		dCard.getChildren().add(dCard1); // HBox에 자식요소로 추가
		
		path = "../cardimg/"+dDeck2+".png";
		url = getClass().getResource(path);
		dCard2 = new ImageView(url.toString());
		dCard2.setPreserveRatio(true);
		dCard.getChildren().add(dCard2); 
		
		
		
		ArrayList<Card> playerCard = bj.playerCard; // 값을 가져오기위한 인스턴스 생성
		String deck1 = playerCard.get(0).getDeck();// 플레이어 덱의 카드리스트 첫번째카드(index 0번)
		String deck2 = playerCard.get(1).getDeck();
		
		deck1 = deck1.replaceAll(" ","");
		deck2 = deck2.replaceAll(" ","");
		
		ImageView card1;
		ImageView card2;
		
		String userPath = "../cardimg/" +deck1 + ".png"; // ex)DIAMOND1.png
		URL userUrl = getClass().getResource(userPath);
		card1 = new ImageView(userUrl.toString());
		card1.setPreserveRatio(true); // 가로세로 비율 유지
		uCard.getChildren().add(card1); // HBox에 자식요소로 추가

		userPath = "../cardimg/" + deck2 + ".png";
		userUrl = getClass().getResource(userPath);
		card2 = new ImageView(userUrl.toString());
		card2.setPreserveRatio(true);
		uCard.getChildren().add(card2); 
	}
	public void CardOpen() {
		System.out.println(dealerCard.get(0));
		ImageView card11 = (ImageView)dCard.getChildren().get(0);
		card11.setImage(new Image(getClass().getResource("../cardimg/"+dealerCard.get(0).toString()+".png").toString()));
		
		for(int i = 2; i < dealerCard.size(); i++) {
			Image img = new Image(getClass().getResource("../cardimg/"+dealerCard.get(i).toString()+".png").toString());
			dCard.getChildren().add(new ImageView(img));
			dCard.alignmentProperty().set(Pos.CENTER_RIGHT);
		}
		System.out.println(dealerCard);
		

	}
	
	private void insertUCard() {
		ArrayList<Card> playerCard = bj.playerCard;
		int count = playerCard.size()-1;
		String addCard = playerCard.get(count).getDeck(); // 마지막 인덱스 불러오기
		addCard = addCard.replaceAll(" ","");
		
		ImageView cardAdd;
		String path = "../cardimg/"+addCard+".png";
		URL url = getClass().getResource(path);
		cardAdd = new ImageView(url.toString());
		uCard.getChildren().add(cardAdd);
		
	}
}



