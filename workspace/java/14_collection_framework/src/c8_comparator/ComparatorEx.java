package c8_comparator;

import java.util.Comparator;
import java.util.TreeSet;

public class ComparatorEx {

	public static void main(String[] args) {
		TreeSet<Fruit> treeSet = new TreeSet<>(new DecendingComparator()); // 3. 그래서 Comparator class만들어서 재정의 후 생성한다.
		treeSet.add(new Fruit("포도",3000));
		treeSet.add(new Fruit("딸기",1500));
		treeSet.add(new Fruit("수박",10000));
		System.out.println(treeSet); // 1. 이러면 정렬 기준이 없기때문에 오류발생 
//		2. 만약 Fruit class가 외부 class라서 수정을 못한다고 할 경우 Comparator를 쓰는거임 
		System.out.println("=====================================================================================================");
		
		Comparator<Fruit> compare = new Comparator<Fruit>() { // 4. 어짜피 한번 쓰고 버릴껀데 class 생성하면 메모리 낭비다
			@Override										// 5. 그래서 익명으로 생성해서 재정의 하는게 좋다.
			public int compare(Fruit o1, Fruit o2) {
				return o1.getPrice() - o2.getPrice(); 	// 오름차순 정리
			}
		}; 
		
		TreeSet<Fruit> treeSet2 = new TreeSet<>(compare);
		treeSet2.add(new Fruit("포도",3000));
		treeSet2.add(new Fruit("딸기",1500));
		treeSet2.add(new Fruit("수박",10000));
		System.out.println(treeSet2); 
	}

}
