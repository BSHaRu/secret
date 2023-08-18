package c4_tree;

import java.util.*;

public class TreeSetEx {

	public static void main(String[] args) {
		TreeSet<Integer> treeSet = new TreeSet<>();
		treeSet.add(87);
		treeSet.add(75);
		treeSet.add(new Integer(90));
		treeSet.add(80);
		treeSet.add(80);				// 중복저장 안됨
		System.out.println(treeSet);	// 값이 들어간 순서가 아니라 오름차순으로 정렬된 상태로 나옴.
		
		Iterator<Integer> itr = treeSet.descendingIterator(); // descending : 역순으로 정리 
		while(itr.hasNext()) {								// -> 내림차순으로 값을 불러옴
			int i = itr.next();
			System.out.print(i+ " ");
		}
		System.out.println();
		System.out.println("==========================================");
		
		TreeSet<Integer> decendingSet = (TreeSet<Integer>) treeSet.descendingSet(); // 역순으로 정렬 된 set을 만듬
		System.out.println(decendingSet); // 기존 set은 변환 없고 
		System.out.println(treeSet);	// 역순으로 정렬 된 set을 새로 만들어 진거임 
		System.out.println("==========================================");
		
		int score = 0;
		score = treeSet.first();
		System.out.println("첫번째 값 : " + score); // 즉, 정렬되어있으니 최소값
		
		score = treeSet.last();
		System.out.println("마지막 값 : " + score); // 그래서 이건 최대값이 됨
		
		score = treeSet.lower(87);		// 해당 변수보다 낮은 수를 검색 -> 89일 경우 87을 가져옴
		System.out.println("87보다 낮은 수(바로 이전 값) : " + score);
		
		score = treeSet.higher(87);
		System.out.println("87보다 높은 수(바로 다음 값) : " + score);
		
		score = treeSet.floor(85);
		System.out.println("85이거나 그 이전 값 : " + score);
		
		score = treeSet.ceiling(85);
		System.out.println("85이거나 그 다음 값 : " + score);
		
		score = treeSet.pollFirst();
		System.out.println("첫번째 값을 꺼내오고 set에서 삭제 : " + score);
		System.out.println(treeSet);	// score = 75로 남아있긴함
		
		score = treeSet.pollLast();
		System.out.println("마지막 값을 꺼내오고 set에서 삭제 : " + score);
		System.out.println(treeSet);
				
	}

}
