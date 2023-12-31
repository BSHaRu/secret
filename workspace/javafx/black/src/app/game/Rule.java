package app.game;

import java.util.ArrayList;

import app.controller.Game;
import javafx.scene.control.TextArea;

public class Rule {

	TextArea log;
	Game gameCont;
	
	Rule(TextArea log, Game gameCont) {
		this.log = log;
		this.gameCont = gameCont;
	}

	// 카드 합산
	public int cardSum(String player, ArrayList<Card> deck) {

		int card = 0;

		card = getCardSum(deck);

		if (player.equals("player")) {
			log.appendText(player + "카드 총합 : " + card + "\n");
		}
		return card;
	}

	// 받은 카드의 합 계산, A,J,Q,K 숫자값 넣어주기
	public int getCardSum(ArrayList<Card> deck) {

		int cardSum = 0;

		for (int i = 0; i < deck.size(); i++) {
			String cardNum = deck.get(i).getNumber();
			// A = 1 , J,Q,K = 10
			if (cardNum.equals("ACE")) {
				cardSum += 1;
			} else if (cardNum.equals("J")) {
				cardSum += 10;
			} else if (cardNum.equals("Q")) {
				cardSum += 10;
			} else if (cardNum.equals("K")) {
				cardSum += 10;
			} else {
				cardSum += Integer.parseInt(cardNum);
			}
		}
		return cardSum;
	}

	// 버스트(카드합이 21 넘어가면 자동 패배)
	public boolean isBust(String bustPlayer, int cardSum) {
		if (cardSum > 21) {
			log.appendText(bustPlayer + "버스트 : " + cardSum + "\n") ;
			return true;
		} else {
			return false;
		}
	}

	public boolean isBust(int cardSum) {
		if (cardSum > 21) {
			return true;
		} else {
			return false;
		}
	}

	// 승패(21가까운 사람 승)
	// 값을 end page로 던져야함
	public void winner(int playerSum, int dealerSum) {
		int player = 21 - playerSum;
		int dealer = 21 - dealerSum;
		String text;
		if (player < dealer) {
			text = "player 승!\n";
		} else if (player == dealer) {
			text = "무승부 입니다.\n";
		} else {
			text = "dealer 승!\n";
		}
		gameCont.CardOpen();
		log.appendText(text);
		gameCont.disabledBtn();
	}
}
