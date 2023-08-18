package c3_map;

import java.util.*;

public class HashMapEx {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("홍길동", 100);
		map.put("김유신", 59);
		map.put("고길동", 98);
		map.put("이순신", 88);
		System.out.println(map);
		map.put("김유신", 80);		// key값은 유일해야되는데 중복이 될 경우
		System.out.println(map);	// 기존 key에 value값이 덮어씌워짐
		
		int score = map.get("홍길동");
		System.out.println("홍길동 점수 : " + score);
		System.out.println(map.size());
		System.out.println("==========================================");
		
		map.remove("김유신");
		System.out.println(map.size());
		System.out.println(map);
		System.out.println("==========================================");
		
		boolean isChecked = map.containsKey("고길동");
		System.out.println("key 존재 : " + isChecked);
		isChecked = map.containsValue(100);
		System.out.println("value 존재 : " + isChecked);
		System.out.println("==========================================");
		
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		
		Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator(); // 반복자 꺼내기
		while(iterator.hasNext()) {
			Map.Entry<String, Integer> entry = iterator.next();
			System.out.print("key : " + entry.getKey() + " ");
			System.out.println("value : " + entry.getValue());
			if(entry.getKey().equals("이순신")) {			//반복을 할껀데 entry에 "이순신"이 있으면 해당 if문 실행
//				map.remove(entry.getKey()); // 이렇게 map에 직접접근하면 오류 발생하니깐 iterator로 접근해야됨
				iterator.remove();
			} // if문 종료
		} // while문 종료
		System.out.println(map);
		System.out.println("==========================================");
		
		Set<String>keySet = map.keySet();
		Iterator<String> keyItr = keySet.iterator();
		while(keyItr.hasNext()) {
			String key = keyItr.next();
			int value = map.get(key);	// 여기서는 map에 직접 접근 가능한 이유는 map 크기변화에 아무런 문제가 없기때문임
			System.out.printf("key : %s,  value : %d | ",key,value);
		}
		System.out.println();
		
		for(String key : keySet) {		// 배열 | list | iterator 다 들어갈 수 있다고함 
			System.out.println("남은 key : " + key);
		}
		System.out.println("==========================================");
		
		Collection<Integer> values = map.values();
		for(int value : values) {
			System.out.print(value + ", ");
		}
		System.out.println();
	}
}
