package c_return;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@FunctionalInterface
interface MyInterface{
	int method(int a, int b, String s);
}

public class RamdaExam {

	public static void main(String[] args) {
		MyInterface i = (a,b,s) ->{
			System.out.println(s);
			return a * b;
		};
		int result = i.method(10, 20, "곱셈연산");
		System.out.println("result : " + result);
		
		Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
			
		});
		
		set = new TreeSet<>((o1,o2)->{
			return o2 - o1;
		});
		
		set.add(10);
		set.add(5);
		set.add(7);
		System.out.println(set);
	}

}
