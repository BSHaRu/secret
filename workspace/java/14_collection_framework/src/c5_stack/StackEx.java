package c5_stack;

import java.util.*;

class Coin{
	private int value;
	
	public Coin(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}

public class StackEx {

	public static void main(String[] args) {
		// LIFO(Last In First Out)
		Stack<Coin> coinBox = new Stack<>(); // class Stack<E> extends Vector<E> 
		coinBox.push(new Coin(50));
		coinBox.push(new Coin(100));
		coinBox.push(new Coin(500));
		coinBox.push(new Coin(100));
		
		Coin coin = coinBox.peek();	// peek() : Stack에서 맨위에 저장되어있는 값이 뭔지 "확인"만 하는거임
		System.out.println("value : " + coin.getValue());
		System.out.println(coinBox.size());
		System.out.println("==========================================");
		
		while(!coinBox.isEmpty()) {	// coinBox가 비어있지 않으면 해당 while문 반복 -> 해당 값이 없으면 while문 종료
			coin = coinBox.pop();	// pop() : 맨 위에 저장된 값을 꺼내오는 친구
			System.out.println(coin.getValue()+"원");
			System.out.println(coinBox.size());
		}
		
	}

}
