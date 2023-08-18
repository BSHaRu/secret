package a.object.equals;

public class MemberEx {

	public static void main(String[] args) {
		Member member = new Member("id123");
		Member member1 = new Member("id123");
		Member member2 = new Member("id456");
		System.out.println(member == member1);
		System.out.println(member == member2);
		
		System.out.println(member.equals(member1));
		System.out.println(member.equals(member2));
		
	} // main 종료

}
