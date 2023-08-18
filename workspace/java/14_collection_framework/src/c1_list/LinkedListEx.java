package c1_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Board{
	String subject;
	String content;
	String writer;
	
	// alt + s + a 생성자 추가
	public Board(String subject, String content, String writer) {
		super();
		this.subject = subject;
		this.content = content;
		this.writer = writer;
	}
}

public class LinkedListEx {		// arrayList랑 linkedList 속도 비교

	public static void main(String[] args) {
		List<Board> arrayList = new ArrayList<>();
		List<Board> linkedList = new LinkedList<>();
		
		long startTime=0;
		long endTime=0;
		int size = 100000;
		
		System.out.println("[추가 작업]");
		startTime = System.nanoTime(); // 10억분의 1초
		
		for(int i=0; i < size; i++) {
			arrayList.add(0,new Board("제목","내용","작성자"));
		}
		endTime = System.nanoTime();
		System.out.println("array 추가시간 : " + (endTime-startTime)+ "ns");
		
		startTime = System.nanoTime();
		for(int i=0; i < size; i++) {
			linkedList.add(0,new Board("제목","내용","작성자"));
		}
		endTime = System.nanoTime();
		System.out.println("linked 추가시간 : "+(endTime-startTime)+"ns");
		
		System.out.println("===============================================");
		System.out.println("[검색 작업]");
		startTime = System.nanoTime();
		for(int i=0; i<size; i++) {
			arrayList.get(i);
		}
		endTime = System.nanoTime();
		System.out.println("array 검색시간 : " + (endTime-startTime)+ "ns");
		
		startTime = System.nanoTime();
		for(int i=0; i<size; i++) {
			linkedList.get(i);
		}
		endTime = System.nanoTime();
		System.out.println("linked 추가시간 : "+(endTime-startTime)+"ns");
	}

}
