package c4_tree;

import java.util.*;

public class TreeMapEx {

	public static void main(String[] args) {
		TreeMap<Integer, String> map = new TreeMap<>();
		map.put(100, "홍길동");
		map.put(15, "고길동");
		map.put(88, "김유신");
		map.put(97, "이도");
		map.put(80, "이순신");
		System.out.println(map);
		
		Map.Entry<Integer, String> entry = null; // map은 key와 value가 한쌍이라 반환은 entry로 한다.
		entry = map.firstEntry();				// 나머지도 treeSet이랑 똑같은데 entry로 가져온다는것만 다름
		System.out.println("가장 낮은 key 값 : " + entry.getKey());
		System.out.println("가장 낮은 value 값 : " + entry.getValue());
		
		int i = map.firstKey();					// 아니면 아싸리 value를 제외하고 key값만 불러 올 수 있다.
		System.out.println("첫번째 key 값 : " + i);
	}

}
