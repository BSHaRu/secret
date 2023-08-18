package b.objects;

import java.util.Objects;

class A{}

public class ObjectsEx {

	public static void main(String[] args) {
		A a = null;
		A a1 = null;
		
		boolean isChecked = Objects.isNull(a);
		// a == null ? true : false;
		System.out.println("a : " + isChecked);
		
		isChecked = Objects.nonNull(a1);
		// a1 == !null ? true : false;
		System.out.println("a1 : " + isChecked);
		
//		isChecked = a.equals(a1); // 이러면 null로 비교하는거라 nullPoint 뜸
		isChecked = Objects.equals(a, a1); // Object를 이용하면 null로도 비교를 할 수 있다.
		System.out.println(isChecked);
		
		A c = new A();
		A d = c;
		isChecked = Objects.equals(a1, c);
		System.out.println(isChecked);
		
		isChecked = Objects.equals(d, c);
		System.out.println(isChecked);
		
		System.out.println("===================================");
		
		int[] arr1 = {1,2,3,4,5};
		int[] arr2 = {1,2,3,4,5};
		isChecked = Objects.equals(arr1, arr2);
		System.out.println(isChecked);
		
		isChecked = Objects.deepEquals(arr1, arr2);
		System.out.println(isChecked);
		
		System.out.println("===================================");
//		System.out.println(a1.toString()); // 이건 안되고 아래는 된다네?
		System.out.println(Objects.toString(arr2));
		System.out.println(Objects.toString(a));	//  return String.valueOf(o);
		System.out.println(Objects.toString(a, "a는 참조하는 값이 없습니다.")); // a가 null이면 해당문구를 출력해라....?
									// -> (o != null) ? o.toString() : nullDefault;
	}

}
