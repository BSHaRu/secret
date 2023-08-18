package c2_set;

import java.util.HashSet;
import java.util.Set;

public class HashCodeEx {

	public static void main(String[] args) {
		String name1 = "홍길동";
		String name2 = "홍길동";
		String name3 = new String("홍길동");
		
		System.out.println(name1 == name3);
		System.out.println(name1.hashCode());
		System.out.println(name2.hashCode());
		System.out.println(name3.hashCode());
		
		Member member1 = new Member(name1,20);
		Member member2 = new Member(name2,22);
		Member member3 = new Member(name3,20);
		Set<Member> setMember = new HashSet<>();
		setMember.add(member1);
		setMember.add(member2);
		setMember.add(member3);
		System.out.println(setMember.size());
		System.out.println(setMember);
		
	}
}
