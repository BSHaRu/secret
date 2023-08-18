package app.game;

import java.util.ArrayList;


import app.controller.Game;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class BlackJack {
	
	Dealer dealer;
	Player player;
	Rule rule;
	
	// 카드 덱
	ArrayList<Card> deck;	
	// 딜러카드
	public ArrayList<Card> dealerCard;	
	// 플레이어 카드
	public ArrayList<Card> playerCard;
	TextArea area;
	Game gameCont;
	
	int playerCardSum;
	int dealerCardSum;
	private int count =2;
	
	public BlackJack(TextArea log, Game gameCont){
		dealer = new Dealer();
		player = new Player();
		rule = new Rule(log, gameCont);
		
		deck = dealer.setCard();
		dealerCard = new ArrayList<Card>();
		playerCard = new ArrayList<Card>();
		
		area = log;
		this.gameCont = gameCont;
		
		for(int i = 1; i<=2; i++) {
			dealerCard.add(dealer.getCard(deck));
			playerCard.add(dealer.getCard(deck));
		}
		
		// 딜러 카드합계
		dealerCardSum = rule.cardSum("dealer", dealerCard);
		// 플레이어 카드합계
		playerCardSum = rule.cardSum("player", playerCard);
		String text = "당신의 받은 카드 : "+playerCard.get(0).getDeck()+" , "+playerCard.get(1).getDeck() + "\n";
		
		area.appendText(text);
	}
	
		
	public void hit() {
		
		
		// 카드 받기
		Card cardAdd = player.hit(dealer, deck);
		
		// 덱에 추가, 히트누른횟수만큼 추가
//		if(playerCard.size()) {
			
//		}
		playerCard.add(cardAdd);
		String text = "새로받은 카드 : "+playerCard.get(count).getDeck()+"\n";
		area.appendText(text);
		
		count++;
		
		// 추가한 덱의 합계
		playerCardSum = rule.cardSum("player", playerCard);
		
		// 플레이어가 버스트하면 종료
		if(rule.isBust("player",playerCardSum)) {
			area.appendText("dealer 승 \n");
			gameCont.disabledBtn();
			count =0;
			gameCont.CardOpen();
		}
		
		// 플레이어의 턴이 끝난 후 딜러의 카드합이 17 미만이면 17이상이 될 때까지 뽑기
		while(dealerCardSum<17) {
			dealerCard.add(dealer.getCard(deck));
			dealerCardSum =  rule.cardSum("dealer",  dealerCard);
			
		}
	}
	public void stay() {
		String text;
		// 딜러 카드 합계
		dealerCardSum = rule.getCardSum(dealerCard);
		// 딜러의 카드합이 17 미만이면 17이상이 될 때까지 뽑기
		while(dealerCardSum < 17) {
			dealerCard.add(dealer.getCard(deck));
			dealerCardSum =  rule.cardSum("dealer",  dealerCard);
		}
		// 17미만 일 때 딜러가 카드를 뽑다가 21이 넘어가면 player 승리
		if(rule.isBust("dealer",dealerCardSum)) {
			area.appendText("player 승 \n");
			gameCont.disabledBtn();
			gameCont.CardOpen();
		} else {
			// 딜러 카드의 합이 17~21인 경우 승자를 정해준다
			rule.winner(playerCardSum, dealerCardSum);
			area.appendText("딜러카드의 합계 : "+dealerCardSum + "\n");
		}
		count =0;
	}


	
}
	
