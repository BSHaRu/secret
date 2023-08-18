package c9_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsEx {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for(int i=1; i<=45; i++) {
			list.add(i);
		}
		System.out.println(list);
		
		Collections.shuffle(list);	// shuffle : 랜덤으로 섞어줌
		System.out.println(list);
		List<Integer> lotto = list.subList(0, 6); // List<E> subList(int fromIndex, int toIndex);
		System.out.println(lotto);
		Collections.sort(lotto);	// 오름차순으로 정렬
		System.out.println(lotto);
		
		Collections.shuffle(list);
		List<Integer> myLotto = list.subList(0, 7);
		Collections.sort(lotto);
		System.out.println(myLotto);
		
		System.out.println(Collections.max(myLotto));
		System.out.println(Collections.min(myLotto));
		
		Collections.sort(list);		// 위에 shuffle 때문에 여기서 그냥 한번 더 정렬해준거임
		Collections.reverse(list); 	// 내림차순으로 정렬
		System.out.println(list);
	}

}
