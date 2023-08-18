package c6_queue;

import java.util.LinkedList;
import java.util.Queue;

class Message{
	String order;	// 명령
	String to;		// 받는이
	
	public Message(String order, String to) {
		super();
		this.order = order;
		this.to = to;
	}

	@Override
	public String toString() {
		return "Message [order=" + order + ", to=" + to + "]";
	}
}

public class QueueEx {

	public static void main(String[] args) {
		Queue<Message> messageQueue = new LinkedList<>();
		messageQueue.offer(new Message("sendMail","홍길동")); 
		messageQueue.offer(new Message("sendSMS","고길동"));
		messageQueue.offer(new Message("sendKaKaoTalk","이순신"));
		System.out.println(messageQueue.size());
		Message peekMessage = messageQueue.peek();	// peek : 다음 작업이 뭔지 "확인"만 하는 친구임
		System.out.println(peekMessage.order);
		System.out.println(messageQueue.size());
		
		while(!messageQueue.isEmpty()) {
			Message m = messageQueue.poll();
			switch(m.order) {
				case "sendMail":
					System.out.println(m.to+"님에게 메일을 전송합니다.");
					break;
				case "sendSMS":
					System.out.println(m.to+"님에게 문자를 보냅니다.");
					break;
				case "sendKaKaoTalk":
					System.out.println(m.to+"님에게 카톡을 보냅니다.");
					break;
			}
			System.out.println(messageQueue.size());
		}// while문 종료
	}

}
