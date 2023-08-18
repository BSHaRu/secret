package c1_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListEx {
	/*	Collection Framework
	  다수의 데이터를 쉽고 효과적으로 처리할 수 있도록
	  표준화된 방법을 제공하는 class의 집합
	  - 데이터를 저장하는 자료 구조와 데이터를 처리하는 알고리즘을 구조화하여 클래스로 구현한 것
	 */

	public static void main(String[] args) {
		// 순서(index)가 존재하고 데이터 중복저장이 가능한 List
		ArrayList array = new ArrayList();
		array.add("문자열");
		array.add(100);
		
		String str = (String) array.get(0);
		System.out.println(array);
		
		ArrayList<String> strs = new ArrayList<>();
		strs.add("java");
		strs.add("JDBC");
		System.out.println(strs.size());
		strs.add("MYSQL");
		strs.add("mysql");
		String str2 = strs.get(0);
		System.out.println(str2);
//		System.out.println(strs.get(5)); // 해당 사이즈는 4인데 5라고 지정해서 해당 index 벗어났다고 오류뜸
		System.out.println("==========================================");
		for(int i=0; i<strs.size(); i++) {
			System.out.println(strs.get(i));
		}
		
		System.out.println("==========================================");
		for(String s : strs) {		// list도 향상된 for문 사용 가
			System.out.print(s+" ");
		}
		System.out.println();
		
		System.out.println("==========================================");
		strs.add(2,"Servlet/JSP");	// 해당 인덱스번호를 주고 추가해주면 해당 인덱스번호에 추가되고 기존꺼 밀림
		System.out.println(strs);
		
		strs.add("JDBC");			// 그냥 값을 추가해주면 맨 마지막인덱스에 해당 값이 저장됨
		System.out.println(strs);
		
		System.out.println("==========================================");
		// remove
		boolean isChecked = strs.remove("JDBC");	// 삭제되면 true , 없으면 false 반환
		System.out.println("isChecked : " + isChecked);
		System.out.println(strs);		// 전체삭제가 아니라 앞에서부터 찾아서 발견하면 탈출 
										// -> 그래서 앞에있는 JDBC만 삭제되고 뒤에는 삭제안됨
		System.out.println("==========================================");
		String result = strs.remove(2);
		System.out.println(result);
		System.out.println(strs);
		
		System.out.println("==========================================");
		isChecked = strs.contains("mysql");	// 해당값이 있으면 true, 없으면 false 반환
		System.out.println("isChecked : " + isChecked);
		
		result = strs.set(2, "Oracle");	
		System.err.println(result);
		System.out.println(strs);
		
		System.out.println("==========================================");
		isChecked = strs.isEmpty(); // list안에 값이 비어있으면 true, 하나라도 있으면 false 반환
		System.out.println("isEmpty : " + isChecked);
		strs.clear();				// 안에 해당값 모두 제거
		System.out.println(strs.size());
		System.out.println("isEmpty : " + strs.isEmpty());
		
		System.out.println("==========================================");
		
		strs.add("JAVA");
		strs.add("Java");
		strs.add("JaVa");
		strs.add("java");
		System.out.println(strs);
		
		// list -> array로 변환 시킴
		String[] ss = strs.toArray(new String[strs.size()]);
		System.out.println(Arrays.toString(ss));
		
		System.out.println("==========================================");
		// array -> list로 변환 시킴
		List<String> list = Arrays.asList(ss);
		System.out.println(list);
		
	} // main 종료
}
